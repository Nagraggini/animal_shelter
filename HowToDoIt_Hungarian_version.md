# Spring Boot

[Ez alapján csináltam.](https://www.youtube.com/watch?v=RK6aAjUMcl0&list=PLg7lel5LdVjyO7jk-4biyr0fqPVygTLOk)

[A fenti youtube videóhoz tartozó repo.](https://github.com/bobbychansfu/springboot-render/tree/7d38d0343465661adceb6372591a8353717dfd5e)

További link:
[Spring MVC Beginner](https://www.youtube.com/watch?v=VqptK6_icjk&list=PL82C6-O4XrHejlASdecIsroNEbZFYo_X1)

[Fejlesztői környezet és kiegészítők letöltése](https://code.visualstudio.com/docs/java/java-spring-boot)

A vs code extension részen ezeket töltsd le:

- Java Development Kit (JDK)
- Extension Pack for Java
- Spring Boot Extension Pack
- Spring Boot Tools
- Spring Initializr

# Új projekt létrehozása

Előre konfigurált Spring Boot projektet lehet generáltatni, hogy ne kelljen kézzel megírni az összes kezdő fájlt és beállítást: [Spring Initializr](https://start.spring.io/)

Project: Maven
Language: Java
Spring Boot: 4.0.2
Artifact és Name: projektneve, amit az elején megadtál.
Packaging: Jar (Ugyanaz, amit az elején megadtál.)
Conf: Properties
Java: 17 (Ugyanaz, amit az elején megadtál.)

Dependenciák:
Spring Boot DevTools
Spring Web
Spring Session for JDBC
Thymeleaf
Spring Data JPA
Spring Data JDBC
H2 Database
PostgreSQL Driver

# Projekt feltöltése githubra és render.com-ra

https://github.com/-ra regisztrálj be.

Github Desktop-t töltsd le. Utána File -> Add local repository-> keresd meg a mappát, ahova az új projektet hoztad létre. -> Add repository -> Create a repoditory -> Töltsd ki az űrlapot. -> Create repository

render.com regisztrálj -> Kösd össze a github fiókoddal.

New -> Web Service -> Válaszd ki a listából a progjekt nevét (animal_shelter)
Language: Docker
-> Deploy web service Kb 15 percig eltart a deploy.

## SSH key

Ez alapján hozd létre: https://github.com/settings/ssh/new

# Projekt futtatása

Ha elindítod a java fájlt (AnimalShelterApplication.java), akkor a böngészőbe írd be ezt: http://localhost:8080/actuator

A dependenciák közt fel vettek az actuatort, ezzel le tudod csekkolni magad.
Az actuator információt ad az alkalmazás állapotáról és működéséről.

Alapból csak a /actuator endpoint lesz elérhető.

Ha több információt akarsz, a application.properties-ben beállíthatod, pl.:
management.endpoints.web.exposure.include=\*

A static mappába hozz létre egy index.html fájlt.

A static mappában lévő html fájlt, itt meg tudod nézni:
http://localhost:8080/index.html

showAll.html fájlt ide hozd létre: animal_shelter/src/main/resources/templates/animals
http://localhost:8080/animals/view

# BL Business Logic - Java logika

Egy régebbi konzolos java projekt fájljait másold be is:
GitHub/animal_shelter/src/main/java/com/example/animal_shelter/models

A Spring Boot architektúra alapja, hogy a MVC mintát használja:

Model – View – Controller

## Controllers mappa

A Controller réteg fogadja a HTTP kéréseket.

Például:

GET /users
POST /addUser

A controller:

- Fogadja a requestet.
- Meghívja a service-t / repository-t.
- Visszaad egy view-t, jelen esetben ez lesz, majd /animals/view. [View (Thymeleaf) = megjelenítés]

## Models mappa

A Model egy Java osztály, ami:

- Az adatbázis egy tábláját reprezentálja.
- Az osztály példányai egy-egy rekordnak felelnek meg.
- JPA segítségével automatikusan össze van kötve az adatbázissal.

osztály = tábla
objektum = sor
mező = oszlop

Az annotáció (@) jelzi a Spring/JPA számára, hogy ez egy adatbázis entitás.

```bash
@Entity
@Table(name = "animals") //Megmondja, hogy melyik adatbázis táblához tartozik.
public class Animal {

  // Ezek az elsődleges kulcs létrehozásához kellenek. Az animals adattáblában van
	// egy uid oszlopunk.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Lefogja generálni az értékét.
	private int uid;

//Utána jönnek a konstruktorok, getterek/setterek és metódusok.

}
```

# Dockerfile

Az alkalmazás győkér könyvtárába hozd létre egyből a Dockerfile, ne adj neki kiterjesztést, txt-t sem.

Ez a Dockerfile a Spring Boot alkalmazás konténerizálására szolgál.

A célja, hogy az alkalmazásodat lefordítsa és futtassa egy Docker konténerben, így bárhol ugyanúgy működjön (Render, saját gép, stb.).

Ez az egész Dockerfile arra kell, hogy:

- lefordítsa a Spring Boot projektet
- JAR fájlt készítsen
- elindítsa egy konténerben

# BootStrap stílus használata

[BootStrap](https://getbootstrap.com/docs/5.3/layout/grid/)

Használhatod a CDN-t a HTML-ben:

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

# CI/CD (Continuous Integration / Continuous Deployment) folyamat:

Új build indítása – Ez lehet manuális push a GitHub/GitLab branchre, vagy egy automatikus trigger.

Dockerfile feldolgozása – A rendszer végigmegy a Dockerfile-on: letölti a base image-et, telepíti a függőségeket, lefordítja a projektet (pl. mvn package), és létrehozza a végső Docker image-et.

Image létrehozása – Ez a “build” lépés, ahol a kész alkalmazás becsomagolódik egy konténerbe.

Image deployolása – Render.com feltölti az image-et a saját konténer registry-jébe és elindítja az új konténert.

Új konténer futtatása – Az új image futtatása a szerveren, a régi konténer lecserélődik (rolling update).

A folyamat 10 perc körüli időt vesz igénybe, mert a Maven build és a Docker image létrehozása időigényes lehet, főleg ha a projekt nagy vagy sok függőséget használ (Ez 8 db-ot használ).

# Debug

[A videó végén szerepel, hogyan kell debuggolni.](https://www.youtube.com/watch?v=p3AIecyvok4&list=PLg7lel5LdVjyO7jk-4biyr0fqPVygTLOk&index=7)

1. Telepítsd a Docker-t.

2. Projekt buildelése Dockerrel:
   Terminálba írd be: sudo docker build -t renderapp .

- A . a build context, azaz a Docker a projekt gyökérkönyvtárából veszi a Dockerfile-t és a forrásfájlokat.
- A parancs létrehoz egy Docker image-et, ami tartalmazza a Maven build-et és az alkalmazást.

3. Mi történik a háttérben:

- A Docker először letölti a base image-et (pl. Maven + JDK).
- Lefuttatja a mvn clean package parancsot, ami:
  Kitisztítja a korábbi build fájlokat
  Létrehozza a target mappát
  Becsomagolja az alkalmazást futtatható .jar fájlba
- A .jar fájl bekerül a Docker image-be, így az image futásra kész lesz.

Felhasználót hozzáadni a docker csoporthoz (jobb hosszú távon):
Terminálba:
sudo usermod -aG docker $USER
newgrp docker

Ez a parancs elindít egy Docker konténert az általad készített renderapp image-ből, és biztosítja, hogy a konténeren belüli 8080-as portot elérd a saját gépedről a localhost:8080 címen:
docker run -p 8080:8080 renderapp

# Templating with Spring and Thymeleaf

Spring és Thymeleaf használatakor a templatingre azért van szükség, hogy dinamikus funkciókat és adatokat jeleníthessünk meg az oldalon.

A templates mappában találhatók a view-k, vagyis a kinézetek.

A models mappában lévő java fájlokkal kezeljük a felhasználókat. (vagy objektumokat, szolgáltatásokat)
A controllers figyeli a szervert, és lehetővé teszi, hogy információkat kapjunk a felhasználókról.

UserController.java vizuális ábrán:  
[Felhasználó / Kliens]  
|  
| GET http://localhost:8080/users/view  
v

---

| UsersController                              |
| -------------------------------------------- |
| @GetMapping("/users/view") --> getAllUsers() |

---

          |
          | Visszatérési érték: "users/showAll"
          v

---

| Thymeleaf Template |
| templates/users/showAll.html |

---

          |
          v

[Böngésző megjeleníti a felhasználónak]

A templates mappában lévő html fájlok rejtve vannak a felhasználó elől, nem úgymint a static mappában lévők.

[Thymeleaf](https://www.thymeleaf.org/doc/tutorials/3.1/usingthymeleaf.html)

# Portok használata

Ezzel kilistázod, hogy melyik app használja a portot.

Terminálban:
sudo lsof -i :8080

A 58608-at írd át arra, amit fentebb kiír.
sudo kill 58608

# Adatbázisok

Az adatbázisokat alapvetően két fő kategóriába soroljuk:

- strukturált adatbázisok
- nem strukturált adatbázisok (A kezdők struktúráltat használnak, lekérdezéshez pedig sql-t.)

Postgresql-t [innen](https://www.postgresql.org/download/) tudod letölteni. Verzió: 16.11 Egyezzen lentebb létrehozott render.com-os adatbázissal.

A PostgreSQL működhet:

- szerverként (adatbázis szerver)
- kliensként (adatbázis kezelő eszköz)

Ebben az esetben neked csak a kliensre lesz szükséged, mert az adatbázis szerver a render.com platformon fog futni, és ahhoz távolról fogsz csatlakozni.

### Windows-on:

Környezeti változó beállítása: PowerShell:setx PATH "$env:PATH;C:\Program Files\PostgreSQL\16\bin" 

Lecsekkoljuk a verziót -> Cmd:cd "C:\Program Files\PostgreSQL\16\bin"psql --version Terminálban.

### Linux-on:

Terminálban:
sudo apt update
sudo apt install postgresql

[Postgesql hivatalos honlapja](https://www.postgresql.org/download/linux/ubuntu/)

## Adatbázis létrehozása a Render.com-on

A render.com-on hozz létre egy Postgres-t. A név legyen database. A verzió 16-os, a lényeg hogy egyezzen a gépre feltepített verzióval. Region: EU Instance Type: Free -> Create Database

Miután elkészült szükséged lesz az External Database URL-re, Username, Database, Password-re.

Terminálban, csatlakoztasd Postgres-t a render.com-os adatbázissal:
psql -h "@-utáni résztől....frankfurt-postgres.render.com-ig" -U "Username" -d "Database"
Entert nyomj.
pl.: psql -h dpg-d69k87buibrs739i5fu0-a.frankfurt-postgres.render.com -U database_olpd_user -d database_olpd
A jelszónak az oldalon lévő password-t másold be. Nem fogja mutatni. Majd entert nyomj.

Adatbázis létrehozása a terminálban:
CREATE TABLE animals (uid SERIAL, name VARCHAR(255), weight INTEGER);

## \dt-vel kilistázzuk a kettő táblát:

```bash
               List of relations

Schema | Name | Type | Owner
--------+----------+-------+--------------------
public | animals | table | database_olpd_user

```

## \d animals-vel a tábla struktúráját láthatod:

```bash
                                    Table "public.animals"
 Column |          Type          | Collation | Nullable |               Default
--------+------------------------+-----------+----------+--------------------------------------
 uid    | integer                |           | not null | nextval('animals_uid_seq'::regclass)
 name   | character varying(255) |           |          |
 weight | integer                |           |          |

```

A "q"-val tudsz kilépni belőle.

Így tudsz hozzáadni plussz sort (objektum):
INSERT INTO animals (name,weight) VALUES ('Cirma',15);

Kilistázás:
SELECT \* FROM animals;

A vs code-ban a pom.xml-hez adjuk hozzá a postgresql drivert. Jobb klikk a pom.xml-en -> Add Starts.. -> Postgresql Driver -> Enter -> Proceed.

Az application.properties fájlban rendeljük össze a weboldalt a render.com-os adatbázissal.

## Gyorsabb módszer, hogy módosítsd az adatbázist:

Postgresql-t [innen](https://www.postgresql.org/download/) tudod letölteni. Verzió: 16.11 Egyezzen fentebb létrehozott render.com-os adatbázissal. A telepítésközben a render.com-ról kell ki másolni az adatbázis jelszót, meg a többit. Az adatbázist be kell állítani a fentebb leírt módon.

A terminálba másold be a render.com-ról a PSQL Command-ot.
PGPASSWORD=sekoojWQ5YUGrgC3080avcnkVvgY4LSQ psql -h dpg-d69k87buibrs739i5fu0-a.frankfurt-postgres.render.com -U database_olpd_user database_olpd

Minden adat törlése:
DELETE FROM animals;

Adatmódosítás:
UPDATE animals
SET name = 'Morzsi'
WHERE uid =44;

A táblát így tudod csekkolni a terminálban:
\d animals
q-val tudsz kilépni.

# Teljes Flow

Browser  
 │  
 │ POST /users/add  
 │ name=Cirmi  
 │ password=1234  
 │ size=42  
 ▼  
Controller  
 │  
 │ new Animal(...)  
 ▼  
Repository  
 │  
 ▼  
Database  
 │  
 ▼  
HTML response: showAll.html
