package hei.school.gasstation.service;

import hei.school.gasstation.repository.SaleRepository;
import lombok.Data;

import java.sql.SQLException;

@Data
public class SaleActionService {
    private SaleRepository saleRepository;
    private String sale(double sellQuantity) throws SQLException{
        return saleRepository.sale(sellQuantity);
    }
}
