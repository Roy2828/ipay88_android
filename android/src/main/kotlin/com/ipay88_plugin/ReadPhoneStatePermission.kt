package com.ipay88_plugin

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import io.flutter.plugin.common.MethodChannel

/**
 *    desc   :
 *    e-mail : 1391324949@qq.com
 *    date   : 2022/2/24 18:10
 *    author : Roy
 *    version: 1.0
 */
class ReadPhoneStatePermission {
    companion object{
        val REQUEST_READ_PHONE_STATE = 0
    }
   var result: MethodChannel.Result?=null;

    fun checkPermissionCheck(activity: Activity, result: MethodChannel.Result) {
       this.result = result;
        val permissionCheck =
            ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_PHONE_STATE)

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions( //请求权限
                activity,
                arrayOf(Manifest.permission.READ_PHONE_STATE,Manifest.permission.READ_PHONE_NUMBERS),
                REQUEST_READ_PHONE_STATE
            )
        } else { //权限通过
            result.success(true)
        }
    }


    fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>?,
        grantResults: IntArray?
    ) {
        when (requestCode) {
            REQUEST_READ_PHONE_STATE ->
                if (grantResults != null) {
                    if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        result?.success(true)
                    }else{
                        result?.success(false)
                    }
                }
            else -> {
                print("")
            }
        }


    }
}