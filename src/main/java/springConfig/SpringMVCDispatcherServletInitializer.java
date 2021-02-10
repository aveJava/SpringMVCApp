package springConfig;

import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.*;

public class SpringMVCDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override   // данный метод использовать не будем, поэтому return null
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override   // должен возвращать массив конфигурационных классов
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {SpringConfig.class};
    }

    @Override   // куда посылать запросы от пользователя. / - значит посылать на DispatcherServlet
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }

    @Override   // метод возвращающий все используемые фильтры (здесь можно создать свои фильтры)
    protected Filter[] getServletFilters() {
        // characterEncodingFilter - решает проблему с кодировкой текста созданного без использования шаблонизатора
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return new Filter[] { characterEncodingFilter};
    }

//    @Override (тоже про кодировку)
//    public void onStartup(ServletContext servletContext) throws ServletException {
//        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
//        context.register(SpringConfig.class);       //, WebConfig.class
//        context.setServletContext(servletContext);
//
//        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(context));
//        dispatcher.setLoadOnStartup(1);
//        dispatcher.addMapping("/");
//
//        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
//        characterEncodingFilter.setEncoding("UTF-8");
//        characterEncodingFilter.setForceEncoding(true);
//
//        FilterRegistration.Dynamic filterRegistration = servletContext
//                .addFilter("characterEncodingFilter", characterEncodingFilter);
//        filterRegistration.addMappingForUrlPatterns(null, false, "/*");
//    }
}
