package edu.hw2.Task3.connectionmanager;

import edu.hw2.Task3.connection.Connection;
import edu.hw2.Task3.connection.FaultyConnection;

public class FaultyConnectionManager implements ConnectionManager {
    private final double faultyConnectionChance;

    public FaultyConnectionManager(double connectionChance) {
        this.faultyConnectionChance = connectionChance;
    }

    @Override
    public Connection getConnection() {
        return new FaultyConnection(faultyConnectionChance);
    }
}
