package com.deremakif.myapplication;


import androidx.annotation.NonNull;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugins.GeneratedPluginRegistrant;

public class MyFlutterActivity extends FlutterActivity {
    private static final String CHANNEL =  "samples.flutter.io/battery";

    @Override
    public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
        GeneratedPluginRegistrant.registerWith(flutterEngine);

        new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), CHANNEL).setMethodCallHandler(((methodCall, result) -> {
            if (methodCall.method.equals("getBatteryLevel")) {

                result.success("batteryLevel"); // It returns string "batteryLevel".

            } else {
                result.notImplemented();
            }
        }));
}
}
