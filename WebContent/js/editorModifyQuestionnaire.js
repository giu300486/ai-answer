var editorModify;
var nomeQuestionario;
$(document).on("click", "#id-a-modifica-questionario", function(){
	nomeQuestionario = $(this).parent().find('span').text();
	$("#nome-modifica-questionario").empty();
	$("#nome-modifica-questionario").append('<textarea id="id-textarea-modifica-editor"></textarea>');
	editorModify = CodeMirror.fromTextArea($("#id-textarea-modifica-editor")[0], {
	    lineNumbers: true,
	    tabSize: 3,
	});
	editorModify.setValue("");
});

$(document).on("click", "#id-btn-modifica-questionario", function(){
	$.ajax({
        url: context+"/QuestionnaireServlet?action=modifyQuestionnaire&usernameAmministratore=" + usernameAmministratore,
        data: {
        	fileXml: editorModify.getValue(),
        	nomeQuestionario: nomeQuestionario
        },
        type: "POST",
        dataType: "json",
    })
    
    .done(function(responseJson) {
    	//QUI IL CODICE CHE VISUALIZZA EVENTUALI ERRORI SULLA CONSOLE O UN MESSAGGIO CHE 
    	//TUTTO E' ANDATO A BUON FINE
    	$("#id-textarea-modifica-console").val("");
    	$.each(responseJson, function(index, error) {
    		if($("#id-textarea-modifica-console").val() === "")
    			$("#id-textarea-modifica-console").val(error);
    		else
    			$("#id-textarea-modifica-console").val($("#id-textarea-modifica-console").val() + "\n\n" + error);
    		if(error == "Il Questionario e' stato modificato con successo")
    			location.reload();
    	});
    })
    
    .fail(function(xhr, status, errorThrown) {
        alert("Sorry, there was a problem!");
        console.log("Error: " + errorThrown);
    });
});