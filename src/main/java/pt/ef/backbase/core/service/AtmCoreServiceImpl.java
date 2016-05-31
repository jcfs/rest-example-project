package pt.ef.backbase.core.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpEntity;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import pt.ef.backbase.core.util.CollectionFilterUtil;
import pt.ef.backbase.core.util.Predicate;
import pt.ef.backbase.rest.api.Atm;

/**
 * @author jsalavisa
 */
public class AtmCoreServiceImpl implements AtmCoreService, InitializingBean {
	private static final Logger LOG = Logger.getLogger(AtmCoreServiceImpl.class);
	private static final String URL_CITY_SEARCH = "locatedin/";

	@Autowired
	private RestTemplate restTemplate;

	private List<Predicate<Atm, String>> predicates;
	private String baseUrl;

	@Override
	public void afterPropertiesSet() throws Exception {
		predicates = new ArrayList<>();

		// initialize the predicates list with a city name matcher predicate
		predicates.add(new Predicate<Atm, String>() {
			@Override
			public boolean validate(Atm source, String argument) {
				if (source == null) {
					return false;
				}

				return source.getAddress().getCity().equals(argument);
			}
		});
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see pt.ef.backbase.core.service.AtmCoreService#listAtms()
	 */
	@Override
	public Atm[] listAtms() {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Invoking atm core service to list atms");
		}

		HttpEntity<Atm[]> entity = restTemplate.getForEntity(baseUrl, Atm[].class);
		return entity.getBody();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see pt.ef.backbase.core.service.AtmCoreService#getAtmByCityPassthrough(java.lang.String)
	 */
	@Override
	public Atm[] getAtmByCityPassthrough(String city) {
		Assert.notNull(city);

		if (LOG.isDebugEnabled()) {
			LOG.debug("Invoking atm core service to list atms by city for city: " + city);
		}

		HttpEntity<Atm[]> entity = restTemplate.getForEntity(baseUrl + URL_CITY_SEARCH + city + "/", Atm[].class);
		return entity.getBody();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see pt.ef.backbase.core.service.AtmCoreService#getAtmByCity(java.lang.String)
	 */
	@Override
	public Atm[] getAtmByCity(final String city) {
		Assert.notNull(city);

		if (LOG.isDebugEnabled()) {
			LOG.debug("Invoking atm core service to list atms by city for city: " + city);
		}

		HttpEntity<Atm[]> entity = restTemplate.getForEntity(baseUrl, Atm[].class);
		
		Collection<Atm> filteredResult = CollectionFilterUtil.filter(Arrays.asList(entity.getBody()), city, predicates);

		return filteredResult.toArray(new Atm[filteredResult.size()]);
	}

	/**
	 * @param baseUrl
	 *            the baseUrl to set
	 */
	@Required
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

}
