package kdo.desafiotokenlab.visao;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.VideoView;

import java.net.URL;

import kdo.desafiotokenlab.R;

public class TelaVideo extends AppCompatActivity {
    String URL_VIDEO = "";
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_video);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            Bundle bundle = getIntent().getExtras();
            URL_VIDEO = bundle.getString("trailerVideo");
            webView = (WebView) findViewById(R.id.webViewOnline);

                    final WebView myWebView = (WebView) findViewById(R.id.webViewOnline);
                    myWebView.setWebViewClient(new WebViewClient());
                    myWebView.getSettings().setJavaScriptEnabled(true);
                    myWebView.setWebChromeClient(new WebChromeClient());

                    try {
                        Log.e("Status", "tried url");
                        myWebView.loadUrl(URL_VIDEO);

                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e("Status", "exception");
                    }
        } catch (Exception e) {
            Log.d("PLAYER",e.getMessage());
        }
    }
}
