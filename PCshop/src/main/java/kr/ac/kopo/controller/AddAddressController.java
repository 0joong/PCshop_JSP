package kr.ac.kopo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import kr.ac.kopo.address.service.AddressService;
import kr.ac.kopo.address.vo.AddressVO;
import kr.ac.kopo.member.vo.MemberVO;

public class AddAddressController implements Controller {

    private AddressService addressService;

    public AddAddressController() {
        this.addressService = new AddressService();
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            // JSON 요청 파싱
            String requestBody = request.getReader().lines()
                .reduce("", (accumulator, actual) -> accumulator + actual);

            // 파싱된 JSON 데이터를 AddressVO로 매핑
            JsonObject json = new Gson().fromJson(requestBody, JsonObject.class);
            AddressVO address = new AddressVO();
            
            MemberVO user = (MemberVO)request.getSession().getAttribute("user");
            String userId = user.getId(); 
            
            address.setName(json.get("name").getAsString());
            address.setPhone(json.get("phone").getAsString());
            address.setUserId(userId); // 세션에서 userId 가져오기
            address.setPostalCode(json.get("postcode").getAsString());
            address.setRoadAddr(json.get("roadAddress").getAsString());
            address.setDetailAddr(json.get("detailAddress").getAsString());
            address.setExtraAddr(json.get("extraAddress").getAsString());
            address.setAlias(json.get("alias").getAsString());
            address.setIsDefault(json.get("isDefault").getAsString());

            System.out.println("파싱된 AddressVO: " + address); // 디버깅용 출력

            // 서비스 호출
            boolean isAdded = addressService.addAddress(address);
            if (isAdded) {
                response.getWriter().write("success");
            } else {
                response.getWriter().write("failure");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("error");
        }
        return null; // AJAX 응답만 처리
    }

}
