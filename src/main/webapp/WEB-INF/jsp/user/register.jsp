<jsp:include page="../include/header.jsp" />

<h1>User Registration</h1>

    <form action="/user/registerSubmit" method="POST">
        Email <input type="text" name="email" id="email"><br>
        First Name <input type="text" name="firstName" id="firstName"><br>
        Last Name <input type="text" name="lastName" id="lastName"><br>
        Password <input type="text" name="password" id="password"><br>
        Confirm Password <input type="text" name="confirmPassword" id="confirmPassword"><br>
        <br>
        <button type="submit">Submit</button>
    </form>

<jsp:include page="../include/footer.jsp" />