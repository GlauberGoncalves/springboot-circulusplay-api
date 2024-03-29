package br.com.glauber.circulusplay.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.glauber.circulusplay.domain.LoginForm;
import br.com.glauber.circulusplay.dto.TokenDto;
import br.com.glauber.circulusplay.security.JWTUtil;

@RestController
@RequestMapping(value="/auth")
public class AutenticacaoResource {
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authManage;
	

	@PostMapping
	public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid LoginForm form){
		
		UsernamePasswordAuthenticationToken dadosLogin = form.converter();
		
		try {
			authManage.authenticate(dadosLogin);
			String token = jwtUtil.geraToken(dadosLogin.getName());			
			return ResponseEntity.ok().body(new TokenDto(token, "Bearer"));			
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
	}		
}
