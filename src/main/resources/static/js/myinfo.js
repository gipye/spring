let index = {
    init: function() {
        $("#btn-update").on("click", this.action);
    },
    action: function() {
        let data = {
            id: $("#id").val(),
            password: $("#password").val(),
            email: $("#email").val()
        }
        console.log(data);

        $.ajax({
            type: "PUT",
            url: "/myinfo/update",
            data: JSON.stringify(data),
            contentType: "application/json;charset=utf-8",
            dataType: "json"
        }).done(function(res) {
            alert("Complete edit");
            location.href="/";
        }).fail(function(err) {
            alert("Edit failed.");
            alert(JSON.stringify(data));
        });
    }
}

index.init();
