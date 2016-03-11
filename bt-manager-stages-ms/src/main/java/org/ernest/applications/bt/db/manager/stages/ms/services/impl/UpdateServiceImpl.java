package org.ernest.applications.bt.db.manager.stages.ms.services.impl;

import org.ernest.applications.bt.db.manager.stages.ct.UpdateJoinStageInput;
import org.ernest.applications.bt.db.manager.stages.ct.UpdateMandatoryInformation;
import org.ernest.applications.bt.db.manager.stages.ct.UpdateUnjoinStageInput;
import org.ernest.applications.bt.db.manager.stages.ct.entities.Stage;
import org.ernest.applications.bt.db.manager.stages.ct.exceptions.RetrieveStageException;
import org.ernest.applications.bt.db.manager.stages.ct.exceptions.UpdateStageException;
import org.ernest.applications.bt.db.manager.stages.ms.services.CrudService;
import org.ernest.applications.bt.db.manager.stages.ms.services.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UpdateServiceImpl implements UpdateService {
	
	@Autowired
	CrudService crudService;

	@Override
	public void updateMandatoryInformation(UpdateMandatoryInformation updateMandatoryInformation) throws RetrieveStageException, UpdateStageException {
		Stage stage = crudService.retrieve(updateMandatoryInformation.getStageId());
		stage.setName(updateMandatoryInformation.getStageName());
		stage.setDate(updateMandatoryInformation.getStageDate());
		stage.setStagePoints(updateMandatoryInformation.getStagePoints());
		stage.setKilomitersTotal(updateMandatoryInformation.getStageKilomitersTotal());
		crudService.update(stage);
		
	}
	
	@Override
	public void joinStage(UpdateJoinStageInput updateJoinStageInput) throws RetrieveStageException, UpdateStageException {
		Stage stage = crudService.retrieve(updateJoinStageInput.getStageId());
		stage.getJoinedMembers().add(updateJoinStageInput.getMemberId());
		crudService.update(stage);
	}

	@Override
	public void unjoinStage(UpdateUnjoinStageInput updateUnjoinStageInput) throws RetrieveStageException, UpdateStageException {
		Stage stage = crudService.retrieve(updateUnjoinStageInput.getStageId());
		stage.getJoinedMembers().remove(updateUnjoinStageInput.getMemberId());
		crudService.update(stage);
	}
}