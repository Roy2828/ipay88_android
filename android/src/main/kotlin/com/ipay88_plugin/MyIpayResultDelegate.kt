package com.ipay88_plugin

import com.ipay.IpayResultDelegate
import io.flutter.plugin.common.MethodChannel
import java.io.Serializable

/**
 *    desc   :
 *    e-mail : 1391324949@qq.com
 *    date   : 2022/2/14 19:34
 *    author : Roy
 *    version: 1.0
 */
open class MyIpayResultDelegate : IpayResultDelegate, Serializable {
    constructor()
    constructor(ipayResult: IpayResult?){
        this.ipayResult  = ipayResult
    }

    var ipayResult: IpayResult?=null;


    override fun onPaymentSucceeded(
            p0: String?,
            p1: String?,
            p2: String?,
            p3: String?,
            p4: String?
    ) {
        ipayResult?.onPaymentSucceeded(p0,p1,p2,p3,p4)

    }

    override fun onPaymentFailed(p0: String?, p1: String?, p2: String?, p3: String?, p4: String?) {
        ipayResult?.onPaymentFailed(p0,p1,p2,p3,p4)
    }

    override fun onPaymentCanceled(
            p0: String?,
            p1: String?,
            p2: String?,
            p3: String?,
            p4: String?
    ) {
        ipayResult?.onPaymentCanceled(p0,p1,p2,p3,p4)
    }

    override fun onRequeryResult(p0: String?, p1: String?, p2: String?, p3: String?) {

    }

}