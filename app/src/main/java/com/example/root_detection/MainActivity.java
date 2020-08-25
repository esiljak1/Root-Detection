package com.example.root_detection;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String DEVICE_IS_ROOTED = "Your device is ROOTED";
    private static final String DEVICE_IS_NOT_ROOTED = "Your device is not ROOTED";

    private TextView rootedTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootedTextView = findViewById(R.id.rootedTextView);
        if(RootDetectionUnit.isDeviceRooted()){
            rootedTextView.setText(DEVICE_IS_ROOTED);
        }else{
            rootedTextView.setText(DEVICE_IS_NOT_ROOTED);
        }
    }
}