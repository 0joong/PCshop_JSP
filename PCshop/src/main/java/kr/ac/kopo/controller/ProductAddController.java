package kr.ac.kopo.controller;

import java.io.IOException;
import java.io.InputStream;
import com.google.gson.JsonObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import kr.ac.kopo.component.dao.ComponentDAO;
import kr.ac.kopo.component.dao.ComponentDAOImpl;
import kr.ac.kopo.component.service.ComponentService;
import kr.ac.kopo.component.vo.CPUInfoVO;
import kr.ac.kopo.component.vo.MainboardInfoVO;
import kr.ac.kopo.product.vo.ComponentVO;
import kr.ac.kopo.util.UploadToS3;

public class ProductAddController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String category;
		try {
			// Form 데이터 수집
			category = request.getParameter("category");
			String name = request.getParameter("name");
			String brand = request.getParameter("brand");

			int price;
			int stockQty;

			try {
				price = Integer.parseInt(request.getParameter("price"));
				stockQty = Integer.parseInt(request.getParameter("stockQty"));
			} catch (NumberFormatException e) {
				throw new IllegalArgumentException("가격 또는 재고 값이 잘못되었습니다.");
			}

			// 파일 업로드 처리
			UploadToS3 uploadToS3 = new UploadToS3();
			String thumbImgUrl = null;
			String detailImgUrl = null;

			// Thumbnail 이미지 업로드
			Part thumbImgPart = request.getPart("thumbImgFile");
			if (thumbImgPart != null && thumbImgPart.getSize() > 0) {
				String thumbFileName = "thumbnails/" + System.currentTimeMillis() + "_"
						+ thumbImgPart.getSubmittedFileName();
				try (InputStream thumbImgStream = thumbImgPart.getInputStream()) {
					thumbImgUrl = uploadToS3.uploadToS3(thumbFileName, thumbImgStream);
				} catch (Exception e) {
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "썸네일 업로드 중 오류가 발생했습니다.");
					return null;
				}
			} else {
				thumbImgUrl = "https://pcshop1.s3.ap-northeast-2.amazonaws.com/ready.jpg";
			}

			// Detail 이미지 업로드
			Part detailImgPart = request.getPart("detailImgFile");
			if (detailImgPart != null && detailImgPart.getSize() > 0) {
				String detailFileName = "details/" + System.currentTimeMillis() + "_"
						+ detailImgPart.getSubmittedFileName();
				try (InputStream detailImgStream = detailImgPart.getInputStream()) {
					detailImgUrl = uploadToS3.uploadToS3(detailFileName, detailImgStream);
				} catch (Exception e) {
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "상세 이미지 업로드 중 오류가 발생했습니다.");
					return null;
				}
			} else {
				detailImgUrl = "https://pcshop1.s3.ap-northeast-2.amazonaws.com/ready.jpg";
			}

			// ComponentVO 객체 생성
			ComponentVO component = new ComponentVO();
			component.setCategory(category);
			component.setName(name);
			component.setBrand(brand);
			component.setPrice(price);
			component.setStockQty(stockQty);
			component.setThumbImgUrl(thumbImgUrl);
			component.setDetailImgUrl(detailImgUrl);

			// 데이터베이스 추가
			ComponentDAO componentDao = new ComponentDAOImpl();
			ComponentService componentService = new ComponentService(componentDao);
			
			boolean isAdded = false;
			
			try {
			    // Component 공통 데이터 추가
			    int itemCd = componentService.addComponent(component); // Component 추가 후 생성된 itemCd 반환
			    System.out.println("componentVO itemCd: " + component.getItemCd());
			    System.out.println("returned itemCd : " + itemCd);
			    if (itemCd == 0 || itemCd == -1) {
			        throw new RuntimeException("Component 추가 실패");
			    }

			    // 카테고리에 따라 CPUInfoVO 또는 MainboardInfoVO 생성 및 데이터 저장
			    if ("cpu".equalsIgnoreCase(category)) {
			        CPUInfoVO cpuInfo = new CPUInfoVO();
			        cpuInfo.setSocket(request.getParameter("cpuSocket"));
			        cpuInfo.setCore(Integer.parseInt(request.getParameter("cpuCore")));
			        cpuInfo.setThread(Integer.parseInt(request.getParameter("cpuThread")));
			        cpuInfo.setBaseClock(Double.parseDouble(request.getParameter("cpuBaseClock")));
			        cpuInfo.setBoostClock(Double.parseDouble(request.getParameter("cpuBoostClock")));
			        cpuInfo.setItemCd(component.getItemCd()); // Component의 itemCd를 설정

			        // CPU 데이터 저장
			        componentService.addCPUInfo(cpuInfo);
			        isAdded = true;

			    } else if ("mainboard".equalsIgnoreCase(category)) {
			        MainboardInfoVO mainboardInfo = new MainboardInfoVO();
			        mainboardInfo.setSocket(request.getParameter("mainboardSocket"));
			        mainboardInfo.setChipset(request.getParameter("mainboardChipset"));
			        mainboardInfo.setFormFactor(request.getParameter("mainboardFormFactor"));
			        mainboardInfo.setMemSlot(Integer.parseInt(request.getParameter("mainboardMemSlot")));
			        mainboardInfo.setItemCd(component.getItemCd()); // Component의 itemCd를 설정

			        // Mainboard 데이터 저장
			        componentService.addMainboardInfo(mainboardInfo);
			        isAdded = true;

			    } else {
			        throw new IllegalArgumentException("유효하지 않은 카테고리입니다.");
			    }

			} catch (Exception e) {
			    e.printStackTrace();
			    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "상품 추가 중 오류가 발생했습니다.");
			}
			
			// JSON 응답
			JsonObject jsonResponse = new JsonObject();
			jsonResponse.addProperty("success", isAdded);
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(jsonResponse.toString());

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "잘못된 요청: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "상품 추가 중 오류가 발생했습니다.");
		}
		return null;
	}
}