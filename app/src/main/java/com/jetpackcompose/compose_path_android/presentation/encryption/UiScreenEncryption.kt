package com.jetpackcompose.compose_path_android.presentation.encryption

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun UiScreenEncryption() {
    val context = LocalContext.current
    val encryptionManager = remember { EncryptionManager(context) }
    var data by remember { mutableStateOf("Omkar Sawant" )}
    var encryptedText by remember { mutableStateOf("") }
    var decryptedText by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            Text("🔐 AES Encryption Demo", style = MaterialTheme.typography.headlineSmall)
        },
        modifier = Modifier.fillMaxSize()
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            OutlinedTextField(
                value = data,
                textStyle = TextStyle(color = Color.Black),
                onValueChange = {
                    data = it
                    encryptedText = encryptionManager.encryptData(data)
                    decryptedText = encryptionManager.decryptData(encryptedText)
                },
                label = { Text("Enter text") },
                modifier = Modifier.fillMaxWidth()
            )

            Text("Encrypted: $encryptedText", style = MaterialTheme.typography.bodyMedium)
            Text("Decrypted: $decryptedText", style = MaterialTheme.typography.bodyMedium)
        }
    }

}
