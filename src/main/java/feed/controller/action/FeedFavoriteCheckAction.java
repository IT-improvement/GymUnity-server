package feed.controller.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import feed.controller.Action;
import feed.model.FeedDAO;
import feed.model.FeedRequestDTO;
import feed.model.FeedResponseDTO;
import org.json.JSONObject;
import user.model.User;
import util.ApiResponseManager;
import util.ParameterValidator;

/**
 * Servlet implementation class FeedFavoriteCreateAction
 */
public class FeedFavoriteCheckAction implements Action {

    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userCodeStr = request.getHeader("Authorization");
        String url[] = request.getPathInfo().split("/");


        if (!ParameterValidator.isInteger(userCodeStr) || !ParameterValidator.isInteger(url[1])) {
            JSONObject resObj = ApiResponseManager.getStatusObject(400);
            response.getWriter().write(resObj.toString());
            return;
        }

        int userCode = Integer.parseInt(userCodeStr);
        int feedIndex = Integer.parseInt(url[1]);

        System.out.println(feedIndex);

        FeedRequestDTO feedDto = new FeedRequestDTO(feedIndex, userCode);
        FeedDAO feedDao = FeedDAO.getInstance();
        FeedResponseDTO feed = feedDao.checkFeedFavorite(feedDto);


        JSONObject resObj = new JSONObject();

        if (feed != null) {
            resObj = ApiResponseManager.getStatusObject(200);
        } else {
            resObj = ApiResponseManager.getStatusObject(400);
        }

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().append(resObj.toString());
    }

}
