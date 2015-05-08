package com.nttdata.timetracker.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nttdata.timetracker.dao.UserDao;
import com.nttdata.timetracker.domain.DeviceAccessDetails;
import com.nttdata.timetracker.domain.LogEntryDetails;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	public void LoadPDFData(List<LogEntryDetails> logEntryDetails) {
		
		for(LogEntryDetails ledtails: logEntryDetails){
			//System.out.println(ledtails);
			userDao.saveLogEntry(ledtails);
		}
		
	}

	public List<LogEntryDetails> getRecordsByDateRange(LogEntryDetails user) {
		// TODO Auto-generated method stub
		return userDao.getRecordsList(user);
	}

	public List<LogEntryDetails> getRecordsByName(LogEntryDetails user) {
		// TODO Auto-generated method stub
		return userDao.getRecordsList(user);
	}

	public List<LogEntryDetails> getViewsByDateRange(LogEntryDetails user) {
		// TODO Auto-generated method stub
		return userDao.getViewsList(user);
	}

	public List<LogEntryDetails> getExcelByViewDetails(LogEntryDetails user) {
		// TODO Auto-generated method stub
		return userDao.getExcelViewsList(user);
	}
	
	@Override
	public void LoadDeviceAccessPDFData(List<DeviceAccessDetails> logAccessDetails) {
		// TODO Auto-generated method stub
		for(DeviceAccessDetails accessdetails: logAccessDetails){
			userDao.saveDeviceAccess(accessdetails);
		}
	}

	@Override
	public List<DeviceAccessDetails> trackEmplRecords(DeviceAccessDetails user) {
		// TODO Auto-generated method stub
		return userDao.trackEmpRcdList(user);
	}

	
	/*@Override
	public void addUser(LogEntryDetails user) {
		userDao.saveUser(user);
	}

	@Override
	public List<LogEntryDetails> getUser() {
		return userDao.getUser();
	}*/

}
