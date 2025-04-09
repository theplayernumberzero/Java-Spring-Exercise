package com.theplayernumberzero.aopdemo;

import com.theplayernumberzero.aopdemo.dao.AccountDao;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AopdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopdemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AccountDao accountDao){
		return runner -> {
			demoTheBeforeAdvice(accountDao);
		};
	}

	private void demoTheBeforeAdvice(AccountDao accountDao) {
		accountDao.addAccount();
	}

}
