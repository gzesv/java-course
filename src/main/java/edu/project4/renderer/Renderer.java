package edu.project4.renderer;

import edu.project4.FractalImage;
import edu.project4.records.Rect;
import edu.project4.transformations.ColoredTransformation;
import edu.project4.transformations.Transformation;
import java.util.List;

@FunctionalInterface
public interface Renderer {
    default void render(
        FractalImage canvas,
        Rect world,
        List<ColoredTransformation> affineTransformations,
        List<Transformation> variations,
        int samples, int perSampleIterations
    ) {
        render(canvas, world, affineTransformations, variations, samples, perSampleIterations, 1);
    }

    void render(
        FractalImage canvas,
        Rect world,
        List<ColoredTransformation> affineTransformations,
        List<Transformation> variations,
        int samples,
        int perSampleIterations,
        int symmetry
    );
}
