import model.Question;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Макс on 08.04.2016.
 */
public class ServletQuestion extends HttpServlet {

    private static final String CONTENT_TYPE =
            "text/html; charset=UTF-8";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        request.getSession().setAttribute("username", username);
        System.out.println(username);
        DAO.getInstance().updateUser(username);

        request.getSession().setAttribute("countQuestion", 0);
        request.getSession().setAttribute("currentPoints", 0);

        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();

        response.setContentType("text/html");
        String page = "question.jsp";

        Integer countQuestion = (Integer)request.getSession().getAttribute("countQuestion");
        String user = (String)request.getSession().getAttribute("username");

        if(countQuestion == 0){
            ArrayList<Question> dataList = DAO.getInstance().getAllQuestion();
            request.getSession().setAttribute("dataList",dataList);
        }

        ArrayList<Question> dataList = (ArrayList<Question>) request.getSession().getAttribute("dataList");

        if(countQuestion != 0){
            //check previous answer
            String answer = request.getParameter("answer");
            if (answer.equals(dataList.get(countQuestion-1).getAnswer())){
                Integer currentPoint = (Integer) request.getSession().getAttribute("currentPoints");
                request.getSession().setAttribute("currentPoints", currentPoint + dataList.get(countQuestion-1).getPoints());
            }
        }

        if(dataList.size() > countQuestion){
            request.getSession().setAttribute("question", dataList.get(countQuestion));
            request.getSession().setAttribute("countQuestion", countQuestion+1);
        }else{
            DAO.getInstance().updateUserPoint(user, (Integer) request.getSession().getAttribute("currentPoints"));
            Map<String, Integer> mapUsers = DAO.getInstance().getAllUsers();
            request.getSession().setAttribute("mapUsers", mapUsers);
            page = "result.jsp";
        }


        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        if (dispatcher != null) {
            dispatcher.forward(request, response);
        }



    }

}
