<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
		
		
		var currentPageNo=1,sumpage;
		
			$(document).ready(function(){
				paginquery();
			});
			
			function lower(){
				if(currentPageNo >= sumpage){
					alert('已经是最后一页了');
					return;
				}
				currentPageNo = currentPageNo+1;
				paginquery();
			}
			
			function apper(){
				if(currentPageNo <= 1){
					alert('已经是第一页了');
					return;
				}
				currentPageNo = currentPageNo-1;
				paginquery();
			};
			
			function paginquery(){
				$.ajax({
					url : "list",
					type : "post",
					dataType : "json",
					data : "currentPageNo="+currentPageNo,
					success : function(data){
						if(data!=null){	
//	 						alert(data);
//	 						alert(data.length);
							 sumpage=data[0].count;
							//alert(sumpage);
							var str="<tr><td>客户名称</td><td>客户级别</td><td>客户来源</td><td>联系人</td><td>电话</td><td>手机</td><td>操作</td></tr>";
							//alert(str);
							for (var i = 0; i < data.length; i++) {
								str = str+"<tr><td>"+data[i].name+"</td><td>"+data[i].jibie+"</td><td>"+data[i].laiyuan+"</td><td>"+data[i].lianxiren+"</td><td>"+data[i].phone+"</td><td>"+data[i].shouji+"</td><td>删除</td></tr>";
							}
							$("#userinfo").html(str);
							var pagetxt = "<a onclick='apper()' href='javascript:void(0)'>上一页</a>"
							+currentPageNo
							+"/"
							+sumpage
							+"<a onclick='lower()' href='javascript:void(0)'>下一页</a>";
							$("#page").html(pagetxt);
						}
					}
				});
			}
			
			
		
			
			
			
			
			
			
			
		
			//alert("112");
			
			 $("#page_no").change(function(){
		        	var one=$("#one").val();
		        	var page_no=$("#page_no option:selected").val();//获取当前选择项.
		        	//var page_no=document.getElementById("page_no").options[index].value;//获取当前选择项的值.
		        	//alert(page_no);
		        	window.location.href="list?page_no="+page_no;
		        	
		        });
		        
				$(".page-btn").click(function(){
					//验证用户的输入
					 var num=$("#inputPage").val();
		         	var one=$("#one").val();
		         	alert(num);
		         	var page_no=$("#page_no").val();
		         	alert(page_no);
// 					var regexp=/^[1-9]\d*$/;
// 					var totalPageCount = document.getElementById("totalPageCount").value;
					//alert(totalPageCount);
// 					if(!regexp.test(num)){
// 						alert("请输入大于0的正整数！");
// 						return false;
// 					}else if((num-totalPageCount) > 0){
// 						alert("请输入小于总页数的页码");
// 						return false;
// 					}else{
						window.location.href="list?currentPageNo="+num+"&page_no="+page_no;
					//}
				
				});
</script>
</head>
<body>
<form action="Onelist">
客户名称：<input type="text" name="name"> <input type="submit" value="筛选">
</form>

<table id="userinfo" border="1px">
		<tr>
		<td>客户名称</td>
		<td>客户级别</td>
		<td>客户来源</td>
		<td>联系人</td>
		<td>电话</td>
		<td>手机</td>
		<td>操作</td>
		<td>下载</td>
		</tr>
		<c:forEach var="list" items="${list}">
		<tr>
		<td>${list.name }</td>
		<td>${list.jibie }</td>
		<td>${list.laiyuan }</td>
		<td>${list.lianxiren }</td>
		<td>${list.phone}</td>
		<td>${list.shouji }</td>
		<td>${list.shouji }</td>
		<td><a href="download?filename=1.png">下载2</a></td>
		<td><a href="">修改</a> &nbsp;&nbsp;<a id="${list.id }" class="shan" href="Del?id=${list.id }">删除</a></td>
		</tr>
		</c:forEach>
</table>
<dir id="page">

</dir>
<a onclick='apper()' href='javascript:void(0)'>上一页</a>    <a onclick='lower()' href='javascript:void(0)'>下一页</a>
<div >
			<ul class="page-num-ul clearfix">
				<li>共${totalCount }条记录&nbsp;&nbsp; ${currentPageNo }/${totalPageCount }页
				每页显示
				<select name="page_no" id="page_no">
				<c:if test="${page_no != null }">
				<option >${page_no}</option>
				</c:if>
				<option >2</option>
				<option >1</option>
				
				<option>3</option>
				<option>4</option>
				<option>5</option>
				
				</select>条
				</li>
				<c:if test="${url eq 'duoge' }">
				<c:if test="${currentPageNo > 1}">
					<a href="Onelist?currentPageNo=${0}">首页</a>
					<a href="Onelist?currentPageNo=${currentPageNo-1}">上一页</a>
				</c:if>
				<c:if test="${currentPageNo < totalPageCount }">
					<a href="Onelist?currentPageNo=${currentPageNo+1}">下一页</a>
					<a href="Onelist?currentPageNo=${totalPageCount }">最后一页</a>
					<input type="hidden" id="one" value="${url }"/>
					
				</c:if>
				<form action="Onelist">
		
		<span class="page-go-form"><label>跳转至</label>
	     <input type="text" name="currentPageNo" id="inputPage" class="page-key" />页
	     <button type="submit" class="page-btn" >GO</button>
		</span>
                   </form>
				</c:if>
				
				<c:if test="${url != 'duoge' }">
				<c:if test="${currentPageNo > 1}">
					<a href="list?currentPageNo=${0}">首页</a>
					<a href="list?currentPageNo=${currentPageNo-1}">上一页</a>
				</c:if>
				<c:if test="${currentPageNo < totalPageCount }">
					<a href="list?currentPageNo=${currentPageNo+1}">下一页</a>
					<a href="list?currentPageNo=${totalPageCount }">最后一页</a>
					<input type="hidden" id="one" value="${url }"/>
				</c:if>
				
		
		<span class="page-go-form"><label>跳转至</label>
	     <input type="text" name="currentPageNo" id="inputPage" class="page-key" />页
	     <button type="button" class="page-btn" >GO</button>
		</span>
				</c:if>
				

				</ul>
				
				  <a href="download?filename=1.png">下载2</a>
		
      
		 
		</div> 
</body>

</html>