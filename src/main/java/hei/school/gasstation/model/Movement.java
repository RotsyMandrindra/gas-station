package hei.school.gasstation.model;

import lombok.*;

import java.time.Instant;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class Movement {
    private UUID movementId;
    private String type;
    private double sellQuantity;
    private double remainingQuantity;
    private double supplyQuantity;
    private Instant date;
}
