package com.example.tranthian;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tranthian.database.AppDatabase;
import com.example.tranthian.database.ProductEntity;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    AppDatabase db;
    EditText edName, edQuantity;
    Button btAdd, btView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edName=findViewById(R.id.edName);
        edQuantity=findViewById(R.id.edQuantity);
        btAdd=findViewById(R.id.btAdd);
        btView=findViewById(R.id.btView);

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAdd();
            }
        });
        btView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoList();
            }
        });
        db=AppDatabase.getAppDatabase(this);
    }

    private void gotoList() {
        Intent shipper=new Intent(this, List.class);
        startActivity(shipper);
        finish();
    }

    private void onAdd() {
        insertProduct(edName.getText().toString(), Integer.parseInt (edQuantity.getText (). toString ()));
    }

    private void insertProduct(String Name, int Quantity) {
        ProductEntity pm=new ProductEntity(Name, Quantity);
        db.productDao().insertProduct(pm);
    }
}