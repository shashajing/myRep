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
   
   <script type="text/javascript">
      $(function(){  
        $(".list_table").colResizable({
          liveDrag:true,
          gripInnerHtml:"<div class='grip'></div>", 
          draggingClass:"dragging", 
          minWidth:30
        }); 
        
      }); 
   </script>
   <title>Document</title>
 </head>
 <body>
  <div class="container">
    
    <div id="search_bar" class="">
       <div class="box">
          <div class="box_border">
            <div class="box_top"><b class="pl15">查询</b></div>
            <div class="box_center pt10 pb10">
            <form action="${ctx}/admin/user.action">
	              <table class="form_table" border="0" cellpadding="0" cellspacing="0">
	                <tr>
	                  <td>用户名</td>
	                  <td><input type="text" name="user.userName" class="input-text lh25" size="10"></td>
	                  <td>登录账号</td>
	                  <td><input type="text" name="user.loginName" class="input-text lh25" size="10"></td>
	                  <td>用户类型</td>
	                  <td>
	                    <span class="fl">
	                      <div class="select_border"> 
	                        <div class="select_containers "> 
	                        <select name="user.type" class="select"> 
	                        <option value="1">普通用户</option> 
	                        <option value="2">管理员</option> 
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
	                        <select name="user.type" class="select"> 
	                        <option value="1">有效</option> 
	                        <option value="0">失效</option> 
	                        </select> 
	                        </div> 
	                      </div> 
	                    </span>
	                  </td>
	                  
	                  
	                  
	                  <td><input type="submit" name="button" class="btn btn82 btn_search" value="查询"> </td>
	                </tr>
	              </table>
              </form>
            </div>
          </div>
        </div>
    </div>
    <div id="button" class="mt10">
       <input type="button" name="button" class="btn btn82 btn_add" value="新增"> 
       <input type="button" name="button" class="btn btn82 btn_del" value="删除"> 
       <input type="button" name="button" class="btn btn82 btn_checked" value="全选"> 
       <input type="button" name="button" class="btn btn82 btn_export" value="导出">
         
     </div>
     <div id="table" class="mt10">
        <div class="box span10 oh">
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
                <tr>
                   <th width="30"><input type="checkbox"></th>
                   <th width="100">用户名称</th>
                   <th width="100">登录账号</th>
                   <th width="100">用户类型</th>
                   <th width="100">邮箱</th>
                   <th width="100">电话</th>
                   <th width="100">状态</th>
                   <th width="130">编辑时间</th>
                   <th width="100">操作人</th>
                   <th >操作</th>
                </tr>
                
                <s:iterator value="userList">
	                <tr class="tr">
	                   <td class="td_center"><input type="checkbox"></td>
	                   <td>${userName}</td>
	                   <td>${loginName}</td>
	                   <td>
		                   <s:if test="type == 1">普通用户</s:if>
		                   <s:elseif test="type == 0">管理员</s:elseif>
	                   </td>
	                   <td>${email}</td>
	                   <td>${tel}</td>
	                   <td>
		                   <s:if test="status == 1">有效</s:if>
		                   <s:elseif test="status == 0">无效</s:elseif>
	                   </td>
	                   <td><fmt:formatDate value="${editTime}" pattern="yyyy-MM-dd HH:mm"/></td>
	                   <td>${editUserName}</td>
	                   <td></td>
	                </tr>
                </s:iterator>
              
              </table>
              <div class="page mt10">
                <div class="pagination">
                  <ul>
                      <li class="first-child"><a href="#">首页</a></li>
                      <li class="disabled"><span>上一页</span></li>
                      <li class="active"><span>1</span></li>
                      <li><a href="#">2</a></li>
                      <li><a href="#">下一页</a></li>
                      <li class="last-child"><a href="#">末页</a></li>
                  </ul>
                </div>

              </div>
        </div>
     </div>
     
     
      
     <div id="forms" class="mt10">
        <div class="box">
          <div class="box_border">
            <div class="box_top"><b class="pl15">表单</b></div>
            <div class="box_center">
              <form action="${ctx}/admin/user!addUser.action" class="jqtransform" method="post">
					<table class="form_table pt15 pb15" width="100%" border="0"
						cellpadding="0" cellspacing="0">
						<tr>
							<td class="td_right">用户名：</td>
							<td class=""><input type="text" name="user.userName"
								class="input-text lh30" size="40"></td>
						</tr>
						<tr>
							<td class="td_right">登录账号：</td>
							<td><input type="text" name="user.loginName"
								class="input-text lh30" size="40"></td>
						</tr>
						<tr>
							<td class="td_right">登录密码：</td>
							<td><input type="text" name="user.loginPassword"
								class="input-text lh30" size="40"></td>
						</tr>
						<tr>
							<td class="td_right">电子邮箱：</td>
							<td><input type="text" name="user.email"
								class="input-text lh30" size="40"></td>
						</tr>
						<tr>
							<td class="td_right">联系电话：</td>
							<td><input type="text" name="user.tel"
								class="input-text lh30" size="40"></td>
						</tr>
						<tr >
                  <td class="td_right">下拉框：</td>
                  <td class="">
 
                    <span class="fl">
                      <div class="select_border"> 
                        <div class="select_containers "> 
                        <select name="" class="select"> 
                        <option>北京</option> 
                        <option>天津</option> 
                        <option>上海</option> 
                        <option>重庆</option> 
                        </select> 
                        </div> 
                      </div> 
                    </span>
                  </td>
                 </tr>
						
						<tr>
							<td class="td_right">&nbsp;</td>
							<td class=""><input type="submit" name="button"
								class="btn btn82 btn_save2" value="保存"> <input
								type="button" name="button" class="btn btn82 btn_res"
								value="重置"></td>
						</tr>
					</table>
				</form>
            </div>
          </div>
        </div>
     </div>
   </div> 
 </body>
</html>