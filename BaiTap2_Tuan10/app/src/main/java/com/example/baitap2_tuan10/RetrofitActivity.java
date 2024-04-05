package com.example.baitap2_tuan10;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitActivity extends AppCompatActivity {
    RecyclerView rcCate;
    //Khai báo Adapter
    CategoryAdapter categoryAdapter;
    APIService apiService;
    List<Category> categoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        GetCategory();//load dữ liệu cho category
    }

    private void AnhXa() {
        //ánh xạ
        rcCate = (RecyclerView) findViewById(R.id.rc_category);
    }

    private void GetCategory() {
        //Goi Interface trong APIService
        apiService = RetrofitClient.getRetrofit().create(APIService.class);
        apiService.getCategoryAll().enqueue(new Callback<List<Category>>() {

            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful()) {
                    categoryList = response.body(); //nhận mảng
                    if (categoryList != null) {
                        //khởi tạo Adapter
                        categoryAdapter = new CategoryAdapter(RetrofitActivity.this, categoryList);
                        rcCate.setHasFixedSize(true);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext()
                                , LinearLayoutManager.HORIZONTAL, false);
                        rcCate.setLayoutManager(layoutManager);
                        rcCate.setAdapter(categoryAdapter); // Gắn Adapter vào RecyclerView
                    } else {
                        // Handle null response
                    }
                } else {
                    int statusCode = response.code();
                    // handle request errors depending on status code
                }
            }



            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.e("RetrofitActivity", "Call failed: " + t.getMessage(), t);
            }
        });
    }
}
