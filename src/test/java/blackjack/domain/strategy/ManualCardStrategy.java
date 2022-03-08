package blackjack.domain.strategy;

import blackjack.domain.Card;

import java.util.List;

public class ManualCardStrategy implements CardStrategy {

    private List<Card> cards;

    public void initCards(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public List<Card> generate() {
        return cards;
    }

}