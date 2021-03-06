package com.spring.controller;



import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.domain.MemberVO;
import com.spring.domain.MessageVO;
import com.spring.service.MemberRestService;
import com.spring.service.MemberService;
import com.spring.service.MessageService;
import com.spring.service.TempKey;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

@RequestMapping("/check/*")
@RestController
@Log4j
public class MemberRestController {
	
	@Setter(onMethod_ = @Autowired)
	private MemberRestService service;
	
	@Setter(onMethod_ = @Autowired)
	private MemberService memberService;
	
	@Setter(onMethod_ = @Autowired)
	private MessageService messageService;
	
	
	//조회
	@GetMapping(value = "/{email}", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<MemberVO> idCheck(@PathVariable("email") String email) {
		log.info("컨트롤러에서 확인 : " + service.idCheck(email));
		
		
		return new ResponseEntity<>(service.idCheck(email), HttpStatus.OK);
	}
	
	
	//핸드폰 메세지보내기
	@PostMapping(value = "/phone/{phone}", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<MemberVO> phoneCheck(@PathVariable("phone") String phone, HttpSession session) throws CoolsmsException {
		
		
		String api_key = "";
		String api_secret = "";
		Message coolsms = new Message(api_key, api_secret);// 메시지보내기 객체 생성
		String authkey = new TempKey().getKey(4, false);
		String phoneNum = phone;
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("to", phone); // 수신번호
		params.put("from", "01039222459"); // 발신번호
		params.put("type", "SMS"); // 문자 타입
		params.put("text", "7hours 인증키는 [ " + authkey + " ] 입니다."); // 문자내용
		params.put("charset", "utf-8");
		params.put("delay", "0");
		MessageVO message = new MessageVO();
		
		message.setAuthkey(authkey);
		message.setPhone(phoneNum);
		messageService.insert(message);
		coolsms.send(params);
		
		session.setAttribute("message", message);
		
		return null;//new ResponseEntity<>(service.phoneCheck(phone), HttpStatus.OK);
		
		
	}	
	
	
	
	
	
}
