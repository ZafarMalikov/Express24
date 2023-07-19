package product;

import common.BaseEntity;
import lombok.*;
import user.User;

import java.time.LocalDateTime;
import java.util.UUID;
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Product extends BaseEntity<UUID> {
   private UUID restaurantId;
   private String name;
   private double price;
   private boolean isAvailable;

   public Product(UUID restaurantId, String name, double price, boolean isAvailable) {
      this.restaurantId = restaurantId;
      this.name = name;
      this.price = price;
      this.isAvailable = isAvailable;
   }

   public Product(UUID uuid, LocalDateTime created, LocalDateTime modified, User createdBy, User modifiedBy, UUID restaurantId, String name, double price, boolean isAvailable) {
      super(uuid, created, modified, createdBy, modifiedBy);
      this.restaurantId = restaurantId;
      this.name = name;
      this.price = price;
      this.isAvailable = isAvailable;
   }
}
