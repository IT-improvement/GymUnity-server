package friend.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import friend.controller.Action;
import org.json.JSONObject;

import friend.model.FriendDao;
import friend.model.FriendRequestDto;
import util.ApiResponseManager;
import util.ParameterValidator;

public class FriendCreateAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		String userCodeStr = request.getHeader("Authorization");
		String newFriendCodeStr = request.getParameter("userCodeOther");

		JSONObject resObj = new JSONObject();

		if (!ParameterValidator.isInteger(userCodeStr) || !ParameterValidator.isInteger(newFriendCodeStr)) {
			resObj = ApiResponseManager.getStatusObject(400);
			response.getWriter().write(resObj.toString());
			return;
		}

		int userCode = Integer.parseInt(userCodeStr);
		int newFriendCode = Integer.parseInt(newFriendCodeStr);

		FriendDao friendDao = FriendDao.getInstance();
		FriendRequestDto friendDto = new FriendRequestDto(userCode, newFriendCode);

		if (friendDao.isFriend(friendDto)) {
			resObj = ApiResponseManager.getStatusObject(400);
		} else if (friendDao.createFriend(friendDto)) {
			resObj = ApiResponseManager.getStatusObject(200, "Friend Create is finished successfully");
		} else {
			resObj = ApiResponseManager.getStatusObject(500);
		}
		
		response.getWriter().write(resObj.toString());
	}
}