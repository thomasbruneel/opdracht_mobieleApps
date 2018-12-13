package com.example.thomas.slidingnavigationmenu;

import android.app.Activity;
import android.view.Menu;

import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class Robotictest {

    @Test
    public void onCreateShouldInflateTheMenu() {
        Activity activity = Robolectric.setupActivity(MainActivity.class);

        final Menu menu = shadowOf(activity).getOptionsMenu();
        assertThat(menu.findItem(R.id.item1).getTitle()).isEqualTo("First menu item");
        assertThat(menu.findItem(R.id.item2).getTitle()).isEqualTo("Second menu item");
    }
}