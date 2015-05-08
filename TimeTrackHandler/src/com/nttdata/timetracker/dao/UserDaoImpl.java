package com.nttdata.timetracker.dao;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nttdata.timetracker.domain.DeviceAccessDetails;
import com.nttdata.timetracker.domain.LogEntryDetails;

@Repository("userDao")
public class UserDaoImpl implements UserDao {
	
	private static final Logger logger = Logger.getLogger(UserDaoImpl.class);

	@Autowired
	private SessionFactory sessionfactory;

	public void saveLogEntry(LogEntryDetails ledtails) {
		sessionfactory.getCurrentSession().save(ledtails);
		
	}
	//Added By Sunita
	public void saveDeviceAccess(DeviceAccessDetails accessdetails) {
		sessionfactory.getCurrentSession().save(accessdetails);
			
	}

	public List<LogEntryDetails> getRecordsList(LogEntryDetails user) {
			
		
		
		String h1= "01/01/2015";
		String h2= "01/26/2015";
		String h3= "05/01/2015";
		String h4= "08/28/2015";
		String h5= "09/17/2015";
		String h6= "09/24/2015";
		String h7= "10/02/2015";
		String h8= "10/23/2015";
		String h9= "11/10/2015";
		String h10= "12/25/2015";
				
		String h11= "2015-01-01"; 
		String h12= "2015-01-26";
		String h13= "2015-05-01";
		String h14= "2015-08-28";
		String h15= "2015-09-17";
		String h16= "2015-09-24";
		String h17= "2015-10-02";
		String h18= "2015-10-23";
		String h19= "2015-11-10";
		String h20= "2015-12-25";
		
		
		LogEntryDetails ledTemp =null;
		//LogEntryAverageBean ledAvgTemp=null;
		DecimalFormat df = new DecimalFormat("###.##");
		List<LogEntryDetails> returnList =new ArrayList<LogEntryDetails>();
		Session session= sessionfactory.getCurrentSession();
		Criteria criteria = session.createCriteria(LogEntryDetails.class);
		ProjectionList projectionList = Projections.projectionList();
        projectionList.add(Projections.property("eId"));
        projectionList.add(Projections.property("eName"));
        projectionList.add(Projections.sum("timeDiffernce"));
        projectionList.add(Projections.groupProperty("eId"));
        criteria.setProjection(projectionList);
        /****** Added By SUnita **********************************/
        criteria.add(
      		  Restrictions.not(
      		    Restrictions.in("eId", new String[] {"1063","4396","7643","611"})
      		  ));
        /****** Ended By SUnita **********************************/
        criteria.add(Restrictions.eq("isvalid", "valid"));
        //criteria.add(Restrictions.between("timeIn", user.getTimeIn()+ " 00:00:00", user.getTimeOut()+ " 23:59:59"));
        
        Calendar c = Calendar.getInstance(); 
		c.setTime(user.getTimeOut()); 
		c.add(Calendar.DATE, 1);
		Date dateWithOneDayAdded = c.getTime();
		
        criteria.add(Restrictions.between("timeIn", user.getTimeIn(), dateWithOneDayAdded));
        if(user.geteName() !=null && user.geteName().length() !=0){
        criteria.add(Restrictions.like("eName", "%"+user.geteName()+"%"));
        }
        
        List<Object[]> list= criteria.list();
        float temp = 60.0f;
        for (Object[] result : list) {
        	ledTemp = new LogEntryDetails();
            ledTemp.seteId((String)result[0]);
            ledTemp.seteName((String)result[1]);
            Float timedifference = (Float) result[2];
            ledTemp.setTimeDiffernce(Float.parseFloat(df.format(timedifference)));
            ledTemp.setTimeIn(user.getTimeIn());
            ledTemp.setTimeOut(user.getTimeOut());
        	
           int eachEmpExactSwipedDaysCount = getEmpExactSwipedDaysCount(ledTemp);
           List<Date> eachEmpExactSwipedDaysDates = getEmpExactSwipedDays(ledTemp);
           
	   	    int empWorkingDaysCount = 0;
	   	    List<Date> empWorkingDaysDates = new ArrayList<Date>();
	   	  
           for (Date empEachWorkDate : eachEmpExactSwipedDaysDates) {
        	  
         	   String empEachSwipedDateString = empEachWorkDate.toString();
        	   Calendar cal = Calendar.getInstance();
               cal.setTime(empEachWorkDate);
               
               if( cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY
                   && cal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY
            	   && !empEachSwipedDateString.equals(h11) 
            	   && !empEachSwipedDateString.equals(h12)
		           && !empEachSwipedDateString.equals(h13)
		           && !empEachSwipedDateString.equals(h14)
		           && !empEachSwipedDateString.equals(h15)
		           && !empEachSwipedDateString.equals(h16)
		           && !empEachSwipedDateString.equals(h17)
		           && !empEachSwipedDateString.equals(h18)
		           && !empEachSwipedDateString.equals(h19)
		           && !empEachSwipedDateString.equals(h20) 
            	   )
               {
            	   empWorkingDaysCount++;
            	   empWorkingDaysDates.add(empEachWorkDate);
               }
           }
            
            try {
            	DateFormat df1 = new SimpleDateFormat("MM/dd/yyyy");
        	    
            	Date date1 = user.getTimeIn();
        	    Date date2 = user.getTimeOut();
        	    
        	    Calendar modifiedCal_date2 = Calendar.getInstance();  
        	    modifiedCal_date2.setTime(date2);    
        	    modifiedCal_date2.add( Calendar.DATE, 1 );
        	    String date2_final =   df1.format(modifiedCal_date2.getTime());
        	    Date date2_final_string = df1.parse(date2_final);
        	    
        	    //Calculate Company working days count and those dates
                Calendar cal1 = Calendar.getInstance();
        	    Calendar cal2 = Calendar.getInstance();
        	    cal1.setTime(date1);
        	    cal2.setTime(date2_final_string);
        	    
                int companyWorkingDaysCount = 0;
                List<Date> companyWorkingDaysDates = new ArrayList<Date>();
                while (cal1.before(cal2)) {
                	Date d = cal1.getTime();
					String dateString = df1.format(d).toString();
        	        if ((Calendar.SATURDAY != cal1.get(Calendar.DAY_OF_WEEK))
        	           &&(Calendar.SUNDAY != cal1.get(Calendar.DAY_OF_WEEK)
        	        		   && !dateString.equals(h1) 
					              && !dateString.equals(h2)
					              && !dateString.equals(h3)
					              && !dateString.equals(h4)
					              && !dateString.equals(h5)
					              && !dateString.equals(h6)
					              && !dateString.equals(h7)
					              && !dateString.equals(h8)
					              && !dateString.equals(h9)
					              && !dateString.equals(h10)
        	        		   
        	        		   )) {
        	        	companyWorkingDaysCount++;
        	        	companyWorkingDaysDates.add(cal1.getTime());
        	        	cal1.add(Calendar.DATE,1);
        	        }else {
        	        	cal1.add(Calendar.DATE,1);
        	        }
        	    }
                
                int totalCompanyHours = companyWorkingDaysCount * 8;
                ledTemp.setTotalCmpWorkDays(String.valueOf(companyWorkingDaysCount));
                ledTemp.setTotalCmpHours(String.valueOf(totalCompanyHours));
                
                int empSwipDiffWithCmpWorkDays = eachEmpExactSwipedDaysCount - companyWorkingDaysCount;
                System.out.println("empSwipDiffWithCmpWorkDays: "+ empSwipDiffWithCmpWorkDays);
                ledTemp.setEmpSwipeDifferent(String.valueOf(empSwipDiffWithCmpWorkDays));
 
                int empLeaveDaysCount_PTO = companyWorkingDaysCount - empWorkingDaysCount;
                
                
        	    if (empSwipDiffWithCmpWorkDays >= 0 && companyWorkingDaysCount != 0) {
        	    	double finalAvg = (timedifference / companyWorkingDaysCount);
                    ledTemp.setEmpAverage(finalAvg);
				} else if(companyWorkingDaysCount == 0){
                    ledTemp.setEmpAverage(timedifference);
				}
				else if(empSwipDiffWithCmpWorkDays < 0){
					double finalAvg = (timedifference / (companyWorkingDaysCount - empLeaveDaysCount_PTO));
                    ledTemp.setEmpAverage(finalAvg);
				}
            	
			} catch (Exception e) {
				System.out.println("Something went wrong"+e);
			}
            returnList.add(ledTemp);
        }
		System.out.println(list.size());
        return returnList;
	}
	
