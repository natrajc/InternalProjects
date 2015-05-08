<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% 
  response.setContentType("application/vnd.ms-excel");
  response.setHeader("Content-disposition","attachment;filename=export.csv"); 
%>
Employee Name,Badge Number,Date Range,Time Logged (in Hrs)
<c:forEach var="item" items="${user}">"<c:out
		value="${item.eName}" />","<c:out value="${item.eId}" />","<c:out
		value="${item.timeIn} to ${item.timeOut}" />","<c:out
		value="${item.timeDiffernce}" />"
</c:forEach>