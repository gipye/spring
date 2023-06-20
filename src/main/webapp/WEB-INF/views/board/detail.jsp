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
  <hr/>

  <div class="card">
    <form>
      <input type="hidden" id="board-id" value="${board.id}"/>
      <div class="card-body"><textarea id="reply-content" class="form-control"rows="1"></textarea></div>
      <div class="card-footer"><button type="button" id="btn-reply-save" class="btn btn-primary">Write</button></div>
    </form>
  </div>
  <br/>

  <div class="card">
    <div class="card-header">Reply list</div>
    <ul id="reply-box" class="list-group">
      <c:forEach var="reply" items="${board.replys}">
      <li id="reply-${reply.id}" class="list-group-item d-flex justify-content-between">
        <div>${reply.content}</div>
        <div class="d-flex">
          <div class="font-italic">writer: ${reply.user.username} &nbsp;</div>
          <button onClick="index.replyDelete(${board.id}, ${reply.id})" class="badge">delete</button>
        </div>
      </li>
      </c:forEach>
    </ul>
  </div>
</div>

<script src="/js/board/detail.js"></script>
<%@ include file="../layout/footer.jsp"%>
