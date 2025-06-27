package com.jetpackcompose.compose_path_android.util.permission

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.jetpackcompose.compose_path_android.MainActivity

interface PermissionBehavior {
    fun isGranted(context: Context, permissionType: PermissionType): Boolean
    fun shouldShowRationale(activity: Activity, permissionType: PermissionType): Boolean
}

open class BasePermissionBehavior : PermissionBehavior {
    override fun isGranted(context: Context, permissionType: PermissionType): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            permissionType.permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    override fun shouldShowRationale(
        activity: Activity,
        permissionType: PermissionType
    ): Boolean {
       return ActivityCompat.shouldShowRequestPermissionRationale(activity, permissionType.permission)
    }
}
