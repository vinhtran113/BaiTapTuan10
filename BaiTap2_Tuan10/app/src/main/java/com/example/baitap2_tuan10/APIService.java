package com.example.baitap2_tuan10;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
public interface APIService {
    @GET("categories.php")
    Call<List<Category>> getCategoryAll();
}