	public List<LogEntryDetails> getRecordsListBackup(LogEntryDetails user) {
		
		LogEntryDetails ledTemp =null;
		DecimalFormat df = new DecimalFormat("###.##");
		List<LogEntryDetails> returnList =new ArrayList<LogEntryDetails>();
		Session session= sessionfactory.getCurrentSession();
		Criteria criteria = session.createCriteria(LogEntryDetails.class);
		ProjectionList projectionList = Projections.projectionList();
        projectionList.add(Projections.property("eId"));
        projectionList.add(Projections.property("eName"));
        projectionList.add(Projections.sum("timeDiffernce"));
        projectionList.add(Projections.groupProperty("eId"));
        criteria.setProjection(projectionList);
        /****** Added By SUnita **********************************/
        criteria.add(
      		  Restrictions.not(
      		    Restrictions.in("eId", new String[] {"1063","4396","7643","611","1983","7066","3007"})
      		  ));
        /****** Ended By SUnita **********************************/
        criteria.add(Restrictions.eq("isvalid", "valid"));
        //criteria.add(Restrictions.between("timeIn", user.getTimeIn()+ " 00:00:00", user.getTimeOut()+ " 23:59:59"));
        
        Calendar c = Calendar.getInstance(); 
		c.setTime(user.getTimeOut()); 
		c.add(Calendar.DATE, 1);
		Date dateWithOneDayAdded = c.getTime();
		
        criteria.add(Restrictions.between("timeIn", user.getTimeIn(), dateWithOneDayAdded));
        if(user.geteName() !=null && user.geteName().length() !=0){
        criteria.add(Restrictions.like("eName", "%"+user.geteName()+"%"));
        }
        
        List<Object[]> list= criteria.list();
        float temp = 60.0f;
        for (Object[] result : list) {
        	ledTemp = new LogEntryDetails();
            ledTemp.seteId((String)result[0]);
            ledTemp.seteName((String)result[1]);
            Float timedifference = (Float) result[2];
            ledTemp.setTimeDiffernce(Float.parseFloat(df.format(timedifference)));
            ledTemp.setTimeIn(user.getTimeIn());
            ledTemp.setTimeOut(user.getTimeOut());
        	
           int eachEmpExactSwipedDaysCount = getEmpExactSwipedDaysCount(ledTemp);
           
            try {
            	String h1= "01/01/2015";
        		String h2= "01/26/2015";
        		String h3= "05/01/2015";
        		String h4= "08/28/2015";
        		String h5= "09/17/2015";
        		String h6= "09/24/2015";
        		String h7= "10/02/2015";
        		String h8= "10/23/2015";
        		String h9= "11/10/2015";
        		String h10= "12/25/2015";
        		
            	DateFormat df1 = new SimpleDateFormat("MM/dd/yyyy");
        	    
            	Date date1 = user.getTimeIn();
        	    Date date2 = user.getTimeOut();
        	    
        	    Calendar modifiedCal_date2 = Calendar.getInstance();  
        	    modifiedCal_date2.setTime(date2);    
        	    modifiedCal_date2.add( Calendar.DATE, 1 );
        	    String date2_final =   df1.format(modifiedCal_date2.getTime());
        	    //System.out.println("After added 1 in in date2: "+date2_final);
        	    
        	    Date date2_final_string = df1.parse(date2_final);
 
        	    Calendar cal1 = Calendar.getInstance();
        	    Calendar cal2 = Calendar.getInstance();
 
        	    cal1.setTime(date1);
        	    cal2.setTime(date2_final_string);
        	    
        	    int empSwipDaysToFindAvg = 0;
        	    System.out.println(cal1.before(cal2));
				while (cal1.before(cal2)) 
				{
					Date d = cal1.getTime();
					String dateString = df1.format(d).toString();
					
					if (		  Calendar.SATURDAY != cal1.get(Calendar.DAY_OF_WEEK)
								  && Calendar.SUNDAY != cal1.get(Calendar.DAY_OF_WEEK)
								  && !dateString.equals(h1) 
					              && !dateString.equals(h2)
					              && !dateString.equals(h3)
					              && !dateString.equals(h4)
					              && !dateString.equals(h5)
					              && !dateString.equals(h6)
					              && !dateString.equals(h7)
					              && !dateString.equals(h8)
					              && !dateString.equals(h9)
					              && !dateString.equals(h10)
					              //have to add PTO condn
							) {
						
						empSwipDaysToFindAvg++;
						cal1.add(Calendar.DATE, 1);
					} else {
						cal1.add(Calendar.DATE, 1);
					}
				}
        	    
        	    if (empSwipDaysToFindAvg != 0) {
        	    	double finalAvg = (timedifference / empSwipDaysToFindAvg);
                    ledTemp.setEmpAverage(finalAvg);
				} else {
					ledTemp.setEmpAverage(timedifference);
				}
                
                //Calculate Company working days
                Calendar cal3 = Calendar.getInstance();
        	    Calendar cal4 = Calendar.getInstance();
        	    cal3.setTime(date1);
        	    cal4.setTime(date2_final_string);
                int companyWorkingDays=0;
                while (cal3.before(cal4)) {
                	
                	Date d = cal3.getTime();
					String dateString = df1.format(d).toString();
					
        	        if ((Calendar.SATURDAY != cal3.get(Calendar.DAY_OF_WEEK))
        	           &&(Calendar.SUNDAY != cal3.get(Calendar.DAY_OF_WEEK)
        	        		   && !dateString.equals(h1) 
					              && !dateString.equals(h2)
					              && !dateString.equals(h3)
					              && !dateString.equals(h4)
					              && !dateString.equals(h5)
					              && !dateString.equals(h6)
					              && !dateString.equals(h7)
					              && !dateString.equals(h8)
					              && !dateString.equals(h9)
					              && !dateString.equals(h10)
        	        		   
        	        		   )) {
        	        	companyWorkingDays++;
                	cal3.add(Calendar.DATE,1);
        	        }else {
        	        	cal3.add(Calendar.DATE,1);
        	        }
        	    }
                
                int totalCompanyHours = companyWorkingDays * 8;
                ledTemp.setTotalCmpHours(String.valueOf(totalCompanyHours));
                ledTemp.setTotalCmpWorkDays(String.valueOf(companyWorkingDays));
   
                int empSwipDiffWithCmpWorkDays = eachEmpExactSwipedDaysCount - companyWorkingDays;
                //System.out.println("Emp non Swiped Days: "+ empSwipDiffWithCmpWorkDays);
                ledTemp.setEmpSwipeDifferent(String.valueOf(empSwipDiffWithCmpWorkDays));
            	
			} catch (Exception e) {
				System.out.println("Something went wrong"+e);
			}
            returnList.add(ledTemp);
        }
		System.out.println(list.size());
        return returnList;
	}
	
