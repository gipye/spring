let index = {
    init: function() {
        $("#btn-signup").on("click", this.action);
    },
    action: function() {
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        }
        console.log(data);
        $.ajax({
            type: "POST",
            url: "/auth/signup/proc",
            data: JSON.stringify(data),
            contentType: "application/json;charset=utf-8",
            dataType: "json"
        }).done(function(res) {
            alert("Success signup");
            alert(JSON.stringify(res));
            location.href="/";
        }).fail(function(err) {
            alert("signup failed");
            alert(JSON.stringify(err));
        })
    }
}
index.init();
