<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<title></title>
		
<!-- 		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"> -->
<!-- 	    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css"> -->
<%-- 	    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/lavish.css"> --%>
<%-- 	    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap-dropdownhover.min.css"> --%>
<%-- 	    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/navbar.css"> --%>
<%-- 	    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/home.css"> --%>
<%-- 	    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/footer.css"> --%>
		
		<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap-iso.css">
	    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/font-awesome-iso.css">
	    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/lavish-iso.css">
	    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap-dropdownhover.min-iso.css">
	    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/navbar-iso.css">
	    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/home-iso.css">
		
<%-- 		<jsp:include page="${request.contextPath}/fragments/header.jsp"></jsp:include> --%>
<%-- 		<link rel="stylesheet" href="<%=request.getContextPath() %>/xml/${loggedUser.username}/${optionSelected}"> --%>
		<link rel="stylesheet" href="${pathCSS}">

	</head>
	
	<body>
		
		<div class="bootstrap-iso">
			<div class="myContainer">
				 <nav class="navbar navbar-inverse navbar-fixed-top" >
				 	 <div class="container-fluid">
				 	 	<div class="navbar-header">
	                    	<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar3">
		                        <span class="sr-only">Toggle navigation</span>
		                        <span class="icon-bar"></span>
		                        <span class="icon-bar"></span>
		                        <span class="icon-bar"></span>
	                    	</button>
	                    	<a class="navbar-brand" href="<%=request.getContextPath() %>/HomeServlet?action=mostraHome"><img src="<%=request.getContextPath() %>/img/Logo.png" alt="Logo"></a>
	                	</div>
	                	<div id="navbar3" class="navbar-collapse collapse" data-hover="dropdown">
	                		 <ul class="nav navbar-nav navbar-right">
	                		 	<c:if test="${loggedUser==null}">
	                		 		<li id="loginDropdown" class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">Sign in <span class="glyphicon glyphicon-log-in"></span></a>
	                		 			<div id="loginPanel" class="dropdown-menu" role="menu">
	                		 				<div>
	                		 					<fieldset>
	                		 						<div class="panel-body">
<!-- 	                		 							<form id="loginForm" action='' method="POST" role="form"> -->
	                		 								<div class="form-group">
	                                                    		<input class="form-control" placeholder="Username" name="username" type="text" autofocus="" value="" required>
	                                                		</div>
			                                                <div class="form-group">
			                                                    <input class="form-control" placeholder="Password" name="password" type="password" value="" required>
			                                                </div>
			                                                <div id="login-auto-close-notification" class="alert alert-success hidden">
			                                                    <p id="login-auto-message"></p>
			                                                </div>
	                                                		<button id="submitLogin" type="button" class="btn btn-sm btn-primary">Login</button>
	                                                		<a id="register-button" class="pull-right btn btn-sm btn-danger" href="<%=request.getContextPath()%>/jsp/registration.jsp">Register</a>
<!-- 	                		 							</form> -->
	                		 						</div>
	                		 					</fieldset>
	                		 				</div>
	                		 			</div>
	                		 		</li>
	                		 	</c:if>
	                		 	<c:if test="${loggedUser!=null}">
	                		 		<li class="dropdown">
	                		 			<a id="loggedUser" href="#" class="navbar-link dropdown-toggle" data-toggle="dropdown" aria-expanded="false">Benvenuto, ${loggedUser.nome} <span class="caret"></span></a>
	                		 			<ul class="dropdown-menu" role="menu">
	                		 				<li class="text-center"><a href="<%=request.getContextPath() %>/jsp/myProfile.jsp">Il Mio Profilo</a></li>
	                		 				<li id="logout" class="text-center"><a href="<%=request.getContextPath()%>/AccountServlet?action=logout" class="btn btn-danger btn-sm">Logout</a></li>
	                		 			</ul>
	                		 		</li>
	                		 	</c:if>
	                		 	<li>
