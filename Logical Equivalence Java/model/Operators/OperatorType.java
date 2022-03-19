package model.Operators;

public enum OperatorType {
    NOT("¬"),
    AND("∧"),
    OR("∨"),
    IF("→"),
    IFF("↔"),
    LOGICALLY_EQUAL("≡"),
    PROPOSITION("qwertyuiopasdfghjklzxcvbnm");
    ;

    private final String text;

    OperatorType(final String text) {
        this.text = text;
    }
    @Override
    public String toString() {
        return text;
    }
}
