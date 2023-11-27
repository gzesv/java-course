package edu.hw6.Task6;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PortScannerTest {
    public static final int LEFT_BORDER = 0;
    public static final int RIGHT_BORDER = 49151;

    @Test
    void portsList_isNotNull_test() {
        List<String> info = new ArrayList<>();

        for (int port = LEFT_BORDER; port <= RIGHT_BORDER; port++) {
            if (PortScanner.isTcpPortClosed(port)) {
                info.add(getPortInfo("TCP", port));
            }

            if (PortScanner.isUdpPortClosed(port)) {
                info.add(getPortInfo("UDP", port));
            }
        }

        assertThat(info).isNotNull();
    }

    private static String getPortInfo(String type, int port) {
        return type + " " + port + " " + PortScanner.getServiceName(port);
    }
}
