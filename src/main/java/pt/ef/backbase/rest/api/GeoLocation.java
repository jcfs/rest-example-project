/*
 * Copyright © 2016 EF - Tecnologias de Software, S.A.
 *
 * Todo o conteúdo deste ficheiro, quer na sua forma de código fonte, quer na de
 * código objecto/executável, é propriedade exclusiva da empresa EF - Tecnologias
 * de Software, S.A., não sendo permitida a sua reprodução ou utilização para
 * qualquer fim sem a explícita autorização escrita desta empresa.
 */
package pt.ef.backbase.rest.api;

/**
 * @author jsalavisa
 *
 */
public class GeoLocation {

    private String lat;
    private String lng;

    /**
     * @return the lat
     */
    public String getLat() {
        return lat;
    }

    /**
     * @param lat the lat to set
     */
    public void setLat(String lat) {
        this.lat = lat;
    }

    /**
     * @return the lng
     */
    public String getLng() {
        return lng;
    }

    /**
     * @param lng the lng to set
     */
    public void setLng(String lng) {
        this.lng = lng;
    }

}
