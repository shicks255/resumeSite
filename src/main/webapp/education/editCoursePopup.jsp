<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="course" type="com.steven.hicks.entities.AcademicCourse" scope="request"/>
<html>
<head>
    <title>Title</title>

    <script>

        $(document).ready( function()
        {
            $( '#collegeNameEdit' ).val('${course.college}');
            $( '#semesterEdit' ).val('${course.semester}');
        });

        function closePopup()
        {
            $( '#editCourseDiv' ).removeClass('popup').addClass('hiddenDiv');
            location.reload();
        }

    </script>

</head>
<body>

<div class="popupContent">
    <button value="Cancel" onclick="closePopup();">Close</button>
<form name="frmEditACourse" id="frmEditACourse" action="${pageContext.request.contextPath}\academic?&action=editACourse&courseId=${course.objectId}" method="post">
    <input type="submit" value="Submit" />
    <table>
        <tr>
            <td><label for="courseNameEdit">Course Name:</label></td>
            <td><input type="text" name="courseNameEdit" id="courseNameEdit" value="${course.courseName}"/></td>
        </tr>
        <tr>
            <td><label for="courseCodeEdit">Course Code:</label></td>
            <td><input required="true" type="text" name="courseCodeEdit" id="courseCodeEdit" value="${course.courseCode}"/></td>
        </tr>
        <tr>
            <td><label for="collegeNameEdit">College:</label></td>
            <td class="input-field col s12">
                <select class="browser-default" required="true" id="collegeNameEdit"  name="collegeNameEdit">
                    <option value="RVCC">RVCC</option>
                    <option value="Stockton College">Stockton College</option>
                </select>
            </td>
        </tr>
        <tr>
            <td><label for="semesterEdit">Semester:</label></td>
            <td class="input-field col s12">
                <select class="browser-default" required="true" name="semesterEdit" id="semesterEdit">
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
            <td><label for="courseGradeEdit">Course Grade:</label></td>
            <td><input type="text" id="courseGradeEdit" name="courseGradeEdit" value="${course.gradeReceived}"/></td>
        </tr>
        <tr>
            <td><label for="relevantCourseWork">Relevant Course Work:</label></td>
            <td><input type="text" id="relevantCourseWork" name="relevantCourseWork"/></td>
        </tr>
    </table>
</form>
</div>

</body>
</html>
