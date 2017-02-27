<%@ page import="java.util.Date" %>
<%@ page import="java.time.LocalDate" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

</body>
<footer class="page-footer">

    <div class="container">
        <div class="row">
            <div class="col s12 center">
                <h5 class="blue-text center">Site Map</h5>
            </div>
            <div class="col s4 center">
                <h5 class="blue-text">Education</h5>
                <ul>
                    <li><a class="black-text text-lighten-3" href="${pageContext.request.contextPath}/academic?action=form">Education</a></li>
                    <li><a class="black-text text-lighten-3" href="${pageContext.request.contextPath}/academic?&action=thesis">Thesis</a></li>
                    <li><a class="black-text text-lighten-3" href="${pageContext.request.contextPath}/academic?&action=bibliography">Bibliography</a></li>
                </ul>
            </div>
            <div class="col s4 center">
                <h5 class="blue-text">Tech Practice</h5>
                <ul>
                    <li><a class="black-text text-lighten-3" href="${pageContext.request.contextPath}/techPractice?action=form">Tech Practice</a></li>
                    <li><a class="black-text text-lighten-3" href="${pageContext.request.contextPath}/techPractice?&action=java">Java</a></li>
                    <li><a class="black-text text-lighten-3" href="${pageContext.request.contextPath}/techPractice?&action=javaScript">Javascript</a></li>
                    <li><a class="black-text text-lighten-3" href="${pageContext.request.contextPath}/techPractice?&action=css">CSS</a></li>
                </ul>
            </div>
            <div class="col s4 center">
                <h5 class="blue-text">Links</h5>
            </div>
        </div>
    </div>

    <div class="footer-copyright">
        <div class="container">
            &copy; Steven M Hicks
            <%
                LocalDate date = LocalDate.now();
                out.print(date.toString());
            %>
            <a class="grey-text text-lighten-4 right" href="#!">More Links</a>
        </div>
    </div>

</footer>
</html>
