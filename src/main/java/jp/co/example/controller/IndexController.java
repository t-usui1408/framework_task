package jp.co.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

	@RequestMapping(value="/index",method = RequestMethod.GET)
	public String index(Model model) {
		//model.addAttribute("msg", "ログイン画面");

		return "index";
	}
	@RequestMapping(value = "/menu",method = RequestMethod.GET)
	public String menu(Model model) {
		return "menu";
	}
}
