package pt.ef.backbase.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pt.ef.backbase.core.service.AtmCoreService;
import pt.ef.backbase.rest.api.Atm;
import pt.ef.backbase.rest.support.RestApiVersion;

/**
 * Controller that will export the rest API interface to be consumed
 *
 * @author jsalavisa
 */
@Controller
@RestApiVersion(1)
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
		Atm[] listAtms = atmCoreService.listAtms();

		// It is here just to demonstrate the diferences between the api
		// versions
		for (Atm atm : listAtms) {
			atm.getAddress().setCity(atm.getAddress().getCity().toLowerCase());
		}

		return listAtms;
	}

	/**
	 * Returns the array of ATMS by city
	 *
	 * @return the array of atms
	 */
	@RequestMapping("/atm/{city}")
	@ResponseBody
	public Atm[] listAtms(@PathVariable("city") String city) {
		Atm[] listAtms = atmCoreService.getAtmByCity(city);

		// It is here just to demonstrate the diferences between the api
		// versions
		for (Atm atm : listAtms) {
			atm.getAddress().setCity(atm.getAddress().getCity().toLowerCase());
		}

		return listAtms;
	}

	/**
	 * Returns the array of ATMS by city
	 *
	 * @return the array of atms
	 */
	@RequestMapping("/atm/{city}/{direct}")
	@ResponseBody
	public Atm[] listAtms(@PathVariable("city") String city, @PathVariable("direct") boolean direct) {
		Atm[] listAtms;

		if (direct) {
			listAtms = atmCoreService.getAtmByCityPassthrough(city);
		} else {
			listAtms = atmCoreService.getAtmByCity(city);
		}

		// It is here just to demonstrate the diferences between the api
		// versions
		for (Atm atm : listAtms) {
			atm.getAddress().setCity(atm.getAddress().getCity().toLowerCase());
		}

		return listAtms;
	}
}
