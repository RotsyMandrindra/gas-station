package hei.school.gasstation.model;

import lombok.*;

import java.time.Instant;
import java.util.UUID;
@Getter
@Setter
@ToString
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class Product {
    private UUID productId;
    private Instant suppliedDate;
    private UUID productTemplateId;
}
