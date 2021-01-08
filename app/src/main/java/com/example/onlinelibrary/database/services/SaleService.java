package com.example.onlinelibrary.database.services;

import com.example.onlinelibrary.database.DAOs.SaleDAO;
import com.example.onlinelibrary.model.Sale;

import java.util.List;

public class SaleService implements SaleDAO {

    private final SaleDAO saleDAO;

    public SaleService(SaleDAO saleDAO) {
        this.saleDAO = saleDAO;
    }


    @Override
    public List<Sale> getSales() {
        return saleDAO.getSales();
    }

    @Override
    public Sale getSale(Integer id) {
        return saleDAO.getSale(id);
    }

    @Override
    public Long addSale(Sale sale) {
        return saleDAO.addSale(sale);
    }

    @Override
    public Integer updateSale(Sale sale) {
        return saleDAO.updateSale(sale);
    }

    @Override
    public void deleteSaleByUserEmail(String email) {
        saleDAO.deleteSaleByUserEmail(email);
    }

    @Override
    public void deleteSale(Sale sale) {
        saleDAO.deleteSale(sale);
    }

    @Override
    public List<Sale> getHundredSales() {
        return saleDAO.getHundredSales();
    }

    @Override
    public List<Sale> getUserSales(String email) {
        return saleDAO.getUserSales(email);
    }

    @Override
    public Float getNOAuthorSigned(boolean auth) {
        return saleDAO.getNOAuthorSigned(auth);
    }
}
