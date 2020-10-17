package com.example.solr.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
        .markForStop(true)
        .itemSkuCount(20)
        .redemptionType("COUPON")
        .voucherCode("MV-002001")
        .voucherStatus(VoucherStatus.ACTIVE.toString())
        .build();
  }
}
