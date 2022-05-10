package com.sharkBytesLab.torch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

public class TermsActivity extends AppCompatActivity {

    private WebView termsWebView;
    private ImageView termsArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);

        termsArrow = findViewById(R.id.terms_back_arrow);
        termsWebView = findViewById(R.id.termsWebView);

        try {

            termsWebView.getSettings().setJavaScriptEnabled(true);
            termsWebView.loadUrl("file:///android_asset/terms.html");
        } catch (Exception e) {
            e.printStackTrace();
        }

        termsArrow.setOnClickListener(new View.OnClickListener() {
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