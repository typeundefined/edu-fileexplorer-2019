package ru.amm.fileexplorer.server.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;
import ru.amm.fileexplorer.server.entity.FileMatcher;
import ru.amm.fileexplorer.server.entity.NameMatcher;

@EnableWebMvc
@Configuration
public class MainConfiguration implements WebMvcConfigurer {
    private final static Logger LOG = LoggerFactory.getLogger(MainConfiguration.class);
    @Bean
    public FreeMarkerViewResolver freemarkerViewResolver() {
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setContentType("text/html;charset=UTF-8");
        resolver.setCache(true);
        resolver.setPrefix("");
        resolver.setSuffix(".ftl");
        return resolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/scripts/**")
                .addResourceLocations("classpath:/static/scripts/");
        registry.addResourceHandler("/css/**")
                .addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("/img/**")
                .addResourceLocations("classpath:/static/img/");
    }

    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer(@Value("${my.name}") String myName) {
        LOG.info("My name: {}", myName);
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setTemplateLoaderPath("classpath:/templates/");
        return freeMarkerConfigurer;
    }

}
