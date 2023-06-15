<%@ include file="./layout/header.jsp" %>

<div class="container">
  <h2>Form Validation</h2>
  <p>In this example, we use <code>.was-validated</code> to indicate what's missing before submitting the form:</p>
  <form action="/auth/signin/proc" method="POST" class="was-validated">
    <div class="form-group">
      <label for="username">Username:</label>
      <input type="text" class="form-control" id="username" placeholder="Enter username" name="username" required>
      <div class="valid-feedback">Valid.</div>
      <div class="invalid-feedback">Please fill out this field.</div>
    </div>

    <div class="form-group">
      <label for="password">Password:</label>
      <input type="password" class="form-control" id="password" placeholder="Enter password" name="password" required>
      <div class="valid-feedback">Valid.</div>
      <div class="invalid-feedback">Please fill out this field.</div>
    </div>

    <button id="btn-login" class="btn btn-primary">Login</button>
    <a href="https://kauth.kakao.com/oauth/authorize?client_id=e85dcad6dd916df7f3a2133f57a72663&redirect_uri=http://localhost:8000/auth/kakao/callback&response_type=code"><img height="38px" src="/image/kakao_login_medium.png"/></a>
  </form>

</div>

<%@ include file="./layout/footer.jsp" %>
