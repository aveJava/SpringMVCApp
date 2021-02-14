package springConfig;

import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
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

//    @Override   // метод возвращающий все используемые фильтры (здесь можно создать свои фильтры)
//    protected Filter[] getServletFilters() {
//        // characterEncodingFilter - решает проблему с кодировкой текста созданного без использования шаблонизатора
//        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
//        characterEncodingFilter.setEncoding("UTF-8");
//        characterEncodingFilter.setForceEncoding(true);
//        return new Filter[] { characterEncodingFilter};
//    }

    // данный метод запускается при запуске Spring-приложения (то, что нужно сделать при запуске)
    @Override
    public void onStartup(ServletContext aServletContext) throws ServletException {
        super.onStartup(aServletContext);
        registerHiddenFieldFilter(aServletContext);         // регистрируем фильтр скрытых полей
        registerCharacterEncodingFilter(aServletContext);   // регистрируем фильтр кодировки
    }

    // для чтения скрытого поля _method и поддержки PATCH, DELETE и прочих типов запросов
    private void registerHiddenFieldFilter(ServletContext aContext) {
        aContext.addFilter("hiddenHttpMethodFilter",
                new HiddenHttpMethodFilter()).addMappingForUrlPatterns(null ,true, "/*");
    }

    // characterEncodingFilter - решает проблему с кодировкой текста созданного без использования шаблонизатора
    private void registerCharacterEncodingFilter(ServletContext aContext) {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);

        FilterRegistration.Dynamic filterRegistration = aContext
                .addFilter("characterEncodingFilter", characterEncodingFilter);
        filterRegistration.addMappingForUrlPatterns(null, false, "/*");
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
