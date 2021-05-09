package kr.mjc.yujin.web.mvc;

import kr.mjc.yujin.web.dao.User;
import kr.mjc.yujin.web.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class UserController {

  private final UserDao userDao;

  @Autowired
  public UserController(UserDao userDao) {
    this.userDao = userDao;
  }

  /**
   * 사용자 목록 화면
   */
  public void userList(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    request.setAttribute("userList", userDao.listUsers(0, 100));

    request.getRequestDispatcher("/WEB-INF/jsp/model2/user/userList.jsp")
        .forward(request, response);
  }

  /**
   * 사용자 등록 화면
   */
  public void userForm(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    request.getRequestDispatcher("/WEB-INF/jsp/model2/user/userForm.jsp")
        .forward(request, response);
  }

  /**
   * 로그인 화면
   */
  public void loginForm(HttpServletRequest request,
                        HttpServletResponse response)
      throws ServletException, IOException {

    request.getRequestDispatcher("/WEB-INF/jsp/model2/user/loginForm.jsp")
        .forward(request, response);
  }

  /**
   * 개인정보 화면
   */
  public void userInfo(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    request.getRequestDispatcher("/WEB-INF/jsp/model2/user/userInfo.jsp")
        .forward(request, response);
  }

  /**
   * 사용자 등록 액션
   */
  public void addUser(HttpServletRequest request, HttpServletResponse response)
      throws IOException {

    User user = new User();
    user.setEmail(request.getParameter("email"));
    user.setPassword(request.getParameter("password"));
    user.setName(request.getParameter("name"));

    try {
      userDao.addUser(user);
      response.sendRedirect(request.getContextPath() + "/mvc/user/userList");
    } catch (DuplicateKeyException e) {
      response.sendRedirect(request.getContextPath() +
          "/mvc/user/userForm?msg=Duplicate email");
    }
  }

  /**
   * 로그인 액션
   */
  public void login(HttpServletRequest request, HttpServletResponse response)
      throws IOException {

    String email = request.getParameter("email");
    String password = request.getParameter("password");

    try {
      User user = userDao.login(email, password);
      HttpSession session = request.getSession();
      session.setAttribute("USER", user);
      response.sendRedirect(request.getContextPath() + "/mvc/user/userInfo");
    } catch (EmptyResultDataAccessException e) {
      response.sendRedirect(request.getContextPath() +
          "/mvc/user/loginForm?msg=Wrong email or password");
    }
  }
}
