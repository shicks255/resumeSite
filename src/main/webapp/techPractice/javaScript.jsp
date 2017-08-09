<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/_pageSections/navBar.jsp"/>

<head>
    <style>
        #calculatorBox {border : 1px solid black;
            height : 50%;
            width : 25%;
            border-radius : 10px;
            background-color : grey;}
        #buttons {width : auto;
            font-size : 85%;}

        .calcButton {border : 1px solid black;
            height : 7.6%;
            width : 15%;
            margin : 3%;
            border-radius : 5px;
            text-align : center;
            display : inline-block;
        }

        #outputt {width : 80%;
            margin-left : 5%;
            margin-right : 5%;
            margin-top : 5%;
            border : 2px solid black;
            height : 5%;
            background-color : white;
            padding : 5px;}

        button {box-shadow : 2px 2px 2px black;}

        button:active {transform : translate(2px, 1px);}

    </style>
</head>

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

    <br/>
    <br/>
    <br/>

    <html>

    <head>



    </head>

    <body>



    <div id="calculatorBox">

        <div id="buttons">

            <div id="outputt">
            </div>

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


    </body>

</div>
<jsp:include page="/_pageSections/footer.jsp" />
