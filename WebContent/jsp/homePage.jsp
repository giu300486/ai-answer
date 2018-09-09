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
	
<!-- 		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"> -->
<!-- 	    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css"> -->
<%-- 	    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/lavish.css"> --%>
<%-- 	    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap-dropdownhover.min.css"> --%>
<%-- 	    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/navbar.css"> --%>
<%-- 	    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/home.css"> --%>
<%-- 	    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/footer.css"> --%>
	    
	    <jsp:include page="${request.contextPath}/fragments/header.jsp"></jsp:include>
	    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/questionnaire.css">
	    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.0/bootstrap-table.min.css" rel="stylesheet"/>
	    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/tabellaQuestionario.css">

		<link href="https://cdn.datatables.net/1.10.15/css/jquery.dataTables.min.css" rel="stylesheet"/>
	    <script type="text/javascript">
   			var context="${pageContext.request.contextPath}";
   		</script>
	</head>
	
	<body>
		
		<div class="container">
			<div class="row">
	           	<table id="id-table-questionnaire" class ="table table-filter" data-toggle="table" data-sort-name="NOME" data-sort-order="cresc">
					<thead class="panel-heading panelHeader">
				 		<tr>
				 			<th class="col-xs-4 col-md-4" data-field="NOME" data-sortable="true"> NOME </th> 
				 		</tr>
					</thead>
					<tbody id="table-body-questionario">
			            <c:forEach var="questionnaire" items="${questionnaires}">
			            	<c:forEach var="questionnaires" items="${questionnaire.value}">
				            	<tr>
				            		<td class="class-td">
				            			<span>${questionnaires}</span>
				            			<a id="id-btn-esegui-questionario" href="<%=request.getContextPath() %>/QuestionnaireServlet?action=initQuestionnaire&username=${questionnaire.key}&nameQuestionnaire=${questionnaires}" style="margin-left: 15px;" class="btn btn-success pull-right">Esegui</a>
				            		</td>
				            	</tr>
				            </c:forEach>
			            </c:forEach>
					</tbody>
				</table>
	        </div>
        </div>
		
		<script src="<%=request.getContextPath() %>/js/jquery-3.1.1.js"></script>
	    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>	
	    <script src="<%=request.getContextPath() %>/js/bootstrap-dropdownhover.min.js"></script>
	    <script src="<%=request.getContextPath() %>/js/header.js"></script>
		<script src="<%=request.getContextPath() %>/js/account.js"></script>
		<script src="<%=request.getContextPath() %>/js/initHome.js"></script>
		
		<script src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
		<script type="text/javascript">
			$('#id-table-questionnaire').DataTable( {
		        "pagingType": "full_numbers"
		    });
		</script>
		
	</body>
</html>