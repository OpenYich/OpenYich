package com.openyich.samples.swagger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api("Swagger Test")
public class SwaggerController {

  @GetMapping("/swagger")
  @ApiOperation(value = "Hello Swagger", notes = "Notes")
  public String helloSwagger(@RequestParam("name") String name) {
    return "Hello " + name + "!";
  }

}
