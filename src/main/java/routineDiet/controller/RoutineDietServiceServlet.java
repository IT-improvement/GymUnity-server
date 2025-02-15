package routineDiet.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RoutineDietServiceServlet  extends HttpServlet {

    public RoutineDietServiceServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        System.out.println(command);
        ActionFactory af = ActionFactory.getInstance();
        Action action = af.getAction(command);

        if (action != null) {
            action.execute(request, response);
        }
    }

        protected void doPost(HttpServletRequest request, HttpServletResponse
        response) throws ServletException, IOException {
            doGet(request, response);
        }
    }