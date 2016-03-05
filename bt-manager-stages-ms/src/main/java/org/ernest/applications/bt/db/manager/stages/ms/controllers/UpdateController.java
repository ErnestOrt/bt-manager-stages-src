package org.ernest.applications.bt.db.manager.stages.ms.controllers;

import org.ernest.applications.bt.db.manager.stages.ct.UpdateJoinStageInput;
import org.ernest.applications.bt.db.manager.stages.ct.UpdateMandatoryInformation;
import org.ernest.applications.bt.db.manager.stages.ct.UpdateUnjoinStageInput;
import org.ernest.applications.bt.db.manager.stages.ct.exceptions.RetrieveStageException;
import org.ernest.applications.bt.db.manager.stages.ct.exceptions.UpdateStageException;
import org.ernest.applications.bt.db.manager.stages.ms.services.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpdateController {
	
	@Autowired
	UpdateService updateService;
	
	@RequestMapping("/update/mandatoryinformation")
	public void updateMandatoryInformation(@RequestBody UpdateMandatoryInformation updateMandatoryInformation) throws UpdateStageException, RetrieveStageException {
		updateService.updateMandatoryInformation(updateMandatoryInformation);
	}
	
	@RequestMapping("/update/joinstage")
	public void joinStage(@RequestBody UpdateJoinStageInput updateJoinStageInput) throws UpdateStageException, RetrieveStageException {
		updateService.joinStage(updateJoinStageInput);
	}
	
	@RequestMapping("/update/unjoinstage")
	public void unjoinStage(@RequestBody UpdateUnjoinStageInput updateUnjoinStageInput) throws UpdateStageException, RetrieveStageException {
		updateService.unjoinStage(updateUnjoinStageInput);
	}
}