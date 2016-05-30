package pt.ef.backbase.core.service;

import pt.ef.backbase.rest.api.Atm;

/**
 * @author jsalavisa
 */
public interface AtmCoreService {

    /**
     * @return
     */
    Atm[] listAtms();

    /**
     * @param city
     * @return
     */
    Atm[] getAtmByCity(String city);

    /**
     * @param city
     * @return
     */
    Atm[] getAtmByCityPassthrough(String city);
}
