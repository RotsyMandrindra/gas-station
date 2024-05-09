package hei.school.gasstation.controller;

import hei.school.gasstation.service.GetSumRemainingQuantityService;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.SQLException;

public class GetSumRemainingQuantityController {
    private GetSumRemainingQuantityService getSumRemainingQuantityService;

    public GetSumRemainingQuantityController(GetSumRemainingQuantityService getSumRemainingQuantityService) {
        this.getSumRemainingQuantityService = getSumRemainingQuantityService;
    }
    @GetMapping("/sum_remaining_quantity")
    public double getSumRemainingQuantity() throws SQLException{
        return getSumRemainingQuantityService.getSumRemainingQuantity();
    }
}
