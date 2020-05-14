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
                    <li >
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
                                <h4>Transaction History with ${user} </h4>
                        </div>

                        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                            <ul class="nav navbar-nav navbar-right">
                                <h4><a href="<c:url value="/logout" />">Logout</a></h4>
                            </ul>
                        </div>
                

            
            </div>
			<div id="table-wrapper">
  				<div id="table-scroll2">
  				<a href="<c:url value='/settleup-${user}' />" class="btn btn-success custom-width">Settle Up</a>
		   <div class="row">
		      
		      <div class="col-md-12 col-lg-12">
		         <div id="tracking-pre"></div>
		         <div id="tracking">
		            
		            <div class="tracking-list">
		               <c:forEach items="${trlist}" var="tr">
		               
			               <div class="tracking-item">
			                  <div class="tracking-icon status-outfordelivery">
			                     <svg class="svg-inline--fa fa-shipping-fast fa-w-20" aria-hidden="true" data-prefix="fas" data-icon="shipping-fast" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 640 512" data-fa-i2svg="">
			                        <path fill="currentColor" d="M624 352h-16V243.9c0-12.7-5.1-24.9-14.1-33.9L494 110.1c-9-9-21.2-14.1-33.9-14.1H416V48c0-26.5-21.5-48-48-48H112C85.5 0 64 21.5 64 48v48H8c-4.4 0-8 3.6-8 8v16c0 4.4 3.6 8 8 8h272c4.4 0 8 3.6 8 8v16c0 4.4-3.6 8-8 8H40c-4.4 0-8 3.6-8 8v16c0 4.4 3.6 8 8 8h208c4.4 0 8 3.6 8 8v16c0 4.4-3.6 8-8 8H8c-4.4 0-8 3.6-8 8v16c0 4.4 3.6 8 8 8h208c4.4 0 8 3.6 8 8v16c0 4.4-3.6 8-8 8H64v128c0 53 43 96 96 96s96-43 96-96h128c0 53 43 96 96 96s96-43 96-96h48c8.8 0 16-7.2 16-16v-32c0-8.8-7.2-16-16-16zM160 464c-26.5 0-48-21.5-48-48s21.5-48 48-48 48 21.5 48 48-21.5 48-48 48zm320 0c-26.5 0-48-21.5-48-48s21.5-48 48-48 48 21.5 48 48-21.5 48-48 48zm80-208H416V144h44.1l99.9 99.9V256z"></path>
			                     </svg>
			                     <!-- <i class="fas fa-shipping-fast"></i> -->
			                  </div>
			                  <div  class="tracking-date">${tr.date}<span>${tr.time}</span></div>
			                  <div class="tracking-content">${tr.tr_name}
			                  <c:forEach items="${tr.output}" var="tro">
			                  <span>${tro}</span>
			                  </c:forEach>
			                  </div>
			               </div>
			               <hr>
		              	</c:forEach>
		               
		            </div>
		         </div>
		      </div>
</div>
</div></div>
</div>
			
        </div>





<%@include file="footer.jsp" %>
