import 'dart:async';
import 'dart:collection';

import 'package:flutter/services.dart';

typedef Future<dynamic> MethodCallHandler(String? event);

class Ipay88Plugin {
  static const MethodChannel _channel = MethodChannel('ipay88_plugin');

  //这个只能支持到android9.0
  static void platformIpay88({String? merchantKey,String? merchantCode
    ,String? paymentId,String? currency,String? refNo,String? amount,String? prodDesc,
    String? userName,String? userEmail,String? userContact,String? remark,
    String? lang,String? country,String? backendPostURL
  }) {
    Map<String, dynamic> arguments = HashMap();
    arguments["merchantKey"] = merchantKey;
    arguments["merchantCode"] = merchantCode;
    arguments["paymentId"] = paymentId;
    arguments["currency"] = currency;
    arguments["refNo"] = refNo;
    arguments["amount"] = amount;
    arguments["prodDesc"] = prodDesc;
    arguments["userName"] = userName;
    arguments["userEmail"] = userEmail;
    arguments["userContact"] = userContact;
    arguments["remark"] = remark;
    arguments["lang"] = lang;
    arguments["country"] = country;
    arguments["backendPostURL"] = backendPostURL;
    _channel.invokeMethod('from_flutter_ipay88',arguments);
  }


  //这个支持android 10
  static void platformIpay88Request({String? merchantCode
    ,String? refNo,String? amount
  }) {
    Map<String, dynamic> arguments = HashMap();
    arguments["merchantCode"] = merchantCode;
    arguments["refNo"] = refNo;
    arguments["amount"] = amount;
    _channel.invokeMethod('from_flutter_ipay88Request',arguments);
  }

  //加这个权限是因为Android需要读取设备id 但是 《permission_handler: ^8.1.2  #权限管理》这个库没有这个权限
  static Future<bool> checkPermission() async{
     return await _channel.invokeMethod('from_flutter_ipay88_check_permission');
  }

  static addIpay88Listener({
    MethodCallHandler? onReceiveIpaySuccess,
    MethodCallHandler? onReceiveIpayError,
    MethodCallHandler? onReceiveIpayCancle,
  }) async {

    _channel.setMethodCallHandler((call) async {
      if (call.method == "ipay_success_to_flutter") {
        //接收原生传递过来的数据
        if (onReceiveIpaySuccess != null) {
          onReceiveIpaySuccess( call.arguments);
        }
      } else if (call.method == "ipay_error_to_flutter") {
        if (onReceiveIpayError != null) {
          onReceiveIpayError( call.arguments);
        }
      } else if (call.method == "ipay_cancle_to_flutter") {
        if(onReceiveIpayCancle !=null){
          onReceiveIpayCancle( call.arguments);
        } 
      }
    });
  }
}
