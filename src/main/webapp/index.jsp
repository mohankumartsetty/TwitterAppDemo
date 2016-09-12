<head>
<link rel="stylesheet" type="text/css" href="css/CustomStyles.css">
<script src="javaScript/twitter.js"></script>
</head>

<body>
<div class="login-page">
  <div class="form">
Enter Login details  : <br><br>
    <form class="login-form" name="loginform" method="POST">
      <input type="text" name="username" placeholder="user name"/>
      <input type="password" name="password" placeholder="password"/>
      <button type="submit" onClick="validateLoginForm('<%=request.getContextPath() %>')">login</button>
      <p class="message">Not registered? <a href="jsp/createUser.jsp">Create an account</a></p>
    </form>
  </div>
</div>
</body>