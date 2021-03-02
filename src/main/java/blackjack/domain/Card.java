package blackjack.domain;

public class Card {
    private final CardPattern pattern;
    private final CardNumber number;

    public Card(CardPattern pattern, CardNumber number) {
        this.pattern = pattern;
        this.number = number;
    }

    public String getPatternAndNumber() {
        return number.name() + pattern.name();
    }

    public int addPoint(int point) {
        return number.addNumber(point);
    }
}
