package com.example.demo.form.app.auth.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.FlashMap;

import org.springframework.web.servlet.support.SessionFlashMapManager;
@Component
public class LoginSucccesHandler extends SimpleUrlAuthenticationSuccessHandler {//extendemos de esta clase

	public LoginSucccesHandler() {
		// TODO Auto-generated constructor stub
	}

	public LoginSucccesHandler(String defaultTargetUrl) {
		super(defaultTargetUrl);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// obtenemos el flash
		SessionFlashMapManager flashMapManager=new SessionFlashMapManager();
		FlashMap flashMap = new FlashMap();//Aqui no s epuede inyectar con el RedirectAtribute como en el controlador por eso se hace así
		flashMap.put("success", "Hola,"+ authentication.getName()+" haz  iniciado sesión con exito");
		flashMapManager.saveOutputFlashMap(flashMap, request, response);
		if(authentication !=null) {
			logger.info("El usuario '"+authentication.getName()+"' ha iniciado sesión con exito");
		}
		super.onAuthenticationSuccess(request, response, authentication);
	}

}
