package com.synergisticit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootApp {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootApp.class, args);
	}

}

/*
End points of Actuator:
http://localhost:8080/actuator

http://localhost:8080/actuator/health
    
URL to get only the health of db:
http://localhost:8080/actuator/health/db

URL to get only the health of the disk space:
http://localhost:8080/actuator/health/diskSpace 

URL to get metrics:
http://localhost:8080/actuator/metrics

Information related to build:
http://localhost:8080/actuator/info
Include following executions tag on pom.xml, inside build tag.
<build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <executions>
                    <execution>
                        <goals>
                            <goal>build-info</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
*/
