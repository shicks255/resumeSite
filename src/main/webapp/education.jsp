<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sh" uri="/WEB-INF/taglib.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="courseList" type="java.util.List<com.steven.hicks.entities.AcademicCourse>" scope="request"/>

<c:if test="${!empty adminComputer}">
    <jsp:useBean id="adminComputer" type="java.lang.String" scope="request"/>
</c:if>
<jsp:include page="_pageSections/navBar.jsp"/>

    <script>

        function buttonAddACourse()
        {
            $('#addACourse').removeClass('hiddenDiv').addClass('popup');
        }

        function editCourse(objectId)
        {
            $.post( 'academic?action=getAjaxForEditingCourse&courseObjectId=' + objectId,
                function(data)
                {
                    $( '#editCourseDiv' ).removeClass('hiddenDiv').addClass('popup').html(data);
                });
        }

        var courseObjectId;
        function deleteACourse(objectId)
        {
            courseObjectId = objectId;
            $( '#deletePrompt' ).removeClass('hiddenDiv').addClass('popup');
        }

        function deleteCourse(objectId)
        {
            $.post( 'academic?action=deleteACourse&objectId=' + objectId,
                function(data)
                {
                    location.reload();
                })
        }

        function showUploadDialog(objectId)
        {
            $( '#uploadCourseId' ).val(objectId);
            $( '#uploadFile' ).removeClass('hiddenDiv').addClass('popup');
        }

        function showCoursework(objectId)
        {
            $.get('academic?action=getCoursework&courseObjectId=' + objectId,
                function(data)
                {
                    $( '#showCourseworkDiv' ).removeClass('hiddenDiv').addClass('popup').html(data);
                });
        }

    </script>

<div class="container">

<h1 style="text-align:center;">Course History</h1>

<c:if test="${!empty adminComputer}">
    <button class="waves-effect waves-light btn" value="Add A Course" onclick="buttonAddACourse();">
        Add A Course
        <i class="material-icons right">add</i>
    </button>
    <br/>
</c:if>

<%--ajax injected page editCoursePopup.jsp--%>
    <div id="editCourseDiv" class="hiddenDiv">
    </div>

<%--ajax injected from showCourseworkPopup.jsp--%>
    <div id="showCourseworkDiv" class="hiddenDiv">
    </div>

    <br/>
    <br/>

    <%--Main table displaying all courses--%>
    <div id="courseList" class="">
        <table class="highlight" style="width: 100%;overflow:hidden">
            <thead>
            <tr>
                <td><b>#</b></td>
                <td><b>Semester</b></td>
                <td><b>College</b></td>
                <td><b>Code</b></td>
                <td><b>Course</b></td>
                <td><b>Grade</b></td>
                <td><b>Action</b></td>
            </tr>
            </thead>

            <tbody>
            <c:set var="index" value="${0}"/>
            <c:forEach var="course" items="${courseList}">
                <c:set var="index" value="${index + 1}"/>
                <tr>
                    <td><c:out value="${index}"/></td>
                    <td><c:out value="${course.semester}"/></td>
                    <td><c:out value="${course.college}"/></td>
                    <td><c:out value="${course.courseCode}"/></td>
                    <td><c:out value="${course.courseName}"/></td>
                    <td><c:out value="${course.gradeReceived}"/></td>
                    <td>
                        <c:if test="${!empty adminComputer}">
                            <button class="waves-effect waves-light btn" name="addCoursework" id="addCoursework" value="Upload Coursework" onclick="showUploadDialog('${course.objectId}');">Upload Coursework<i class="material-icons right">attach_file</i></button>
                            <button class="waves-effect waves-light btn" name="editCourse" id="editCourse" onclick="editCourse('${course.objectId}');" value="Edit">Edit<i class="material-icons right">mode_edit</i></button>
                            <button class="waves-effect waves-light btn" name="deleteCourse" id="deleteCourse" onclick="deleteACourse('${course.objectId}');">Delete<i class="material-icons right">delete</i></button>
                        </c:if>
                        <a class="waves-effect waves-light btn" name="viewCoursework" id="viewCoursework" value="View Coursework" onclick="showCoursework('${course.objectId}');">Coursework (${course.countOfCoursework()})</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

