<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/_pageSections/navBar.jsp"/>

<div class="container">

<br/>
Javacsript


    <script>
        $(document).ready(function() {
            $.getJSON("http://ws.audioscrobbler.com/2.0/?method=user.getTopArtists&user=test&api_key=c349ab1fcb6b132ffb8d842e982458db&limit=10&format=json&callback=?", function(json) {
                var html = '';
                $.each(json.topartists.artist, function(i, item) {
                    html += "<p><a href=" + item.url + " target='_blank'>" + item.name + " - " + "Play count : " +item.playcount + "</a></p>";
                });
                $('#result').append(html);
            });
        });
    </script>
    <div id="result"></div>

</div>
<jsp:include page="/_pageSections/footer.jsp" />
