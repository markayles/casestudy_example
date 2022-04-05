<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp"/>

<h1>Search</h1>

<form action="/user/search" method="GET">
    <label for="searchFirstName">First Name: </label>
    <input type="text" name="searchFirstName" id="searchFirstName">
    <button type="submit">Search</button> ${test}
</form>

<table class="table">
    <tr>
        <th>Email</th>
        <th>First Name</th>
        <th>Last Name</th>
    </tr>
    <c:forEach items="${users}" var="user">
        <tr scope="row">
            <td>${user.email}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
        </tr>
    </c:forEach>
</table>


<jsp:include page="../include/footer.jsp"/>