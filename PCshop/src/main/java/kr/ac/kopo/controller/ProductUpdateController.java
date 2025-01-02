package kr.ac.kopo.controller;

import java.io.InputStream;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import kr.ac.kopo.component.dao.ComponentDAO;
import kr.ac.kopo.component.dao.ComponentDAOImpl;
import kr.ac.kopo.component.service.ComponentService;
import kr.ac.kopo.product.vo.ComponentVO;
import kr.ac.kopo.util.UploadToS3;

public class ProductUpdateController implements Controller {

	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
	    try {
	        // itemCd 파라미터 검증 및 처리
	        String itemCdParam = request.getParameter("itemCd");
	        if (itemCdParam == null || itemCdParam.isEmpty()) {
	            throw new IllegalArgumentException("itemCd 값이 없습니다.");
	        }
	        int itemCd = Integer.parseInt(itemCdParam);

	        // 폼 데이터 수집
	        String category = request.getParameter("category");
	        String name = request.getParameter("name");
	        String brand = request.getParameter("brand");

	        // 가격과 재고 처리 (예외 처리 추가)
	        double price = 0.0;
	        int stockQty = 0;
	        try {
	            price = Double.parseDouble(request.getParameter("price"));
	            stockQty = Integer.parseInt(request.getParameter("stockQty"));
	        } catch (NumberFormatException e) {
	            throw new IllegalArgumentException("가격 또는 재고 값이 잘못되었습니다.");
	        }

	        // 파일 업로드 처리 (썸네일과 상세 이미지)
	        UploadToS3 uploadToS3 = new UploadToS3();
	        String thumbImgUrl = null;
	        String detailImgUrl = null;

	        Part thumbImgPart = request.getPart("thumbImgFile");
	        Part detailImgPart = request.getPart("detailImgFile");

	        if (thumbImgPart != null && thumbImgPart.getSize() > 0) {
	            String thumbFileName = "thumbnails/" + System.currentTimeMillis() + "_" + thumbImgPart.getSubmittedFileName();
	            try (InputStream thumbImgStream = thumbImgPart.getInputStream()) {
	                thumbImgUrl = uploadToS3.uploadToS3(thumbFileName, thumbImgStream);
	            }
	        }

	        if (detailImgPart != null && detailImgPart.getSize() > 0) {
	            String detailFileName = "details/" + System.currentTimeMillis() + "_" + detailImgPart.getSubmittedFileName();
	            try (InputStream detailImgStream = detailImgPart.getInputStream()) {
	                detailImgUrl = uploadToS3.uploadToS3(detailFileName, detailImgStream);
	            }
	        }

	        // 기존 데이터를 업데이트
	        ComponentDAO componentDao = new ComponentDAOImpl();
	        ComponentService componentService = new ComponentService(componentDao);
	        ComponentVO component = componentService.getComponentById(itemCd);

	        if (component == null) {
	            throw new IllegalArgumentException("해당 itemCd에 대한 데이터를 찾을 수 없습니다.");
	        }

	        // 업데이트된 값 설정
	        component.setCategory(category);
	        component.setName(name);
	        component.setBrand(brand);
	        component.setPrice(price);
	        component.setStockQty(stockQty);

	        // 이미지를 새로 업로드한 경우에만 업데이트
	        if (thumbImgUrl != null) {
	            component.setThumbImgUrl(thumbImgUrl);
	        }
	        if (detailImgUrl != null) {
	            component.setDetailImgUrl(detailImgUrl);
	        }

	        // 데이터베이스 업데이트 실행
	        boolean isUpdated = componentService.updateComponent(component);
	        if (!isUpdated) {
	            throw new RuntimeException("데이터베이스 업데이트 실패");
	        }

	        // 성공 시 목록 페이지로 리다이렉트
	        return "redirect:/admin/productManagement.do";

	    } catch (IllegalArgumentException e) {
	        request.setAttribute("errorMsg", "잘못된 요청입니다: " + e.getMessage());
	        return "/admin/error.jsp"; // 에러 처리 페이지
	    } catch (Exception e) {
	        e.printStackTrace();
	        request.setAttribute("errorMsg", "상품 수정 중 오류가 발생했습니다: " + e.getMessage());
	        return "/admin/error.jsp"; // 에러 처리 페이지
	    }
	}

}
