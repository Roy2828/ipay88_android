package com.ipay88_plugin

import java.io.Serializable

/**
 *    desc   :
 *    e-mail : 1391324949@qq.com
 *    date   : 2022/2/14 20:47
 *    author : Roy
 *    version: 1.0
 */
interface IpayResult : Serializable {
    fun onPaymentSucceeded(
            p0: String?,
            p1: String?,
            p2: String?,
            p3: String?,
            p4: String?
    )

    fun onPaymentFailed(p0: String?, p1: String?, p2: String?, p3: String?, p4: String?)



    fun onPaymentCanceled(
            p0: String?,
            p1: String?,
            p2: String?,
            p3: String?,
            p4: String?
    )



}