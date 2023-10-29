package edu.hw3.Task2;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("HideUtilityClassConstructor")
public class BracketsClusterize {
    public static List<String> clusterize(String input) {
        int state = 0;
        List<String> clusters = new ArrayList<>();
        StringBuilder cluster = new StringBuilder();

        for (char ch : input.toCharArray()) {
            if (ch == '(') {
                state++;
            }

            if (ch == ')') {
                state--;
            }

            cluster.append(ch);

            if (state == 0) {
                clusters.add(cluster.toString());
                cluster = new StringBuilder();
            }
        }

        return clusters;
    }
}
