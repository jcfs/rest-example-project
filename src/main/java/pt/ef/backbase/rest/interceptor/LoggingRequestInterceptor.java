/*
 * Copyright © 2016 EF - Tecnologias de Software, S.A.
 *
 * Todo o conteúdo deste ficheiro, quer na sua forma de código fonte, quer na de
 * código objecto/executável, é propriedade exclusiva da empresa EF - Tecnologias
 * de Software, S.A., não sendo permitida a sua reprodução ou utilização para
 * qualquer fim sem a explícita autorização escrita desta empresa.
 */
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

    final static Logger logger = Logger.getLogger(LoggingRequestInterceptor.class);

    /**
     * {@inheritDoc}
     * @see org.springframework.http.client.ClientHttpRequestInterceptor#intercept(org.springframework.http.HttpRequest, byte[], org.springframework.http.client.ClientHttpRequestExecution)
     */
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        logger.info("Request: " + request.getMethod() + " " + request.getURI() + " " + request.getHeaders());
        ClientHttpResponse response = execution.execute(request, body);
        logger.info("Response: " + response.getHeaders() + " " + response.getStatusText());

        return response;
    }
}
