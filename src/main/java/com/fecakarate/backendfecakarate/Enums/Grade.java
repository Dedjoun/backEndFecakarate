package com.fecakarate.backendfecakarate.Enums;




public enum Grade {
    K_6_KYU("6EME KYU"),
    K_5_KYU("5EME KYU"),
    K_4_KYU("4EME KYU"),
    K_3_KYU("3EME KYU"),
    K_2_KYU("2EME KYU"),
    K_1_KYU("1ER KYU"),
    D_1_N("1ER DAN"),
    D_2_N("2EME DAN"),
    D_3_N("3EME DAN"),
    D_4_N("4EME DAN"),
    D_5_N("5EME DAN"),
    D_6_N("6EME DAN"),
    D_7_N("7EME DAN"),
    D_8_N("8EME DAN"),
    D_9_N("9EME DAN"),
    D_10_N("10EME DAN");

  private String description;

  Grade(String description){
      this.description = description;
  }

  public String getDescription(){
      return description;
  }
}
