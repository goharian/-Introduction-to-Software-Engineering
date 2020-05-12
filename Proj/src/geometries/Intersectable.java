package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.List;

/**
 * @author BS"D Matanya Goharian, Yaniv Moradov
 * <matanya.goharian@gmail.com > <MoradovYaniv.Ym@gmail.com>
 */
public interface Intersectable{
    /**
     * Find intersections points with the geometry shape
     * @param ray Ray that coming out from the camera
     * @return intersections points with the geometry shape
     */
    List<Point3D> findIntersections(Ray ray);
}
