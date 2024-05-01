package com.cnr_furniture.service;

import com.cnr_furniture.domain.MaterialInfo.MaterialInfoSearch;
import com.cnr_furniture.domain.MaterialInfo.MaterialInfoVO;
import com.cnr_furniture.mapper.MaterialInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j;

import java.util.List;

@Log4j
@Service
public class MaterialInfoServiceImpl implements MaterialInfoService{

    @Autowired
    MaterialInfoMapper mtInfoMapper;


    /** 자재 조회 및 검색 **/
    @Override
    public List<MaterialInfoVO> getMaterialInfo(MaterialInfoSearch MTInfoSearch) {
        return mtInfoMapper.getMaterialInfo(MTInfoSearch);
    }


    /** 자재 등록 **/
    @Override
    public int insertMaterial(
            String m_name,
            String m_uses
    ) {
        MaterialInfoVO materialInfoVO = new MaterialInfoVO();
        materialInfoVO.setM_name(m_name);
        materialInfoVO.setM_uses(m_uses);

        int rtn = mtInfoMapper.addMaterial(materialInfoVO);
        return rtn;
    }


    /** 수정 할 자재 조회 **/
    @Override
    public MaterialInfoVO getOneMaterial(int m_id) {
        return mtInfoMapper.getOneMaterial(m_id);
    }


    /** 자재 수정 **/
    @Override
    public int updateMaterial(
            String m_name,
            String m_uses,
            int m_id
    ) {
        MaterialInfoVO materialInfoVO = new MaterialInfoVO();
        materialInfoVO.setM_name(m_name);
        materialInfoVO.setM_uses(m_uses);
        materialInfoVO.setM_id(m_id);

        return mtInfoMapper.modifyMaterial(materialInfoVO);
    }
}


