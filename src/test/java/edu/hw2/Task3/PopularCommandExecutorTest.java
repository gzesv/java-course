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
    void testFaultyConnectionThroughFaultyConnectionManager() {
        ConnectionManager connectionManager = new FaultyConnectionManager(1.0);
        int maxAttempts = 100;
        PopularCommandExecutor executor = new PopularCommandExecutor(connectionManager, maxAttempts);

        assertThrows(ConnectionException.class, executor::updatePackages);
    }

    @Test
    @DisplayName("Стабильное соединение")
    void testStableConnection() {
        var connectionManager = new DefaultConnectionManager(0.0);
        int maxAttempts = 100;
        var executor = new PopularCommandExecutor(connectionManager, maxAttempts);

        assertDoesNotThrow(executor::updatePackages);
    }

    @Test
    @DisplayName("Неисправное соединение через DefaultConnectionManager")
    void testFaultyConnectionThroughDefaultConnectionManager() {
        var connectionManager = new DefaultConnectionManager(1.0);
        int maxAttempts = 100;
        var executor = new PopularCommandExecutor(connectionManager, maxAttempts);

        assertThrows(ConnectionException.class, executor::updatePackages);
    }
}
