package edu.rest.shop.orders.controllers;

import edu.rest.shop.orders.domain.Orders;
import edu.rest.shop.orders.repo.OrdersRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("orders")
public class OrdersController {
   private final OrdersRepo ordersRepo;

   @Autowired
    public OrdersController(OrdersRepo ordersRepo) {
        this.ordersRepo = ordersRepo;
   }


    @GetMapping
    public List<Orders> ordersList(){
        return (List<Orders>) ordersRepo.findAll();
    }

    @GetMapping("{id}")
    public Orders getOne(@PathVariable("id") Integer orderId){
        return ordersRepo.findById(orderId);
    }


    @PostMapping
    public Orders create(@RequestBody Orders order){
       order.setCreationDate(LocalDateTime.now());
        return ordersRepo.save(order);
    }

    @PutMapping("{id}")
    public Orders update(
            @PathVariable("id") Integer orderId,
            @RequestBody Orders order)
    {
        Orders byId = ordersRepo.findById(orderId);
        BeanUtils.copyProperties(order,byId,"id");
        return ordersRepo.save(byId);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Integer orderId){

        ordersRepo.delete(ordersRepo.findById(orderId));
    }


}
