package cart;

import common.BaseEntity;
import lombok.*;
import product.Product;
import user.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Cart extends BaseEntity<UUID> {
   private UUID userId;
   private UUID restaurantId;
   private List<UUID> products;
   private LocalDate expirationDat;


   public Cart(UUID id, LocalDateTime created, LocalDateTime modified, User createdBy, User modifiedBy, UUID userId, UUID restaurantId, List<UUID> products, LocalDate expirationDat) {
      super(id, created, modified, createdBy, modifiedBy);
      this.userId = userId;
      this.restaurantId = restaurantId;
      this.products = products;
      this.expirationDat = expirationDat;
   }
}
