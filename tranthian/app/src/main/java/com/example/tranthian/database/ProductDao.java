package com.example.tranthian.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;
@Dao
public interface ProductDao {
    @Insert(onConflict = REPLACE)
    void  insertProduct(ProductEntity product);

    @Update
    void updateProduct(ProductEntity product);

    @Delete
    void deleteProduct(ProductEntity product);

    @Query("SELECT * FROM Product")
    List<ProductEntity> getAllProduct();

    @Query("SELECT * FROM Product WHERE Id= :Id")
    ProductEntity getProduct(int Id);

    @Query("DELETE FROM Product")
    void deleteAll();
}
