package kr.ac.kopo.address.service;

import java.util.List;

import kr.ac.kopo.address.dao.AddressDAO;
import kr.ac.kopo.address.dao.AddressDAOImpl;
import kr.ac.kopo.address.vo.AddressVO;

public class AddressService {

    private AddressDAO addressDAO;

    public AddressService() {
        this.addressDAO = new AddressDAOImpl();
    }

    // 주소 추가
    public boolean addAddress(AddressVO address) {
        try {
            return addressDAO.insertAddress(address);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 기본 배송지 해제
    public void updateDefaultAddress(String userId) {
        try {
            addressDAO.resetDefaultAddress(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
 // 사용자별 주소 조회 추가
    public List<AddressVO> getAddressesByUserId(String userId) {
        return addressDAO.selectAddressesByUserId(userId);
    }

}
