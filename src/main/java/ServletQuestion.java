import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by Макс on 08.04.2016.
 */
public class ServletQuestion extends HttpServlet {

    private static final String CONTENT_TYPE =
            "text/html; charset=UTF-8";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        System.out.println(username);
        DAO.getInstance().updateUser(username);

        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();

        response.setContentType("text/html");
        String page = "question.jsp";

        ArrayList<Question> dataList = DAO.getInstance().getAll();

        request.getSession().setAttribute(dataList.get(0).getQuestion(), "question");
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        if (dispatcher != null) {
            dispatcher.forward(request, response);
        }

    }

}
