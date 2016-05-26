package pt.ef.backback.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pt.ef.backback.rest.api.Bean;

/**
 * Controller responsible to handle the logic for all beans
 * 
 * @author jsalavisa
 */
@Controller
public class GenericController {

	/**
	 * Returns the array of beans
	 * 
	 * @return the array of bean
	 */
	@RequestMapping("/get")
	@ResponseBody
	public Bean[] getBeans() {
		List<Bean> beans = new ArrayList<Bean>();
		return beans.toArray(new Bean[beans.size()]);
	}
}
