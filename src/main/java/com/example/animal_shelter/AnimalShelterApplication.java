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
	/*
	 * TODO: Nézd meg a másik három videót:
	 * https://www.youtube.com/watch?v=RK6aAjUMcl0&list=PLg7lel5LdVjyO7jk-
	 * 4biyr0fqPVygTLOk
	 * 
	 * ! A webes nézetben állítsd be a poco-meretet és úgy csekkold a weboldalt.
	 */
	// ! Tailwind rendes import állása (npm init -y stb tailwind.config.js stb)

	// & TODO: Legyen egy beléptető felület a dolgozóknak, ahol admin admin a jelszó
	// &
	// youtube.com/watch?v=X7pGCmVxx10&pp=ygUkSG93IHRvIGNyZWF0ZSBhIGxvZ2luIGluIHNwcmluZyBib290

	/*
	 * 
	 * TODO: Örökbe fogadás és törlés az adatbázisból. Legyen egy külön tábla az
	 * örökbe fogadott állatoknak.
	 * Legyen egy külön tábla a mennyországba távozott állatoknak
	 * Örökbeadás funkció gomb és hozzá az ORM.
	 */

	// ~ TODO: Kutya ás Macska osztály használata. Valamint az abstract osztály
	// visszaállítása.

}
