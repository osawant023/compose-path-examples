package com.jetpackcompose.compose_path_android.util.permission

import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

enum class PermissionResult(){
    GRANTED,
    DENIED,
    DENIED_FOREVER
}

class PermissionManager(
    private val activity: ComponentActivity,
    private val behavior: PermissionBehavior,
) {

    private var onGrantedCallback: ((PermissionResult) -> Unit) ?= null
    private lateinit var permissionType : PermissionType
    private val launcher: ActivityResultLauncher<String> = activity.registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            if (granted) {
                onGrantedCallback?.invoke(PermissionResult.GRANTED)
                onGrantedCallback = null
            }else if (behavior.shouldShowRationale(activity, permissionType)){
                onGrantedCallback?.invoke(PermissionResult.DENIED)
            }else{
                onGrantedCallback?.invoke(PermissionResult.DENIED_FOREVER)
            }
        }

    fun request(permissionType: PermissionType, onResult: (PermissionResult) -> Unit) {
        onGrantedCallback = onResult
        this.permissionType = permissionType
        if (!behavior.isGranted(activity, permissionType)) {
            launcher.launch(permissionType.permission)
        } else {
            onGrantedCallback?.invoke(PermissionResult.GRANTED)
        }
    }
}