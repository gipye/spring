<%@ include file="./layout/header.jsp" %>

<div class="container">

  <c:forEach var="board" items="${boards.content}">
    <div class="card m-3">
      <div class="card-body">
        <h4 class="card-title">${board.title}</h4>
        <a href="#" class="btn btn-primary">See Pro</a>
      </div>
    </div>
  </c:forEach>

  <ul class="pagination">
    <li class="page-item"><a class="page-link"href="?page=0">First</a></li>
    <c:set var="page-size" value="${boards.size}"/>
      <% int pageSize = (int)pageContext.getAttribute("page-size");
      for(int i = 0; i <= pageSize; i++) { 
          pageContext.setAttribute("i", i); %>
          <li class="page-item"><a class="page-link"href="?page=${i}">${i}</a></li>
      <% } %>
    <li class="page-item"><a class="page-link"href="?page=${boards.size}">Last</a></li>
  </ul>

</div>

<%@ include file="./layout/footer.jsp" %>
