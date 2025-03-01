package by.strunin.myblog.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "by.strunin.myblog")
@PropertySource("classpath:application.properties") // Для считывания application.properties
public class WebConfiguration {}
