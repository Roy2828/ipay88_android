#import "Ipay88Plugin.h"
#if __has_include(<ipay88_plugin/ipay88_plugin-Swift.h>)
#import <ipay88_plugin/ipay88_plugin-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "ipay88_plugin-Swift.h"
#endif

@implementation Ipay88Plugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftIpay88Plugin registerWithRegistrar:registrar];
}
@end
