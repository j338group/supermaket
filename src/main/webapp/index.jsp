<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  <script src="${pageContext.request.contextPath }/static/js/jquery-1.8.3.min.js" type="text/javascript"></script>
  <script type="text/javascript">
      $(function(){
          $("#ssend").click(function () {
              // alert("发送ajax请求")
             // var formData= $("form").serialize();//序列化：name=tom&id=101
             var formData= $("form").serializeArray();
              $.ajax({
                  "url":"senduser",
                  "type":"POST",
                 "data":formData,
                  "success":function(datas){
                      // $("#sp").html("ajax方式--"+datas.message);
                      alert(datas);
                      alert(datas.userName);
                      $("[name='uname']").val(datas.userName);
                  },
                  "dataType":"json"
              })

          })
      })
  </script>
  <body>
    This is my JSP page. <br>

    <form action="#" method="post">
        用户名：<input id="uname" type="text" name="uname" value="tom" /><br />
        密码：<input class="pwd" type="password" name="password" value="123" /><br />
        ID：<input  type="text" name="id" value="1" /><br />

    </form>
    <button id="ssend">ajax请求</button>
  </body>
</html>
