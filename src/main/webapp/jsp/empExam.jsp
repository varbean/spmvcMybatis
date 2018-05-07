<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<title>regist</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/style.css" />
	</head>
	<body>
		<div id="wrap">
			<div id="top_content">
					<div id="header">
						<div id="rightheader">
							<p>
								2009/11/20
								<br />
							</p>
						</div>
						<div id="topheader">
							<h1 id="title">
								<a href="#">main</a>
							</h1>
						</div>
						<div id="navigation">
						</div>
					</div>
				<div id="content">
					<p id="whereami">
					</p>
					<h1>
						员工考核
					</h1>
					<form action="${pageContext.request.contextPath}/infor.do?method=create" method="post">
						<table cellpadding="0" cellspacing="0" border="0"
							class="form_table">
							<tr>
								<td valign="middle" align="right">
									员工编号：
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="empNo" value="${param.empNo}" readonly/>
									
								</td>
							</tr>
							
							
							<tr>
							   <td valign="middle" align="right">
									日常考勤
								</td>
								<td valign="middle" align="left">
								     <select name="punchIn">
								     <c:if test="${sessionScope.employee.empLevel<2}">
								           <option value="-20">-20</option>
								     	</c:if>
								       <c:if test="${sessionScope.employee.empLevel<3}">
								           <option value="-15">-15</option>
								     	</c:if>
								     	<c:if test="${sessionScope.employee.empLevel<4}">
								           <option value="-10">-10</option>
								     	</c:if>
								       <option value="-5">-5</option>
								       <option value="0" selected="selected">0</option>
								       <option value="5">5</option>
								       
								       <c:if test="${sessionScope.employee.empLevel<4}">
								           <option value="10">10</option>
								       </c:if>
								       <c:if test="${sessionScope.employee.empLevel<3}">
								           <option value="15">15</option>
								     	</c:if>
								     	<c:if test="${sessionScope.employee.empLevel<2}">
								           <option value="20">20</option>
								     	</c:if>
								     </select>
								</td>
							</tr>
							<tr>
							   <td valign="middle" align="right">
									工作态度
								</td>
								<td valign="middle" align="left">
								     <select name="attitude">
								       
								           <c:if test="${sessionScope.employee.empLevel<2}">
								           <option value="-20">-20</option>
								     	</c:if>
								       <c:if test="${sessionScope.employee.empLevel<3}">
								           <option value="-15">-15</option>
								     	</c:if>
								     	<c:if test="${sessionScope.employee.empLevel<4}">
								           <option value="-10">-10</option>
								     	</c:if>
								       <option value="-5">-5</option>
								       <option value="0" selected="selected">0</option>
								       <option value="5">5</option>
								       
								       <c:if test="${sessionScope.employee.empLevel<4}">
								           <option value="10">10</option>
								       </c:if>
								       <c:if test="${sessionScope.employee.empLevel<3}">
								           <option value="15">15</option>
								     	</c:if>
								     	<c:if test="${sessionScope.employee.empLevel<2}">
								           <option value="20">20</option>
								     	</c:if>
								     
								     </select>
								</td>
							</tr>
							<tr>
							   <td valign="middle" align="right">
									工作业绩
								</td>
								<td valign="middle" align="left">
								     <select name="achievement">
								       
								          <c:if test="${sessionScope.employee.empLevel<2}">
								           <option value="-20">-20</option>
								     	</c:if>
								       <c:if test="${sessionScope.employee.empLevel<3}">
								           <option value="-15">-15</option>
								     	</c:if>
								     	<c:if test="${sessionScope.employee.empLevel<4}">
								           <option value="-10">-10</option>
								     	</c:if>
									       <option value="-5">-5</option>
									       <option value="0" selected="selected">0</option>
									       <option value="5">5</option>
								       
								       <c:if test="${sessionScope.employee.empLevel<4}">
								           <option value="10">10</option>
								       </c:if>
								       <c:if test="${sessionScope.employee.empLevel<3}">
								           <option value="15">15</option>
								     	</c:if>
								     	<c:if test="${sessionScope.employee.empLevel<2}">
								           <option value="20">20</option>
								     	</c:if>
								     
								     </select>
								</td>
							</tr>
							
							
							
						</table>
						<p>
							<input type="submit" class="button" value="Submit &raquo;" />
						</p>
					</form>
				</div>
			</div>
			<div id="footer">
				<div id="footer_bg">
				ABC@126.com
				</div>
			</div>
		</div>
	</body>
</html>
