<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/_pageSections/navBar.jsp"/>

<script>
    function showAddAdminDialog()
    {
        $( '#addAdminUser' ).removeClass('hiddenDiv').addClass('popup');
    }
</script>

<div class="container">

    <h1>Tech Practice</h1>

    <button class="waves-effect waves-light btn" name="enterStoreButton" id="enterStoreButton" onclick="window.location.href = '${pageContext.request.contextPath}/portal?action=form';" value="Edit Store">
        Enter Store
    </button>

    <br/>
    <br/>

    <%--Add admin portal account--%>
    <c:if test="${!empty adminComputer}">
        <button class="waves-effect waves-light btn" name="addAdminUserButton" id="addAdminUserButton" onclick="showAddAdminDialog();" value="Edit">
            Add an admin user
            <i class="large material-icons">person_add</i>
        </button>
        <br/>
    </c:if>

    <div id="addAdminUser" name="addAdminUser" class="hiddenDiv">
        <div class="popupContent">
            <div class="popupHeader">
                <span style="margin: auto;">Upload A File</span>
                <i class="small material-icons closeIcon" style="cursor:pointer" onclick="closePopups();">close</i>
            </div>
            <div class="popupContainer">
                <form name="frmAddAdminUser" method="post" action="${pageContext.request.contextPath}/userRegistration?action=addAdminUser">
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
                <br/>
            </div>
        </div>
    </div>

</div>
<jsp:include page="_pageSections/footer.jsp" />
