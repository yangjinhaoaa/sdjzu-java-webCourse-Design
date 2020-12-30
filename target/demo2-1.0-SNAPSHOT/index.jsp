<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>车辆管理系统</title>
</head>
<body>
<img src = 'img/VCG211282253524.jpg' border = '0' width = '100%' height = '100%' style = 'position: absolute; left:0px; top:0px; z-index: -1'>
<h1 align="center">车辆管理系统</h1>
<h4 align="center">Java Web课程设计</h4>
<h4 align="center">物联171杨金浩 201708104019</h4>
<marquee><span style="font-weight: bolder;font-size: 40px;">🚗🚗🚗🚗🚗</span></marquee>
<div align="center"><img src="img/1608640019900.jpg" width="10%"/></div>

<script type="text/javascript">
    function carleave(){
        var userId=document.getElementById("id").value;
        var result=confirm("确认离开？");
        if(result==true){
            window.location.href='carleave?id='+userId;
        }
        //alert(userId);
    }
    function carblack(){
        var userId=document.getElementById("id").value;
        var result=confirm("确认拉黑？");

        if(result==true){
            window.location.href='carblack?id='+userId;
        }
        //alert(userId);
    }
</script>
<div align="center" style="font-size:20px">
    <form name="input" action="carcome" method="get">
        车牌号: <input type="text" name="id" id="id" style="height:30px"/>
        <p>
        <input name="reset" type="reset" value="重新输入"/>
        <input name="submit" type="submit" value="进入"/>
        <input name="leave" type="button" value="离开" onclick="carleave();"/>
        <input name="blacklist" type="button" value="拉黑" onclick="carblack();"/>
    </form>
</div>

<form action="allinfo.jsp" method="post">
    <p>数据库信息查看</p>
    <input name="submit" type="submit" value="所有车辆信息"/>
</form>

    <form action="fixedcar.jsp" method="post">
        <input name="submit" type="submit" value="固定车辆信息"/>
    </form>
    <form action="temcar.jsp" method="post">
        <input name="submit" type="submit" value="临时车辆信息"/>
    </form>
    <form action="blacklistcar.jsp" method="post">
        <input name="submit" type="submit" value="黑名单车辆信息"/>
    </form>

<br/>

</body>
</html>