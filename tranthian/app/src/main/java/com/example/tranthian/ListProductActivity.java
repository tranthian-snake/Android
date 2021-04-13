package com.example.tranthian;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.tranthian.database.AppDatabase;
import com.example.tranthian.database.ProductEntity;

import java.util.ArrayList;
import java.util.List;

public class ListProductActivity extends AppCompatActivity {

    AppDatabase db;
    RecyclerView rvProduct;
    List<ProductEntity> listProduct= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);

        db = AppDatabase.getAppDatabase(this);

        listProduct = db.productDao().getAllProduct();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        ProductAdapter adapter = new ProductAdapter(this, listProduct);

        rvProduct=findViewById(R.id.rvProduct);
        rvProduct.setLayoutManager(layoutManager);
        rvProduct.setAdapter(adapter);
    }
}