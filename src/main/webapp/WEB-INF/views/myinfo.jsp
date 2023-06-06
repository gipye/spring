<%@ include file="./layout/header.jsp" %>

<div class="container">
  <h2>Form Validation</h2>
  <p>In this example, we use <code>.was-validated</code> to indicate what's missing before submitting the form:</p>
  <form class="was-validated">
    <input type="hidden" id="id" value="${principal.user.id}" />
    <div class="form-group">
      <label for="username">Username:</label>
      <input type="text" value="${principal.user.username}" class="form-control" id="username" placeholder="Enter username" name="username" readonly>
      <div class="valid-feedback">Valid.</div>
      <div class="invalid-feedback">Please fill out this field.</div>
    </div>

    <div class="form-group">
      <label for="password">Password:</label>
      <input type="password" class="form-control" id="password" placeholder="Enter password" name="password" required>
      <div class="valid-feedback">Valid.</div>
      <div class="invalid-feedback">Please fill out this field.</div>
    </div>

    <div class="form-group">
      <label for="email">Email:</label>
      <input type="text" value="${principal.user.email}" class="form-control" id="email" placeholder="Enter email" name="email" required>
      <div class="valid-feedback">Valid.</div>
      <div class="invalid-feedback">Please fill out this field.</div>
    </div>

    <div class="form-group form-check">
      <label class="form-check-label">
        <input class="form-check-input" type="checkbox" name="remember"> I agree on blabla.
        <div class="valid-feedback">Valid.</div>
        <div class="invalid-feedback">Check this checkbox to continue.</div>
      </label>
    </div>
  </form>

  <button id="btn-update" class="btn btn-primary">Update</button>
</div>

<script type="text/javascript" src="/js/myinfo.js"></script>
<%@ include file="./layout/footer.jsp" %>
