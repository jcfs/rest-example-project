package pt.ef.backbase.rest.support;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
public class WebConfiguration extends WebMvcConfigurationSupport {

	@Bean
	@Override
	public RequestMappingHandlerMapping requestMappingHandlerMapping() {
		ApiVersionRequestMappingHandlerMapping handlerMapping = new ApiVersionRequestMappingHandlerMapping("v");
		handlerMapping.setOrder(0);
		handlerMapping.setInterceptors(getInterceptors());
		handlerMapping.setContentNegotiationManager(mvcContentNegotiationManager());
		handlerMapping.setUseTrailingSlashMatch(false);
		handlerMapping.setUseSuffixPatternMatch(false);

		return handlerMapping;
	}
}