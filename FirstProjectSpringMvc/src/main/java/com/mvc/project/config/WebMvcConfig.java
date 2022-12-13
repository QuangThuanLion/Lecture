package com.mvc.project.config;

import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    // Cấu hình để sử dụng các file nguồn tĩnh (html, image, ..)

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /*registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(31556926);*/
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/")
                .setCachePeriod(31556926);
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorConfig interceptorConfig = new InterceptorConfig();
        registry.addInterceptor(interceptorConfig).addPathPatterns("/*");

        LocaleChangeInterceptor localChange = new LocaleChangeInterceptor();
        localChange.setParamName("language");
        registry.addInterceptor(localChange).addPathPatterns("/*");
        super.addInterceptors(registry);
    }
    @Bean(name = "messageSource")
    public MessageSource getMessageSource() {
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        source.setBasename("classpath:static/i18n/messages/messages");
        source.setDefaultEncoding("UTF-8");
        return source;
    }

    @Bean(name = "localeResolver")
    public LocaleResolver getLocaleResolver(){
        /*SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();*/
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setDefaultLocale(Locale.ENGLISH);
        cookieLocaleResolver.setCookieMaxAge(60*60*24*2);
        cookieLocaleResolver.setCookiePath("/");

        return cookieLocaleResolver;
    }

    @Bean(name = "multipartResolver")
    public MultipartResolver multipartResolver() {
        //-1 nghia la khong gioi han dung luong upload filesize
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setMaxUploadSize(-1);
        return commonsMultipartResolver;
    }
}
