package com.nttdata.timetracker.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.pdfbox.pdfparser.PDFParser;
import org.pdfbox.pdmodel.PDDocument;
import org.pdfbox.util.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.itextpdf.text.log.SysoLogger;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.nttdata.timetracker.domain.DeviceAccessDetails;
import com.nttdata.timetracker.domain.LogEntryDetails;
import com.nttdata.timetracker.domain.NameTimeComparator;
import com.nttdata.timetracker.service.UserService;

/**
 * @author 095481
 * Log4j Added By Sunita
 *
 */
@Controller
public class HomeController {
	
	private static final Logger logger = Logger.getLogger(HomeController.class);

	@Autowired
	private UserService userService;

	@RequestMapping("/loadPDF")
	public ModelAndView getLoadPDFForm(
			@ModelAttribute("user") LogEntryDetails user, BindingResult result) {
		  //log it via log4j
		  if(logger.isDebugEnabled()){
		   logger.debug("Start debug");
		  }
		  logger.info("Going to run HomeController-getLoadPDFForm method");
		  logger.info("Exiting the program"); 
		return new ModelAndView("loadPDF");
	}
	
	@RequestMapping("/bynamereport")
	public ModelAndView getByNameReportForm(
			@ModelAttribute("user") LogEntryDetails user, BindingResult result) {
		
		  //log it via log4j
		  if(logger.isDebugEnabled()){
		   logger.debug("Start debug");
		  }
		  logger.info("Going to run HomeController-getByNameReportForm method");

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("user", null);
		logger.info("Exiting the program"); 
		return new ModelAndView("bynamereport",model);
	}
	
	@RequestMapping("/bydaterangereport")
	public ModelAndView getByDateangeReportForm(
			@ModelAttribute("user") LogEntryDetails user, BindingResult result) {
		if(logger.isDebugEnabled()){
			   logger.debug("Start debug");
			  }
	    logger.info("Going to run HomeController-getByDateangeReportForm method");

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("user", null);
		logger.info("Exiting the program"); 
		return new ModelAndView("bydaterangereport",model);
	}
	
	//Added Sunita
	@RequestMapping("/trackEmployee")
	public ModelAndView trackEmp(
			@ModelAttribute("user") DeviceAccessDetails user, BindingResult result,HttpServletRequest req) {
		if(logger.isDebugEnabled()){
			   logger.debug("Start debug");
			  }
	    logger.info("Going to run HomeController-trackEmp method");
		Map<String, Object> model = new HashMap<String, Object>();
		 Map<String,String> year = new LinkedHashMap<String,String>();
		    year.put("2014", "2014");
		    year.put("2015", "2015");
		    model.put("yearList", year);
		     
		    Map<String,String> month = new LinkedHashMap<String,String>();
		    month.put("01", "JANUARY");
		    month.put("02", "FEBRUARY");
		    month.put("03", "MARCH");
		    month.put("04", "APRIL");
		    month.put("05", "MAY");
		    month.put("06", "JUNE");
		    month.put("07", "JULY");
		    month.put("08", "AUGUST");
		    month.put("09", "SEPTEMBER");
		    month.put("10", "OCTOBER");
		    month.put("11", "NOVEMBER");
		    month.put("12", "DECEMBER");
		    model.put("monthList", month);
		    
			model.put("user", model);
		    		
		logger.info("Exiting the program"); 
		return new ModelAndView("trackEmployee",model);
	}
	
	
	@RequestMapping(value="/trackEmpbycalendar", method = RequestMethod.POST)
	public ModelAndView onSubmit(
			@ModelAttribute("user") DeviceAccessDetails user, BindingResult result,HttpServletRequest req) throws ParseException {
		if(logger.isDebugEnabled()){
			   logger.debug("Start debug");
			  }
	    logger.info("Going to run HomeController-getByDateangeReport method");
	    
	    String year=req.getParameter("year");
	    String month=req.getParameter("month");	    
	    user.setYear(year);
	    user.setMonth(month);	      
	    List<DeviceAccessDetails>  trackEmp =  userService.trackEmplRecords(user);
		
	    Map<String, Object> model = new HashMap<String, Object>();
	    model.put("year", user.getYear());
	    model.put("month", user.getMonth());
	    model.put("count", trackEmp.size());
		model.put("user", trackEmp);
	    
		logger.info("Exiting the program"); 
		return new ModelAndView("trackEmpbycalendar",model);
	    
	}
	//Ended Sunita
	
