$("#dati-account").on("click", function(){
	
	$("#body-dati").load("updateAccount.jsp", function() {
		
		$("#accountInformationForm").change(function() {
        	$("#saveInfoButton").prop('disabled',false);
        });
		
		$("#accountInformationForm").submit(function(e) {
		    e.preventDefault();

//		    var disabledValues = this.find(':input:disabled').removeAttr('disabled');
		    $("#username").removeAttr('disabled');
		    
		    $.ajax({
		    	url: context+"/AccountServlet?action=modifyInfoProfile",
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
		    	 $("#username").attr('disabled','disabled');
		    	 profileInformationResponse(responseJson);
            	 $("#saveInfoButton").prop('disabled',true);
		    })

		    .fail(function(xhr, status, errorThrown) {
		        alert("Sorry, there was a problem!");
		        console.log("Error: " + errorThrown);
		    })
		});
		
	});
	
});

function profileInformationResponse(responseJson) {
    if (responseJson["modifyInfoProfile"] === "ok") {
        $("#profile-information-auto-message").html("<strong>Modificato con successo</strong>")
        $("#profile-information-auto-close-notification").removeClass("alert-danger hidden").addClass("alert-success fade in");
        $("#profile-information-auto-close-notification").fadeTo(1500, 750).slideUp(500, function() {
        });

    } else if (responseJson["modifyInfoProfile"] === "fail") {
        $("#profile-information-auto-message").html("<strong>Modifica non andata a buon fine</strong>")
        $("#profile-information-auto-close-notification").removeClass("alert-success hidden").addClass("alert-danger fade in");
        $("#profile-information-auto-close-notification").fadeTo(1500, 750).slideUp(500, function() {
        });
    }
}