	private List<Date> getEmpExactSwipedDays(LogEntryDetails user) {


		List<Date> empSwipedDays = new ArrayList<Date>();
		Session session= sessionfactory.getCurrentSession();		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Date date1 = user.getTimeIn();
		Date date2Modified = null;
		String eId = user.geteId();
		
		Calendar calendar = Calendar.getInstance(); 
		calendar.setTime(user.getTimeOut()); 
		
		calendar.add(Calendar.HOUR, 23);
		calendar.add(Calendar.MINUTE, 59);
		calendar.add(Calendar.SECOND, 59);
		date2Modified = calendar.getTime();
		
		String date1_yyyymmddFormat = dateFormat.format(date1);
		String date2_yyyymmddFormat = dateFormat.format(date2Modified);
		
		String sql = "SELECT distinct DATE(timeIn) as swipedDays FROM LOGENTRYDETAILS1  where eId="+eId+" and  timeIn BETWEEN '"+date1_yyyymmddFormat+"' AND '"+date2_yyyymmddFormat+"' ";
		 
		//System.out.println("The native SQL Query: "+ sql);
		 
		SQLQuery query = session.createSQLQuery(sql);
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		List results = query.list();
		
		for(Object object : results)
         {
            Map row = (Map)object;
            Date empSwipeDate = (Date) row.get("swipedDays");
            empSwipedDays.add(empSwipeDate);
            
         }
        return empSwipedDays;
		
	
	}

