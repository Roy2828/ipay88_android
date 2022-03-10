package com.ipay88_plugin

/**
 *    desc   :
 *    e-mail : 1391324949@qq.com
 *    date   : 2022/2/14 19:46
 *    author : Roy
 *    version: 1.0
 */
class ConstantEvent { //注册事件 用于原生和flutter通信
   companion object{
        val ipay_success_to_flutter = "ipay_success_to_flutter"//原生回调给flutter
        val ipay_error_to_flutter =  "ipay_error_to_flutter"//原生回调给flutter
        val ipay_cancle_to_flutter = "ipay_cancle_to_flutter"//原生回调给flutter



       val method_channel_name = "ipay88_plugin"  //注册通道名字

       val from_flutter_ipay88 = "from_flutter_ipay88" //flutter调用原生 只能支持到android9.0

       val from_flutter_ipay88Request = "from_flutter_ipay88Request";//flutter调用原生这个支持android10.0

       val from_flutter_ipay88_check_permission = "from_flutter_ipay88_check_permission"//flutter调用原生检测权限

   }
}