
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

.td1 {
	width: 100px;
}

.td2 {
	width: 350px;
}

.td3 {
	border-right-width: 0;
}
</style>

<link rel="stylesheet" type="text/css" href="styles.css">
<script language="Javascript">
</script>
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
				<li><a href="loadPDFDeviceAccess.html">Load Device Access
						Data</a></li>
				<li><a href="trackEmployee.html">Track Employee</a></li>
			</ul>
		</div>
		<div id="content" style="min-height: 400px">
			<h2>Track Employee Records</h2>
			<table>
				<tr>
					<td style="color: Red">Selected Year :</td>
					<td>${year}</td>
				</tr>
				<tr>
					<td style="color: Red">Selected Month :</td>
					<td>${month}</td>
				</tr>
				<tr>
					<td style="color: Red">Count(New Employees):</td>
					<td>${count}</td>
				</tr>
			</table>

			<c:choose>
				<c:when test="${!empty user}">

					<table border="1" align="center">
						<tr>
							<th colspan="3" align="center" style="color: RED">NEW
								EMPLOYEES RECORD</th>
						</tr>
						<tr
							style="background-color: #3399FF; color: white; text-align: center;"
							height="40px">
							<th>Badge Number</th>
							<th>Employee Name</th>
							<th>Location</th>
						</tr>
						<c:forEach items="${user}" var="user">
							<tr>
								<td class="td1"><c:out value="${user.cardNumber}" /></td>
								<td class="td2"><c:out value="${user.batchHolderName}" /></td>
								<td class="td3"><c:out value="${user.clearanceCode}" /></td>
						</c:forEach>
						<tr>
							<th colspan="3" align="center" style="color: RED">EXIT
								EMPLOYEES RECORD</th>
						</tr>
						<tr>
							<td colspan="3" align="center" style="color: green">NO
								RECORDS FOUND</td>
						</tr>
						<%-- <c:forEach items="${user}" var="user">
<tr>
<td class="td1"><c:out value="${user.cardNumber}"/></td>
<td class="td2"><c:out value="${user.batchHolderName}"/></td>
<td class="td3"><c:out value="${user.clearanceCode}"/></td>
</c:forEach> --%>
						<tr>
							<th colspan="3" align="center" style="color: RED">EXISTING
								EMPLOYEES RECORD</th>
						</tr>
						<tr>
							<td colspan="3" align="center" style="color: green">NO
								RECORDS FOUND</td>
						</tr>
					</table>
				</c:when>
				<c:otherwise>
					<table border="1" align="center">
						<tr
							style="background-color: #3399FF; color: white; text-align: center;"
							height="40px">
							<th>Badge Number</th>
							<th>Employee Name</th>
							<th>Location</th>
						</tr>
						<tr>
							<td colspan="3" align="center" style="color: green">RECORDS
								NOT AVAILABLE</td>
						</tr>
					</table>
				</c:otherwise>
			</c:choose>
		</div>
		<div style="background-color: #06F; height: 5px;">&nbsp;</div>
		<div id="footer">Copyright &copy; NTT Data INC, 2014</div>
	</div>
</body>
</html>
