package hei.school.gasstation.model;

import lombok.*;

import java.time.Instant;
import java.util.UUID;
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Station {
    private UUID stationId;
    private String stationName;
    private String location;
    private String contact;
    private double totalAmountStation;
}
