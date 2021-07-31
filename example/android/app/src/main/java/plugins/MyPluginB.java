package plugins;

import android.util.Log;

import androidx.annotation.NonNull;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;

public class MyPluginB implements FlutterPlugin, ActivityAware {
    private static final String TAG = MyPluginB.class.getName();

    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding binding) {
        Log.d(TAG, "onAttachedToEngine");
    }

    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
        Log.d(TAG, "onDetachedFromEngine");
    }

    @Override
    public void onAttachedToActivity(@NonNull ActivityPluginBinding binding) {
        Log.d(TAG, "onAttachedToActivity");
    }

    @Override
    public void onDetachedFromActivityForConfigChanges() {
        Log.d(TAG, "onDetachedFromActivityForConfigChanges");
    }

    @Override
    public void onReattachedToActivityForConfigChanges(@NonNull ActivityPluginBinding binding) {
        Log.d(TAG, "onReattachedToActivityForConfigChanges");
    }

    @Override
    public void onDetachedFromActivity() {
        Log.d(TAG, "onDetachedFromActivity");
    }
}
