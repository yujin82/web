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


@WebServlet("/model2/article/getArticle")
public class GetArticleServlet extends HttpServlet {

    @Autowired
    private ArticleDao articleDao;
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String articleId = request.getParameter("num");
        Article article = articleDao.getArticle(Integer.parseInt(articleId));
        request.setAttribute("ARTICLE", article);
        request.getRequestDispatcher("/WEB-INF/jsp/model2/article/getArticle.jsp").forward(request, response);


    }
}
