<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@include file="header.jsp" %>



        <div class="wrapper">
            <!-- Sidebar Holder -->
            <nav id="sidebar">
                <div class="sidebar-header">
                    <img class="home" src="<c:url value="/static/images/splitwiselogin.jpg"/>"/>
                </div>

                <ul class="list-unstyled components">
                    <li>
                        <a href="/SplitwiseApp/friends">Friends</a>
                        
                    </li>
                    <li>
                        <a href="/SplitwiseApp/list" >People you may know</a>
                        
                    </li>
                    <li>
                        <a href="/SplitwiseApp/invitefriends">Invite Friends</a>
                    </li>
                    <li>
                        <a href="/SplitwiseApp/notifications">Friend Requests</a>
                    </li>
                    <li>
                        <a href="/SplitwiseApp/transactions">My Transactions</a>
                    </li>
                    <li class="active">
                        <a href="/SplitwiseApp/expenselist">My Expenses</a>
                    </li>
                    
                    
                </ul>

                
            </nav>

            <!-- Page Content Holder -->
            <div id="content">

                <nav class="navbar navbar-default">
                    <div class="container-fluid">

                        <div class="navbar-header">
                                <h4>Dear <strong>${loggedinuser}</strong>, Welcome to SplitWise App</h4>
                        </div>

                        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                            <ul class="nav navbar-nav navbar-right">
                                <h4><a href="<c:url value="/logout" />">Logout</a></h4>
                            </ul>
                        </div>
                    </div>
                </nav>

            
            </div>
            
            <div class="generic-container">
		
		<div class="panel panel-default">
			  <!-- Default panel contents -->
		  	<div class="panel-heading"><span class="lead">Expense List </span></div>
		  	<div id="table-wrapper">
  				<div id="table-scroll">
			<table class="table table-hover">
	    		<thead>
		      		<tr>
		      			
				        <th>Expense Id</th>
				        <th>Split With</th>
				        <th>Description</th>
				        <th>Amount</th>
				        <th width="300">Actions</th>
				        
				        
					</tr>
		    	</thead>
	    		<tbody>
				<c:forEach items="${expenselist}" var="exp">
					<tr>
						
						<td>${exp.id}</td>
						<td>
						<c:forEach items="${exp.split_with}" var="e">
						<span>${e}</span>
						<br>
						</c:forEach>
						</td>
						<td>${exp.description}</td>
						<td>${exp.amount}</td>
						<td><a href="<c:url value='/edit-expense-${exp.id}' />" class="btn btn-primary custom-width">Edit</a>
							<a href="<c:url value='/delete-expense-${exp.id}' />" class="btn btn-danger custom-width">Delete</a>
							<a href="<c:url value='/add-document-${exp.id}' />" class="btn btn-success custom-width">Bills</a>
						</td>
					</tr>
				</c:forEach>
	    		</tbody>
	    	</table>
	    	</div>
	    	</div>
		</div>
		<%-- <sec:authorize access="hasRole('ADMIN')">
		 	<div class="well">
		 		<a href="<c:url value='/newuser' />">Add New User</a>
		 	</div>
	 	</sec:authorize> --%>
   	</div>
        </div>





<%@include file="footer.jsp" %>
