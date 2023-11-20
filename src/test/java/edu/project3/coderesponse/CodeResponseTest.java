package edu.project3.coderesponse;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CodeResponseTest {

    @Test
    void getDescriptionByCode_test() {
        String expected = "OK";

        String result = CodeResponse.getDescriptionByCode(200);

        assertThat(result).isNotNull().isNotEmpty().isEqualTo(expected);
    }
}
