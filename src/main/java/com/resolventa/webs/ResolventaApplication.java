package com.resolventa.webs;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.resolventa.prosol.Problem;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ResolventaApplication {

	static void PrintTestData() {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		Problem problem = new Problem("natural_sequent_type", "[(p->q)&(p->r)] |- [p->(q&r)]");
		String res = gson.toJson(problem);
		System.out.println(res);
	}
	public static void main(String[] args) {
		//PrintTestData();
		SpringApplication.run(ResolventaApplication.class, args);
	}

}
