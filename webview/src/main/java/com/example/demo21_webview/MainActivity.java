package com.example.demo21_webview;
/**
 * webview使用步骤:
 * 1.搭配环境
 * 2.再布局中创建webview
 * 3.使用webview加载网页
 * 4.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ProgressBar;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @Bind(R.id.path_et)
    EditText path_et;
    @Bind(R.id.webView_pb)
    ProgressBar webView_pb;
    @Bind(R.id.www_wb)
    WebView www_wb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //如果直接加载,再一些机型上会跳转到浏览器上,因此要做一些特殊操作
        //初始化操作
        webviewinit();
        //初始化设置
        webviewsettingInit();
    }

    private void webviewsettingInit() {
        //加载一个网址,跳转到浏览器进行加载,点击事件监听
        www_wb.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        //指UI发送改变时进行各种监听
        www_wb.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                //让进度条显示
                webView_pb.setVisibility(View.VISIBLE);
                //对进度条设置进度参数
                webView_pb.setProgress(newProgress);
                if (newProgress == 100) {
                    //让进度条消失
                    webView_pb.setVisibility(View.GONE);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
    }

    private void webviewinit() {
        WebSettings settings = www_wb.getSettings();
        //使webview支持JS
        settings.setJavaScriptEnabled(true);
        //使webview可以自动适配
        settings.setSupportZoom(true);
        //.只加载文字,不加载图片,为用户省流量
        settings.setBlockNetworkImage(true);
    }

    public void load(View view) {
        www_wb.loadUrl("http://www.qq.com");
    }

    public void advance(View view) {

    }

    public void back(View view) {

    }

    public void refresh(View view) {

    }
}
