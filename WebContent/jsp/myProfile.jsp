<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<title>Questionari On-line</title>
			<jsp:include page="${request.contextPath}/fragments/header.jsp"></jsp:include>
<!-- 		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"> -->
<!-- 	    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css"> -->
<%-- 	    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/lavish.css"> --%>
<%-- 	    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap-dropdownhover.min.css"> --%>
<%-- 	    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/navbar.css"> --%>
<%-- 	    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/home.css"> --%>
		
	    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/questionnaire.css">
<%-- 	    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/footer.css"> --%>
	    <link rel="stylesheet" href="<%=request.getContextPath() %>/codemirror/lib/codemirror.css">
	    <link rel="stylesheet" href="<%=request.getContextPath() %>/codemirror/addon/hint/show-hint.css">
	    
<%-- 	    <link rel=stylesheet href="<%=request.getContextPath() %>/codemirror/mode/xml/docs.css"> --%>
	    
	    <script type="text/javascript">
   			var context="${pageContext.request.contextPath}";
   		</script>
	    
	</head>
	
	<body>
<%-- 		<jsp:include page="${request.contextPath}/fragments/header.jsp"></jsp:include> --%>
		<div class="bootstrap-iso">
			<c:if test="${loggedUser!=null}">
				<div class="container main-container">
				   <div class="col-md-2 sidebar">
						<ul class="nav nav-pills nav-stacked">
							<li><a  id="dati-account" data-toggle="pill" href="#dati">Dati Account</a></li>
							<li><a  id ="nuovo-questionario" data-toggle="pill" href="#questionario">Crea Un Nuovo Questionario</a></li>
							<li><a  id ="i-miei-questionari" data-toggle="pill" href="#questionari">I Miei Questionari</a></li>
						</ul> 
					</div>
					<div class="col-md-10 tab-content">
						<div id="dati" class="tab-pane panel panel-default fade">
							<div class="modal-content">
								<div class="modal-header">
				                    <h4 class="modal-title">Dati Account</h4>
			                	</div>
			                	<div id="body-dati" class="panel-body panelContainer">
			                		
			                	</div>
							</div>
			            </div>
			            <div id="questionario" class="tab-pane panel panel-default fade">
			                <div class="modal-content">
								<div class="modal-header">
				                    <h4 class="modal-title">Crea Un Nuovo Questionario</h4>
			                	</div>
			                	<br>
			                	<div id="body-nuovo-questionario" class="container-fluid">
			                		<div class="panel-heading panelHeader">
			            				<div class="row">
			                				<div class="col-xs-6 col-md-6">
			                    				<span class="panel-title"><i class="fa fa-bars fa-fw" aria-hidden="true"></i>Editor Questionario</span>
			                				</div>
			                				<div class="col-xs-6 col-md-6">
		                    					<button id="id-btn-nuovo-questionario" style="margin-left: 10px;" class="btn btn-primary pull-right showCollapse" type="button" name="btn-nuovo-questionario" >Crea Questionario <i class="fa fa-plus-square" aria-hidden="true"></i></button>
			                				</div>
			            				</div>
			        				</div>
			                	</div>
			                	<div id="nome-questionario" class="panel-body panelContainer">
			                		
			                	</div>
			                	<div id="body-console" class="container-fluid">
			                		<div class="panel-heading panelHeader">
			            				<div class="row">
			                				<div class="col-xs-6 col-md-6">
			                    				<span class="panel-title"><i class="fa fa-bars fa-fw" aria-hidden="true"></i>Console</span>
			                				</div>
			            				</div>
			        				</div>
			                	</div>
			                	<div id="id-div-console" class="panel-body panelContainer">
			                		<textarea id="id-textarea-console" disabled="disabled" style="width: 100%; height: 25%;"></textarea>
			                	</div>
			                	<div class="verticalSpacer"></div> 
			                	<br>
							</div>
			            </div>
			            <div id="questionari" class="tab-pane panel panel-default fade">
			                <div class="modal-content">
								<div class="modal-header">
				                    <h4 id="h4-questionari" class="modal-title"></h4>
			                	</div>
			                	<div id="body-i-miei-questionari" class="container-fluid">
			                			
			                	</div>
							</div>
						 </div>
						 
						 
						 <div id="modifica-questionario" class="tab-pane panel panel-default fade">
			                <div class="modal-content">
								<div class="modal-header">
				                    <h4 class="modal-title">Modifica Questionario</h4>
			                	</div>
			                	<br>
			                	<div id="body-modifica-questionario" class="container-fluid">
			                		<div class="panel-heading panelHeader">
			            				<div class="row">
			                				<div class="col-xs-6 col-md-6">
			                    				<span class="panel-title"><i class="fa fa-bars fa-fw" aria-hidden="true"></i>Editor Questionario</span>
			                				</div>
			                				<div class="col-xs-6 col-md-6">
		                    					<button id="id-btn-modifica-questionario" style="margin-left: 10px;" class="btn btn-primary pull-right showCollapse" type="button" name="btn-modifica-questionario" >Modifica Questionario <i class="fa fa-plus-square" aria-hidden="true"></i></button>
			                				</div>
			            				</div>
			        				</div>
			                	</div>
			                	<div id="nome-modifica-questionario" class="panel-body panelContainer">
			                	
			                	</div>
			                	<div id="body-modifica-console" class="container-fluid">
			                		<div class="panel-heading panelHeader">
			            				<div class="row">
			                				<div class="col-xs-6 col-md-6">
			                    				<span class="panel-title"><i class="fa fa-bars fa-fw" aria-hidden="true"></i>Console</span>
			                				</div>
			            				</div>
			        				</div>
			                	</div>
			                	<div id="id-div-console" class="panel-body panelContainer">
			                		<textarea id="id-textarea-modifica-console" disabled="disabled" style="width: 100%; height: 25%;"></textarea>
			                	</div>
			                	<div class="verticalSpacer"></div> 
			                	<br>
							</div>
			            </div>
		
						<div id="modifica-css" class="tab-pane panel panel-default fade">
			            	<div class="modal-content">
								<div class="modal-header">
				                    <h4 class="modal-title">Stile CSS Quesionario</h4>
				               	</div>
			               		<br>
				               	<div id="body-modifica-css" class="container-fluid">
				               		<div class="panel-heading panelHeader">
				           				<div class="row">
				               				<div class="col-xs-6 col-md-6">
				                   				<span class="panel-title"><i class="fa fa-bars fa-fw" aria-hidden="true"></i>Editor CSS</span>
				               				</div>
				               				<div class="col-xs-6 col-md-6">
				                  				<button id="id-btn-modifica-css" style="margin-left: 10px;" class="btn btn-primary pull-right showCollapse" type="button" name="btn-modifica-css" >Salva CSS <i class="fa fa-plus-square" aria-hidden="true"></i></button>
				               				</div>
				           				</div>
				       				</div>
				               	</div>
				               	<div id="nome-modifica-css" class="panel-body panelContainer">
				               	
				               	</div>
				        	</div>
				        </div>
						 
					 	<div class="modal fade" id="css-form" tabindex="-1" role="dialog" aria-labelledby="searchFormLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
					                    <button id="id-btn-close-window-css" type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
					                    <h4 class="modal-title" id="searchFormLabel">Seleziona CSS</h4>
					                    <div id="div-delete-css" class="alert alert-success hidden">
		        							<p id="delete-css-message"></p>
		     							</div>
				                	</div>
				                	<div class="modal-body">
				                		<div class="container-fluid">
				                			<div class="row">
				                				<form class="form-horizontal col-xs-12 col-md-12">
				                					<fieldset class="form-group">
														<select id="select-css" class="form-control">
															<option>Nuovo CSS</option>
														</select>
				                                	</fieldset>
				                				</form>
				                			</div>
				                		</div>
				                	</div>
				                	<div class="modal-footer">
				                    	<div class="row">
									        <div class="col-xs-4">
									            <button id="seleziona" type="button" class="btn btn-primary" data-dismiss="modal">Seleziona</button>
									        </div>
									        <div class="col-xs-4">
									            <button id="elimina" type="button" class="btn btn-primary" data-dismiss="modal">Elimina</button>
									        </div>
									        <div class="col-xs-4">
									            <button id="modifica" type="button" class="btn btn-primary" data-dismiss="modal" data-toggle="pill" data-target="#modifica-css">Modifica</button>
									        </div>
		    							</div>
				                	</div>
								</div>
							</div>
						</div>
						
						<div class="modal fade" id="save-css-form" tabindex="-1" role="dialog" aria-labelledby="searchFormLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
					                    <button id="btn-save-close-css" type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
					                    <h4 class="modal-title" id="searchFormLabel">Salva CSS</h4>
					                    <div id="div-save-css" class="alert alert-success hidden">
		        							<p id="save-css-message"></p>
		     							</div>
				                	</div>
				                	<div class="modal-body">
				                		<div class="container-fluid">
				                			<div class="row">
				                				<form class="form-horizontal col-xs-12 col-md-12">
				                					<fieldset class="form-group">
				                						<input id="input-salva-css" type="search" name="searchBar" class="form-control" value="" placeholder="Inserisci il nome del file CSS"/>
				                                	</fieldset>
				                				</form>
				                			</div>
				                		</div>
				                	</div>
				                	<div class="modal-footer">
				                    	<button id="salva" type="button" class="btn btn-primary" data-dismiss="modal">Salva</button>
				                	</div>
								</div>
							</div>
						</div>
						
						<div class="modal fade" id="confirm-css-form" tabindex="-1" role="dialog" aria-labelledby="searchFormLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
					                    <button id="btn-confirm-close-css" type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
					                    <h4 class="modal-title" id="searchFormLabel">Vuoi sovrascrivere il file</h4>
					                    <div id="div-confirm-css" class="alert alert-success hidden">
		        							<p id="confirm-css-message"></p>
		     							</div>
				                	</div>
				                	<div class="modal-footer">
				                    	<button id="confirm-si" type="button" class="btn btn-primary" data-dismiss="modal">Si</button>
				                    	<button id="confirm-no" type="button" class="btn btn-primary" data-dismiss="modal">No</button>
				                	</div>
								</div>
							</div>
						</div>
						
			         </div>	
				</div>
			</c:if>
		</div>
		
		<script src="<%=request.getContextPath() %>/js/jquery-3.1.1.js"></script>
	    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>	
	    <script src="<%=request.getContextPath() %>/js/bootstrap-dropdownhover.min.js"></script>
	    <script type="text/javascript">
	    	var contextPath="${pageContext.request.contextPath}";
	    	var usernameAmministratore = "${loggedUser.username}";
	    </script>
	    <script src="<%=request.getContextPath() %>/js/header.js"></script>
		<script src="<%=request.getContextPath() %>/js/account.js"></script>
		<script src="<%=request.getContextPath() %>/js/updateAccount.js"></script>