<!-- 	                		 	data-toggle="modal" data-target="#searchForm" -->
		                        	<button id="searchToggle" style="margin-top:20px;" class="btn btn-primary"><i id="searchIcon" class="glyphicon glyphicon-search"></i></button>
		                        </li>
	                		 </ul>
	                	</div>
				 	 </div>
				 </nav>
			</div>
			<div style="margin-bottom: 100px; "></div>
			<div class="modal fade" id="searchForm" tabindex="-1" role="dialog" aria-labelledby="searchFormLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
		                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
		                    <h4 class="modal-title" id="searchFormLabel">Cerca Questionario</h4>
	                	</div>
	                	<div class="modal-body">
	                		<div class="container-fluid">
	                			<div class="row">
	                				<form class="form-horizontal col-xs-12 col-md-12">
	                					<fieldset class="form-group">
	                                    	<input type="search" name="searchBar" class="form-control" value="" />
	                                	</fieldset>
	                				</form>
	                			</div>
	                		</div>
	                	</div>
	                	<div class="modal-footer">
	                    	<button type="button" class="btn btn-primary" data-dismiss="modal" id="cerca">Cerca</button>
	                	</div>
					</div>
				</div>
			</div>
		</div>
		
		
		
		
		<div id="content" class="container">
			<div class="row">
				<div class="col-md-6">
					<c:if test="${output == null}">
						<section id="section-domanda">
							<div class="panel-heading panelHeader">
								<div class="row">
									<div class="col-xs-9 col-md-9">
		                    			<span class="panel-title"><i class="fa fa-bars fa-fw" aria-hidden="true"></i>Domanda n° ${count}</span>
		                			</div>
								</div>
							</div>
							<div id="id-div-domanda" class="panel-body panelContainer">
					        	<div class="row">
				      				<div class="col-xs-8 col-md-10">
										${domandaCorrente.contenuto}
				             		</div>
				           		</div>
				        	</div>
						</section>
						<section id="responses" style="visibility: visible;">
							<div class="panel-heading panelHeader">
								<div class="row">
									<div class="col-xs-9 col-md-9">
		                    			<span class="panel-title"><i class="fa fa-bars fa-fw" aria-hidden="true"></i>Risposte</span>
		                			</div>
								</div>
							</div>
							<div id="id-div-domanda" class="panel-body panelContainer">
								<c:forEach var="risposta" items="${domandaCorrente.risposte}">
									<div class="row">
										<div class="col-xs-8 col-md-10">
											<a id="${domandaCorrente.codiceDomanda}" href="#" class="btn btn-primary" <%--style="border-radius: 10px; width: 200px; position: absolute;top: <%=base %>px;left: 370px;"--%>>${risposta}</a>
										</div>
									</div>
								</c:forEach>
							</div>
						</section>
					</c:if>
					<c:if test="${output != null}">
						<section id="output">
							<div class="panel-heading panelHeader">
								<div class="row">
									<div class="col-xs-9 col-md-9">
		                    			<span class="panel-title"><i class="fa fa-bars fa-fw" aria-hidden="true"></i>Oggetti consigliati</span>
		                			</div>
								</div>
							</div>
							<div id="id-div-output" class="panel-body panelContainer">
								<ul id="list-sessions">
									<c:forEach var="oggetto" items="${output}">
										<li><span class="guessed_item blue">${oggetto}</span></li>
									</c:forEach>
								</ul>
							</div>
						</section>
<!-- 						<section id="infos-area"> -->
<!-- 							<div id="infos-area-title"> -->
<!-- 								<h2 class="titre-home">Oggetti consigliati</h2> -->
<!-- 							</div> -->
<!-- 							<div id="infos-area-content"> -->
<!-- 								<ul id="list-sessions"> -->
<%-- 									<c:forEach var="oggetto" items="${output}"> --%>
<%-- 										<li><span class="guessed_item blue">${oggetto}</span></li> --%>
<%-- 									</c:forEach> --%>
<!-- 								</ul> -->
<!-- 							</div> -->
<!-- 							<div id="infos-area-footer"> -->
								
