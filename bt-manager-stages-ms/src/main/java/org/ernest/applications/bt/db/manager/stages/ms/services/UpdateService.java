package org.ernest.applications.bt.db.manager.stages.ms.services;

import org.ernest.applications.bt.db.manager.stages.ct.UpdateJoinStageInput;
import org.ernest.applications.bt.db.manager.stages.ct.UpdateMandatoryInformation;
import org.ernest.applications.bt.db.manager.stages.ct.UpdateUnjoinStageInput;
import org.ernest.applications.bt.db.manager.stages.ct.exceptions.RetrieveStageException;
import org.ernest.applications.bt.db.manager.stages.ct.exceptions.UpdateStageException;

public interface UpdateService {

	void updateMandatoryInformation(UpdateMandatoryInformation updateMandatoryInformation) throws RetrieveStageException, UpdateStageException;

	void joinStage(UpdateJoinStageInput updateJoinStageInput) throws RetrieveStageException, UpdateStageException;

	void unjoinStage(UpdateUnjoinStageInput updateUnjoinStageInput) throws RetrieveStageException, UpdateStageException;

}
