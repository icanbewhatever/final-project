package com.cnr_furniture.service;

import com.cnr_furniture.domain.ItemInfo.ItemInfoSearch;
import com.cnr_furniture.domain.ItemInfo.ItemInfoVO;
import com.cnr_furniture.mapper.ItemInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j;

import java.util.List;

@Log4j
@Service
public class ItemInfoServiceImpl implements ItemInfoService{

    @Autowired
    private ItemInfoMapper itemInfoMapper;


    /** 제품 조회 및 검색 **/
    @Override
    public List<ItemInfoVO> getItemInfoList(ItemInfoSearch itemInfoSearch) {

        return itemInfoMapper.getItemInfoList(itemInfoSearch);
    }

    /** 수정할 제품 값 불러오기 **/
    @Override
    public ItemInfoVO getOneItem(int i_id) {
        return itemInfoMapper.getOneItem(i_id);
    }

    /** 제품 등록 **/
    @Override
    public int insertItem(
            String i_name,
            String i_color,
            String i_standard,
            String i_type,
            String i_uses
    ) {
        ItemInfoVO itemInfoVO = new ItemInfoVO();
        itemInfoVO.setI_name(i_name);
        itemInfoVO.setI_color(i_color);
        itemInfoVO.setI_standard(i_standard);
        itemInfoVO.setI_type(i_type);
        itemInfoVO.setI_uses(i_uses);

        int rtn = itemInfoMapper.addItem(itemInfoVO);
        return rtn;
    }

    /** 제품 수정 **/
    @Override
    public int updateItem(
            String i_name,
            String i_color,
            String i_standard,
            String i_type,
            String i_uses,
            int i_id
    ) {
        ItemInfoVO itemInfoVO = new ItemInfoVO();
        itemInfoVO.setI_name(i_name);
        itemInfoVO.setI_color(i_color);
        itemInfoVO.setI_standard(i_standard);
        itemInfoVO.setI_type(i_type);
        itemInfoVO.setI_uses(i_uses);
        itemInfoVO.setI_id(i_id);

        return itemInfoMapper.modifyList(itemInfoVO);
    }
}
