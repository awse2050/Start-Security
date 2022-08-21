package com.security.startsecurity.api.security.resolver;

import com.security.startsecurity.api.dto.LoginPrincipal;
import com.security.startsecurity.api.security.annotation.LoginUser;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCheckResolver implements HandlerMethodArgumentResolver {

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {

        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();

        HttpSession session = request.getSession(false);

        Authentication authentication = (Authentication) session.getAttribute("auth");

        return authentication.getPrincipal();

    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        /*
            1. Annotation 적용 유무
            2. 적용한 매개변수 Type
         */
        boolean hasAnnotation = parameter.hasParameterAnnotation(LoginUser.class);
        boolean isLoginPrincipalType = parameter.getParameterType().isAssignableFrom(LoginPrincipal.class);

        return hasAnnotation && isLoginPrincipalType;
    }
}
