<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Bee Market</title>
    <script src="https://apis.google.com/js/platform.js" async defer></script>
    <meta name="google-signin-client_id"
          content="826429247341-ma24lpmujq81e5v9ddm89dq6rpg0bh01.apps.googleusercontent.com">
</head>
<body>
<h1>Welcome to BeeMarket!!!</h1>
<br/>
<div class="g-signin2" data-onsuccess="onSignIn">
</div>
<div id="status">
</div>
<script type="text/javascript">
    function onSignIn(googleUser) {
        let id_token = googleUser.getAuthResponse().id_token;
        let xhr = new XMLHttpRequest();
        xhr.open('POST', 'http://localhost:8080/BeeMarket_war_exploded/rest/login?idtoken=' + id_token);
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xhr.onload = function () {
            let result = xhr.responseText;
            if (result === "exist") {
                window.location.href = "welcome.jsp";
            } else if (result === "new") {
                window.location.href = "role.jsp";
            } else {
                document.getElementById("status").innerText = "Invalid Google Login";
            }
        };
        xhr.send();
    }

</script>
<br/>
</body>
</html>