package com.example.thomas.slidingnavigationmenu;

import android.widget.Button;
import android.widget.EditText;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.robolectric.shadows.support.v4.SupportFragmentTestUtil.startFragment;

@RunWith(RobolectricTestRunner.class)
public class ZoekertjeTest
{
ZoekertjeToevoegen fragment;


    @Before
    public void shouldNotBeNull() throws Exception
    {
        fragment = ZoekertjeToevoegen.newInstance(null,null);
        startFragment( fragment );
    }

    @Test
    public void checkActivityNotNull() throws Exception {
        assertNotNull(fragment);
    }

    @Test
    public void testGoedeToevoeging(){
        EditText etTitel=(EditText)fragment.getView().findViewById(R.id.uiTitel);
        EditText etPrijs=(EditText)fragment.getView().findViewById(R.id.uiPrijs);
        EditText etBeschrijving=(EditText)fragment.getView().findViewById(R.id.uiBeschrijving);
        etTitel.setText("test");
        etPrijs.setText("1");
        etBeschrijving.setText("test");

        Button button=(Button) fragment.getView().findViewById(R.id.uiToevoegButton);
        button.performClick();

    }

    @Test
    public void testSlechteToevoeging(){
        EditText etTitel=(EditText)fragment.getView().findViewById(R.id.uiTitel);
        EditText etPrijs=(EditText)fragment.getView().findViewById(R.id.uiPrijs);
        EditText etBeschrijving=(EditText)fragment.getView().findViewById(R.id.uiBeschrijving);
        etTitel.setText("test");
        etPrijs.setText("test");
        etBeschrijving.setText("test");

        Button button=(Button) fragment.getView().findViewById(R.id.uiToevoegButton);
        button.performClick();

    }

}