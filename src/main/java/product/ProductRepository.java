package product;


import common.BaseRepository;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductRepository extends BaseRepository<Product,UUID> {
    private static final ProductRepository repository=new ProductRepository();

    public List<Product>findByRestaurantId(UUID restaurantId){
       return entities.stream()
                .filter(product -> product.getRestaurantId()
                        .equals(restaurantId))
                .toList();

    }

    public Optional<Product> findFoodByiId(Product product ){
       return entities.stream().filter(product1 -> product1.getRestaurantId().equals(product.getRestaurantId()))
               .filter(product1 -> product1.getName().equals(product.getName())).findFirst();
    }

    public static ProductRepository getInstance(){
        return repository;
    }

}
