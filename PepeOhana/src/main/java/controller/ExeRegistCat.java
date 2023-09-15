package controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.CatsInfoDto;
import model.RegistCatBL;
import model.UsersInfoDto;

/**
 * Servlet implementation class ExeRegistCat
 */
@WebServlet("/ExeRegistCat")
public class ExeRegistCat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExeRegistCat() {
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
//		doGet(request, response);
		
//		レスポンス（出力データ）の文字コードを設定
			response.setContentType("text/html;charset=UTF-8");
		
//		リクエスト（受信データ）の文字コードを設定
			request.setCharacterEncoding("UTF-8");
			
			HttpSession session           = request.getSession();
			UsersInfoDto userInfoOnSession = (UsersInfoDto)session.getAttribute("LOGIN_INFO");
		
//			リクエストパラメータを取得

			
			String catName = request.getParameter("CATNAME");
//			(CATNAME)
			String catKind = request.getParameter("KIND");
//			(CATKIND)
			
			System.out.println(catName);
			
			String catBirth = request.getParameter("BIRTH");
			java.sql.Date sqlDate = null; 
//			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
//			// String を LocalDate に変換
//			LocalDate birthDate = LocalDate.parse(catBirth, formatter);
//			// LocalDate を java.sql.Date に変換
//			java.sql.Date sqlDate = java.sql.Date.valueOf(birthDate);
			
			if (catBirth != null) {
			    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			    // String を LocalDate に変換
			    LocalDate birthDate = LocalDate.parse(catBirth, formatter);
			    // LocalDate を java.sql.Date に変換
			   sqlDate = java.sql.Date.valueOf(birthDate);
			} else {
			    // catBirth が null の場合のエラー処理を行うか、適切なデフォルト値を設定します。
				System.out.println("誕生日が受け取れません");
			}
			
						
//			(Birth)
			
			String weightParameter = request.getParameter("WEIGHT");
			float catWeight = 0.0f; // デフォルト値を設定

			if (weightParameter != null && !weightParameter.isEmpty()) {
			    catWeight = Float.parseFloat(weightParameter);
			} else {
			    // エラーメッセージまたはデフォルト値を設定
			    System.out.println("体重が不正です");
			}
			
			
			
//			float catWeight = Float.parseFloat(request.getParameter("WEIGHT"));
//			(Weight)
			int catGender = Integer.parseInt(request.getParameter("GENDER"));
//			(Gender)

////			画像ファイルの受け取り方
//			Part filePart = request.getPart("IMAGE");
//			InputStream fileContent = filePart.getInputStream();
//			byte[] catImage = fileContent.readAllBytes();
			
			// 画像ファイルの受け取り
			Part filePart = request.getPart("IMAGE");
			byte[] catImage = null;

			if (filePart != null) {
			    InputStream fileContent = filePart.getInputStream();
			    catImage = fileContent.readAllBytes();
			}			

//			
			String catComment = request.getParameter("COMMENT");			
			int userId = userInfoOnSession.getID(); 
			
//			(COMMENT)	
			
			System.out.println(sqlDate);
			System.out.println(catName);
			System.out.println(catBirth);
			
			int ownerId = userInfoOnSession.getID();
			
			
			
			//ユーザーデータ（CatInfoDto型）の作成
			CatsInfoDto dto = new CatsInfoDto();
			dto.setId(ownerId);
			dto.setCatName( catName );
			dto.setKind( catKind );
			dto.setBirth( sqlDate );
			dto.setGender( catGender );
			dto.setWeight( catWeight );
			dto.setImage( catImage );
			dto.setComment( catComment );
			dto.setReg_Date( new Timestamp(System.currentTimeMillis()) ); 
			
			System.out.println(catKind);
			System.out.println(sqlDate);
			
			
//			データをDBに登録
			RegistCatBL logic = new RegistCatBL();
			boolean succesInsert = logic.exeInsertCatInfo(dto);
			
			
			
			if (succesInsert) {
//				DBに成功した場合、ログイン後のtop画面(top.jsp)を表示する
				response.sendRedirect("/WEB-INF/view/index.jsp");
			} else {
//				DBに失敗した場合、エラー画面(registusererror.html)を表示する
				response.sendRedirect("htmls/registusererror.html");
			}
		
		
		
		
	}	
		
		
		
		
		
	}

