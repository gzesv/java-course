package edu.hw5.Task3.parsers;

import java.time.LocalDate;

public interface Parser {

    boolean tryParse(String date);

    LocalDate parse(String date);

}
