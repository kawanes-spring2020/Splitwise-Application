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
                    <li class="active">
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
                    <li>
                        <a href="/SplitwiseApp/expenselist">My Expenses</a>
                    </li>
                    
                    
                </ul>

                
            </nav>

         
            
            <div class="generic-containerfriend">
		
			
	 	<div class="well">

                

                        <div class="navbar-header">
                                <a style="font-size:20px;" href="<c:url value='/addexpense' />">Add Expense</a>
                        </div>

                        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                            <ul class="nav navbar-nav navbar-right">
                                <h4><a href="<c:url value="/logout" />">Logout</a></h4>
                            </ul>
                        </div>
                

            
            </div>
	 	
		<div class="panel panel-default">
			  <!-- Default panel contents -->
		  	<div class="panel-heading"><span class="lead">Friends </span></div>
		  	<div id="table-wrapper">
  				<div id="table-scroll1">
			<table class="table table-hover">
	    		<thead>
		      		<tr>
		      			<th></th>
				        <th></th>
				        <th>Name</th>
				        <th></th>
				        <th>Status</th>
				        <th></th>
				        <th></th>
				        <th>Balance</th>
				        
					</tr>
		    	</thead>
	    		<tbody>
				<c:forEach items="${friends}" var="friend">
				
					<tr>
					
						<td><a href="<c:url value="/individualTransaction-${friend.friend_username}" />"><img src="https://ui-avatars.com/api/?name=${friend.friendFirstName}+${friend.friendLastName}" alt="Mike Anamendolla" class="rounded-circle mx-auto d-block img-fluid"></a></td>
						<td></td>
						<td><a href="<c:url value="/individualTransaction-${friend.friend_username}" />">${friend.friendFirstName}  ${friend.friendLastName}</a></td>
						<td></td>
						<td><a href="<c:url value="/individualTransaction-${friend.friend_username}" />">${friend.status}</a></td>
						<td></td>
						<td></td>
						<c:choose>
						<c:when test="${friend.balance gt 0}">
							<td ><a style="color: green;" href="<c:url value="/individualTransaction-${friend.friend_username}" />">Owes you <br>US $${friend.balance}</a></td>
						</c:when>
						<c:when test="${friend.balance eq 0}">
							<td><a href="<c:url value="/individualTransaction-${friend.friend_username}" />">Owes you <br>US $${friend.balance}</a></td>
						</c:when>
						<c:otherwise>
							<td ><a style="color: red;" href="<c:url value="/individualTransaction-${friend.friend_username}" />">You Owe <br>US $${friend.balance}</a></td>
						</c:otherwise>
						
						</c:choose>
					</tr>
					
				</c:forEach>
	    		</tbody>
	    	</table>
	    	</div></div>
		</div>
		 
   	</div>
        </div>





<%@include file="footer.jsp" %>
