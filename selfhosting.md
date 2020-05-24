# 2009Scape selfhosting guide

## Requirements
You will need:
```
git
gradle
java 8
```

## Getting the code:
Git clone this repository, or download the latest source with the clone or download button

## Building the server:
### Option 1: Build script(Linux/Unix only.)
- In a terminal, navigate to the project root (for example, /home/user/2009scape)
- Use ./build.sh:
  - Management server: ./build.sh ms
  - Game server: ./build.sh server
  - Client: ./build.sh client
    - If you wish to distribute copies of the client, an output jar with the IP you set is located in Client/bin/2009scape
### Option 2: gradle by itself
- In a terminal, enter both the Management-Server and Server folders and run `gradle build`

#### Building the client:
- In a terminal, enter the Client folder and run `gradle build`
  - The build process should prompt you to enter a different IP, this is when you would set the IP you want the client to connect to.

## Installing the databases:

### Windows & Linux GUI:
- Download and install [xampp](https://www.apachefriends.org/download.html)
- Start the `Apache` and `MySQL` modules
- Navigate to http://localhost/phpmyadmin/
- Create 2 new databases named `server` and `global`
- Import `Server/server.sql` and `Server/global.sql` into their respective databases
  - _Refer [here](https://www.thecodedeveloper.com/import-large-sql-files-xampp/) for help importing the `.sql` files_

### Linux Command Line
- Get and install mariadb or some other sql compatible database from your repository
- Login and create global & server databases:
Create:
```
CREATE DATABASE server;
CREATE DATABASE global;
```
- Import global & server database contents from the files provided:
```
mysql -u root -p server < server.sql
mysql -u root -p global < global.sql
```

## Running the server/client
### Option 1: Using the run script (Linux/Unix only.)
- From the project root:
  - Step 1: Start up mysql or whichever database server you are using if it isn't already running.
  - Step 2: Start the management server ``./run.sh ms``
  - Step 3: Start the game server ``./run.sh server Server/worldprops/server1.properties``
  - The client can be ran either using `./run.sh client` or by running the .jar you generated earlier.
  
### Option 2: Gradle by itself
- In a terminal, navigate to the Management-Server folder and run `gradle run`
- Then navigate to the Server folder and run `gradle run`
- The client can also be run with `gradle run` or by using the jar generated earlier.
