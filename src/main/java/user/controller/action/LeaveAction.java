package user.controller.action;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import user.controller.Action;
import user.model.User;
import user.model.UserDao;
import user.model.UserRequestDto;
import user.model.UserResponseDto;

public class LeaveAction extends HttpServlet implements Action {
	private static final long serialVersionUID = 1L;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();

		StringBuilder sb = new StringBuilder();
		BufferedReader reader = request.getReader();

		String line = "";
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}

		UserDao userDao = UserDao.getInstance();
		UserResponseDto user = (UserResponseDto) session.getAttribute("user");

		JSONObject jsonRequest = new JSONObject(sb.toString());

		String id = jsonRequest.getString("id");
		String password = jsonRequest.getString("password");

		System.out.println("id : " + id + ", password : " + password);

		UserRequestDto userDto = new UserRequestDto();

		userDto.setId(id);
		userDto.setPassword(password);

		boolean result = userDao.deleteUser(userDto);
		JSONObject jsonResponse = new JSONObject();

		if(result) {
			session.setAttribute("id", id);
			session.setAttribute("password", password);
			session.removeAttribute("user");
			session.invalidate();
			jsonResponse.put("id", id);
			jsonResponse.put("password", password);
			jsonResponse.put("status", 200);
			jsonResponse.put("message", "회원탈퇴 완료");

			System.out.println("회원탈퇴 완료");
		} else {
			jsonResponse.put("status", 401);
			jsonResponse.put("message", "회원탈퇴 실패");
			System.out.println("회원탈퇴 실패");
		}

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jsonResponse.toString());
		System.out.println("result: " + result);
	}
}