package ru.amm.fileexplorer.server.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

@EnableWebMvc
@Configuration
@Import(SecurityConfig.class)
public class MainConfiguration implements WebMvcConfigurer {
    private final static Logger LOG = LoggerFactory.getLogger(MainConfiguration.class);

    @Bean
    public FreeMarkerViewResolver freemarkerViewResolver(@Value("${explorer.template.cacheEnabled}") boolean cacheEnabled) {
        LOG.debug("FTL caching enabled = {}", cacheEnabled);
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setContentType("text/html;charset=UTF-8");
        resolver.setCache(cacheEnabled);
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
    public FreeMarkerConfigurer freeMarkerConfigurer(@Value("${explorer.template.path}") String templatePath) {
        LOG.debug("FTL will be loaded from: {}", templatePath);
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setTemplateLoaderPath(templatePath);
        return freeMarkerConfigurer;
    }
}
