package com.spring.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.spring.api.NaverLoginBO;
import com.spring.domain.SocialVO;
import com.spring.service.SocialService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/social/*")
@Log4j

public class SocialController {

	@Setter(onMethod_ = @Autowired)
	private SocialService service;
	
	/* NaverLoginBO */
	private NaverLoginBO naverLoginBO;
	private String apiResult = null;

	@Autowired
	private void setNaverLoginBO(NaverLoginBO naverLoginBO) {
		this.naverLoginBO = naverLoginBO;
	}

	
	//db에 처음 로그인 했을 때 데이터 저장
	@PostMapping("/insert")
	public String insert(SocialVO social) {
		log.info("insert : " + social);
		
		service.insert(social);
		
		return "/page/home";
	}
	
	//첫번쨰 이후 로그인 시, 마지막 접속 날짜값만 SYSDATE로 업데이트
	@PostMapping("/update")
	public String update(SocialVO  social) {
		log.info("update : " + social);
		
		service.modify(social);
		
		return "redirect:/page/home";
	}
	
	//db에 저장이 되어있는지 안되어있는지 count Ajax
	//cnt = 0 insert / cnt = 1 update
	@PostMapping("/count")
	@ResponseBody
	public Map<Object, Object> count(SocialVO social) {
		
		int count = 0;
		Map<Object, Object> map = new HashMap<Object, Object>();
		
		count = service.count(social);
		
		log.info("cnt : " + count);
		
		map.put("cnt", count);
		
		return map;
	}
	
	
	// 로그인 첫 화면 요청 메소드
		@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
		public String login(Model model, HttpSession session) {

			/* 네이버아이디로 인증 URL을 생성하기 위하여 naverLoginBO클래스의 getAuthorizationUrl메소드 호출 */
			String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);

			// https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=sE***************&
			// redirect_uri=http%3A%2F%2F211.63.89.90%3A8090%2Flogin_project%2Fcallback&state=e68c269c-5ba9-4c31-85da-54c16c658125
			System.out.println("네이버:" + naverAuthUrl);

			// 네이버
			model.addAttribute("url", naverAuthUrl);

			/* 생성한 인증 URL을 View로 전달 */
			return "naverLogin";
		}

		// 네이버 로그인 성공시 callback호출 메소드
		@RequestMapping(value = "/callback", method = { RequestMethod.GET, RequestMethod.POST })
		public String callback(Model model, @RequestParam String code, @RequestParam String state, HttpSession session)
				throws IOException {
			System.out.println("callback 컨트롤러");
			OAuth2AccessToken oauthToken;
			oauthToken = naverLoginBO.getAccessToken(session, code, state);
			// 로그인 사용자 정보를 읽어온다.
			apiResult = naverLoginBO.getUserProfile(oauthToken);

			System.out.println("apiResult : " + apiResult);
			model.addAttribute("result", apiResult);
			
			
			/* 네이버 로그인 성공 페이지 View 호출 */
			return "naverCallback";
		}
	
	
}
