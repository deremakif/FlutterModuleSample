import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class SecondScreen extends StatefulWidget {
  SecondScreen({Key key, this.title}) : super(key: key);

  final String title;

  @override
  _SecondScreenState createState() => _SecondScreenState();
}

class _SecondScreenState extends State<SecondScreen> {
  int _counter = 0;

  String title = "";

  static const platformMethodChannel =
      const MethodChannel('samples.flutter.io/battery');
  void _incrementCounter() async {
    try {
      var response =
          await platformMethodChannel.invokeMethod('getBatteryLevel');

      print(response);
    } on PlatformException catch (e) {
      print("e.message");
      print(e.message);
    }

    setState(() {
      _counter++;
    });
  }

  @override
  void initState() {
    super.initState();

    getTitle();
  }

  getTitle() async {
      String res = await platformMethodChannel.invokeMethod('getTitle');

      setState(() {
        title = res;
      });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Second Page"),
      ),
      body: Center(
        // Center is a layout widget. It takes a single child and positions it
        // in the middle of the parent.
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text(
              'Parameter: ' +  title,
            ),

            Text(
              'You have pushed the button this many times:',
            ),
            Text(
              '$_counter',
              style: Theme.of(context).textTheme.headline4,
            ),
          ],
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: _incrementCounter,
        tooltip: 'Increment',
        child: Icon(Icons.add),
      ), // This trailing comma makes auto-formatting nicer for build methods.
    );
  }
}
