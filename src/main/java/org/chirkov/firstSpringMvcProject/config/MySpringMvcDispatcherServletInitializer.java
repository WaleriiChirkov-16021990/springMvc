package org.chirkov.firstSpringMvcProject.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
//import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

public class MySpringMvcDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() { //не используем пока
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {SpringConfig.class}; //даём понять где брать конфинурацию вместо applicationContextMVC.xml
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"}; //перенаправляем все запросы на данный "диспетчер сервлет"
    }
}
