package hei.school.gasstation.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Sale extends Movement{
    private double sellQuantity;
}
