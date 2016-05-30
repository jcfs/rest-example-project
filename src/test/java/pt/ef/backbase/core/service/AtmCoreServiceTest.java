package pt.ef.backbase.core.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pt.ef.backbase.rest.api.Atm;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class AtmCoreServiceTest extends AbstractJUnit4SpringContextTests {
    private static final String VALID_CITY = "LELYSTAD";
    private static final String INVALID_CITY = "XXX";

    @Autowired
    private AtmCoreService atmCoreService;

    @Test
    public void testListAtms() {
        Atm[] listAtms = atmCoreService.listAtms();
        Assert.assertTrue(listAtms != null);
        Assert.assertTrue(listAtms.length > 0);
    }

    @Test
    public void testGetAtmsByCity() {
        Atm[] listAtms = atmCoreService.getAtmByCity(VALID_CITY);
        Assert.assertTrue(listAtms != null);
        Assert.assertTrue(listAtms.length > 0);

        listAtms = atmCoreService.getAtmByCity(INVALID_CITY);
        Assert.assertTrue(listAtms != null);
        Assert.assertTrue(listAtms.length == 0);
    }

    @Test
    public void testGetAtmsByCityPassthrough() {
        Atm[] listAtms = atmCoreService.getAtmByCityPassthrough(VALID_CITY);
        Assert.assertTrue(listAtms != null);
        Assert.assertTrue(listAtms.length > 0);

        listAtms = atmCoreService.getAtmByCityPassthrough(INVALID_CITY);
        Assert.assertTrue(listAtms != null);
        Assert.assertTrue(listAtms.length == 0);
    }
}
