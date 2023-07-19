package order;

import common.BaseEntity;
import lombok.*;
import product.Product;
import user.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Order extends BaseEntity<UUID> {
    private UUID userId;
    private UUID restaurantId;
    private UUID cookId;
    private UUID courierId;
    private List<Product>products;
    private OrderStatus orderStatus;

    public Order(UUID id, LocalDateTime created, LocalDateTime modified, User createdBy, User modifiedBy, UUID userId, UUID restaurantId, UUID cookId, UUID courierId, List<Product> products, OrderStatus orderStatus) {
        super(id, created, modified, createdBy, modifiedBy);
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.cookId = cookId;
        this.courierId = courierId;
        this.products = products;
        this.orderStatus = orderStatus;
    }
}
