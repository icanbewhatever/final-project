package com.cnr_furniture.domain.work.workMNG;

import lombok.*;

/**
 * work.jsp 의 작업목록, 생산실적 VO
 */
@Getter
@Setter
public class WorkVO {
    // 작업목록
    private int rn;
    private int w_id;
    private int w_lot_id;
    private String w_date;
    private String e_dp_name;
    private int w_pi_id;
    private String pi_name;
    private String mi_name;
    private String position;
    private int ins_item_id;
    private String i_name;
    private String w_status;
    private String i_standard;
    private String ct_unit;
    private String w_time;
    private int w_plan_quantity;
    private int w_item_quantity;

    // 작업목록 + 생산실적 (작업자 사원번호,불량수량 추가)
    private String allemplist;
    private String substrallemp;
    private int w_dft_quantity;

}
