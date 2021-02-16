package springConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.sql.DataSource;

// Конфигурационный Java-класс для Spring
@Configuration
@ComponentScan("controllers")    // пакет, где лежат контроллеры (Spring делает из них бины)
@ComponentScan("dao")            // dao-классы (из них тоже делаются бины)
@EnableWebMvc       // равноценна тегу <mvc:annotation-driven/>
public class SpringConfig implements WebMvcConfigurer {
    private final ApplicationContext applicationContext;

    @Autowired      // внедряем зависимость ApplicationContext
    public SpringConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    // бины, отвечающие за конфигурацию шаблонизатора thymeleaf
    @Bean   // будет использован в методе templateEngine()
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/views/");  // папка, где будут лежать представления
        templateResolver.setSuffix(".html");            // расширение представлений
        // следующая строка решает проблему с кодировкой текста, создаваемого шаблонизатором
        templateResolver.setCharacterEncoding("UTF-8");
        return templateResolver;
    }

    @Bean   // будет использован в методе configureViewResolvers
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    @Override   // метод для задания своего шаблонизатора
    public void configureViewResolvers(ViewResolverRegistry registry) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        resolver.setCharacterEncoding("UTF-8");   // попытка исправить кодировку
        registry.viewResolver(resolver);
    }

    // бины для template
    @Bean // источник данных, содержит данные для подключения к БД
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/first_db");
        dataSource.setUsername("postgres");
        dataSource.setPassword("Postgres123");

        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }
}
