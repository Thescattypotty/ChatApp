# Compile the project
compile-project:
	@find src -name "*.java" > sources.txt
	@javac -d bin -cp "lib/*:lib/lib/*:src" @sources.txt
	@rm sources.txt

# Run the project
run-project:
	java --module-path "lib/javafx.base.jar:lib/javafx.controls.jar:lib/javafx.fxml.jar:lib/javafx.graphics.jar:lib/javafx.media.jar:lib/javafx.swing.jar:lib/javafx.web.jar:lib/lib" --add-modules javafx.controls,javafx.fxml -cp "bin:lib/*:lib/lib/*" Client.Client

# Compile and run the project
start-project: compile-project run-project

# Compile the database initialization
init-database-compile:
	javac -d bin -cp "lib/*:lib/lib/*:src" src/Utils/Database.java


# Run the database initialization
init-database-start:
	java -cp "bin:lib/*:lib/lib/*:src" Utils.Database $(filter-out $@,$(MAKECMDGOALS))

# Clean .class files
clean:
	rm -rf bin/*


# Command aliases for convenience
compile: compile-project
run: run-project
start: start-project
init-database: init-database-start
