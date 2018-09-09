<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Prova</title>
		
		<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.css">
<%-- 		<link rel="stylesheet" href="<%=request.getContextPath() %>/codemirror/lib/codemirror.css"> --%>
<%-- 	    <link rel="stylesheet" href="<%=request.getContextPath() %>/codemirror/addon/hint/show-hint.css"> --%>
		<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap-iso.css">
		
	</head>
	<body>
	
<!-- 		<span class="button-checkbox"> -->
<!-- 	        <button type="button" class="btn" data-color="primary">Unchecked</button> -->
<!-- 	        <input type="checkbox" class="hidden" /> -->
<!--     	</span> -->
		
<!-- 		<button id="id-btn-css-questionario" class="btn btn-warning" data-toggle="modal" data-target="#css-form" type="button">CSS</button> -->
		
<!-- 		<div class="modal fade" id="css-form" tabindex="-1" role="dialog" aria-labelledby="searchFormLabel" aria-hidden="true"> -->
<!-- 					<div class="modal-dialog"> -->
<!-- 						<div class="modal-content"> -->
<!-- 							<div class="modal-header"> -->
<!-- 			                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button> -->
<!-- 			                    <h4 class="modal-title" id="searchFormLabel">Seleziona CSS</h4> -->
<!-- 		                	</div> -->
<!-- 		                	<div class="modal-body"> -->
<!-- 		                		<div class="container-fluid"> -->
<!-- 		                			<div class="row"> -->
<!-- 		                				<form class="form-horizontal col-xs-12 col-md-12"> -->
<!-- 		                					<fieldset class="form-group"> -->
<!-- 												<select class="form-control"> -->
<!-- 													<option>default.css</option> -->
<!-- 													<option>Nuovo CSS</option> -->
<!-- 												</select> -->
<!-- 		                                	</fieldset> -->
<!-- 		                				</form> -->
<!-- 		                			</div> -->
<!-- 		                		</div> -->
<!-- 		                	</div> -->
<!-- 		                	<div class="modal-footer"> -->
<!-- 		                    	<button type="button" class="btn btn-primary" data-dismiss="modal" data-toggle="pill" data-target="#seleziona-css" id="seleziona">Seleziona</button> -->
<!-- 		                	</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 				</div> -->
				
<!-- 				<div id="seleziona-css" class="tab-pane panel panel-default fade"> -->
<!-- 	            	<div class="modal-content"> -->
<!-- 						<div class="modal-header"> -->
<!-- 		                    <h4 class="modal-title">Stile CSS Quesionario</h4> -->
<!-- 		               	</div> -->
<!-- 	               		<br> -->
<!-- 		               	<div id="body-seleziona-css" class="container-fluid"> -->
<!-- 		               		<div class="panel-heading panelHeader"> -->
<!-- 		           				<div class="row"> -->
<!-- 		               				<div class="col-xs-6 col-md-6"> -->
<!-- 		                   				<span class="panel-title"><i class="fa fa-bars fa-fw" aria-hidden="true"></i>Editor CSS</span> -->
<!-- 		               				</div> -->
<!-- 		               				<div class="col-xs-6 col-md-6"> -->
<!-- 		                  					<button id="id-btn-seleziona-css" style="margin-left: 10px;" class="btn btn-primary pull-right showCollapse" type="button" name="btn-seleziona-css" >Salva CSS <i class="fa fa-plus-square" aria-hidden="true"></i></button> -->
<!-- 		               				</div> -->
<!-- 		           				</div> -->
<!-- 		       				</div> -->
<!-- 		               	</div> -->
<!-- 		               	<div id="nome-seleziona-css" class="panel-body panelContainer"> -->
		               	
<!-- 		               	</div> -->
<!-- 		        	</div> -->
<!-- 		        </div> -->

		<div class="bootstrap-iso">
			<button class="btn btn-warning" type="button">CSS</button>
		</div>
		
		<script src="<%=request.getContextPath() %>/js/jquery-3.1.1.js"></script>
	    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>	
	    <script src="<%=request.getContextPath() %>/js/bootstrap-dropdownhover.min.js"></script>
	    
	    <script src="<%=request.getContextPath() %>/codemirror/lib/codemirror.js"></script>
		<script src="<%=request.getContextPath() %>/codemirror/mode/xml/xml.js"></script>
		<script src="<%=request.getContextPath() %>/codemirror/mode/css/css.js"></script>
		<script src="<%=request.getContextPath() %>/codemirror/addon/hint/show-hint.js"></script>
		<script src="<%=request.getContextPath() %>/codemirror/addon/hint/css-hint.js"></script>
		<script src="<%=request.getContextPath() %>/codemirror/addon/hint/xml-hint.js"></script>
		
		<script type="text/javascript">
			$("#seleziona").on("click", function(event){
				
				var editorCSS;
				$("#nome-seleziona-css").empty();
				$("#nome-seleziona-css").append('<textarea id="id-textarea-seleziona-css"></textarea>');
				editorCSS = CodeMirror.fromTextArea($("#id-textarea-seleziona-css")[0], {
				    lineNumbers: true,
				    tabSize: 3,
				    mode: "text/css"
				});
				editorCSS.setValue("");
				
				var optionSelected = $( "select option:selected" ).text();
				
				$.ajax({
					url: "/TESI-V2/QuestionnaireServlet?action=selectedCSS&username=giu300486&optionSelected=" + optionSelected,
					type: "POST",
					dataType: "json",
				})
				.done(function( responseJson ){
					if(responseJson["selectedCSS"] == "ok"){
						editorCSS.setValue(responseJson["css"]);
						alert(editorCSS.getValue());
					}
				})
				.fail(function( xhr, status, errorThrown ) {
					alert( "Sorry, a problem : " +  errorThrown + " " + status + " " + xhr);
					console.log( "Error: " + errorThrown );
					console.log( "Status: " + status );
					console.dir( xhr );
				});
			});
		</script>
		
		<script type="text/javascript">
		$(function () {
		    $('.button-checkbox').each(function () {

		        // Settings
		        var $widget = $(this),
		            $button = $widget.find('button'),
		            $checkbox = $widget.find('input:checkbox'),
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
		        $button.on('click', function () {
		            $checkbox.prop('checked', !$checkbox.is(':checked'));
		            $checkbox.triggerHandler('change');
		            updateDisplay();
		        });
		        $checkbox.on('change', function () {
		            updateDisplay();
		        });

		        // Actions
		        function updateDisplay() {
		            var isChecked = $checkbox.is(':checked');

		            // Set the button's state
		            $button.data('state', (isChecked) ? "on" : "off");

		            // Set the button's icon
		            $button.find('.state-icon')
		                .removeClass()
		                .addClass('state-icon ' + settings[$button.data('state')].icon);

		            // Update the button's color
		            if (isChecked) {
		                $button
		                    .removeClass('btn-default')
		                    .addClass('btn-' + color + ' active');
		            }
		            else {
		                $button
		                    .removeClass('btn-' + color + ' active')
		                    .addClass('btn-default');
		            }
		        }

		        // Initialization
		        function init() {

		            updateDisplay();

		            // Inject the icon if applicable
		            if ($button.find('.state-icon').length == 0) {
		                $button.prepend('<i class="state-icon ' + settings[$button.data('state')].icon + '"></i> ');
		            }
		        }
		        init();
		    });
		});
		</script>
		
	</body>
</html>