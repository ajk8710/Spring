package com.synergisticit.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.synergisticit.domain.Employee;
import com.synergisticit.domain.User;
import com.synergisticit.repository.UserRepository;
import com.synergisticit.service.EmployeeService;

//Lowest is the value of Order, highest is the priority of execution.
@Component
public class CommandLineRunnerImpl3 implements CommandLineRunner, Ordered {
	@Autowired EmployeeService employeeService;

	@Override
	public void run(String... args) throws Exception {
		List<Employee> employees = employeeService.findAll();
		for(Employee e : employees)
		System.out.println("***"+ "name:"+e.getName() );

	}

	@Override
	public int getOrder() {
		return 100;
	}

}
