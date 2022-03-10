import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:ipay88_plugin/ipay88_plugin.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {

  @override
  void initState() {
    super.initState();
    initPlatformState();
    //TODO 回调类型待定
    Ipay88Plugin.addIpay88Listener(onReceiveIpaySuccess:(String? message) async {
         //接收成功的
    },onReceiveIpayError: (String? message) async {
         //失败的
    },onReceiveIpayCancle: (String? message) async {
        //取消的
    });
  }

  // Platform messages are asynchronous, so we initialize in an async method.
   void  initPlatformState()   async{
    if(await Ipay88Plugin.checkPermission()){
      Ipay88Plugin.platformIpay88Request();
    }

  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: Text('Running on: '),
        ),
      ),
    );
  }
}
