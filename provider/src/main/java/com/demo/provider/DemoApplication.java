package com.demo.provider;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Connector;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServer;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@MapperScan("com.demo.provider.mapper")
@ComponentScan({"com.demo.provider.*","com.demo.redisson.*"})
@SpringBootApplication
@EnableDiscoveryClient
//开启自定义定时任务
@EnableScheduling
//启用@Retryable
@EnableRetry
@Slf4j
public class DemoApplication {

    public static void main(String[] args) {
//        SpringApplication.run(DemoApplication.class, args);
        SpringApplication springApplication = new SpringApplication(DemoApplication.class);
        // 禁用命令行参数
        springApplication.setAddCommandLineProperties(false);
        springApplication.run(args);

    }


    // -------------------------------------------------------------------------------------------------

    /**
     * 用于接受 shutdown 事件
     */
    @Bean
    public GracefulShutdown gracefulShutdown() {
        return new GracefulShutdown();
    }

    /**
     * 配置tomcat
     *
     * @return
     */
    @Bean
    public ServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addConnectorCustomizers(gracefulShutdown());
        return tomcat;
    }

    /**
     * 优雅关闭 Spring Boot。容器必须是 tomcat
     */
    private class GracefulShutdown implements TomcatConnectorCustomizer, ApplicationListener<ContextClosedEvent> {
        private volatile Connector connector;
        private final int waitTime = 10;

        @Override
        public void customize(Connector connector) {
            this.connector = connector;
        }

        @Override
        public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
            this.connector.pause();
            Executor executor = this.connector.getProtocolHandler().getExecutor();
            if (executor instanceof ThreadPoolExecutor) {
                try {
                    ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executor;
                    threadPoolExecutor.shutdown();
                    if (!threadPoolExecutor.awaitTermination(waitTime, TimeUnit.SECONDS)) {
                        log.warn("Tomcat 进程在" + waitTime + " 秒内无法结束，尝试强制结束");
                    }
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }


    // -------------------------------------------------------------------------------------------------


    // -------------------------------------------------------------------------------------------------


    /**
     * 优雅关闭 Spring Boot undertow
     */
//    @Component
//    public class GracefulShutdownUndertow implements ApplicationListener<ContextClosedEvent> {
//
//        @Autowired
//        private GracefulShutdownWrapper gracefulShutdownWrapper;
//
//        @Autowired
//        private ServletWebServerApplicationContext context;
//
//        @Override
//        public void onApplicationEvent(ContextClosedEvent contextClosedEvent){
//            gracefulShutdownWrapper.getGracefulShutdownHandler().shutdown();
//            try {
//                UndertowServletWebServer webServer = (UndertowServletWebServer)context.getWebServer();
//                Field field = webServer.getClass().getDeclaredField("undertow");
//                field.setAccessible(true);
//                Undertow undertow = (Undertow) field.get(webServer);
//                List<Undertow.ListenerInfo> listenerInfo = undertow.getListenerInfo();
//                Undertow.ListenerInfo listener = listenerInfo.get(0);
//                ConnectorStatistics connectorStatistics = listener.getConnectorStatistics();
//                while (connectorStatistics.getActiveConnections() > 0){}
//            }catch (Exception e){
//                // Application Shutdown
//            }
//        }
//    }

    // -------------------------------------------------------------------------------------------------

}
