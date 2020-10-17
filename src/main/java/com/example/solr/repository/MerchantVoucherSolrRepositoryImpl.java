package com.example.solr.repository;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.stereotype.Repository;

import com.example.solr.document.MerchantVoucherSolrDocument;
import lombok.SneakyThrows;

/**
 * @author rishi - created on 15/08/20
 **/
@Repository
public class MerchantVoucherSolrRepositoryImpl {

  SolrClient solr = new HttpSolrClient.Builder("http://localhost:8983/solr/merchant_voucher").build();

  public void addVoucherToSolr(MerchantVoucherSolrDocument merchantVoucherSolrDocument) throws IOException, SolrServerException {
    SolrInputDocument document = new SolrInputDocument();

    document.addField("voucherName", merchantVoucherSolrDocument.getVoucherName());
    document.addField("voucherStatus", merchantVoucherSolrDocument.getVoucherStatus());
    document.addField("redemptionType", merchantVoucherSolrDocument.getRedemptionType());
    document.addField("allStore", merchantVoucherSolrDocument.isAllStore());
    document.addField("merchantCode", merchantVoucherSolrDocument.getMerchantCode());
    document.addField("itemSkuCount", merchantVoucherSolrDocument.getItemSkuCount());
    document.addField("itemSku", merchantVoucherSolrDocument.getItemSku());
    document.addField("markForStop", merchantVoucherSolrDocument.isMarkForStop());
    document.addField("minAmountSpend", merchantVoucherSolrDocument.getMinAmountSpend());
    document.addField("startDate", merchantVoucherSolrDocument.getStartDate());
    document.addField("endDate", merchantVoucherSolrDocument.getEndDate());
    document.addField("markForDelete", merchantVoucherSolrDocument.isMarkForDelete());
    document.addField("storeId", merchantVoucherSolrDocument.getStoreId());

    solr.add(document);
    solr.commit();
  }

  public void get(String query) throws IOException, SolrServerException {
    SolrDocument solrDocument = solr.getById("6c8bf2ca-9576-420b-abc8-c783d5513b36");
    System.out.println(solrDocument.getFieldNames());
    System.out.println(solrDocument.getFieldValue("voucherName"));
    System.out.println();
    System.out.println();

    SolrQuery solrQuery = new SolrQuery();
    solrQuery.setQuery(query);
    QueryResponse queryResponse = solr.query(solrQuery);
    SolrDocumentList solrDocuments = queryResponse.getResults();
    for (SolrDocument solrDocument1 : solrDocuments) {
      System.out.println(solrDocument1.getFieldValue("voucherName"));
      System.out.println(solrDocument1.getFieldValue("voucherCode"));
      System.out.println(solrDocument1.getFieldValue("merchantCode"));
      System.out.println(solrDocument1.getFieldValue("itemSku"));
    }
  }

  public void reindex(MerchantVoucherSolrDocument merchantVoucherSolrDocument) throws IOException, SolrServerException {
    SolrInputDocument solrInputDocument = new SolrInputDocument();
    solrInputDocument.setField("id", "56118edd-bb36-485e-a29b-5357ae9a3ea3");
    solrInputDocument.setField("merchantCode", "name of new merchant");
    solrInputDocument.setField("itemSku", "item item item item");

    UpdateResponse updateResponse = solr.add(solrInputDocument);
    solr.commit();
    System.out.println(updateResponse.getResponse());
  }

  @SneakyThrows
  public void delete() {
//    solr.deleteByQuery("*");
//    solr.commit();
  }

  @SneakyThrows
  public void query() {
    String x = null;
    QueryResponse queryResponse = solr.query(getQuery());
//    solr.commit();
    SolrDocumentList solrDocuments = queryResponse.getResults();
    for (SolrDocument solrDocument1 : solrDocuments) {
      x = String.valueOf(solrDocument1.getFieldValue("voucherName"));
      System.out.print(solrDocument1.getFieldValue("voucherName") + " ");
      System.out.print(solrDocument1.getFieldValue("voucherStatus") + " ");
      System.out.print(solrDocument1.getFieldValue("merchantCode") + " ");
      System.out.print(solrDocument1.getFieldValue("itemSku") + " ");
      System.out.println();
    }
    System.out.println("Hey i am x = " + x);
  }

  private SolrQuery getQuery() {
    SolrQuery solrQuery = new SolrQuery();
    solrQuery.setQuery("*" + ":" + "*");
    return solrQuery;
    // Done
  }
}