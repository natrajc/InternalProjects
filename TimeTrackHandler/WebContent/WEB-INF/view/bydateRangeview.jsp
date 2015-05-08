
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<title>Employee Time Entry Report Handler</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<style type="text/css">
#container {
	margin: 0 30px;
	background: #fff;
}

#header {
	background: #FFF;
	padding: 20px;
}

#header h1 {
	margin: 0;
}

#navigation {
	float: left;
	width: 100%;
	background: #06F;
}

#navigation ul {
	margin: 0;
	padding: 0;
}

#navigation ul li {
	list-style-type: none;
	display: inline;
}

#navigation li a {
	display: block;
	float: left;
	padding: 5px 10px;
	color: #fff;
	text-decoration: none;
	border-right: 1px solid #fff;
}

#navigation li a:hover {
	background: #7593F9;
}

#content {
	clear: left;
	padding: 20px;
}

#content h2 {
	color: #000;
	font-size: 160%;
	margin: 0 0 .5em;
}

#footer {
	background: #FFF;
	text-align: right;
	padding: 10px;
	height: 20px;
}

#pure-table-odd {
	background-color: #999;
}
</style>

<link rel="stylesheet" type="text/css" href="styles.css">

</head>
<body bgcolor="#E2E0E0">
	<div id="container">
		<div id="header">
			<h1>
				<font color="#0066FF">NTT Data</font>-<font color="#FFCC00">Wells
					Fargo</font> Employee Time Entry Report Handler
			</h1>
		</div>
		<div id="navigation">
			<ul>
				<li><a href="loadPDF.html">Load Data</a></li>
				<li><a href="bydaterangereport.html">Report By Date range</a></li>
				<li><a href="bynamereport.html">Report By Employee Name</a></li>
			</ul>
		</div>
		<div id="content" style="min-height: 400px">
			<h2>Report By Date Range</h2>

			<table>

				<%--            <tr>
              <td style="color: Red">Total Swiped Days : </td>
              <td>${empSwipedDays}</td>
           </tr> --%>
			</table>

			<table border="1" align="center">
				<tr
					style="background-color: #3399FF; color: white; text-align: center;"
					height="40px">
					<th>Employee Name</th>
					<th>Badge Number</th>
					<th>Location</th>
					<!-- <th>Date Range</th> -->
					<th>Time In</th>
					<th>Time Out</th>
					<th>Total Time Logged(Every day)</th>
					<th>Message</th>

				</tr>
				<c:forEach items="${user}" var="user">
					<tr>
						<c:set var="eId" value="${user.eId}" />
						<c:set var="eName" value="${user.eName}"></c:set>
						<td><c:out value="${user.eName}" /></td>
						<td><c:out value="${user.eId}" /></td>
						<td><c:out value="Kalyani - Wellsfargo - Entry&Exit" /></td>
						<td><fmt:formatDate value="${user.timeIn}"
								pattern="MM/dd/yyyy" /></td>
						<td><fmt:formatDate value="${user.timeOut}"
								pattern="MM/dd/yyyy" /></td>
						<td><c:out value="${user.timeDiffernce}" /></td>
						<td><c:out value="${user.message}" /></td>


					</tr>
				</c:forEach>
			</table>
			<p style="padding-left: 300px;">
				<a
					href="bydateviewexportExcel.html?eId=<c:out value="${eId}"/>&eName=<c:out value="${eName}"/>">Export
					to Excel</a>
			</p>


		</div>
		<div style="background-color: #06F; height: 5px;">&nbsp;</div>
		<div id="footer">Copyright &copy; NTT Data INC, 2014</div>
	</div>
</body>
</html>
