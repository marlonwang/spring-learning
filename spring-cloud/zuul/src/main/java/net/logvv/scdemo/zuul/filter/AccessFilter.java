package net.logvv.scdemo.zuul.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class AccessFilter extends ZuulFilter {
	
	private static Logger log = LoggerFactory.getLogger(AccessFilter.class);

	@Override
	public Object run() {
		// 过滤器的具体逻辑
		RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
        Object accessToken = request.getParameter("accessToken");
        if(accessToken == null) {
            log.warn("access token is empty");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            return null;
        }
        log.info("access token ok");
        return null;
	}

	@Override
	public boolean shouldFilter() {
		// 返回一个boolean类型来判断该过滤器是否要执行，所以通过此函数可实现过滤器的开关
		return true;
	}

	@Override
	public int filterOrder() {
		// 通过int值来定义过滤器的执行顺序
		return 0;
	}

	@Override
	public String filterType() {
		return "pre";
		/**
		 *  pre：可以在请求被路由之前调用
			routing：在路由请求时候被调用
			post：在routing和error过滤器之后被调用
			error：处理请求时发生错误时被调用
		 */
	}

}
