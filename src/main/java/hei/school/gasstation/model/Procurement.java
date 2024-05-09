package hei.school.gasstation.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Procurement extends Movement{
    private double supplyQuantity;
}
