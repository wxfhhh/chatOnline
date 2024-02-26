package com.chatOnline.config;

import com.chatOnline.common.JacksonObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@Configuration
@Slf4j
public class webMvcConfig extends WebMvcConfigurationSupport {

    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "classpath:/static/", "classpath:/templates/" };

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
    }
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        log.info("开始进行静态资源映射");
//        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
//        registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");
//    }



    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new com.chatOnline.config.LoginInterceptor())
                .addPathPatterns("/index");
        super.addInterceptors(registry);
    }
    /**
     * 扩展mvc框架的消息转换器 不太懂
     * @param converters
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        //创建消息转换器对象
        MappingJackson2HttpMessageConverter httpMessageConverter=new MappingJackson2HttpMessageConverter();
        //设置对象转换器 将Java对象转成json对象
        httpMessageConverter.setObjectMapper(new JacksonObjectMapper());
        //将消息转换器追加到mvc框架的转换器中
        converters.add(0,httpMessageConverter);
    }

}
