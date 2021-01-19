package com.deremakif.myapplication;


import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.FlutterEngineCache;
import io.flutter.embedding.engine.dart.DartExecutor;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodCall;

import io.flutter.plugins.GeneratedPluginRegistrant;

public class MyFlutterActivity extends FlutterActivity {
    private static final String CHANNEL = "samples.flutter.io/battery";

    String title = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();

        title = extras.getString("title");
    }


    @Override
    public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
        GeneratedPluginRegistrant.registerWith(flutterEngine);

        new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), CHANNEL).setMethodCallHandler(((methodCall, result) -> {
            if (methodCall.method.equals("getBatteryLevel")) {

                result.success("batteryLevel"); // It returns string "batteryLevel".

            } else if (methodCall.method.equals("getTitle")) {

                result.success(title); // It returns string "batteryLevel".

            } else {
                result.notImplemented();
            }
        }));
    }
}
