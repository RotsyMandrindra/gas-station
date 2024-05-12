package hei.school.gasstation.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Procurement extends Movement{
    private double supplyQuantity;
}
