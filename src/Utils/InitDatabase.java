package Utils;

abstract class InitDatabase {

    protected final String createTableUser = "CREATE TABLE USER(ID_user INT PRIMARY KEY , username VARCHAR(40) NOT NULL, password varchar(220) NOT NULL, UNIQUE(username) ); INSERT INTO USER(ID_user, username , password) VALUES(0,'yahya','yahyabgbg');";
    protected final String createTableProfile = "CREATE TABLE PROFILE(ID_Profile INT PRIMARY KEY, ID_user INT NOT NULL, firstname VARCHAR(30) NOT NULL , lastname VARCHAR(30) NOT NULL , age int NOT NULL , FOREIGN KEY(ID_user) REFERENCES USER(ID_user)); INSERT INTO PROFILE(firstname ,lastname ,age, ID_user) VALUES('yahya','bennis',21,0)";

    protected final String DeleteTables = "DROP TABLE IF EXISTS USER ;DROP TABLE IF EXISTS PROFILE ;";
}
