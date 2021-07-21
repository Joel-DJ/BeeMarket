<%@ page import="com.emarket.BeeMarket.model.AppUser" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bee Market</title>
    <meta name="google-signin-client_id"
          content="826429247341-ma24lpmujq81e5v9ddm89dq6rpg0bh01.apps.googleusercontent.com">
</head>
<body>
<%
    response.setHeader("Cache-Control", "no-store");
    if (session != null && session.getAttribute("user") != null) { %>
<h1>Welcome,<%=((AppUser) session.getAttribute("user")).getRoleEnumName().getRoleName()%>
    &lt;<%=((AppUser) session.getAttribute("user")).getUserName()%>&gt; </h1>
<%} else {%>
<script>
    window.location.href = "index.jsp";
</script>
<%}%>
<input type="button" value="Change Role" onclick="changeRole()">
<div id="status">
</div>
<script>
    function signOut() {
        var auth2 = gapi.auth2.getAuthInstance();
        auth2.signOut().then(function () {
            let xhr = new XMLHttpRequest();
            xhr.open('GET', 'http://localhost:8080/BeeMarket_war_exploded/rest/logout');
            xhr.onload = function () {
                console.log('Sign out ' + xhr.responseText);
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

    function changeRole() {
        let xhr = new XMLHttpRequest();
        xhr.open('POST', 'http://localhost:8080/BeeMarket_war_exploded/rest/changeRole');
        xhr.onload = function () {
            let result = xhr.responseText;
            if (result === "Success") {
                window.alert("Role Changed successfully.");
                window.location.href = "welcome.jsp";
            } else {
                document.getElementById("status").innerText = "Role Change not successful.";
            }
        };
        xhr.send();
    }
</script>
<a href="#" onclick="signOut();">Sign out</a>

<script src="https://apis.google.com/js/platform.js?onload=onLoad" async defer></script>
</body>
</html>
