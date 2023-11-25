JAVAC = javac
JAVA = java

SRC_DIR = src
BIN_DIR = bin
LIB_DIR = lib/lib
JAVAFX_MODULES = javafx.base,javafx.controls,javafx.fxml,javafx.graphics

SOURCES = $(wildcard $(SRC_DIR)/**/*.java)
CLASSES = $(SOURCES:$(SRC_DIR)/%.java=$(BIN_DIR)/%.class)

CLASSPATH = $(LIB_DIR):$(JAVAFX_LIB)
MAIN_CLASS = main.app

compile: $(BIN_DIR) $(CLASSES)

$(BIN_DIR):
	mkdir -p $(BIN_DIR)

$(BIN_DIR)/%.class: $(SRC_DIR)/%.java
	$(JAVAC) -cp $(CLASSPATH) --module-path $(LIB_DIR) --add-modules $(JAVAFX_MODULES) -d $(BIN_DIR) $<

run: compile
	$(JAVA) -cp $(CLASSPATH):$(BIN_DIR) --module-path $(LIB_DIR) --add-modules $(JAVAFX_MODULES) $(MAIN_CLASS)

clean:
	rm -rf $(BIN_DIR)

.PHONY: compile run clean
