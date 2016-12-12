package net.logvv.swagger.web;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.annotations.ApiIgnore;

/**
 * Author: wangwei
 * Created on 2016/12/5 14:02.
 */
@RestController
public class HomeController {

	@ApiIgnore
	@ApiOperation(value="获取首页", notes="获取首页内容")
	@RequestMapping(value="/home", method = RequestMethod.GET)
    public String index(){
        return "hello world";
    }
	
	@ApiOperation(value="当前时间戳", notes="查看当前时间戳")
	@ApiImplicitParam(name = "val", value = "输入值", required = true, dataType = "String")
	@RequestMapping(value="/sys/timestamp", method=RequestMethod.GET)
	public String date(@RequestParam("val")String val){
		return val + System.currentTimeMillis();
	}

}