	int getEmpExactSwipedDaysCount(LogEntryDetails user) {

		int empSwipedDays = 0;
		DecimalFormat df = new DecimalFormat("###.##");
		Session session= sessionfactory.getCurrentSession();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Date date1 = user.getTimeIn();
		Date date2Modified = null;
		String eId = user.geteId();
		
		Calendar calendar = Calendar.getInstance(); 
		calendar.setTime(user.getTimeOut()); 
		
		calendar.add(Calendar.HOUR, 23);
		calendar.add(Calendar.MINUTE, 59);
		calendar.add(Calendar.SECOND, 59);
		date2Modified = calendar.getTime();
		
		String date1_yyyymmddFormat = dateFormat.format(date1);
		String date2_yyyymmddFormat = dateFormat.format(date2Modified);
		
		String sql = "SELECT count(distinct DATE_FORMAT(timeIn, '%y-%m-%d' )) as timeInCount FROM LOGENTRYDETAILS1  where eId="+eId+" and  timeIn BETWEEN '"+date1_yyyymmddFormat+"' AND '"+date2_yyyymmddFormat+"' ";
		 
		System.out.println("The native SQL Query: "+ sql);
		 
		SQLQuery query = session.createSQLQuery(sql);
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		List results = query.list();
		
		for(Object object : results)
         {
            Map row = (Map)object;
            BigInteger bigInt = (BigInteger) row.get("timeInCount");
            empSwipedDays = bigInt.intValue();
            
         }
		 
        return empSwipedDays;
		
	}
	
