package com.shipserv.authservice;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Component;

import com.shipserv.authservice.model.Company;
import com.shipserv.authservice.model.Consumer;
import com.shipserv.authservice.repository.ConsumerRepository;

@SpringBootApplication
@EnableJpaAuditing
public class AuthServiceApplication {

	@Autowired
	private ConsumerRepository apiConsumerRepository;

	@Component
	class DataSetup implements ApplicationRunner {
		@Override
		public void run(ApplicationArguments args) throws Exception {
			Consumer consumer = Consumer.builder().username("test-username-1").clientId("test-client-id")
					.clientSecret("test-client-secret").consumerId("test-consumer-id").build();
			
			Set<Company> companies = new HashSet<>();
			consumer.setCompanies(companies);

			Company company1 = Company.builder().companyId("52323").companyType("SUPPLIER").consumer(consumer).build();
			Company company2 = Company.builder().companyId("100").companyType("BUYER").consumer(consumer).build();
			Company company3 = Company.builder().companyType("INTERNAL").consumer(consumer).build();

			companies.add(company1);
			companies.add(company2);
			companies.add(company3);

			apiConsumerRepository.save(consumer);
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(AuthServiceApplication.class, args);
	}
}
