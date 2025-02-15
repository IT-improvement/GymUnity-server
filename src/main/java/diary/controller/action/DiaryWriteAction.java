package diary.controller.action;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import diary.controller.Action;
import diary.model.DiaryDAO;
import diary.model.DiaryRequestDTO;

public class DiaryWriteAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, UnsupportedEncodingException {
		HttpSession session = request.getSession();
//		int userCode = (int)session.getAttribute("code");
		int userCode = 1;
		System.out.println("userCode: "+userCode);
		request.setCharacterEncoding("UTF-8");
		System.out.println("작성");
		String content = request.getParameter("content");
		Timestamp diaryDate = Timestamp.valueOf(request.getParameter("date"));
		
		DiaryRequestDTO dto = new DiaryRequestDTO();
		dto.setUserCode(userCode);
		dto.setContent(content);
		dto.setDiary_date(diaryDate);
		
		DiaryDAO dao = DiaryDAO.getInstance();
		dao.writeDiary(dto);
	}

}
