import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:ipay88_plugin/ipay88_plugin.dart';


void main() {
  const MethodChannel channel = MethodChannel('ipay88_plugin');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('from_flutter_ipay88', () async {
    expect( Ipay88Plugin.platformIpay88, '42');
  });
}
