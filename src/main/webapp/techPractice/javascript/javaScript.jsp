<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/_pageSections/navBar.jsp"/>

<div class="container">

<br/>
    <h1>Javacsript Practice</h1>

    <br/>
    <br/>

    <button class="btn waves-effect waves-light" onclick="window.location.href = '${pageContext.request.contextPath}/techPractice?action=charts'">Charts</button>

    <%--<script>--%>
        <%--$(document).ready(function() {--%>
            <%--$.getJSON("http://ws.audioscrobbler.com/2.0/?method=user.getTopArtists&user=test&api_key=c349ab1fcb6b132ffb8d842e982458db&limit=10&format=json&callback=?",--%>
                <%--function(json)--%>
                <%--{--%>
                    <%--console.log(json);--%>
                    <%--var html = '';--%>
                    <%--$.each(json.topartists.artist,--%>
                        <%--function(i, item)--%>
                        <%--{--%>
                            <%--html += "<p><a href=" + item.url + " target='_blank'>" + item.name + " - " + "Play count : " +item.playcount + "</a></p>";--%>
                        <%--});--%>
                    <%--$('#result').append(html);--%>
                <%--});--%>
        <%--});--%>
<%--//    </script>--%>
<%--//    <div id="result"></div>--%>

    <br/>
    <br/>

    <h3>A Calculator</h3>

    <div id="calculatorBox">
        <div id="buttons">
            <div id="outputt"></div>

            <button class="calcButton" onClick="Clear()" style="display:block;min-width : 40px;">
                Clear
            </button>

            <button class="calcButton" onClick="ButtonPress('7')">
                7
            </button>

            <button class="calcButton" onClick="ButtonPress('8')" >
                8
            </button>

            <button class="calcButton" onClick="ButtonPress('9')">
                9
            </button>

            <button class="calcButton" onClick="ButtonPress('/')">
                /
            </button>

            <button class="calcButton" onClick="ButtonPress('4')">
                4
            </button>

            <button class="calcButton" onClick="ButtonPress('5')">
                5
            </button>

            <button class="calcButton" onClick="ButtonPress('6')">
                6
            </button>

            <button class="calcButton"  onClick="ButtonPress('*')">
                *
            </button>

            <button class="calcButton" onClick="ButtonPress('1')">
                1
            </button>

            <button class="calcButton" onClick="ButtonPress('2')">
                2
            </button>

            <button class="calcButton" onClick="ButtonPress('3')">
                3
            </button>

            <button class="calcButton" onClick="ButtonPress('-')">
                -
            </button>

            <button class="calcButton" style="width : 38%;" onClick="ButtonPress('0')">
                0
            </button>

            <button class="calcButton" onClick="ButtonPress('.')">
                .
            </button>

            <button class="calcButton" onClick="ButtonPress('+')">
                +
            </button>

            <button class="calcButton" onClick="ButtonPress('calc')" style="display:block;">
                =
            </button>

        </div>
    </div>
    <script>

        var first ='';
        var second ='';
        var operator ='';
        var final ='';
        var x = false;

        function Clear()
        {
            document.getElementById('outputt').innerHTML = '';
            first = '';
            second = '';
            operator = '';
            final = '';
            x = false;
        }

        function ButtonPress(n)
        {

            if (n == 'calc')
            {
                final = eval(first + operator + second);
                document.getElementById('outputt').innerHTML = first + " " + operator + " " + second + " = " + final;
            }
            else
            {
                if (x == false)
                {
                    if (n == '/' || n == '*' || n == '-' || n == '+')
                    {
                        operator = n;
                        document.getElementById('outputt').innerHTML = first + " " + operator;
                        x = true;
                    }
                    else
                    {
                        first += n;
                        document.getElementById('outputt').innerHTML = first;
                    }
                }
                else if (x == true)
                {
                    second += n;
                    document.getElementById('outputt').innerHTML = first + " " + operator + " " + second;
                }
            }
        }
    </script>
</div>
<jsp:include page="/_pageSections/footer.jsp" />
