package com.example.solr.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @author rishi - created on 16/08/20
 **/
public class Test {

  @Autowired
  @Qualifier("fixedThreadPool")
  private ExecutorService executorService;

  public static void main(String[] args) throws InterruptedException {
    Test test = new Test();
    test.x(2, 3);

    Thread.sleep(10000);
  }

  public void x(int a, int b) {
    CompletableFuture.runAsync(() -> {
      System.out.println("not nice");
      int x = y();
      System.out.println(x);
      System.out.println("Nice");
    });
    System.out.println("Outside " + a * b);
  }

  public int y() {

    throw new RuntimeException("Hi there");

  }

}
