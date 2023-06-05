<%@ include file="../layout/header.jsp"%>

<div class="container">

  <form>
    <div class="form-group">
      <label for="title">Title</label>
      <input type="text" class="form-control" placeholder="Enter title" id="title">
    </div>

    <div>
      write id: <span id="id"><i>${board.id}</i></span>
    </div>
    <div class="form-group">
      <label for="content">Content:</label>
      <textarea class="form-control summernote"row="5"id="content"></textarea>
    </div>

  </form>
  <button id="btn-edit" class="btn btn-primary">edit</button>
</div>

<script>
  $('.summernote').summernote({
      tabsize: 2,
      height: 400
  });
</script>
<script src="/js/board/edit.js"></script>
<%@ include file="../layout/footer.jsp"%>
