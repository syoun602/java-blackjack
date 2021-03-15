package blackjack.domain.money;

import java.math.BigDecimal;

public class BettingMoney {
    private static final String NEGATIVE_MONEY_ERROR_MESSAGE = "베팅 금액은 0보다 커야합니다.";

    private final BigDecimal money;

    public BettingMoney(String money) {
        this(new BigDecimal(money));
    }

    private BettingMoney(BigDecimal money) {
        validateNegative(money);
        this.money = money;
    }

    private void validateNegative(BigDecimal money) {
        if (money.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(NEGATIVE_MONEY_ERROR_MESSAGE);
        }
    }

    public Profits multiply(BigDecimal rate) {
        return new Profits(money.multiply(rate));
    }
}