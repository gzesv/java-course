package edu.hw2.Task3;

import edu.hw2.Task3.connectionmanager.ConnectionManager;
import edu.hw2.Task3.connectionmanager.DefaultConnectionManager;
import edu.hw2.Task3.connectionmanager.FaultyConnectionManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PopularCommandExecutorTest {

    @Test
    @DisplayName("Неисправное соединение через FaultyConnectionManager")
    void faultyConnectionManager_return_faultyConnection_test() {
        ConnectionManager connectionManager = new FaultyConnectionManager(1.0);
        int maxAttempts = 100;
        PopularCommandExecutor executor = new PopularCommandExecutor(connectionManager, maxAttempts);

        assertThrows(ConnectionException.class, executor::updatePackages);
    }

    @Test
    @DisplayName("Стабильное соединение")
    void defaultConnectionManager_return_stableConnection_test() {
        var connectionManager = new DefaultConnectionManager(0.0);
        int maxAttempts = 100;
        var executor = new PopularCommandExecutor(connectionManager, maxAttempts);

        assertDoesNotThrow(executor::updatePackages);
    }

    @Test
    @DisplayName("Неисправное соединение через DefaultConnectionManager")
    void defaultConnectionManager_return_faultyConnection_test() {
        var connectionManager = new DefaultConnectionManager(1.0);
        int maxAttempts = 100;
        var executor = new PopularCommandExecutor(connectionManager, maxAttempts);

        assertThrows(ConnectionException.class, executor::updatePackages);
    }
}
