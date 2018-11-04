package com.edutilos;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class WorkerFormActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_form);
        Configuration configuration = getResources().getConfiguration();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if(configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            FragmentWorkerForm_PM fragment = new FragmentWorkerForm_PM();
            fragmentTransaction.replace(R.id.worker_form_fragment_container, fragment)
            .addToBackStack(null).commit();
        } else if(configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            FragmentWorkerForm_LM fragment = new FragmentWorkerForm_LM();
            fragmentTransaction.replace(R.id.worker_form_fragment_container, fragment)
                    .addToBackStack(null).commit();
        }
    }

}
