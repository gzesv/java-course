package edu.project4;

import edu.project4.postprocessing.GammaCorrector;
import edu.project4.records.Color;
import edu.project4.records.Rect;
import edu.project4.renderer.MultiThreadRenderer;
import edu.project4.renderer.Renderer;
import edu.project4.transformations.ColoredTransformation;
import edu.project4.transformations.LinearTransformation;
import edu.project4.transformations.SpiralTransformation;
import edu.project4.transformations.Transformation;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ImageUtilsTest {
    private final Path saveFilePathPng = Path.of("src", "test", "java", "edu", "project4", "savetest.png");
    private final Path saveFilePathBmp = Path.of("src", "test", "java", "edu", "project4", "savetest.bmp");
    private final Path saveFilePathJpeg = Path.of("src", "test", "java", "edu", "project4", "savetest.jpeg");

    @Test
    void save_test() {
        FractalImage canvas = FractalImage.create(1920, 1080);
        Rect world = new Rect(-1, -1, 1, 1);
        List<ColoredTransformation> affines = List.of(
            new ColoredTransformation(
                LinearTransformation.randomTransformation(),
                Color.generate()
            )
        );
        List<Transformation> variations = List.of(
            new SpiralTransformation()
        );
        Renderer simpleRenderer = new MultiThreadRenderer(8);

        simpleRenderer.render(
            canvas,
            world,
            affines,
            variations,
            10,
            100
        );

        GammaCorrector gammaCorrector = new GammaCorrector();
        gammaCorrector.process(canvas);

        ImageUtils.save(canvas, saveFilePathPng, ImageFormat.PNG);
        ImageUtils.save(canvas, saveFilePathBmp, ImageFormat.PNG);
        ImageUtils.save(canvas, saveFilePathJpeg, ImageFormat.PNG);

        assertThat(Files.exists(saveFilePathPng)).isTrue();
        assertThat(Files.exists(saveFilePathBmp)).isTrue();
        assertThat(Files.exists(saveFilePathJpeg)).isTrue();
    }

    @AfterEach
    void deleteFail() {
        try {
            Files.delete(saveFilePathPng);
            Files.delete(saveFilePathBmp);
            Files.delete(saveFilePathJpeg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
