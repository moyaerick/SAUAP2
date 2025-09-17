package mx.sauap.ui;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("*.xhtml")
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest  request  = (HttpServletRequest)  req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession         session  = request.getSession(false);

        String uri = request.getRequestURI();

        boolean esLogin     = uri.endsWith("/login.xhtml");
        boolean esRecursoJSF= uri.contains("/javax.faces.resource/");
        boolean esRecursoApp= uri.contains("/resources/");
        boolean logueado = session != null && session.getAttribute("usuario") != null;

        // Evitar volver con atras despues del logout
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        if (logueado || esLogin || esRecursoJSF || esRecursoApp) {
            chain.doFilter(req, res);
        } else {
            response.sendRedirect(request.getContextPath() + "/login.xhtml");
        }
    }
}