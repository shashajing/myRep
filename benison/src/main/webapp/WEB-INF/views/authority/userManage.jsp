<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
   <meta charset="UTF-8">
   <link rel="stylesheet" href="../static/admin/css/common.css">
   <link rel="stylesheet" href="../static/admin/css/main.css">
   <script type="text/javascript" src="../static/admin/js/jquery.min.js"></script>
   <script type="text/javascript" src="../static/admin/js/colResizable-1.3.min.js"></script>
   <script type="text/javascript" src="../static/admin/js/common.js"></script>
   <title>Document</title>
   <style type="text/css">
	.hidden{display:none;}
	#www_zzjs_net{width:498px; height:100px;padding:4px 10px 10px;background-color:#FFFFFF;border:1px solid #05549d;color:#333333;line-height:24px;text-align:left;-webkit-box-shadow:5px 2px 6px #000;-moz-box-shadow:3px 3px 6px #555;}
   </style>

<script type="text/javascript">
//判断浏览器ie6创建的div样式和非ie6创建的div样式
//创建div
function showUserEditDiv(idName,displayFlag) {
	var div = document.getElementById(idName);
	if(displayFlag == 0){
		div.style.display = "none";
	} else {
		div.style.display = "block";
	}
}

function initAdd(){
	restEditForm();
	showUserEditDiv('edtiDiv',1);
}

function restEditForm(){
	//$("#editForm")[0].reset();
	$("#editForm").find(":input").not(":button,:submit,:reset").val("").removeAttr("checked").removeAttr("selected");
}

function initEdit(id,operateType){
	if(id && id != ''){
		if(operateType == 'delete'){
			if(confirm("是否将此用户信息删除?")){
				window.location.href="${ctx}/admin/user!deleteUser.action?id="+id+"&operateType="+operateType;
			}
		} else {
			window.location.href="${ctx}/admin/user!initInput.action?id="+id+"&operateType="+operateType;
		}
	}
}

