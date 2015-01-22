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
//显示或者隐藏编辑表单
function showModuleEditDiv(idName,displayFlag) {
	var div = document.getElementById(idName);
	if(displayFlag == 0){
		div.style.display = "none";
	} else {
		div.style.display = "block";
	}
}
//添加数据初始化
function initAdd(){
	restEditForm();
	showModuleEditDiv('edtiDiv',1);
}
//重置编辑表单
function restEditForm(){
	$("#editForm").find(":input").not(":button,:submit,:reset").val("").removeAttr("checked").removeAttr("selected");
}
//初始化查看、编辑表单或者单条删除数据
function initEdit(id,operateType){
	if(id && id != ''){
		if(operateType == 'delete'){
			if(confirm("是否将此数据删除?")){
				window.location.href="${ctx}/admin/module!deleteModule.action?id="+id+"&operateType="+operateType;
			}
		} else {
			window.location.href="${ctx}/admin/module!initInput.action?id="+id+"&operateType="+operateType;
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
            <div class="box_top"><b class="pl15">编辑</b></div>
            <div class="box_center">
						<form id="editForm" action="${ctx}/admin/module!editModule.action?"
							class="jqtransform" method="post">
							<table class="form_table pt5 pb5" width="100%" border="0"
								cellpadding="0" cellspacing="0">
								<tr>
									<td class="td_right">菜单名称：</td>
									<td class=""><input type="text" name="editModule.moduleName" value="${editModule.moduleName}"
										class="input-text lh30" size="40">
										<input type="hidden" name="editModule.moduleId" value="${editModule.moduleId}">
									</td>
									
									<td class="td_right">菜单url：</td>
									<td><input type="text" name="editModule.moduleUrl" value="${editModule.moduleUrl}"
										class="input-text lh30" size="40">
									</td>
								</tr>
								<tr>
									<td class="td_right">父菜单：</td>
									<td>
										<select name="editModule.parentId" class="select">
					                        <option value="">所有</option>
					                        <s:iterator value="searchModuleList">
					                        	<s:if test="operateType != 'edit'">
					                        		<option value="${moduleId}" ${moduleId == editModule.parentId?'selected="selected"':''}>${moduleName}</option>
					                        	</s:if>
					                        	<s:elseif test="editModule.moduleId != moduleId">
					                        		<option value="${moduleId}" ${moduleId == editModule.parentId?'selected="selected"':''}>${moduleName}</option>
					                        	</s:elseif>
					                        </s:iterator>
				                        </select> 
									</td>
									
									<td class="td_right">菜单类型：</td>
									<td class="">
										<div class="select_border">
											<div class="select_containers ">
												<select name="editModule.type" class="select">
													<option value=""></option>
													<option value="1" ${editModule.type==1?'selected="selected"':''}>菜单</option>
													<option value="2" ${editModule.type==2?'selected="selected"':''}>按钮</option>
												</select>
											</div>
										</div>
									</td>
									
									<td class="td_right">描述：</td>
									<td><input type="text" name="editModule.description" value="${editModule.description}"
										class="input-text lh30" size="40">
									</td>
								</tr>
								<tr>
									<td class="td_right">&nbsp;</td>
									<td class="">
										<input type="submit" id="buttonSave" name="button1" class="btn btn82 btn_save2" value="保存"> 
										<input type="button" id="buttonReset" name="button2" class="btn btn82 btn_res" onclick="restEditForm()" value="重置">
										<input type="button" name="button3" class="btn btn82 btn_res" value="关闭" onclick="editFormClose();">
									</td>
								</tr>
							</table>
						</form>
					</div>
          </div>
        </div>
     </div>
     <form id="searchForm" action="${ctx}/admin/module.action?operateType=search" method="post">
     	<input type="hidden" id="common_currentPage" name="page.currentPage" />
	    <div id="search_bar" class="">
	       <div class="box">
	          <div class="box_border">
	            <div class="box_top"><b class="pl15">查询</b></div>
	            <div class="box_center">
	              <table class="form_table" border="0" cellpadding="0" cellspacing="0">
	                <tr>
	                  <td>菜单名称</td>
	                  <td><input type="text" name="searchModule.moduleName" value="${searchModule.moduleName}" class="input-text lh25" size="20"></td>
	                  <td>菜单类型</td>
	                  <td>
	                    <span class="fl">
	                      <div class="select_border"> 
	                        <div class="select_containers "> 
	                        <select name="searchModule.type" class="select">
	                        	<option value="">所有</option>
		                        <option value="1" ${searchModule.type==1?'selected="selected"':''}>菜单</option> 
		                        <option value="2" ${searchModule.type==2?'selected="selected"':''}>按钮</option> 
	                        </select> 
	                        </div> 
	                      </div> 
	                    </span>
	                  </td>
	                  <td>父菜单</td>
	                  <td>
	                    <span class="fl">
	                      <div class="select_border"> 
	                        <div class="select_containers "> 
		                        <select name="searchModule.parentId" class="select">
			                        <option value="">所有</option>
			                        <s:iterator value="searchModuleList">
			                        	<s:if test="parentId == null">
			                        		<option value="${moduleId}" ${moduleId == searchModule.parentId?'selected="selected"':''}>${moduleName}</option>
			                        	</s:if>
			                        </s:iterator>
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
	    <div id="button" class="mt5">
	       <input type="button" name="button" class="btn btn82 btn_add" value="新增" onclick="initAdd();"> 
	       <input type="button" name="button" class="btn btn82 btn_del" value="删除" onclick="batchDelete();"> 
	       <input type="button" name="button" class="btn btn82 btn_checked" value="全选" onclick="allSelect();">
	     </div>
	     <div id="table" class="mt5">
	        <div class="box span10 oh">
	              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
	                <tr>
	                   <th width="3%"><input type="checkbox" id="checkboxHead" onclick="idSelect()"></th>
	                   <th width="12%">菜单名称</th>
	                   <th width="12%">上级菜单</th>
	                   <th width="10%">菜单类型</th>
	                   <th width="15%">菜单url</th>
	                   <th width="10%">描述</th>
	                   <th width="10%">操作人</th>
	                   <th width="10%">操作时间</th>
	                   <th width="13%">操作</th>
	                </tr>
	                
	                <s:iterator value="moduleList">
		                <tr class="tr">
		                   <td class="td_center"><input type="checkbox" name="idCheckboxGroup" value="${moduleId}"></td>
		                   <td>${moduleName}</td>
		                   <td>${parentModule}</td>
		                   <td>
			                   <s:if test="type == 1">菜单</s:if>
			                   <s:elseif test="type == 2">按钮</s:elseif>
		                   </td>
		                   <td>${moduleUrl}</td>
		                   <td>${description}</td>
		                   <td>${editor}</td>
		                   <td><fmt:formatDate value="${editTime}" pattern="yyyy-MM-dd HH:mm"/></td>
		                   <td>
		                   		<a href="javascript:;" onclick="initEdit('${ModuleId}','view');">查看</a>
		                   		<a href="javascript:;" onclick="initEdit('${ModuleId}','edit');">编辑</a>
		                   		<a href="javascript:;" onclick="initEdit('${ModuleId}','delete');">删除</a>
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
var operateType = "${operateType}";
$(function(){  
  $(".list_table").colResizable({
    liveDrag:true,
    gripInnerHtml:"<div class='grip'></div>", 
    draggingClass:"dragging", 
    minWidth:30
  });
  //控制编辑表单的显示或者隐藏
  if(operateType != ''){ 
	  if(operateType == 'update' || operateType == 'edit' || operateType == 'view'){
		  if(operateType == 'view'){
			  $("#buttonSave").hide();
			  $("#buttonReset").hide();
		  }else {
			  $("#buttonSave").show();
			  $("#buttonReset").show();
		  }
		  showModuleEditDiv('edtiDiv',1);
	  } else {
		  showModuleEditDiv('edtiDiv',0);
	  }
  }
});
//编辑表单的关闭动作
function editFormClose(){
	if(operateType == 'update' || operateType == 'edit' || operateType == 'view'){
		$("#searchForm").submit();
	} else {
		showModuleEditDiv('edtiDiv',0);
	}
}
//全选单选框动作
function idSelect(){
	if($("#checkboxHead").attr("checked")){
		$("[name = idCheckboxGroup]:checkbox").attr("checked", true);
	}else {
		$("[name = idCheckboxGroup]:checkbox").attr("checked", false);
	}
}
//全选按钮动作
function allSelect(){
	$("#checkboxHead").attr("checked",true);
    $("[name = idCheckboxGroup]:checkbox").attr("checked", true);
}
//批量删除
function batchDelete(){
	var ids = getSelectId();
	if(ids == ''){
		alert("请勾选数据！");
		return;
	}
	if(confirm("是否确定删除这些数据?")){
		window.location.href="${ctx}/admin/module!deleteModule.action?id="+ids;
	}
}
//或者勾选的id
function getSelectId(){
	var result = new Array();
	$("[name = idCheckboxGroup]:checkbox").each(function () {
			if ($(this).is(":checked")) {
				result.push($(this).attr("value"));
			}
		 });
	return result.join(",");
}
//翻页查询
function pageSearch(currentPage, searchForm){
	$("#common_currentPage").attr("value",currentPage);
	var form = "#"+searchForm;
	$(form).submit();
	$("#common_currentPage").attr("value",'');
}
</script>
</html>