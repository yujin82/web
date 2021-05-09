package kr.mjc.yujin.web.model2.article;

import kr.mjc.yujin.web.dao.Article;
import kr.mjc.yujin.web.dao.ArticleDao;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/model2/article/articleUpdate")
public class UpdateArticleServlet extends HttpServlet {
    @Autowired
    private ArticleDao articleDao;
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Article article = new Article();
        article.setTitle(request.getParameter("title"));
        article.setContent(request.getParameter("content"));

        try {
            articleDao.updateArticle(article);
            response.sendRedirect(request.getContextPath() + "/model2/article/articleUpdate");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
