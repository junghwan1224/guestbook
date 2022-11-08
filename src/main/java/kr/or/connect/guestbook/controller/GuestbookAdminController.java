package kr.or.connect.guestbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class GuestbookAdminController {
    @GetMapping(path="/loginform")
    public String loginForm() {
        return "loginform";
    }

    @PostMapping(path = "/login")
    public String login(@RequestParam(name = "passwd", required = true) String passwd
                        , HttpSession session
                        , RedirectAttributes redirectAttr
    ) {
        if("1234".equals(passwd)) {
            session.setAttribute("isAdmin", "true");
        }
        else {
            redirectAttr.addFlashAttribute("errorMessage", "invalid password");
            return "redirect:/loginform";
        }
        return "redirect:/list";
    }

    @GetMapping(path = "/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("isAdmin");
        return "redirect:/loginform";
    }
}
