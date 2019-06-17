var global=[];
$(function(){
	abc();

})

function abc(){
//alert(1)
	$(".privatebodynum").click(function(){
	//alert(2)
		 global.s1=$("#num").val().toString().trim()  ;
		var reg=new RegExp("^[1-9][0-9]*$")
		if(global.s1.length==0||!reg.test(global.s1)){
			alert("有必填项不能为空");

			return;
		}
	//alert(3)
		var str = "";
		var str1="";
		for (var i=1;i<=global.s1;i++) {
			str+='<div class="form-group has-success has-feedback"><label class="control-label" for="f"'+i+'>'
			str+="input section---"+i+'</label><input type="text" class="form-control inputname" '
			str+="name='f"+i+"'id='f"+i+"'placeholder='f"+i+"'></div>";

			str1+='<div class="form-group has-success has-feedback"><label class="control-label" for="n"'+i+'>'
			str1+="input name---"+i+'</label><input type="text" class="form-control  manyinput" '
			str1+="name='n"+i+"'id='n"+i+"'placeholder='n"+i+"'></div>";

			console.log(i);
		}
		//alert(4)
		$("#privateBodyNum").prop("hidden","hidden")
		$("#privateBodyinput").prepend(str);
		$("#privateBodyname").prepend(str1).find(".manyinput").change(function(){
            $(this).each(function(){
            	var id="#f"+$(this).attr("id").substring(1);
                $(id).attr("name",$(this).val());
            })
        });


		$("#privateBodyinput").removeAttr("style");
		$("#privateBodyname").removeAttr("style");
		//alert(5)
	})


	$(".privatebodyupd").click(function(){
        var str = "";
        $(".inputname").each(function(){
              var value = $(this).attr("name");
              str+=value+"||"

        })
        alert(str)
 		console.log(str);
         var jsonstr=$("#privateBodyinput").serialize();
        alert("privateBodyinput**"+jsonstr);

	})
}

