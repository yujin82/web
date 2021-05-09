package kr.mjc.yujin.web.model2.article;

import kr.mjc.yujin.web.dao.Article;
import kr.mjc.yujin.web.dao.ArticleDao;
import kr.mjc.yujin.web.dao.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet("/model2/article/addArticle")
public class AddArticleServlet extends HttpServlet {
    @Autowired
    private ArticleDao articleDao;
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Article article = new Article();
        User user = (User) session.getAttribute("USER");
        article.setTitle(request.getParameter("title"));
        article.setContent(request.getParameter("content"));
        article.setName(user.getName());
        article.setUserId(user.getUserId());

        try {
            articleDao.addArticle(article);
            response.sendRedirect(request.getContextPath() + "/model2/article/articleList");
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() +
                    "/mvc/article/articleList?msg=Something Wrong. Try this again.");
        }
    }
}
