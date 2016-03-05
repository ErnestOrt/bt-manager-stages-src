package org.ernest.applications.bt.db.manager.stages.ms.controllers;

import org.ernest.applications.bt.db.manager.stages.ct.entities.Stage;
import org.ernest.applications.bt.db.manager.stages.ct.exceptions.CreateStageException;
import org.ernest.applications.bt.db.manager.stages.ct.exceptions.DeleteStageException;
import org.ernest.applications.bt.db.manager.stages.ct.exceptions.RetrieveStageException;
import org.ernest.applications.bt.db.manager.stages.ms.services.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CrdController {

	@Autowired
	CrudService crudService;
	
	@RequestMapping(path = "/create", method = RequestMethod.GET)
	public String create() throws CreateStageException {
		return crudService.create();
	}
	
	@RequestMapping(path = "/retrieve/{stageId}", method = RequestMethod.GET)
	public Stage retrieve(@PathVariable("stageId") String stageId) throws RetrieveStageException {
		return crudService.retrieve(stageId);
	}
	
	@RequestMapping(path = "/delete/{stageId}", method = RequestMethod.GET)
	public void delete(@PathVariable("stageId") String stageId) throws DeleteStageException {
		crudService.delete(stageId);
	}
}