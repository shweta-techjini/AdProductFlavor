package com.udacity.gradle.builditbigger;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.udacity.gradle.builditbigger.listener.EndpointsListener;
import com.udacity.gradle.builditbigger.network.EndpointsAsycTask;
import com.udacity.gradle.displayjoke.LibraryActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainActivityFragment extends Fragment implements View.OnClickListener, EndpointsListener {


    private ProgressBar mProgressBar;

    public MainActivityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("MainActivityFragment", "onCreate Fragment default from paid");
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        root.findViewById(R.id.btn_tell).setOnClickListener(this);
        mProgressBar = (ProgressBar) root.findViewById(R.id.progress_bar);
        // Inflate the layout for this fragment
        return root;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_tell) {
            Log.d("MainActivityFragment", "Tell Joke is clicked");
            mProgressBar.setVisibility(View.VISIBLE);
            new EndpointsAsycTask(this).execute();
        }
    }

    @Override
    public void resultFromEndPoint(String response) {
        if (getActivity() != null) {
            mProgressBar.setVisibility(View.GONE);
            Log.d("MainActivityFragment", "result from end point.");
            Intent libActivity = new Intent(getContext(), LibraryActivity.class);
            libActivity.putExtra(getString(R.string.joke_string), response);
            startActivity(libActivity);
        }
    }
}
