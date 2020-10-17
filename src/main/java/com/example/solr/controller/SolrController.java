package com.example.solr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.solr.document.MerchantVoucherSolrDocument;
import com.example.solr.service.SolrService;

/**
 * @author rishi - created on 15/08/20
 **/
@RestController
@RequestMapping("/solr")
public class SolrController {

  @Autowired
  private SolrService solrService;

  @PostMapping("/addVoucherToSolr")
  public String addVoucherToSolr(@RequestBody MerchantVoucherSolrDocument merchantVoucherSolrDocument) {
    solrService.addVoucherToSolr(merchantVoucherSolrDocument);
    return "Api executed";
  }

  @GetMapping("/get")
  public String get(@RequestParam String query) {
    System.out.println(query);
    solrService.get(query);
    return "Done";
    // http://localhost:8080/solr/get?query=voucherName:"New Voucher"
  }

  @PostMapping("/reindexing")
  public String reindexing() {
    solrService.reindexing();
    return "Done";
  }

  @PutMapping("/deleteAllDocuments")
  public String deleteAllDocuments() {
    solrService.delete();
    return "Done";
  }

  @GetMapping("/gett")
  public String get() {
    solrService.query();
    return "DONE";
  }

}
