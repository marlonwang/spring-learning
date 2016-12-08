package net.logvv.scdemo.feign.impl;

import net.logvv.scdemo.feign.component.CalculateClientHystrix;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/*使用@FeignClient("calculate-service")注解来绑定该接口对应calculate-service服务
 * fallback 指定熔断回调
 * */

@FeignClient(value = "calculate-service", fallback = CalculateClientHystrix.class)
public interface CalculateClient {
	
	// calculate service add rest-api
	@RequestMapping(method = RequestMethod.GET, value = "/add")
    Integer add(@RequestParam(value = "a") Integer a, @RequestParam(value = "b") Integer b);
}
