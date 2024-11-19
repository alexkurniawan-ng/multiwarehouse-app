//package com.multiwarehouse.app.order.service.domain;
//
//import com.multiwarehouse.app.order.service.domain.dto.create.CreateOrderCommand;
//import com.multiwarehouse.app.order.service.domain.dto.create.CreateOrderResponse;
//import com.multiwarehouse.app.order.service.domain.dto.create.OrderAddress;
//import com.multiwarehouse.app.order.service.domain.dto.create.OrderItem;
//import com.multiwarehouse.app.order.service.domain.entity.Customer;
//import com.multiwarehouse.app.order.service.domain.entity.Order;
//import com.multiwarehouse.app.order.service.domain.entity.Product;
//import com.multiwarehouse.app.order.service.domain.entity.Seller;
//import com.multiwarehouse.app.order.service.domain.exception.OrderDomainException;
//import com.multiwarehouse.app.order.service.domain.mapper.OrderDataMapper;
//import com.multiwarehouse.app.order.service.domain.ports.input.service.OrderApplicationService;
//import com.multiwarehouse.app.order.service.domain.ports.output.repository.CustomerRepository;
//import com.multiwarehouse.app.order.service.domain.ports.output.repository.OrderRepository;
//import com.multiwarehouse.app.order.service.domain.ports.output.repository.SellerRepository;
//import com.multiwarehouse.app.domain.valueobject.*;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.math.BigDecimal;
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@SpringBootTest(classes = OrderTestConfiguration.class)
//public class OrderApplicationServiceTest {
//    @Autowired
//    private OrderApplicationService orderApplicationService;
//
//    @Autowired
//    private OrderDataMapper orderDataMapper;
//
//    @Autowired
//    private OrderRepository orderRepository;
//
//    @Autowired
//    private CustomerRepository customerRepository;
//
//    @Autowired
//    private SellerRepository sellerRepository;
//
//    private CreateOrderCommand createOrderCommand;
//    private CreateOrderCommand createOrderCommandWrongPrice;
//    private CreateOrderCommand createOrderCommandWrongProductPrice;
//    private final UUID CUSTOMER_ID = UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb41");
//    private final UUID SELLER_ID = UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb45");
//    private final UUID PRODUCT_ID = UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb48");
//    private final UUID ORDER_ID = UUID.fromString("15a497c1-0f4b-4eff-b9f4-c402c8c07afb");
//    private final BigDecimal PRICE = new BigDecimal("200.00");
//
//    @BeforeAll
//    public void init(){
//        createOrderCommand = CreateOrderCommand.builder()
//                .customerId(CUSTOMER_ID)
//                .sellerId(SELLER_ID)
//                .address(OrderAddress.builder()
//                        .street("Street_1")
//                        .postalCode("100AB")
//                        .city("Paris")
//                        .build())
//                .price(PRICE)
//                .items(List.of(OrderItem.builder()
//                                .productId(PRODUCT_ID)
//                                .quantity(1)
//                                .price(new BigDecimal("50.00"))
//                                .subTotal(new BigDecimal("50.00"))
//                                .build(),
//                        OrderItem.builder()
//                                .productId(PRODUCT_ID)
//                                .quantity(3)
//                                .price(new BigDecimal("50.00"))
//                                .subTotal(new BigDecimal("150.00"))
//                                .build()))
//                .build();
//
//        createOrderCommandWrongPrice = CreateOrderCommand.builder()
//                .customerId(CUSTOMER_ID)
//                .sellerId(SELLER_ID)
//                .address(OrderAddress.builder()
//                        .street("Street_1")
//                        .postalCode("100AB")
//                        .city("Paris")
//                        .build())
//                .price(new BigDecimal("250.00"))
//                .items(List.of(OrderItem.builder()
//                                .productId(PRODUCT_ID)
//                                .quantity(1)
//                                .price(new BigDecimal("50.00"))
//                                .subTotal(new BigDecimal("50.00"))
//                                .build(),
//                        OrderItem.builder()
//                                .productId(PRODUCT_ID)
//                                .quantity(3)
//                                .price(new BigDecimal("50.00"))
//                                .subTotal(new BigDecimal("150.00"))
//                                .build()))
//                .build();
//
//        createOrderCommandWrongProductPrice = CreateOrderCommand.builder()
//                .customerId(CUSTOMER_ID)
//                .sellerId(SELLER_ID)
//                .address(OrderAddress.builder()
//                        .street("Street_1")
//                        .postalCode("100AB")
//                        .city("Paris")
//                        .build())
//                .price(new BigDecimal("210.00"))
//                .items(List.of(OrderItem.builder()
//                                .productId(PRODUCT_ID)
//                                .quantity(1)
//                                .price(new BigDecimal("60.00"))
//                                .subTotal(new BigDecimal("60.00"))
//                                .build(),
//                        OrderItem.builder()
//                                .productId(PRODUCT_ID)
//                                .quantity(3)
//                                .price(new BigDecimal("50.00"))
//                                .subTotal(new BigDecimal("150.00"))
//                                .build()))
//                .build();
//
//        Customer customer = new Customer();
//        customer.setId(new CustomerId(CUSTOMER_ID));
//        Seller sellerResponse = Seller.builder()
//                .sellerId(new SellerId(createOrderCommand.getSellerId()))
//                .products(List.of(new Product(new ProductId(PRODUCT_ID), "product-1",
//                                new Money(new BigDecimal("50.00"))),
//                        new Product(new ProductId(PRODUCT_ID), "product-2",
//                                new Money(new BigDecimal("50.00")))))
//                .active(true)
//                .build();
//        Order order = orderDataMapper.createOrderCommandToOrder(createOrderCommand);
//        order.setId(new OrderId(ORDER_ID));
//
//        when(customerRepository.findCustomer(CUSTOMER_ID)).thenReturn(Optional.of(customer));
//        when(sellerRepository.findSellerInformation(orderDataMapper.createOrderCommandToSeller(createOrderCommand)))
//                .thenReturn(Optional.of(sellerResponse));
//        when(orderRepository.save(any(Order.class))).thenReturn(order);
//    }
//
//    @Test
//    public void testCreateOrder(){
//        /*System.out.println(createOrderCommand.getCustomerId());
//        System.out.println(createOrderCommand.getSellerId());
//        System.out.println(createOrderCommand.getAddress().getStreet());
//        System.out.println(createOrderCommand.getPrice());
//        System.out.println(createOrderCommand.getItems());*/
//        CreateOrderResponse createOrderResponse = orderApplicationService.createOrder(createOrderCommand);
//        assertEquals(createOrderResponse.getOrderStatus(), OrderStatus.AWAITING_PAYMENT);
//        assertEquals(createOrderResponse.getMessage(), "Order created successfully");
//        assertNotNull(createOrderResponse.getOrderTrackingId());
//    }
//
//    @Test
//    public void testCreateOrderWithWrongTotalPrice() {
//        OrderDomainException orderDomainException = assertThrows(OrderDomainException.class,
//                () -> orderApplicationService.createOrder(createOrderCommandWrongPrice));
//        assertEquals(orderDomainException.getMessage(),
//                "Total price: 250.00 is not equal to Order items total: 200.00!");
//    }
//
//    @Test
//    public void testCreateOrderWithWrongProductPrice() {
//        OrderDomainException orderDomainException = assertThrows(OrderDomainException.class,
//                () -> orderApplicationService.createOrder(createOrderCommandWrongProductPrice));
//        assertEquals(orderDomainException.getMessage(),
//                "Order item price: 60.00 is not valid for product " + PRODUCT_ID);
//    }
//    /*
//    @Test
//    public void testCreateOrderWithPassiveSeller() {
//        Seller sellerResponse = Seller.builder()
//                .sellerId(new SellerId(createOrderCommand.getSellerId()))
//                .products(List.of(new Product(new ProductId(PRODUCT_ID), "product-1", new Money(new BigDecimal("50.00"))),
//                        new Product(new ProductId(PRODUCT_ID), "product-2", new Money(new BigDecimal("50.00")))))
//                .active(false)
//                .build();
//        when(sellerRepository.findSellerInformation(orderDataMapper.createOrderCommandToSeller(createOrderCommand)))
//                .thenReturn(Optional.of(sellerResponse));
//        OrderDomainException orderDomainException = assertThrows(OrderDomainException.class,
//                () -> orderApplicationService.createOrder(createOrderCommand));
//        assertEquals(orderDomainException.getMessage(),
//                "Seller with id " + SELLER_ID + " is currently not active!");
//    }
//    */
//
//}
