package pt.ef.backbase.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pt.ef.backbase.core.service.AtmCoreService;
import pt.ef.backbase.rest.api.Atm;

/**
 * Controller that will export the rest API interface to be consumed
 *
 * @author jsalavisa
 */
@Controller
public class AtmController {

	@Autowired
	private AtmCoreService atmCoreService;

	/**
	 * Returns the array of ATMS
	 *
	 * @return the array atms
	 */
	@RequestMapping("/atm")
	@ResponseBody
	public Atm[] listAtms() {
		return atmCoreService.listAtms();
	}

	/**
	 * Returns the array of ATMS by city
	 *
	 * @return the array of atms
	 */
	@RequestMapping("/atm/{city}")
	@ResponseBody
	public Atm[] listAtms(@PathVariable("city") String city) {
		return atmCoreService.getAtmByCity(city);
	}

	/**
	 * Returns the array of ATMS by city
	 *
	 * @return the array of atms
	 */
	@RequestMapping("/atm/{city}/{direct}")
	@ResponseBody
	public Atm[] listAtms(@PathVariable("city") String city, @PathVariable("direct") boolean direct) {
		if (direct) {
			return atmCoreService.getAtmByCityPassthrough(city);
		} else {
			return atmCoreService.getAtmByCity(city);
		}
	}
}
