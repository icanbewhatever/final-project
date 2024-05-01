package com.cnr_furniture.domain.MaterialInfo;

import lombok.Getter;
import lombok.Setter;

/**
 *검색을 위한 클래스
 */
@Getter
@Setter
public class MaterialInfoSearch {

  private String type;
  private String keyword;

  public String[] getTypeArr() {
    return type == null ? new String[] {} : new String[] {type};
  }
}
