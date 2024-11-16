package com.riki.client.service;

import com.riki.client.form.LoginForm;
import com.riki.client.form.RegisterForm;

public interface HomeService {

	public void login(LoginForm loginForm);

	public void register(RegisterForm registerForm);

	public String loginGoogle();

	public void logout();

}
