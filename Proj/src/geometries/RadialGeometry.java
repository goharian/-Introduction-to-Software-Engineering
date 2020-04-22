package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * radial shapes with a radius
 *
 * @author BS"D Matanya Goharian, Yaniv Moradov
 * <matanya.goharian@gmail.com > <MoradovYaniv.Ym@gmail.com>
 */
public abstract class RadialGeometry implements Geometry
{

    private double _radius;

    /**
     * builds Radial Geometry with radius
     *
     * @param radius sets the desired radius
     */
    public RadialGeometry(double radius)
    {
        this._radius = radius;
    }

    /**
     * builds a copy of the resulting radial
     *
     * @param radial radial to copy
     */
    RadialGeometry(RadialGeometry radial)
    {
        _radius = radial._radius;
    }

    /**
     * @return radius of the radial geometry
     */
    public double getRadius()
    {
        return _radius;
    }

    /**
     * @return A string representing the specified radial geometry object
     */
    @Override
    public String toString()
    {
        return "radius=" + _radius;
    }

}
