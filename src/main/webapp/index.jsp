<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/taglib.tld" prefix="sh" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/_pageSections/navBar.jsp"/>

<style>
    td {padding : 0;}
</style>

<script>
    function emailPopup()
    {
        $( '#dialog-emailPopup' ).removeClass('hiddenDiv').addClass('popup');
    }

    function getRecentTracks()
    {
        console.log("getting recent track info");
        $.getJSON('https://ws.audioscrobbler.com/2.0/?method=user.getrecenttracks&user=shicks255&api_key=c349ab1fcb6b132ffb8d842e982458db&limit=10&format=json',
            function(json)
            {
                console.log(json);
                $.each(json.recenttracks.track, function(i, item)
                {
                    if (item['@attr'])
                    {
                        $( '#recentTracksTable tr:last' ).after(
                            '<tr>' +
                            '<td><img src="' + item.image[1]['#text'] + '" /></td>' +
                            '<td>' + item.artist['#text'] + ' - ' + item.name + '</td>' +
                            '<td> <i class="material-icons">equalizer</i>" now playing </td>' +
                            '</tr>'
                        );
                    }
                    else
                    {
                        var date = new Date(item.date['#text']);
                        date.setHours(date.getHours() - 5);
                        $( '#recentTracksTable tr:last' ).after(
                            '<tr>' +
                            '<td><img src="' + item.image[1]['#text'] + '" /></td>' +
                            '<td>' + item.artist['#text'] + ' - ' + item.name + '</td>' +
                            '<td>' + date.toLocaleString() + '</td>' +
                            '</tr>'
                        );
                    }
                });
            });
    }
</script>

<div class="container">

    <div onclick="emailPopup();" class="fixed-action-btn hide-on-small-and-down" style="bottom:45px; right: 14px;">
        <a class="btn-floating btn-medium waves-effect waves-light deep-purple lighten-3"><i class="large material-icons">mail</i></a>
    </div>

    <button class="btn waves-effect waves-light" onclick="getRecentTracks();">Musivc</button>
    <div id="recentTracks" name="recentTracks">
        <table id="recentTracksTable" class="striped">
            <tr></tr>
        </table>
    </div>

    <br><br>
    <h1 style="text-align: center; font-weight : bold;">Skills</h1>
    <br>

        <div style="text-align: center;">
            <ul>
                Maintain and enhance the functionality of an industry leading JAVA EE8 web application used by New Jersey schools for staff management.
            </ul>
        </div>

    <!-- START LANGUAGES HERE!!!!!!!!!!!!!!!!!! -->

    <h1 style="text-align: center; font-weight : bold;">Tools and Frameworks</h1>

        <ul>
            <li>Java</li>
            <li>-Java EE7 with jdk 8</li>
            <li>-Java collections, JPA, JDBC</li>
            <li>-MVC framework using servlets and jsp</li>
            <li>-Oracle Database 12c</li>
            <li>-Apache Tomcat 8 web container/server</li>
            <li>-Apache POI library</li>
            <li>-Javascript, jQuery, jQuery UI</li>
            <li>-JSON and ajax</li>
            <li>-Intellij Idea IDE</li>
            <li>-Maven deployment and dependencies</li>
            <li>-JIRA issue tracking</li>
            <li>-Jenkins Continuous Integration</li>
            <li>-BitBucket Git Repository</li>
        </ul>

    <!-- START EDUCATION HERE!!!!!!!!!!!!!!!!!!!!!!!!!!!!! -->

    <h1 style="text-align:center; font-weight : bold;">Education</h1>

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

        <br>

    <b>Raritan Valley Community College</b>, Branchburg NJ
    <br>
    A.A. Liberal Studies, Spring 2011
    <br>

    <!-- START WORK HISTORY HERE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! -->


<h1 style="text-align:center ; font-weight : bold;">Work History</h1>

        <b>Forge-Tech Inc</b> , Martinsville NJ
        <br/>

        <u>Web Developer/Programmer January 2016 - Present</u>

        <ul>
            <li>Help design and write software used by New Jersey school districts including:</li>
            <li>
                <ul>
                    <li>Staff Management and Teacher Evaluations</li>
                    <li>Attendance and Reporting</li>
                    <li>Techer and other Employees Portal</li>
                    <li>Payroll System</li>
                </ul>
            </li>
        </ul>

        <b>Fedway Associates Inc</b>., Basking Ridge NJ
        <br>

        <u>Computer Support Specialist November 2014 - Present</u>

        <ul>
            <li>Help everybody with computer issues</li>
        </ul>

        <br>

        <u>Accounts Receivable Agent August 2013 - November 2014</u>

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
        <u>Computer Services Student Worker September 2011 - May 2012</u>

        <ul>
            <li>Assisted both students and teachers with audio visual queries, as well as providing
                general computer help.</li>

        </ul>

        <br>

        <b>Five Below Inc</b>., Flemington NJ
        <br>
        <u>Shift Manager September 2009 - August 2011</u>

        <ul>
            <li>Resonsible for providing outstanding service to customers and performing operational duties to help
                drive sales through product knowledge, merchandising, register functions, store cleanliness and other
                related duties.</li>
        </ul>

        <br><br>

        <div id="dialog-emailPopup" class="hiddenDiv">
            <div class="popupContent">
                <button class="waves-effect waves-light btn closeButton" id="closeEmail" name="closeEmail" onclick="closePopups();">Close</button>
                    <br/>
                    <br/>
                    <br/>
                <form name="" id="" method="post" action="${pageContext.request.contextPath}\academic?action=uploadCoursework">
                    <label for="emailSubject">Subject:</label>
                    <input type="text" name="emailSubject" id="emailSubject">

                    <label for="emailBody">Body:</label>
                    <textarea style="overflow: hidden;max-width: 100%" placeholder="Enter your message here..." id="emailBody" id="emailBody"></textarea>
                </form>
            </div>
        </div>

</div>

<jsp:include page="_pageSections/footer.jsp" />