<!-- 							</div> -->
<!-- 						</section> -->
					</c:if>
				</div>
				<div class="col-md-6">
					<section id="section-storico-domande">
						<div class="panel-heading panelHeader">
		            		<div class="row">
		                		<div class="col-xs-9 col-md-9">
		                    		<span class="panel-title"><i class="fa fa-bars fa-fw" aria-hidden="true"></i>Storico delle domande</span>
		                		</div>
		                		<div class="col-xs-3 col-md-3">
		                   			<button id="id-btn-storico-delle-domande" class="btn btn-primary pull-right showCollapse" type="button" name="btn-storico-delle-domande" data-target="#storico-delle-domande" data-toggle="collapse" aria-expanded="false"><i class="fa fa-plus-square" aria-hidden="true"></i></button>
		                		</div>
		            		</div>
		        		</div>
				        <div id="storico-delle-domande" class="panel-body panelContainer collapse">
				        	<div id="id-div-storico-delle-domande" class="row">
			      				<div class="col-xs-8 col-md-10">
			      					<c:forEach var="memento" items="${mementoStack}">
			      						<c:if test="${memento.domandaCorrente.contenuto != null}">
					      			  		<div id="id-div-infoRow" class="row infoRow">
					      			  			<a id="${memento.domandaCorrente.codiceDomanda}" href="#" class="btn btn-primary a-storico-domanda">${memento.domandaCorrente.contenuto}</a>
					             			</div>
				             			</c:if>
			             			</c:forEach>
			             		</div>
			           		</div>
				        </div>
				        <div class="verticalSpacer"></div> 
					</section>
				</div>
			</div>
		</div>

		<script src="<%=request.getContextPath() %>/js/jquery-3.1.1.js"></script>
	    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>	
	    <script src="<%=request.getContextPath() %>/js/bootstrap-dropdownhover.min.js"></script>
	    <script src="<%=request.getContextPath() %>/js/header.js"></script>
		<script src="<%=request.getContextPath() %>/js/account.js"></script>
	    <script type="text/javascript">
	    	var contextPath = "${pageContext.request.contextPath}";
	    	var usernameAmministratore = "${loggedUser.username}";
	    	var nameQuestionnaire = "${nameQuestionnaire}"
	    	
	    	if(usernameAmministratore == "")
	    		usernameAmministratore = "${username}";
	    	
// 	    	var count = "${count}";
// 	    	if(count <= 0)
// 	    		$("#id-div-correct a").hide();
// 	    	else
// 	    		$("#id-div-correct a").show();
    		
	    	$(document).on("click", "section a", function(event){
//     			$("#id-div-correct a").show();
// 				var id = $(this).attr("id");
    			$(this).attr("href",contextPath + "/QuestionnaireServlet?action=response&username=" + usernameAmministratore + "&nameQuestionnaire=" + nameQuestionnaire + "&contentResponse=" + $(this).text() /*+ "&id=" + id + "&count=" + count*/);
    		});
// 	    	$(document).on("click", "#id-div-correct a", function(event){
// 	    		$(this).attr("href", contextPath + "/QuestionnaireServlet?action=correct&count=" + count);
// 	    	});
	    	$(document).on("click", "#id-div-infoRow a", function(event){
	    		var id = $(this).attr("id");
	    		$(this).attr("href", contextPath + "/QuestionnaireServlet?action=restoreStoricoDomande"/*&count=" + count*/ + "&id=" + id);
	    	});
	    </script>
	    <script type="text/javascript">
	    $("#submitLogin").on("click", function(e) {
		        $.ajax({
		            url: contextPath+"/AccountServlet?action=login",
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
		            });
		    });
		    
		    function loginResponse(responseJson) {
		        if (responseJson["loginResult"] === "ok") {
		            $("#login-auto-message").html("<strong>Login effettuato con successo!</strong>")
		            $("#login-auto-close-notification").removeClass("alert-danger hidden").addClass("alert-success fade in");
		            $("#login-auto-close-notification").fadeTo(1500, 750).slideUp(500, function() {
		            	 window.location.href=contextPath+"/HomeServlet?action=mostraHome";
		            });

		        } else if (responseJson["loginResult"] === "fail") {
		            $("#login-auto-message").html("<strong>Oops!</strong> Username e/o password errati.")
		            $("#login-auto-close-notification").removeClass("alert-success hidden").addClass("alert-danger fade in");
		            $("#login-auto-close-notification").fadeTo(1500, 750).slideUp(500, function() {

		            });
		        }
		    }
		    
	    </script>
		<script type="text/javascript">
    		$("#searchToggle").on("click", function(event){
    			$("#searchForm").addClass("active in");
    			$("#searchForm").css("display","block");
    		});
    		
    		$("#cerca").on("click", function(event){
    			$("#searchForm").removeClass("active in");
    			$("#searchForm").css("display","none");
    			
    			window.location.href=contextPath+"/HomeServlet?action=cercaQuestionario&wordMatch="+$("input[name='searchBar']").val();
    			
    		});
    		
    		$(".close").on("click", function(event){
    			$("#searchForm").removeClass("active in");
    			$("#searchForm").css("display","none");
    		});
		</script>
	</body>
</html>