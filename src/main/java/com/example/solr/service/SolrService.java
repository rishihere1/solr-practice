package com.example.solr.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.solr.document.MerchantVoucherSolrDocument;
import com.example.solr.repository.MerchantVoucherSolrRepositoryImpl;
import com.example.solr.util.SolrUtil;
import lombok.SneakyThrows;

/**
 * @author rishi - created on 15/08/20
 **/
@Service
public class SolrService {

  @Autowired
  private MerchantVoucherSolrRepositoryImpl merchantVoucherSolrRepository;

  public void addVoucherToSolr(MerchantVoucherSolrDocument merchantVoucherSolrDocument) {
    try {
      Date date = new Date();
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);
      calendar.add(Calendar.DATE, 5);
      date = calendar.getTime();
      merchantVoucherSolrDocument.setStartDate(new Date());
      merchantVoucherSolrDocument.setEndDate(date);
      merchantVoucherSolrRepository.addVoucherToSolr(merchantVoucherSolrDocument);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public void get(String query) {
    try {
      merchantVoucherSolrRepository.get(query);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  @SneakyThrows
  public void reindexing() {
    MerchantVoucherSolrDocument merchantVoucherSolrDocument = SolrUtil.getMerchantVoucherDocument();
    merchantVoucherSolrRepository.reindex(merchantVoucherSolrDocument);
  }

  public void delete() {
    merchantVoucherSolrRepository.delete();
  }

  public void query() {
    merchantVoucherSolrRepository.query();
  }

  @SneakyThrows
  public void addDataInBulk() {
    for (int i=1;i<=100;i++) {
      MerchantVoucherSolrDocument merchantVoucherSolrDocument = SolrUtil.getMerchantVoucherSolrDocumentInBulk(i);
      merchantVoucherSolrRepository.addVoucherToSolr(merchantVoucherSolrDocument);
    }
  }
}
