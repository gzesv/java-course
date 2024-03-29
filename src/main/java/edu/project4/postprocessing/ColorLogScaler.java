package edu.project4.postprocessing;

import edu.project4.FractalImage;
import edu.project4.records.Color;
import edu.project4.records.Pixel;
import static java.lang.Math.log10;

public class ColorLogScaler implements PostProcessor {
    private final int scaleCoefficient;

    public ColorLogScaler(int scale) {
        this.scaleCoefficient = scale;
    }

    @Override
    public void process(FractalImage canvas) {
        for (int i = 0; i < canvas.width(); i++) {
            for (int j = 0; j < canvas.height(); j++) {
                Pixel pixel = canvas.pixel(i, j);
                Color color = pixel.color();
                pixel.setColor(scaleColor(color, pixel.hitCount()));
            }
        }
    }

    public Color scaleColor(Color color, int hitCount) {
        int newR = scale(color.r(), hitCount);
        int newG = scale(color.g(), hitCount);
        int newB = scale(color.b(), hitCount);
        return new Color(newR, newG, newB);
    }

    private int scale(int color, int hitCount) {
        return (int) (scaleCoefficient * color * (log10(hitCount) / hitCount));
    }
}
