package m.co.rh.id.flutter_serviceloader_plugin;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.embedding.engine.plugins.service.ServiceAware;
import io.flutter.embedding.engine.plugins.service.ServicePluginBinding;

/**
 * FlutterServiceloaderPlugin
 */
public class FlutterServiceloaderPlugin implements FlutterPlugin, ActivityAware, ServiceAware {
    private static final String TAG = FlutterServiceloaderPlugin.class.getName();

    private List<FlutterPlugin> flutterPluginList;

    public FlutterServiceloaderPlugin() {
    }

    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
        // create instances of flutter plugin instead of global singleton from serviceLoader
        // this is to avoid concurrency issue where App have multiple FlutterEngine instances
        if (flutterPluginList == null) {
            flutterPluginList = new ArrayList<>();
            try {
                InputStream is = this.getClass().getResourceAsStream("/META-INF/services/io.flutter.embedding.engine.plugins.FlutterPlugin");
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String className = br.readLine();
                while (className != null) {
                    Class<FlutterPlugin> clazz = (Class<FlutterPlugin>) Class.forName(className);
                    FlutterPlugin flutterPlugin = clazz.newInstance();
                    flutterPluginList.add(flutterPlugin);
                    className = br.readLine();
                }
                br.close();
                is.close();
            } catch (Exception e) {
                Log.e(TAG, "flutter_serviceloader_plugin error", e);
                Toast.makeText(flutterPluginBinding.getApplicationContext(), "flutter_serviceloader_plugin error, see logs for details", Toast.LENGTH_LONG);
                throw new RuntimeException(e);
            }
        }
        Iterator<FlutterPlugin> flutterPluginIterator = flutterPluginList.iterator();
        while (flutterPluginIterator.hasNext()) {
            flutterPluginIterator.next().onAttachedToEngine(flutterPluginBinding);
        }
    }

    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
        Iterator<FlutterPlugin> flutterPluginIterator = flutterPluginList.iterator();
        while (flutterPluginIterator.hasNext()) {
            flutterPluginIterator.next().onDetachedFromEngine(binding);
        }
    }

    @Override
    public void onAttachedToActivity(@NonNull ActivityPluginBinding binding) {
        Iterator<FlutterPlugin> flutterPluginIterator = flutterPluginList.iterator();
        while (flutterPluginIterator.hasNext()) {
            FlutterPlugin flutterPlugin = flutterPluginIterator.next();
            if (flutterPlugin instanceof ActivityAware) {
                ((ActivityAware) flutterPlugin).onAttachedToActivity(binding);
            }
        }
    }

    @Override
    public void onDetachedFromActivityForConfigChanges() {
        Iterator<FlutterPlugin> flutterPluginIterator = flutterPluginList.iterator();
        while (flutterPluginIterator.hasNext()) {
            FlutterPlugin flutterPlugin = flutterPluginIterator.next();
            if (flutterPlugin instanceof ActivityAware) {
                ((ActivityAware) flutterPlugin).onDetachedFromActivityForConfigChanges();
            }
        }
    }

    @Override
    public void onReattachedToActivityForConfigChanges(@NonNull ActivityPluginBinding binding) {
        Iterator<FlutterPlugin> flutterPluginIterator = flutterPluginList.iterator();
        while (flutterPluginIterator.hasNext()) {
            FlutterPlugin flutterPlugin = flutterPluginIterator.next();
            if (flutterPlugin instanceof ActivityAware) {
                ((ActivityAware) flutterPlugin).onReattachedToActivityForConfigChanges(binding);
            }
        }
    }

    @Override
    public void onDetachedFromActivity() {
        Iterator<FlutterPlugin> flutterPluginIterator = flutterPluginList.iterator();
        while (flutterPluginIterator.hasNext()) {
            FlutterPlugin flutterPlugin = flutterPluginIterator.next();
            if (flutterPlugin instanceof ActivityAware) {
                ((ActivityAware) flutterPlugin).onDetachedFromActivity();
            }
        }
    }

    @Override
    public void onAttachedToService(@NonNull ServicePluginBinding binding) {
        Iterator<FlutterPlugin> flutterPluginIterator = flutterPluginList.iterator();
        while (flutterPluginIterator.hasNext()) {
            FlutterPlugin flutterPlugin = flutterPluginIterator.next();
            if (flutterPlugin instanceof ServiceAware) {
                ((ServiceAware) flutterPlugin).onAttachedToService(binding);
            }
        }
    }

    @Override
    public void onDetachedFromService() {
        Iterator<FlutterPlugin> flutterPluginIterator = flutterPluginList.iterator();
        while (flutterPluginIterator.hasNext()) {
            FlutterPlugin flutterPlugin = flutterPluginIterator.next();
            if (flutterPlugin instanceof ServiceAware) {
                ((ServiceAware) flutterPlugin).onDetachedFromService();
            }
        }
    }
}
