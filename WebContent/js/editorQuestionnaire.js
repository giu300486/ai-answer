var xml = '<?xml version="1.0" encoding="UTF-8" standalone="no"?>\n\n'+
		  '<tns:questionario nome="" abilita="false" css="" xmlns:tns="http://www.tesi.org/questionario" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://www.tesi.org/questionario ../../xsd/questionario.xsd ">\n\n'+
		  '\t<tns:classeAppartenenza nome="C1">\n\n'+
		  '\t\t<tns:oggetto nome="O1"/>\n\n'+
		  '\t</tns:classeAppartenenza>\n\n'+
		  '\t<tns:domanda codiceDomanda="D1" precedenza="0">\n\n'+
		  '\t\t<tns:contenutoDomanda>contenutoDomanda</tns:contenutoDomanda>\n\n'+
		  '\t\t<tns:risposta peso="0">\n\n'+
		  '\t\t\t<tns:contenutoRisposta>contenutoRisposta1</tns:contenutoRisposta>\n\n'+
		  '\t\t\t<tns:classeAppartenenza nome="C1"/>\n\n'+
		  '\t\t</tns:risposta>\n\n'+
		  '\t\t<tns:risposta peso="0">\n\n'+
		  '\t\t\t<tns:contenutoRisposta>contenutoRisposta2</tns:contenutoRisposta>\n\n'+
		  '\t\t\t<tns:classeAppartenenza nome="C1"/>\n\n'+
		  '\t\t</tns:risposta>\n\n'+
		  '\t</tns:domanda>\n\n'+
		  '</tns:questionario>';
var editor;
$(document).on("click", "#nuovo-questionario", function(){
	$("#nome-questionario").empty();
	$("#nome-questionario").append('<textarea id="id-textarea-editor"></textarea>');
	editor = CodeMirror.fromTextArea($("#id-textarea-editor")[0], {
	    lineNumbers: true,
	    tabSize: 3,
	});
	editor.setValue(xml);
});

$(document).on("click", "#id-btn-nuovo-questionario", function(){
	$.ajax({
        url: context+"/QuestionnaireServlet?action=createQuestionnaire&usernameAmministratore=" + usernameAmministratore,
        data: {
        	fileXml: editor.getValue()
        },
        type: "POST",
        dataType: "json",
    })
    
    .done(function(responseJson) {
    	//QUI IL CODICE CHE VISUALIZZA EVENTUALI ERRORI SULLA CONSOLE O UN MESSAGGIO CHE 
    	//TUTTO E' ANDATO A BUON FINE
    	$("#id-textarea-console").val("");
    	$.each(responseJson, function(index, error) {
    		if($("#id-textarea-console").val() === "")
    			$("#id-textarea-console").val(error);
    		else
    			$("#id-textarea-console").val($("#id-textarea-console").val() + "\n\n" + error);
    		if(error == "Il Questionario e' stato creato con successo")
    			location.reload();
    	});
    })
    
    .fail(function(xhr, status, errorThrown) {
        alert("Sorry, there was a problem!");
        console.log("Error: " + errorThrown);
    });
});