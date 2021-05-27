package info.team23h.acc.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFoxConfig  {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
													  .apis(RequestHandlerSelectors.any()) // 현재 RequestMapping으로
													  // 할당된 모든 URL 리스트를 추출
													  .paths(PathSelectors.ant("/api/**")) // 그중 /api/** 인 URL들만 필터링
													  .build()
													  .apiInfo(apiInfo())
													  .useDefaultResponseMessages(false);
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("이 문서는 FinalLap Api 문서 입니다.")
								   .description("Created by FinlLab").license("Apache License Version 2.0")
								   .licenseUrl("https://github.com/IBM-Bluemix/news-aggregator/blob/master/LICENSE").version("1.0").build();
	}
}
