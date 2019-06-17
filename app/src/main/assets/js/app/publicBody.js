$(function(){
    def();
	abc();
	
})

function abc(){
	$(".publicbodyupd").click(function(){
		var s1=$("#method").val().toString().trim()  ;
		var s2=$("#version").val().toString().trim()  ;
		var s3= $("#timestamp").val().toString().trim() ;
		
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

		var str=""+
        		"||"+ $("#method").attr("name");
        		"||"+ $("#version").attr("name");
        		"||"+ $("#timestamp").attr("name");

        		alert(str);

                var jsonstr=$("#pulicBodyinput").serialize();

                alert("pulicBodyinput--"+jsonstr);

	})
}


function def(){

		$("#methodname").blur(function(){
		 var s1=$("#method").val().toString().trim()  ;
		    $("#method").attr("name",s1);
		})
		$("#versionname").blur(function(){
		var s2=$("#version").val().toString().trim()  ;
		    $("#version").attr("name",s2);
		})
		$("#timestampname").blur(function(){
		var s3= $("#timestamp").val().toString().trim() ;
		    $("#timestamp").attr("name",s3);
		})
}