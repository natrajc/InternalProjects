package com.nttdata.timetracker.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "month_emplist")

public class DeviceAccessDetails implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private Integer Id;
	
	@Column (name="batchHolderName")
	private String batchHolderName;
	
	@Column (name="timeZone")
	private String timeZone;
	
	@Column (name="clearanceCode")
	private String clearanceCode;
	
	@Column (name="cardNumber")
	private String cardNumber;
	
	@Column (name="pdf_date")
	private String pdf_date;
	
	@Transient
	private String from_date;
	
	@Transient
	private String to_date;
	
	@Transient
	private String year;
	@Transient
	private String month;
	
	
	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getBatchHolderName() {
		return batchHolderName;
	}

	public void setBatchHolderName(String batchHolderName) {
		this.batchHolderName = batchHolderName;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public String getClearanceCode() {
		return clearanceCode;
	}

	public void setClearanceCode(String clearanceCode) {
		this.clearanceCode = clearanceCode;
	}

	public String getPdf_date() {
		return pdf_date;
	}

	public void setPdf_date(String pdf_date) {
		this.pdf_date = pdf_date;
	}

	public String getFrom_date() {
		return from_date;
	}

	public void setFrom_date(String from_date) {
		this.from_date = from_date;
	}

	public String getTo_date() {
		return to_date;
	}

	public void setTo_date(String to_date) {
		this.to_date = to_date;
	}

	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	

}
