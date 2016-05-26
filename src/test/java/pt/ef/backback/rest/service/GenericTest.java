package pt.ef.backback.rest.service;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pt.ef.backback.rest.service.GenericService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class GenericTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	private GenericService genericService;

	@Test
	public void testGetGenericMethod() {
		Assert.assertEquals(0, genericService.getGenericMethod());

	}
}
