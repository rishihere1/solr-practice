package com.example.solr.document;

import java.util.Date;
import java.util.List;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;

import lombok.Builder;
import lombok.Data;

/**
 * @author rishi - created on 15/08/20
 **/
@Data
@Builder
public class MerchantVoucherSolrDocument {

  @Id
  @Field(value = "id")
  private String id;

  @Field(value = "voucherName")
  private String voucherName;

  @Field(value = "voucherStatus")
  private String voucherStatus;

  @Field(value = "redemptionType")
  private String redemptionType;

  @Field(value = "allStore")
  private boolean allStore;

  @Field(value = "merchantCode")
  private String merchantCode;

  @Field(value = "itemSkuCount")
  private long itemSkuCount;

  @Field(value = "itemSku")
  private String itemSku;

  @Field(value = "voucherCode")
  private String voucherCode;

  @Field(value = "minAmountSpend")
  private long minAmountSpend;

  @Field(value = "startDate")
  private Date startDate;

  @Field(value = "endDate")
  private Date endDate;

  @Field(value = "markForDelete")
  private boolean markForDelete;

  @Field(value = "storeId")
  private String storeId;

  @Field(value = "productUrls")
  private List<String> productUrls;

}
