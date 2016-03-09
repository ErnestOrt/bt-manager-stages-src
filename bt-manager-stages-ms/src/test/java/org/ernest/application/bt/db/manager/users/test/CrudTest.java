package org.ernest.application.bt.db.manager.users.test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.ernest.applications.bt.db.manager.stages.ct.UpdateJoinStageInput;
import org.ernest.applications.bt.db.manager.stages.ct.UpdateMandatoryInformation;
import org.ernest.applications.bt.db.manager.stages.ct.UpdateUnjoinStageInput;
import org.ernest.applications.bt.db.manager.stages.ct.entities.Stage;
import org.ernest.applications.bt.db.manager.stages.ms.Application;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@WebIntegrationTest({"server.port=0"})
public class CrudTest {
	
	@Value("${local.server.port}")
	String port;
	
	String satgeIdCreated;
	
	@Before
	public void setUp(){
		satgeIdCreated = new RestTemplate().getForObject("http://localhost:"+port+"/create", String.class);
		Assert.assertEquals(satgeIdCreated, new RestTemplate().getForObject("http://localhost:"+port+"/retrieve/"+satgeIdCreated, Stage.class).get_id());
	}
	
	@After
	public void cleanUp(){
		new RestTemplate().getForObject("http://localhost:"+port+"/delete/"+satgeIdCreated, String.class);
		satgeIdCreated = null;
	}
	
	@Test
	public void updateMandatoryInformation(){
		UpdateMandatoryInformation updateMandatoryInformation = new UpdateMandatoryInformation();
		updateMandatoryInformation.setStageId(satgeIdCreated);
		updateMandatoryInformation.setStageName("New Name");
		updateMandatoryInformation.setStageDate(new Date());
		updateMandatoryInformation.setStageKilomitersTotal(80);
		
		Map<Double, Double> altitude = new HashMap<Double, Double>();
		altitude.put(Double.parseDouble("0"), Double.parseDouble("200"));
		altitude.put(Double.parseDouble("10"), Double.parseDouble("200"));
		altitude.put(Double.parseDouble("20"), Double.parseDouble("200"));
		altitude.put(Double.parseDouble("30"), Double.parseDouble("200"));
		altitude.put(Double.parseDouble("40"), Double.parseDouble("200"));
		altitude.put(Double.parseDouble("50"), Double.parseDouble("200"));
		altitude.put(Double.parseDouble("60"), Double.parseDouble("200"));
		altitude.put(Double.parseDouble("70"), Double.parseDouble("200"));
		altitude.put(Double.parseDouble("80"), Double.parseDouble("200"));
		altitude.put(Double.parseDouble("90"), Double.parseDouble("200"));
		altitude.put(Double.parseDouble("100"), Double.parseDouble("200"));
		updateMandatoryInformation.setStageAltitudeByKilomiter(altitude);
	
		new RestTemplate().postForObject("http://localhost:"+port+"/update/mandatoryinformation", updateMandatoryInformation, String.class);
		Assert.assertEquals(updateMandatoryInformation.getStageName(), new RestTemplate().getForObject("http://localhost:"+port+"/retrieve/"+satgeIdCreated, Stage.class).getName());
	}

	@Test
	public void updateJoinAndUnjoinStage(){
		UpdateJoinStageInput updateJoinStageInput = new UpdateJoinStageInput();
		updateJoinStageInput.setStageId(satgeIdCreated);
		updateJoinStageInput.setMemberId(UUID.randomUUID().toString());
		
		new RestTemplate().postForObject("http://localhost:"+port+"/update/joinstage", updateJoinStageInput, String.class);
		Assert.assertTrue(new RestTemplate().getForObject("http://localhost:"+port+"/retrieve/"+satgeIdCreated, Stage.class).getJoinedMembers().contains(updateJoinStageInput.getMemberId()));
		
		UpdateUnjoinStageInput updateUnjoinStageInput = new UpdateUnjoinStageInput();
		updateUnjoinStageInput.setStageId(satgeIdCreated);
		updateUnjoinStageInput.setMemberId(updateJoinStageInput.getMemberId());
		
		new RestTemplate().postForObject("http://localhost:"+port+"/update/unjoinstage", updateUnjoinStageInput, String.class);
		Assert.assertTrue(!new RestTemplate().getForObject("http://localhost:"+port+"/retrieve/"+satgeIdCreated, Stage.class).getJoinedMembers().contains(updateUnjoinStageInput.getMemberId()));
	}
}