<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp"/>

<h1>Search</h1>

<form action="/user/search" method="GET">
    <div class="row">
        <div class="alert alert-danger" role="alert" id="errorBox">
            ${searchError}
        </div>
    </div>
    <div class="row">
        <label for="searchFirstName" class="col-1 col-form-label" >First Name: </label>
        <div class="col-3">
            <input type="text" class="form-control" name="searchFirstName" id="searchFirstName" value="${searchTerm}">
        </div>
        <div class="col-1">
            <button type="submit" class="btn btn-primary">Search</button>
        </div>
    </div>
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

<script>
    let errorMessage = "${searchError}";
    let errorBox = $('#errorBox');

    if(errorMessage == ""){
        errorBox.hide();
    }
</script>

<jsp:include page="../include/footer.jsp"/>