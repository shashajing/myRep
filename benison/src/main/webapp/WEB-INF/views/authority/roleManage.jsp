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
   
   <link rel="stylesheet" type="text/css" href="../static/admin/dataTables/css/jquery.dataTables.css">
   
   <script type="text/javascript" src="../static/admin/js/jquery.min.js"></script>
   <script type="text/javascript" src="../static/admin/js/colResizable-1.3.min.js"></script>
   <script type="text/javascript" src="../static/admin/js/common.js"></script>
   <script type="text/javascript" src="../static/admin/dataTables/js/jquery.dataTables.js"></script>
   
   <title>Document</title>
   <style type="text/css">
	.hidden{display:none;}
	#www_zzjs_net{width:498px; height:100px;padding:4px 10px 10px;background-color:#FFFFFF;border:1px solid #05549d;color:#333333;line-height:24px;text-align:left;-webkit-box-shadow:5px 2px 6px #000;-moz-box-shadow:3px 3px 6px #555;}
   </style>

<script type="text/javascript">


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
      <div id="" class="mt5" >
      	 <table>
      	 	<tr>
      	 		<td width="40%">
					<table cellpadding="0" cellspacing="0" border="0" class="cell-border" 
					       id="userRole_table">
					    <thead>
					    <tr>
					        <th>xxxxx</th>
					        <th>bbbbb</th>
					        <th>操作</th>
					    </tr>
					    </thead>
					    <tbody>
					    </tbody>
					</table>

      	 		</td>
      	 		<td width="30%"></td>
      	 		<td width="40%">
      	 			<table cellpadding="0" cellspacing="0" border="0" class="cell-border" 
					       id="user_table">
					    <thead>
					    <tr>
					        <th width="15%"><input type="checkbox" id='userCheckAll'></th>
					        <th width="30%">用户名</th>
					        <th width="30%">账号</th>
					        <th width="25%">电话</th>
					    </tr>
					    </thead>
					    <tbody>
					    </tbody>
					</table>
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
  
//checkbox全选
  $("#userCheckAll").live("click", function () {
      if ($(this).attr("checked") === "checked") {
          $("input[name='userCheckList']").attr("checked", $(this).attr("checked"));
      } else {
          $("input[name='userCheckList']").attr("checked", false);
      }
  });
  
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
var userRoleTable;
var searchRoleId;
var userTable;
function initRoleUser(roleId) {
	if(!roleId || roleId == ''){
		return;
	}
	searchRoleId = roleId;
	var userRoleUrl = "${ctx}/admin/userRoleSearch.action";// + roleId+"&date="+ new Date().getTime();
	if(userRoleTable){
		userRoleTable.fnDestroy(); 
	}
	userRoleTable = $('#userRole_table').dataTable({
		sAjaxSource: userRoleUrl,
        bPaginate: false,//开启分页功能，如果不开启，将会全部显示 
        bServerSide: true,
        bProcessing: true,//开启读取服务器数据时显示正在加载中
        bFilter:false,//控制是否显示search框
        bInfo:false,//控制是否显示表格上的数据信息
        "bSort": false,
        "sScrollY": "300px",//是否开启垂直滚动
        "sScrollX": "100%",
        "bScrollCollapse": true,
        columns: [
                  {data:'roleName'},
                  {data:'userName'},
                  {
                      "mDataProp": "id",
                      "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                          $(nTd).html("<a href='javascript:void(0);' onclick='deleteUserRole(" + sData + ")'>删除</a>");
                      }
                  }
	 			  ],
	 	"fnServerData":retrieveData, //与后台交互获取数据的处理函数 
        "fnInitComplete": function (oSettings, json) {
        },
    } );
	
	initUserTable(roleId);
}

function initUserTable(roleId) {
	if(!roleId || roleId == ''){
		return;
	}
	var url = "${ctx}/admin/role!userSearch.action";// + roleId+"&date="+ new Date().getTime();
	if(userTable){
		userTable.fnDestroy(); 
	}
	userTable = $('#user_table').dataTable({
		sAjaxSource: url,
        bPaginate: false,//开启分页功能，如果不开启，将会全部显示 
        bServerSide: true,
        bProcessing: true,//开启读取服务器数据时显示正在加载中
        bFilter:false,//控制是否显示search框
        bInfo:false,//控制是否显示表格上的数据信息
        "bSort": false,
        "sScrollY": "300px",//是否开启垂直滚动
        "sScrollX": "100%",
        "bScrollCollapse": true,
        columns: [
				  {
				    "mDataProp": "userId",
				    "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
				        $(nTd).html("<input type='checkbox' name='userCheckList' value='" + sData + "'/>");
				    }
				  },
                  {data:'userName'},
                  {data:'loginName'},
                  {data:'tel'}
	 			  ],
	 	"fnServerData":retrieveData, //与后台交互获取数据的处理函数 
	 	"sDom": "<'row-fluid'<'span6 myBtnBox'><'span6'f>r>t<'row-fluid'<'span6'i><'span6 'p>>",
        "fnInitComplete": function (oSettings, json) {
            $('<a href="#" class="btn btn-danger" id="addUserToRole">添加到当前角色</a>').appendTo($('.myBtnBox'));
            $("#addUserToRole").click(addUserToRoleFun);
        }
        
        
    } );
}

//函数的参数是固定，不能更改。  
function retrieveData(sSource, aoData, fnCallback ) {
    //查询条件称加入参数数组     
    $.ajax( {     
        type: "POST",      
        //contentType: "application/json",   //这段代码不要加，我时延的是否后台会接受不到数据  
        url: sSource,   
        dataType:"json",  
        data:"id="+searchRoleId, //以json格式传递(struts2后台还是以string类型接受),year和month直接作为参数传递。  
        success: function(data) {   
           $("#url_sortdata").val(data.data);  
            fnCallback(data); //服务器端返回的对象的returnObject部分是要求的格式     
        }     
    });    
} 

//删除用户角色
function deleteUserRole(id) {
	if(!confirm("是否确定删除?")){
		return;
	}
    $.ajax({
        url: "${ctx}/admin/role!deleteUserRole.action",
        dataType:'json',
        data: {"id": id},
        type: "post",
        success: function (backdata) {
            if (backdata.success) {
                //userRoleTable.fnReloadAjax(userRoleTable.fnSettings());
            	initRoleUser(searchRoleId);
            } else {
                alert(backdata.message);
            }
        }, error: function (error) {
            console.log(error);
        }
    });
}

function addUserToRoleFun() {
    var str = '';
    $("input[name='userCheckList']:checked").each(function (i, o) {
        str += $(this).val();
        str += ",";
    });
    if (str.length > 0) {
        var ids = str.substr(0, str.length - 1);
        
        if(confirm("是否确定将这些用户添加到当前角色?")){
        	$.ajax({
                url: "${ctx}/admin/role!addUserRole.action",
                dataType:'json',
                data: {"id": ids,"roleId":searchRoleId},
                type: "post"
                success: function (backdata) {
                    if (backdata.success) {
                    	initRoleUser(searchRoleId);
                    } else {
                        alert(backdata.message);
                    }
                }, error: function (error) {
                    console.log(error);
                }
            });
    	}
    } else {
        alert("至少选择一个用户!");
    }
}




</script>
</html>