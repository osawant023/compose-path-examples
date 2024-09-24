package com.jetpackcompose.compose_path_android

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions.RESULT_FORMAT_JPEG
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions.RESULT_FORMAT_PDF
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions.SCANNER_MODE_FULL
import com.google.mlkit.vision.documentscanner.GmsDocumentScanning
import com.google.mlkit.vision.documentscanner.GmsDocumentScanningResult
import com.jetpackcompose.compose_path_android.ui.theme.ComposePathTheme
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ComposePathTheme(dynamicColor = false) {
                val localActivity = staticCompositionLocalOf<ComponentActivity> { noLocalProvidedFor("LocalActivity") }
                CompositionLocalProvider(value = localActivity provides this) {
                    DocumentScannerApp()
                }
            }
        }
    }

    private fun noLocalProvidedFor(localActivity: String): Nothing {
        error("CompositionLocal $localActivity not present")
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposePathTheme(dynamicColor = false) {
        DocumentScannerApp()
    }
}

@Composable
fun DocumentScannerApp() {
    val activity = LocalContext.current as Activity
    val snackbarHostState = remember { SnackbarHostState() }
    val options = remember {
        GmsDocumentScannerOptions.Builder()
            .setScannerMode(SCANNER_MODE_FULL)
            .setGalleryImportAllowed(true)
            .setPageLimit(5)
            .setResultFormats(RESULT_FORMAT_PDF, RESULT_FORMAT_JPEG)
            .build()
    }
    val scanner = remember { GmsDocumentScanning.getClient(options) }

    var imageUris by remember {
        mutableStateOf<List<Uri>>(emptyList())
    }
    val scannerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult()) {
        if (it.resultCode == RESULT_OK) {
            //Show images

            val result =  GmsDocumentScanningResult.fromActivityResultIntent(it.data)
            imageUris = result?.pages?.map { it.imageUri } ?: emptyList()

            //Save PDF
            result?.pdf?.let { pdf ->
                val fos = FileOutputStream(File(activity.filesDir, "scan_document.pdf"))
                activity.contentResolver.openInputStream(pdf.uri)?.use { inputStream ->
                    inputStream.copyTo(fos)
                }
            }
        }
    }


    val cs = rememberCoroutineScope()
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState )
        },
        modifier = Modifier.fillMaxSize()) { padding ->
        Surface {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .scrollable(rememberScrollState(), orientation = Orientation.Horizontal)) {
                    imageUris.forEach { uri ->
                        AsyncImage(
                            model = uri,
                            contentDescription = null,
                            contentScale = ContentScale.FillWidth,
                            modifier = Modifier
                                .fillMaxSize()
                        )
                    }
                }

                Text(
                    modifier = Modifier
                        .padding(15.dp)
                        .width(IntrinsicSize.Max)
                        .height(IntrinsicSize.Max)
                        .align(Alignment.BottomEnd)
                        .background(MaterialTheme.colorScheme.secondary, shape = RoundedCornerShape(10.dp))
                        .clickable(role = Role.Button) {
                            scanner
                                .getStartScanIntent(activity)
                                .addOnSuccessListener { intent ->
                                    scannerLauncher.launch(
                                        IntentSenderRequest
                                            .Builder(intent)
                                            .build()
                                    )
                                }
                                .addOnFailureListener {
                                    cs.launch { snackbarHostState.showSnackbar(it.message ?: "") }
                                }
                        }
                        .padding(10.dp),
                    text = "Scan Document +",
                    textAlign = TextAlign.Center,
                    style = TextStyle(textAlign = TextAlign.Center),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}


