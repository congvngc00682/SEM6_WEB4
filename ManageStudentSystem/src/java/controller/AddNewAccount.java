package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.SendMail;
import utils.WsadUtils;
import entities.Account;
import entities.Profile;
import model.AccountDAO;
import model.ProfileDAO;

/**
 * Servlet implementation class AddNewAccount
 */
@WebServlet("/AddNewAccount")
public class AddNewAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddNewAccount() {
		super();
	}

	/**
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
        @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Profile p = new Profile();
		Account acc = new Account();
		int role = Integer.parseInt(request.getParameter("role"));
		int faculty = Integer.parseInt(request.getParameter("faculty"));
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String middlename = request.getParameter("middlename");
		String email = request.getParameter("email");
		String result = "";
		ServletOutputStream out = response.getOutputStream();

		p.setFirstname(firstname);
		p.setMiddlename(middlename);
		p.setLastname(lastname);
		boolean existed = false;
		try {
			existed = ProfileDAO.checkExistingProfile(p);
			if (existed) {
				result = "Existed";
				buildOutput(result, out);
				return;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		String username = WsadUtils.createUsername(firstname, middlename, lastname);
		username = recreateUsername(username);
		String password = WsadUtils.generatePassword();

		acc.setUsername(username);
		acc.setPassword(password);
		acc.setEmail(email);// TODO: check exist email
		acc.setRole(role);
		acc.setFaculty(faculty);

		try {
			Account insertedAcc = AccountDAO.insertAccount(acc);
			p.setAccountId(insertedAcc.getId());

			ProfileDAO.insertProfile(p);

			String mailContent = WsadUtils.buildMailContent(username, password);

			SendMail.sendMail(email, "New Account", mailContent);

			response.sendRedirect("AddNewAccountResult.jsp");
		} catch (SQLException e) {
			buildOutput("insert failed", out);
			e.printStackTrace();
		}

		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
	}

	/**
	 * @param username
	 * @return
	 */
	private static String recreateUsername(String username) {
		Account similarAccount = new Account();
		try {
			similarAccount = AccountDAO.getSimilarUsername(username);
			int nextNumber = 1;
			if (similarAccount != null) {
				int currentHighestNumber = similarAccount.getId();
				nextNumber += currentHighestNumber;
			}

			username = username + String.valueOf(nextNumber);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return username;
	}

	/**
	 * @param result
	 * @param out
	 * @throws IOException
	 */
	public void buildOutput(String result, ServletOutputStream out) throws IOException {
		out.println("<html>");
		out.println("<head><title>Hello Servlet</title></head>");

		out.println("<body>");
		out.println("<h3>" + result + "</h3>");
		out.println("</body>");
		out.println("<html>");
	}

	/**
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
        @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
