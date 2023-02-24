package bitcamp.myapp.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bitcamp.myapp.controller.PageController;

@WebServlet("/app/*")
public class DispatcherServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String pathInfo = request.getPathInfo();

    request.getRequestDispatcher(pathInfo).include(request, response);

    ServletContext ctx = getServletContext();

    PageController controller = (PageController) ctx.getAttribute(pathInfo);
    if (controller == null) {
      request.getRequestDispatcher(pathInfo).include(request, response);
      return;
    }

    String view = controller.execute(request, response);

    @SuppressWarnings("unchecked")
    List<Cookie> cookies = (List<Cookie>) request.getAttribute("cookies");
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        response.addCookie(cookie);
      }
    }

    if (view != null) {
      if (view.startsWith("redirect:")) {
        response.sendRedirect(view.substring(9));
      } else {
        request.getRequestDispatcher(view).forward(request, response);
      }
    }
  }
}