</script>
</head>
 <body>
  <div class="container">
    <div id="edtiDiv" class="mt10" style="display:none;">
        <div class="box">
          <div class="box_border">
            <div class="box_top"><b class="pl15">用户编辑</b></div>
            <div class="box_center">
						<form id="editForm" action="${ctx}/admin/user!editUser.action?"
							class="jqtransform" method="post">
							<table class="form_table pt15 pb15" width="100%" border="0"
								cellpadding="0" cellspacing="0">
								<tr>
									<td class="td_right">用户名：</td>
									<td class=""><input type="text" name="editUser.userName" value="${editUser.userName}"
										class="input-text lh30" size="40">
										<input type="hidden" name="editUser.userId" value="${editUser.userId}">
									</td>
									<td class="td_right">登录账号：</td>
									<td><input type="text" name="editUser.loginName" value="${editUser.loginName}"
										class="input-text lh30" size="40"></td>
								</tr>
								<tr>
									<td class="td_right">登录密码：</td>
									<td><input type="text" name="editUser.loginPassword" value="${editUser.loginPassword}"
										class="input-text lh30" size="40">
									</td>
									
									<td class="td_right">电子邮箱：</td>
									<td><input type="text" name="editUser.email" value="${editUser.email}"
										class="input-text lh30" size="40">
									</td>
								</tr>
								<tr>
									<td class="td_right">联系电话：</td>
									<td><input type="text" name="editUser.tel" value="${editUser.tel}"
										class="input-text lh30" size="40"></td>
									<td class="td_right">用户类型：</td>
									<td class="">
										<div class="select_border">
											<div class="select_containers ">
												<select name="editUser.type" class="select">
													<option value="1" ${editUser.type==1?'selected="selected"':''}>普通用户</option>
													<option value="2" ${editUser.type==2?'selected="selected"':''}>管理员</option>
												</select>
											</div>
										</div>
									</td>
									<td class="td_right">是否有效：</td>
									<td class="">
										<div class="select_border">
											<div class="select_containers ">
												<select name="editUser.status" class="select">
													<option value="1" ${editUser.status==1?'selected="selected"':''}>有效</option>
													<option value="0" ${editUser.status==0?'selected="selected"':''}>无效</option>
												</select>
											</div>
										</div>
									</td>
								</tr>
								<tr>
									<td class="td_right">&nbsp;</td>
									<td class="">
										<input type="submit" id="buttonSave" name="button1" class="btn btn82 btn_save2" value="保存"> 
										<input type="button" id="buttonReset" name="button2" class="btn btn82 btn_res" onclick="restEditForm()" value="重置">
										<input type="button" name="button3" class="btn btn82 btn_res" value="关闭" onclick="showUserEditDiv('edtiDiv',0);">
									</td>
								</tr>
							</table>
						</form>
					</div>
          </div>
        </div>
     </div>
     <form id="searchForm" action="${ctx}/admin/user.action?operateType=search" method="post">
     	<input type="hidden" id="common_currentPage" name="page.currentPage" />
	    <div id="search_bar" class="">
	       <div class="box">
	          <div class="box_border">
	            <div class="box_top"><b class="pl15">查询</b></div>
	            <div class="box_center pt10 pb10">
	              <table class="form_table" border="0" cellpadding="0" cellspacing="0">
	                <tr>
	                  <td>用户名</td>
	                  <td><input type="text" name="searchUser.userName" value="${searchUser.userName}" class="input-text lh25" size="10"></td>
	                  <td>登录账号</td>
	                  <td><input type="text" name="searchUser.loginName" value="${searchUser.loginName}" class="input-text lh25" size="10"></td>
	                  <td>用户类型</td>
	                  <td>
	                    <span class="fl">
	                      <div class="select_border"> 
	                        <div class="select_containers "> 
	                        <select name="searchUser.type" class="select">
	                        	<option value="">所有</option>
		                        <option value="1" ${searchUser.type==1?'selected="selected"':''}>普通用户</option> 
		                        <option value="2" ${searchUser.type==2?'selected="selected"':''}>管理员</option> 
	                        </select> 
	                        </div> 
	                      </div> 
	                    </span>
	                  </td>
	                  <td>状态</td>
	                  <td>
	                    <span class="fl">
	                      <div class="select_border"> 
	                        <div class="select_containers "> 
		                        <select name="searchUser.status" class="select">
			                        <option value="">所有</option>
			                        <option value="1" ${searchUser.status==1?'selected="selected"':''}>有效</option> 
			                        <option value="0" ${searchUser.status==0?'selected="selected"':''}>无效</option> 
		                        </select> 
	                        </div> 
	                      </div> 
	                    </span>
	                  </td>
	                  <td><input type="submit" name="button" class="btn btn82 btn_search" value="查询"> </td>
	                </tr>
	              </table>
	            </div>
	          </div>
	        </div>
	    </div>
	    <div id="button" class="mt10">
	       <input type="button" name="button" class="btn btn82 btn_add" value="新增" onclick="initAdd();"> 
	       <input type="button" name="button" class="btn btn82 btn_del" value="删除" onclick="batchDelete();"> 
	       <input type="button" name="button" class="btn btn82 btn_checked" value="全选" onclick="allSelect();">
	       <input type="button" name="button" class="btn btn82 btn_export" value="导出">
	         
	     </div>
	     <div id="table" class="mt10">
	        <div class="box span10 oh">
	              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
	                <tr>
	                   <th width="3%"><input type="checkbox" id="checkboxHead" onclick="idSelect()"></th>
	                   <th width="12%">用户名称</th>
	                   <th width="12%">登录账号</th>
	                   <th width="10%">用户类型</th>
	                   <th width="15%">邮箱</th>
	                   <th width="10%">电话</th>
	                   <th width="5%">状态</th>
	                   <th width="10%">编辑时间</th>
	                   <th width="10%">操作人</th>
	                   <th width="13%">操作</th>
	                </tr>
	                
	                <s:iterator value="userList">
		                <tr class="tr">
		                   <td class="td_center"><input type="checkbox" name="idCheckboxGroup" value="${userId}"></td>
		                   <td>${userName}</td>
		                   <td>${loginName}</td>
		                   <td>
			                   <s:if test="type == 1">普通用户</s:if>
			                   <s:elseif test="type == 2">管理员</s:elseif>
		                   </td>
		                   <td>${email}</td>
		                   <td>${tel}</td>
		                   <td>
			                   <s:if test="status == 1">有效</s:if>
			                   <s:elseif test="status == 0">无效</s:elseif>
		                   </td>
		                   <td><fmt:formatDate value="${editTime}" pattern="yyyy-MM-dd HH:mm"/></td>
		                   <td>${editUserName}</td>
		                   <td>
		                   		<a href="javascript:;" onclick="initEdit('${userId}','view');">查看</a>
		                   		<a href="javascript:;" onclick="initEdit('${userId}','edit');">编辑</a>
		                   		<a href="javascript:;" onclick="initEdit('${userId}','delete');">删除</a>
		                   </td> 
		                </tr>
	                </s:iterator>
	              
	              </table>
	              <div class="page mt10">
	                <div class="pagination">
	                  <ul>
	                      <s:if test="page.currentPage == 1"><li class="disabled"><span>首页</span></li></s:if>
	                      <s:else><li><a href="#" onclick="pageSearch(1, 'searchForm')">首页</a></li></s:else>
	                      <s:if test="page.currentPage == 1"><li class="disabled"><span>上一页</span></li></s:if>
	                      <s:else><li><a href="#" onclick="pageSearch(${page.currentPage - 1}, 'searchForm')">上一页</a></li></s:else>
	                      
				          <s:iterator value="page.pageShowList" var="value">
				          	<s:if test="#value == page.currentPage">
				          		<li><a href="#" onclick="pageSearch(${value}, 'searchForm')"><font style="color:red">${value}</font></a></li>
				          	</s:if>
			          		<s:else>
			          			<li><a href="#" onclick="pageSearch(${value}, 'searchForm')">${value}</a></li>
			          		</s:else>
				          </s:iterator>
	                      
	                      <s:if test="page.currentPage == page.pageTotal"><li class="disabled"><span>下一页</span></li></s:if>
	                      <s:else><li><a href="#" onclick="pageSearch(${page.currentPage + 1}, 'searchForm')">下一页</a></li></s:else>
	                      <s:if test="page.currentPage == page.pageTotal"><li class="disabled"><span>末页</span></li></s:if>
	                      <s:else><li><a href="#" onclick="pageSearch(${page.pageTotal}, 'searchForm')">末页</a></li>
	                      </s:else><li class="last-child"><a href="#">共${page.total}条,${page.pageTotal}页</a></li>
	                  </ul>
	                </div>
	              </div>
	        </div>
	     </div>
     </form>
   </div> 
 </body>
