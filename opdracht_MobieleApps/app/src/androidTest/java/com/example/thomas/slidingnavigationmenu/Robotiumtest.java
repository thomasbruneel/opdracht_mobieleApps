package com.example.thomas.slidingnavigationmenu;

import android.support.test.InstrumentationRegistry;
import android.support.test.filters.MediumTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@SuppressWarnings("rawtypes")
public class Robotiumtest{

    @Rule
    public ActivityTestRule<Email> activityTestRule =
            new ActivityTestRule<>(Email.class);


    private Solo solo;


    @Before
    public void setUp() throws Exception {
        solo = new Solo(InstrumentationRegistry.getInstrumentation(), activityTestRule.getActivity());
    }



    @After
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }

    @Test
    public void testSendEmail() throws Exception {

        solo.enterText((EditText)solo.getView(R.id.editText1), "test");
        solo.enterText((EditText)solo.getView(R.id.editText1), "test2");
        solo.enterText((EditText)solo.getView(R.id.editText1), "test3");

        solo.clickOnView(solo.getView(R.id.sendButton));



    }


}



