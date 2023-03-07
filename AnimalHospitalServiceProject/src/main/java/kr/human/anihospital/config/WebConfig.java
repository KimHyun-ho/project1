package kr.human.anihospital.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	private String resourcePath = "classpath:static/pdf/";
	private String connectPath = "/pdf/**";

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(connectPath).addResourceLocations(resourcePath);
	}
	
	// CORS 설정
	private final long MAX_AGE_SECS = 3600;
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// 모든 경로에 대해
		registry.addMapping("/**")
		// Origin이 http:localhost:8080에 대해
				.allowedOrigins("http://localhost:8080")
		// GET, POST, PUT, PATCH, DELETE, OPTIONS 메서드를 허용한다.
				.allowedMethods("GET", "POST")
				.allowedHeaders("*")
				.allowCredentials(true)
				.maxAge(MAX_AGE_SECS);
	}
}
