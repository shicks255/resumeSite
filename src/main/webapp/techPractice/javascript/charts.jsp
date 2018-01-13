<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="semesters"        type="java.util.List<java.lang.String>"                  scope="request"/>
<jsp:useBean id="averageGrades"    type="java.util.Map<java.lang.String, java.lang.Integer>" scope="request"/>
<jsp:useBean id="allGrades"        type="java.util.List<java.lang.String>"                   scope="request"/>
<jsp:useBean id="averages"         type="java.util.Map<java.lang.String, java.math.BigDecimal>"              scope="request"/>

<jsp:include page="/_pageSections/navBar.jsp"/>

<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.0/Chart.bundle.min.js">
</script>

<div class="bread nav-wrapper hide-on-small-and-down">
    <div class="col s12">
        <a href="${pageContext.request.contextPath}/techPractice?action=form" class="breadcrumb">Tech Practice</a>
        <a href="${pageContext.request.contextPath}/techPractice?action=javaScript" class="breadcrumb">Javascript</a>
        <a href="${pageContext.request.contextPath}/techPractice?action=charts" class="breadcrumb">Charts</a>
    </div>
</div>
<div class="container">

    <h1>Charts JS Library</h1>

    <canvas id="myChart4" width="400" height="400"></canvas>
    <script>
        var ctx4 = document.getElementById("myChart4");
        var myChart4 = new Chart(ctx4, {
            type: 'doughnut',
            data: {
                labels: [
                    <c:forEach var="letter" items="${allGrades}">
                    "${letter}"
                    <c:if test="${allGrades.indexOf(letter) != allGrades.size()-1}">
                    ,
                    </c:if>
                    </c:forEach>
                ],
                datasets: [{
                    data: [
                        <c:forEach var="letter" items="${allGrades}">
                        ${averageGrades[letter].intValue()}
                        <c:if test="${allGrades.indexOf(letter) != allGrades.size()-1}">
                        ,
                        </c:if>
                        </c:forEach>
                    ],
                    backgroundColor: [
                        "rgb(102, 204, 255)",
                        "rgb(0, 102, 255)",
                        "rgb(51, 51, 204)",
                        "rgb(102, 0, 204)",
                        "rgb(204, 0, 255)",
                        "rgb(255, 0, 102)",
                        "rgb(255, 0, 0)",
                        "rgb(153, 0, 0)",
                        "rgb(102, 51, 0)"]
                }]
            },
            options: {
                scales: {
                    yAxes: [],
                    xAxes: []
                },
                cutoutPercentage: [0],
                title: {
                    display: true,
                    text: 'Count Of Grades',
                    position: 'top',
                    fontSize: 20
                }
            }
        });
    </script>

    <canvas id="myChart3" width="400" height="400"></canvas>
    <script>
        var ctx3 = document.getElementById("myChart3");
        var myChart3 = new Chart(ctx3, {
            type: 'line',
            data: {
                labels: ['2007Fall', '2008Spring', '2008Fall', '2009Spring', '2009Fall', '2010Spring', '2010Summer',
                    '2010Fall', '2011Spring', '2011Fall', '2012Spring', '2012Summer', '2012Fall', '2013Spring',
                    '2014Summer', '2014Fall', '2015Spring', '2015Summer', '2015Fall', '2016Spring', '2016Summer'],
                datasets: [{
                    label: "School Grades",
                    lineTension: 0.5,
                    pointHoverRadius: 15,
                    pointHitRadius: 5,
                    pointBorderColor: "rgb(102, 51, 0)",
                    borderColor: "rgb(188, 43, 86)",
                    backgroundColor: "rgb(66, 203, 244)",
                    data: [
                        <c:forEach var="semester" items="${semesters}">
                        '${averages[semester]}'
                        <c:if test="${semesters.indexOf(semester) != semesters.size()}">,</c:if>
                        </c:forEach>
                    ]
                }]
            },
            options: {
                scales: {
                    yAxes: [],
                    xAxes: []
                },
                title: {
                    display: true,
                    text: 'Average GPA Per Semester',
                    position: 'top',
                    fontSize: 20
                }
            }
        });
    </script>

    <canvas id="myChart" width="400" height="400"></canvas>
    <script>
        var ctx = document.getElementById("myChart");
        var myChart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: ["Red", "Blue", "Yellow", "Green", "Purple", "Orange"],
                datasets: [{
                    label: '# of Votes',
                    data: [12, 19, 3, 5, 2, 3],
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.2)',
                        'rgba(54, 162, 235, 0.2)',
                        'rgba(255, 206, 86, 0.2)',
                        'rgba(75, 192, 192, 0.2)',
                        'rgba(153, 102, 255, 0.2)',
                        'rgba(255, 159, 64, 0.2)'
                    ],
                    borderColor: [
                        'rgba(255,99,132,1)',
                        'rgba(54, 162, 235, 1)',
                        'rgba(255, 206, 86, 1)',
                        'rgba(75, 192, 192, 1)',
                        'rgba(153, 102, 255, 1)',
                        'rgba(255, 159, 64, 1)'
                    ],
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    yAxes: [{
                        ticks: {
                            beginAtZero: true
                        }
                    }]
                },
                title: {
                    display: true,
                    text: 'TestTest',
                    position: 'top',
                    fontSize: 20
                }
            }
        });
    </script>


</div>
<jsp:include page="/_pageSections/footer.jsp" />