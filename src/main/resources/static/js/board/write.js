let index = {
    init: function() {
        $("#btn-write").on("click", this.action);
    },
    action: function() {
        data = {
            title: $("#title").val(),
            content: $("#content").val()
        };
        console.log(data);

        $.ajax({
            type: "POST",
            url: "/board/write/proc",
            data: JSON.stringify(data),
            contentType: "application/json;charset=utf-8",
            dataType: "json"
        }).done(function(res) {
            alert("Complete write");
            location.href="/";
        }).fail(function(err) {
            alert(JSON.stringify(err));
        });
    }
}
index.init();
