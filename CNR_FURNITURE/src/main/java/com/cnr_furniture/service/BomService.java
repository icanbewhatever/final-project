package com.cnr_furniture.service;

import com.cnr_furniture.domain.ItemInfo.ItemInfoVO;
import com.cnr_furniture.domain.MaterialInfo.MaterialInfoSearch;
import com.cnr_furniture.domain.MaterialInfo.MaterialInfoVO;
import com.cnr_furniture.domain.bom.BomSearch;
import com.cnr_furniture.domain.bom.BomVO;

import java.util.List;

public interface BomService {

    List<ItemInfoVO> getBomInfoList(BomSearch bomSearch); //BOM관리 제품목록 조회, 검색

    List<BomVO> getBomDetails(); //BOM관리 bom 세부목록 조회

    List<BomVO> getListBom(int i_id); // BOM관리 제품번호 한개 값 가져오기

    List<MaterialInfoVO> getMaterialList(MaterialInfoSearch mtSearch); //*BOM 등록의 자재목록 조회, 검색

    int modify(BomVO bomVO); // BOM관리의 BOM수정

    int modifyAll(BomVO bomVO); //Bom Insert bom 추가 후 수정

    int insertBomList(BomVO bomVO); //Bom Insert bom추가


}
