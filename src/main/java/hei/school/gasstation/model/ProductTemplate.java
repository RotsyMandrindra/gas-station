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
    private float evaporationRate;
    private UUID movementId;
}
