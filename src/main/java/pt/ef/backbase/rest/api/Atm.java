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
public class Atm {

    private Address address;
    private long distance;
    private String type;

    /**
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * @return the distance
     */
    public long getDistance() {
        return distance;
    }

    /**
     * @param distance the distance to set
     */
    public void setDistance(long distance) {
        this.distance = distance;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

}
