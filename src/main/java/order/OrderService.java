package order;

import java.util.List;
import java.util.UUID;

public class OrderService {
    private static final OrderService orderService=new OrderService();

   private   final OrderRepository orderRepository=  OrderRepository.getInstance();


  public  void create(Order order){
      orderRepository.add(order);


  }

    public static OrderService getInstance(){
        return orderService;
    }

    public List<Order> findByUserId(UUID id) {
        return orderRepository.findByUserId(id);
    }
}
