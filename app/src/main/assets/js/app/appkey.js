$(function(){
	abc();
})

function abc(){
	var data=[["1","3","3","3","3"],
	["32","3","3","3","3"],
	["3","3","3","3","3"],
	["34","3","3","3","3"],
	["35","3","3","3","3"],
	["36","3","3","3","3"],
	["37","3","3","3","3"],
	["3","3","3","3","3"],
	["3","3","3","3","3"],
	["3","3","3","3","3"],
	["3","3","3","3","3"],
	["3","3","3","3","3"],
	["3","3","3","3","3"],
	["3","3","3","3","3"],
	["3","3","3","3","3"],
	["3","3","3","3","3"],
	["3","3","3","3","3"],
	["3","3","3","3","3"],
	["3","3","3","3","3"],
	["3","3","3","3","3"],
	["3","3","3","3","3"]]
	var str="";
	for(var i=0;i<data.length;i++){
		var clazz=
		(i%2==0)?
		((i%3==0)?
		((i%4==0)? 
		((i%5==0)?
		"active":"info")
		:"danger")
		:"warning")
		:"success";
		 str+="<tr class=\""+clazz+"\">";
		for(var j=0;j<data[i].length;j++){
			str+="<td>"+data[i][j]+"</td>"
		}
		str+="<td><a class='appkeyupd' id='"+data[i][0]+"'>修改</a>"+
		"&nbsp&nbsp&nbsp&nbsp<a class='appkeyupd' id='"+data[i][0]+"'>删除</a>";
		 str+="</tr>"
	}
	  
	$("#appkey").append(str).find(".appkeyupd").click(function(){
		console.log($(this).attr("id"))
		alert($(this).attr("id"))
	}).end().find(".appkeydel").click(function(){
			console.log($(this).attr("id"))
		alert($(this).attr("id"))
	})
}
