package blackjack.domain;

import blackjack.domain.strategy.ManualCardStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ParticipantTest {

    private final ManualCardStrategy manualCardStrategy = new ManualCardStrategy();

    @ParameterizedTest
    @MethodSource("provideForDrawCardTest")
    @DisplayName("참여자는 카드를 뽑을 수 있어야 한다.")
    void drawCardTest(final List<Card> expectedCards) {
        manualCardStrategy.initCards(expectedCards);
        final Deck deck = Deck.generate(manualCardStrategy);
        final Participant participant = new Participant();

        for (int i = 0; i < expectedCards.size(); i++) {
            participant.drawCard(deck);
        }

        final List<Card> actualCards = participant.getCards();
        assertThat(actualCards).isEqualTo(expectedCards);
    }

    private static Stream<Arguments> provideForDrawCardTest() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Card(CardPattern.DIAMOND, CardNumber.ACE),
                                new Card(CardPattern.DIAMOND, CardNumber.EIGHT)
                        )
                ),
                Arguments.of(
                        List.of(
                                new Card(CardPattern.SPADE, CardNumber.ACE),
                                new Card(CardPattern.HEART, CardNumber.EIGHT),
                                new Card(CardPattern.DIAMOND, CardNumber.EIGHT)
                        )
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideForCannotContinueDrawCardTest")
    @DisplayName("카드의 합계가 21 초과인지 확인할 수 있어야 한다.")
    void cannotContinueDrawTest(final List<Card> expectedCards) {
        manualCardStrategy.initCards(expectedCards);
        final Deck deck = Deck.generate(manualCardStrategy);
        final Participant participant = new Participant();

        for (int i = 0; i < expectedCards.size(); i++) {
            participant.drawCard(deck);
        }

        assertThat(participant.isBurst()).isTrue();
    }

    private static Stream<Arguments> provideForCannotContinueDrawCardTest() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Card(CardPattern.DIAMOND, CardNumber.EIGHT),
                                new Card(CardPattern.DIAMOND, CardNumber.JACK),
                                new Card(CardPattern.DIAMOND, CardNumber.QUEEN),
                                new Card(CardPattern.DIAMOND, CardNumber.KING)
                        )
                ),
                Arguments.of(
                        List.of(
                                new Card(CardPattern.SPADE, CardNumber.ACE),
                                new Card(CardPattern.HEART, CardNumber.TEN),
                                new Card(CardPattern.DIAMOND, CardNumber.JACK),
                                new Card(CardPattern.DIAMOND, CardNumber.EIGHT)
                        )
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideForCanContinueDrawCardTest")
    @DisplayName("카드의 합계가 21 이하인지 확인할 수 있어야 한다.")
    void canContinueDrawTest(final List<Card> expectedCards) {
        manualCardStrategy.initCards(expectedCards);
        final Deck deck = Deck.generate(manualCardStrategy);
        final Participant participant = new Participant();

        for (int i = 0; i < expectedCards.size(); i++) {
            participant.drawCard(deck);
        }

        assertThat(participant.isBurst()).isFalse();
    }

    private static Stream<Arguments> provideForCanContinueDrawCardTest() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Card(CardPattern.DIAMOND, CardNumber.KING),
                                new Card(CardPattern.DIAMOND, CardNumber.THREE),
                                new Card(CardPattern.DIAMOND, CardNumber.EIGHT)
                        )
                ),
                Arguments.of(
                        List.of(
                                new Card(CardPattern.SPADE, CardNumber.ACE),
                                new Card(CardPattern.SPADE, CardNumber.TWO),
                                new Card(CardPattern.SPADE, CardNumber.SEVEN),
                                new Card(CardPattern.SPADE, CardNumber.THREE)
                        )
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideForIsHigherThanTest")
    @DisplayName("두 참여자의 카드 합계를 비교한다.")
    void isHigherThanTest(final List<Card> initializedCards) {
        manualCardStrategy.initCards(initializedCards);
        final Deck deck = Deck.generate(manualCardStrategy);
        final Participant participant1 = new Participant();
        participant1.drawCard(deck);
        participant1.drawCard(deck);
        final Participant participant2 = new Participant();
        participant2.drawCard(deck);
        participant2.drawCard(deck);

        assertThat(participant1.isHigherThan(participant2)).isTrue();
    }

    private static Stream<Arguments> provideForIsHigherThanTest() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Card(CardPattern.DIAMOND, CardNumber.KING),
                                new Card(CardPattern.DIAMOND, CardNumber.TEN),
                                new Card(CardPattern.DIAMOND, CardNumber.EIGHT),
                                new Card(CardPattern.DIAMOND, CardNumber.NINE)
                        )
                ),
                Arguments.of(
                        List.of(
                                new Card(CardPattern.SPADE, CardNumber.TEN),
                                new Card(CardPattern.SPADE, CardNumber.EIGHT),
                                new Card(CardPattern.SPADE, CardNumber.NINE),
                                new Card(CardPattern.HEART, CardNumber.EIGHT)
                        )
                )
        );
    }


}