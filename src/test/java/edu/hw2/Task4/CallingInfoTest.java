package edu.hw2.Task4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw2.Task4.CallingInfoUtils.callingInfo;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CallingInfoTest {
    @Test
    @DisplayName("Тест CallingInfo")
    void callingInfo_test() {
        CallingInfo callingInfo = callingInfo();

        String className = callingInfo.className();
        String methodName = callingInfo.methodName();

        assertThat(className).isEqualTo("edu.hw2.Task4.CallingInfoTest");
        assertThat(methodName).isEqualTo("testCallingInfo");
    }
}
