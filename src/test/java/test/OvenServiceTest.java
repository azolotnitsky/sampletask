package test;

import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import com.oven.OvenService;
import com.oven.domain.Oven;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.ContextConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring-config.xml"})
public class OvenServiceTest extends Assert {

  @Test
  public void testOvenService() {

	OvenService ovenService = new OvenService();
	assertEquals(ovenService.registerDevice(1, 1), "<result>success</result>");
	assertEquals(ovenService.changeTemperature(1, 10), "<result>success</result>");
	assertEquals(ovenService.setMode(1, Oven.Mode.CONVECTION), "<result>success</result>");

  }

}