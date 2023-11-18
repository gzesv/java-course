package edu.hw5.Task6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw5.Task6.SubsequenceRegex.isSubsequence;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SubsequenceRegexTest {

    @Test
    @DisplayName("Является подпоследовательностью")
    void isSubsequence_test() {
        String subsequence = "abc";
        String sequence = "achfdbaabgabcaabg";

        boolean result = isSubsequence(sequence, subsequence);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Не является подпоследовательностью")
    void isNotSubsequence_test() {
        String subsequence = "abc";
        String sequence = "achfdbaabgabfaabg";

        boolean result = isSubsequence(sequence, subsequence);

        assertThat(result).isFalse();
    }
}
