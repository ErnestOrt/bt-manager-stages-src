package org.ernest.applications.bt.db.manager.stages.ms.services.impl;

import java.util.UUID;

import org.ernest.applications.bt.db.manager.stages.ct.entities.Stage;
import org.ernest.applications.bt.db.manager.stages.ct.exceptions.CreateStageException;
import org.ernest.applications.bt.db.manager.stages.ct.exceptions.DeleteStageException;
import org.ernest.applications.bt.db.manager.stages.ct.exceptions.RetrieveStageException;
import org.ernest.applications.bt.db.manager.stages.ct.exceptions.UpdateStageException;
import org.ernest.applications.bt.db.manager.stages.ms.services.CrudService;
import org.lightcouch.CouchDbClient;
import org.lightcouch.CouchDbProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CrudServiceImpl implements CrudService{
	
	@Value("${db.name}")
	private String dbName;
	
	@Value("${db.host}")
	private String dbHost;

	@Override
	public String create() throws CreateStageException {
		
		Stage stage = new Stage();
		stage.set_id(UUID.randomUUID().toString());
		try{
			CouchDbClient dbClient = new CouchDbClient(buildCouchDbProperties());
			dbClient.save(stage);
			dbClient.shutdown();
		
			return stage.get_id();
			
		}catch(Exception e){
			e.printStackTrace();
			throw new CreateStageException(e.getMessage());
		}
	}

	@Override
	public Stage retrieve(String stageId) throws RetrieveStageException {
		
		try{
			CouchDbClient dbClient = new CouchDbClient(buildCouchDbProperties());
			Stage stage = dbClient.find(Stage.class, stageId);
			dbClient.shutdown();
			
			return stage;
			
		}catch(Exception e){
			e.printStackTrace();
			throw new RetrieveStageException(e.getMessage());
		}
		
	}

	@Override
	public void delete(String stageId) throws DeleteStageException {
		try{
			CouchDbClient dbClient = new CouchDbClient(buildCouchDbProperties());
			Stage stage = dbClient.find(Stage.class, stageId);
			dbClient.remove(stage);
			dbClient.shutdown();
		}catch(Exception e){
			e.printStackTrace();
			throw new DeleteStageException(e.getMessage());
		}
	}

	@Override
	public void update(Stage stage) throws UpdateStageException {
		try{
			CouchDbClient dbClient = new CouchDbClient(buildCouchDbProperties());
			dbClient.update(stage);
			dbClient.shutdown();
		}catch(Exception e){
			e.printStackTrace();
			throw new UpdateStageException(e.getMessage());
		}
	}
	
	private CouchDbProperties buildCouchDbProperties() {
		CouchDbProperties properties = new CouchDbProperties();
		properties.setDbName(dbName);
		properties.setHost(dbHost);
		properties.setPort(5984);
		properties.setCreateDbIfNotExist(true);
		properties.setProtocol("http");
		return properties;
	}

}
