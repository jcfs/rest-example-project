package pt.ef.backbase.rest.interceptor;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

/**
 * @author jsalavisa
 */
public class LoggingRequestInterceptor implements ClientHttpRequestInterceptor {

	private static final Logger LOG = Logger.getLogger(LoggingRequestInterceptor.class);

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.springframework.http.client.ClientHttpRequestInterceptor#intercept(org.springframework.http.HttpRequest,
	 *      byte[], org.springframework.http.client.ClientHttpRequestExecution)
	 */
	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Request: " + request.getMethod() + " " + request.getURI() + " " + request.getHeaders());
		}

		ClientHttpResponse response = execution.execute(request, body);

		if (LOG.isDebugEnabled()) {
			LOG.debug("Response: " + response.getHeaders() + " " + response.getStatusText());
		}

		return response;
	}
}
