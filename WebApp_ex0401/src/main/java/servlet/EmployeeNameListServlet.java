/*
 * WebApp_ex0401
 * servlet.EmployeeNameListServlet.java
 */
package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 従業員名の一覧表示のための処理を行う
 * @author emBex Education
 */
@WebServlet("/employee-name-list-servlet")
public class EmployeeNameListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeNameListServlet() {
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

		// リクエストオブジェクトのエンコーディング方式の指定
		request.setCharacterEncoding("UTF-8");

		// リクエストパラメータの取得
		String section = request.getParameter("section");

		final String URL = "jdbc:mysql://localhost:3306/sampledb";
		final String USER = "embexU";
		final String PASSWORD = "embexP";

		// JDBCドライバの読み込み
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			List<String> nameList = null;

			String sql = "SELECT name FROM m_employee WHERE section = ?";

			// データベースへの接続の取得、PreparedStatementの取得
			try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
					PreparedStatement pstmt = con.prepareStatement(sql)) {

				nameList = new ArrayList<String>();

				// プレースホルダへの値の設定
				pstmt.setString(1, section);

				// SQLステートメントの実行
				ResultSet res = pstmt.executeQuery();

				// 結果の操作
				while (res.next()) {
					nameList.add(res.getString("name"));
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

			// リクエストスコープへの属性の設定
			request.setAttribute("nameList", nameList);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// リクエストの転送
		RequestDispatcher rd = request.getRequestDispatcher("employee-name-list.jsp");
		rd.forward(request, response);

	}

}
