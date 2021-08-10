package com.jf.springcloud.controller;

import com.jf.springcloud.entities.CommonResult;
import com.jf.springcloud.entities.Payment;
import com.jf.springcloud.service.PaymentService;
import com.mysql.jdbc.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentServicel;
    @Resource
    private DiscoveryClient discoveryClient;
    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result=paymentServicel.create(payment);
        log.info("*******插入结果："+result);
        if(result>0){
            return new CommonResult(200,"success: server.port :"+serverPort,result);

        }else {
            return new CommonResult(444,"插入数据库失败",null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment=paymentServicel.getPaymentById(id);
        log.info("********插入结果："+payment);
        if(payment!=null){
            return new CommonResult(200, "success,serverPort:"+serverPort, payment);

        }else {
            return new CommonResult(444,"没有对应记录，查询id:"+id,null);
        }
    }
    @GetMapping("/payment/discovery")
    public Object discovery(){
        List<String> services=discoveryClient.getServices();
        for (String service:services){
            log.info("****dss*******service:"+service);
        }
        List<ServiceInstance> serviceInstances=discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance serviceInstance:serviceInstances){
            log.info(serviceInstance.getHost()+"\t"+serviceInstance.getInstanceId()+"\t"+serviceInstance.getPort()+"\t"+serviceInstance.getUri());
        }
        return this.discoveryClient;
    }
    @GetMapping("/payment/lb")
    public  String getPaymentLB(){
        return serverPort;
    }
    @GetMapping("/payment/feign")
    public String paymentFeign(){

        return serverPort;
    }
    @GetMapping("/payment/timeout")
    public String timeOut(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }
}
