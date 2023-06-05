<%@ include file="../layout/header.jsp"%>

<div class="container">

  <div class="form-group">
    <label for="title">Title</label>
    <h3>${board.title}<h3>
  </div>

  <div>
    write id: <span id="id"><i>${board.id}</i></span>
    writer: <span><i>${board.user.username}</i></span>
  </div>

  <div>
    <label for="content">Content:</label>
    <div>${board.content}</div>
  </div>

  <c:if test="${board.user.id == principal.user.id}">
    <a href="/board/edit/${board.id}" id="btn-edit"class="btn btn-warning">edit</a>
    <button id="btn-delete"class="btn btn-danger">delete</button>
  </c:if>
  <button class="btn btn-secondary"onclick="history.back()">back</button>
</div>

<script src="/js/board/detail.js"></script>
<%@ include file="../layout/footer.jsp"%>
