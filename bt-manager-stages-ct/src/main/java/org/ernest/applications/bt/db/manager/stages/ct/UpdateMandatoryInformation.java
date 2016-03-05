package org.ernest.applications.bt.db.manager.stages.ct;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UpdateMandatoryInformation {

	private String stageId;
	private String stageName;
	private Date stageDate;
	private int stageKilomitersTotal;
	private Map<Integer, Integer> stageAltitudeByKilomiter;
	
	public UpdateMandatoryInformation() {
		stageAltitudeByKilomiter = new HashMap<Integer, Integer>();
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
	public Map<Integer, Integer> getStageAltitudeByKilomiter() {
		return stageAltitudeByKilomiter;
	}
	public void setStageAltitudeByKilomiter(Map<Integer, Integer> stageAltitudeByKilomiter) {
		this.stageAltitudeByKilomiter = stageAltitudeByKilomiter;
	}
}