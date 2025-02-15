package user.controller.action;

import org.json.JSONArray;
import org.json.JSONObject;
import user.controller.Action;
import user.model.UserDao;
import user.model.UserResponseDto;
import util.ParameterValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserReadAllByQueryAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String query = request.getParameter("query");
        String pageNumberStr = request.getParameter("pageNumber");

        int pageNumber = ParameterValidator.isInteger(pageNumberStr) ? Integer.parseInt(pageNumberStr) : 1;
        UserDao userDao = UserDao.getInstance();

        List<UserResponseDto> users = userDao.findUserAllByIdOrName(query, pageNumber);

        JSONArray userJsonArr = new JSONArray();

        for (UserResponseDto user : users) {
            JSONObject userObj = new JSONObject();

            userObj.put("code", user.getCode());
            userObj.put("id", user.getId());
            userObj.put("name", user.getName());
            userObj.put("profileImage", user.getProfileImage());

            userJsonArr.put(userObj);
        }

        response.getWriter().write(userJsonArr.toString());
    }
}