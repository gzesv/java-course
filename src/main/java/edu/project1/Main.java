package edu.project1;

import edu.project1.consolehangman.ConsoleHangman;
import edu.project1.dictionary.Dictionary;

public final class Main {

    private Main() { }

    public static void main(String[] args) {
        ConsoleHangman game = new ConsoleHangman(new Dictionary());
        game.run();
    }
}
