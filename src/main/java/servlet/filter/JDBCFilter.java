//package servlet.filter;
//
//import DAO.DBConnector;
//import DAO.impl.DBWorkConnector;
//import servlet.MyUtils;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//import java.sql.Connection;
//import java.util.Collection;
//import java.util.Map;
//
//@WebFilter(filterName = "jdbcFilter", urlPatterns = { "/*" })
//public class JDBCFilter implements Filter {
//    DBConnector dbConnector = new DBWorkConnector();
//
//    public JDBCFilter() {
//    }
//
//    @Override
//    public void init(FilterConfig fConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//
//    // Проверить является ли Servlet цель текущего request?
////    private boolean needJDBC(HttpServletRequest request) {
////        System.out.println("JDBC Filter");
////        //
////        // Servlet Url-pattern: /spath/*
////        //
////        // => /spath
////        String servletPath = request.getServletPath();
////        // => /abc/mnp
////        String pathInfo = request.getPathInfo();
////
////        String urlPattern = servletPath;
////
////        if (pathInfo != null) {
////            // => /spath/*
////            urlPattern = servletPath + "/*";
////        }
////
////        // Key: servletName.
////        // Value: ServletRegistration
////        //Map<String, ? extends ServletRegistration> servletRegistrations = request.getSession().getServletContext().getServletRegistration();
////        // Коллекционировать все Servlet в вашем WebApp.
////        //Collection<? extends ServletRegistration> values = servletRegistrations.values();
////        for (ServletRegistration sr : values) {
////            Collection<String> mappings = sr.getMappings();
////            if (mappings.contains(urlPattern)) {
////                return true;
////            }
////        }
////        return false;
////    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//
//        HttpServletRequest req = (HttpServletRequest) request;
//
//        // Открыть  connection (соединение) только для request со специальной ссылкой.
//        // (Например ссылка к servlet, jsp, ..)
//        // Избегать открытия Connection для обычных запросов.
//        // (Например image, css, javascript,... )
//        if (this.needJDBC(req)) {
//
//            System.out.println("Open Connection for: " + req.getServletPath());
//
//            Connection conn = null;
//            try {
//                // Создать объект Connection подключенный к database.
//                conn = dbConnector.connect();
//                // Настроить автоматический commit false, чтобы активно контролировать.
//                conn.setAutoCommit(false);
//
//                // Сохранить объект Connection в attribute в request.
//                MyUtils.storeConnection(request, conn);
//
//                // Разрешить request продвигаться далее.
//                // (Далее к следующему Filter tiếp или к цели).
//                chain.doFilter(request, response);
//
//                // Вызвать метод commit() чтобы завершить транзакцию с DB.
//                conn.commit();
//            } catch (Exception e) {
//                e.printStackTrace();
//                dbConnector.rollbackQuietly(conn);
//                throw new ServletException();
//            } finally {
//                dbConnector.closeQuietly(conn);
//            }
//        }
//        // Для обычных request (image,css,html,..)
//        // не нужно открывать connection.
//        else {
//            // Разрешить request продвигаться далее.
//            // (Далее к следующему Filter tiếp или к цели).
//            chain.doFilter(request, response);
//        }
//
//    }
//
//}
