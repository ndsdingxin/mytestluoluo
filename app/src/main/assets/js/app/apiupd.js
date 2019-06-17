$(function(){
var str=JSInterface.apibyid();
alert(str);
 if(str != null){
    var s=JSON.parse(str);
    }else{
    str="";
    }
    alert(s);
        abc(s);
        def();
})

function abc(s){
    $(s).each(function(index,item){
        var sd1=$("#id").val(item.id);
        var sd2=$("#api").val(item.api);
        var sd3=$("#version").val(item.version);
        var sd4=$("#remark").val(item.remark);
    })
}
function def(){
$(".apiupd").click(function(){
		var s1=$("#api").val().toString().trim()  ;
		var s2=$("#version").val().toString().trim()  ;
		var s3= $("#remark").val().toString().trim() ;

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

		var jsonstr=$("#apiupdinput").serialize();
                		var s="{'"
                		var str = jsonstr.replace(/&/g,"','").replace(/=/g,"':'");
                		s+=str+"'}"
                var jsonstr = JSON.stringify(s);
        alert("apiupdinput=="+jsonstr);
	})
}