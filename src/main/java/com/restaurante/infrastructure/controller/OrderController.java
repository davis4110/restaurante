package com.restaurante.infrastructure.controller;

import com.restaurante.application.service.*;
import com.restaurante.domain.*;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user/order")
@Slf4j
public class OrderController {

    private final CartService cartService;
    private final RestaurantService userService;
    private final ProductService productService;
    private final OrderService orderService;
    private final OrderProductService orderProductService;
    private final StockService stockService;
    private final ValidateStock validateStock;

    private final Integer UNIT_IN = 0;

    public OrderController(CartService cartService, RestaurantService userService, ProductService productService, OrderService orderService, OrderProductService orderProductService, StockService stockService, ValidateStock validateStock) {
        this.cartService = cartService;
        this.userService = userService;
        this.productService = productService;
        this.orderService = orderService;
        this.orderProductService = orderProductService;
        this.stockService = stockService;
        this.validateStock = validateStock;
    }

    @GetMapping("/sumary-order")
    public String showSumaryOrder(Model model, HttpSession httpSession){
        log.info("iduser desde la variable de sesion: {}",httpSession.getAttribute("iduser").toString());
        Restaurant user = userService.buscarById(Integer.parseInt(httpSession.getAttribute("iduser").toString()));
        model.addAttribute("cart", cartService.getItemCarts());
        model.addAttribute("total", cartService.getTotalCart());
        model.addAttribute("user", user);
        model.addAttribute("id", httpSession.getAttribute("iduser").toString());
        return "/user/sumaryorder";
    }

    @GetMapping("/create-order")
    public String createOrder(RedirectAttributes attributes, HttpSession httpSession){
        log.info("create order..");
        log.info("iduser desde la variable de sesion: {}",httpSession.getAttribute("iduser").toString());
        //Obtener un usuario temporal
        Restaurant user = userService.buscarById(Integer.parseInt(httpSession.getAttribute("iduser").toString()));

        //order
        Order order = new Order();
        order.setDateCreated(LocalDateTime.now());
        order.setUser(user);

        order = orderService.createOrder(order);
        List<OrderProduct> orderProducts = new ArrayList<>();

        // itemcart - orderproduct

        for (ItemCart itemCart: cartService.getItemCarts()){
            orderProducts.add(new OrderProduct(
                productService.getProductById(itemCart.getIdProduct()),
                itemCart.getQuantity(),
                order));
        }

        //guardar
        orderProducts.forEach(
                op -> {
                    orderProductService.create(op);
                    Stock stock = new Stock();
                    stock.setDateCreated(LocalDateTime.now());
                    stock.setProduct(op.getProduct());
                    stock.setDescription("venta");
                    stock.setUnitIn(UNIT_IN);
                    stock.setUnitOut(op.getQuantity());
                    stockService.saveStock(validateStock.calculateBalance(stock));
                }
        );
        //vaciar el carrito
        cartService.removeAllItemsCart();
        attributes.addFlashAttribute("id", httpSession.getAttribute("iduser").toString());
        return "redirect:/home";
    }
}
