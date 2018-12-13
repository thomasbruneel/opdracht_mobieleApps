package com.example.thomas.slidingnavigationmenu;

import android.app.Activity;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.tools.ant.Main;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.w3c.dom.Text;

import static org.robolectric.Shadows.shadowOf;


@RunWith(RobolectricTestRunner.class)
//@Config(constants = BuildConfig.class)
public class EmailTest {

    private Email activity;

    @Before
    public void setUp() throws Exception
    {
        activity = Robolectric.buildActivity( Email.class )
                .create()
                .resume()
                .get();
    }

    @Test
    public void test() throws Exception
    {

        EditText receiver=(EditText) activity.findViewById(R.id.editText1);
        EditText subject=(EditText) activity.findViewById(R.id.editText2);
        EditText message=(EditText) activity.findViewById(R.id.editText3);

        Button buttonSend = (Button) activity.findViewById(R.id.sendButton);

    }

    @Test
    public void test2() throws Exception
    {

        EditText receiver=(EditText) activity.findViewById(R.id.editText1);
        EditText subject=(EditText) activity.findViewById(R.id.editText2);
        EditText message=(EditText) activity.findViewById(R.id.editText3);

        Button buttonSend = (Button) activity.findViewById(R.id.sendButton);
        buttonSend.performClick();


    }


}
