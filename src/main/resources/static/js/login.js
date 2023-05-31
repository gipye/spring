let index = {
    init: function() {
        $("#btn-login").on("click", this.action);
    },
    action: function() {
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
        }
        $.ajax({
            type: "POST",
            url: "/signin/proc",
            data: JSON.stringify(data),
            contentType: "application/json;charset=utf-8",
            dataType: "json"
        }).done(function(res) {
            alert("Success login");
            alert(JSON.stringify(res));
            location.href="/";
        }).fail(function(err) {
            alert("Login failed");
            alert(JSON.stringify(err));
        })
    }
}
index.init();
