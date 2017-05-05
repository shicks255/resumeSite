<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="album" type="com.steven.hicks.entities.store.items.MusicAlbum" scope="request"/>

<jsp:include page="/_pageSections/portalNavBar.jsp"/>



<div class="container">

    Music Album

</div>


<jsp:include page="/_pageSections/portalFooter.jsp" />
