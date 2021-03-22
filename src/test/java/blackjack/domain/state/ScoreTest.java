package blackjack.domain.state;

import blackjack.domain.state.hand.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {

    @Test
    @DisplayName("Score 객체 생성된다.")
    void createScoreTest() {
        /*give*/
        Score score = new Score(21);

        /*then*/
        assertThat(score).isInstanceOf(Score.class);
        assertThat(score.getValue()).isEqualTo(21);
    }

    @Test
    @DisplayName("값이 21 이상이면 Ace를 1로 바꾼다.")
    void changeToHardAceTest() {
        /*give*/
        Score score = new Score(31);

        /*when*/
        score = score.changeHardAce();

        /*then*/
        assertThat(score.getValue()).isEqualTo(21);
    }

    @Test
    @DisplayName("Score 비교 테스트")
    void isHigherTest() {
        /*give*/
        Score score1 = new Score(20);
        Score score2 = new Score(21);

        /*then*/
        assertThat(score1.isHigher(score2)).isFalse();
        assertThat(score2.isHigher(score1)).isTrue();
    }

    @Test
    @DisplayName("Score가 Bust인지 아닌지 판단한다.")
    void isBustTest() {
        /*give*/
        Score noneBustScore = new Score(21);
        Score bustScore = new Score(22);

        /*then*/
        assertThat(noneBustScore.isBust()).isFalse();
        assertThat(bustScore.isBust()).isTrue();
    }

    @Test
    @DisplayName("Score가 Blackjack인지 아닌지 판단한다.")
    void isBlackjackTest() {
        /*give*/
        Score noneBlackjackScore = new Score(20);
        Score blackjackScore = new Score(21);

        /*then*/
        assertThat(noneBlackjackScore.isBlackjack()).isFalse();
        assertThat(blackjackScore.isBlackjack()).isTrue();
    }

    @Test
    @DisplayName("값이 같으면 같은 hashCode를 반환한다.")
    void hashCodeTest() {
        /*give*/
        Score score1 = new Score(20);
        Score score2 = new Score(20);

        /*then*/
        assertThat(score1.hashCode()).isEqualTo(score2.hashCode());
    }

    @Test
    @DisplayName("딜러의 점수가 히트할 수 있는 한계 점수(16점을 초과)인지 체크 16점을 초과하면 true, 아니면 false반환")
    void isDealerLimitScoreTest() {
        //give
        Score score1 = new Score(16);
        Score score2 = new Score(17);

        //when
        boolean isDealerLimitScore1 = score1.isDealerLimitScore();
        boolean isDealerLimitScore2 = score2.isDealerLimitScore();

        //then
        assertThat(isDealerLimitScore1).isFalse();
        assertThat(isDealerLimitScore2).isTrue();
    }

    @Test
    @DisplayName("getValue로 Score의 value 반환된다.")
    void getValueTest() {
        //give
        Score score = new Score(10);

        //when
        int value = score.getValue();

        //then
        assertThat(value).isEqualTo(10);
    }
}