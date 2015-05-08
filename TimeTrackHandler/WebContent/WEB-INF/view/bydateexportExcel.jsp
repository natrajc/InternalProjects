<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<% response.setContentType("application/vnd.ms-excel");response.setHeader("Content-disposition","attachment;filename=export.csv"); %>Employee
Name,Badge Number,Date Range,Total Time Logged,Time Logged (Emp
Avg),Total Company working Days,More/Less Swiped Days than company work
days,Total Company Hours
<c:forEach var="item" items="${user}">"<c:out
		value="${item.eName}" />","<c:out value="${item.eId}" />","<fmt:formatDate
		value="${item.timeIn}" pattern="MM/dd/yyyy" /> to <fmt:formatDate
		value="${item.timeOut}" pattern="MM/dd/yyyy" />","<c:out
		value="${item.timeDiffernce}" />","<c:out value="${item.empAverage}" />","<c:out
		value="${item.totalCmpWorkDays}" />","<c:out
		value="${item.empSwipeDifferent}" />","<c:out
		value="${item.totalCmpHours}" />"
</c:forEach>

<%-- <%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.io.*"%>
<%@page import="org.apache.poi.hssf.usermodel.HSSFSheet"%>
<%@page import="org.apache.poi.hssf.usermodel.HSSFWorkbook"%>
<%@page import="org.apache.poi.hssf.usermodel.HSSFRow"%>
<%@page import="org.apache.poi.hssf.usermodel.HSSFCell"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<% 
  String eName=request.getParameter("daterangeform"); 
  String eId=request.getParameter("daterangeform"); 
  String timeIn=request.getParameter("daterangeform"); 
  String timeDiffernce=request.getParameter("daterangeform"); 
  HSSFWorkbook wb = new HSSFWorkbook();
  HSSFSheet sheet = wb.createSheet("Excel Sheet"); 
  HSSFRow rowhead = sheet.createRow((short)0);
  rowhead.createCell((short)0).setCellValue("Employee Name");
  rowhead.createCell((short)1).setCellValue("Badge Number");
  rowhead.createCell((short)2).setCellValue("Date Range");
  rowhead.createCell((short)3).setCellValue("Time Logged (in Hrs)");
  HSSFRow row = sheet.createRow((short)1);
  row.createCell((short)0).setCellValue(eName);
  row.createCell((short)1).setCellValue(eId);
  row.createCell((short)2).setCellValue(timeIn);
  row.createCell((short)3).setCellValue(timeDiffernce); 
  FileOutputStream fileOut = new FileOutputStream("D:\\Excel_Report.xls");
  wb.write(fileOut);
  fileOut.close();
  out.println("Data is saved in excel file.");
%>   --%>