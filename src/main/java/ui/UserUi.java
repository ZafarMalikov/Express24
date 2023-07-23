package ui;

import cart.Cart;
import order.Order;
import order.OrderService;
import order.OrderStatus;
import product.Product;
import product.ProductService;
import restaurant.Restaurant;
import restaurant.RestaurantService;
import user.User;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class UserUi {
    public static Scanner scanner = new Scanner(System.in);
    private final static RestaurantService restaurantService=RestaurantService.getInstance();
    private final static ProductService productService=ProductService.getInstance();
    private final static OrderService orderService=OrderService.getInstance();

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
                    showOrders(user);
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

    private void showOrders(User user) {
        List<Order> orders= orderService.findByUserId(user.getId());
        for (int i = 0; i < orders.size(); i++) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm.ss");
            Order order = orders.get(i);
            System.out.println(i+". "+dateTimeFormatter.format(order.getCreated())+ "  "+order.getOrderStatus());
            Optional<Restaurant> byRestaurantId = restaurantService.findByRestaurantId(order.getRestaurantId());
            if (byRestaurantId.isPresent()){
            System.out.println("sotib olingan restaurant >>"+byRestaurantId.get().getName());
                System.out.println("--------------------");
            }else {
                System.out.println("Buyurtmalar yo'q");
            }

            for (Product product : order.getProducts()) {
                System.out.println(" - "+product.getName()+ " - "+product.getPrice());
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
                   createOrder(cart,user,restaurant);
                   isExited=true;
               }
               case "<" ->{
                   isExited=true;
                   break;
               }
               case "#" -> {
                   showCart(cart);
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

    private void createOrder(Cart cart, User user,Restaurant restaurant) {
        int sum=0;
        List<Product> products = new ArrayList<>();
        for (UUID productId : cart.getProducts()) {
            Product product = productService.findById(productId).get();
            products.add(product);
              sum+=product.getPrice();
        }
        if (user.getBalance()>=sum){
            user.setBalance(user.getBalance()-sum);
            Order order = new Order(UUID.randomUUID(),LocalDateTime.now(),LocalDateTime.now(),null,null,user.getId(),restaurant.getId(),null,null,
                    products,OrderStatus.NEW);
            orderService.create(order);
            System.out.println("Xaridingiz uchun raxmat");
        }else {
            System.out.println("balansingiz yetarli emas " +user.getBalance() );
            System.out.println("Mahsulot summasi >> "+ sum);
        }
    }

    private void showCart(Cart cart) {
        boolean isExited=false;
        while (!isExited){

        List<UUID> selectedProduct = cart.getProducts();
        AtomicReference<Double> counter = new AtomicReference<>((double) 0);
        List<Product> list = selectedProduct.stream()
                .map(productService::findById)
                .map(Optional::get)
                .toList();
        list= new ArrayList<>(list);

        list.stream()
                .map(Product::getPrice)
                .forEach(price-> counter.updateAndGet(v->v+price));


        System.out.println(counter);
        System.out.println("-----------------------------");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i+". "+list.get(i).getName()+" - "+list.get(i).getPrice());
        }
            System.out.println("-1. orqaga");
            int command = scanner.nextInt();
            switch (command){
                case -1-> {
                    isExited=true;
                }
                default ->{
                    if (command>=0&&command<list.size()){
                        Product product = list.get(command);
                        cart.getProducts().remove(command);


                    }else {
                        System.out.println("togri buyruq kiriting ");
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
