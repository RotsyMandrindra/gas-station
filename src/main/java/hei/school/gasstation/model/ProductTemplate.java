package hei.school.gasstation.model;

import lombok.*;

import java.util.UUID;
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
public class ProductTemplate {
    private UUID productTemplateId;
    private String productName;
    private double productPrice;
    private double quantitySold;
    private double remainingQuantity;
    private double productAmount;
    private float evaporationRate;
    private double suppliedQuantity;
}
