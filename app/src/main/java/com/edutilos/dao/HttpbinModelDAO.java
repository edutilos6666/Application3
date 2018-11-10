package com.edutilos.dao;

import com.edutilos.model.HttpbinModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface HttpbinModelDAO {
    @GET("get")
    Call<HttpbinModel> doGet();
}
