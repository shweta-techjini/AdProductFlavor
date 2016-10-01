package com.udacity.gradle.displayjoke;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class LibraryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
        String jokeString = getIntent().getStringExtra(getString(R.string.joke_string));
        ((TextView) findViewById(R.id.joke_text)).setText(jokeString);
    }

}
