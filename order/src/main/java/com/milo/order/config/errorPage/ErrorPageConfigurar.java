package com.milo.order.config.errorPage;

/**
 * 描述：Error code 自动转发 方法三
 * 日期：2020-03-05
 */
//@Configuration
//public class ErrorPageConfigurar {
//
//  /**
//   * SpringBoot2.0以上版本WebServerFactoryCustomizer代替之前版本的EmbeddedWebServerFactoryCustomizerAutoConfiguration
//   */
//  @Bean
//  public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
////        //常规写法
////        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {
////            @Override
////            public void customize(ConfigurableWebServerFactory factory) {
////                ErrorPage errorPage404 = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
////                factory.addErrorPages(errorPage404);
////            }
////        };
//    //lambda写法
//    return (factory -> {
//      ErrorPage errorPage404 = new ErrorPage(HttpStatus.NOT_FOUND, "/404/3");
//      factory.addErrorPages(errorPage404);
//    });
//  }
//
//}