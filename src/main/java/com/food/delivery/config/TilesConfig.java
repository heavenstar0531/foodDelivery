package com.food.delivery.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

/**
 * Apache Tiles framework.
 * It allows reusability of template layouts.
 */
@Configuration
public class TilesConfig {

    @Bean
       public UrlBasedViewResolver viewResolver() {
           UrlBasedViewResolver resolver = new UrlBasedViewResolver();
           resolver.setViewClass(TilesView.class);
           resolver.setOrder(-2);
           return resolver;
       }

       @Bean
       public TilesConfigurer tilesConfigurer() {
           TilesConfigurer configurer = new TilesConfigurer();
           configurer.setDefinitions(
                   "/WEB-INF/layouts/definitions/userTiles.xml",
                   "/WEB-INF/layouts/definitions/adminTiles.xml");
           configurer.setCheckRefresh(true);
           return configurer;
       }
}
