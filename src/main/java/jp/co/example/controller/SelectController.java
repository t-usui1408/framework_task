package jp.co.example.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.example.entity.User;
import jp.co.example.entity.UserForm;
import jp.co.example.service.UserService;

@Controller
public class SelectController {

	@Autowired
	private UserService userService;

	@Autowired
	HttpSession session;

	@RequestMapping("/select")
	public String select(@ModelAttribute("select")UserForm form,Model model) {
		return "select";
	}

	@RequestMapping(value = "/list",method = RequestMethod.GET)
	public String list (@Validated @ModelAttribute("select")UserForm form,BindingResult bindingResult,Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("msg", "入力されたデータはありませんでした");
			return "select";
		}

		List<User>list = userService.find(form.getId(),form.getName(),form.getTel());
		if(list == null || list.size() == 0) {
			model.addAttribute("msg", "入力されたデータはありませんでした");
			return "select";
		}else {
			model.addAttribute("user", list);
			return "selectResult";
		}


	}


	}