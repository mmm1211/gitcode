<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html >
<html>
<head>
<meta  charset="UTF-8">
<link rel="shortcut icon" type="image/x-icon" href="../../../easyui/images/yc.png"/>
<title>试卷标准答案</title>
<style>
	#exam_header{
		width:100%;
		height:140px;
		background-color:#3D69AB;
	}
	
	#exam_name{
		width:100%;
		height:60%;
		font-size:30px;	
		color:white;
		text-align:center;
		line-height:80px;		
	}
	
	#stuinfo_name{
		margin-left:1100px;
		color:red;
	}
	#surplustime{
		padding-right:20px;
		color:red;
	}
	#question_body{
		margin:30px;
		zoom:1;
	}
	ul{
		list-style:none;
	}
	#question_list{
		width:99%;
		margin:0 auto;
		border:1px solid #3D69AB;
	}
	#btn_submit{
		margin-left:500px;
		margin-bottom:20px;
	}
	#exam_foot{
		margin-top:20px;
		width:100%;
		height:100px;
		background-color:#3D69AB;
		float:left;
		color:white;
		text-align:center;
		line-height:80px;
		font-size:20px;
	}
	
</style>
</head>
<body>

	<header>
		<div id="exam_header">
			<div id="exam_name">${examPaperInfo.ename }</div>
			<div id="header_2">
			<span id="stuinfo_name"><span style="color:white;">当前操作员：</span>${currentLoginAdmin.aname }</span>&nbsp;&nbsp;&nbsp;&nbsp;
			</div>
		</div>
	</header>
	<div id="question_body">
		<div id="question_list">
			<ul >
			<c:forEach items="${ questionInfo }" var="item" varStatus="index">
				<li>
					<h4><a name="t_${item.qid }"> 第${index.index+1 }题&nbsp;-&nbsp; ${item.qname }</a></h4>
					<c:if test="${item.tid == 1 }"><!-- 单选题 -->
						<input type="radio" name="${item.qid} }" value="${item.qid }-A-1"/>&nbsp;A:&nbsp;&nbsp;${item.ans1 }<br/>
						<input type="radio" name="${item.qid} }" value="${item.qid }-B-1"/>&nbsp;B:&nbsp;&nbsp;${item.ans2 }<br/>
						<input type="radio" name="${item.qid} }" value="${item.qid }-C-1"/>&nbsp;C:&nbsp;&nbsp;${item.ans3 }<br/>
						<input type="radio" name="${item.qid} }" value="${item.qid }-D-1"/>&nbsp;D:&nbsp;&nbsp;${item.ans4 }<br/>
						<br />正确答案:<span style="color:red;">${item.ans}</span>
						
					</c:if>
					<c:if test="${item.tid == 2}"><!-- 多选题 -->
						<input type="checkbox" name="${item.qid} }" value="${item.qid }-A-2"/>&nbsp;A:&nbsp;&nbsp;${item.ans1 }<br/>
						<input type="checkbox" name="${item.qid} }" value="${item.qid }-B-2"/>&nbsp;B:&nbsp;&nbsp;${item.ans2 }<br/>
						<input type="checkbox" name="${item.qid} }" value="${item.qid }-C-2"/>&nbsp;C:&nbsp;&nbsp;${item.ans3 }<br/>
						<input type="checkbox" name="${item.qid} }" value="${item.qid }-D-2"/>&nbsp;D:&nbsp;&nbsp;${item.ans4 }<br/>
						<br />正确答案:<span style="color:red;">${item.ans}</span>
					</c:if>
					<c:if test="${item.tid == 3 }"><!-- 判断题 -->
						<input type="radio" name="${item.qid} }" value="${item.qid }-正确-3"/>&nbsp;正确&nbsp;&nbsp;&nbsp;&nbsp;<br/>
						<input type="radio" name="${item.qid} }" value="${item.qid }-错误-3"/>&nbsp;错误<br/>
						<br />正确答案:<span style="color:red;">${item.ans}</span>
					</c:if>
					<c:if test="${item.tid == 4 }"> <!-- 填空题 -->
						<input type="text" style="width:90%" value="" id="${item.qid }"/>&nbsp;<br/>
						<br />正确答案:<span style="color:red;">${item.ans}</span>
					</c:if>
				</li>	
			</c:forEach>
			</ul>
			<div id="btn_submit">
				<input type="button"  value="返回" onClick="javascript :history.back(-1);"/>
			</div>
		</div>
	</div>
	<div id="exam_foot">
		版权所有@在线考试系统
	</div>
	<script type="text/javascript" src="../../../easyui/js/jquery-1.11.3.js"></script>
	<script  type="text/javascript">
		
		$(function(){
			
		});
	</script>
</body>
</html>