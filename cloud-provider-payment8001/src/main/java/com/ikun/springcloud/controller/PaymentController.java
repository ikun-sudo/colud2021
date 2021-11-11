package com.ikun.springcloud.controller;

import com.ikun.springcloud.entities.CommonResult;
import com.ikun.springcloud.entities.Payment;
import com.ikun.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;

    @RequestMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("插入的结果" + result);
        if (result > 0) {
            return new CommonResult(200, "插入成功"+serverPort);

        } else {
            return new CommonResult(444, "插入失败"+serverPort);
        }
    }

    @RequestMapping("/payment/get/{id}")
    public CommonResult paymentById(@PathVariable("id") Long id) {
        Payment paymentById = paymentService.getPaymentById(id);
        log.info("查询的结果" + paymentById);
        if (paymentById != null) {
            return new CommonResult(200, "查询成功"+serverPort, paymentById);

        } else {
            return new CommonResult(444, "查询失败"+serverPort, null);
        }
    }

    @GetMapping("/payment/zipkin")
    public String paymentZipkin() {
        return "hi ,i'am paymentzipkin server fall back，welcome to here, O(∩_∩)O哈哈~";
    }

}
