package com.example.jerometian.webviewtest;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;


/**
 * Created by jjtian on 2015/11/16.
 */
public class MainActivity extends AppCompatActivity {

    private WebView webView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

//        MainActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.main_activity);

        webView = (WebView) findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String
                    url) {
                view.loadUrl(url); // 根据传入的参数再去加载新的网页
                return true; // 表示当前WebView可以处理打开新网页的请求，不用借助系统浏览器
            }
        });
        webView.loadUrl("http://www.baidu.com");
    }
}
