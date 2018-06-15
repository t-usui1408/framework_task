package jp.co.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.example.entity.Login;
import jp.co.example.entity.LoginForm;
import jp.co.example.service.LoginService;


@Controller
public class AuthController {

	@Autowired
	private LoginService loginService;

	@Autowired
	HttpSession session;

	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(@Validated @ModelAttribute("login") LoginForm loginForm,BindingResult bindingResult,Model model) {
			return "login";
	}


	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public String list(@ModelAttribute("login") LoginForm loginForm,BindingResult bindingResult,Model model) {
		String id = loginForm.getId();
		String pass = loginForm.getPass();
		Login login = loginService.findByIdAndPass(id,pass);

		if((id ==null)||(pass == null)||("".equals(id))||("".equals(pass))) {
			model.addAttribute("msg", "IDまたはPASSが間違っています。");
			return "login";
		}

		session.setAttribute("adminName", login.getAdmin_name());
		return "menu";
	}

	@RequestMapping(value="/logout",method = RequestMethod.POST)
	public String logout(Model model) {
		session.invalidate();
		return "logout";
	}
}
