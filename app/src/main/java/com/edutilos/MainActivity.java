package com.edutilos;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.edutilos.broadcast.CustomReceiver2;
import com.edutilos.model.Worker;
import com.edutilos.service.CustomService1;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        intentCustomService1 = new Intent(getBaseContext(), CustomService1.class);
        startService(intentCustomService1);
        customReceiver2 = new CustomReceiver2();
        IntentFilter intentFilter = new IntentFilter("com.edutilos.CUSTOM_INTENT_1");
        registerReceiver(customReceiver2, intentFilter);
        registerReceiver(customReceiver2, new IntentFilter("com.edutilos.CUSTOM_INTENT_2"));
        registerReceiver(customReceiver2, new IntentFilter("com.edutilos.CUSTOM_INTENT_3"));
        broadcastIntent();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(intentCustomService1);
        unregisterReceiver(customReceiver2);
    }


    private void broadcastIntent() {
        Intent i = new Intent();
        i.setAction("com.edutilos.CUSTOM_INTENT_1");
        sendBroadcast(i);
    }


    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        initComponents();
        registerEvents();

    }


    private CustomReceiver2 customReceiver2;
    private  Intent intentCustomService1;
    private Button btnLinearLayoutActivity, btnTableLayoutActivity, btnGridLayoutActivity,
         btnDynamicLayoutActivity, btnSendIntent1, btnSendIntent2, btnSendIntent3,
         btnSendMultiplIntents, btnAlarmActivity, btnReceiverWorkerActivity,
         btnTodoActivity, btnFragmentActivity, btnWorkerFormtActivity,
         btnCustomListActivity, btnPlanetListActivity, btnIntentExampleActivity,
         btnUIControlsActvity;

    private void initComponents() {
        btnLinearLayoutActivity = (Button) findViewById(R.id.ma_btnLinearLayoutActivity);
        btnTableLayoutActivity = (Button) findViewById(R.id.ma_btnTableLayoutActivity);
        btnGridLayoutActivity = findViewById(R.id.ma_btnGridLayoutActivity);
        btnDynamicLayoutActivity = findViewById(R.id.ma_btnDynamicLayoutActivity);
        btnSendIntent1 = findViewById(R.id.ma_btnSendIntent1);
        btnSendIntent2 = findViewById(R.id.ma_btnSendIntent2);
        btnSendIntent3 = findViewById(R.id.ma_btnSendIntent3);
        btnSendMultiplIntents = findViewById(R.id.ma_btnSendMultipleIntents);
        btnAlarmActivity = findViewById(R.id.ma_btnAlarmActivity);
        btnReceiverWorkerActivity = findViewById(R.id.ma_btnReceiverWorkerActivity);
        btnTodoActivity = findViewById(R.id.ma_btnTodoActivity);
        btnFragmentActivity = findViewById(R.id.ma_btnFragmentActivity);
        btnWorkerFormtActivity = findViewById(R.id.ma_btnWorkerFormtActivity);
        btnCustomListActivity = findViewById(R.id.ma_btnCustomListActivity);
        btnPlanetListActivity = findViewById(R.id.ma_btnPlanetListActivity);
        btnIntentExampleActivity = findViewById(R.id.ma_btnIntentExampleActivity);
        btnUIControlsActvity = findViewById(R.id.ma_btnUIControlsActvity);
    }

    private void registerEvents() {

        btnLinearLayoutActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LinearLayoutActivity.class);
                intent.putExtra("worker1", new Worker(1, "foo", "bar", 10, 100.0, true));
                startActivity(intent);
            }
        });

        btnTableLayoutActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TableLayoutActivity.class);
                intent.putExtra("worker2", new Worker(2, "leo","messi", 20, 200.0, false ));
                startActivity(intent);
            }
        });

        btnGridLayoutActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GridLayoutActivity.class);
                intent.putExtra("worker3", new Worker(3, "cris", "ronaldo", 30, 300.0, true));
                startActivity(intent);
            }
        });

        btnDynamicLayoutActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), DynamicLayoutActivity.class);
                i.putExtra("worker4", new Worker(4, "diego", "armando", 40 , 400.0, false));
                startActivity(i);
            }
        });

        btnSendIntent1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setAction("com.edutilos.CUSTOM_INTENT_1");
                sendBroadcast(i);
            }
        });

        btnSendIntent2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setAction("com.edutilos.CUSTOM_INTENT_2");
                sendBroadcast(i);
            }
        });

        btnSendIntent3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setAction("com.edutilos.CUSTOM_INTENT_3");
                sendBroadcast(i);
            }
        });

        //Does not work
        btnSendMultiplIntents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setAction("com.eduilos.CUSTOM_INTENT_1");
                sendBroadcast(i);
                i.setAction("com.eduilos.CUSTOM_INTENT_2");
                sendBroadcast(i);
                i.setAction("com.eduilos.CUSTOM_INTENT_3");
                sendBroadcast(i);
            }
        });

        btnAlarmActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), AlarmActivity.class);
                startActivity(i);
            }
        });

        btnReceiverWorkerActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), ReceiverWorkerActivity.class);
                startActivity(i);
            }
        });

        btnTodoActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), TodoActivity.class);
                startActivity(i);
            }
        });

        btnFragmentActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), CustomFragmentActivity.class);
                startActivity(i);
            }
        });

        btnWorkerFormtActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), WorkerFormActivity.class);
                startActivity(i);
            }
        });

        btnCustomListActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), CustomListActivity.class);
                startActivity(i);
            }
        });

        btnPlanetListActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), PlanetListActivity.class);
                startActivity(i);
            }
        });

        btnIntentExampleActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), IntentExampleActivity.class);
                startActivity(i);
            }
        });

        btnUIControlsActvity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), UIContolsActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
