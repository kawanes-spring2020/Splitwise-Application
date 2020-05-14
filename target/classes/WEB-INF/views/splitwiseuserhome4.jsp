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
                    <li class="active">
                        <a href="/SplitwiseApp/notifications">Friend Requests</a>
                    </li>
                    <li>
                        <a href="/SplitwiseApp/transactions">My Transactions</a>
                    </li>
                    <li>
                        <a href="/SplitwiseApp/expenselist">My Expenses</a>
                    </li>
                </ul>

                
            </nav>

            <!-- Page Content Holder -->
            <div id="content">

                <nav class="navbar navbar-default">
                    <div class="container-fluid">

                        <div class="navbar-header">
                                <h4>Dear <strong>${loggedinuser}</strong>, Welcome to SplitWise App
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
		  	<div class="panel-heading"><span class="lead">Friend Requests </span></div>
		  	<div id="table-wrapper">
  				<div id="table-scroll">
			<table class="table table-hover">
	    		<thead>
		      		<tr>
				        <th>Firstname</th>
				        <th>Lastname</th>
				        <th>Email</th>
				        <th>Username</th>
				        <sec:authorize access="hasRole('USER')">
				        	<th width="100"></th>
				        </sec:authorize>
				        <sec:authorize access="hasRole('USER')">
				        	<th width="100"></th>
				        </sec:authorize>
				        
					</tr>
		    	</thead>
	    		<tbody>
				<c:forEach items="${notifications}" var="notification">
					<tr>
						<td>${notification.fromFirstName}</td>
						<td>${notification.fromLastName}</td>
						<td>${notification.fromEmail}</td>
						<td>${notification.from_username}</td>
					    <sec:authorize access="hasRole('USER')">
							<td><a href="<c:url value='/acceptrequest-${notification.from_username}' />" class="btn btn-success custom-width">Accept</a></td>
				        </sec:authorize>
				        <sec:authorize access="hasRole('USER')">
							<td><a href="<c:url value='/rejectrequest-${notification.from_username}' />" class="btn btn-danger custom-width">Reject</a></td>
        				</sec:authorize>
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
