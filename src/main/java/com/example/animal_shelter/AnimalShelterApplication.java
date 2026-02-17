package com.example.animal_shelter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Itt kell rámenned a start gombra, hogy localhost-on tesztelt az weboldalt.
//Elindult az oldal, ha ezt látod a lenti terminálban: Started AnimalShelterApplication in 9.588 seconds (process running for 10.541)
//Ha nem indul el, akkor alul a terminálban keresd meg az ERROR-t.
//http://localhost:8080/add.html
@SpringBootApplication
public class AnimalShelterApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnimalShelterApplication.class, args);
	}

}
