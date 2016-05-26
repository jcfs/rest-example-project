package pt.ef.backback.rest.logging;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author jsalavisa
 */
public class RequestInterceptorLogger extends HandlerInterceptorAdapter {
	final static Logger logger = Logger.getLogger(HandlerInterceptorAdapter.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		logger.info("INCOMING REQUEST: " + request.getMethod() + " > " + request.getRequestURL());
		return super.preHandle(request, response, handler);
	}
}
