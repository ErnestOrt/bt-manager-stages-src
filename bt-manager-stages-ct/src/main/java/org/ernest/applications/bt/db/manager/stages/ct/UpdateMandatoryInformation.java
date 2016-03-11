package org.ernest.applications.bt.db.manager.stages.ct;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.ernest.applications.bt.db.manager.stages.ct.entities.StagePoint;

public class UpdateMandatoryInformation {

	private String stageId;
	private String stageName;
	private Date stageDate;
	private int stageKilomitersTotal;
	private List<StagePoint> stagePoints;
	
	public UpdateMandatoryInformation() {
		stagePoints = new ArrayList<StagePoint>();
	}
	public String getStageId() {
		return stageId;
	}
	public void setStageId(String stageId) {
		this.stageId = stageId;
	}
	public String getStageName() {
		return stageName;
	}
	public void setStageName(String stageName) {
		this.stageName = stageName;
	}
	public Date getStageDate() {
		return stageDate;
	}
	public void setStageDate(Date stageDate) {
		this.stageDate = stageDate;
	}
	public int getStageKilomitersTotal() {
		return stageKilomitersTotal;
	}
	public void setStageKilomitersTotal(int stageKilomitersTotal) {
		this.stageKilomitersTotal = stageKilomitersTotal;
	}
	public List<StagePoint> getStagePoints() {
		return stagePoints;
	}
	public void setStagePoints(List<StagePoint> stagePoints) {
		this.stagePoints = stagePoints;
	}

}