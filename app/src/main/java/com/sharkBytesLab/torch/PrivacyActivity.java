package com.sharkBytesLab.torch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

public class PrivacyActivity extends AppCompatActivity {

    private WebView privacyWebView;
    private ImageView privacyArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy);

        privacyArrow = findViewById(R.id.privacy_back_arrow);
        privacyWebView = findViewById(R.id.privacyWebView);

        try {

            privacyWebView.getSettings().setJavaScriptEnabled(true);
            privacyWebView.loadUrl("file:///android_asset/privacy.html");
        } catch (Exception e) {
            e.printStackTrace();
        }

        privacyArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(intent);
                finish();

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(getApplicationContext(), MenuActivity.class));
        finish();

    }

}