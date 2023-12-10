package edu.project4.renderer;

import edu.project4.FractalImage;
import edu.project4.records.Pixel;
import edu.project4.records.Point;
import edu.project4.records.Rect;
import edu.project4.transformations.ColoredTransformation;
import edu.project4.transformations.Transformation;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class SingleRenderer implements Renderer {
    @Override
    public void render(
        FractalImage canvas,
        Rect world,
        List<ColoredTransformation> affineTransformations,
        List<Transformation> variations,
        int samples,
        int perSampleIterations,
        int symmetry
    ) {
        Random random = ThreadLocalRandom.current();
        for (int i = 0; i < samples; i++) {
            Point currentPoint = getRandomPointFromRect(world);

            for (int step = 0; step < perSampleIterations; step++) {
                ColoredTransformation affine = affineTransformations.get(random.nextInt(affineTransformations.size()));
                Transformation variation = variations.get(random.nextInt(0, variations.size()));
                currentPoint = transformPoint(currentPoint, affine, variation);

                double theta = 0.0;
                for (int s = 0; s < symmetry; s++) {
                    Point pointRotated = rotatePoint(currentPoint, theta);
                    theta += 2 * PI / symmetry;

                    if (!world.contains(pointRotated)) {
                        continue;
                    }

                    Pixel pixel = mapPointToPixel(canvas, world, pointRotated);
                    if (pixel == null) {
                        continue;
                    }

                    pixel.mixColor(affine.color());
                    pixel.hit();
                }
            }
        }
    }

    private Point getRandomPointFromRect(Rect world) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        double x = random.nextDouble(world.x(), world.x() + world.width());
        double y = random.nextDouble(world.y(), world.y() + world.height());
        return new Point(x, y);
    }

    private Point transformPoint(
        Point currentPoint, ColoredTransformation affineTransformation,
        Transformation variation
    ) {
        return variation.apply(affineTransformation.transformation().apply(currentPoint));
    }

    private Point rotatePoint(Point currentPoint, double theta) {
        double x = currentPoint.x() * cos(theta) - currentPoint.y() * sin(theta);
        double y = currentPoint.x() * sin(theta) + currentPoint.y() * cos(theta);
        return new Point(x, y);
    }

    private Pixel mapPointToPixel(FractalImage canvas, Rect world, Point point) {
        if (!world.contains(point)) {
            return null;
        }
        int pixelX = (int) ((point.x() - world.x()) * canvas.width() / world.width());
        int pixelY = (int) ((point.y() - world.y()) * canvas.height() / world.height());
        if (!canvas.contains(pixelX, pixelY)) {
            return null;
        }
        return canvas.pixel(pixelX, pixelY);
    }

}
