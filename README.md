# flutter_serviceloader_plugin

Flutter plugin used to handle FlutterPlugin instances using ServiceLoader instead of defining it in `pubspec.yaml` dependencies.

The benefit of using this plugin is that it works for plugin that execute dart function in background such as `android_alarm` plugin.

In order to execute dart function in background we need to instantiate `new FlutterEngine(context)` and execute the dart function using the engine instance.
The engine instance automatically register all plugins using reflection during instantiation (calling generated class `GeneratedPluginRegistrant.registerWith`).
Since this plugin will be registered in that generated class and forward all events to the ServiceLoader FlutterPlugin classes, when dart function call platform channel within ServiceLoader FlutterPlugin it will work. If you register all the plugin in your `MainActivity` class it will not work since flutter engine didn't call/invoke `MainActivity`

## Getting Started

Only for Android platform.
1. On your android project create new Java resource folder (if you haven't create it, default path should be `src/main/resources`)
2. Create services file `META-INF/services/io.flutter.embedding.engine.plugins.FlutterPlugin` in java resource folder
3. Add your plugin implementation classes for example

```
com.example.MyFlutterPluginA
com.example.MyFlutterPluginB
com.example.MyFlutterPluginC
```
