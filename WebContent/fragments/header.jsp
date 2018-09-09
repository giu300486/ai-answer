<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html>
	<head>
	    <title></title>
		
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
	    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css">
	    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/lavish.css">
	    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap-dropdownhover.min.css">
	    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/navbar.css">
	    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/home.css">
	    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/footer.css">	    

<%-- 		<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap-iso.css"> --%>
<%-- 	    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/font-awesome-iso.css"> --%>
<%-- 	    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/lavish-iso.css"> --%>
<%-- 	    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap-dropdownhover.min-iso.css"> --%>
<%-- 	    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/navbar-iso.css"> --%>
<%-- 	    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/home-iso.css"> --%>
<%-- 	    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/footer-iso.css"> --%>
	    
	   
	    <script type="text/javascript">
	    	var context="<%=request.getContextPath() %>";
	    </script>
	    
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
	<!--                 		 <ul id="categoryTree" class="nav navbar-nav"> -->
	<!--                 		 	 <li class="dropdown"> -->
	<!--                 		 	 	<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Categorie</a> -->
	<!--                 		 	 	<ul class="dropdown-menu" role="menu"> -->
	<!--                 		 	 		<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Cinema<span class="caret"></span></a> -->
	<!-- 	                                    <ul class="dropdown-menu" role="menu"> -->
	<%-- 	                                        <li><a href="<%= request.getContextPath()%>/SearchServlet?action=ricercaPerCategoria&offset=0&parameter=Azione">Azione</a></li> --%>
	<%-- 	                                        <li><a href="<%= request.getContextPath()%>/SearchServlet?action=ricercaPerCategoria&offset=0&parameter=Fantasy">Fantasy</a></li> --%>
	<%-- 	                                        <li><a href="<%= request.getContextPath()%>/SearchServlet?action=ricercaPerCategoria&offset=0&parameter=Horror">Horror</a></li> --%>
	<!-- 	                                    </ul> -->
	<!--                                 	</li> -->
	<!--                                 	<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Concerti<span class="caret"></span></a> -->
	<!-- 	                                    <ul class="dropdown-menu" role="menu"> -->
	<%-- 	                                        <li><a href="<%= request.getContextPath()%>/SearchServlet?action=ricercaPerCategoria&offset=0&parameter=Club e Dance">Club & Dance </a></li> --%>
	<%-- 	                                        <li><a href="<%= request.getContextPath()%>/SearchServlet?action=ricercaPerCategoria&offset=0&parameter=Metal">Metal</a></li> --%>
	<%-- 	                                        <li><a href="<%= request.getContextPath()%>/SearchServlet?action=ricercaPerCategoria&offset=0&parameter=Rock">Rock</a></li> --%>
	<!-- 	                                    </ul> -->
	<!--                                 	</li> -->
	<!--                                 	<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Sport<span class="caret"></span></a> -->
	<!-- 	                                    <ul class="dropdown-menu" role="menu"> -->
	<%-- 	                                        <li><a href="<%= request.getContextPath()%>/SearchServlet?action=ricercaPerCategoria&offset=0&parameter=Basket">Basket</a></li> --%>
	<%-- 	                                        <li><a href="<%= request.getContextPath()%>/SearchServlet?action=ricercaPerCategoria&offset=0&parameter=Calcio">Calcio</a></li> --%>
	<%-- 	                                        <li><a href="<%= request.getContextPath()%>/SearchServlet?action=ricercaPerCategoria&offset=0&parameter=Tennis">Tennis</a></li> --%>
	<!-- 	                                    </ul> -->
	<!--                                 	</li> -->
	<!--                 		 	 	</ul> -->
	<!--                 		 	 </li> -->
	<!--                 		 </ul> -->
	                		 <ul class="nav navbar-nav navbar-right">
	                		 	<c:if test="${loggedUser==null}">
	                		 		<li id="loginDropdown" class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">Sign in <span class="glyphicon glyphicon-log-in"></span></a>
	                		 			<div id="loginPanel" class="dropdown-menu" role="menu">
	                		 				<div>
	                		 					<fieldset>
	                		 						<div class="panel-body">
	                		 							<form id="loginForm" action='' method="POST" role="form">
	                		 								<div class="form-group">
	                                                    		<input class="form-control" placeholder="Username" name="username" type="text" autofocus="" value="" required>
	                                                		</div>
			                                                <div class="form-group">
			                                                    <input class="form-control" placeholder="Password" name="password" type="password" value="" required>
			                                                </div>
			                                                <div id="login-auto-close-notification" class="alert alert-success hidden">
			                                                    <p id="login-auto-message"></p>
			                                                </div>
	                                                		<button id="submitLogin" type="submit" class="btn btn-sm btn-primary">Login</button>
	                                                		<a id="register-button" class="pull-right btn btn-sm btn-danger" href="<%=request.getContextPath()%>/jsp/registration.jsp">Register</a>
	                		 							</form>
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
	<%--                 		 				<li class="text-center"><a href="<%=request.getContextPath() %>/ClientServlet?action=myProfile">I Miei Questionari</a></li> --%>
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
	<!--                                 	<fieldset class="form-group text-center"> -->
	<!--                                 		<label> -->
	<!--                                         	<input type="radio" name="searchFilter" value="name" /> Nome -->
	<!--                                     	</label> -->
	<!--                                     	<label> -->
	<!--                                         	<input type="radio" name="searchFilter" value="categoria" /> Categoria -->
	<!--                                     	</label> -->
	<!--                                 	</fieldset> -->
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
		
		<script src="<%=request.getContextPath() %>/js/jquery-3.1.1.js"></script>
		<script type="text/javascript">
    		$("#searchToggle").on("click", function(event){
    			$("#searchForm").addClass("active in");
    			$("#searchForm").css("display","block");
    		});
    		
    		$("#cerca").on("click", function(event){
    			$("#searchForm").removeClass("active in");
    			$("#searchForm").css("display","none");
    			
    			window.location.href=context+"/HomeServlet?action=cercaQuestionario&wordMatch="+$("input[name='searchBar']").val();
    			
    		});
    		
    		$(".close").on("click", function(event){
    			$("#searchForm").removeClass("active in");
    			$("#searchForm").css("display","none");
    		});
		</script>
		
	</body>
</html>
