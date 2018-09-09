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
	
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
	    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css">
	    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/lavish.css">
	    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap-dropdownhover.min.css">
	    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/navbar.css">
	    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/home.css">
	    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/footer.css">
		<link rel="stylesheet" href="<%=request.getContextPath() %>/css/motore.css">
	    
	    <script type="text/javascript">
   			var context="${pageContext.request.contextPath}";
   		</script>
	</head>
	
	<body>
		<jsp:include page="${request.contextPath}/fragments/header.jsp"></jsp:include>
		
		<div id="main" role="main">
			<div id="content" class="container">
				<c:if test="${output == null}">
	<!-- 					<div id="id-div-correct"> -->
	<!-- 						<a href="#" class="btn btn-primary" style="width: 200px; position: absolute;top: 50px;left: 100px; border-radius: 10px;">Correggi</a> -->
	<!-- 					</div> -->
	<!-- 					<div id="questionNo" style="visibility: visible;">Domanda n° -->
	<%-- 						<span id="nqp">${count}</span> --%>
	<!-- 					</div> -->
	<!-- 					<div id="bulle-outer"> -->
	<!-- 						<div id="bulle-inner" style="display: inline-block;"> -->
	<%-- 							${domandaCorrente.contenuto} --%>
	<!-- 						</div> -->
	<!-- 					</div> -->
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
						<% int base = 255; %>
						<c:forEach var="risposta" items="${domandaCorrente.risposte}">
							<div class="response">
								<a id="${domandaCorrente.codiceDomanda}" href="#" class="btn btn-primary" style="border-radius: 10px; width: 200px; position: absolute;top: <%=base %>px;left: 370px;">${risposta}</a>
							</div>
							<% base = base + 50; %>
						</c:forEach>
					</section>
				</c:if>
				<c:if test="${output != null}">
					<section id="infos-area">
						<div id="infos-area-title">
							<h2 class="titre-home">Oggetti consigliati</h2>
						</div>
						<div id="infos-area-content">
							<ul id="list-sessions">
								<c:forEach var="oggetto" items="${output}">
									<li><span class="guessed_item blue">${oggetto}</span></li>
								</c:forEach>
							</ul>
						</div>
						<div id="infos-area-footer">
							
						</div>
					</section>
				</c:if>
<!-- 				<section id="genio"> -->
<%-- 					<img id="genio-img" src="<%=request.getContextPath() %>/img/Gandalf.png" class="img-responsive"> --%>
<!-- 				</section> -->
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
		<script src="<%=request.getContextPath() %>/js/jquery-3.1.1.js"></script>
	    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>	
	    <script src="<%=request.getContextPath() %>/js/bootstrap-dropdownhover.min.js"></script>
	    <script type="text/javascript">
	    	var contextPath = "${pageContext.request.contextPath}";
	    	var usernameAmministratore = "${loggedUser.username}";
	    	var nameQuestionnaire = "${nameQuestionnaire}"
	    	
// 	    	var count = "${count}";
// 	    	if(count <= 0)
// 	    		$("#id-div-correct a").hide();
// 	    	else
// 	    		$("#id-div-correct a").show();
    		
	    	$(document).on("click", "section a", function(event){
//     			$("#id-div-correct a").show();
				var id = $(this).attr("id");
    			$(this).attr("href",contextPath + "/QuestionnaireServlet?action=response&username=" + usernameAmministratore + "&nameQuestionnaire=" + nameQuestionnaire + "&contentResponse=" + $(this).text() + "&id=" + id /*+ "&count=" + count*/);
    		});
// 	    	$(document).on("click", "#id-div-correct a", function(event){
// 	    		$(this).attr("href", contextPath + "/QuestionnaireServlet?action=correct&count=" + count);
// 	    	});
	    	$(document).on("click", "#id-div-infoRow a", function(event){
	    		var id = $(this).attr("id");
	    		$(this).attr("href", contextPath + "/QuestionnaireServlet?action=restoreStoricoDomande"/*&count=" + count*/ + "&id=" + id);
	    	});
	    </script>
	    <script src="<%=request.getContextPath() %>/js/header.js"></script>
		<script src="<%=request.getContextPath() %>/js/account.js"></script>
		<script src="<%=request.getContextPath() %>/js/initHome.js"></script>
	</body>
</html>