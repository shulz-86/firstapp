package ru.geekbrains.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.model.Product;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "BasicServlet", urlPatterns = "/basic_servlet")
public class BasicServlet implements Servlet {

    private static Logger logger = LoggerFactory.getLogger(BasicServlet.class);

    private transient ServletConfig config;

    // Метод вызывается контейнером после того как был создан класс сервлета
    @Override
    public void init(ServletConfig config) throws ServletException {
        // Сохраняем полученную от сервера конфигурацию
        this.config = config;
    }

    @Override
    public ServletConfig getServletConfig() {
        return config;
    }

    // Метод вызывается для каждого нового HTTP запроса к данному сервлету
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        logger.info("New request");

        List<Product> productList = Arrays.asList(
                new Product(1, "картофель", 15.20),
                new Product(2, "свекла", 12.10),
                new Product(3, "морковь", 10.00),
                new Product(4, "капуста", 8.50),
                new Product(5, "груши", 30.10),
                new Product(6, "бананы", 25.50),
                new Product(7, "апельсины", 23.30),
                new Product(8, "яблоки", 28.50),
                new Product(9, "черешня", 55.90),
                new Product(10, "клубника", 60.00));
        // получаем объект типа BufferedWriter и пишем в него ответ на пришедший запрос

        res.setContentType("text/html; charset=UTF-8");
        res.setCharacterEncoding("UTF-8");
        res.getWriter().println("<h1>Servlet content</h1>");

        for(Product a : productList) {
            res.getWriter().println("<li>" + a + "</li>");
        }

        /*productList.forEach(item -> {
            try {
                res.getWriter().println("<li>" + item+ "</li>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });*/
    }

    @Override
    public String getServletInfo() {
        return "BasicServlet";
    }

    // При завершении работы веб приложения, контейнер вызывает этот метод для всех сервлетов из этого приложения
    @Override
    public void destroy() {
        logger.info("Servlet {} destroyed", getServletInfo());
    }
}