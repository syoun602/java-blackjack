package blackjack.domain;

public enum Result {
    BLACKJACK("블랙잭"),
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String value;

    Result(final String value) {
        this.value = value;
    }

    public static Result compareScore(final int score, final int opponentScore) {
        if (score == opponentScore) {
            return Result.DRAW;
        }
        if (score > opponentScore) {
            return Result.WIN;
        }
        return Result.LOSE;
    }

    public double calculateRate(final double money) {
        if (this == BLACKJACK) {
            return money * 1.5;
        }
        if (this == WIN) {
            return money;
        }
        if (this == DRAW) {
            return 0;
        }
        return -money;
    }

    public String getValue() {
        return value;
    }
}