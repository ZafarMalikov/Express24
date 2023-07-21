package ui;

import product.Product;
import product.ProductService;
import restaurant.Restaurant;
import restaurant.RestaurantService;
import user.Role;
import user.User;
import user.UserService;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.UUID;

public class MainUi {
    {
        RestaurantService restaurantService=RestaurantService.getInstance();
        Restaurant restaurant1=new Restaurant(UUID.randomUUID(), LocalDateTime.now(),LocalDateTime.now(),null,null,"chorsu","belissimoo");
        Restaurant restaurant2=new Restaurant(UUID.randomUUID(), LocalDateTime.now(),LocalDateTime.now(),null,null,"chorsu","feedup");
        Restaurant restaurant3=new Restaurant(UUID.randomUUID(), LocalDateTime.now(),LocalDateTime.now(),null,null,"chorsu","look");
        Restaurant restaurant4=new Restaurant(UUID.randomUUID(), LocalDateTime.now(),LocalDateTime.now(),null,null,"chorsu","chopar");
        Restaurant restaurant5=new Restaurant(UUID.randomUUID(), LocalDateTime.now(),LocalDateTime.now(),null,null,"chorsu","5 qozon");

        restaurantService.add(restaurant1);
        restaurantService.add(restaurant2);
        restaurantService.add(restaurant3);
        restaurantService.add(restaurant4);
        restaurantService.add(restaurant5);


        Product product1 = new Product(UUID.randomUUID(),LocalDateTime.now(),LocalDateTime.now(),null,null,restaurant5.getId(),"osh",10,true);
        Product product2 = new Product(UUID.randomUUID(),LocalDateTime.now(),LocalDateTime.now(),null,null,restaurant5.getId(),"honim",10,true);
        Product product3 = new Product(UUID.randomUUID(),LocalDateTime.now(),LocalDateTime.now(),null,null,restaurant5.getId(),"manti",10,true);
        Product product4 = new Product(UUID.randomUUID(),LocalDateTime.now(),LocalDateTime.now(),null,null,restaurant4.getId(),"peperoni ",10,true);
        Product product5 = new Product(UUID.randomUUID(),LocalDateTime.now(),LocalDateTime.now(),null,null,restaurant4.getId(),"chopar special",10,true);
        Product product6 = new Product(UUID.randomUUID(),LocalDateTime.now(),LocalDateTime.now(),null,null,restaurant3.getId(),"Classic",10,true);
        Product product7 = new Product(UUID.randomUUID(),LocalDateTime.now(),LocalDateTime.now(),null,null,restaurant3.getId(),"Junior burger",10,true);
        Product product8 = new Product(UUID.randomUUID(),LocalDateTime.now(),LocalDateTime.now(),null,null,restaurant3.getId(),"donar pizza",10,true);
        Product product9 = new Product(UUID.randomUUID(),LocalDateTime.now(),LocalDateTime.now(),null,null,restaurant2.getId(),"disert",10,true);
        Product product10 = new Product(UUID.randomUUID(),LocalDateTime.now(),LocalDateTime.now(),null,null,restaurant2.getId(),"xot dog",10,true);
        Product product11 = new Product(UUID.randomUUID(),LocalDateTime.now(),LocalDateTime.now(),null,null,restaurant2.getId(),"lavash",10,true);
        Product product12 = new Product(UUID.randomUUID(),LocalDateTime.now(),LocalDateTime.now(),null,null,restaurant1.getId(),"peperoni",10,true);
        Product product13 = new Product(UUID.randomUUID(),LocalDateTime.now(),LocalDateTime.now(),null,null,restaurant1.getId(),"Classic",10,true);
        Product product14 = new Product(UUID.randomUUID(),LocalDateTime.now(),LocalDateTime.now(),null,null,restaurant1.getId(),"donar pizza",10,true);

        ProductService productService = new ProductService();
        productService.add(product1);
        productService.add(product2);
        productService.add(product3);
        productService.add(product4);
        productService.add(product5);
        productService.add(product6);
        productService.add(product7);
        productService.add(product8);
        productService.add(product9);
        productService.add(product9);
        productService.add(product10);
        productService.add(product12);
        productService.add(product13);
        productService.add(product14);

    }


    public static Scanner scanner = new Scanner(System.in);
    public static UserService userService=new UserService();

    public  void start(){
        boolean isExited=false;
        while (!isExited){
            System.out.print("""
                    1. SinIn
                    2. SingUp
                    0. Exit
                    >>>\s
                    """);
            int command = scanner.nextInt();
            switch (command){
                case 1 ->{
                    signIn();
                }
                case 2 ->{
                    signUp();
                }
                case 0 -> isExited=true;
                default -> System.out.println("Noto'g'ri comman kiritingiz");
            }




        }

    }

    private void signUp() {
        String phoneNumber = getConsole("Enter phone Number ");
        String name = getConsole("Enter name ");
        String surname = getConsole("Enter surname");
        String password = getConsole("Enter password ");
        System.out.print("enter balance");
        double balance = scanner.nextDouble();
        User user = User.builder()
                .name(name)
                .surname(surname)
                .phoneNumber(phoneNumber)
                .balance(balance)
                .created(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .password(password)
                .roel(Role.USER)
                .build();

        boolean isCreat = userService.creat(user);
        if (isCreat){
            System.out.println("yangi user yaratildi");
        }else {
            System.out.println("bu telefon raqam royxatdan o'tib bo'lgan");
        }

    }

    private void signIn() {
        String phoneNumber = getConsole("Enter phone number");
        String password = getConsole("Enter password");
        User user = userService.signIn(password, phoneNumber);
        if (user==null){
            System.out.println("phone number yoki password hato");
        }else {
            switch (user.getRoel()) {
                case USER ->{
                    UserUi userUi = new UserUi();
                    userUi.start(user);
                }
                case COOK -> {
                    CookUi cookUi=new CookUi();
                    cookUi.start(user);
                }
                case COURIER -> {
                    CourierUi courierUi = new CourierUi();
                    courierUi.start(user);
                }
                case ADMIN -> {
                    AdminUi adminUi = new AdminUi();
                    adminUi.start(user);
                }
            }
        }

    }

    private String getConsole(String str){
        System.out.printf(str);
        return scanner.next();
    }
}
