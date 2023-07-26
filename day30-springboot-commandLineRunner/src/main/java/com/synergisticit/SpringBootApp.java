package com.synergisticit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootApp {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootApp.class, args);
	}
	
//  Lombok installation:
//	Add into POM: Project Lombok » 1.18.28
//	After adding to POM, Install Lombok for IDE using cmd.
//	cd C:\Users\AdminUserName\.m2\repository\org\projectlombok\Lombok\1.18.28
//	dir (to list files)
//	Unjar jar file: java -jar Lombok-1.18.28.jar
//	Then Lombok Installer is launched – check IDEs - click Install/Update.
//	IDE – help – about – check if Lombok is installed.

}
