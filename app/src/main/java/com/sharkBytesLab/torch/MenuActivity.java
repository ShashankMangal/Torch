package com.sharkBytesLab.torch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxAdView;
import com.applovin.mediation.ads.MaxInterstitialAd;

import java.util.concurrent.TimeUnit;

public class MenuActivity extends AppCompatActivity implements MaxAdListener {

    private CardView ratings, review, terms, privacy;
    private MaxInterstitialAd interstitialAd;
    private int retryAttempt;
    private MaxAdView adView;
    TextView support, watch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ratings = findViewById(R.id.ratings);
        review = findViewById(R.id.review);
        terms = findViewById(R.id.terms);
        privacy = findViewById(R.id.privacy);
        support = findViewById(R.id.supportUs);
        watch = findViewById(R.id.watch_video);
        adView = findViewById(R.id.applovinAd);

        adView.loadAd();

        interstitialAd = new MaxInterstitialAd( "19a73b4927442c34", this );
        interstitialAd.setListener( this );

        // Load the first ad
        interstitialAd.loadAd();

        watch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this, RewardActivity.class));
            }
        });
        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName());
                Intent i = new Intent(Intent.ACTION_VIEW, uri);

                try {
                    startActivity(i);
                } catch (Exception e) {

                    Toast.makeText(getApplicationContext(), "Error :" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });

        support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( interstitialAd.isReady() )
                {
                    interstitialAd.showAd();
                }
            }
        });

        ratings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName());
                Intent i = new Intent(Intent.ACTION_VIEW, uri);

                try {
                    startActivity(i);
                } catch (Exception e) {

                    Toast.makeText(getApplicationContext(), "Error :" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });


        terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), TermsActivity.class));
                finish();

            }
        });


        privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), PrivacyActivity.class));
                finish();

            }
        });



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();

    }

    // MAX Ad Listener
    @Override
    public void onAdLoaded(final MaxAd maxAd)
    {
        // Interstitial ad is ready to be shown. interstitialAd.isReady() will now return 'true'

        // Reset retry attempt
        retryAttempt = 0;
        Toast.makeText(this, "Open Torch...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAdLoadFailed(final String adUnitId, final MaxError error)
    {
        // Interstitial ad failed to load
        // AppLovin recommends that you retry with exponentially higher delays up to a maximum delay (in this case 64 seconds)

        retryAttempt++;
        long delayMillis = TimeUnit.SECONDS.toMillis( (long) Math.pow( 2, Math.min( 6, retryAttempt ) ) );

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                interstitialAd.loadAd();
            }
        }, delayMillis );
    }

    @Override
    public void onAdDisplayFailed(final MaxAd maxAd, final MaxError error)
    {
        // Interstitial ad failed to display. AppLovin recommends that you load the next ad.
        interstitialAd.loadAd();
    }

    @Override
    public void onAdDisplayed(final MaxAd maxAd) {}

    @Override
    public void onAdClicked(final MaxAd maxAd) {}

    @Override
    public void onAdHidden(final MaxAd maxAd)
    {
        // Interstitial ad is hidden. Pre-load the next ad
        interstitialAd.loadAd();
    }


}