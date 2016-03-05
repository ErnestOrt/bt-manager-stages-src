package org.ernest.applications.bt.db.manager.stages.ms.services;

import org.ernest.applications.bt.db.manager.stages.ct.entities.Stage;
import org.ernest.applications.bt.db.manager.stages.ct.exceptions.CreateStageException;
import org.ernest.applications.bt.db.manager.stages.ct.exceptions.DeleteStageException;
import org.ernest.applications.bt.db.manager.stages.ct.exceptions.RetrieveStageException;
import org.ernest.applications.bt.db.manager.stages.ct.exceptions.UpdateStageException;

public interface CrudService {

	String create() throws CreateStageException;
	void update(Stage stage) throws UpdateStageException;
	Stage retrieve(String stageId) throws RetrieveStageException;
	void delete(String stageId) throws DeleteStageException;
	
}
