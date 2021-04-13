package com.example.tranthian.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Product")
public class ProductEntity {

    @PrimaryKey(autoGenerate = true)
    private  int Id;

    @ColumnInfo(name = "Name")
    private String Name;

    @ColumnInfo(name = "Quantity")
    private int Quantity;

    public ProductEntity(String Name, int Quantity){
        this.Name=Name;
        this.Quantity=Quantity;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public void setName(String name) {
        Name = name;
    }
}
