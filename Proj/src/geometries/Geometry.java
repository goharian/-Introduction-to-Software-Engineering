package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 *
 * @author BS"D Matanya Goharian, Yaniv Moradov
 * <matanya.goharian@gmail.com > <MoradovYaniv.Ym@gmail.com>
 */
interface Geometry
{

    /**
     * @param point Point across the geometric body
     * @return The normal (vertical) vector to the body at this point
     */
    Vector getNormal(Point3D point);
}
