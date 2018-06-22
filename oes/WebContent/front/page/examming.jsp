<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html >
<html>
<head>
<meta  charset="UTF-8">
<link rel="shortcut icon" type="image/x-icon" href="../../easyui/images/yc.png"/>
<title>在线考试界面</title>
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
	#question_ans{
		width:20%;
		float:left;
		border:1px solid #3D69AB;
	}
	ul{
		list-style:none;
	}
	#question_list{
		width:79%;
		float:right;
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
			<span id="stuinfo_name"><span style="color:white;">当前考生：</span>${currentLoginUser.sname }</span>&nbsp;&nbsp;&nbsp;&nbsp;
			<label id="surplustime"><span>${examPaperInfo.conTime}</span><font color ="white">&nbsp;&nbsp;分钟(剩余时间)</font></label>
			</div>
		</div>
	</header>
	<div id="question_body">
		<div id="question_ans">
			<div style="text-align:center;color:#3D69AB;">答题板(点击可跳转到指定题目)</div>
			<ul>
			<c:forEach items="${questionInfo}" var="item" varStatus="index">
				<li id="a_${item.qid}-${item.tid }"><a href="#t_${item.qid }"><label>第${index.index+1 }题：</label><span></span></a></li>	
			</c:forEach>
			</ul>
		</div>
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
					</c:if>
					<c:if test="${item.tid == 2}"><!-- 多选题 -->
						<input type="checkbox" name="${item.qid} }" value="${item.qid }-A-2"/>&nbsp;A:&nbsp;&nbsp;${item.ans1 }<br/>
						<input type="checkbox" name="${item.qid} }" value="${item.qid }-B-2"/>&nbsp;B:&nbsp;&nbsp;${item.ans2 }<br/>
						<input type="checkbox" name="${item.qid} }" value="${item.qid }-C-2"/>&nbsp;C:&nbsp;&nbsp;${item.ans3 }<br/>
						<input type="checkbox" name="${item.qid} }" value="${item.qid }-D-2"/>&nbsp;D:&nbsp;&nbsp;${item.ans4 }<br/>
					</c:if>
					<c:if test="${item.tid == 3 }"><!-- 判断题 -->
						<input type="radio" name="${item.qid} }" value="${item.qid }-正确-3"/>&nbsp;正确&nbsp;&nbsp;&nbsp;&nbsp;<br/>
						<input type="radio" name="${item.qid} }" value="${item.qid }-错误-3"/>&nbsp;错误<br/>
					</c:if>
					<c:if test="${item.tid == 4 }"> <!-- 填空题 -->
						<input type="text" style="width:90%" value="" id="${item.qid }"/>&nbsp;<br/>
					</c:if>
				</li>	
			</c:forEach>
			</ul>
			<div id="btn_submit">
				<input type="button"  value="交卷" onclick="assignment()"/>
			</div>
		</div>
	</div>
	<div id="exam_foot">
		版权所有@在线考试系统
	</div>
	<script type="text/javascript" src="../../easyui/js/jquery-1.11.3.js"></script>
	<script  type="text/javascript">
		var time= 0;
		var second = 60;
		var timer;
		$(function(){
			time = parseInt($.trim($("#surplustime>span").text()));
			time--;
			timer =setInterval("countDown()",1000);

			//给每一个input绑定一个选择事件
			$("#question_list input:radio,#question_list input:checkbox").click(function(){
				var val = this.value;
				val = val.split("-");
				if(val[2] =="2"){ //说明是多选
					var obj = $("#a_"+val[0]+"-"+val[2]+" span:eq(0)");
					if(this.checked){ //如果当前对象是选中的
						var temp = obj.text()+val[1];
					if(temp.length == 1){
						obj.text(temp);
					}else{
						var arr=[];
						//然后排序
						for(var i=0,len=temp.length;i<len;i++){
							arr[arr.length] = temp.substr(i,1);
						}
						arr.sort();
						obj.text(arr.join(""));
					}
				}else{ //如果是取消选中的
					obj.text(obj.text().replace(val[1],""));
					
				}
			}else{//说明是单选或判断
				$("#a_"+val[0]+"-"+val[2]+" span").text(val[1]);
			}
			});
			$("#question_list input:text").blur(function(){
				var id = this.id;
				$("#a_"+id+"-4 span").text(this.value);
			});
		})
		//禁止f5刷新
		document.onkeydown =function(e){
			var ev = window.event ||e;
			var code = ev.keyCode || ev.which;
			if(code == 116){
				ev.keyCode ? ev.keyCode = 0 : ev.which =0;
				cancelBubble = true;
				return false;
			}
		}
		
		//禁止右击刷新
		document.oncontextmenu = function(){
			return false;
		}
		//时间减少
		function countDown(){
			second--;
			if(second == 0){
				if(time ==0 && second ==0){
					clearInterval(timer);
					//自动交卷
					alert("交卷时间已到，系统已自动为您交卷...");
					autoAssignment();
				}else{
					time--;
					second =59;
				}
			}
			var str1= second<10?'0'+second:second;
			var str2 = time<10?'0'+time:time;
			$("#surplustime>span").text(str2+":"+str1);
			
		}
		//手动交卷
		function assignment(){
			var ansStr = "";
			var i=0;
			$("#question_ans li").each(function(indedx,item){
				i +=1;
				var eid= $(this).attr("id"); 
				eid = eid.substring(eid.indexOf("_")+1);//获取题目编号和题目类型编号 Q_1-1
				var ans = $(this).find("span").eq(0).text(); //获取答案
				if( ans == ""){
					 var mymessage=confirm("第"+i+"题未作答，您确认要交卷吗?");
					  if(mymessage==true)
					  {  
						ans ="";
					  } else{ 
						  return;
					  }
				}
				ansStr +=eid+"-"+ans+",";
				
			});
			var surplus;
			var a = parseFloat(time +second/60).toFixed(3);
			surplus = a.substring(0, a.toString().length - 1);
			alert(ansStr+surplus);
			$.post("../../AnsPaperServlet",{op:"addAnsPaper",ans:ansStr,surplus:surplus},function(data){
				data = parseInt($.trim(data));
				if(data >0){
					alert("您已成功提交试卷,即将跳转到主页...");
					window.location.href="../index.jsp";
				}else{
					alert("交卷失败...");
				}
			},"json");
		}
		//自动交卷
		function autoAssignment(){
			var ansStr = "";
			var i=0;
			$("#question_ans li").each(function(indedx,item){
				i +=1;
				var eid= $(this).attr("id"); 
				eid = eid.substring(eid.indexOf("_")+1);//获取题目编号和题目类型编号 Q_1-1
				var ans = $(this).find("span").eq(0).text(); //获取答案
				if( ans == ""){
					ans ="";
				}
				ansStr +=eid+"-"+ans+",";
				
			});
			var surplus;
			var a = parseFloat(time +second/60).toFixed(3);
			surplus = a.substring(0, a.toString().length - 1);
			$.post("../../AnsPaperServlet",{op:"addAnsPaper",ans:ansStr,surplus:surplus},function(data){
				data = parseInt($.trim(data));
				if(data >0){
					alert("您已成功提交试卷,即将跳转到主页...");
					window.location.href="../index.jsp";
				}else{
					alert("交卷失败...");
				}
			},"json");
		}
	</script>
</body>
</html>