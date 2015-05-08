<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<% response.setContentType("application/vnd.ms-excel");
  response.setHeader("Content-disposition","attachment;filename=export.csv"); 
  %>Employee Name,Badge Number,Location,Time In,Time Out,Message
<c:forEach var="items" items="${user}">"<c:out
		value="${items.eName}" />","<c:out value="${items.eId}" />","<c:out
		value="Kalyani - Wellsfargo - Entry&Exit" />","<fmt:formatDate
		value="${items.timeIn}" pattern="MM/dd/yyyy" />","<fmt:formatDate
		value="${items.timeOut}" pattern="MM/dd/yyyy" />","<c:out
		value="${items.message}" />"
</c:forEach>