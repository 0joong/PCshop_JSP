package kr.ac.kopo.payment.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import kr.ac.kopo.payment.vo.KakaoPayApprovalVO;
import okhttp3.*;

public class KakaoPayService {

	private static final String KAKAO_PAY_URL = "https://open-api.kakaopay.com/online/v1/payment/ready";
	private static final String KAKAO_APPROVE_URL = "https://open-api.kakaopay.com/online/v1/payment/approve";
	private static final String DEV_SECRET_KEY = "DEV47A1B6F9A2E9A44B3A3B4585384E6B838D35A"; // Dev Secret Key
	private static final String CID = "TC0ONETIME"; // 테스트용 CID
	private static final String localhost = "172.31.9.61";

	private String transactionId; // Ready 응답에서 받아오는 Transaction ID (TID)

	// 싱글톤 인스턴스
	private static KakaoPayService instance;

	// private 생성자
	private KakaoPayService() {
	}

	// 싱글톤 인스턴스 접근 메서드
	public static KakaoPayService getInstance() {
		if (instance == null) {
			synchronized (KakaoPayService.class) {
				if (instance == null) {
					instance = new KakaoPayService();
				}
			}
		}
		return instance;
	}

	// 결제 준비
	public String preparePayment(String jsonData, String jsessionId) throws Exception {
		OkHttpClient client = new OkHttpClient();
		Gson gson = new Gson();

		JsonObject requestData = gson.fromJson(jsonData, JsonObject.class);
		
		// JSON 데이터에서 필요한 정보 추출
		String addressId = requestData.get("addressId").getAsString(); // addressId 추출
		String userId = requestData.get("userId").getAsString();
		String itemNames = requestData.get("itemNames").getAsString(); // 클라이언트에서 전달된 itemNames 사용
		int totalAmount = requestData.get("totalPrice").getAsInt();

		JsonObject kakaoRequest = new JsonObject();
		kakaoRequest.addProperty("cid", CID);
		kakaoRequest.addProperty("partner_order_id", "1001");
		kakaoRequest.addProperty("partner_user_id", userId);
		kakaoRequest.addProperty("item_name", itemNames);
		kakaoRequest.addProperty("quantity", 1);
		kakaoRequest.addProperty("total_amount", totalAmount);
		kakaoRequest.addProperty("tax_free_amount", 0);
		String approval_url = String.format("http://%s:8080/PCshop/payment/approval.do?addressId=%s&jsessionId=%s", localhost, addressId, jsessionId);
		String cancel_url = String.format("http://%s:8080/PCshop/payment/cancel.do", localhost);
		String fail_url = String.format("http://%s:8080/PCshop/payment/fail.do", localhost);
		System.out.println(approval_url);
		System.out.println(cancel_url);
		System.out.println(fail_url);
		kakaoRequest.addProperty("approval_url", approval_url);
		kakaoRequest.addProperty("cancel_url", cancel_url);
		kakaoRequest.addProperty("fail_url", fail_url);
		//kakaoRequest.addProperty("approval_url", "http://localhost:8080/PCshop/payment/approval.do?addressId=" + addressId);
		//kakaoRequest.addProperty("cancel_url", "http://localhost:8080/PCshop/payment/cancel.do");
		//kakaoRequest.addProperty("fail_url", "http://localhost:8080/PCshop/payment/fail.do");

		RequestBody body = RequestBody.create(kakaoRequest.toString(),
				MediaType.get("application/json; charset=utf-8"));
		Request request = new Request.Builder().url(KAKAO_PAY_URL).post(body)
				.addHeader("Authorization", "DEV_SECRET_KEY " + DEV_SECRET_KEY)
				.addHeader("Content-Type", "application/json").build();

		try (Response response = client.newCall(request).execute()) {
			if (!response.isSuccessful()) {
				System.out.println("응답 코드: " + response.code());
				System.out.println("응답 메시지: " + response.message());
				System.out.println("응답 바디: " + response.body().string());
				throw new Exception("Kakao Pay API 호출 실패: " + response.message());
			}

			String responseBody = response.body().string();
			System.out.println("결제 준비 응답: " + responseBody);

			JsonObject kakaoResponse = gson.fromJson(responseBody, JsonObject.class);
			transactionId = kakaoResponse.get("tid").getAsString(); // TID 저장
			System.out.println(transactionId);
			return kakaoResponse.get("next_redirect_pc_url").getAsString();
		}
	}

	// 결제 승인
	public KakaoPayApprovalVO approvePayment(String pgToken, String userId) throws Exception {
		OkHttpClient client = new OkHttpClient();

		System.out.println("------------");
		System.out.println(userId);
		System.out.println(pgToken);
		System.out.println(transactionId);
		System.out.println("------------");
		// 승인 요청 데이터 생성 (JSON 형식)
		JsonObject approveRequest = new JsonObject();
		approveRequest.addProperty("cid", CID);
		approveRequest.addProperty("tid", transactionId); // preparePayment 메서드에서 저장한 TID
		approveRequest.addProperty("partner_order_id", "1001");
		approveRequest.addProperty("partner_user_id", userId);
		approveRequest.addProperty("pg_token", pgToken);

		RequestBody body = RequestBody.create(approveRequest.toString(),
				MediaType.get("application/json; charset=utf-8"));

		// HTTP 요청 생성
		Request request = new Request.Builder().url(KAKAO_APPROVE_URL).post(body)
				.addHeader("Authorization", "DEV_SECRET_KEY " + DEV_SECRET_KEY)
				.addHeader("Content-Type", "application/json").build();
		System.out.println(request);
		// 요청 실행 및 응답 처리
		try (Response response = client.newCall(request).execute()) {
			if (response.isSuccessful()) {
				String responseBody = response.body().string();
				Gson gson = new Gson();
				System.out.println(responseBody);
				return gson.fromJson(responseBody, KakaoPayApprovalVO.class); // VO 객체로 반환
			} else {
				System.out.println("응답 코드: " + response.code());
				System.out.println("응답 메시지: " + response.message());
				System.out.println("응답 바디: " + response.body().string());
				throw new Exception("결제 승인 요청 실패: " + response.message());
			}
		}
	}

}
