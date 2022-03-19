package model.Operators;

public enum OperatorType {
    NOT("¬"),
    AND("∧"),
    OR("∨"),
    IF("→"),
    IFF("↔"),
    PROPOSITION("abcdefghijklmnopqrstuvwxyz"),
    LOGICALLY_EQUAL("≡");
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
