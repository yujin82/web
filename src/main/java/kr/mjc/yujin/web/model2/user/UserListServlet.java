package kr.mjc.yujin.web.model2.user;

import kr.mjc.yujin.web.dao.User;
import kr.mjc.yujin.web.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/model2/user/userList")
public class UserListServlet extends HttpServlet {

  @Autowired
  private UserDao userDao;

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    List<User> userList = userDao.listUsers(0, 100);
    request.setAttribute("userList", userList);

    request.getRequestDispatcher("/WEB-INF/jsp/model2/user/userList.jsp")
        .forward(request, response);
  }
}