	public List<LogEntryDetails> getViewsList(LogEntryDetails user) {
		
		LogEntryDetails logDetails =null;
		DecimalFormat df = new DecimalFormat("###.##");
		List<LogEntryDetails> returnViewList =new ArrayList<LogEntryDetails>();
		Session session = sessionfactory.getCurrentSession();

		Date timeOut = user.getTimeOut();
		Date timeOutModified = null;

		Calendar cal = Calendar.getInstance();
		cal.setTime(timeOut);
		cal.add(Calendar.HOUR, 23);
		cal.add(Calendar.MINUTE, 59);
		cal.add(Calendar.SECOND, 59);
		timeOutModified = cal.getTime();
			 
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dateTimeIn = user.getTimeIn();
		Date dateTimeOut = timeOutModified;
		
		String dateTimeIn_yyyymmdd_Format = dateFormat.format(dateTimeIn);
		String dateTimeOut_yyymmdd_Format = dateFormat.format(dateTimeOut);
		
		String sqlQueryForEachUser = "";
		sqlQueryForEachUser = "SELECT eId, eName, location, timeIn, timeOut, message, sum(timeDiffernce) as timeDiffernce"
					+ " FROM"
					+ " LOGENTRYDETAILS1"
					+ " WHERE "
					+ " isvalid='valid'"
					+ " AND eId="+user.geteId()
					+ " AND timeIn BETWEEN "+  "'"+dateTimeIn_yyyymmdd_Format+"'" +" AND "+ "'"+dateTimeOut_yyymmdd_Format+ "'"
					+ " GROUP BY DATE_FORMAT(timeIn, '%y-%m-%d')";
					
		/*if (user.geteName() != null && user.geteName().length() != 0) {
			String eNameCondition = "AND eName LIKE %"+ user.geteName() + "%";
			sqlQueryForEachUser = sqlQueryForEachUser + eNameCondition;
			
			System.out.println("final query if user name available: "+ sqlQueryForEachUser);
			
		}*/
		
		//System.out.println("Each user native sql query with Group by timeIN: "+ sqlQueryForEachUser);
		SQLQuery query = session.createSQLQuery(sqlQueryForEachUser);
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        
		List list = query.list();
        //System.out.println("list of records size: "+ list.size());
        
        float temp = 60.0f;
        for (Object eachRecord : list) {
        	Map eachRowValues = (Map)eachRecord;
        	
        	logDetails = new LogEntryDetails();
        	logDetails.seteId((String)eachRowValues.get("eId"));
        	logDetails.seteName((String)eachRowValues.get("eName"));
        	logDetails.setLocation((String)eachRowValues.get("location"));
        	logDetails.setTimeIn((Date) eachRowValues.get("timeIn"));
        	logDetails.setTimeOut((Date)eachRowValues.get("timeOut"));
        	logDetails.setMessage((String)eachRowValues.get("message"));
        	
        	Double eachDayTimeDiffTotal = (Double)eachRowValues.get("timeDiffernce");
        	logDetails.setTimeDiffernce(eachDayTimeDiffTotal.floatValue());
        	
            returnViewList.add(logDetails);
        }
        return returnViewList;
}
	
