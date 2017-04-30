

<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/mainStyle.css">
    <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/icons/ShicksLogo.ico" />
    <title>Steven M Hicks | Java Developer</title>

    <link href="${pageContext.request.contextPath}/CSS/mainStyle.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/CSS/materialize.min.css" rel="stylesheet" type="text/css">

    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta name="description" content="Steven M. Hicks - New Jersey based Website Developer and Computer Programmer, currelty using Java and Tomcat with various front end libraries...JQuery/Jquery UI, Materialize CSS, ajax & json,."/>
    <meta name="keywords" content="Steven Hicks, Steven M Hicks, Steven M. Hicks, shicks, shicks255, New Jersey, NJ, Hunterdon County, java, forge-tech, forge tech, web developer, software developer, computer programmer, programming"/>

    <script type="text/javascript" src=https://code.jquery.com/jquery-2.1.1.min.js></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/JS/materialize.min.js"></script>

    <script>
        $(document).ready(function()
        {
            $( '#j_username' ).focus();

            $(document.body).keyup(function(event)
            {
                if (event.keyCode == 27)
                {
                    $( '.popup' ).each(function(i, obj){
                        $( this ).removeClass('popup').addClass('hiddenDiv');
                    });
                    location.reload();
                }
            });

            $( '#newUserName' ).focusout(function(event)
            {
                var userNameEntered = $( '#newUserName' ).val();
                $.get('userRegistration?action=userNameAlreadyUsed&userNameEntered=' + userNameEntered,
                    function(data)
                    {
                        if (data == 'true')
                        {
                            $( '#userNameHint' ).css('display', 'inline');
                        }
                        if (data == 'false')
                        {
                            $( '#userNameHint' ).css('display', 'none');
                        }
                    });
            });

            $( '#newPassword, #newPassword2, #newFirstName, #newLastName' ).keyup(function(event)
            {
                var pass1 = $( '#newPassword' ).val();
                var pass2 = $( '#newPassword2' ).val();

                if (pass1 !== pass2)
                {
                    $('#passwordHint').css('display', 'inline');
                    $( '#registerBtn' ).addClass('disabled');
                }
                if (pass1 === pass2)
                {
                    $('#passwordHint').css('display', 'none');
                    $( '#registerBtn' ).removeClass('disabled');
                }

                if (pass1.length == 0 && pass2.length == 0)
                    $( '#registerBtn' ).addClass('disabled');

                var firstName = $( '#newFirstName' );
                var lastName = $( '#newLastName' );

                if (firstName.length == 0 && lastName == 0)
                    $( '#registerBtn' ).addClass('disabled');

            });

        });

        function registerUser()
        {
            $( '#registerNewUser' ).addClass('popup').removeClass('hiddenDiv');
        }

        function closeRegisterNewUser()
        {
            $( '#registerNewUser' ).addClass('hiddenDiv').removeClass('popup');
            location.reload();
        }

    </script>

</head>

<body>

<br/><br/>
<br/><br/>

<div class="container">

<div class="row">
    <div class="col s12">
        <div class="card blue-grey darken-1">
            <div class="card-content white-text">
                <span class="card-title">Portal Login</span>
                <form id="frmPortalLogin" action="j_security_check" method="get">
                    <table>
                        <tr>
                            <td>
                                <label for="j_username">UserName:</label>
                                <input type="text" id="j_username" name="j_username">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label for="j_password">Password:</label>
                                <input type="password" id="j_password" name="j_password">
                            </td>
                        </tr>
                    </table>
                    <button class="btn waves-effect waves-light" type="submit" name="action">Login
                        <i class="material-icons right">forward</i>
                    </button>
                </form>

                <br/>
                <br/>

                <button class="btn waves-effect waves-light" onclick="registerUser();" name="action">Register
                    <i class="material-icons right">person add</i>
                </button>
            </div>
        </div>
    </div>
</div>

<div id="registerNewUser"  class="hiddenDiv">
    <div id="divRegisterNewUser" class="popupContent">
        <a class="waves-effect waves-light btn red" id="closeRegisterUser" name="closeRegisterUser" onclick="closeRegisterNewUser();">Close</a>
        <form name="frmRegisterNewUser" id="frmRegisterNewUser" action="${pageContext.request.contextPath}/userRegistration?action=form" method="post">
            <table>
                <tr>
                    <td>
                        <label for="newUserName">Username:</label>
                        <input type="text" name="newUserName" id="newUserName"/>
                    </td>
                </tr>
                <tr id="userNameHint" style="display:none">
                    <td>NOOOOOOO</td>
                </tr>
                <tr>
                    <td>
                        <label for="newFirstName">First Name:</label>
                        <input type="text" name="newFirstName" id="newFirstName"/>
                    </td>
                    <td>
                        <label for="newLastName">Last Name:</label>
                        <input type="text" name="newLastName" id="newLastName"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="newPassword">Password;</label>
                        <input type="password" name="newPassword" id="newPassword">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="newPassword2">Re-type password;</label>
                        <input type="password" name="newPassword2" id="newPassword2">
                    </td>
                </tr>
                <tr id="passwordHint" style="display:none">
                    <td>Password do not match</td>
                </tr>
                <tr>
                    <td>
                        <label for="newEmail">Email:</label>
                        <input type="email" name="newEmail" id="newEmail">
                    </td>
                </tr>
            </table>

            <button class="btn waves-effect disabled waves-light" type="submit" id="registerBtn" name="action">Register
                <i class="material-icons right">forward</i>
            </button>
        </form>
    </div>
</div>

</div>
</body>