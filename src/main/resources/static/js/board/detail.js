let index={
    init: function() {
        $("#btn-edit").on("click", this.edit);
        $("#btn-delete").on("click", this.deleteById);
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
    }
}

index.init();
