package com.spring.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@Configuration
public class WebServlet extends AbstractAnnotationConfigDispatcherServletInitializer{

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] {
                SpringConfig.class
        };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };

    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{RootConfig.class};
    }

}