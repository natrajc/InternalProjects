
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
<script language="Javascript">

// Declaring valid date character, minimum year and maximum year
var dtCh= "/";
var minYear=1900;
var maxYear=2100;

function isInteger(s){
	var i;
    for (i = 0; i < s.length; i++){   
        // Check that current character is number.
        var c = s.charAt(i);
        if (((c < "0") || (c > "9"))) return false;
    }
    // All characters are numbers.
    return true;
}

function stripCharsInBag(s, bag){
	var i;
    var returnString = "";
    // Search through string's characters one by one.
    // If character is not in bag, append to returnString.
    for (i = 0; i < s.length; i++){   
        var c = s.charAt(i);
        if (bag.indexOf(c) == -1) returnString += c;
    }
    return returnString;
}

function daysInFebruary (year){
	// February has 29 days in any year evenly divisible by four,
    // EXCEPT for centurial years which are not also divisible by 400.
    return (((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28 );
}
function DaysArray(n) {
	for (var i = 1; i <= n; i++) {
		this[i] = 31
		if (i==4 || i==6 || i==9 || i==11) {this[i] = 30}
		if (i==2) {this[i] = 29}
   } 
   return this
}

function isDate(dtStr){
	var daysInMonth = DaysArray(12)
	var pos1=dtStr.indexOf(dtCh)
	var pos2=dtStr.indexOf(dtCh,pos1+1)
	var strMonth =dtStr.substring(0,pos1)
	var strDay=dtStr.substring(pos1+1,pos2)
	var strYear=dtStr.substring(pos2+1)
	strYr=strYear
	if (strDay.charAt(0)=="0" && strDay.length>1) strDay=strDay.substring(1)
	if (strMonth.charAt(0)=="0" && strMonth.length>1) strMonth=strMonth.substring(1)
	for (var i = 1; i <= 3; i++) {
		if (strYr.charAt(0)=="0" && strYr.length>1) strYr=strYr.substring(1)
	}
	month=parseInt(strMonth)
	day=parseInt(strDay)
	year=parseInt(strYr)
	if (pos1==-1 || pos2==-1){
		alert("The date format should be : MM/dd/yyyy")
		return false
	}
	if (strMonth.length<1 || month<1 || month>12){
		alert("Please enter a valid month")
		return false
	}
	if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
		alert("Please enter a valid day")
		return false
	}
	if (strYear.length != 4 || year==0 || year<minYear || year>maxYear){
		alert("Please enter a valid 4 digit year between "+minYear+" and "+maxYear)
		return false
	}
	if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
		alert("Please enter a valid date")
		return false
	}
return true
}

function ValidateForm(){
	var stardt=document.daterangeform.timeIn
	var enddt=document.daterangeform.timeOut
	if (isDate(stardt.value)==false || isDate(enddt.value)==false){
		return false
	}
    return true
 }
 
 function passCmpWorkDaysToViewPage()
 {
	
	 //document.getElementById("totalCmpWorkDays").value = "1";
	 //alert("totalCmpWork days value: "+ document.getElementById("totalCmpWorkDays").value);
 }

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
			</ul>
		</div>
		<div id="content" style="min-height: 400px">
			<h2>Report By Date Range</h2>

			<c:url var="recordsbydaterange" value="recordsbydaterange.html" />
			<form:form id="daterangeform" modelAttribute="user" method="post"
				action="${recordsbydaterange}" onSubmit="return ValidateForm()"
				name="daterangeform">
				<table align="center">
					<tr>
						<td colspan="2" align="center"></td>
					</tr>
					<tr>
						<td style="padding-right: 10px;"><font size="+1">
								Start Date:</font></td>
						<td><input type="text" name="timeIn" value="01/01/2015">
							&nbsp (MM/dd/yyyy)</td>
					</tr>
					<tr>
						<td style="padding-right: 10px;"><font size="+1"> End
								Date:</font></td>
						<td><input type="text" name="timeOut" value=""> &nbsp
							(MM/dd/yyyy)</td>
					</tr>
					<tr>
						<td colspan="2" align="center" style="padding-top: 20px"><input
							type="submit" value="Generate Report"></td>
					</tr>

				</table>

				<input type="hidden" name="totalCmpWorkDays" id="totalCmpWorkDays"
					value="${totalCompanyHours}">
				<table>
					<tr>
						<td style="color: Red">Total Company Working Days :</td>
						<td>${totalCmpWorkDays}days</td>
					</tr>
					<tr>
						<td style="color: Red">Total Company Working Hours :</td>
						<td>${totalCompanyHours}hrs</td>
					</tr>
				</table>

			</form:form>





			<c:if test="${!empty user}">

				<table border="1" align="center">

					<tr
						style="background-color: #3399FF; color: white; text-align: center;"
						height="40px">
						<th>Employee Name</th>
						<th>Badge Number</th>
						<th>Date Range</th>
						<th>Total Time Logged (in Hrs)</th>
						<th>Time Logged (Emp Avg)</th> <
						<th>More/Less Swiped Days</th>
						<th>View Details</th>
					</tr>
					<c:forEach items="${user}" var="user">
						<tr>
							<%-- <c:set var="eId" value="${user.eId}"/>  --%>
							<c:set var="timein" value="${user.timeIn}" />
							<c:set var="timeout" value="${user.timeOut}" />
							<td><c:out value="${user.eName}" /></td>
							<td><c:out value="${user.eId}" /></td>

							<%-- <td><c:out value="${user.timeIn} to ${user.timeOut}"/></td> --%>
							<%-- <fmt:formatDate value="${user.timeIn}" pattern="yyyy-MM-dd"/> --%>
							<td><fmt:formatDate value="${user.timeIn}"
									pattern="MM/dd/yyyy" /> to <fmt:formatDate
									value="${user.timeOut}" pattern="MM/dd/yyyy" /></td>

							<td><c:out value="${user.timeDiffernce}" /></td>
							<td><c:out value="${user.empAverage}" /></td>
							<td><c:out value="${user.empSwipeDifferent}" /></td>
							<%-- <td><c:out value="2"/></td> --%>
							<%-- <td><a onclick="passCmpWorkDaysToViewPage();" href="bydateRangeview.html?eId=<c:out value="${user.eId}"/>&timein=<c:out value="${timein}"/>&timeout=<c:out value="${timeout}"/>">View Details</a></td> --%>
							<td><a onclick="passCmpWorkDaysToViewPage();"
								href="bydateRangeview.html?eId=<c:out value="${user.eId}"/>&timein=<fmt:formatDate value="${timein}" pattern="MM/dd/yyyy"/>&timeout=<fmt:formatDate value="${timeout}" pattern="MM/dd/yyyy"/>&totalSwipedDays=<c:out value="${user.empSwipeDifferent}"/>">View
									Details</a></td>
						</tr>
					</c:forEach>
				</table>

				<%-- <p style="padding-left:300px;"><a href="bydateexportExcel.html?timein=<c:out value="${timein}"/>&timeout=<c:out value="${timeout}"/>">Export to Excel</a></p> --%>
				<p style="padding-left: 300px;">
					<a
						href="bydateexportExcel.html?timein=<fmt:formatDate value="${timein}" pattern="MM/dd/yyyy"/>&timeout=<fmt:formatDate value="${timeout}" pattern="MM/dd/yyyy"/>">Export
						to Excel</a>
				</p>
			</c:if>


		</div>
		<div style="background-color: #06F; height: 5px;">&nbsp;</div>
		<div id="footer">Copyright &copy; NTT Data INC, 2014</div>
	</div>
</body>
</html>
