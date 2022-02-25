package com.xgr.easypay.demo;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.xgr.alipay.alipay.AliPay;
import com.xgr.alipay.alipay.AlipayInfoImpli;
import com.xgr.easypay.EasyPay;
import com.xgr.easypay.callback.IPayCallback;
import com.xgr.unionpay.unionpay.Mode;
import com.xgr.unionpay.unionpay.UnionPay;
import com.xgr.unionpay.unionpay.UnionPayInfoImpli;
import com.xgr.wechatpay.wxpay.WXPay;
import com.xgr.wechatpay.wxpay.WXPayInfoImpli;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.unionpay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unionpay();
            }
        });
        findViewById(R.id.wxpay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wxpay();
            }
        });
        findViewById(R.id.alipay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alipay();
            }
        });
    }

    /**
     * 银联提供了测试帐号：
     *测试卡号信息：
     *借记卡：6226090000000048
     *手机号：18100000000
     *密码：111101
     *短信验证码：123456
     *（短信验证码记得点下获取验证码之后再输入）
     * 测试订单生成网址：http://101.231.204.84:8091/sim/getacptn，生成后直接传入setTn()。
     */
    private void unionpay(){
        //实例化银联支付策略
        UnionPay unionPay = new UnionPay();
        //构造银联订单实体。一般都是由服务端直接返回。测试时可以用Mode.TEST,发布时用Mode.RELEASE。
        UnionPayInfoImpli unionPayInfoImpli = new UnionPayInfoImpli();
        unionPayInfoImpli.setTn("625623784203097901200");
        unionPayInfoImpli.setMode(Mode.TEST);
        //策略场景类调起支付方法开始支付，以及接收回调。
        EasyPay.pay(unionPay, this, unionPayInfoImpli, new IPayCallback() {
            @Override
            public void success() {
                toast("支付成功");
            }

            @Override
            public void failed(int code, String message) {
                toast("支付失败");
            }

            @Override
            public void cancel() {
                toast("支付取消");
            }
        });
    }

    private void wxpay(){
        //实例化微信支付策略
        WXPay wxPay = WXPay.getInstance();
        //构造微信订单实体。一般都是由服务端直接返回。
        WXPayInfoImpli wxPayInfoImpli = new WXPayInfoImpli();
        wxPayInfoImpli.setTimestamp("");
        wxPayInfoImpli.setSign("");
        wxPayInfoImpli.setPrepayId("");
        wxPayInfoImpli.setPartnerid("");
        wxPayInfoImpli.setAppid("");
        wxPayInfoImpli.setNonceStr("");
        wxPayInfoImpli.setPackageValue("");
        //策略场景类调起支付方法开始支付，以及接收回调。
        EasyPay.pay(wxPay, this, wxPayInfoImpli, new IPayCallback() {
            @Override
            public void success() {
                toast("支付成功");
            }

            @Override
            public void failed(int code, String message) {
                toast("支付失败");
            }

            @Override
            public void cancel() {
                toast("支付取消");
            }
        });
    }

    private void alipay(){
        //实例化支付宝支付策略
        AliPay aliPay = new AliPay();
        //构造支付宝订单实体。一般都是由服务端直接返回。
        AlipayInfoImpli alipayInfoImpli = new AlipayInfoImpli();
        alipayInfoImpli.setOrderInfo("");
        //策略场景类调起支付方法开始支付，以及接收回调。
        EasyPay.pay(aliPay, this, alipayInfoImpli, new IPayCallback() {
            @Override
            public void success() {
                toast("支付成功");
            }

            @Override
            public void failed(int code, String message) {
                toast("支付失败");
            }

            @Override
            public void cancel() {
                toast("支付取消");
            }
        });
    }

    private void toast(String context){
        Toast.makeText(this,context,Toast.LENGTH_LONG).show();
    }
}
