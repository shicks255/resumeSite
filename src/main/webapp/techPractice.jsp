<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/_pageSections/navBar.jsp"/>

<script>

    function showAddAdminDialog()
    {
        $( '#addAdminUser' ).removeClass('hiddenDiv').addClass('popup');
    }

    function closeAddAdminUser()
    {
        $( '#addAdminUser' ).removeClass('popup').addClass('hiddenDiv');
    }

</script>

<div class="container">

    <h1>Tech Practice:</h1>


    Enter the store <a href="${pageContext.request.contextPath}/portal?action=form">here</a>

    <br/>
    <br/>

    Add admin portal account
    <a class="waves-effect waves-light btn" name="addAdminUserButton" id="addAdminUserButton" onclick="showAddAdminDialog();" value="Edit">here</a>



    <div id="addAdminUser" name="addAdminUser" class="hiddenDiv">
        <div class="popupContent">
            <form name="frmAddAdminUser" method="post" action="${pageContext.request.contextPath}/userRegistration?action=addAdminUser">
                <a class="waves-effect waves-light btn" name="closeAddAdminUser" id="closeAddAdminUser" onclick="closeAddAdminUser();">Close</a>
                <table>
                    <tr>
                        <td>
                            <label for="adminUserName">Username:</label>
                            <input type="text" name="adminUserName" id="adminUserName">
                        </td>
                        <td>
                            <label for="adminPassword">Password:</label>
                            <input type="text" name="adminPassword" id="adminPassword">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="adminEmail">Email:</label>
                            <input type="text" name="adminEmail" id="adminEmail">
                        </td>
                        <td>
                            <label for="adminFirstName">First Name:</label>
                            <input type="text" name="adminFirstName" id="adminFirstName">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="adminLastName">Last Name:</label>
                            <input type="text" name="adminLastName" id="adminLastName">
                        </td>
                    </tr>
                </table>
                <button class="btn waves-effect waves-light" type="submit" id="addAdminBtn" name="action">Create
                    <i class="material-icons right">forward</i>
                </button>
            </form>
        </div>
    </div>





</div>
<jsp:include page="_pageSections/footer.jsp" />
