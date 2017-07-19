package com.food.delivery.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.Locale;

/**
 * Created by mz on 14/07/17.
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.food.delivery")
public class WebAppConfig extends WebMvcConfigurerAdapter {

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * Sets the path where the views file are located(prefix) and its extensions(suffix).
     */
    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    /**
     * Sets the name of the properties file used to store messages.
     */
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource resource = new ResourceBundleMessageSource();
        resource.setBasename("messages");
        return resource;
    }

    /**
     * Sets the default language for the application.
     */
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver resolver = new SessionLocaleResolver();
        resolver.setDefaultLocale(new Locale("en"));
        return resolver;
    }

    /**
     * Adds interceptors to the application, which allows process before rendering the page.
     * So far is settled only the parameter name for language translation handling.
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Language internationalization config
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("language");
        registry.addInterceptor(localeChangeInterceptor);
    }

    /**
     * Tells Spring where the image files are located in our project,
     * so that Spring can serve those files upon request.
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**").addResourceLocations("/resources/images/");
    }

    /**
     * Determines whether the given request contains multipart content and
     * parses the given HTTP request into multipart files and parameters.
     */
    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("utf-8");
        return resolver;
    }
}
