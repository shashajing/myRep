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
   
   <link type="text/css" rel="stylesheet" href="../static/admin/jqGrid/themes/cupertino/jquery-ui.min.css">
   <link type="text/css" rel="stylesheet" href="../static/admin/jqGrid/themes/ui.jqgrid.css">
   
   <script type="text/javascript" src="../static/admin/js/jquery.min.js"></script>
   <script type="text/javascript" src="../static/admin/js/colResizable-1.3.min.js"></script>
   <script type="text/javascript" src="../static/admin/js/common.js"></script>
   
   <script type="text/javascript" src="../static/admin/jqGrid/js/i18n/grid.locale-cn.js"></script>
   <script type="text/javascript" src="../static/admin/jqGrid/js/jquery.jqGrid.min.js"></script>
   
   <title>Document</title>
   <style type="text/css">
	.hidden{display:none;}
	#www_zzjs_net{width:498px; height:100px;padding:4px 10px 10px;background-color:#FFFFFF;border:1px solid #05549d;color:#333333;line-height:24px;text-align:left;-webkit-box-shadow:5px 2px 6px #000;-moz-box-shadow:3px 3px 6px #555;}
   </style>

<script type="text/javascript">

//http://blog.mn886.net/jqGrid/
</script>
</head>
 <body>
  <div class="container">
    <div id="edtiDiv" class="mt10" style="display:none;">
        <div class="box">
          <div class="box_border">
            <div class="box_top"><b class="pl15">编辑</b></div>
            <div class="box_center">
						<form id="editForm" action="${ctx}/admin/role!editRole.action"
							class="jqtransform" method="post">
							<table class="form_table pt5 pb5" width="100%" border="0"
								cellpadding="0" cellspacing="0">
								<tr>
									<td class="td_right">角色名称：</td>
									<td class=""><input type="text" name="editRole.roleName" value="${editRole.roleName}"
										class="input-text lh30" size="40">
										<input type="hidden" name="editRole.moduleId" value="${editRole.roleId}">
									</td>
									
									<td class="td_right">角色描述：</td>
									<td><input type="text" name="editRole.moduleUrl" value="${editRole.description}"
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
     <form id="searchForm" action="${ctx}/admin/role.action?operateType=search" method="post">
	    <div id="search_bar" class="">
	       <div class="box">
	          <div class="box_border">
	            <div class="box_top"><b class="pl15">查询</b></div>
	            <div class="box_center">
	              <table class="form_table" border="0" cellpadding="0" cellspacing="0">
	                <tr>
	                  <td>角色名称</td>
	                  <td><input type="text" name="searchRole.roleName" value="${searchRole.roleName}" class="input-text lh25" size="20"></td>
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
	                   <th width="12%">角色名称</th>
	                   <th width="10%">描述</th>
	                   <th width="13%">操作</th>
	                </tr>
	                <s:iterator value="roleList">
		                <tr class="tr">
		                   <td class="td_center"><input type="checkbox" name="idCheckboxGroup" value="${moduleId}"></td>
		                   <td>${roleName}</td>
		                   <td>${description}</td>
		                   <td>
		                   		<a href="javascript:;" onclick="initEdit('${roleId}','view');">查看</a>
		                   		<a href="javascript:;" onclick="initEdit('${roleId}','edit');">编辑</a>
		                   		<a href="javascript:;" onclick="initEdit('${roleId}','delete');">删除</a>
		                   		<a href="javascript:;" onclick="initRoleUser('${roleId}');">角色用户</a>
		                   		<a href="javascript:;" onclick="initEdit('${roleId}');">角色菜单</a>
		                   </td> 
		                </tr>
	                </s:iterator>
	              </table>
	        </div>
	     </div>
     </form>
      <div id="button" class="mt5">
      	 <table>
      	 	<tr>
      	 		<td>
      	 			<table id="roleUserGridTable"></table>
      	 			<!-- jqGrid 分页 div gridPager
				      <div id="gridPager"></div>
				       -->
      	 		</td>
      	 		<td>
      	 			<table id="gridTable1"></table>
      	 		</td>
      	 	</tr>
      	 </table>
      
	      
	      
	   </div>
      
	  
     
     
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
		window.location.href="${ctx}/admin/role!deleteRole.action?id="+ids;
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

