package org.ernest.applications.bt.db.manager.stages.ct.entities;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Stage {

	private String _id;
	private String _rev;
	
	private String name;
	private Date date;
	private int kilomitersTotal;
	private Map<Integer, Integer> altitudeByKilomiter;
	private Set<String> joinedMembers;
	
	public Stage(){
		joinedMembers = new HashSet<String>();
		altitudeByKilomiter = new HashMap<Integer, Integer>();
	}
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String get_rev() {
		return _rev;
	}
	public void set_rev(String _rev) {
		this._rev = _rev;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Set<String> getJoinedMembers() {
		return joinedMembers;
	}
	public void setJoinedMembers(Set<String> joinedMembers) {
		this.joinedMembers = joinedMembers;
	}
	public int getKilomitersTotal() {
		return kilomitersTotal;
	}
	public void setKilomitersTotal(int kilomitersTotal) {
		this.kilomitersTotal = kilomitersTotal;
	}
	public Map<Integer, Integer> getAltitudeByKilomiter() {
		return altitudeByKilomiter;
	}
	public void setAltitudeByKilomiter(Map<Integer, Integer> altitudeByKilomiter) {
		this.altitudeByKilomiter = altitudeByKilomiter;
	}	
}