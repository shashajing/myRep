<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
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
    
    <div id="search_bar" class="mt10">
       <div class="box">
          <div class="box_border">
            <div class="box_top"><b class="pl15">查询</b></div>
            <div class="box_center pt10 pb10">
            <form action="/benison/admin/user.action">
	              <table class="form_table" border="0" cellpadding="0" cellspacing="0">
	                <tr>
	                  <td>姓名</td>
	                  <td><input type="text" name="name" class="input-text lh25" size="10"></td>
	                  <td>性别</td>
	                  <td>
	                    <span class="fl">
	                      <div class="select_border"> 
	                        <div class="select_containers "> 
	                        <select name="" class="select"> 
	                        <option>男</option> 
	                        <option>女</option> 
	                        </select> 
	                        </div> 
	                      </div> 
	                    </span>
	                  </td>
	                  <td>姓名</td>
	                  <td><input type="text" name="name" class="input-text lh25" size="10"></td>
	                  <td>性别</td>
	                  <td>
	                    <span class="fl">
	                      <div class="select_border"> 
	                        <div class="select_containers "> 
	                        <select name="" class="select"> 
	                        <option>男</option> 
	                        <option>女</option> 
	                        </select> 
	                        </div> 
	                      </div> 
	                    </span>
	                  </td>
	                  <td>姓名</td>
	                  <td><input type="text" name="name" class="input-text lh25" size="10"></td>
	                  <td>性别</td>
	                  <td>
	                    <span class="fl">
	                      <div class="select_border"> 
	                        <div class="select_containers "> 
	                        <select name="" class="select"> 
	                        <option>男</option> 
	                        <option>女</option> 
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
                   <th width="100">用户名</th>
                   <th width="100">登录名</th>
                   <th width="100">邮箱</th>
                   <th width="100">电话</th>
                   <th >状态</th>
                </tr>
                
                <s:iterator value="userList">
	                <tr class="tr">
	                   <td class="td_center"><input type="checkbox"></td>
	                   <td>${userName}</td>
	                   <td>${loginName}</td>
	                   <td>${email}</td>
	                   <td>${tel}</td>
	                   <td>${status}</td>
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
              <form action="" class="jqtransform">
               <table class="form_table pt15 pb15" width="100%" border="0" cellpadding="0" cellspacing="0">
                 <tr>
                  <td class="td_right">输入框：</td>
                  <td class=""> 
                    <input type="text" name="name" class="input-text lh30" size="40">
                  </td>
                  <td class="td_right">输入框：</td><td><input type="text" name="name" class="input-text lh30" size="40"></td>
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
                  <td class="td_right">文本框：</td>
                  <td class="">
                    <textarea name="" id="" cols="30" rows="10" class="textarea"></textarea>
                  </td>
                 </tr>
                 <tr>
                  <td class="td_right">单选：</td>
                  <td class="">
                    <input type="radio" name="status"> 可用
                    <input type="radio" name="status"> 不可用
                  </td>
                 </tr>
                 <tr>
                  <td class="td_right">多选：</td>
                  <td class="">
                    <input type="checkbox" name="check" > 1
                    <input type="checkbox" name="check" > 2
                    <input type="checkbox" name="check" > 3
                  </td>
                </tr>
                <tr>
                  <td class="td_right">文件：</td>
                  <td class=""><input type="file" name="file" class="input-text lh30" size="10"></td>
                 </tr>
                 <tr>
                   <td class="td_right">&nbsp;</td>
                   <td class="">
                     <input type="button" name="button" class="btn btn82 btn_save2" value="保存"> 
                    <input type="button" name="button" class="btn btn82 btn_res" value="重置"> 
                   </td>
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