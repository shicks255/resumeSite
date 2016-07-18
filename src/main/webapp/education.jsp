<%--
  Created by IntelliJ IDEA.
  User: Steven
  Date: 6/18/2016
  Time: 6:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Education</title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/mainStyle.css">

</head>
<body>
<jsp:include page="_pageSections/header.jsp"/>
<jsp:include page="_pageSections/navBar.jsp"/>
<jsp:include page="/education/educationNavBar.jsp"/>

<h1>Course History:</h1>


<div id="addACourse">
    <form name="frmAddACourse" id="frmAddACourse" action="${pageContext.request.contextPath}\academic?&action=addACourse" method="post">
        <table>
            <tr>
                <td><label for="courseName">Course Name:</label></td>
                <td><input type="text" name="courseName" id="courseName"/></td>
            </tr>
            <tr>
                <td><label for="courseCode">Course Code:</label></td>
                <td><input type="text" name="courseCode" id="courseCode"/></td>
            </tr>
            <tr>
                <td><label for="collegeName">College:</label></td>
                <td><input type="text" name="collegeName" id="collegeName"/></td>
            </tr>
            <tr>
                <td><label for="">Semester:</label></td>
                <td><select>
                    <option value="dslkafjds">Lkdfjaf</option>
                    <option value="dslkafjds2">Lkdfjaf2</option>
                </select></td>
            </tr>
            <tr>
                <td><label for="courseGrade">Course Grade:</label></td>
                <td><input type="text" id="courseGrade" name="courseGrade"/></td>
            </tr>
            <tr>
                <td><label for="relevantCourseWork">Relevant Course Work:</label></td>
                <td><input type="text" id="relevantCourseWork" name="relevantCourseWork"/></td>
            </tr>
            <tr>
                <td></td>
                <td></td>
            </tr>
        </table>
        <input type="submit" value="Submit"/>
    </form>
</div>

<jsp:include page="_pageSections/footer.jsp" />
</body>
</html>
