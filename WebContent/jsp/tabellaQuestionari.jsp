<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
	    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/questionnaire.css">
	    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/footer.css">
	    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.0/bootstrap-table.min.css" rel="stylesheet"/>
	    <link rel="stylesheet"  type="text/css" href="<%=request.getContextPath() %>/css/tabellaQuestionario.css"></link>
		
	</head>
	
	<body>
		<div id="remove-auto-close-notification" class="alert alert-success hidden">
        	<p id="remove-auto-message"></p>
     	</div>
     	<div id="div-select-css" class="alert alert-success hidden">
        	<p id="selected-css-message"></p>
     	</div>
		<br>
       	<div class="row">
           	<table id="id-table-questionnaire" class ="table table-filter" data-toggle="table" data-sort-name="NOME" data-sort-order="cresc">
				<thead class="panel-heading panelHeader">
			 		<tr>
<!-- 			 			<th class="col-xs-4 col-md-4" data-field="CODICE" data-sortable="true"> CODICE </th>  -->
			 			<th class="col-xs-4 col-md-4" data-field="NOME" data-sortable="true"> NOME </th> 
<!-- 			 			<th class="col-xs-4 col-md-4" data-field="CATEGORIA" data-sortable="true"> CATEGORIA </th>  -->
			 		</tr>
				</thead>
				<tbody id="table-body-questionario"></tbody>
			</table>
        </div>
        <br>
		
		<script src="<%=request.getContextPath() %>/js/jquery-3.1.1.js"></script>
	    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>	
	    <script src="<%=request.getContextPath() %>/js/bootstrap-dropdownhover.min.js"></script>
	    <script src="<%=request.getContextPath() %>/js/header.js"></script>
		<script src="<%=request.getContextPath() %>/js/account.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.0/bootstrap-table.min.js"></script>
		
	</body>
</html>