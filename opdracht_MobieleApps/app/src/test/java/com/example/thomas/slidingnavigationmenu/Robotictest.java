package com.example.thomas.slidingnavigationmenu;

import android.app.Activity;
import android.view.Menu;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.robolectric.Shadows.shadowOf;


@RunWith(RobolectricTestRunner.class)
//@Config(constants = BuildConfig.class)
public class Robotictest {

    private MainActivity activity;

    @Before
    public void setUp() throws Exception
    {
        activity = Robolectric.buildActivity( MainActivity.class )
                .create()
                .resume()
                .get();
    }



/*    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(RobolectricActivity.class)
                .create()
                .resume()
                .get();
    }

*/





 /*   @Test
    public void onCreateShouldInflateTheMenu() {
        Activity activity = Robolectric.setupActivity(MainActivity.class);


        activity.button.performClick();

        assertThat(activity.message.getText()).isEqualTo("Robolectric Rocks!");*/

/*        final Menu menu = shadowOf(activity).getOptionsMenu();
        assertThat(menu.findItem(R.id.item1).getTitle()).isEqualTo("First menu item");
        assertThat(menu.findItem(R.id.item2).getTitle()).isEqualTo("Second menu item");
        */
    }
