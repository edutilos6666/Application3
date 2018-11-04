package com.edutilos;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class CustomFragmentActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_fragment);
        Configuration config = getResources().getConfiguration();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            LM_Fragment fragment = new LM_Fragment();
            //fragmentTransaction.add(R.id.fragment_container, fragment)
            fragmentTransaction.replace(R.id.fragment_container, fragment)
            .addToBackStack(null).commit();
        } else if(config.orientation == Configuration.ORIENTATION_PORTRAIT) {
            PM_Fragment fragment = new PM_Fragment();
            fragmentTransaction.replace(R.id.fragment_container, fragment)
            .addToBackStack(null).commit();
        }
//        fragmentTransaction.commit();
    }

}
