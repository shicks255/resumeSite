<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/_pageSections/navBar.jsp"/>

<html>
<head>
    <title>Gallery</title>

    <script>
        $(document).ready(function()
        {
//            $('.carousel .carousel-slider').carousel({fullWidth: true});
            $('.carousel').carousel();

            $( '.materialboxed' ).materialbox();

        });
    </script>

</head>
<body>
<div class="container">

    <h1>Various Photos:</h1>

    <div class="carousel">
        <a class="carousel-item" onclick="showImage();" href="#one!">  <img src="images/AccordFinal.jpg"></a>
        <a class="carousel-item" href="#two!">  <img src="images/ArlingtonHillView.JPG"></a>
        <a class="carousel-item" href="#three!"><img src="images/DSCF0375.JPG"></a>
        <a class="carousel-item" href="#four!"> <img src="images/DSCF0547new.jpg"></a>
        <a class="carousel-item" href="#five!"> <img src="images/Whitehouse.JPG"></a>
    </div>

    <img class="materialboxed" width="650" src="images/AccordFinal.jpg"/>


</div>

<jsp:include page="_pageSections/footer.jsp" />

</body>
</html>