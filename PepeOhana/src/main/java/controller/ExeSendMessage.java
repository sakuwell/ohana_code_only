package controller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MessagesDto;
import model.SendMessageBL;
import model.UsersInfoDto;

/**----------------------------------------------------------------------*
 *Filename:ExeSendMessage.java
 *
 *Description:
 *	このクラスは、メッセージ送信機能を提供するためのものです。
 *	セッション情報とリクエストパラメータから登録に必要な情報を取得し、
 *	データベースに登録し、マイページ画面へ遷移する
 *	データベース登録に失敗した場合は失敗時メッセージをリクエストスコープにセットし、
 *	メッセージ作成画面に戻る
 *	
 *Author:櫻井
 *Creation Date:2023-09-15
 *
 *Copyright © 2023 KEG Sakura All rights reserved.
 *----------------------------------------------------------------------**/
/**
 * Servlet implementation class SendMessage
 */
@WebServlet("/ExeSendMessage")
public class ExeSendMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExeSendMessage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//レスポンス（出力データ）の文字コードを設定
		response.setContentType("text/html;charset=UTF-8");
		//リクエスト（受信データ）の文字コードを設定
		request.setCharacterEncoding("UTF-8");
		//レスポンス(出力データ)の文字コードを設定
		response.setCharacterEncoding("UTF-8");
		
		//セッション情報の取得
		HttpSession session           = request.getSession();
		UsersInfoDto userInfoOnSession = (UsersInfoDto)session.getAttribute("LOGIN_INFO");
		
		//セッション情報から「ID」情報を取得し変数「senderId」に格納
		int senderId=userInfoOnSession.getID();
		
		String catId=request.getParameter("CATID");//リクエストパラメーター(CATID)
		String recieverId=request.getParameter("RECIEVERID");//リクエストパラメーター(RECIEVERID)
		String message=request.getParameter("COMMENT");//リクエストパラメーター(COMMENT)
		
		//「messages」テーブルに取得した情報を登録
		MessagesDto dto = new MessagesDto();
		dto.setSenderId(senderId);
		dto.setRecieverId(Integer.parseInt(recieverId));
		dto.setCatId(Integer.parseInt(catId));
		dto.setMessage(message);
		dto.setSend_Date(new Timestamp(System.currentTimeMillis()));
		
		SendMessageBL logic = new SendMessageBL();
		boolean succesInsert = logic.executeInsertMessage(dto);
		
		//登録の登録成功/失敗に応じて画面を振り分ける
		if (succesInsert) {
			//成功時:マイページ表示
			response.sendRedirect("ExeMyPage");
		} else {
			//失敗時
			String recieverName = request.getParameter("RECIEVERNAME"); //リクエストパラメータ（RECIEVERID)
			String catName = request.getParameter("CATNAME"); //リクエストパラメータ（RECIEVERID)
			// リクエストスコープにデータを設定
			request.setAttribute("message", "メッセージの送信が出来ませんでした。内容をご確認ください。");
			response.sendRedirect("Message?CATID="+catId+"&RECIEVERID="+recieverId+"&CATNAME="+catName+"&RECIEVERNAME="+recieverName);

		}

	}
	

}
