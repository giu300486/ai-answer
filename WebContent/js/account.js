
$("#registrationForm").submit(function(e) {
    e.preventDefault();
    
    $.ajax({
        url: context+"/AccountServlet?action=registration",
        data: {
        	username: $("#username").val(),
        	password: $("#password").val(),
        	nome: $("#nome").val(),
        	cognome: $("#cognome").val(),
        	email: $("#email").val()
        },
        type: "POST",
        dataType: "json",
    })
     .done(function(responseJson) {
        registrationResponse(responseJson);
    })

    .fail(function(xhr, status, errorThrown) {
        alert("Sorry, there was a problem!");
        console.log("Error: " + errorThrown);
    });
});

$("#loginForm").submit(function(e) {
    e.preventDefault();
    
    $.ajax({
        url: context+"/AccountServlet?action=login",
        data: {
        	username: $("input[name='username']").val(),
        	password: $("input[name='password']").val()
        },
        type: "POST",
        dataType: "json",
    })

    .done(function(responseJson) {
        loginResponse(responseJson);
    })

    .fail(function(xhr, status, errorThrown) {
        alert("Sorry, there was a problem!");
        console.log("Error: " + errorThrown);
        })
});

   

function registrationResponse(responseJson) {
	if (responseJson["registrationResult"] === "ok") {
        $("#registration-auto-message").html("<strong>Congratulazioni!</strong> Registrazione effettuata con successo.")
        $("#registration-auto-close-notification").removeClass("alert-danger hidden").addClass("alert-success fade in");
        $("#registration-auto-close-notification").fadeTo(1500, 750).slideUp(500, function() {
            window.location.href=context+"/HomeServlet?action=mostraHome";
        });
    } else if (responseJson["registrationResult"] === "fail") {
        $("#registration-auto-message").html("<strong>Oops!</strong> username gi&egrave; presente.")
        $("#registration-auto-close-notification").removeClass("alert-success hidden").addClass("alert-danger fade in");
        $("#registration-auto-close-notification").fadeTo(1500, 750).slideUp(500, function() {

        });
    }
}

function loginResponse(responseJson) {
    if (responseJson["loginResult"] === "ok") {
        $("#login-auto-message").html("<strong>Login effettuato con successo!</strong>")
        $("#login-auto-close-notification").removeClass("alert-danger hidden").addClass("alert-success fade in");
        $("#login-auto-close-notification").fadeTo(1500, 750).slideUp(500, function() {
        	 window.location.href=context+"/HomeServlet?action=mostraHome";
        });

    } else if (responseJson["loginResult"] === "fail") {
        $("#login-auto-message").html("<strong>Oops!</strong> Username e/o password errati.")
        $("#login-auto-close-notification").removeClass("alert-success hidden").addClass("alert-danger fade in");
        $("#login-auto-close-notification").fadeTo(1500, 750).slideUp(500, function() {

        });
    }
}
