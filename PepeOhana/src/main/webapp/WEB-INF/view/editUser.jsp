<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.UsersInfoDto" %>
<% UsersInfoDto user = (UsersInfoDto)request.getAttribute("user"); %>
<% String error = (String) request.getAttribute("message"); %>

<%--
 *Filename:editUser.jsp
 *
 *Description:
 *	このクラスは、ユーザー情報編集機能を提供するためのものです。
 *	EditUser.javaで抽出したユーザーの情報を入力フォームに表示する
 *	入力フォームに変更したい情報をそれぞれ入力し「更新する」をクリックすると
 *	入力した内容を登録し、マイページ画面へ遷移する
 *	いずれかの項目が入力されず「更新する」をクリックするとアラートを表示する
 *	ユーザー情報の更新に失敗した場合は、画面が遷移されずエラーメッセージを表示する
 *	
 *
 *Author:櫻井
 *Creation Date:2023-09-03
 *
 *Copyright © 2023 KEG Sakura All rights reserved. --%>
 
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
    <title>ユーザー編集 | pepeohana</title>
    
   	<script>
		function validateForm() {
			var inputID = document.getElementById("inputID").value;
			var inputName = document.getElementById("inputName").value;
			var inputPass = document.getElementById("inputPass").value;

			if(inputID === "" || inputName === "" ||inputPass === ""){
				alert("入力できていない項目があります");
				return false; // フォームの送信を中止
			}
			document.getElementById("btn").disabled = true;
			return true; // フォームの送信を続行
		}
	</script>
</head>

<%
    // セッションを取得
	UsersInfoDto userInfoOnSession = (UsersInfoDto)session.getAttribute("LOGIN_INFO");  
	int id = userInfoOnSession.getID();
	String userId = userInfoOnSession.getUserId();
	String userName = userInfoOnSession.getUserName();
%>

<%!  String replaceEscapeChar(String inputText) {
		String charAfterEscape = inputText;
		charAfterEscape = charAfterEscape.replace("&", "&amp;");
		charAfterEscape = charAfterEscape.replace("<", "&lt;");
		charAfterEscape = charAfterEscape.replace(">", "&gt;");
		charAfterEscape = charAfterEscape.replace("\"", " &quot; ");
		charAfterEscape = charAfterEscape.replace(" ' ", "&#039;");
		return charAfterEscape;}%>
		
<body style="background-color:beige; color:#523F24;">
    <!-- ナビゲーションボタンのカラー -->
    <style>
        .custom-btn {
            border-color: #523F24;
            color: #523F24;
        }
        .custom-btn:focus, .custom-btn:active {
            background-color: #523F24;
            color: #ffffff;
            border-color: #523F24;
        }
    </style>
    
    <!-- ログイン済ヘッダー -->
    <nav class="navbar navbar-expand-lg navbar-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="index.jsp">
                <img src="images/pepe_logo.png" alt="ページロゴ" width="auto" height="60">
            </a>
            <div class="btn-group">
                <button type="button" class="btn custom-btn dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                    <%= replaceEscapeChar(userName) %>
                </button>
                <ul class="dropdown-menu dropdown-menu-end">
                    <li><a class="dropdown-item" href="<%=request.getContextPath()%>/ExeMyPage">マイページ</a></li>
                    <li><a class="dropdown-item" href="<%=request.getContextPath()%>/ExeLogout"onClick="return confirm('ログアウトしますか?');">ログアウト</a></li>
                </ul>
            </div>
        </div>
    </nav>
    <!-- ログイン済ヘッダーここまで -->
    
    
    <!-- ここから下　ページごとの内容 -->
	<div class="p-5">
        <form action="<%=request.getContextPath()%>/ExeEditUser" method="post" onsubmit="return validateForm()" class="container bg-white p-4 rounded" style="max-width:500px;">
            <div class="h2 pb-2 mb-4 text-center">
                ユーザー編集
            </div>
            <% if(error != null){ %>
            	<p class="text-danger"><%= error %></p>
            <% } %>
            <div class="mb-3">
                <input type="hidden" class="form-control" name="ID" value="<%= id %>">
            </div>
            <div class="mb-3">
                <label for="" class="form-label">ユーザーID　<span class="badge text-bg-danger">必須</span></label>
                <input type="text" class="form-control" name="userId" id="inputID" value="<%= userId %>">
            </div>
            <div class="mb-3">
                <label for="" class="form-label">ユーザー名　<span class="badge text-bg-danger">必須</span></label>
                <input type="text" class="form-control" name="userName" id="inputName" value="<%= replaceEscapeChar(userName) %>">
            </div>
            <div class="mb-3">
                <label for="" class="form-label">パスワード　<span class="badge text-bg-danger">必須</span></label>
                <input type="password" class="form-control" name="userPass" id="inputPass">
            </div>
            <div style="text-align: center;">
                <button type="submit" id="btn" class="btn btn-lg mt-3" style="background-color:#E87B4C; color:#ffffff;">更新する</button>
            </div>
        </form>
    </div>

    
	<!-- ここまで　ページごとの内容 -->
	
	
    <!-- フッター -->
	<footer class="d-flex flex-wrap justify-content-between align-items-center py-3 my-4 border-top">
    	<div class="col-md-4 d-flex align-items-center ms-3">
          	<span class="mb-3 mb-md-0 text-body-secondary">© 2023 pepeohana, Inc</span>
        </div>
        <ul class="nav col-md-4 justify-content-end list-unstyled d-flex me-3">
            <li><a href="#"><img src=images/twitter_logo.png width="auto" height="25"></a></li>
            <li class="ms-4"><a href="#"><img src=images/insta_logo.png width="auto" height="25"></a></li>
            <li class="ms-4"><a href="#"><img src=images/facebook_logo.png width="auto" height="25"></a></li>
        </ul>
	</footer>
    <!-- フッター　ここまで -->

</body>
</html>