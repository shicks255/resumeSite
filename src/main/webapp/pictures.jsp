<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/_pageSections/navBar.jsp"/>

<jsp:useBean id="fileList" type="java.util.List<java.lang.String>" scope="request"/>

    <script>
        $(document).ready(function()
        {
            $('.carousel.carousel-slider').carousel({fullWidth: true});
            $('.carousel').carousel();

//            $( '.materialboxed' ).materialbox();

        });

        function showImage(pic)
        {
            console.log("hi");
            console.log(pic);
            var picBox = $( '#pic' );
//            picBox.addClass('materialboxed');
            $( '#picBox').addClass('materialboxed');
            $( '.materialboxed' ).materialbox();
        }
    </script>

<div class="container">

    <h1>Various Photos:</h1>

    <div class="carousel carousel-slider">
        <c:set var="index" value="${0}"/>
        <c:forEach var="file" items="${fileList}">
            <c:set var="index" value="${index + 1}"/>
            <script>console.log("hi");</script>
            <a id="pic${index}" class="carousel-item" onclick="showImage('pic${index}');" href="#${index}!">
                <%--<img class="materialboxed" src="images/${file}">--%>
                <img src="images/${file}">
            </a>
        </c:forEach>
    </div>

    <%--<img height="450" src="images/AccordFinal.jpg"/>--%>

</div>

<jsp:include page="_pageSections/footer.jsp" />