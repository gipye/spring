let index={
    init: function() {
        $("#btn-edit").on("click", this.edit);
        $("#btn-delete").on("click", this.deleteById);
        $("#btn-reply-save").on("click", this.replySave);
    },
    edit: function() {
        alert("edit click");
    },
    deleteById: function() {
        var id = $("#id").text();

        $.ajax({
            type: "DELETE",
            url: "/board/delete/"+id,
            dataType: "json"
        }).done(function(res) {
            alert(JSON.stringify(res));
            location.href="/";
        }).fail(function(err) {
            alert("Delete failed");
        });
    },
    replySave: function() {
        let data = {
            content: $("#reply-content").val()
        };
        let boardId = $("#board-id").val();

        $.ajax({
            type: "POST",
            url: `/board/${boardId}/reply/write`,
            data: JSON.stringify(data),
            contentType: "application/json;charset=utf-8",
            dataType: "json"
        }).done(function(res) {
            alert(JSON.stringify(res));
            location.href = `/board/${boardId}`;
        }).fail(function(err) {
            alert(JSON.stringify(err));
        });
    },
    replyDelete: function(boardId, replyId) {
        $.ajax({
            type: "DELETE",
            url: `/board/${boardId}/reply/${replyId}`,
            dataType: "json"
        }).done(function(res) {
            alert(JSON.stringify(res));
            location.href = `/board/${boardId}`;
        }).fail(function(err) {
            alert(JSON.stringify(err));
        });
    }
}

index.init();
