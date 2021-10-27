package edu.rest.shop.orders.controllers;

import edu.rest.shop.orders.domain.Orders;
import edu.rest.shop.orders.exceptions.OrderNotFoundException;
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
    public List<Orders> getAll(){
        return (List<Orders>) ordersRepo.findAll();
    }

    @GetMapping("{id}")
    public Orders getOne(@PathVariable(value = "id") Long orderId)throws OrderNotFoundException {
        return ordersRepo.findById(orderId)
                .orElseThrow(OrderNotFoundException::new);
    }


    @PostMapping
    public Orders create(@RequestBody Orders order){
        order.setCreationDate(LocalDateTime.now());
        return ordersRepo.save(order);
    }

    @PutMapping("{id}")
    public Orders update(
            @PathVariable("id") Long orderId,
            @RequestBody Orders order) throws OrderNotFoundException
    {
        Orders byId = ordersRepo.findById(orderId).orElseThrow(OrderNotFoundException::new);
        BeanUtils.copyProperties(order,byId,"id","creationDate");
        return ordersRepo.save(byId);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long orderId)throws OrderNotFoundException {
        Orders order = ordersRepo.findById(orderId).orElseThrow(OrderNotFoundException::new);
        ordersRepo.delete(order);
    }


}
