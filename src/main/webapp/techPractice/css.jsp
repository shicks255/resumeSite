<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/_pageSections/navBar.jsp"/>

<script>
    $(document).ready(function(){
        $('.carousel').carousel();
        $('.slider').slider();
        $('.parallax').parallax();
    });
</script>

<div class="container">

    <br/>
    <h1>CSS Practice</h1>

    <br/><br/>

    <%--Start carousel thing--%>
    <div class="carousel">
        <a class="carousel-item" href="#one!"><img   src="${pageContext.request.contextPath}/images/20160410_175343.jpg" alt="">"></a>
        <a class="carousel-item" href="#two!"><img   src="${pageContext.request.contextPath}/images/AccordFinal.jpg"></a>
        <a class="carousel-item" href="#three!"><img src="${pageContext.request.contextPath}/images/ArlingtonHillView.JPG"></a>
        <a class="carousel-item" href="#four!"><img  src="${pageContext.request.contextPath}/images/DSCF0375.JPG"></a>
        <a class="carousel-item" href="#five!"><img  src="${pageContext.request.contextPath}/images/DSCF0548.JPG"></a>
    </div>

    <br/><br/><br/>

    <%--Start slider--%>
    <div class="slider">
        <ul class="slides">
            <li>
                <img src="${pageContext.request.contextPath}/images/20160410_175343.jpg"> <!-- random image -->
                <div class="caption center-align">
                    <h3>This is our big Tagline!</h3>
                    <h5 class="light grey-text text-lighten-3">Here's our small slogan.</h5>
                </div>
            </li>
            <li>
                <img src="${pageContext.request.contextPath}/images/AccordFinal.jpg"> <!-- random image -->
                <div class="caption left-align">
                    <h3>Left Aligned Caption</h3>
                    <h5 class="light grey-text text-lighten-3">Here's our small slogan.</h5>
                </div>
            </li>
            <li>
                <img src="${pageContext.request.contextPath}/images/ArlingtonHillView.JPG"> <!-- random image -->
                <div class="caption right-align">
                    <h3>Right Aligned Caption</h3>
                    <h5 class="light grey-text text-lighten-3">Here's our small slogan.</h5>
                </div>
            </li>
            <li>
                <img src="${pageContext.request.contextPath}/images/DSCF0375.JPG"> <!-- random image -->
                <div class="caption center-align">
                    <h3>This is our big Tagline!</h3>
                    <h5 class="light grey-text text-lighten-3">Here's our small slogan.</h5>
                </div>
            </li>
        </ul>
    </div>


    <br/><br/>

    <%--Parallax thing--%>
    <div class="parallax-container">
        <div class="parallax"><img src="${pageContext.request.contextPath}/images/DSCF0375.JPG"></div>
    </div>
    <div class="section white">
        <div class="row container">
            <h2 class="header">Parallax</h2>
            <p class="grey-text text-darken-3 lighten-3">Parallax is an effect where the background content or image in this case, is moved at a different speed than the foreground content while scrolling.</p>
        </div>
    </div>
    <div class="parallax-container">
        <div class="parallax"><img src="${pageContext.request.contextPath}/images/ArlingtonHillView.JPG"></div>
    </div>




</div>
<jsp:include page="/_pageSections/footer.jsp" />
