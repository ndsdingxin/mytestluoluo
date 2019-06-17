$(function(){
	abc();
	
})

function abc(){
	$(".appkeyupd").click(function(){
		var s1=$("#appid").val().toString().trim()  ;
		var s2=$("#appsecret").val().toString().trim()  ;
		var s3= $("#accesstoken").val().toString().trim() ;
		var s4=$("#taxtabletoken").val().toString().trim()  ;
		
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
	})
}