</div>

<br/><br/>

<%--Upload coursework popup--%>
<div id="uploadFile" class="hiddenDiv">
    <div class="popupContent">
        <div class="popupHeader">
            <span style="margin: auto;">Upload A File</span>
            <i class="small material-icons closeIcon" style="cursor:pointer" onclick="closePopups();">close</i>
        </div>
        <div class="popupContainer">
            <form enctype="multipart/form-data" name="frmUploadFile" id="frmUploadFile" method="post" action="${pageContext.request.contextPath}\academic?action=uploadCoursework">
                <input type="hidden" name="uploadCourseId" id="uploadCourseId" value=""/>
                <br/>
                <input type="file" name="file" id="file" enctype="multipart/form-data">
                <br/>
                <br/>
                <label for="courseworkNotes">Coursework notes:</label>
                <input type="text" name="courseworkNotes" id="courseworkNotes"/>
                <button class="btn waves-effect waves-light submitButton" type="submit" name="action">Upload
                    <i class="material-icons right">send</i>
                </button>
            </form>
        </div>
    </div>
</div>

<%--Delete course popup--%>
<div id="deletePrompt" class="hiddenDiv">
    <div class="popupContent">
        <div class="popupHeader">
            <span style="margin: auto;">Delete Course</span>
            <i class="small material-icons closeIcon" style="cursor:pointer" onclick="closePopups();">close</i>
        </div>
        <div class="popupContainer">
            <h4>Are you sure you wanna delete this entry?</h4>
            <button class="waves-effect waves-light btn" id="deletePrompt_confirmButton" onclick="deleteCourse(courseObjectId)" >
                Ok
                <i class="material-icons right">check</i>
            </button>
        </div>
    </div>/
</div>

<%--Add a course popup--%>
<div id="addACourse"  class="hiddenDiv">
    <div id="frmDiv" class="popupContent">
        <div class="popupHeader">
            <span style="margin: auto;">Add A Course</span>
            <i class="small material-icons closeIcon" style="cursor:pointer" onclick="closePopups();">close</i>
        </div>
        <div class="popupContainer">
            <br/>
            <br/>
            <form name="frmAddACourse" id="frmAddACourse" action="${pageContext.request.contextPath}\academic?&action=addACourse" method="post">

                <table class="slimTable">
                    <tr>
                        <td class="rightCell"><label for="courseName">Course Name:</label></td>
                        <td class="left-align"><input type="text" name="courseName" id="courseName"/></td>
                    </tr>
                    <tr>
                        <td class="rightCell"><label for="courseCode">Course Code:</label></td>
                        <td><input required="true" type="text" name="courseCode" id="courseCode"/></td>
                    </tr>
                    <tr>
                        <td class="rightCell"><label for="collegeName">College:</label></td>
                        <td class="input-field col s12">
                            <select class="browser-default" required="true" id="collegeName" name="collegeName">
                                <option value="RVCC">RVCC</option>
                                <option value="Stockton College">Stockton College</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td class="rightCell"><label for="semester">Semester:</label></td>
                        <td class="input-field col s12">
                            <select class="browser-default" required="true" name="semester" id="semester">
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
                        <td class="rightCell"><label for="courseGrade">Course Grade:</label></td>
                        <td><input type="text" id="courseGrade" name="courseGrade"/></td>
                    </tr>
                    <tr>
                        <td class="rightCell"><label for="relevantCourseWork">Relevant <br/> Course Work:</label></td>
                        <td><input type="text" id="relevantCourseWork" name="relevantCourseWork"/></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <button class="waves-effect waves-light btn submitButton" type="submit" value="Submit">
                                Submit
                                <i class="material-icons right">send</i>
                            </button>
                        </td>
                    </tr>

                </table>
            </form>
        </div>
    </div>
</div>


<jsp:include page="_pageSections/footer.jsp" />
