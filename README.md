# scoreDEI

To run:

    - Have a postgreSQL database running
    - Run the following commands:
      - jar -xvf scoresDei-0.0.1-SNAPSHOT.war
      - cd WEB-INF
    - Configure WEB-INF/classes/application.properties file with the respective database credentials (spring.datasource.url, spring.datasource.username and spring.datasource.password)
    - Run the command:
      - java -classpath "lib/*:classes" com.scoresDei.ScoresDeiApplication
  

