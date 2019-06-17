var global={};
$(function(){

 var str=JSInterface.appmsgget();
    alert(str);
    if(str != null){
     global.str=JSON.parse(str);
    }else{
    str="";
    }
    alert(global.str)
    abc(global.str);
    def();

})

function abc(s){
   $("#compress").val("GZIP")  ;
       $("#appRate").val("10")  ;
       $("#dataType").val("JSON")  ;
       $("#signMethod").val("AES")  ;
       if(s.length>0){
   	    var appKey="";
   	     $.each(s,function(index,item){
   	                 appKey+="<option value='"+item.id+"'>"+item.appid+"</option>"
   	         });
   	        $("#appKey").empty();
   	        $("#appKey").append(appKey);
   	        $("#appKey").change(function(){
   	            $.each(global.str,function(index,item){

   	            	var id1= $(appKey).val()
   	            	var id2=item.id
   	                if(id1==id2){
   	                 $("#accessToken").empty();
   	                 $("#accessToken").append("<option value='"+item.id+"'>"+item.accesstoken+"</option>");

   	                     var str=JSInterface.tokenmsgbyid(item.taxtableid)

   	                      if(str != null){
   	                         str=JSON.parse(str);
   	                         }else{
   	                         str="";
   	                         }

   	                      if(str.length>0){
   	                        $.each(str,function(idex,item){
   	                            $("#userTax").val(item.taxnum);
   	                        })
   	                      }

   	                }
   	            })
   	        });

       }


	$(".publicheadupd").click(function(){
		var s1=$("#userTax").val().toString().trim()  ;
		var s2=$("#compress").val().toString().trim()  ;
		var s3= $("#appKey").val().toString().trim() ;
		var s4=$("#appRate").val().toString().trim()  ;
		var s5=$("#dataType").val().toString().trim()  ;
		var s6=$("#signMethod").val().toString().trim()  ;
		var s7=$("#accessToken").val().toString().trim()  ;
		if(s1.length==0){
			alert("有必填项不能为空");
			
			return;
		}
		if(s2.length==0){
			alert("有必填项不能为空");
			return;
		}
		if(s3.length==0){
			alert("有必填项不能为空");
			return;
		}
		if(s4.length==0){
			alert("有必填项不能为空");
			return;
		}
		if(s5.length==0){
			alert("有必填项不能为空");
			return;
		}
		if(s6.length==0){
			alert("有必填项不能为空");
			return;
		}
		if(s7.length==0){
			alert("有必填项不能为空");
			return;
		}
		var str=""+
		"||"+ $("#userTax").attr("name");
		"||"+ $("#compress").attr("name");
		"||"+ $("#appKey").attr("name");
		"||"+ $("#appRate").attr("name");
		"||"+$("#dataType").attr("name");
		"||"+$("#signMethod").attr("name");
		"||"+ $("#accessToken").attr("name");

		alert(str);

        var jsonstr=$("#publicHeadinput").serialize();

        alert("publicHeadinput++"+jsonstr);
	})
}
function def(){
		$("#userTaxname").blur(function(){
		var s1=$("#userTaxname").val().toString().trim()  ;
		    $("#userTax").attr("name",s1);
		})
		$("#compressname").blur(function(){
		var s2=$("#compressname").val().toString().trim()  ;
		    $("#compress").attr("name",s2);
		})
		$("#appKeyname").blur(function(){
		var s3= $("#appKeyname").val().toString().trim() ;
		    $("#appKey").attr("name",s3);
		})
		$("#appRatename").blur(function(){
		var s4=$("#appRatename").val().toString().trim()  ;
		    $("#appRate").attr("name",s4);
		})
		$("#dataTypename").blur(function(){
		var s5=$("#dataTypename").val().toString().trim()  ;
		    $("#dataType").attr("name",s5);
		})
		$("#signMethodname").blur(function(){
		var s6=$("#signMethodname").val().toString().trim()  ;
		    $("#signMethod").attr("name",s6);
		})
		$("#accessTokenname").blur(function(){
		var s7=$("#accessTokenname").val().toString().trim()  ;
		    $("#accessToken").attr("name",s7);
		})
}