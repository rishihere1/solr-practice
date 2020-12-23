package com.example.solr.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.solr.client.solrj.response.Group;
import org.apache.solr.client.solrj.response.GroupCommand;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import com.example.solr.document.MerchantVoucherSolrDocument;
import com.example.solr.dto.VoucherStatus;

/**
 * @author rishi - created on 16/08/20
 **/
public class SolrUtil {
//  public static List<MerchantVoucherSolrDocument> getDocumentFromSolrResponse(QueryResponse queryResponse) {
//    List<SolrDocumentList> solrDocumentLists = getMerchantVoucherSolrDocument(queryResponse);
//    List<MerchantVoucherSolrDocument> solrResponse = new ArrayList<>();
//    if(solrDocumentLists != null) {
//      for (SolrDocumentList solrDocuments : solrDocumentLists) {
//        solrResponse.addAll(toMerchantVoucherSolrVOs(solrDocuments));
//      }
//    }
//    return solrResponse;
//  }
//
//  public static List<SolrDocumentList> getMerchantVoucherSolrDocument(QueryResponse queryResponse) {
//    List<SolrDocumentList> solrDocumentLists = null;
//    if (Objects.nonNull(queryResponse.getGroupResponse())) {
//      List<GroupCommand> groupCommands = queryResponse.getGroupResponse().getValues();
//      for (GroupCommand groupCommand : groupCommands) {
//        solrDocumentLists =
//            groupCommand.getValues().stream().map(Group::getResult).collect(Collectors.toList());
//      }
//    }
//    return solrDocumentLists;
//  }
//
//  private static List<MerchantVoucherSolrDocument> toMerchantVoucherSolrVOs(SolrDocumentList solrDocumentList) {
//    List<MerchantVoucherSolrDocument> merchantVoucherSolrVOList = new ArrayList<>();
//    solrDocumentList.forEach(solrDocument -> merchantVoucherSolrVOList.add(toMerchantVoucherSolrVo(solrDocument)));
//    return merchantVoucherSolrVOList;
//  }
//
//  private static MerchantVoucherSolrDocument toMerchantVoucherSolrVo(SolrDocument solrDocument) {
//    MerchantVoucherSolrDocument merchantVoucherSolrDocument = new MerchantVoucherSolrDocument();
//    merchantVoucherSolrDocument.setVoucherName(String.valueOf(solrDocument.getFieldValue("voucherName")));
//    return merchantVoucherSolrDocument;
//  }

  public static MerchantVoucherSolrDocument getMerchantVoucherDocument() {
    return MerchantVoucherSolrDocument.builder()
        .id("56118edd-bb36-485e-a29b-5357ae9a3ea3")
        .merchantCode("TOQ-32110")
        .voucherName("Blibli voucher")
        .allStore(true)
        .itemSku("TOQ-32110-00010-00001")
//        .markForStop(true)
        .itemSkuCount(20)
        .redemptionType("COUPON")
        .voucherCode("MV-002001")
        .voucherStatus(VoucherStatus.ACTIVE.toString())
        .build();
  }

  public static MerchantVoucherSolrDocument getMerchantVoucherSolrDocumentInBulk(int i) {
    return MerchantVoucherSolrDocument.builder()
        .id(UUID.randomUUID().toString())
        .voucherCode("MV-" + getVoucherCode(i))
        .itemSku(getItemSku(i))
        .allStore(i % 2 == 0)
//        .markForStop(i % 3 == 0)
        .merchantCode(getMerchantCode(i))
        .redemptionType(i%4 == 0 ? "AMOUNT_OFF" : "DISCOUNT")
        .itemSkuCount(i)
        .voucherName("Seller voucher - " + getVoucherCode(i))
        .voucherStatus(i%5==0? VoucherStatus.ACTIVE.name() : i%9 == 0? VoucherStatus.EXPIRED.name() : VoucherStatus.PENDING.name())
        .startDate(getDate(i))
        .endDate(getDate(i + 1))
        .markForDelete(i%8 ==0)
        .minAmountSpend(i * 3000 + 300+500)
        .storeId("10001")
        .productUrls(getProductUrls(i))
        .build();
  }

  private static List<String> getProductUrls(int i) {
    List<String> productUrls = new ArrayList<>();
    if (i % 2 == 0) {
      productUrls.add("https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png");
      productUrls.add(
          "https://pngtree.com/element/down?id=NTIyMjA3OA==&type=1&time=1608743829&token"
              + "=NjRiNWQ4ZGYyNjcwMjkwYzhhODNkZTE2ZjBhZTkwZTg=&t=0");
      productUrls.add("https://i.pinimg.com/originals/c0/f8/ba/c0f8ba185db581d6f312d860f8004257.png");
      return productUrls;
    }
    productUrls.add("https://www.pngfind.com/mpng/xTohJh_latest-smartphones-android-mobile-phones-at-best-smartphone/");
    productUrls.add(
        "https://toppng.com/free-image/lobal-identity-verification-transparent-android-mobile-PNG-free-PNG"
            + "-Images_170142");
    productUrls.add("https://www.pngfind.com/mpng/TRobwhT_smartphone-hd-png-download/");
    return productUrls;
  }

  private static Date getDate(int i) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(new Date());
    calendar.add(Calendar.DAY_OF_MONTH, i);
    return calendar.getTime();
  }

  private static String getVoucherCode(int i) {
    String voucherCode = "";
    if(i/10 == 0){
      voucherCode = voucherCode + "0000" + i ;
    } else if (i/100==0) {
      voucherCode = voucherCode + "000" + i ;
    } else if (i/1000 == 0) {
      voucherCode = voucherCode + "00" + i ;
    }
    return voucherCode;
  }

  public static String getItemSku(int i) {
    if (i % 2 == 0) {
      return "TOQ-16110-00001-" + completeItemSku(i);
    } else if (i % 3 == 0) {
      return "CHA-15132-00001-" + completeItemSku(i);
    } else if (i % 5 == 0) {
      return "TOL-16162-00001-" + completeItemSku(i);
    } else if (i % 7 == 0) {
      return "AQU-64385-00001-" + completeItemSku(i);
    } else {
      return "AYU-64223-00001-" + completeItemSku(i);
    }
  }

  public static String getMerchantCode(int i) {
    if (i % 2 == 0) {
      return "TOQ-16110";
    } else if (i % 3 == 0) {
      return "CHA-15132";
    } else if (i % 5 == 0) {
      return "TOL-16162";
    } else if (i % 7 == 0) {
      return "AQU-64385";
    } else {
      return "AYU-64223";
    }
  }

  public static String completeItemSku(int i) {
    if (i / 10 == 0) {
      return "0000" + i;
    } else if (i / 100 == 0) {
      return "000" + i;
    } else if (i / 1000 == 0) {
      return "00" + i;
    } else if (i / 10000 == 0) {
      return "0" + i;
    } else
      return String.valueOf(i);
  }
}
