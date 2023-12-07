
compile:
	javac -d bin -cp "lib/*:lib/lib/*:src" $(find src -name "*.java")

run:
	java --module-path "lib/javafx.base.jar:lib/javafx.controls.jar:lib/javafx.fxml.jar:lib/javafx.graphics.jar:lib/javafx.media.jar:lib/javafx.swing.jar:lib/javafx.web.jar:lib/lib" --add-modules javafx.controls,javafx.fxml -cp "bin:lib/*:lib/lib/*" Client.Client	

init-database:
	compile : 
		javac -d bin -cp "lib/*:lib/lib/*:src" src/Utils/Database.java
	run : 
		java -cp "bin:lib/*:lib/lib/*:src" Utils.Database
		java -cp "bin:lib/*:lib/lib/*:src" -Dorg.slf4j.simpleLogger.defaultLogLevel=debug Utils.Database

clean:
	rm -rf bin/*

