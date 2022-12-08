package com.mvc.project.config;

import com.mvc.project.constant.MappingUtils;
import com.mvc.project.dto.UserDTO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class InterceptorConfig extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        UserDTO userDTO = (UserDTO) session.getAttribute(MappingUtils.SESSION_NAME);
        String currentURI = request.getRequestURI();
        String loginPageUIR = request.getContextPath() + "/login";

        if (!loginPageUIR.equals(currentURI)) {
            //check dieu kien luc chua dang nhap vao ma muon truy cap vao bat ki 1 url nao khac
            if (StringUtils.isEmpty(userDTO)) {
                response.sendRedirect(request.getContextPath() + "/login");
                return false;
            }
        } else if (loginPageUIR.equals(currentURI) && !StringUtils.isEmpty(userDTO)) {
            //check dieu kien luc da dang nhap vao va muon redirect qua trang login hoac contextPath
            response.sendRedirect(request.getContextPath() + "/products");
            return false;
        }

        //return false nghia la request tiep tuc thuc hien co mot interceptor khac doi xu ly, false nghia la
        //chua di vao controller ma tiep tuc di qua interceptor mot lan nua
        //return true nghia la ket thuc interceptor va di vao xu ly tai controller, no gan nhu
        //filterChain.doFilter(request, response) --> tiep tuc di vao trong
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}
