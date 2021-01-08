package com.example.onlinelibrary.database.DAOs;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.onlinelibrary.model.Sale;


import java.util.List;

@Dao
public interface SaleDAO  {

    @Query("SELECT * FROM sales")
    List<Sale> getSales();

    @Query("SELECT * FROM sales WHERE sale_id=:id")
    Sale getSale(Integer id);

    @Insert
    Long addSale(Sale sale);

    @Update
    Integer updateSale(Sale sale);

    @Query("Delete from sales Where userEmail=:email")
    void deleteSaleByUserEmail(String email);

    @Delete
    void deleteSale(Sale sale);

    @Query("SELECT * FROM sales WHERE bookPrice>=100")
    List<Sale> getHundredSales();

    @Query("SELECT * FROM sales WHERE userEmail=:email")
    List<Sale> getUserSales(String email);

    @Query("SELECT COUNT(*) FROM sales WHERE isAuthorSigned=:auth")
    Float getNOAuthorSigned(boolean auth);

}
