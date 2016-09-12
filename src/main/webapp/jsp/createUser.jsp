
<html>
<head>
<title>User Creation Page</title>
<link rel="stylesheet" type="text/css" href="../css/CustomStyles.css">
<script src="../javaScript/twitter.js"></script>
</head>
<body>

<div class="register-form">
  <div class="form">
	Enter details to create user : <br><br>
    <form class="login-form" name="createUserForm" method="POST">
      <input type="text" name="username" placeholder="user name"/>
      <input type="password" name="password" placeholder="password"/>
      <button type="submit" onClick="validateCreateUserForm('<%=request.getContextPath() %>')">Register</button>
    </form>
  </div>
</div>
</body>
</html>