<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp" />

<c:if test="${empty form.id}">
    <h1>User Registration</h1>
</c:if>

<c:if test="${not empty form.id}">
    <h1>User Edit</h1>
</c:if>

    <form action="/user/registerSubmit" method="POST">
        <input type="hidden" name="id" value="${form.id}">
        Email <input type="text" name="email" id="email" value="${form.email}"><br>
        First Name <input type="text" name="firstName" id="firstName" value="${form.firstName}"><br>
        Last Name <input type="text" name="lastName" id="lastName" value="${form.lastName}"><br>
        Password <input type="text" name="password" id="password" value="${form.password}"><br>
        Confirm Password <input type="text" name="confirmPassword" id="confirmPassword" value="${form.password}"><br>
        <br>
        <button type="submit">Submit</button>
    </form>

<jsp:include page="../include/footer.jsp" />