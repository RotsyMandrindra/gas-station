package hei.school.gasstation.controller;

import hei.school.gasstation.service.GetSumSellQuantityService;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.SQLException;

public class GetSumSellQuantityController {
    private GetSumSellQuantityService getSumSellQuantityService;

    public GetSumSellQuantityController(GetSumSellQuantityService getSumSellQuantityService) {
        this.getSumSellQuantityService = getSumSellQuantityService;
    }

    @GetMapping("/sum_sell_quantity")
    public double getSumSellQuantity() throws SQLException{
        return getSumSellQuantityService.getSumSellQuantity();
    }
}
