package hei.school.gasstation.service;

import hei.school.gasstation.repository.GetSumSellQuantityRepository;

import java.sql.SQLException;

public class GetSumSellQuantityService {
    private GetSumSellQuantityRepository getSumSellQuantityRepository;

    public GetSumSellQuantityService(GetSumSellQuantityRepository getSumSellQuantityRepository) {
        this.getSumSellQuantityRepository = getSumSellQuantityRepository;
    }
    public double getSumSellQuantity() throws SQLException{
        return getSumSellQuantityRepository.getSumQuantitySell();
    }
}
