package blackjack.domain.result;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class MatchResultTest {

    @ParameterizedTest
    @MethodSource("provideForMatchResult")
    @DisplayName("플레이어의 승패에 따른 딜러 승패 계산")
    void calculateCountOfWinningResult(final Map<String, WinningResult> winningResults,
                                       final Map<WinningResult, Integer> dealerCountOfWinningResult) {
        final MatchResult matchResult = new MatchResult(winningResults);

        Map<WinningResult, Integer> count = matchResult.getDealerResult();
        assertThat(count).isEqualTo(dealerCountOfWinningResult);
    }

    private static Stream<Arguments> provideForMatchResult() {
        return Stream.of(
                Arguments.of(
                        Map.of(
                                "sun", WinningResult.WIN,
                                "if", WinningResult.WIN,
                                "pobi", WinningResult.LOSS
                        ),
                        Map.of(
                                WinningResult.WIN, 1,
                                WinningResult.LOSS, 2
                        )
                )
        );
    }

}
