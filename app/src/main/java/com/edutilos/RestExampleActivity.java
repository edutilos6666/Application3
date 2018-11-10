package com.edutilos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.edutilos.dao.HttpbinModelDAO;
import com.edutilos.dao.MongoWorkerDAO;
import com.edutilos.model.HttpbinModel;
import com.edutilos.model.MongoWorker;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *  to find ip4 address type  ifconfig -a  or ip addr show
 *  you have to open wifi , and connect from device to your notebook via this wifi
 *  and start node server.1.js
 */
public class RestExampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_example);
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
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        initComponents();
        registerEvents();
    }

    private EditText editContent, editUpdateId, editDeleteId, editFindOneId;
    private Button btnLoad, btnCreate, btnUpdate, btnDelete, btnFindOne, btnGetHttpbin;
    private MongoWorkerDAO mongoWorkerDAO;

    private void initComponents() {
        editContent = findViewById(R.id.rest_edit_content);
        btnLoad = findViewById(R.id.rest_btn_load);
        btnCreate = findViewById(R.id.rest_btn_create);
        btnUpdate = findViewById(R.id.rest_btn_update);
        btnDelete = findViewById(R.id.rest_btn_delete);
        btnFindOne = findViewById(R.id.rest_btn_findOne);
        editUpdateId = findViewById(R.id.rest_edit_update_id);
        editDeleteId = findViewById(R.id.rest_edit_delete_id);
        editFindOneId = findViewById(R.id.rest_edit_findOne_id);
        btnGetHttpbin = findViewById(R.id.rest_btn_getHttpbin);
        initDAO();
    }

    private void initDAO() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        // to find ip4 address type  ifconfig -a  or ip addr show
        // you have to open wifi , and connect from device to your notebook via this wifi
        // and start node server.1.js
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.4.20.13:4500/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        mongoWorkerDAO = retrofit.create(MongoWorkerDAO.class);
    }

    private void registerEvents() {
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<List<MongoWorker>> workersCall = mongoWorkerDAO.findAllWorkers();
                workersCall.enqueue(new Callback<List<MongoWorker>>() {
                    @Override
                    public void onResponse(Call<List<MongoWorker>> call, Response<List<MongoWorker>> response) {
                        List<MongoWorker> workers = response.body();
                        StringBuilder sb = new StringBuilder();
                        for(MongoWorker worker: workers) {
                            sb.append(worker.toString()).append("\n");
                        }
                        editContent.setText(sb.toString());
                    }

                    @Override
                    public void onFailure(Call<List<MongoWorker>> call, Throwable t) {
                        editContent.setText(t.getMessage());
                        call.cancel();
                    }
                });

            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<MongoWorker> call = mongoWorkerDAO.create(new MongoWorker("cristiano", 50, 500.0, false));
                call.enqueue(new Callback<MongoWorker>() {
                    @Override
                    public void onResponse(Call<MongoWorker> call, Response<MongoWorker> response) {
                        editContent.setText(response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<MongoWorker> call, Throwable t) {
                        editContent.setText(t.getMessage());
                    }
                });
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String id = editUpdateId.getText().toString();
                    Call<Void> call = mongoWorkerDAO.update(id, new MongoWorker("updated-name", 66, 666.6, false));
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
//                            editContent.setText(response.body().toString());
                            editContent.setText("Updated successfully.");
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            editContent.setText(t.getMessage());
                        }
                    });
                } catch(Exception ex) {
                    editContent.setText(ex.getMessage());
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String id = editDeleteId.getText().toString();
                    Call<Void> call = mongoWorkerDAO.delete(id);
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
//                            editContent.setText(response.body().toString());
                            editContent.setText("deleted successfully.");
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            editContent.setText(t.getMessage());
                        }
                    });
                } catch(Exception ex) {
                    editContent.setText(ex.getMessage());
                }
            }
        });

        btnFindOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String id = editFindOneId.getText().toString();
                    Call<MongoWorker> call = mongoWorkerDAO.findOne(id);
                    call.enqueue(new Callback<MongoWorker>() {
                        @Override
                        public void onResponse(Call<MongoWorker> call, Response<MongoWorker> response) {
                            editContent.setText(response.body().toString());
                        }

                        @Override
                        public void onFailure(Call<MongoWorker> call, Throwable t) {
                            editContent.setText(t.getMessage());
                        }
                    });
                } catch(Exception ex) {
                    editContent.setText(ex.getMessage());
                }
            }
        });

        btnGetHttpbin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
                // to find ip4 address type  ifconfig -a  or ip addr show
                // you have to open wifi , and connect from device to your notebook via this wifi
                // and start node server.1.js
                Retrofit retrofit = new Retrofit.Builder().baseUrl("http://eu.httpbin.org/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(client)
                        .build();
                HttpbinModelDAO dao = retrofit.create(HttpbinModelDAO.class);
                Call<HttpbinModel> one = dao.doGet();
                one.enqueue(new Callback<HttpbinModel>() {
                    @Override
                    public void onResponse(Call<HttpbinModel> call, Response<HttpbinModel> response) {
                        editContent.setText(response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<HttpbinModel> call, Throwable t) {
                        editContent.setText(t.getMessage());
                    }
                });
            }
        });

    }

    private void sendWithVolley() {
        String url = "http://10.4.20.13:4500/api/workers";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        editContent.setText(response);
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        editContent.setText(error.getMessage());
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(getBaseContext());
        requestQueue.add(stringRequest);
    }
}
