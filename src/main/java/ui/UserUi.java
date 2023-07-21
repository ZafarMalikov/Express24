package ui;

import cart.Cart;
import product.Product;
import product.ProductService;
import restaurant.Restaurant;
import restaurant.RestaurantService;
import user.User;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class UserUi {
    public static Scanner scanner = new Scanner(System.in);
    private final static RestaurantService restaurantService=RestaurantService.getInstance();
    private final static ProductService productService=ProductService.getInstance();


    public void start(User user){
        boolean isExited=false;
        while (!isExited){
        System.out.println("""
                1. Restaranlar
                2. Buyurtmalar
                3. Balance
                0. Exit
                >>>\s
                """
        );
            int  command = scanner.nextInt();
            switch (command){
                case 1-> {
                    showRestaurant(user);
                }
                case 2-> {

                }
                case 3-> {
                    showBalance(user);
                }
                case 0-> {
                    isExited=true;
                }
                default -> System.out.println("Notogori command kiritingiz");
            }
        }
    }

    private void showRestaurant(User user) {
        List<Restaurant> all = restaurantService.findAll();
        for (int i = 0; i < all.size(); i++) {
            System.out.println(i+"  "+all.get(i).getName());
        }
        System.out.print("Restaurant tanlang >>> ");
        int index = scanner.nextInt();
        if (index>=0&&all.size()>index){
           showProductsByRestaurant(all.get(index),user);

        }else {
            System.out.println("notogri restaurant tanladingiz");
        }

    }

    private void showProductsByRestaurant(Restaurant restaurant,User user) {
        Cart cart = new Cart(UUID.randomUUID(), LocalDateTime.now(), LocalDateTime.now(), user, user, user.getId(), restaurant.getId(), new ArrayList<>(), null);

        boolean isExited=false;
        while (!isExited){
            List<Product> products = productService.findByRestaurantId(restaurant.getId());

            for (int i = 0; i < products.size(); i++) {
                Product product = products.get(i);
                System.out.println(i+". "+ product.getName()+" - "+product.getPrice());
            }

            System.out.print("""
                    > Oldinga
                    < Orqaga
                    # Savatcha
                    >>>\s
                    """);
            String command = scanner.next();
            switch (command){
               case ">" ->{

               }
               case "<" ->{
                   isExited=true;
               }
               case "#" -> {
                   List<UUID> selectedProduct = cart.getProducts();
                   AtomicReference<Double> counter = new AtomicReference<>((double) 0);
                   selectedProduct.stream()
                            .map(productService::findById)
                            .map(Optional::get)
                            .map(Product::getPrice)
                            .forEach(price-> counter.updateAndGet(v->v+price));
                   System.out.println("sizning buyurtmalaringizni  narxi>> "+counter);

               }
                default -> {
                   try {
                       int index = Integer.parseInt(command);
                       int newProduct=0;
                       if (index>=0&&index<products.size()){
                           Product product = products.get(index);
                           cart.getProducts().add(product.getId());
                           System.out.println(product.getName()+ " "+ "savatga qoshildi");
                       }else {
                           System.out.println("notogri product tanladingiz ");
                       }
                   }catch (NumberFormatException e){
                       System.out.println("siz notogri command kiritingz!");
                   }
                }
            }

        }

    }

    private void showBalance(User user) {
        System.out.println("Your balce >> "+user.getBalance());

    }

    private String getConsole(String str){
        System.out.printf(str);
        return scanner.next();
    }
}
