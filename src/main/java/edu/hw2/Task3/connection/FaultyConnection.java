package edu.hw2.Task3.connection;

import edu.hw2.Task3.ConnectionException;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FaultyConnection implements Connection {
    private final static Logger LOGGER = LogManager.getLogger();
    private static final Random RANDOM = new Random();
    private final double faultyConnectionChance;

    public FaultyConnection(double faultyConnectionChance) {
        this.faultyConnectionChance = faultyConnectionChance;
    }

    @Override
    public void execute(String command) {
        if (RANDOM.nextDouble() < faultyConnectionChance) {
            throw new ConnectionException("Failed");
        }
        LOGGER.info("Command " + command + " executed successfully!");
    }

    @Override
    public void close() throws Exception { }
}
