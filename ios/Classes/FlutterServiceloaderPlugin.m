#import "FlutterServiceloaderPlugin.h"
#if __has_include(<flutter_serviceloader_plugin/flutter_serviceloader_plugin-Swift.h>)
#import <flutter_serviceloader_plugin/flutter_serviceloader_plugin-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "flutter_serviceloader_plugin-Swift.h"
#endif

@implementation FlutterServiceloaderPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftFlutterServiceloaderPlugin registerWithRegistrar:registrar];
}
@end
