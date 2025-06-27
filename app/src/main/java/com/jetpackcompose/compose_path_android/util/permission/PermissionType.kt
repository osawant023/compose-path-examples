package com.jetpackcompose.compose_path_android.util.permission

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.Settings


data class PermissionConfig(
    val title: String,
    val subTitle: String,
    val positiveBtnText: String,
    val negativeBtnText: String
)

interface PermissionType {
    val permission: String
    val permissionConfig:PermissionConfig
    fun navigateToSetting(activity: Activity){}
}

sealed class AppPermission{
    data object Camera : PermissionType {
        override val permission = android.Manifest.permission.CAMERA
        override val permissionConfig: PermissionConfig = PermissionConfig("Camera Permission", "Camera Permission", "Ok", "Cancel")
        override fun navigateToSetting(activity: Activity) {
            activity.startActivity(
                Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts("package", activity.packageName, null))
            )
        }
    }

    data object Location : PermissionType {
        override val permission = android.Manifest.permission.ACCESS_FINE_LOCATION
        override val permissionConfig: PermissionConfig = PermissionConfig("Camera Permission", "Camera Permission", "Ok", "Cancel")
        override fun navigateToSetting(activity: Activity) {
            activity.startActivity(
                Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS, Uri.fromParts("package", activity.packageName, null))
            )
        }
    }

}






