# flutter_serviceloader_plugin

Flutter plugin used to handle FlutterPlugin instances using ServiceLoader instead of official way.

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
