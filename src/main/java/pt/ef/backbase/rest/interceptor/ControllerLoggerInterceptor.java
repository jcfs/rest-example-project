package pt.ef.backbase.rest.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author jsalavisa
 */
public class ControllerLoggerInterceptor extends HandlerInterceptorAdapter {
	private static final Logger LOG = Logger.getLogger(HandlerInterceptorAdapter.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		if (LOG.isDebugEnabled()) {
			LOG.debug("Incoming Request: " + request.getMethod() + " > " + request.getRequestURL());
		}

		return super.preHandle(request, response, handler);
	}
}
