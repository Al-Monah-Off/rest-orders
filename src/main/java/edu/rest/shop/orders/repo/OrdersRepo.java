package edu.rest.shop.orders.repo;

import edu.rest.shop.orders.domain.Orders;
import org.springframework.data.repository.CrudRepository;

public interface OrdersRepo extends CrudRepository <Orders,Long> {
    Orders findById(Integer id);
}
