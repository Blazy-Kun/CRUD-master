package com.example.crud;

import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

class SSLTolerantWebViewClient extends WebViewClient {
    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        //    super.onReceivedSslError(view, handler, error);
        handler.proceed();
    }
}
