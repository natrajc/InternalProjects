
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
				<li><a href="loadPDFDeviceAccess.html">Load Device Access
						Data</a></li>
				<li><a href="trackEmployee.html">Track Employee</a></li>
			</ul>
		</div>
		<div id="content" style="min-height: 400px">
			<h2>Load Data</h2>
			<form:form id="registerForm" modelAttribute="command" method="post"
				action="PDFdataread.html" enctype="multipart/form-data">

				<table align="center" bordercolor="#666666">
					<tr>
						<td colspan="2" align="center"></td>
					</tr>
					<tr>
						<td style="padding-right: 10px;"><font size="+1">
								Select PDF file to be Loaded:</font></td>
						<td><input type="file" name="pdffile"></td>
					</tr>
					<tr>
						<td colspan="2" align="center" style="padding-top: 20px"><input
							type="submit" value="Load to DB"></td>
					</tr>
				</table>

			</form:form>


		</div>
		<div style="background-color: #06F; height: 5px;">&nbsp;</div>
		<div id="footer">Copyright &copy; NTT Data INC, 2014</div>
	</div>
</body>
</html>
