package com.bayrak.hrms;

import com.bayrak.hrms.config.AppConfig;
import com.bayrak.hrms.model.Candidate;
import com.bayrak.hrms.model.resume.Resume;
import com.bayrak.hrms.repository.CandidateDao;
import com.bayrak.hrms.repository.ResumeDao;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@RequiredArgsConstructor
public class HrmsApplication implements CommandLineRunner {

	final CandidateDao candidateDao;
	final ResumeDao resumeDao;


	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(HrmsApplication.class, args);
		AppConfig appConfig = run.getBean(AppConfig.class);
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();
	}

	@Override
	public void run(String... args ) throws Exception {

		candidateDao.save(Candidate.builder()
				.birthYear(1996)
				.firstName("test")
				.lastName("test")
				.identityNumber("465465454654")
				.email("test2@email.com")
				.password("654654654")
				.build());

		Candidate cdt = Candidate.builder()
				.identityNumber("4654654")
				.firstName("testname")
				.lastName("test")
				.birthYear(1996)
				.email("test@email.com")
				.password("654654654")
				.build();

		candidateDao.save(cdt);

		resumeDao.save(Resume.builder()
				.candidate(cdt)
				.coverLetter("coverletter")
				.build());
	}
}