//添加数据初始化
function initAdd(){
	restEditForm();
	showModuleEditDiv('edtiDiv',1);
}

//显示或者隐藏编辑表单
function showModuleEditDiv(idName,displayFlag) {
	var div = document.getElementById(idName);
	if(displayFlag == 0){
		div.style.display = "none";
	} else {
		div.style.display = "block";
	}
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
				window.location.href="${ctx}/admin/role!deleteRole.action?id="+id+"&operateType="+operateType;
			}
		} else {
			window.location.href="${ctx}/admin/role!initInput.action?id="+id+"&operateType="+operateType;
		}
	}
}

function initRoleUser(roleId){
	if(!roleId || roleId == ''){
		return;
	}
	var userRoleUrl = "http://127.0.0.1:8081/benison/admin/role!userRoleSearch.action?id=" + roleId+"&date="+ new Date().getTime();
	alert(userRoleUrl);
	$("#roleUserGridTable").jqGrid({
		  url: userRoleUrl,
	      datatype: "json",
	      //postData:{'id':roleId}, //发送数据
	      height: 250,
	      colNames:['ID','角色名称','用户名称'],
	      colModel:[
	              {name:'id',index:'id', width:60, sorttype:"int"},
	              {name:'roleName',index:'roleName', width:90},
	              {name:'userName',index:'userName', width:90}              
	      ],
	      sortname:'id',
	      sortorder:'asc',
	      viewrecords:true,
	      rowNum:10,
	      rowList:[10,20,30],
	      mtype:"get",
	      viewrecords:true,
	      //pager:"#gridPager",
	      caption: "用户角色"
	}).trigger("reloadGrid");
	
	
	$("#gridTable12").jqGrid({
	      datatype: "local",
	      height: 250,
	      colNames:['编号','用户名', '性别', '邮箱', 'QQ'],
	      colModel:[
	              {name:'id',index:'id', width:60, sorttype:"int"},
	              {name:'userName',index:'userName', width:90},
	              {name:'gender',index:'gender', width:90},
	              {name:'email',index:'email', width:125,sorttype:"string"},
	              {name:'QQ',index:'QQ', width:100}                
	      ],
	      sortname:'id',
	      sortorder:'asc',
	      viewrecords:true,
	      rowNum:10,
	      rowList:[10,20,30],
	      //pager:"#gridPager",
	      caption: "第一个jqGrid例子"
	});
	  
	  var mydata = [
	        {id:"1",userName:"polaris",gender:"男",email:"fef@163.com",QQ:"33334444"},
	        {id:"2",userName:"李四",gender:"女",email:"faf@gmail.com",QQ:"222222222"},
	        {id:"3",userName:"王五",gender:"男",email:"fae@163.com",QQ:"99999999"},
	        {id:"4",userName:"马六",gender:"女",email:"aaaa@gmail.com",QQ:"23333333"},
	        {id:"5",userName:"赵钱",gender:"男",email:"4fja@gmail.com",QQ:"22222222"},
	        {id:"6",userName:"小毛",gender:"男",email:"ahfi@yahoo.com",QQ:"4333333"},
	        {id:"7",userName:"小李",gender:"女",email:"note@sina.com",QQ:"21122323"},
	        {id:"8",userName:"小三",gender:"男",email:"oefh@sohu.com",QQ:"242424366"},
	        {id:"9",userName:"孙先",gender:"男",email:"76454533@qq.com",QQ:"76454533"}
	        ];
	  	/* for(var i=0;i<=mydata.length;i++){
	  		jQuery("#roleUserGridTable").jqGrid('addRowData',i+1,mydata[i]);
	  		jQuery("#gridTable1").jqGrid('addRowData',i+1,mydata[i]);
	  	} */
	
}








</script>
</html>