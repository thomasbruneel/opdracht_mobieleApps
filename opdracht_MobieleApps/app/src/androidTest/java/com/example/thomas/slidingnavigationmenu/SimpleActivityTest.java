package com.example.thomas.slidingnavigationmenu;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.robotium.solo.Solo;

import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertEquals;

import junit.framework.Assert;
import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class SimpleActivityTest extends ActivityInstrumentationTestCase2<ZoekertjeToevoegen>{
   /* @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.thomas.slidingnavigationmenu", appContext.getPackageName());
    }*/



        private Solo solo;

        public SimpleActivityTest() {
            super(ZoekertjeToevoegen.class);
        }

        public void setUp() throws Exception {
            solo = new Solo(getInstrumentation(), getActivity());
        }


        @Override
        public void tearDown() throws Exception {
            solo.finishOpenedActivities();
        }

    public void testListItemClickShouldDisplayToast() throws Exception {
        solo.assertCurrentActivity("wrong activity", ZoekertjeToevoegen.class);

    }


    }



