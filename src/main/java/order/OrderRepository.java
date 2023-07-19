package order;

import common.BaseRepository;

import java.util.UUID;

public class OrderRepository extends BaseRepository<Order, UUID> {
    private static final OrderRepository repository=new OrderRepository();
    public static OrderRepository getInstance(){
        return  repository;
    }
}
