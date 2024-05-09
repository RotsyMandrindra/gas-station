package hei.school.gasstation.service;

import hei.school.gasstation.repository.GetSumRemainingQuantityRepository;

import java.sql.SQLException;

public class GetSumRemainingQuantityService {
    private GetSumRemainingQuantityRepository getSumRemainingQuantityRepository;

    public GetSumRemainingQuantityService(GetSumRemainingQuantityRepository getSumRemainingQuantityRepository) {
        this.getSumRemainingQuantityRepository = getSumRemainingQuantityRepository;
    }
    public double getSumRemainingQuantity() throws SQLException{
        return getSumRemainingQuantityRepository.getSumRemainingQuantity();
    }
}
