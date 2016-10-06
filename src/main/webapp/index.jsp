<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <link href="${pageContext.request.contextPath}/CSS/mainStyle.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="_pageSections/header.jsp"/>
<jsp:include page="_pageSections/navBar.jsp"/>

<!-- Need to figure out the spacing on this issue -->

<!-- START SKILLS HERE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! -->

<a href="${pageContext.request.contextPath}/control?tab1=test&tab2=test2">Click Here</a>

<br><br>
<h3 style="text-align: center; font-weight : bold;">Skills</h3>
<br>

<ul>
    <li>Proven and demonstrated customer service ability in different environments,
        both over the phone and face to face</li>
    <li>Adept with computer hardware/software specifications and maintenance</li>
    <li>Well accustomed to using Microsoft Word, Excel, Powerpoint and Access</li>
    <li>Advanced Spanish Skills</li>
</ul>

<!-- START LANGUAGES HERE!!!!!!!!!!!!!!!!!! -->

<h3 style="text-align: center; font-weight : bold;">Computer Languages</h3>

<ul>
    <li>Java</li>
    <li>JavaScript</li>
    <li>HTML</li>
    <li>CSS</li>
    <li>SQL</li>
</ul>

<h3 style="text-align: center; font-weight : bold;">Technical Competencies</h3>

<ul>
    <li>Windows XP,Vista,7,8</li>
    <li>Linux (Ubunut Distribution)</li>
    <li>Microsoft Office</li>
    <li>Windows Batch Files</li>
    <li>Apache</li>
    <li>MySQL</li>
    <li>AS400</li>
</ul>

<!-- START EDUCATION HERE!!!!!!!!!!!!!!!!!!!!!!!!!!!!! -->

<h3 style="text-align:center; font-weight : bold;">Education</h3>

<b>Raritan Valley Community College</b>, Branchburg NJ
<br>

A.S. Computer Programming, Fall 2015

<br><br><br>

<b>Richard Stockton College of New Jersey</b>, Galloway NJ
<br>
B.A. History, Spring 2013
<br>
Spanish Minor
<br>
<b>Honors:</b>

<ul>
    <li>Program Distinction, with Thesis Award</li>
    <li>Presenter, Northeast Regional Honors Council 2013 Conference in Philadelphia</li>
    <li>Published in <i>Stockton Innovations</i>, school's undergraduate research journal</li>
    <li>Dean's List recipient during 4 semesters</li>
</ul>

<b>Raritan Valley Community College</b>, Branchburg NJ
<br>
A.A. Liberal Studies, Spring 2011
<br>

<!-- START WORK HISTORY HERE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! -->


<h3 style="text-align:center ; font-weight : bold;">Work History</h3>

<b>Fedway Associates Inc</b>., Basking Ridge NJ
<br>

Computer Support Specialist November 2014 - Present

<ul>
    <li>Help everybody with computer issues</li>
</ul>

<br>

Accounts Receivable Agent August 2013 - November 2014

<ul>
    <li>Responsible for billing customers for goods delivered through various sales divisions of a $900
        million corporation.</li>
    <li>Work with customers and internal sales force daily to ensure prompt payments, and to identify
        past-due balances, short payments, and over payments.</li>
    <li>Contact customers as needed to obtain or communicate account information as well as for collections
        and the resolution of discrepancies.</li>
    <li>Review aging reports to confirm outstanding unpaid balances have been filed with the state's
        Credit Compliance website, as defined by legal requirementes established by the New Jersey Alcohol
        Beverage Control.</li>
</ul>


<br>

<b>Stockton College</b>, Galloway NJ
<br>
Computer Services Student Worker September 2011 - May 2012

<ul>
    <li>Assisted both students and teachers with audio visual queries, as well as providing
        general computer help.</li>

</ul>

<br>

<b>Five Below Inc</b>., Flemington NJ
<br>
Shift Manager September 2009 - August 2011

<ul>
    <li>Resonsible for providing outstanding service to customers and performing operational duties to help
        drive sales through product knowledge, merchandising, register functions, store cleanliness and other
        related duties.</li>
</ul>

<br><br>

<jsp:include page="_pageSections/footer.jsp" />

</body>


</html>