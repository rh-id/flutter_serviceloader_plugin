package m.co.rh.id.flutter_serviceloader_plugin;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.embedding.engine.plugins.service.ServiceAware;
import io.flutter.embedding.engine.plugins.service.ServicePluginBinding;

/**
 * FlutterServiceloaderPlugin
 */
public class FlutterServiceloaderPlugin implements FlutterPlugin, ActivityAware, ServiceAware {

    private final List<FlutterPlugin> flutterPluginList;

    public FlutterServiceloaderPlugin() {
        flutterPluginList = new ArrayList<>();
        Iterator<FlutterPlugin> flutterPluginIterator = ServiceLoader.load(FlutterPlugin.class).iterator();
        while (flutterPluginIterator.hasNext()) {
            flutterPluginList.add(flutterPluginIterator.next());
        }
    }

    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
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
