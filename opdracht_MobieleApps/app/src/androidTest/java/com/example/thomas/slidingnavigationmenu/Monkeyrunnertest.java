 /*       from com.android.monkeyrunner import MonkeyRunner, MonkeyDevice

        device = MonkeyRunner.waitForConnection()


        device.installPackage('myproject/bin/MyApplication.apk')

        package = 'com.example.android.myapplication'

        activity = 'com.example.android.myapplication.MainActivity'

        runComponent = package + '/' + activity

        device.startActivity(component=runComponent)

        device.press('KEYCODE_MENU', MonkeyDevice.DOWN_AND_UP)

        result = device.takeSnapshot()
                Writes the screenshot to a file
        result.writeToFile('myproject/shot1.png','png')
        */