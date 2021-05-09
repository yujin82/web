package kr.mjc.yujin.web.model2.user;

import kr.mjc.yujin.web.dao.User;
import kr.mjc.yujin.web.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/model2/user/login")
public class LoginServlet extends HttpServlet {

  @Autowired
  private UserDao userDao;

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String email = request.getParameter("email");
    String password = request.getParameter("password");

    try {
      User user = userDao.login(email, password);
      HttpSession session = request.getSession();
      session.setAttribute("USER", user);
      response.sendRedirect(request.getContextPath() + "/model2/user/userInfo");
    } catch (EmptyResultDataAccessException e) {
      response.sendRedirect(request.getContextPath() +
          "/model2/user/loginForm?msg=Wrong email or password");
    }
  }
}
