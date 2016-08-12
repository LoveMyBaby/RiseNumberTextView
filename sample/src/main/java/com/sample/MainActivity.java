package com.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.chrischeng.risenumbertextview.RiseNumberTextView;

public class MainActivity extends AppCompatActivity {

    private RiseNumberTextView rnTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rnTextView = (RiseNumberTextView) findViewById(R.id.rntv);
    }

    @Override
    protected void onResume() {
        super.onResume();

        rnTextView.run();
    }
}
