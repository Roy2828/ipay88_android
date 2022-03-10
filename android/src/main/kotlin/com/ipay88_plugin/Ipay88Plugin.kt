package com.ipay88_plugin

import android.app.Activity
import android.icu.text.MessagePattern
import androidx.annotation.NonNull

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry


class Ipay88Plugin: FlutterPlugin, MethodCallHandler, ActivityAware {

  private lateinit var channel : MethodChannel
   var activity:Activity?=null;
  private var readPhoneStatePermission:ReadPhoneStatePermission?=null

  override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {

    channel = MethodChannel(flutterPluginBinding.binaryMessenger, ConstantEvent.method_channel_name)
    channel.setMethodCallHandler(this)
    Payment.channel = channel
    readPhoneStatePermission = ReadPhoneStatePermission();

  }

  override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
    if (call.method == ConstantEvent.from_flutter_ipay88) { //这个支付只能支持到android9.0
      activity?.apply {
        Payment.ipay88(this,call.arguments)
      }

    }else if(call.method == ConstantEvent.from_flutter_ipay88Request){//这个支付支持到android10.0
        activity?.apply {
            Payment.ipay88Request(this,call.arguments)
        }
    }else if(call.method == ConstantEvent.from_flutter_ipay88_check_permission){ //获取设备id需要的权限只能到android9.0  api28
       activity?.apply {
         readPhoneStatePermission?.checkPermissionCheck(this,result)
       }
    } else {
      result.notImplemented()
    }
  }


  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
  }

  override fun onAttachedToActivity(binding : ActivityPluginBinding) {
        activity = binding.activity;

       binding.addRequestPermissionsResultListener(object :PluginRegistry.RequestPermissionsResultListener{
       override fun onRequestPermissionsResult(
         requestCode: Int,
         permissions: Array<out String>?,
         grantResults: IntArray?
       ): Boolean {
          readPhoneStatePermission?.onRequestPermissionsResult(requestCode,permissions,grantResults)
         return false
       }

     })
  }

  override fun onDetachedFromActivityForConfigChanges() {

  }

  override fun onReattachedToActivityForConfigChanges(p0: ActivityPluginBinding) {

  }

  override fun onDetachedFromActivity() {

  }

}
