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
public abstract class Movement {
    private UUID movementId;
    private String type;
    private Instant date;
    private double remainingQuantity;
}
