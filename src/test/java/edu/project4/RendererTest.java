package edu.project4;

import edu.project4.postprocessing.ColorLogScaler;
import edu.project4.postprocessing.GammaCorrector;
import edu.project4.records.Color;
import edu.project4.records.Rect;
import edu.project4.renderer.MultiThreadRenderer;
import edu.project4.renderer.Renderer;
import edu.project4.renderer.SingleRenderer;
import edu.project4.transformations.ColoredTransformation;
import edu.project4.transformations.HeartTransformation;
import edu.project4.transformations.HyperbolicTransformation;
import edu.project4.transformations.LinearTransformation;
import edu.project4.transformations.SphericalTransformation;
import edu.project4.transformations.SpiralTransformation;
import edu.project4.transformations.Transformation;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class RendererTest {
    @Test
    @DisplayName("Однопоточный вариант")
    void singleRenderer_test() {
        FractalImage canvas = FractalImage.create(1920, 1080);
        Rect world = new Rect(-1, -1, 1, 1);
        List<ColoredTransformation> affines = List.of(
            new ColoredTransformation(
                new LinearTransformation(0.45, 0.65, 0.31, 0.65, 0.5, 0.2),
                Color.generate()
            ),
            new ColoredTransformation(
                new LinearTransformation(-0.26516, 0.56498462, -0.13151, 0.468485, 0.9876541, -0.634864),
                new Color(69, 25, 175)
            )
        );
        List<Transformation> variations = List.of(
            new SpiralTransformation(),
            new HeartTransformation()
        );
        Renderer simpleRenderer = new SingleRenderer();

        simpleRenderer.render(
            canvas,
            world,
            affines,
            variations,
            1000,
            100000
        );
        ColorLogScaler colorLogScaler = new ColorLogScaler(1);
        colorLogScaler.process(canvas);

        assertThat(canvas.data()).isNotNull();
    }

    @Test
    @DisplayName("Многопоточный вариант")
    void multiThreadRenderer_test() {
        FractalImage canvas = FractalImage.create(1920, 1080);
        Rect world = new Rect(-1, -1, 2, 2);
        List<ColoredTransformation> affines = List.of(
            new ColoredTransformation(
                LinearTransformation.randomTransformation(),
                Color.generate()
            ),
            new ColoredTransformation(
                LinearTransformation.randomTransformation(),
                Color.generate()
            )
        );
        List<Transformation> variations = List.of(
            new SpiralTransformation(),
            new HeartTransformation(),
            new SphericalTransformation(),
            new HyperbolicTransformation()
        );
        Renderer simpleRenderer = new MultiThreadRenderer(8);

        simpleRenderer.render(
            canvas,
            world,
            affines,
            variations,
            1000,
            100000,
            1
        );

        GammaCorrector gammaCorrector = new GammaCorrector();
        gammaCorrector.process(canvas);

        assertThat(canvas.data()).isNotNull();
    }
}
