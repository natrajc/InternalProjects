package com.nttdata.timetracker.domain;

import java.util.Comparator;

public class NameTimeComparator implements Comparator<LogEntryDetails> {

	public int compare(LogEntryDetails o1, LogEntryDetails o2) {
		
		// TODO Auto-generated method stub
			int dateComparison = o1.geteId() .compareTo(o2.geteId());
	        return dateComparison == 0 ? o1.getTimeIn() .compareTo(o2.getTimeIn()) : dateComparison;
		
	}

}
