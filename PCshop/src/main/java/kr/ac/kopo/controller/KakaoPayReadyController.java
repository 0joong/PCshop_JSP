package kr.ac.kopo.controller;

import java.io.BufferedReader;
import java.io.PrintWriter;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.ac.kopo.payment.service.KakaoPayService;

public class KakaoPayReadyController implements Controller {

	private KakaoPayService kakaoPayService;

	public KakaoPayReadyController() {
		this.kakaoPayService = KakaoPayService.getInstance();
	}

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 클라이언트로부터 전달받은 JSON 데이터 처리
		BufferedReader reader = request.getReader();
		StringBuilder temp = new StringBuilder();
		String line;

		while ((line = reader.readLine()) != null) {
			temp.append(line);
		}
		
		String jsonData = temp.toString();

		try {
			
			HttpSession session = request.getSession();
			String jsessionId = session.getId();
			
			Cookie sessionCookie = new Cookie("JSESSIONID", session.getId());
			sessionCookie.setPath("/"); // 경로 설정
			sessionCookie.setHttpOnly(true); // 보안 설정
			response.addCookie(sessionCookie);
			// KakaoPayService를 이용해 결제 준비 요청
			String redirectUrl = kakaoPayService.preparePayment(jsonData, jsessionId);
			
			
			// 응답에 URL 반환
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			out.print("{\"next_redirect_pc_url\": \"" + redirectUrl + "\"}");
			out.flush();
			return null; // JSON 응답이므로 JSP 포워드 없음
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return null;
		}
	}
}
