package com.nttdata.timetracker.service;

import java.text.ParseException;
import java.util.List;

import com.nttdata.timetracker.domain.DeviceAccessDetails;
import com.nttdata.timetracker.domain.LogEntryDetails;

public interface UserService {
	
	/*public void addUser(LogEntryDetails user);

	public List<LogEntryDetails> getUser();*/

	public void LoadPDFData(List<LogEntryDetails> logEntryDetails);

	public List<LogEntryDetails> getRecordsByDateRange(LogEntryDetails user);

	public List<LogEntryDetails> getRecordsByName(LogEntryDetails user);
	
	public List<LogEntryDetails> getViewsByDateRange(LogEntryDetails user);
	
	public List<LogEntryDetails> getExcelByViewDetails(LogEntryDetails user);
	
	public void LoadDeviceAccessPDFData(List<DeviceAccessDetails> logAccessDetails);

	public List<DeviceAccessDetails> trackEmplRecords(DeviceAccessDetails user);
	
	}
