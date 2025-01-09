//package com.multiwarehouse.app.payment.service.domain.rest;
//
//import com.multiwarehouse.app.payment.service.domain.dto.create.CreatePaymentCommand;
//import com.multiwarehouse.app.payment.service.domain.dto.create.CreatePaymentResponse;
//
//import com.multiwarehouse.app.payment.service.domain.ports.input.service.PaymentApplicationService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.UUID;
//
//@Slf4j
//@RestController
//@RequestMapping(value = "/payments", produces = "application/vnd.api.v1+json")
//public class PaymentController {
//
//    private final PaymentApplicationService paymentApplicationService;
//
//    public PaymentController(PaymentApplicationService paymentApplicationService) {
//        this.paymentApplicationService = paymentApplicationService;
//    }
//
//    @GetMapping("/generate")
//    public ResponseEntity<String> generateUUID() {
//        String uuid = UUID.randomUUID().toString();
//        log.info("Generated UUID: {}", uuid);
//        return ResponseEntity.ok(uuid);
//    }
//
//    @PostMapping
//    public ResponseEntity<CreatePaymentResponse> createPayment(@RequestBody CreatePaymentCommand createPaymentCommand) {
//        log.info("Creating payment for customer: {} for order: {}", createPaymentCommand.getCustomerId(),
//                createPaymentCommand.getOrderId());
//        CreatePaymentResponse createPaymentResponse = paymentApplicationService.createPayment(createPaymentCommand);
//        log.info("Payment created with order id: {}", createPaymentResponse.getPaymentId());
//        return ResponseEntity.ok(createPaymentResponse);
//    }
//}
