var count = 0;
var count1 = 0;
var count2 = 0;
var editorCSS;
var questionarioCSSSelezionto;

$("#i-miei-questionari").on("click", function(){
	count = 0;
	count1 = 0;
	$("#body-i-miei-questionari").load("tabellaQuestionari.jsp", function() {
		$("#h4-questionari").text("I Miei Questionari");
		$.ajax({
			url: contextPath + "/QuestionnaireServlet?action=showQuestionnaires&username=" + usernameAmministratore,
			type: "POST",
			dataType : "json",
		})
		.done(function( responseJson ) {
			$.each(responseJson, function(index, questionario) {
				var parse = '<tr> '+
								'<td class="class-td">'+ 
									'<span>' + questionario + '</span>'+ 
									'<a id="id-a-modifica-questionario" data-toggle="pill" href="#modifica-questionario" style="margin-left: 15px;" class="btn btn-info pull-right">Modifica XML</a>'+
//									'data-toggle="modal" data-target="#css-form"<button id="id-btn-modifica-questionario" style="margin-left: 15px;" class="btn btn-info pull-right" type="button">Modifica</button>'+
//									'<button id="id-btn-esegui-questionario" style="margin-left: 15px;" class="btn btn-success pull-right" type="button">Esegui</button>'+
									'<a id="id-btn-esegui-questionario" href="' + contextPath + '/QuestionnaireServlet?action=initQuestionnaire&username=' + usernameAmministratore + '&nameQuestionnaire=' + questionario + '" style="margin-left: 15px;" class="btn btn-success pull-right">Esegui</a>'+
									'<button id="id-btn-elimina-questionario" style="margin-left: 15px;" class="btn btn-danger pull-right" type="button">Elimina</button>'+
									'<button id="id-btn-css-questionario-' + questionario + '" style="margin-left: 15px;" class="btn btn-warning pull-right" type="button">CSS</button>'+
									'<button id="id-btn-checkbox-' + questionario + '" type="button" class="btn pull-right" data-color="primary">Abilita</button>'+
									'<input id="id-input-checkbox-' + questionario + '" type="checkbox" class="hidden"/>'+
								'</td>'+
							'</tr>';
				
				var toAppend = $.parseHTML(parse);
				$("#table-body-questionario").append(toAppend);
			});
		})
		.fail(function( xhr, status, errorThrown ) {
			alert( "Sorry, a problem : " +  errorThrown + " " + status + " " + xhr);
			console.log( "Error: " + errorThrown );
			console.log( "Status: " + status );
			console.dir( xhr );
		});
		
		var questionarioCSSSelezionto;
		
		$(function () {

	        // Settings
	        var $button = $("#id-btn-checkbox"),
	            $checkbox = $("#id-input-checkbox"),
	            color = $button.data('color'),
	            settings = {
	                on: {
	                    icon: 'glyphicon glyphicon-check'
	                },
	                off: {
	                    icon: 'fa fa-square-o'
	                }
	            };
	        
	        // Event Handlers
	        $(document).on('click', $("button[id^='id-btn-checkbox']"), function (event) {
	        	if(event.target.id.indexOf("id-btn-checkbox") !== -1  || event.target.nodeName === "I"){
	        		var str = event.target.id.split("-");
	        		questionario = str[str.length-1];
	        		
	        		$.ajax({
		    			url: contextPath + "/QuestionnaireServlet?action=checkUnckeckQuestionnaire&username=" + usernameAmministratore + "&nameQuestionnaire=" + questionario,
		    			type: "POST",
		    			dataType : "json",
		    		})
		    		.done(function( responseJson ) {
		    			var checkUnckeckQuestionnaire = responseJson["checkUnckeckQuestionnaire"];
		    			$("#id-input-checkbox-"+questionario).prop('checked', checkUnckeckQuestionnaire);
			        	$("#id-input-checkbox-"+questionario).triggerHandler('change');
			            updateDisplay(questionario);
		    		})
		    		.fail(function( xhr, status, errorThrown ) {
		    			alert( "Sorry, a problem : " +  errorThrown + " " + status + " " + xhr);
		    			console.log( "Error: " + errorThrown );
		    			console.log( "Status: " + status );
		    			console.dir( xhr );
		    		});
	        	}
	        });
	        
	        $("#id-input-checkbox").on('change', function (event) {
	        	if(event.target.id.indexOf("id-input-checkbox") !== -1){
	        		updateDisplay(questionario);
	        	}
	        });

	        // Actions
	        function updateDisplay(questionario) {
	            var isChecked = $("#id-input-checkbox-"+questionario).is(':checked');
	            
	            // Set the button's state
	            $("#id-btn-checkbox-"+questionario).data('state', (isChecked) ? "on" : "off");

	            // Set the button's icon
	            $("#id-btn-checkbox-"+questionario).find('.state-icon')
	                .removeClass()
	                .addClass('state-icon ' + settings[$("#id-btn-checkbox-"+questionario).data('state')].icon);

	            // Update the button's color
	            if (isChecked) {
	            	$("#id-btn-checkbox-"+questionario)
	                    .removeClass('btn-default')
	                    .addClass('btn-' + color + ' active');
	            }
	            else {
	            	$("#id-btn-checkbox-"+questionario)
	                    .removeClass('btn-' + color + ' active')
	                    .addClass('btn-default');
	            }
	        }

	        // Initialization
	        function init() {

//	            updateDisplay();
	        	
	        	$.ajax({
	    			url: contextPath + "/QuestionnaireServlet?action=checkQuestionnaire&username=" + usernameAmministratore,
	    			type: "POST",
	    			dataType : "json",
	    		})
	    		.done(function( responseJson ) {
	    			$.each(responseJson, function(questionario, abilita) {
	    				// Set the button's state
	    				$("#id-btn-checkbox-"+questionario).data('state', (abilita) ? "on" : "off");
	    				
	    				// Set the button's state
	    				$("#id-btn-checkbox-"+questionario).find('.state-icon')
		                .removeClass()
		                .addClass('state-icon ' + settings[$("#id-btn-checkbox-"+questionario).data('state')].icon);
	    				
	    				// Update the button's color
	    	            if (abilita) {
	    	            	$("#id-btn-checkbox-"+questionario)
	    	                    .removeClass('btn-default')
	    	                    .addClass('btn-' + color + ' active');
	    	            }
	    	            else {
	    	            	$("#id-btn-checkbox-"+questionario)
	    	                    .removeClass('btn-' + color + ' active')
	    	                    .addClass('btn-default');
	    	            }
	    	            
	    	            $("#id-btn-checkbox-"+questionario).prepend('<i class="state-icon ' + settings[$("#id-btn-checkbox-"+questionario).data('state')].icon + '"></i> ');
	    			});
	    		})
	    		.fail(function( xhr, status, errorThrown ) {
	    			alert( "Sorry, a problem : " +  errorThrown + " " + status + " " + xhr);
	    			console.log( "Error: " + errorThrown );
	    			console.log( "Status: " + status );
	    			console.dir( xhr );
	    		});
	        	
	        }
	        init();
	    });
		
		$("#modifica").on("click", function(event){
			$("#css-form").removeClass("active in");
			
			$("#nome-modifica-css").empty();
			$("#nome-modifica-css").append('<textarea id="id-textarea-modifica-css"></textarea>');
			editorCSS = CodeMirror.fromTextArea($("#id-textarea-modifica-css")[0], {
			    lineNumbers: true,
			    tabSize: 3,
			    mode: "text/css"
			});
			editorCSS.setValue("");
			
			var optionSelected = $( "select option:selected" ).text();
			
			$.ajax({
				url: contextPath + "/QuestionnaireServlet?action=modifyCSS&username=" + usernameAmministratore + "&optionSelected=" + optionSelected,
				type: "POST",
				dataType: "json",
			})
			.done(function( responseJson ){
				if(responseJson["modifyCSS"] == "ok"){
					editorCSS.setValue(responseJson["css"]);
				}
			})
			.fail(function( xhr, status, errorThrown ) {
				alert( "Sorry, a problem : " +  errorThrown + " " + status + " " + xhr);
				console.log( "Error: " + errorThrown );
				console.log( "Status: " + status );
				console.dir( xhr );
			});
		});
		
		$("#seleziona").on("click", function(event){
			$("#css-form").removeClass("active in");
			var optionSelected = $("select option:selected" ).text();
			
			$.ajax({
				url: contextPath + "/QuestionnaireServlet?action=selectedCSS&username=" + usernameAmministratore + "&optionSelected=" + optionSelected + "&questionarioCSSSelezionto=" + questionarioCSSSelezionto,
				type: "POST",
				dataType: "json",
			})
			.done(function( responseJson ){
				if(responseJson["selectedCSS"] == "ok"){
					$("#selected-css-message").html("<strong>Hai selezionato " + responseJson["optionSelected"] + "</strong>")
			        $("#div-select-css").removeClass("alert-danger hidden").addClass("alert-success fade in");
			        $("#div-select-css").fadeTo(1500, 750).slideUp(500, function() {
			        	
			        });
				}
			})
			.fail(function( xhr, status, errorThrown ) {
				alert( "Sorry, a problem : " +  errorThrown + " " + status + " " + xhr);
				console.log( "Error: " + errorThrown );
				console.log( "Status: " + status );
				console.dir( xhr );
			});
		});
		
		$("#btn-save-close-css").on("click", function(event){
			$("#save-css-form").removeClass("active in");
		});
		
		$("#btn-confirm-close-css").on("click", function(event){
			$("#confirm-css-form").removeClass("active in");
		});
			
		$("#confirm-no").on("click", function(event){
			$("#confirm-css-form").removeClass("active in");
			$("#save-css-form").addClass("active in");
		});
		
		$("#confirm-si").on("click", function(event){
			var nomeFileCSS = $("select option:selected" ).text();
			var contentCSS = editorCSS.getValue();
			
			$.ajax({
				url: contextPath + "/QuestionnaireServlet?action=overwriteFileCSS",
				data : {
					username : usernameAmministratore,
					contentCSS : contentCSS,
					nomeFileCSS : nomeFileCSS
				},
				type: "GET",
				dataType: "json",
			})
			.done(function( responseJson ){
				if(responseJson["overwriteFileCSS"] == "ok"){
			        $("#div-confirm-css").fadeTo(1500, 750).slideUp(500, function() {
			        	alert("Il file " + nomeFileCSS + " e' stato salvato con successo.");
			        	$("#confirm-css-form").removeClass("active in");
			        	location.reload();
			        });
				}
				else{
					$("#div-confirm-css").fadeTo(1500, 750).slideUp(500, function() {
			        	alert("Il file " + nomeFileCSS + " non e' stato salvato correttamente.");
			        	$("#confirm-css-form").removeClass("active in");
			        });
				}
			})
			.fail(function( xhr, status, errorThrown ) {
				alert( "Sorry, a problem : " +  errorThrown + " " + status + " " + xhr);
				console.log( "Error: " + errorThrown );
				console.log( "Status: " + status );
				console.dir( xhr );
			});
		});
		
		$("#salva").on("click", function(event){
			var nomeFileCSS = $("#input-salva-css").val();
			var contentCSS = editorCSS.getValue();

			if(nomeFileCSS == ""){
				$("#save-css-form").addClass("active in");
			}
			else{
				$.ajax({
					url: contextPath + "/QuestionnaireServlet?action=saveCSS",
					data : {
						username : usernameAmministratore,
						contentCSS : contentCSS,
						nomeFileCSS : nomeFileCSS
					},
					type: "GET",
					dataType: "json",
				})
				.done(function( responseJson ){
					if(responseJson["saveCSS"] == "ok"){
				        $("#div-save-css").fadeTo(1500, 750).slideUp(500, function() {
				        	alert("Il file " + nomeFileCSS + " e' stato salvato con successo.");
				        	$("#save-css-form").removeClass("active in");
				        	location.reload();
				        });
					}
					else{
						$("#div-save-css").fadeTo(1500, 750).slideUp(500, function() {
				        	alert("Non e' possibile salvare il file " + nomeFileCSS + ". Il file " + nomeFileCSS + " esiste gia.");
				        	$("#save-css-form").removeClass("active in");
				        });
					}
				})
				.fail(function( xhr, status, errorThrown ) {
					alert( "Sorry, a problem : " +  errorThrown + " " + status + " " + xhr);
					console.log( "Error: " + errorThrown );
					console.log( "Status: " + status );
					console.dir( xhr );
				});
			}
			
		});
		
		$("#elimina").on("click", function(event){
			$("#css-form").removeClass("active in");
			var nomeFileCSS = $( "select option:selected" ).text();
			
			var vuoiEliminare = confirm("Vuoi eliminare il file " + nomeFileCSS + "?");
			
			if(vuoiEliminare == true){
				$.ajax({
					url: contextPath + "/QuestionnaireServlet?action=deleteCSS&username=" + usernameAmministratore + "&nomeFileCSS=" + nomeFileCSS,
					type: "POST",
					dataType: "json",
				})
				.done(function( responseJson ){
					if(responseJson["deleteCSS"] == "ok"){
				        $("#div-delete-css").fadeTo(1500, 750).slideUp(500, function() {
				        	alert("Il file " + nomeFileCSS + " e' stato eliminato con successo.");
				        	$("#delete-css-form").removeClass("active in");
				        	location.reload();
				        });
					}
					else{
						$("#div-delete-css").fadeTo(1500, 750).slideUp(500, function() {
				        	alert("Il file " + nomeFileCSS + " non e' stato eliminato correttamente.");
				        	$("#delete-css-form").removeClass("active in");
				        });
					}
				})
				.fail(function( xhr, status, errorThrown ) {
					alert( "Sorry, a problem : " +  errorThrown + " " + status + " " + xhr);
					console.log( "Error: " + errorThrown );
					console.log( "Status: " + status );
					console.dir( xhr );
				});
			}
		});
		
		
		$("#id-btn-close-window-css").on("click", function(event){
			$("#css-form").removeClass("active in");
		});
		
		$(document).on("click", $("button[id^='id-btn-css-questionario']"), function(event){
			if(event.target.id.indexOf("id-btn-css-questionario") !== -1){
				$("#css-form").addClass("active in");
				var str = event.target.id.split("-");
	    		questionarioCSSSelezionto = str[str.length-1];
			}
		});
		
		$("#id-btn-modifica-css").on("click", function(event){
			var nomeFileCSS = $( "select option:selected" ).text();
			
			if(nomeFileCSS == "Nuovo CSS")
				$("#save-css-form").addClass("active in");
			else{
				$("#confirm-css-form").addClass("active in");
			}
//			if(nomeFileCSS == "Nuovo CSS"){
//				nomeFileCSS = prompt("Inserisci il nome del file CSS","");
//			}
//			else{
//				condition = confirm("Vuoi sovrascrivere il file " + nomeFileCSS + "?");
//			}
//			
//			$.ajax({
//				url: contextPath + "/QuestionnaireServlet?action=saveCSS&username=" + usernameAmministratore + "&contentCSS=" + contentCSS,
//				type: "POST",
//				dataType: "json",
//			})
//			.done(function( responseJson ){
//				if(responseJson["saveCSS"] == "ok"){
//					$("#save-css-message").html("<strong>Il file " + responseJson["fileCSS"] + " e' stato salvato con successo.</strong>")
//			        $("#div-select-css").removeClass("alert-danger hidden").addClass("alert-success fade in");
//			        $("#div-save-css").fadeTo(1500, 750).slideUp(500, function() {
//			        	
//			        });
//				}
//			})
//			.fail(function( xhr, status, errorThrown ) {
//				alert( "Sorry, a problem : " +  errorThrown + " " + status + " " + xhr);
//				console.log( "Error: " + errorThrown );
//				console.log( "Status: " + status );
//				console.dir( xhr );
//			});
		});
		
		$(document).on("click","#id-a-modifica-questionario", function(event){
			//QUI IL CODICE CHE CARICA L'XML DEL QUESTIONARIO PER EVENTUALI MODIFICHE
			if(count == 0){
//				alert($(this).find(".class-td").text());
				var nomeQuestionario = $(this).parent().find('span').text();
				
				$.ajax({
					url: contextPath + "/QuestionnaireServlet?action=showQuestionnaire&username=" + usernameAmministratore + "&nomeQuestionario=" + nomeQuestionario,
					type: "POST",
					dataType: "json",
				})
				.done(function( responseJson ){
					if(responseJson["showQuestionnaire"] == "ok"){
//						$("#body-i-miei-questionari").load("updateQuestionnaire.jsp", function() {
//							$("#h4-questionari").text("Questionario");
//						});
						editorModify.setValue(responseJson["questionnaire"]);
					}
				})
				.fail(function( xhr, status, errorThrown ) {
					alert( "Sorry, a problem : " +  errorThrown + " " + status + " " + xhr);
					console.log( "Error: " + errorThrown );
					console.log( "Status: " + status );
					console.dir( xhr );
				});
			}
			count++;
		});
		
		$(document).on("click","#id-btn-elimina-questionario", function(event){
//			if(count1 == 0){
				var nomeQuestionario = $(this).parent().find('span').text();
				var questionario = $(this).parent().parent();
				
				var vuoiEliminare = confirm("Vuoi eliminare il questionario " + nomeQuestionario + "?");
				
				if(vuoiEliminare == true){
					$.ajax({
						url: contextPath + "/QuestionnaireServlet?action=removeQuestionnaire&username=" + usernameAmministratore + "&nomeQuestionario=" + nomeQuestionario,
						type: "POST",
						dataType: "json",
					})
					.done(function( responseJson ){
						if (responseJson["removeQuestionnaire"] === "ok") {
							questionario.remove();
					        $("#remove-auto-message").html("<strong>Il Questionario " + nomeQuestionario + " e' stato eliminato.</strong>")
					        $("#remove-auto-close-notification").removeClass("alert-danger hidden").addClass("alert-success fade in");
					        $("#remove-auto-close-notification").fadeTo(1500, 750).slideUp(500, function() {
					        location.reload();
					        });
	//				    } else if (responseJson["removeQuestionnaire"] === "fail") {
	//				        $("#remove-auto-message").html("<strong>Non e' possibile eliminare il questionario " + nomeQuestionario + "</strong>")
	//				        $("#remove-auto-close-notification").removeClass("alert-success hidden").addClass("alert-danger fade in");
	//				        $("#remove-auto-close-notification").fadeTo(1500, 750).slideUp(500, function() {
	//	
	//				        });
					    }
					 })
					.fail(function( xhr, status, errorThrown ) {
						alert( "Sorry, a problem : " +  errorThrown + " " + status + " " + xhr);
						console.log( "Error: " + errorThrown );
						console.log( "Status: " + status );
						console.dir( xhr );
					});
				}
//			}
//			count1++;
		});
	});
});
