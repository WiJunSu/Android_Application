package com.example.qqw;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Webview extends Activity {
    EditText edit;
    Button bt1, bt2, bt3;
    WebView web;
    String domain;
    String base = "http://";
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        setTitle("인터넷 검색");

        this.edit = (EditText) findViewById(R.id.edit);
        this.bt1 = (Button) findViewById(R.id.bt1);
        this.bt2 = (Button) findViewById(R.id.bt2);
        this.bt3 = (Button) findViewById(R.id.bt3);
        web = (WebView) findViewById(R.id.web);


        web.setWebViewClient(new CookWebViewClient());

        WebSettings webSet = web.getSettings();
        webSet.setBuiltInZoomControls(true);

        bt1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                domain = edit.getText().toString();
                if (domain.equals(base) == true || domain.equals("") == true) {
                    web.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(),
                            "주소를 정확히 입력해 주세요", Toast.LENGTH_SHORT).show();
                } else {
                    web.setVisibility(View.VISIBLE);
                    web.loadUrl(domain);
                    count++;
                }
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                count--;
                web.goBack();
                if (count == 0) {
                    edit.setText(base);
                    web.setVisibility(View.INVISIBLE);
                }
            }
        });

        bt3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if(count>=1) {
                    web.setVisibility(View.VISIBLE);
                    web.goForward();
                }
            }
        });
    }
}