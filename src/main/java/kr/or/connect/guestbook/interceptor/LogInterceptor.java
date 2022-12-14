package kr.or.connect.guestbook.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogInterceptor extends HandlerInterceptorAdapter {
    @Override
    public void postHandle(HttpServletRequest reques, HttpServletResponse response, Object handler
                            , ModelAndView modelAndView
    ) throws Exception {
        System.out.println(handler.toString() + " 가 종료되었습니다.  " + modelAndView.getViewName() + "을 view로 사용합니다.");
    }

    @Override
    public boolean preHandle(HttpServletRequest reques, HttpServletResponse response, Object handler) throws Exception {
        System.out.println(handler.toString() + " 를 호출했습니다.");
        return true;
    }
}