<script type="text/javascript">
$(function(){  
  $(".list_table").colResizable({
    liveDrag:true,
    gripInnerHtml:"<div class='grip'></div>", 
    draggingClass:"dragging", 
    minWidth:30
  });
  var operateType = "${operateType}";
  if(operateType != ''){ 
	  if(operateType == 'update' || operateType == 'edit' || operateType == 'view'){
		  if(operateType == 'view'){
			  $("#buttonSave").hide();
			  $("#buttonReset").hide();
		  }else {
			  $("#buttonSave").show();
			  $("#buttonReset").show();
		  }
		  showUserEditDiv('edtiDiv',1);
	  } else {
		  showUserEditDiv('edtiDiv',0);
	  }
  }
});

function idSelect(){
	if($("#checkboxHead").attr("checked")){
		$("[name = idCheckboxGroup]:checkbox").attr("checked", true);
	}else {
		$("[name = idCheckboxGroup]:checkbox").attr("checked", false);
	}
}

function allSelect(){
	$("#checkboxHead").attr("checked",true);
    $("[name = idCheckboxGroup]:checkbox").attr("checked", true);
}
function batchDelete(){
	var ids = getSelectId();
	if(ids == ''){
		alert("请勾选数据！");
		return;
	}
	if(confirm("是否确定删除这些数据?")){
		window.location.href="${ctx}/admin/user!deleteUser.action?id="+ids;
	}
}

function getSelectId(){
	var result = new Array();
	$("[name = idCheckboxGroup]:checkbox").each(function () {
			if ($(this).is(":checked")) {
				result.push($(this).attr("value"));
			}
		 });
	return result.join(",");
}
//翻页相关
function pageSearch(currentPage, searchForm){
	$("#common_currentPage").attr("value",currentPage);
	var form = "#"+searchForm;
	$(form).submit();
	$("#common_currentPage").attr("value",'');
}


</script>
</html>