	//Modified By Suresh
	@RequestMapping("/recordsbydaterange")
	public ModelAndView getByDateangeReport(
			@ModelAttribute("user") LogEntryDetails user, BindingResult result) {
		if(logger.isDebugEnabled()){
			   logger.debug("Start debug");
			  }
	    logger.info("Going to run HomeController-getByDateangeReport method");
		
		List<LogEntryDetails>  dateRangeRecords =  userService.getRecordsByDateRange(user);
		Map<String, Object> model = new HashMap<String, Object>();
				
		model.put("user", dateRangeRecords);
		
		if (dateRangeRecords.size() > 0) {
			model.put("totalCompanyHours", dateRangeRecords.get(0).getTotalCmpHours());
			model.put("totalCmpWorkDays", dateRangeRecords.get(0).getTotalCmpWorkDays());
		}
		
		logger.info("Exiting the program"); 
		return new ModelAndView("bydaterangereport",model);
	}
	//Ended 
	
	@RequestMapping("/recordsbyname")
	public ModelAndView getByName(
			@ModelAttribute("user") LogEntryDetails user, BindingResult result) {
		if(logger.isDebugEnabled()){
			   logger.debug("Start debug");
			  }
	    logger.info("Going to run HomeController-getByName method");
		
		userService.getRecordsByName(user);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("user", userService.getRecordsByDateRange(user));
		logger.info("Exiting the program"); 
		return new ModelAndView("bynamereport",model);
	}

