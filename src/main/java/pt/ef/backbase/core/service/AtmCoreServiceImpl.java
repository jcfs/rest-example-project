package pt.ef.backbase.core.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpEntity;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import pt.ef.backbase.rest.api.Atm;

/**
 * @author jsalavisa
 */
public class AtmCoreServiceImpl implements AtmCoreService {
    final static Logger logger = Logger.getLogger(AtmCoreServiceImpl.class);

    private static final String URL_CITY_SEARCH = "locatedin/";

    private String baseUrl;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * {@inheritDoc}
     * @see pt.ef.backbase.core.service.AtmCoreService#listAtms()
     */
    @Override
    public Atm[] listAtms() {
        logger.info("Invoking atm core service to list atms");
        HttpEntity<Atm[]> entity = restTemplate.getForEntity(baseUrl, Atm[].class);
        return entity.getBody();
    }

    /**
     * {@inheritDoc}
     * @see pt.ef.backbase.core.service.AtmCoreService#getAtmByCityPassthrough(java.lang.String)
     */
    @Override
    public Atm[] getAtmByCityPassthrough(String city) {
        Assert.notNull(city);
        logger.info("Invoking atm core service to list atms by city for city: " + city);
        HttpEntity<Atm[]> entity = restTemplate.getForEntity(baseUrl + URL_CITY_SEARCH + city + "/", Atm[].class);
        return entity.getBody();
    }

    /**
     * {@inheritDoc}
     * @see pt.ef.backbase.core.service.AtmCoreService#getAtmByCity(java.lang.String)
     */
    @Override
    public Atm[] getAtmByCity(String city) {
        Assert.notNull(city);
        logger.info("Invoking atm core service to list atms by city for city: " + city);

        HttpEntity<Atm[]> entity = restTemplate.getForEntity(baseUrl, Atm[].class);

        List<Atm> result = new ArrayList<Atm>();

        for (Atm atm : entity.getBody()) {
            if (atm.getAddress().getCity().equals(city)) {
                result.add(atm);
            }
        }

        return result.toArray(new Atm[result.size()]);
    }

    /**
     * @param baseUrl the baseUrl to set
     */
    @Required
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

}
