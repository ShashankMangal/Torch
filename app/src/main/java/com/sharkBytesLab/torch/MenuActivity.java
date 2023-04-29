package com.sharkBytesLab.torch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ironsource.mediationsdk.ISBannerSize;
import com.ironsource.mediationsdk.IronSource;
import com.ironsource.mediationsdk.IronSourceBannerLayout;
import com.ironsource.mediationsdk.adunit.adapter.utility.AdInfo;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.model.Placement;
import com.ironsource.mediationsdk.sdk.LevelPlayBannerListener;
import com.ironsource.mediationsdk.sdk.LevelPlayInterstitialListener;
import com.ironsource.mediationsdk.sdk.LevelPlayRewardedVideoListener;

import java.util.concurrent.TimeUnit;

public class MenuActivity extends AppCompatActivity{

    private CardView ratings, review, terms, privacy;
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


        IronSource.init(this, "19c55b555");
        LinearLayout bannerId = findViewById(R.id.bannerId);

        IronSourceBannerLayout banner = IronSource.createBanner(this, ISBannerSize.BANNER);
        bannerId.addView(banner);
        banner.setLevelPlayBannerListener(new LevelPlayBannerListener() {
            // Invoked each time a banner was loaded. Either on refresh, or manual load.
            //  AdInfo parameter includes information about the loaded ad
            @Override
            public void onAdLoaded(AdInfo adInfo) {}
            // Invoked when the banner loading process has failed.
            //  This callback will be sent both for manual load and refreshed banner failures.
            @Override
            public void onAdLoadFailed(IronSourceError error) {}
            // Invoked when end user clicks on the banner ad
            @Override
            public void onAdClicked(AdInfo adInfo) {}
            // Notifies the presentation of a full screen content following user click
            @Override
            public void onAdScreenPresented(AdInfo adInfo) {}
            // Notifies the presented screen has been dismissed
            @Override
            public void onAdScreenDismissed(AdInfo adInfo) {}
            //Invoked when the user left the app
            @Override
            public void onAdLeftApplication(AdInfo adInfo) {}

        });

        IronSource.loadBanner(banner);
        IronSource.loadInterstitial();

        IronSource.setLevelPlayInterstitialListener(new LevelPlayInterstitialListener() {
            // Invoked when the interstitial ad was loaded successfully.
            // AdInfo parameter includes information about the loaded ad
            @Override
            public void onAdReady(AdInfo adInfo) {}
            // Indicates that the ad failed to be loaded
            @Override
            public void onAdLoadFailed(IronSourceError error) {}
            // Invoked when the Interstitial Ad Unit has opened, and user left the application screen.
            // This is the impression indication.
            @Override
            public void onAdOpened(AdInfo adInfo) {}
            // Invoked when the interstitial ad closed and the user went back to the application screen.
            @Override
            public void onAdClosed(AdInfo adInfo) {}
            // Invoked when the ad failed to show
            @Override
            public void onAdShowFailed(IronSourceError error, AdInfo adInfo) {}
            // Invoked when end user clicked on the interstitial ad
            @Override
            public void onAdClicked(AdInfo adInfo) {}
            // Invoked before the interstitial ad was opened, and before the InterstitialOnAdOpenedEvent is reported.
            // This callback is not supported by all networks, and we recommend using it only if
            // it's supported by all networks you included in your build.
            @Override
            public void onAdShowSucceeded(AdInfo adInfo){}
        });
        support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                IronSource.showInterstitial();

            }
        });

        IronSource.loadRewardedVideo();
        IronSource.setLevelPlayRewardedVideoListener(new LevelPlayRewardedVideoListener() {
            // Indicates that there's an available ad.
            // The adInfo object includes information about the ad that was loaded successfully
            // Use this callback instead of onRewardedVideoAvailabilityChanged(true)
            @Override
            public void onAdAvailable(AdInfo adInfo) {}
            // Indicates that no ads are available to be displayed
            // Use this callback instead of onRewardedVideoAvailabilityChanged(false)
            @Override
            public void onAdUnavailable() {}
            // The Rewarded Video ad view has opened. Your activity will loose focus
            @Override
            public void onAdOpened(AdInfo adInfo) {}
            // The Rewarded Video ad view is about to be closed. Your activity will regain its focus
            @Override
            public void onAdClosed(AdInfo adInfo) {}
            // The user completed to watch the video, and should be rewarded.
            // The placement parameter will include the reward data.
            // When using server-to-server callbacks, you may ignore this event and wait for the ironSource server callback
            @Override
            public void onAdRewarded(Placement placement, AdInfo adInfo) {}
            // The rewarded video ad was failed to show
            @Override
            public void onAdShowFailed(IronSourceError error, AdInfo adInfo) {}

            @Override
            public void onAdClicked(Placement placement, AdInfo adInfo) {

            }

        });

        watch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IronSource.showRewardedVideo();
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

}