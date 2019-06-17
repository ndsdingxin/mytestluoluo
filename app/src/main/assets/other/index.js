$(function(){;
    $(".btn").click(function(){
    	var mydata = document.getElementById("form1").files[0];
        var  formData = new FormData();
        formData.append("mydata", mydata);
        formData.append("accessToken", $(".accessToken").val());
        formData.append("groupId", $(".groupId").val());
        var strjson= $("#form1").serializeArray();
 //       contentType:"multipart/form-data",
 //       processData: false,
        // 告诉jQuery不要去处理发送的数据
  //      contentType: false,
        // 告诉jQuery不要去设置Content-Type请求头
        $.ajax({

            type: "POST",
            url: "http://36.7.144.236:8088/vpr/registerWithMp3",
            data: formData,

            success: function(data){
             alert( "Data result:===== " + data );
           },
           error:function(err){
        	   alert( "Data  result:===== " + err );
           }
        })

        alert( "Data Saved:===== " + 123456);

        /*var mydata = document.getElementById("form1").files[0];
        var  formData = new FormData();
        formData.append("mydata", mydata);
        formData.append("accessToken", $(".accessToken").val());
        formData.append("groupId", $(".groupId").val());
        var strjson= $("#form1").serializeArray();*/
        /*$.ajax({
                contentType:"multipart/form-data",
                type: "POST",
                url: "http://36.7.144.236:8088/vpr/registerWithMp3",
                data: formData,
                processData: false, // 告诉jQuery不要去处理发送的数据
                contentType: false, // 告诉jQuery不要去设置Content-Type请求头
                success: function(data){
                 alert( "Data Saved:===== " + data );
               }
        })*/
})
})