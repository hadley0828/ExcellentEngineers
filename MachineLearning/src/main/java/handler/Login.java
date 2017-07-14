package handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Login {
	@RequestMapping("/login.do")
	public String login(String account,String password) {
		System.out.println("我进来了");
		System.out.println(account+" "+password);
		return "login";
	}
}
