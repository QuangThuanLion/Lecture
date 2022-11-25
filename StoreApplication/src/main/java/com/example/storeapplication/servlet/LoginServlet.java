package com.example.storeapplication.servlet;

import com.example.storeapplication.common.FunctionUtils;
import com.example.storeapplication.constant.MappingUtils;
import com.example.storeapplication.model.Account;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LoginServlet", value = "/login-servlet")
public class LoginServlet extends HttpServlet {
    private List<Account> mappingAccounts;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String requestURL = request.getContextPath();
        response.sendRedirect(requestURL);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String email = request.getParameter("email");
        final String password = request.getParameter("password");

        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        if (Objects.isNull(email) || Objects.isNull(password)) {
            dispatcher.forward(request, response);
            return;
        }

        final Optional<Account> account = mappingAccounts.stream()
                .filter(x -> x.getEmail() != null && x.getPassword() != null)
                .filter(x -> email.equals(x.getEmail()) && password.equals(x.getPassword()))
                .findFirst();

        account.ifPresentOrElse(x -> {
                    final String requestURL = request.getContextPath() + "/products-servlet";
                    HttpSession session = request.getSession();
                    session.setAttribute("account", account);

                    try {
                        response.sendRedirect(requestURL);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                },
                () -> {
                    request.setAttribute("message", MappingUtils.MESSAGE_ERROR);
                    try {
                        dispatcher.forward(request, response);
                    } catch (ServletException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    @Override
    public void init() throws ServletException {
        this.mappingAccounts = FunctionUtils.mappingAccountModel();
        super.init();
    }
}