	public List<LogEntryDetails> getExcelViewsList(LogEntryDetails user) {
		LogEntryDetails logtempDetails =null;
		DecimalFormat df = new DecimalFormat("###.##");
		List<LogEntryDetails> returnExcelList =new ArrayList<LogEntryDetails>();
		Session session= sessionfactory.getCurrentSession();
		Criteria criteria = session.createCriteria(LogEntryDetails.class);
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.property("eId"));
        projectionList.add(Projections.property("eName"));
       // projectionList.add(Projections.property("location"));
        projectionList.add(Projections.property("timeIn"));
        projectionList.add(Projections.property("timeOut"));
        projectionList.add(Projections.property("message"));
        criteria.setProjection(projectionList);
        criteria.add(Restrictions.eq("eId", user.geteId()));

        List<Object[]> listtemp= criteria.list();
            for (Object[] result : listtemp) {
        	logtempDetails = new LogEntryDetails();
        	logtempDetails.seteId((String)result[0]);
        	logtempDetails.seteName((String)result[1]);
    	//logtempDetails.setLocation((String)result[2]);
        	logtempDetails.setTimeIn((Date)result[2]);
        	logtempDetails.setTimeOut((Date)result[3]);
        	logtempDetails.setMessage((String)result[4]);
    	returnExcelList.add(logtempDetails);
        }
        return returnExcelList;
	}


	public static List<Date> datesBetween(Date d1, Date d2) {
	    List<Date> ret = new ArrayList<Date>();
	    Calendar c = Calendar.getInstance();
	    c.setTime(d1);
	    while (c.getTimeInMillis()<d2.getTime()) {
	        c.add(Calendar.MONTH, 1);
	        ret.add(c.getTime());
	    }
	    return ret;
	}

	@Override
	public List<DeviceAccessDetails> trackEmpRcdList(DeviceAccessDetails user) {
		DeviceAccessDetails	tckEmpRecords=null;
		List<DeviceAccessDetails> returnEmpRec = new ArrayList<DeviceAccessDetails>();
		Session session = sessionfactory.getCurrentSession();
		String year=user.getYear();
		String month=user.getMonth();
		String query="";

		if(year.matches("2015") && month.matches("01"))
		{
			//jan pdf don't have right now .. so records are same for dec nd jan according to pdf
		
		query="SELECT distinct(cardNumber),batchHolderName,clearanceCode,pdf_date FROM month_emplist WHERE year(pdf_date) = '"+year+"' and month(pdf_date) ='"+month+"' ";
		SQLQuery qry=session.createSQLQuery(query);
		qry.setResultTransformer(Criteria.PROJECTION);	
		List<Object[]> list = qry.list();
		int count=0;
		count=list.size();
		System.out.println(count);
        System.out.println("list of records size: "+ list.size());
        for(Object[] tckRcd : list)
        {
        	tckEmpRecords = new DeviceAccessDetails();
        	tckEmpRecords.setCardNumber((String)tckRcd[0]);
        	tckEmpRecords.setBatchHolderName((String)tckRcd[1]);
        	tckEmpRecords.setClearanceCode((String)tckRcd[2]);
        	//tckEmpRecords.setPdf_date((String)tckRcd[3]);
        	tckEmpRecords.setYear(year);
        	tckEmpRecords.setMonth(month);
        	returnEmpRec.add(tckEmpRecords);
        	
        }
		
		}
		
		//incompleted codes
	/*	else if(year.matches("2015") && month.matches("02"))
		{
			String query2="";
			String year1=query.valueOf(year);
			String month1=query.valueOf(month);
			System.out.println("year1: "+year1);
			System.out.println("month1: "+month1);
			query2="select distinct(cardNumber), batchHolderName,clearanceCode,pdf_date from month_emplist where year(pdf_date)='"+year+"' and month(pdf_date)='"+month+"' and cardNumber not in (select distinct(cardNumber) from month_emplist where year(pdf_date)='2015' and month(pdf_date)='01') ";
			System.out.println("SQL QUERY For EXISTING Employees :"+query2);
			SQLQuery qry1=session.createSQLQuery(query2);
			qry1.setResultTransformer(Criteria.PROJECTION);	
			List<Object[]> list = qry1.list();
			int count=0;
			count=list.size();
			System.out.println(count);
	        System.out.println("list of records size: "+ list.size());
	        for(Object[] tckRcd : list)
	        {
	        	tckEmpRecords = new DeviceAccessDetails();
	        	tckEmpRecords.setCardNumber((String)tckRcd[0]);
	        	tckEmpRecords.setBatchHolderName((String)tckRcd[1]);
	        	tckEmpRecords.setClearanceCode((String)tckRcd[2]);
	        	//tckEmpRecords.setPdf_date(Date.parse(pdf_date));
	        	tckEmpRecords.setYear(year);
	        	tckEmpRecords.setMonth(month);
	        	tckEmpRecords.setCardNumber((String)tckRcd[3]);
	        	returnEmpRec.add(tckEmpRecords);
	        	
	        }
		}
		else if(year.matches("2015") && month.matches("03"))
		{
			//don't hv pdf for march.
			query="select distinct(cardNumber),batchHolderName,clearanceCode,pdf_date from month_emplist where year(pdf_date)='2015' and month(pdf_date)='02' and cardNumber in (select distinct(cardNumber),batchHolderName,clearanceCode,pdf_date from month_emplist where year(pdf_date)='2015' and month(pdf_date)='03')";
			System.out.println("SQL QUERY For EXISTING Employees :"+query);
		}
		else if(year.matches("2015") && month.matches("04"))
		{
			query="select distinct(cardNumber),batchHolderName,clearanceCode,pdf_date from month_emplist where year(pdf_date)='2015' and month(pdf_date)='02' and cardNumber in (select distinct(cardNumber),batchHolderName,clearanceCode,pdf_date from month_emplist where year(pdf_date)='2015' and month(pdf_date)='04')";
			System.out.println("SQL QUERY For EXISTING Employees :"+query);
		}*/
		

		return returnEmpRec;
	}
		

	
}
