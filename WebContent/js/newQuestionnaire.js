var idOggetto = 0;
var idDomanda = 0;
var idRisposta = 0;

var map = new Map();

$("#nuovo-questionario").on("click", function(){
		
	$("#body-nuovo-questionario").load("newQuestionnaire.jsp", function() {

		$("button[name='btn-nuovo-oggetto']").on("click", function(){
			idOggetto++;
			var parse=$.parseHTML('<div id="id-div-object' + idOggetto + '" class="row">'+
	                '<div class="col-xs-8 col-md-10">'+
	                    '<div class="row infoRow">'+
	                        '<div class="col-xs-12 col-md-4 mainInfoRow">'+
	                            '<span><strong>Nome Oggetto</strong></span>'+
	                        '</div>'+
	                        '<div class="col-xs-12 col-md-6 secondaryInfoRow">'+
	                            '<input id="input-nome-oggetto' + idOggetto + '" name="nome-oggetto" type="text" placeholder="Inserisci Nome Oggetto">'+
	                        '</div>'+
	                        '<div class="col-xs-12 col-md-2 secondaryInfoRow">'+
                            	'<button id="remove-object' + idOggetto + '" type="button" class="btn btn-danger btn-sm">Rimuovi Oggetto</button>'+
                            '</div>'+
	                    '</div>'+
	                    '<div class="row infoRow">'+
	                        '<div class="col-xs-12 col-md-4 mainInfoRow">'+
	                            '<span><strong>Classe Di Equivalenza</strong></span>'+
	                        '</div>'+
	                        '<div class="col-xs-12 col-md-2 secondaryInfoRow">'+
	                            '<input id="input-classe-equivalenza' + idOggetto + '" name="nome-classe-di-equivalenza" type="text" placeholder="Inserisci Classe Di Equivalenza">'+
	                        '</div>'+
	                   '</div>'+
	                '</div>'+
	                '<script type="text/javascript">'+
	                	'$("#remove-object' + idOggetto + '").on("click", function () {'+
		                	'$("#id-div-object' + idOggetto + '").remove();'+
		                	'$("#hr' + idOggetto + '").remove();'+
	                	'});'+
		            '</script>'+
	            '</div>'+
	        '<hr id="hr' + idOggetto +'"/>',true);
			
			$("#oggetto").append(parse);
			$("#oggetto").show();
			
		});
		
		$("button[name='btn-nuova-domanda']").on("click", function(){
			idDomanda++;
			map.set(idDomanda,[]);
			map.forEach(function(value, key) {
				alert(key + "  " + value);
			});
			
			var parse=$.parseHTML('<div id="id-div-domanda' + idDomanda + '" name="nome-div-domanda" class="row">'+
	                '<div class="col-xs-8 col-md-10">'+
	                    '<div class="row infoRow">'+
	                        '<div class="col-xs-12 col-md-4 mainInfoRow">'+
	                            '<span><strong>Domanda</strong></span>'+
	                        '</div>'+
	                        '<div class="col-xs-12 col-md-4 secondaryInfoRow">'+
	                            '<input id="input-domanda' + idDomanda + '" name="domanda" type="text" placeholder="Inserisci Domanda">'+
	                        '</div>'+
	                        '<div class="col-xs-12 col-md-2 secondaryInfoRow">'+
	                        	'<button id="aggiungi-risposta' + idDomanda + '" name="btn-aggiungi-risposta" type="button" class="btn btn-success btn-sm">Aggiungi Risposta</button>'+
                            '</div>'+
                            '<div class="col-xs-12 col-md-2 secondaryInfoRow">'+
                            	'<button id="remove-domanda' + idDomanda + '" name="btn-rimuovi-domanda" style="margin-left: 30px;" type="button" class="btn btn-danger btn-sm">Rimuovi Domanda</button>'+
                            '</div>'+
	                    '</div>'+
	                    '<div id="id-div-domanda-risposta' + idDomanda + '" name="nome-div-domanda-risposta" class="row infoRow"></div>'+
	                '</div>'+
	                '<script id="id-script-remove-domanda' + idDomanda + '" name="nome-script-remove-domanda" type="text/javascript">'+
	                
	                	'$("#remove-domanda' + idDomanda + '").on("click", function () {'+
	                		'map.delete(' + idDomanda + ');'+
	                		'map.forEach(function(value, key) {'+
	            				'alert(key + "  " + value);'+
	            			'});'+
		                	'$("#id-div-domanda' + idDomanda + '").remove();'+
			            	'$("#hr' + idDomanda + '").remove();'+
	                	'});'+
	                
		            '</script>'+
		            '<script type="text/javascript">'+
		            	'$("#aggiungi-risposta' + idDomanda + '").on("click", function () {'+
		            	
		            		'var idButton = $(this).attr("id");'+
		            		'var numId = idButton.charAt(17);'+
		            		'idRisposta++;'+
		            		'map.get(' + idDomanda + ').push(idRisposta);'+
		            		'map.forEach(function(value, key) {'+
            					'alert(key + "  " + value);'+
            				'});'+
		            		
		            		'$.ajax({'+
		            			'url: contextPath+"/AccountServlet?action=response",'+
		            			'data: {'+
		            				'idRisposta: idRisposta,'+
		            				'idDomanda: ' + idDomanda + ''+
		            			'},'+
		            			'type: "POST",'+
		            			'dataType: "json",'+
		            		'})'+
		            		'.done(function(responseJson) {'+
		            			'var codeHTML = responseJson["parse"];'+
		            			'$("#id-div-domanda-risposta" + numId).append(codeHTML);'+
		            			'$("#id-div-domanda-risposta" + numId).show();'+
		            		'})'+
		            		
		            		'.fail(function(xhr, status, errorThrown) {'+
		            			'alert("Sorry, there was a problem!");'+
		            			'console.log("Error: " + errorThrown);'+
		            		'});'+
		            	
		            	'});'+
		            '</script>'+
	            '</div>'+
	        '<hr id="hr' + idDomanda +'"/>',true);
			
			$("#domanda").append(parse);
			$("#domanda").show();
			
		});
		
		$("button[name='btn-nuovo-nome-questionario']").on("click", function(){
			$("#nome-questionario").toggle();
		});
		
		$("button[name='btn-mostra-meno-oggetto']").on("click", function(){
			$("#oggetto").toggle();
		});
		
		$("button[name='btn-mostra-meno-domanda']").on("click", function(){
			$("#domanda").toggle();
		});
		
		$("#questionnaire-information-form").submit(function(e){
			
			e.preventDefault();
			
			var questionario = {};
			
			var oggetti = [];
			var nomiOggetti = [];
			var nomiClassiDiEquivalenza = [];
			nomiOggetti = $("input[name=nome-oggetto]");
			nomiClassiDiEquivalenza = $("input[name=nome-classe-di-equivalenza]");
			
			for(var i = 0; i < $("input[name=nome-oggetto]").length; i++){
				var oggetto = {nome: nomiOggetti[i].value, classeDiEquivalenza: nomiClassiDiEquivalenza[i].value};
				oggetti.push(oggetto);
			}
			
			var domande = [];
			var domanda = {};
			var risposte = [];
			
			var inputRisposte = [];
			var inputDomande = [];
			inputRisposte = $("input[name=risposta]");
			inputDomande = $("input[name=domanda]");
			var count = 0;
			
			for(var i = 0; i < $("input[name=domanda]").length; i++){
				
				for(var j = 0, y = count; j < map.get(i+1).length; j++, y++){
					
					risposte.push(inputRisposte[y].value);
					if(j == map.get(i+1).length - 1){
						count = y + 1;
					}
					
				}
				
				domanda = {contenuto: inputDomande[i].value, risposte: risposte};
				domande.push(domanda);
				risposte = [];
			}
			
			var nome = $("#input-nome-questionario").val();
			
			var categoria = "";
			
			$("#input-categoria")
			  .change(function() {
			    categoria =  $("#input-categoria option:selected").text();
			  })
			  .trigger("change");
			
			
			questionario = {oggetti: oggetti, domande: domande, nome: nome, categoria: categoria};
			
			$.ajax({
                url: context+"/QuestionnaireServlet?action=createQuestionnaire",
                data: JSON.stringify(questionario),
                type: "POST",
                dataType: "json",
            })
            
            .done(function(responseJson) {
            	 questionnaireInformationResponse(responseJson);
            })
            
            .fail(function(xhr, status, errorThrown) {
                alert("Sorry, there was a problem!");
                console.log("Error: " + errorThrown);
            });
			
		});
		
		function questionnaireInformationResponse(responseJson) {
            if (responseJson["saveInfoQuestionnaire"] === "ok") {
                $("#profile-information-auto-message").html("<strong>Modificato con successo</strong>")
                $("#profile-information-auto-close-notification").removeClass("alert-danger hidden").addClass("alert-success fade in");
                $("#profile-information-auto-close-notification").fadeTo(1500, 750).slideUp(500, function() {
                });

            } else if (responseJson["saveInfoQuestionnaire"] === "fail") {
                $("#profile-information-auto-message").html("<strong>Modifica non andata a buon fine</strong>")
                $("#profile-information-auto-close-notification").removeClass("alert-success hidden").addClass("alert-danger fade in");
                $("#profile-information-auto-close-notification").fadeTo(1500, 750).slideUp(500, function() {
                });
            }
        }
		
	});
	
});