<%-- 		<script src="<%=request.getContextPath() %>/js/newQuestionnaire.js"></script> --%>
		<script src="<%=request.getContextPath() %>/js/tabellaQuestionari.js"></script>
		
		<script src="<%=request.getContextPath() %>/codemirror/lib/codemirror.js"></script>
		<script src="<%=request.getContextPath() %>/codemirror/mode/xml/xml.js"></script>
		<script src="<%=request.getContextPath() %>/codemirror/mode/css/css.js"></script>
		<script src="<%=request.getContextPath() %>/codemirror/addon/hint/show-hint.js"></script>
		<script src="<%=request.getContextPath() %>/codemirror/addon/hint/css-hint.js"></script>
		<script src="<%=request.getContextPath() %>/codemirror/addon/hint/xml-hint.js"></script>

		<script src="<%=request.getContextPath() %>/js/editorQuestionnaire.js"></script>
		<script src="<%=request.getContextPath() %>/js/editorModifyQuestionnaire.js"></script>

		<script type="text/javascript">
				$.ajax({
					url: contextPath + "/AccountServlet?action=myProfile&username=" + usernameAmministratore,
					type: "POST",
					dataType : "json",
				})
				.done(function( responseJson ) {
					$.each(responseJson, function(index, css) {
						var parse = '<option>' + css + '</option>';
						var toAppend = $.parseHTML(parse);
						$("#select-css").append(toAppend);
					});
				})
				.fail(function( xhr, status, errorThrown ) {
					alert( "Sorry, a problem : " +  errorThrown + " " + status + " " + xhr);
					console.log( "Error: " + errorThrown );
					console.log( "Status: " + status );
					console.dir( xhr );
				});
		</script>
		
	</body>
</html>