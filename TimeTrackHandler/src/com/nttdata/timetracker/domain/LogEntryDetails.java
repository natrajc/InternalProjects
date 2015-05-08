package com.nttdata.timetracker.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "LOGENTRYDETAILS1")
public class LogEntryDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private Integer empId;
	
	@Column (name="eId")
	private String eId;
	
	@Column (name="eName")
	private String eName;
	
	
	@Column (name="timein")
	private Date timeIn;
	
	@Column (name="timeout")
	private Date timeOut;
	
	@Column (name="location")
	private String location;
	
	@Column (name="message")
	private String message;
	
	@Column (name="isvalid")
	private String isvalid;
	
	@Column (name="timeDiffernce")
	private float timeDiffernce;
	
	@Transient
	private double empAverage;
	
	@Transient
	private String totalCmpHours;
	
	@Transient
	private String totalCmpWorkDays;
	
	@Transient
	private String empSwipedDays;
	
	@Transient
	private String empSwipeDifferent;
	
	
	
	public float getTimeDiffernce() {
		return timeDiffernce;
	}
	public void setTimeDiffernce(float timeDiffernce) {
		this.timeDiffernce = timeDiffernce;
	}
	public String geteName() {
		return eName;
	}
	public void seteName(String eName) {
		this.eName = eName;
	}
	public String geteId() {
		return eId;
	}
	public void seteId(String eId) {
		this.eId = eId;
	}
	
	
	
	public Date getTimeIn() {
		return timeIn;
	}
	public void setTimeIn(Date timeIn) {
		this.timeIn = timeIn;
	}
	public Date getTimeOut() {
		return timeOut;
	}
	public void setTimeOut(Date timeOut) {
		this.timeOut = timeOut;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getIsvalid() {
		return isvalid;
	}
	public void setIsvalid(String isvalid) {
		this.isvalid = isvalid;
	}
	public double getEmpAverage() {
		return empAverage;
	}
	public void setEmpAverage(double empAverage) {
		this.empAverage = empAverage;
	}
	
	public String getTotalCmpHours() {
		return totalCmpHours;
	}
	public void setTotalCmpHours(String totalCmpHours) {
		this.totalCmpHours = totalCmpHours;
	}
	public String getTotalCmpWorkDays() {
		return totalCmpWorkDays;
	}
	public void setTotalCmpWorkDays(String totalCmpWorkDays) {
		this.totalCmpWorkDays = totalCmpWorkDays;
	}
	public String getEmpSwipedDays() {
		return empSwipedDays;
	}
	public void setEmpSwipedDays(String empSwipedDays) {
		this.empSwipedDays = empSwipedDays;
	}
	public String getEmpSwipeDifferent() {
		return empSwipeDifferent;
	}
	public void setEmpSwipeDifferent(String empSwipeDifferent) {
		this.empSwipeDifferent = empSwipeDifferent;
	}
	
	
	


}
