package edu.project4.postprocessing;

import edu.project4.FractalImage;

@FunctionalInterface
public interface PostProcessor {
    void process(FractalImage image);
}
