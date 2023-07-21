package product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ProductService {

    private static final ProductService productService=new ProductService();

    private final ProductRepository productRepository=ProductRepository.getInstance();


    public List<Product>findByRestaurantId(UUID restaurantId){
        return productRepository.findByRestaurantId(restaurantId);
    }


    public  boolean add(Product product){
        Optional<Product> foodByiId = productRepository.findFoodByiId(product);
        if (foodByiId.isPresent()){
            return false;
        }else {
            productRepository.add(product);
            return true;
        }
    }
    public static ProductService getInstance(){
        return productService;

    }

    public Optional<Product> findById(UUID productId) {
       return productRepository.findById(productId);

    }
}
