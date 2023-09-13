package com.synergisticit.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.synergisticit.domain.Employee;
import com.synergisticit.domain.User;
import com.synergisticit.repository.UserRepository;
import com.synergisticit.service.EmployeeService;

@Order(value=3)
@Component
public class CommandLineRunnerImpl0 implements CommandLineRunner {
	
	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("87*10: "+ (87*10));

	}

}
