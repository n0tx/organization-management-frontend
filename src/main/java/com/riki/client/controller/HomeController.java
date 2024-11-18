package com.riki.client.controller;

import com.riki.client.form.LoginForm;
import com.riki.client.form.RegisterForm;
import com.riki.client.service.HomeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {

	@Autowired
	private HomeService homeService;

	@GetMapping("/hello")
	public String home(Model model) {
		model.addAttribute("home_message", "Hello World from SpringMVC + Thymleaf");
		return "home";
	}

	@GetMapping("/")
	public String login(Model model) {
		LoginForm loginForm = new LoginForm();
		model.addAttribute("loginForm", loginForm);
		return "authentication/login";
	}

	@PostMapping("/")
	public String login(@ModelAttribute("loginForm") @Valid LoginForm loginForm,
						BindingResult bindingResult,
						Errors errors,
						Model model) {
		if (errors.hasErrors()) {
			model.addAttribute("loginForm", loginForm);
			return "authentication/login";
		}
		homeService.login(loginForm);
		return "redirect:/employee/list";
	}

	@GetMapping("/register")
	public String register(Model model) {
		RegisterForm registerForm = new RegisterForm();
		model.addAttribute("registerForm", registerForm);
		return "authentication/register";
	}

	@PostMapping("/register")
	public String register(@ModelAttribute("registerForm") @Valid RegisterForm registerForm,
						BindingResult bindingResult,
						Errors errors,
						Model model, RedirectAttributes redirectAttributes) {
		if (errors.hasErrors()) {
			model.addAttribute("registerForm", registerForm);
			return "authentication/register";
		}
		homeService.register(registerForm);
		redirectAttributes.addFlashAttribute("message", "Account successfully registered!");
		return "redirect:/register";
	}

	@GetMapping("/login/google")
	public String loginGoogleAuth() {
		String backendService = homeService.loginGoogle();
		return "redirect:" + backendService;
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		homeService.logout();
		// Invalidate session di client
		request.getSession().invalidate();

		// Redirect ke halaman login setelah logout
		return "redirect:/?logout";
	}
}
