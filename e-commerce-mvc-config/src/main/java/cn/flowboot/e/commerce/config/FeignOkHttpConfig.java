package cn.flowboot.e.commerce.config;

import feign.Feign;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * <h1>OpenFeign 使用OKHttp配置类</h1>
 *
 * @version 1.0
 * @author: Vincent Vic
 * @since: 2022/03/05
 */
@Configuration
@ConditionalOnClass(Feign.class)
@AutoConfigureBefore(FeignAutoConfiguration.class)
public class FeignOkHttpConfig {

    /**
     * <h2> okHttpClient - 注入 OkHttp, 并自定义配置<h2>
     * version: 1.0 - 2022/3/5
     * @param
     * @return {@link OkHttpClient }
     */
    @Bean
    public okhttp3.OkHttpClient okHttpClient() {

        return new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)    // 设置连接超时
                .readTimeout(5, TimeUnit.SECONDS)   // 设置读超时
                .writeTimeout(5, TimeUnit.SECONDS)  // 设置写超时
                .retryOnConnectionFailure(true)     // 是否自动重连
                // 配置连接池中的最大空闲线程个数为 10, 并保持 5 分钟
                .connectionPool(new ConnectionPool(
                        10, 5L, TimeUnit.MINUTES))
                .build();
    }

}