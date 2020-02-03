package xyz.chph.toy.validation;

public enum ARGUMENT_ERRORS {
    NONE(""),
    NO_FILE("Specify files for compilation"),
    BAD_FILE_EXTENSION("ToyLang source file has to end with .toy extension");

    private String message;

    ARGUMENT_ERRORS(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
