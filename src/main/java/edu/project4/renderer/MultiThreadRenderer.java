package edu.project4.renderer;

import edu.project4.FractalImage;
import edu.project4.records.Pixel;
import edu.project4.records.Point;
import edu.project4.records.Rect;
import edu.project4.transformations.ColoredTransformation;
import edu.project4.transformations.Transformation;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class MultiThreadRenderer implements Renderer {
    private final int threadCount;

    public MultiThreadRenderer(int threadCount) {
        this.threadCount = threadCount;
    }

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
        try (ExecutorService executorService = Executors.newCachedThreadPool()) {
            int samplesPerThread = samples / threadCount;
            List<Callable<Void>> tasks = new ArrayList<>();
            for (int i = 0; i < threadCount; i++) {
                double splitSize = world.width() / threadCount;
                double startPoint = i * (splitSize) + world.x();
                Rect threadWorld = new Rect(startPoint, 0, splitSize, world.height());
                Callable<Void> splitTask = getSplitTask(canvas, threadWorld, world,
                    affineTransformations, variations,
                    samplesPerThread, perSampleIterations, symmetry
                );
                tasks.add(splitTask);
            }
            var futures = executorService.invokeAll(tasks);
            for (var future : futures) {
                future.get();
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("ParameterNumber")
    private Callable<Void> getSplitTask(
        FractalImage canvas, Rect threadWorld, Rect world,
        List<ColoredTransformation> affineTransformations, List<Transformation> variations,
        int samplesPerThread, int perSampleIterations, int symmetry
    ) {
        return () -> {
            for (int num = 0; num < samplesPerThread; num++) {
                Point currentPoint = getRandomPointFromRect(threadWorld);

                for (int step = 0; step < perSampleIterations; step++) {
                    ColoredTransformation affine = getRandomAffineTransformation(affineTransformations);
                    Transformation variation = getRandomVariation(variations);
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
                        pixel.lock.lock();
                        pixel.mixColor(affine.color());
                        pixel.hit();
                        pixel.lock.unlock();
                    }
                }
            }
            return null;
        };
    }

    private Point getRandomPointFromRect(Rect world) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        double x = random.nextDouble(world.x(), world.x() + world.width());
        double y = random.nextDouble(world.y(), world.y() + world.height());
        return new Point(x, y);
    }

    private ColoredTransformation getRandomAffineTransformation(List<ColoredTransformation> affineTransformations) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return affineTransformations.get(random.nextInt(affineTransformations.size()));
    }

    private Transformation getRandomVariation(List<Transformation> variations) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return variations.get(random.nextInt(0, variations.size()));
    }

    private Point transformPoint(
        Point currentPoint,
        ColoredTransformation affineTransformation,
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
