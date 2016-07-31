<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Steven
  Date: 6/18/2016
  Time: 6:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="courseList" type="java.util.List<com.steven.hicks.entities.AcademicCourse>" scope="request"/>
<html>
<head>
    <title>My Education</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/mainStyle.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/JS/jquery-3.1.0.js"></script>

    <script>
        function buttonAddACourse()
        {
            $( '#frmDiv' ).css("display","inline");
        }

        function editCourse(objectId)
        {
            $.post( 'academic?action=getAjaxForEditingCourse&courseObjectId=' + objectId,
                function(data)
                {
                    $( '#editCourse' ).addClass('popup');
                    $( '#editCourse' ).html(data);
                }

            );
        }

    </script>

</head>
<body>
<jsp:include page="_pageSections/header.jsp"/>
<jsp:include page="_pageSections/navBar.jsp"/>
<jsp:include page="/education/educationNavBar.jsp"/>

<h1>Course History:</h1>

<button value="Add A Course" onclick="buttonAddACourse();">Add A Course</button>

<div id="editCourse">
    <div id="frmEditCourse" style="display:none">
        hello
    </div>
</div>

<div id="addACourse" class="notecard">
    <div id="frmDiv" style="display:none">
        <form name="frmAddACourse" id="frmAddACourse" action="${pageContext.request.contextPath}\academic?&action=addACourse" method="post">
            <table>
                <tr>
                    <td><label for="courseName">Course Name:</label></td>
                    <td><input type="text" name="courseName" id="courseName"/></td>
                </tr>
                <tr>
                    <td><label for="courseCode">Course Code:</label></td>
                    <td><input required="true" type="text" name="courseCode" id="courseCode"/></td>
                </tr>
                <tr>
                    <td><label for="collegeName">College:</label></td>
                    <td>
                        <select required="true" id="collegeName" name="collegeName">
                            <option value="RVCC">RVCC</option>
                            <option value="Stockton College">Stockton College</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><label for="semester">Semester:</label></td>
                    <td>
                        <select required="true" name="semester" id="semester">
                            <option value="2007Fall">Fall 2007</option>
                            <option value="2008Spring">Spring 2008</option>
                            <option value="2008Summer">Summer 2008</option>
                            <option value="2008Fall">Fall 2008</option>
                            <option value="2009Spring">Spring 2009</option>
                            <option value="2009Summer">Summer 2009</option>
                            <option value="2009Fall">Fall 2009</option>
                            <option value="2010Spring">Spring 2010</option>
                            <option value="2010Summer">Summer 2010</option>
                            <option value="2010Fall">Fall 2010</option>
                            <option value="2011Spring">Spring 2011</option>
                            <option value="2011Summer">Summer 2011</option>
                            <option value="2011Fall">Fall 2011</option>
                            <option value="2012Spring">Spring 2012</option>
                            <option value="2012Summer">Summer 2012</option>
                            <option value="2012Fall">Fall 2012</option>
                            <option value="2013Spring">Spring 2013</option>
                            <option value="2013Summer">Summer 2013</option>
                            <option value="2013Fall">Fall 2013</option>
                            <option value="2014Spring">Spring 2014</option>
                            <option value="2014Summer">Summer 2014</option>
                            <option value="2014Fall">Fall 2014</option>
                            <option value="2015Spring">Spring 2015</option>
                            <option value="2015Summer">Summer 2015</option>
                            <option value="2015Fall">Fall 2015</option>
                            <option value="2016Spring">Spring 2016</option>
                            <option value="2016Summer">Summer 2016</option>
                            <option value="2016Fall">Fall 2016</option>
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
            <input type="submit" value="Submit" />
        </form>
    </div>
</div>

<br/>
<br/>


<div id="courseList" class="notecard">

    <table border="1">
        <tr>
            <td>Semester:</td>
            <td>Code:</td>
            <td>Course:</td>
            <td>Grade:</td>
            <td>Actions:</td>
        </tr>

        <c:forEach var="course" items="${courseList}">
            <tr>
                <td><c:out value="${course.semester}"/></td>
                <td><c:out value="${course.courseCode}"/></td>
                <td><c:out value="${course.courseName}"/></td>
                <td><c:out value="${course.gradeReceived}"/></td>
                <td>
                    <button name="addCoursework" id="addCoursework" value="Upload Coursework" onclick="">Upload Coursework</button>
                    <button name="editCourse" id="editCourse" onclick="editCourse('${course.objectId}');" value="Edit">Edit</button>
                </td>
            </tr>
        </c:forEach>


    </table>

</div>

<jsp:include page="_pageSections/footer.jsp" />
</body>
</html>
