let index = {
    init: function() {
        $("#btn-edit").on("click", this.action);
    },
    action: function() {
        var id = $("#id").text();
        let data = {
            title: $("#title").val(),
            content: $("#content").val()
        };
        console.log(data);

        $.ajax({
            type: "PUT",
            url: "/board/edit/"+id,
            data: JSON.stringify(data),
            contentType: "application/json;charset=utf-8",
            dataType: "json"
        }).done(function(res) {
            alert("Complete edit");
            alert(JSON.stringify(res));
            location.href="/";
        }).fail(function(err) {
            alert("edit failed.");
            alert(JSON.stringify(err));
        });
    }
}

index.init();
