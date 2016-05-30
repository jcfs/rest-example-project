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
public class ResponseTrimmerInterceptor implements ClientHttpRequestInterceptor {
    final static Logger logger = Logger.getLogger(ResponseTrimmerInterceptor.class);

    private static final String ERROR_STRING = ")]}',\n";

    /**
     * {@inheritDoc}
     * @see org.springframework.http.client.ClientHttpRequestInterceptor#intercept(org.springframework.http.HttpRequest, byte[], org.springframework.http.client.ClientHttpRequestExecution)
     */
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        ClientHttpResponse response = execution.execute(request, body);
        boolean markSupported = response.getBody().markSupported();

        byte[] byteArray = new byte[ERROR_STRING.length()];

        if (markSupported) {
            response.getBody().mark(ERROR_STRING.length());
        }

        response.getBody().read(byteArray, 0, ERROR_STRING.length());

        String errorString = new String(byteArray);

        if (markSupported && !errorString.equals(ERROR_STRING)) {
            response.getBody().reset();
        }

        return response;
    }
}
