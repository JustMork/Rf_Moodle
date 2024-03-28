

# Rendszerfejlesztés 34. Csapat

   -  [Csécs Márk Ádám](https://github.com/JustMork) (ZF9F3F)
   -  [Csővári Zalán]() (LM12LC)
   -  [Kiss Dávid]() (ULQ045)


## Rf Moodle Project

This project is a Moodle-like learning management system developed using Java Spring Boot for the backend and Angular for the frontend.

## Requirements

Before running the project, ensure that the following prerequisites are met:

- Git
- Java JDK (version 22 used in the project)
- Node.js (version 18.20 used in the project)
- XAMPP (for local database access, version 8.2.12 used)

## Installation Prerequisites

1. **Install Java JDK, Node.js, and XAMPP:**
   - Download and install Java JDK, Node.js, and XAMPP from their respective websites.
2. **Add JAVA_HOME Environment Variable:**
   - Open Control Panel -> System and Security -> System -> Advanced system settings -> Environment Variables.
   - Click New.
   - Set `JAVA_HOME` as Variable name and the Java JDK location (e.g., "C:\Program Files\Java\jdk-22") as the Variable value.

## Installation

1. **Clone the Git Repository:**
   - git clone https://github.com/JustMork/Rf_moodle.git
2. **Build Backend:**
    - cd Rf_moodle\Backend
    - mvnw clean install
3. **Install Frontend Dependencies:**
    - cd Rf_moodle\Frontend\Rf_MoodleKliens
    - npm install
    - npm install -g @angular/cli
4. **Start XAMPP:**
   - Start XAMPP and ensure Apache and MySQL servers are running.
   - Open phpMyAdmin.
5. **Database Setup:**
   - Create a new database named "moodle" with the collation "utf8_hungarian_ci".
   - Import the SQL files from the backend folder into the "moodle" database.

## How to Run

1. **Start Backend:**
    - cd Rf_moodle\Backend\target
    - java -jar rfmoodle-0.0.1-SNAPSHOT.jar
2. **Start Frontend:**
    - cd Rf_moodle\Frontend\Rf_MoodleKliens
    - ng serve
3. **Access the Website:**
   - Open your preferred web browser and navigate to localhost:4200.
