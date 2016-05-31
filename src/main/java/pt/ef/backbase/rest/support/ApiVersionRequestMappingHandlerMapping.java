package pt.ef.backbase.rest.support;

import java.lang.reflect.Method;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.servlet.mvc.condition.ConsumesRequestCondition;
import org.springframework.web.servlet.mvc.condition.HeadersRequestCondition;
import org.springframework.web.servlet.mvc.condition.ParamsRequestCondition;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.ProducesRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

public class ApiVersionRequestMappingHandlerMapping extends RequestMappingHandlerMapping {

	private final String prefix;

	public ApiVersionRequestMappingHandlerMapping(String prefix) {
		this.prefix = prefix;
	}

	@Override
	protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
		RequestMappingInfo info = super.getMappingForMethod(method, handlerType);

		RestApiVersion methodAnnotation = AnnotationUtils.findAnnotation(method, RestApiVersion.class);
		if (methodAnnotation != null) {
			RequestCondition<?> methodCondition = getCustomMethodCondition(method);
			info = createApiVersionInfo(methodAnnotation, methodCondition).combine(info);
		} else {
			RestApiVersion typeAnnotation = AnnotationUtils.findAnnotation(handlerType, RestApiVersion.class);
			if (typeAnnotation != null) {
				RequestCondition<?> typeCondition = getCustomTypeCondition(handlerType);
				info = createApiVersionInfo(typeAnnotation, typeCondition).combine(info);
			}
		}

		return info;
	}

	private RequestMappingInfo createApiVersionInfo(RestApiVersion annotation, RequestCondition<?> customCondition) {
		int[] values = annotation.value();
		String[] patterns = new String[values.length];
		for (int i = 0; i < values.length; i++) {
			patterns[i] = prefix + values[i];
		}

		return new RequestMappingInfo(new PatternsRequestCondition(patterns, getUrlPathHelper(), getPathMatcher(), useSuffixPatternMatch(),
				useTrailingSlashMatch(), getFileExtensions()), new RequestMethodsRequestCondition(), new ParamsRequestCondition(),
				new HeadersRequestCondition(), new ConsumesRequestCondition(), new ProducesRequestCondition(), customCondition);
	}

}