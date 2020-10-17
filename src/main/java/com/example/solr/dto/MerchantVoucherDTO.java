package com.example.solr.dto;


import lombok.Data;

/**
 * @author rishi - created on 14/06/20
 **/
@Data
public class MerchantVoucherDTO {
  private String voucherName;
  private String voucherCode;
  private VoucherStatus voucherStatus;
  private String redemptionType;
  private String combinationType;
  private String rewardType;
  private boolean displayOnProductDetail;
  private String changeLog;
  private boolean allStore;
  private String storeName;
  private String merchantName;
  private String merchantCode;
  private long itemSkuCount;
  private boolean markForStop;
  private long minAmountSpend;
  private String startDate;
  private String endDate;
  private String markForDelete;
  private String itemSku;
  private String storeId;
}
