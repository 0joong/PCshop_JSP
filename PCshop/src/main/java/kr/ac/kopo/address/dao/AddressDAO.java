package kr.ac.kopo.address.dao;

import java.util.List;
import kr.ac.kopo.address.vo.AddressVO;

public interface AddressDAO {
    // 사용자 ID로 모든 배송지 조회
    List<AddressVO> selectAddressesByUserId(String userId);

    // 배송지 추가
    boolean insertAddress(AddressVO address);

    // 배송지 수정
    void updateAddress(AddressVO address);

    // 배송지 삭제
    void deleteAddress(int addressId);
    
    void resetDefaultAddress(String userId);
}
