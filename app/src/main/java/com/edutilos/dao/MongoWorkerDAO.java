package com.edutilos.dao;

import com.edutilos.model.MongoWorker;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface MongoWorkerDAO {
    @POST("workers")
    Call<MongoWorker> create(@Body MongoWorker worker);
    @PUT("workers/{id}")
    Call<Void> update(@Path("id") String id, @Body MongoWorker worker);
    @DELETE("workers/{id}")
    Call<Void> delete(@Path("id")String id);
    @GET("workers")
    Call<List<MongoWorker>> findAllWorkers();
    @GET("workers/{id}")
    Call<MongoWorker> findOne(@Path("id")String id);
}
