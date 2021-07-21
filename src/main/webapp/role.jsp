<%@ page import="com.emarket.BeeMarket.model.GoogleUser" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Role</title>
    <meta name="google-signin-client_id"
          content="826429247341-ma24lpmujq81e5v9ddm89dq6rpg0bh01.apps.googleusercontent.com">
</head>
<body>
<%
    response.setHeader("Cache-Control", "no-store");
    if (session != null && session.getAttribute("googleUser") != null && session.getAttribute("user") == null) { %>
<h1>Hi, <%=((GoogleUser) session.getAttribute("googleUser")).getName()%>
</h1>
<%} else {%>
<script>
    window.location.href = "index.jsp";
</script>
<%}%>
<label for="role">Choose a Role to register:</label>
<select name="role" id="role">
    <option value="1">Seller</option>
    <option value="2">Buyer</option>
</select>
<br><br>
<input type="button" value="Register" onclick="register()">

<div id="status">
</div>
<script>
    function signOut() {
        var auth2 = gapi.auth2.getAuthInstance();
        auth2.signOut().then(function () {
            let xhr = new XMLHttpRequest();
            xhr.open('GET', 'http://localhost:8080/BeeMarket_war_exploded/rest/logout');
            xhr.onload = function () {
                window.location.href = "index.jsp";
            };
            xhr.send();
        });
    }

    function onLoad() {
        gapi.load('auth2', function () {
            gapi.auth2.init();
        });
    }

    function register() {
        var role = document.getElementById("role").value;
        let xhr = new XMLHttpRequest();
        xhr.open('POST', 'http://localhost:8080/BeeMarket_war_exploded/rest/register?role=' + role);
        xhr.onload = function () {
            let result = xhr.responseText;
            if (result === "Success") {
                window.alert("Registration successful");
                window.location.href = "welcome.jsp";
            } else {
                document.getElementById("status").innerText = "Registration not successful.";
            }
        };
        xhr.send();
    }
</script>
<a href="#" onclick="signOut();">Sign out</a>

<script src="https://apis.google.com/js/platform.js?onload=onLoad" async defer></script>
</body>
</html>
