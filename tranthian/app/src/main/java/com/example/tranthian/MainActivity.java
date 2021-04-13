package com.example.tranthian;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tranthian.database.AppDatabase;
import com.example.tranthian.database.ProductEntity;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
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

        db=AppDatabase.getAppDatabase(this);

        InitView();
    }

    private void InitView() {
        btAdd.setOnClickListener(this);
        btView.setOnClickListener(this);
    }

    public void gotoList() {
        Intent shipper=new Intent(MainActivity.this, ListProductActivity.class);
        startActivity(shipper);

    }
    private boolean checkAdd() {
        if (edName != null&&edQuantity!=null){
            return true;
        }
        return false;
    }
    private void onAdd() {
        insertProduct(edName.getText().toString(), Integer.parseInt (edQuantity.getText (). toString ()));
    }

    private void insertProduct(String Name, int Quantity) {
        ProductEntity pm=new ProductEntity(Name, Quantity);
        db.productDao().insertProduct(pm);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btAdd:
                if (checkAdd()) {
                    onAdd();
                }
                break;
            case R.id.btView:
                gotoList();
                break;
            default:
                break;
        }
    }
}