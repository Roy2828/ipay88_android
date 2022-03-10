package com.ipay88_plugin

import android.app.Activity
import android.content.Intent
import com.ipay.Ipay
import com.ipay.IpayPayment
import com.ipay.IpayR
import io.flutter.plugin.common.MethodChannel
import java.util.HashMap

/**
 *    desc   :
 *    e-mail : 1391324949@qq.com
 *    date   : 2022/2/14 19:34
 *    author : Roy
 *    version: 1.0
 */
object Payment {

     var channel: MethodChannel?=null;  //必须为静态 不然 序列化会有问题

   var myIpayResultDelegate = MyIpayResultDelegate(object :IpayResult{
        override fun onPaymentSucceeded(p0: String?, p1: String?, p2: String?, p3: String?, p4: String?) {
            channel?.invokeMethod(ConstantEvent.ipay_success_to_flutter,"") //支付成功回调
        }

        override fun onPaymentFailed(p0: String?, p1: String?, p2: String?, p3: String?, p4: String?) {
            channel?.invokeMethod(ConstantEvent.ipay_error_to_flutter,"") //支付失败回调
        }

        override fun onPaymentCanceled(p0: String?, p1: String?, p2: String?, p3: String?, p4: String?) {
            channel?.invokeMethod(ConstantEvent.ipay_cancle_to_flutter,"") //支付取消回调
        }

    })

      fun ipay88(activity: Activity, arguments: Any?) {
          val payment = IpayPayment()
          arguments?.apply {
               var map:HashMap<String,String?>? = arguments as HashMap<String,String?>?
              payment.setMerchantKey(map?.get("merchantKey"))//（必填） - iPay88 提供的商户密钥。例如苹果88KEY。
              payment.setMerchantCode(map?.get("merchantCode")) //有 （必填） - iPay88 提供的商户代码。例如 M09999。
              payment.setPaymentId(map?.get("paymentId")) //PaymentId 是用于请求将显示在 iPay88 支付页面上的支付方式的值。
              payment.setCurrency(map?.get("currency"))//（必需）- 基于 ISO 标准的货币代码。例如印尼盾。
              payment.setRefNo(map?.get("refNo"))//（必填）- 用于商户参考目的的参考号，对于每笔交易必须是唯一的。
              payment.setAmount(map?.get("amount"))//（必填）- 账单付款的最终总额，保留小数点后 2 位。例如 10.90。对于测试，请使用 1.00。
              payment.setProdDesc(map?.get("prodDesc")) //（必填）- 客户购买的产品的描述。
              payment.setUserName(map?.get("userName")) //（必填） - 商户系统中的客户名称
              payment.setUserEmail(map?.get("userEmail"))//（必填） - 商家系统中客户的电子邮件地址，电子邮件格式有效
              payment.setUserContact(map?.get("userContact"))//必填） - 商户系统中的客户联系电话。
              payment.setRemark(map?.get("remark"))//（可选）- 特定交易的记录。注意：不允许使用特殊字符。
              payment.setLang(map?.get("lang"))//可选）- 语言。例如，ISO-8859-1。
              payment.setCountry(map?.get("country"))//（必填） - 设置此部分以连接 iPay88 支付网关。需要用 IDR 填写此部分。重要提示： 确保商家已经在 iPay88 Indonesia 拥有商家账户。
              payment.setBackendPostURL(map?.get("backendPostURL"))//（必填） - 支付成功后指定有效的商户回调 URL。例如 http://www.ipay88shop.com/backend_page。
          }


        val checkoutIntent: Intent = Ipay.getInstance().checkout(
                payment,
                activity,
               myIpayResultDelegate)

        activity.startActivityForResult(checkoutIntent, 1)
    }

    fun ipay88Request(activity: Activity, arguments: Any?){
        val payment = IpayR()
        arguments?.apply {
            var map:HashMap<String,String?>? = arguments as HashMap<String,String?>?
            payment.setMerchantCode(map?.get("merchantCode")) //有 （必填） - iPay88 提供的商户代码。例如 M09999。
            payment.setRefNo(map?.get("refNo"))//（必填）- 用于商户参考目的的参考号，对于每笔交易必须是唯一的。
            payment.setAmount(map?.get("amount"))//（必填）- 账单付款的最终总额，保留小数点后 2 位。例如 10.90。对于测试，请使用 1.00。
        }


        val checkoutIntent: Intent = Ipay.getInstance().requery(
            payment,
            activity,
            myIpayResultDelegate
        )

        activity.startActivityForResult(checkoutIntent, 1)
    }
}



