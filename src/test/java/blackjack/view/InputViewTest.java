package blackjack.view;

import blackjack.view.reader.CustomReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class InputViewTest {

    private final CustomReader customReader = new CustomReader();
    private final InputView inputView = new InputView(customReader);

    @ParameterizedTest
    @MethodSource("provideForParsePlayerNamesTest")
    @DisplayName("플레이어 이름은 쉼표로 구분되어야 한다.")
    void parsePlayerNamesTest(final String inputLine, final List<String> expectedPlayerNames) {
        customReader.initTest(inputLine);

        final List<String> actualPlayerNames = inputView.requestPlayerNames();

        assertThat(actualPlayerNames).isEqualTo(expectedPlayerNames);
    }

    public static Stream<Arguments> provideForParsePlayerNamesTest() {
        return Stream.of(
                Arguments.of("pobi,if,sun", List.of("pobi", "if", "sun")),
                Arguments.of("pobi, if, sun", List.of("pobi", "if", "sun")),
                Arguments.of("", List.of("")),
                Arguments.of("    ", List.of("")),
                Arguments.of("pobi,", List.of("pobi", "")),
                Arguments.of("pobi,,,", List.of("pobi", "", "", ""))
        );
    }

    @ParameterizedTest
    @MethodSource("provideForParseHitAndStandTest")
    @DisplayName("y 또는 n을 입력받을 수 있어야 한다.")
    void parseProgress(final String inputLine, final boolean expectedContinuable) {
        customReader.initTest(inputLine);

        final boolean actualContinuable = inputView.requestContinuable();

        assertThat(actualContinuable).isEqualTo(expectedContinuable);
    }

    public static Stream<Arguments> provideForParseHitAndStandTest() {
        return Stream.of(
                Arguments.of("y", true),
                Arguments.of("Y", true),
                Arguments.of("n", false),
                Arguments.of("n", false)
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "yyy", "nnn", "123"})
    @DisplayName("y 또는 n만 입력할 수 있습니다.")
    void parseProgress(final String inputLine) {
        customReader.initTest(inputLine);

        assertThatThrownBy(inputView::requestContinuable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("입력은 y 또는 n이어야 합니다.");
    }

}
