package com.yunusen.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yunusen
 * @version 1.0
 * @date 2020/8/8 15:41
 */
@Component
public class MyLB implements LoadBalancer {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAndIncrement(){
        int current;
        int next;
        do{
            current = this.atomicInteger.get();
            next = current >= Integer.MAX_VALUE ? 0 : current + 1;//Integer.MAX_VALUE=2147483647
        }while (!this.atomicInteger.compareAndSet(current, next));
        System.out.println("**次数next: " + next);
        return next;
    }

    // 负载均衡算法：rest接口第几次请求树 % 服务器集群总数量 = 实际调用服务器位置下标，每次服务器重启后rest接口从1开始计数
    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {

        int index = getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }
}