	@RequestMapping("/PDFdataread")
	public ModelAndView readPDFData(@RequestParam("pdffile") MultipartFile multipartFile) {

		String name = "Logentry";

		File convFile = new File( multipartFile.getOriginalFilename());
		try {
			multipartFile.transferTo(convFile);
		} catch (IllegalStateException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if (convFile.exists()){ //!multipartFile.isEmpty()) { //nat
			try {
				//byte[] pdfByteArray = multipartFile.getBytes(); //nat

				List<LogEntryDetails> logEntryDetails = readPdf(convFile); //readPdf(pdfByteArray); //nat
				userService.LoadPDFData(logEntryDetails);
				return new ModelAndView("loadPDF");
			} catch (Exception e) {
				return new ModelAndView("loadPDF");
			}
		} else {
			return new ModelAndView("loadPDF");
		}

	}
	
	//Added By Sunita 	
	@RequestMapping(value="/bydateexportExcel",method=RequestMethod.GET)
	public ModelAndView getExcelReport(
			@RequestParam("timein") String timeIn,@RequestParam("timeout") String timeOut) {
		if(logger.isDebugEnabled()){
			   logger.debug("Start debug");
			  }
	    logger.info("Going to run HomeController-getExcelReport method");
		LogEntryDetails user = new LogEntryDetails();
		user.setTimeIn(new Date(timeIn));
		user.setTimeOut(new Date(timeOut));				
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("user", userService.getRecordsByDateRange(user));
		logger.info("Exiting the program"); 
		return new ModelAndView("bydateexportExcel",model);
	}
	
	@RequestMapping(value="/bydateRangeview",method=RequestMethod.GET)
	public ModelAndView getViewDetails(HttpServletRequest req) {
		if(logger.isDebugEnabled()){
			   logger.debug("Start debug");
			  }
	    logger.info("Going to run HomeController-getViewDetails method");
	    LogEntryDetails user = new LogEntryDetails();
		String eId=req.getParameter("eId");
		String timein=req.getParameter("timein");
		String timeout=req.getParameter("timeout");
		user.seteId(eId);		
		user.setTimeIn(new Date(timein));
		user.setTimeOut(new Date(timeout));		
		ModelAndView model=new ModelAndView();		
		List<LogEntryDetails> empDetails = userService.getViewsByDateRange(user);		
		model.addObject("user", empDetails);
		model.setViewName("bydateRangeview");
		logger.info("Exiting the program"); 
		return model;
	}
	
	@RequestMapping(value="/bydateviewexportExcel",method=RequestMethod.GET)
	public ModelAndView getviewExcelReport(HttpServletRequest req) {
		if(logger.isDebugEnabled()){
			   logger.debug("Start debug");
			  }
	    logger.info("Going to run HomeController-getviewExcelReport method");
		LogEntryDetails user = new LogEntryDetails();
		String eId=req.getParameter("eId");
		String eName=req.getParameter("eName");
		user.seteId(eId);
		user.seteName(eName);
		ModelAndView model=new ModelAndView();
		model.addObject("user", userService.getExcelByViewDetails(user));
		model.setViewName("bydateviewexportExcel");
		return model;
		
	}
	
	@RequestMapping("/loadPDFDeviceAccess")
	public ModelAndView getLoadPDFDeviceForm(
			@ModelAttribute("user") DeviceAccessDetails user, BindingResult result) {
		  //log it via log4j
		  if(logger.isDebugEnabled()){
		   logger.debug("Start debug");
		  }
		  logger.info("Going to run HomeController-getLoadPDFForm method");
		  logger.info("Exiting the program"); 
		return new ModelAndView("loadPDFDeviceAccess");
	}
	
	//Added By Sunita	
	@RequestMapping("/DevicePDFdataread")
	public ModelAndView readDeviceAccessPDFData(@RequestParam("pdffile") MultipartFile file,HttpServletRequest req) {

		String name = "DeviceAccess";

		if (!file.isEmpty()) {
			try {
				byte[] pdfByteArray = file.getBytes();
				List<DeviceAccessDetails> logAccessDetails = readDeviceAccessPdf(pdfByteArray,req);			
				userService.LoadDeviceAccessPDFData(logAccessDetails);				
				return new ModelAndView("loadPDFDeviceAccess");
			} catch (Exception e) {
				return new ModelAndView("loadPDFDeviceAccess");
			}
		} else {
			return new ModelAndView("loadPDFDeviceAccess");
		}

	}
	
	private List<DeviceAccessDetails> readDeviceAccessPdf(byte[] pdfByteArray,HttpServletRequest req) throws IOException {
		String contentOfPdf = null;
		String[] data = null;
		DeviceAccessDetails ledDeviceAccessBean = null;
		List<DeviceAccessDetails> ledDeviceAccessList = new ArrayList<DeviceAccessDetails>();
		try {
			// reads the PDF file from the given path
			PdfReader reader = new PdfReader(pdfByteArray);
			int pageNums = reader.getNumberOfPages();
			
			List<String> timeZone;
			List<String> clearanceCode;
			
			//list to match the Time Zone column
			timeZone=new ArrayList<String>();
			timeZone.add("System All Times");
			
			//list to match the Clearance Code
			clearanceCode=new ArrayList<String>();
			clearanceCode.add("Wells Fargo - Kalyani");
			clearanceCode.add("Wells Fargo-Kalyani-Server");
			//clearanceCode.add("SER2 4th Floor West Wing");	
			
			for (int j = 1; j <= pageNums; j++) {
				
				System.out.println("Page number: "+ j);

				contentOfPdf = PdfTextExtractor.getTextFromPage(reader, j);
				data = contentOfPdf.split("\\n");				
				// for loop to split the each row using substring
				for (int i = 0; i < data.length; i++) {
					
					if (data[i].contains("NTT DATA GDS LTD.")
							|| data[i].contains("Logical Device Access By A Badge Holder")
							|| data[i]
									.contains("Logical Device Location Badge Holder Name Time Zone Clearance Code Card Number")
							|| data[i].contains("Report Run Date:")) {

					}else {
						
						ledDeviceAccessBean = new DeviceAccessDetails();
									
						if (data[i].contains("System All Times")) {
							
							String name = "";
							if (data[i].startsWith("System All Times")) {
								if (!(data[i-1].contains("System All Times"))) {
									String splittedStringInSingleLine = data[i-1].concat(" ").concat(data[i]);
									
									name = splittedStringInSingleLine.substring(0, (splittedStringInSingleLine.indexOf("System"))).trim();
								}
							}
							else {
								 name = data[i].substring(0, (data[i].indexOf("System"))).trim();}
							
							
							ledDeviceAccessBean.setBatchHolderName(name);							
							for(String tzone:timeZone)
							{
								if(data[i].contains(tzone))
								{
									if(tzone.equals("System All Times"))
									{
									   ledDeviceAccessBean.setTimeZone(tzone);	
									   
								    }
								}
							}
							for(String clrCode:clearanceCode)
							{
								if(data[i].contains(clrCode))
								{
									if(clrCode.equals("Wells Fargo - Kalyani"))
									{
										ledDeviceAccessBean.setClearanceCode(clrCode);
										int indexOfKalyani = data[i].lastIndexOf("Kalyani");
										String cardNum = data[i].substring(data[i].lastIndexOf("Kalyani")+7);							
										ledDeviceAccessBean.setCardNumber(cardNum);
									}
									else if(clrCode.equals("Wells Fargo-Kalyani-Server"))
									{
										ledDeviceAccessBean.setClearanceCode(clrCode);
										int indexOfServer = data[i].lastIndexOf("Server");
										String cardNum = data[i].substring(data[i].lastIndexOf("Server")+6);
										ledDeviceAccessBean.setCardNumber((cardNum));
									}
									
								}
							}	
							String pdf_date=req.getParameter("pdf_date");
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							String d=sdf.format(new Date(pdf_date));
							//String pdf_dat="2015-02-28 00:00:00";
							ledDeviceAccessBean.setPdf_date(d);

							ledDeviceAccessList.add(ledDeviceAccessBean);		
						}
						else {
							System.out.println("Last name only available in this line "+ data[i]);
						}						
					}
				}
			}
			reader.close();
						
		return ledDeviceAccessList;
	
	}catch (Exception e) {
		logger.error("Sorry, Exception!", e);
		e.printStackTrace();
		return ledDeviceAccessList;
	}
	}		
	// Ended By Sunita  
	
	//DOne By Partha.

	private List<LogEntryDetails> readPdf(File file) throws IOException {
		// WriteToExcel excel = new WriteToExcel();
		String contentOfPdf = null;
		String[] data = null;
		LogEntryDetails ledBean = null;
		List<LogEntryDetails> ledArrayList = new ArrayList<LogEntryDetails>();
		FileInputStream fis = null; //nat

		try {

			// reads the PDF file from the given path
			//PdfReader reader = new PdfReader(pdfByteArray); //nat
			//int pageNums = reader.getNumberOfPages(); //nat
			List<String> grantList;
			List<String> descList;

			// list to match the detail column
			grantList = new ArrayList<String>();
			grantList.add("Local Grant");
			grantList.add("Invalid PIN code has been entered");
			grantList.add("Antipassback error");
			grantList.add("Valid Card at an unauthorized reader");
			grantList.add("Output point is active");
			grantList.add("Input point held past shunt time");

			// list to match the description column
			descList = new ArrayList<String>();
			descList.add("Kalyani - Wellsfargo - Entry");
			descList.add("Kalyani - Wellsfargo - Exit");
			descList.add("Kalyani - Wellsfargo - Horn");
			descList.add("Kalyani - Wellsfargo - Door Position");
			
			//channelList("NTT Kalyani Channel");

			// Extracting the content from a particular page of pdf.
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
		//	for (int j = 1; j <= pageNums; j++) {

				//System.out.println("JJJJJJJJJJ================ "+j);
				
				//contentOfPdf = PdfTextExtractor.getTextFromPage(reader, j);  //nat
			
			//============nat
			try{
			
				fis = new FileInputStream(file);
				PDFParser parser = new PDFParser(fis); 
				parser.parse(); 
				PDDocument pdfDocument = parser.getPDDocument(); 
				PDFTextStripper stripper = new PDFTextStripper(); 
				contentOfPdf = stripper.getText(pdfDocument); 
				
			}catch(Exception e){
				System.out.println("ERROR Parsing PDF file: ");
				e.printStackTrace();
			}
			finally{
				fis.close();

			}
			//============nat

			
				data = contentOfPdf.split("\\n");

				//natraj
				String rowPartData = ""; 
				String consolidatedRow = "";
				int recordNum = 0;
				
				// for loop the split the each row using substring
				for (int i = 0; i < data.length; i++) {
					
					ledBean = new LogEntryDetails();
					if (data[i].contains("NTT DATA GDS LTD.")
							|| data[i].contains("Event Log Report")
							|| (data[i]
									.contains("Event Date Description Detail Message Channel Card Number Badge Holder Name")
								) 
							|| data[i].contains("Report Run Date:")
						) {

					} else {
						//ledBean.setTimeIn(data[i].substring(0, 19));

						//==================================
						
						data[i] = data[i].replaceAll("(?:\\n|\\r)", "");
						
						try {
							Date dateString = null;
							boolean isDate = false;
							if(data[i].length()>20){
								dateString = new Date(data[i].substring(0, 19));
							    isDate = true;
							}
							if(isDate && rowPartData.length()>0){
								consolidatedRow = rowPartData;
								rowPartData= "";
							}	

							rowPartData = rowPartData + " "+ data[i];
							
						} catch (IllegalArgumentException e) {
							rowPartData = rowPartData + " "+ data[i];
						}
					
						
						if(consolidatedRow.length()>0)
						{
							//recordNum++;
							//System.out.println("Pdf Row   ================ "+consolidatedRow+",  Record num#: "+recordNum);

							//==================================
							//Suresh Added
							Date pdfDateStringToDate = new Date(consolidatedRow.substring(0, 19));
							ledBean.setTimeIn(pdfDateStringToDate);
							
							// traversing through the description list
							for (String desc : descList) {
	
								// comparing the description column with the
								// elements of the
								// descList list
								if (consolidatedRow.contains(desc)) {
									if (desc.equals("Kalyani - Wellsfargo - Horn")) {
										ledBean.setIsvalid("invalid");
									}
									ledBean.setLocation(desc);
									ledBean.setIsvalid("valid");
									break;
	
								} else {
									ledBean.setIsvalid("invalid");
								}
	
							}
	
							// traversing through the details list
							for (String grnt : grantList) {
								// comparing the detail column with the elements of
								// the
								// grantList list
								if (consolidatedRow.contains(grnt)) {
									ledBean.setMessage(grnt);
									if (consolidatedRow.contains("Local Grant")
											&& ledBean.getIsvalid()
													.equalsIgnoreCase("valid")) {
										ledBean.setIsvalid("valid");
									} else {
										ledBean.setIsvalid("invalid");
									}
									break;
								} else {
									ledBean.setMessage("");
									ledBean.setIsvalid("invalid");
								}
	
							}
	
							int leftPart = 0;
	
							try {
								// storing the length of the event date ,
								// description and
								// detail column
							/*	if (ledBean.getMessage().trim().length() == 0) {
									leftPart = consolidatedRow.lastIndexOf(ledBean
											.getLocation())
											+ ledBean.getLocation().length() + 1;
								} else {
									leftPart = consolidatedRow.lastIndexOf(ledBean
											.getMessage())
											+ ledBean.getMessage().length() + 1;
								}*/  //nat
							} catch (NullPointerException e) {
								 System.out.println(consolidatedRow);
								
								e.printStackTrace();
							} finally {
								
								consolidatedRow = consolidatedRow.replaceAll("\\s+", " ").trim();
								String[]  arr = consolidatedRow.split("\\s");
								for(int k = 0;k<arr.length;k++){
									if(arr[k].matches("[0-9]+")){
										ledBean.seteId(arr[k]);
										break;
									}
								}
								
								if(ledBean.geteId()!=null && consolidatedRow.contains(ledBean.geteId())){
									int index = consolidatedRow.indexOf(ledBean.geteId());
									ledBean.seteName(consolidatedRow.substring(index + ledBean.geteId().length(), consolidatedRow.length()));
								}
								
								consolidatedRow = "";
								
								// storing the content of last 2 column into the
								// string last
								// two column
								//String lastTwoColumns = data[i].substring(leftPart,
										//data[i].length());
	
								// storing the content of card number column into
								// the string
								// cartNum
								//ledBean.seteId((lastTwoColumns.substring(0,
										//lastTwoColumns.indexOf(" "))));
	
								// storing the content of the card holder name into
								// the
								// string card holder name
								//ledBean.seteName(lastTwoColumns.substring(ledBean
										//.geteId().length() + 1, lastTwoColumns
										//.length()));
								
								
								if(ledBean.geteId()!=null) //only condn added, nat
									ledArrayList.add(ledBean);
							}
	
						}
					}
				}

			//}  //nat
			//reader.close(); //nat

			Collections.sort(ledArrayList, new NameTimeComparator());
			System.out.println(ledArrayList.size());
			ledArrayList = prepareTimeTrackBean(ledArrayList);
			System.out.println(ledArrayList.size());

			/*
			 * for (TimeTrackBean ttb : ttBeanArrayList) {
			 * System.out.println(ttb); }
			 */

			return ledArrayList;

		} catch (Exception e) {
			logger.error("Sorry, Exception!", e);
			fis.close(); //natraj
			e.printStackTrace();
			return ledArrayList;
		}
	}

	private List<LogEntryDetails> prepareTimeTrackBean(
			List<LogEntryDetails> ledArrayList) {
		// TODO Auto-generated method stub
		int ArraListSize = ledArrayList.size();
		int i;
		int j;
		LogEntryDetails ledBean = null;
		LogEntryDetails ledBeantemp = null;
		List<LogEntryDetails> ttBeanArrayListUpdate = new ArrayList<LogEntryDetails>();

		for (i = 0; i < ArraListSize; i++) {
			// System.out.println();

			ledBean = ledArrayList.get(i);
			if (!(ledBean.getIsvalid().equalsIgnoreCase("Marked"))) {

				if (!(ledBean.getIsvalid().equalsIgnoreCase("valid"))) {
					// Insert into Database directly as Invalid Data
					ttBeanArrayListUpdate.add(ledBean);
				} else {
					for (j = i + 1; j < ArraListSize; j++) {
						ledBeantemp = ledArrayList.get(j);
						// System.out.println(j);
						if (ledBeantemp.getIsvalid().equalsIgnoreCase("valid")) {
							if (ledBean.getLocation().contains("Entry")
									&& ledBeantemp.getLocation().contains(
											"Entry")
									&& ledBeantemp.geteId().equals(
											ledBean.geteId())) {
								ledBean.setTimeOut(ledBean.getTimeIn());
								ledBean = setTimeDifferenceValue(ledBean);
								ttBeanArrayListUpdate.add(ledBean);
								break;
							}
							else if (ledBean.getLocation().contains("Entry")
									&& ledBeantemp.getLocation().contains(
											"Exit")
									&& ledBeantemp.geteId().equals(
											ledBean.geteId())) {
								ledBean.setTimeOut(ledBeantemp.getTimeIn());
								ledBeantemp.setIsvalid("Marked");
								ledBean = setTimeDifferenceValue(ledBean);
								ttBeanArrayListUpdate.add(ledBean);

								break;
							} 
						}

					}
				}
				// System.out.println(obj);
			}
		}
		return ttBeanArrayListUpdate;
	}

	private LogEntryDetails setTimeDifferenceValue(LogEntryDetails ledBean) {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		
		//String dateInString = ledBean.getTimeIn();
		//String dateOutString = ledBean.getTimeOut();
		
		try{
			
			//long duration  = formatter.parse(dateOutString).getTime() - formatter.parse(dateInString).getTime();
			
			long duration  = ledBean.getTimeOut().getTime() - ledBean.getTimeIn().getTime();
			long diffInSeconds = TimeUnit.MILLISECONDS.toSeconds(duration);
			float temp = 60.0f;
			float tempdif = (float) diffInSeconds;
			float inHours = (tempdif/temp)/temp;
			ledBean.setTimeDiffernce(inHours);
			
			//ledBean.setTimeDiffernce(inHours); //nat
			
			return ledBean;
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Sorry, something ParseException!", e);
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return ledBean;
		
	}

	/*
	 * @RequestMapping("/register") public ModelAndView
	 * getRegisterForm(@ModelAttribute("user") LogEntryDetails user,
	 * BindingResult result) { ArrayList<String> gender = new
	 * ArrayList<String>(); gender.add("Male"); gender.add("Female");
	 * ArrayList<String> city = new ArrayList<String>(); city.add("Delhi");
	 * city.add("Kolkata"); city.add("Chennai"); city.add("Bangalore");
	 * Map<String, Object> model = new HashMap<String, Object>();
	 * model.put("gender", gender); model.put("city", city);
	 * System.out.println("Register Form"); return new ModelAndView("Register",
	 * "model", model); }
	 * 
	 * @RequestMapping("/saveUser") public ModelAndView
	 * saveUserData(@ModelAttribute("user") LogEntryDetails user, BindingResult
	 * result) { userService.addUser(user);
	 * System.out.println("Save User Data"); return new
	 * ModelAndView("redirect:/userList.html"); }
	 * 
	 * @RequestMapping("/userList") public ModelAndView getUserList() {
	 * Map<String, Object> model = new HashMap<String, Object>();
	 * model.put("user", userService.getUser()); return new
	 * ModelAndView("UserDetails", model);
	 * 
	 * }
	 */
}
