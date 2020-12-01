package com.example.twoactivities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.twoactivities.databinding.ActivityMainBinding;
import com.example.twoactivities.databinding.ActivitySecondBinding;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static final String EXTRA_MESSAGE = "com.example.android.twoactivities.extra.Message";
    public static final int TEXT_REQUEST = 1;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Log.d(LOG_TAG, "onCreate");

        if (savedInstanceState != null) {
            boolean isVisible =
                    savedInstanceState.getBoolean("REPLY_VISIBLE");
            // Show both the header and the message views. If isVisible is
            // false or missing from the bundle, use the default layout.
            if (isVisible) {
                binding.textHeaderReply.setVisibility(View.VISIBLE);
                binding.textMessageReply.setText(savedInstanceState
                        .getString("REPLY_TEXT"));
                binding.textMessageReply.setVisibility(View.VISIBLE);
            }
        }

    }
    
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(LOG_TAG, "onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {
                String reply =
                        null;
                if (data != null) {
                    reply = data.getStringExtra(SecondActivity.EXTRA_REPLY);
                }
                binding.textHeaderReply.setVisibility(View.VISIBLE);
                binding.textMessageReply.setText(reply);
                binding.textMessageReply.setVisibility(View.VISIBLE);
            }
        }
    }

    public void launchSecondActivity(View view) {
        Log.d(LOG_TAG,"Button clicked");

        Intent intent = new Intent(this, SecondActivity.class);
        String message = binding.editTextMain.getText().toString();
        intent.putExtra(EXTRA_MESSAGE,message);
        startActivityForResult(intent, TEXT_REQUEST);


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (binding.textHeaderReply.getVisibility() == View.VISIBLE){
            outState.putBoolean("REPLY_VISIBLE",true);
            outState.putString("REPLY_TEXT",binding.textMessageReply.getText().toString());
        }
    }
}