package com.txb.app.config;

import cn.hutool.json.JSONObject;
import com.txb.app.utils.ServletUtils;
import com.txb.app.utils.StrFormatter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint , Serializable {
    private static final long serialVersionUID = -8970718410437077606L;
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
        String msg = StrFormatter.format("请求访问：{}，认证失败，无法访问系统资源", request.getRequestURI());
        JSONObject json = new JSONObject();
        json.append("msg",msg);
        json.append("code",httpStatus.value());
        ServletUtils.renderString(response, json.toString());
    }
}
