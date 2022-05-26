package com.scoresDei;

import com.scoresDei.populate.PopulateDB;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.scoresDei.*")
@EntityScan("com.scoresDei.*")
public class ScoresDeiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScoresDeiApplication.class, args);
	}

}
