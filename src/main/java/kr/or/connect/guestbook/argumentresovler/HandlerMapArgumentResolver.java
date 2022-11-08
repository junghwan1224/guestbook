package kr.or.connect.guestbook.argumentresovler;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Iterator;

public class HandlerMapArgumentResolver implements HandlerMethodArgumentResolver {

    // supportsParameter 값이 true일 경우에만 resolveArgument 메서드 호출
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType() == HeaderInfo.class;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HeaderInfo headerinfo = new HeaderInfo();

        Iterator<String> headerNames = webRequest.getHeaderNames();
        while(headerNames.hasNext()) {
            String headerName = headerNames.next();
            String headerValue = webRequest.getHeader(headerName);
            //System.out.println(headerName + ", " + headerValue);
            headerinfo.put(headerName, headerValue);
        }

        return headerinfo;
    }
}
