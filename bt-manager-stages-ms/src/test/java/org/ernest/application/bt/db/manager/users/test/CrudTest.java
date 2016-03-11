package org.ernest.application.bt.db.manager.users.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.ernest.applications.bt.db.manager.stages.ct.UpdateJoinStageInput;
import org.ernest.applications.bt.db.manager.stages.ct.UpdateMandatoryInformation;
import org.ernest.applications.bt.db.manager.stages.ct.UpdateUnjoinStageInput;
import org.ernest.applications.bt.db.manager.stages.ct.entities.Stage;
import org.ernest.applications.bt.db.manager.stages.ct.entities.StagePoint;
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
		
		List<StagePoint> stagePoints = new ArrayList<StagePoint>();
		stagePoints.add(new StagePoint(0.0, 20.2, 20.2, 30.2));
		stagePoints.add(new StagePoint(10.0, 20.2, 20.2, 30.2));
		stagePoints.add(new StagePoint(20.0, 20.2, 20.2, 30.2));
		stagePoints.add(new StagePoint(30.0, 20.2, 20.2, 30.2));
		stagePoints.add(new StagePoint(40.0, 20.2, 20.2, 30.2));
		stagePoints.add(new StagePoint(50.0, 20.2, 20.2, 30.2));
		stagePoints.add(new StagePoint(60.0, 20.2, 20.2, 30.2));
		stagePoints.add(new StagePoint(70.0, 20.2, 20.2, 30.2));
		stagePoints.add(new StagePoint(80.0, 20.2, 20.2, 30.2));
		stagePoints.add(new StagePoint(90.0, 20.2, 20.2, 30.2));
		stagePoints.add(new StagePoint(100.0, 20.2, 20.2, 30.2));
		updateMandatoryInformation.setStagePoints(stagePoints);
		
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