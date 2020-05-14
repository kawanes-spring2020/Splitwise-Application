<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <!-- jQuery CDN -->
        <script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>
        <!-- Bootstrap Js CDN -->
        
        <!-- jQuery Custom Scroller CDN -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.concat.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.8.1/js/bootstrap-select.js"></script>
        <script type="text/javascript">
        
        
	        	
        
            $(document).ready(function () {
            	$('.selectpicker').addClass('col-lg-12');
            	$('.actiondiv1').hide();
            	
            	$('#friendsList').on('change.bs.select', function() {
            		var friends = document.getElementById("friendsList");
                	var selected1 = [];
                    for (var i = 0; i < friends.length; i++) {
                        if (friends.options[i].selected) selected1.push(friends.options[i].value);
                    }
                    console.log(selected1);
                    if(selected1.length<=1){
                    	$('.actiondiv1').show();
                    	$('.actiondiv').hide();
                    	
                    
                    	
                    	$('#paid_by1').children('option').remove();
                        $('#paid_by1').selectpicker('refresh');
                    	
                        var seloption1 = "Paid by YOU and split Equally with "+selected1[0];
                        var seloption2 = "Paid by "+selected1[0]+" and split Equally";
                        var seloption3 = "YOU owe "+selected1[0]+" the whole amount";
                        var seloption4 = selected1[0]+" owes you the whole amount";
						selected1[0] = seloption1;
						selected1[1] = seloption2;
						selected1[2] = seloption3;
						selected1[3] = seloption4;
						var seloption1 = "";
						
						$.each(selected1,function(i){
                            seloption += '<option value="'+selected1[i]+'">'+selected1[i]+'</option>'; 
                        });


                        $('#paid_by1').append(seloption).selectpicker();
                        $('#paid_by1').selectpicker('refresh');
                        
                        //---------------------------------------------------------------------------------
                        
                        
                    }
                    else{
                    	$('.actiondiv').show();
                    	$('.actiondiv1').hide();
                    	$('#paid_by').children('option:not(:first)').remove();
                        $('#paid_by').selectpicker('refresh');
                        var seloption = "";

                        $.each(selected1,function(i){
                            seloption += '<option value="'+selected1[i]+'">'+selected1[i]+'</option>'; 
                        });

                        $('#paid_by').append(seloption).selectpicker();
                        $('#paid_by').selectpicker('refresh');
                        //---------------------------------------------------------------------------------
                        
                        $('#split_with').children('option:not(:first)').remove();
                        $('#split_with').selectpicker('refresh');
                        var seloption = "";

                        $.each(selected1,function(i){
                            seloption += '<option value="'+selected1[i]+'">'+selected1[i]+'</option>'; 
                        });

                        $('#split_with').append(seloption).selectpicker();
                        $('#split_with').selectpicker('refresh');

                    }
                                           
		        });
            	
            	
            	

            	
                $('#sidebarCollapse').on('click', function () {
                    $('#sidebar, #content').toggleClass('active');
                    $('.collapse.in').toggleClass('in');
                    $('a[aria-expanded=true]').attr('aria-expanded', 'false');
                });
            });
        </script>
    </body>
</html>