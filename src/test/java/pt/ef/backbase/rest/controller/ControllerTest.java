package pt.ef.backbase.rest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
public class ControllerTest {

    @Autowired
    private AtmController atmController;
    @Autowired
    private RequestMappingHandlerAdapter handlerAdapter;
    @Autowired
    private RequestMappingHandlerMapping handlerMapping;

    /**
     * Test the json return for the generic controller
     *
     * @throws Exception
     */
    @Test
    public void testAtmController() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(this.atmController).build();
        mockMvc.perform(get("/atm")).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
        mockMvc.perform(get("/atm/XXX/")).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
        mockMvc.perform(get("/atm/XXX/true")).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
        mockMvc.perform(get("/atm/XXX/false")).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
        mockMvc.perform(get("/logout"));
    }

    /**
     * Test the code in the logging interceptor
     *
     * @throws Exception
     */
    @Test
    public void testLoggingInterceptor() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRequestURI("/atm");
        request.setMethod("GET");

        MockHttpServletResponse response = new MockHttpServletResponse();

        HandlerExecutionChain handlerExecutionChain = handlerMapping.getHandler(request);

        HandlerInterceptor[] interceptors = handlerExecutionChain.getInterceptors();

        for (HandlerInterceptor interceptor : interceptors) {
            interceptor.preHandle(request, response, handlerExecutionChain.getHandler());
        }

        ModelAndView mav = handlerAdapter.handle(request, response, handlerExecutionChain.getHandler());

        for (HandlerInterceptor interceptor : interceptors) {
            interceptor.postHandle(request, response, handlerExecutionChain.getHandler(), mav);
        }

        Assert.assertEquals(200, response.getStatus());

    }

}
