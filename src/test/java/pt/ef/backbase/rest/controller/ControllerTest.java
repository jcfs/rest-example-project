package pt.ef.backbase.rest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
public class ControllerTest {

	@Autowired
	private AtmController atmController;
	
	@Autowired
	private NewAtmController newAtmController;

	/**
	 * Test the json return for the generic controller
	 *
	 * @throws Exception
	 */
	@Test
	public void testAtmController() throws Exception {
		MockMvc mockMvc1 = MockMvcBuilders.standaloneSetup(this.atmController).build();
		mockMvc1.perform(get("/atm")).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
		mockMvc1.perform(get("/atm/VEENENDAAL/")).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
		mockMvc1.perform(get("/atm/VEENENDAAL/true")).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
		mockMvc1.perform(get("/atm/VEENENDAAL/false")).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
		
		MockMvc mockMvc2 = MockMvcBuilders.standaloneSetup(this.newAtmController).build();
		mockMvc2.perform(get("/atm")).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
		mockMvc2.perform(get("/atm/VEENENDAAL/")).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
		mockMvc2.perform(get("/atm/VEENENDAAL/true")).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
		mockMvc2.perform(get("/atm/VEENENDAAL/false")).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
	}

}
