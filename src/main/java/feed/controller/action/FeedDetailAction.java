package feed.controller.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import feed.controller.Action;
import feed.model.Feed;
import feed.model.FeedDAO;
import feed.model.FeedRequestDTO;
import feed.model.FeedResponseDTO;
import util.ApiResponseManager;
import util.ParameterValidator;


public class FeedDetailAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userCodeStr = request.getHeader("Authorization");
		String url[] = request.getPathInfo().split("/");


		if (!ParameterValidator.isInteger(userCodeStr) || !ParameterValidator.isInteger(url[1])) {
			JSONObject resObj = ApiResponseManager.getStatusObject(400);
			response.getWriter().write(resObj.toString());
			return;
		}

		int feedIndex = Integer.parseInt(url[1]);
		System.out.println(feedIndex);

		FeedDAO feedDao = new FeedDAO();
		Feed feed = feedDao.getFeedByFeedIndex(feedIndex, userCodeStr);

		JSONObject feedObj = new JSONObject();


		System.out.println(feed.getTitle());
		System.out.println(feed.getContent());
		System.out.println(feed.getFeedIndex());
		System.out.println(feed.getUserCode());


		feedObj.put("title", feed.getTitle());
		feedObj.put("content", feed.getContent());
		feedObj.put("feedIndex", feed.getFeedIndex());
		feedObj.put("userCode", feed.getUserCode());
		feedObj.put("comment", feed.getComments());



		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(feedObj.toString());
	}






}
