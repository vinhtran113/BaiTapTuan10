package com.example.baitap2_tuan10;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class RetrofitClient {
    private static Retrofit retrofit;
    public static Retrofit getRetrofit(){
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    // đường dẫn API
                    .baseUrl("http://app.iotstar.vn/appfoods/")
                    .addConverterFactory (GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
