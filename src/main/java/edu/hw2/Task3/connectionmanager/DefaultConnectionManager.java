package edu.hw2.Task3.connectionmanager;

import edu.hw2.Task3.connection.Connection;
import edu.hw2.Task3.connection.FaultyConnection;
import edu.hw2.Task3.connection.StableConnection;
import java.util.Random;

public class DefaultConnectionManager implements ConnectionManager {
    private final double faultyConnectionChance;
    private static final Random RANDOM = new Random();

    public DefaultConnectionManager(double faultyConnectionChance) {
        this.faultyConnectionChance = faultyConnectionChance;
    }

    @Override
    public Connection getConnection() {
        if (RANDOM.nextDouble() < faultyConnectionChance) {
            return new FaultyConnection(faultyConnectionChance);
        }
        return new StableConnection();
    }
}
