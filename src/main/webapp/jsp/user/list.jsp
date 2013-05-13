<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<html>
<head>
	<title>${title}</title>
</head>

<body>

<div class="container">

	<div class="row">
		<div class="span12">
		</div>
	</div>
	
	<div class="row">
		<div class="span12">
			<h1>${title}</h1>
			<h4>Count : ${total}</h4>
		</div>
	</div>
	

	<c:if test="${not empty message}">
		<div class="row">
			<div class="span12">
				<div class="alert alert-info">
					${message}
				</div>
			</div>
		</div>	
	</c:if>


	<div class="row">
		
		<div class="span12">

			<div class="btn-toolbar">
				<div class="btn-group">

					<%  int total = Integer.parseInt(request.getAttribute("total").toString());
						int resultsPerPage = Integer.parseInt(request.getAttribute("resultsPerPage").toString());
						int activePage = Integer.parseInt(request.getAttribute("activePage").toString());
						
						int currentPage = 1;
					    for(int m = 0; m < total; m++){ 
							if(m % resultsPerPage == 0){%>
								<%if(activePage == currentPage){%>
									<a href="/shiro_hai/app/user/list?offset=<%=m%>&max=<%=resultsPerPage%>&page=<%=currentPage%>" class="btn active"><%=currentPage%></a>
								<%}else{%>
									<a href="/shiro_hai/app/user/list?offset=<%=m%>&max=<%=resultsPerPage%>&page=<%=currentPage%>" class="btn"><%=currentPage%></a>
								<%}%>
								
							<%	
								currentPage++;
							}%>
					<%}%>

				</div>
			</div>
			
				
			<table class="table table-condensed table-bordered">
				<thead>
					<tr>
						<th>Id</th>
						<th>Name</th>
						<th>Email</th>
						<th>Username</th>
						<th>Password Hash</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="user" items="${users}">
						<tr>
							<td>${user.id}</td>
							<td>${user.name}</td>
							<td>${user.email}</td>
							<td>${user.username}</td>
							<td>${user.passwordHash}</td>
							<td><a href="/shiro_hai/app/user/show/${user.id}" title="Show ${user.name}" class="btn">Show</a>
						</tr>									
					</c:forEach>
				</tbody>
			</table>
			
		</div>
	</div>
</body>
</html>