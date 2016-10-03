package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.udacity.gradle.builditbigger.listener.EndpointsListener;
import com.udacity.gradle.builditbigger.network.EndpointsAsycTask;
import com.udacity.gradle.displayjoke.LibraryActivity;


/**
 *
 */
public class MainActivityFragment extends Fragment implements EndpointsListener, View.OnClickListener {

    private InterstitialAd mInterstitialAd;
    private String mJoke;
    private boolean displayJoke;
    private ProgressBar mProgressBar;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("MainActivityFragment", "onCreate Fragment default from free");
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        root.findViewById(R.id.btn_tell_joke).setOnClickListener(this);

        mProgressBar = (ProgressBar) root.findViewById(R.id.progress_bar);

        Log.d("MainActivityFragment", "onCreate Fragment show ad in free");
        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
        prepareInterstitialAd();
        return root;
    }

    private void prepareInterstitialAd() {
        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                displayJoke();
            }
        });
        requestNewInterstitial();
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mInterstitialAd.loadAd(adRequest);
    }

    @Override
    public void resultFromEndPoint(String response) {
        if (getActivity() != null) {
            mJoke = response;
            if (displayJoke == true) {
                mProgressBar.setVisibility(View.GONE);
                displayJoke();
            }
        }
    }

    private void displayJoke() {
        Intent libActivity = new Intent(getContext(), LibraryActivity.class);
        libActivity.putExtra(getString(R.string.joke_string), mJoke);
        startActivity(libActivity);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_tell_joke){
            new EndpointsAsycTask(this).execute();
            if (mInterstitialAd.isLoaded()) {
                displayJoke = false;
                mInterstitialAd.show();
            }else {
                displayJoke = true;
                mProgressBar.setVisibility(View.VISIBLE);
            }
        }
    }
}
