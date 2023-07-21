package restaurant;

import java.util.List;
import java.util.Optional;

public class RestaurantService {
    private static final RestaurantService restaurantService=new RestaurantService();

    private final RestaurantRepository repository=RestaurantRepository.getInstance();


    public List<Restaurant>findAll(){
        return repository.findAll();
    }


    public boolean  add(Restaurant restaurant){
        Optional<Restaurant> restaurant1 = repository.findByName(restaurant.getName());
        if (restaurant1.isPresent()){
            return false;
        }
        repository.add(restaurant);
        return true;

    }

    public static RestaurantService getInstance(){
        return restaurantService;
    }
}
