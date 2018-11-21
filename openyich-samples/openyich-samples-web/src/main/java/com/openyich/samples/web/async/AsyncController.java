package com.openyich.samples.web.async;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openyich.framework.boot.OpenYich;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api")
@Api(tags = "Async")
public class AsyncController {

  private static final Logger log = LoggerFactory.getLogger(AsyncController.class);

  @Autowired
  private OpenYich openYich;

  /**
   * 带注解的异步线程测试
   * 
   * @return
   * @throws InterruptedException
   */
  @Async
  @GetMapping("async/test1")
  public String asyncTest1() throws InterruptedException {
    Thread.sleep(1000L);
    log.info(UUID.randomUUID().toString());
    return "Hello async";
  }

  /**
   * 带注解的异步线程测试（异常场景）
   * 
   * @return
   * @throws InterruptedException
   */
  @Async
  @GetMapping("async/test1/error")
  public String asyncTest1Error() throws InterruptedException {
    Thread.sleep(1000L);
    log.info(UUID.randomUUID().toString());
    throw new RuntimeException();
  }

  /**
   * 自定义工具类的异步线程测试
   */
  @GetMapping("async/test2")
  public String asyncTest2() {
    openYich.execute(() -> {
      try {
        Thread.sleep(1000L);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      log.info(UUID.randomUUID().toString());
    });
    return "Hello async";
  }

  /**
   * 自定义工具类的异步线程测试（异常场景）
   */
  @GetMapping("async/test2/error")
  public String asyncTest3() {
    openYich.execute(() -> {
      try {
        Thread.sleep(1000L);
        throw new RuntimeException();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      log.info(UUID.randomUUID().toString());
    });
    return "Hello async";
  }

}
