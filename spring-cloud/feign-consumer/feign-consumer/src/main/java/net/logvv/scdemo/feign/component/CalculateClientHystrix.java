package net.logvv.scdemo.feign.component;

import net.logvv.scdemo.feign.impl.CalculateClient;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class CalculateClientHystrix implements CalculateClient {
	
    public Integer add(@RequestParam(value = "a") Integer a, @RequestParam(value = "b") Integer b) {
        return -9999;
    }
}