package com.nttdata.timetracker.dao;

import java.text.ParseException;
import java.util.List;

import com.nttdata.timetracker.domain.DeviceAccessDetails;
import com.nttdata.timetracker.domain.LogEntryDetails;

public interface UserDao {
/*public void saveUser ( LogEntryDetails user );
public List<LogEntryDetails> getUser();*/
public void saveLogEntry(LogEntryDetails ledtails);

public List<LogEntryDetails> getRecordsList(LogEntryDetails user);

public List<LogEntryDetails> getViewsList(LogEntryDetails user);

public List<LogEntryDetails> getExcelViewsList(LogEntryDetails user);

public void saveDeviceAccess(DeviceAccessDetails accessdetails);

public List<DeviceAccessDetails> trackEmpRcdList(DeviceAccessDetails user);
}
