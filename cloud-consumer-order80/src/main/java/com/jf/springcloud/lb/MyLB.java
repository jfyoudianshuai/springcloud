package com.jf.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLB implements LoadBalancer{
    private AtomicInteger atomicInteger=new AtomicInteger(0);


    private final int getAndIncrement() {
        int current;
        int next;
        do{
            current=atomicInteger.get();
            next=current >= 2147483647 ? 0 : current + 1;
            System.out.println("**************************************");
        }while(!this.atomicInteger.compareAndSet(current,next));
        System.out.println("第几次访问dd，next="+next);
        return next;
    }
    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
       int index = getAndIncrement() % serviceInstances.size();
       return serviceInstances.get(index);
    }
}
