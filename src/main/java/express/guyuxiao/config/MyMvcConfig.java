package express.guyuxiao.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
       // registry.addViewController("/signUp").setViewName("signUp");
        registry.addViewController("/main").setViewName("main");

    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor()).excludePathPatterns("/",
                "/index",
                "/login/*",
                "/css/*",
                "/js/*",
                "/img/*",
                "/fonts/*",
                "/signUp",
                "/sign",
                "/kaptcha/*");
    }
}

