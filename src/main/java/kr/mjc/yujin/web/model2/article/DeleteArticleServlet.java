package kr.mjc.yujin.web.model2.article;

import kr.mjc.yujin.web.dao.ArticleDao;
import org.springframework.beans.factory.annotation.Autowired;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet("/model2/article/articleDelete")
public class DeleteArticleServlet extends HttpServlet  {

    @Autowired
    private ArticleDao articleDao;
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        /*String articleId = request.getParameter("num");
        Article article = articleDao.getArticle(Integer.parseInt(articleId));
        request.setAttribute("ARTICLE", article);
        request.getRequestDispatcher("/WEB-INF/jsp/model2/article/getArticle.jsp").forward(request, response);
        */
        /*
        Article article = new Article();
        article.setTitle(request.getParameter("title"));
        article.setContent(request.getParameter("content"));
        article.setName(request.getParameter("name"));
        article.setUserId(Integer.parseInt(request.getParameter("userId")));
        */
        String articleIds = request.getParameter("articleNum"); //url?num
        String userIDs = request.getParameter("userNum"); // url?
        int articleId = Integer.parseInt(articleIds);
        int userId = Integer.parseInt(userIDs);

        int article = articleDao.deleteArticle(articleId, userId);



    }
}
