<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/_pageSections/navBar.jsp"/>

<jsp:useBean id="fileList" type="java.util.List<java.lang.String>" scope="request"/>
<jsp:useBean id="imagePath" type="java.lang.String" scope="request"/>

    <script>
        $(document).ready(function()
        {
            $(document.body).click(function(event)
            {
//                unMaterializeAll();
//                if (event.keyCode == 27)
//                {
//                    $( '.popup' ).each(function(i, obj){
//                        $( this ).removeClass('popup').addClass('hiddenDiv');
//                    });
//                }
            });
        });

        function openPicture(fileName)
        {
            var cutHere = fileName.indexOf("_small");
            var bigPicNameStart = fileName.slice(0,cutHere);
            var bigPicNameEnd = fileName.slice(cutHere+6);

            var newFileName = bigPicNameStart + bigPicNameEnd;
            var servletContext = "${pageContext.request.contextPath}";
            window.open(servletContext + '/images/' + newFileName, '');
        }
    </script>

    <style>
        .liquidPic {width : 100%;}
    </style>

<div class="container">

    <h1>Various Photos:</h1>

    <div class="row">
        <c:set var="classType" value="col s12 m6 l3"/>
        <c:forEach var="file" items="${fileList}">
            <div class="${classType}" style="margin-bottom : 1.8%;">
                <a href="#"><img onclick="openPicture('${file}');" class="liquidPic hoverable" data-caption="A pic" src="${pageContext.request.contextPath}/images/${file}"/></a>
            </div>
        </c:forEach>
    </div>

    <%--<hr/>--%>

    <%--<script>--%>
        <%--function makeMaterialize(element)--%>
        <%--{--%>
            <%--var fileNameSmall = element.getAttribute('data-source');--%>

            <%--var cutHere = fileNameSmall.indexOf("_small");--%>
            <%--var bigPicNameStart = fileNameSmall.slice(0,cutHere);--%>
            <%--var bigPicNameEnd = fileNameSmall.slice(cutHere+6);--%>

            <%--var newFileName = bigPicNameStart + bigPicNameEnd;--%>
            <%--var servletContext = "${pageContext.request.contextPath}";--%>
            <%--element.setAttribute('src', servletContext + '/images/' + newFileName);--%>
            <%--element.setAttribute('data-souce', newFileName);--%>
            <%--element.setAttribute('width', '950');--%>
            <%--element.setAttribute('class', 'materialboxed');--%>
            <%--console.log(element);--%>
        <%--}--%>

        <%--function unMaterializeAll()--%>
        <%--{--%>
            <%--$( '.materialboxed' ).each(function(i, obj)--%>
            <%--{--%>
                <%--console.log('we got to the function');--%>
<%--//                $( this ).removeClass('materialboxed');--%>
            <%--});--%>
        <%--}--%>
    <%--</script>--%>

    <%--<div class="row">--%>
        <%--<c:set var="classType" value="col s12 m6 l3"/>--%>
        <%--<c:forEach var="file" items="${fileList}">--%>
            <%--<div class="${classType}" style="margin-bottom : 1.8%;">--%>
                <%--<a><img onclick="makeMaterialize(this);" data-source="${file}" src="${pageContext.request.contextPath}/images/${file}"/></a>--%>
            <%--</div>--%>
        <%--</c:forEach>--%>
    <%--</div>--%>

    <%--<div>--%>
        <%--<img--%>
                <%--class="materialboxed"--%>
                <%--width="650"--%>
                <%--src="/resumeSite/images/Whitehouse.JPG"--%>
                <%--srcset="/resumeSite/images/Whitehouse_small.JPG 200w, /resumeSite/images/Whitehouse.JPG 2000w"--%>
                <%--sizes="(max-width: 200px) 100vw, 200px" />--%>
    <%--</div>--%>


</div>
<jsp:include page="_pageSections/footer.jsp" />
