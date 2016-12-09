package net.logvv.scdemo.ribbon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/* hystrix熔断机制  */
@Service
public class CalculateService {
	@Autowired
    RestTemplate restTemplate;
	
    @HystrixCommand(fallbackMethod = "addServiceFallback")
    public String addService(int a,int b) {
    	String url = "http://CALCULATE-SERVICE/add?a="+ a +"&b=" + b;
        return restTemplate.getForEntity(url, String.class).getBody();
    }
    
    // error handle
    public String addServiceFallback(int a,int b) {
        return "error, not response service.";
    }
}
