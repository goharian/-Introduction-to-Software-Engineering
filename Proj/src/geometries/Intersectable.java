package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.List;

/**
 * @author BS"D Matanya Goharian, Yaniv Moradov
 * <matanya.goharian@gmail.com > <MoradovYaniv.Ym@gmail.com>
 */
public interface Intersectable {
    List<Point3D> findIntsersections(Ray ray);
}
