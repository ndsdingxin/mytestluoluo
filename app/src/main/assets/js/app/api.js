$(function(){
    $("#dbput").click(function(){
    	    alert("dpput");
    	})

    $("#dbapidel").click(function(){
            alert("dbapidel");
        })
    $("#dbappkeyput").click(function(){
            alert("dbappkeyput");
        })
    $("#dbappkeydel").click(function(){
            alert("dbappkeydel");
        })
    $("#dbusertaxput").click(function(){
            alert("dbusertaxput");
        })
    $("#dbusertaxdel").click(function(){
            alert("dbusertaxdel");
        })
    var str=JSInterface.aaaaa();
    alert(str);
    if(str != null){
    var s=JSON.parse(str);
    }else{
    str="";
    }
    alert(s);
        abc(s);
})

function abc(str1){

	if(str1.length<=0){
    return
	}
	alert(str1.length);
	var str="";
	$(str1).each(function(index,item){
		var clazz=
		(index%2==0)?
		((index%3==0)?
		((index%4==0)?
		((index%5==0)?
		"active":"info")
		:"danger")
		:"warning")
		:"success";
		    str+="<tr class=\""+clazz+"\">";
			str+="<td>"+item.id+"</td>"
		    str+="<td>"+item.api+"</td>"
		    str+="<td>"+item.version+"</td>"
		    str+="<td>"+item.remark+"</td>"
		str+="&nbsp&nbsp&nbsp&nbsp<td><a class='apiupd' id='"+item.id+"'>修改</a></br>&nbsp&nbsp&nbsp&nbsp</br>"+
		"</br>&nbsp&nbsp&nbsp&nbsp</br><a class='apidel' id='"+item.id+"'>删除</a></br>&nbsp&nbsp&nbsp&nbsp</br>"+
		"</br>&nbsp&nbsp&nbsp&nbsp</br><a class='apiuse' id='"+item.id+"'>调用</a></br>&nbsp&nbsp&nbsp&nbsp"
		;
		   
		 
		 str+="</td></tr>"
    })
	  
	$("#api").append(str).find(".apiupd").click(function(){
		
		console.log($(this).attr("id"))
       setTimeout(a, 1000);
        var id =$(this).attr("id");
        function a() {
                    layer.confirm('你确定要更新吗？', {
                        btn: ['确定', '取消'] //按钮
                    }, function() {
                        layer.msg(alert("apiupd=="+id), {
                            time: 1,
                        });
                    }, function() {
                        layer.msg(alert("取消"), {
                            time: 1, //20s后自动关闭
                        });
                    });
                }

	}).end().find(".apidel").click(function(){
		setTimeout(b, 1000);
		 var id =$(this).attr("id");
		function b() {
            		layer.confirm('你确定要删除吗？', {
            			btn: ['确定', '取消'] //按钮
            		}, function() {
            			layer.msg(alert("apidel=="+id), {
            				time: 200,
            			});
            		}, function() {
            			layer.msg(alert("取消"), {
            				time: 200, //20s后自动关闭
            			});
            		});
            	}
	}).end().find(".apiuse").click(function(){
		
		console.log($(this).attr("id"))
		alert($(this).attr("id"))
		setTimeout(c, 1000);
		var id =$(this).attr("id");
        		function c() {
                    		layer.confirm('你确定要调用吗？', {
                    			btn: ['确定', '取消'] //按钮
                    		}, function() {
                    			layer.msg(alert("apiuse=="+id), {
                    				time: 200,
                    			});
                    		}, function() {
                    			layer.msg(alert("取消"), {
                    				time: 200, //20s后自动关闭
                    			});
                        });
                    }
	})

}
