package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;

/**
 * radial shapes with a radius
 *
 * @author BS"D Matanya Goharian, Yaniv Moradov
 * <matanya.goharian@gmail.com > <MoradovYaniv.Ym@gmail.com>
 */
public abstract class RadialGeometry extends Geometry
{

    private double _radius;

    /**
     * constructor
     * @param emission
     * @param material
     */
    public RadialGeometry(Color emission, Material material, double _radius) {
        super(emission, material);
        this._radius = _radius;
    }

    /**
     * constructor
     * @param emission
     * @param _radius
     */
    public RadialGeometry(Color emission, double _radius) {

        this(emission, new Material(0,0,0), _radius);
    }

    /**
     * builds Radial Geometry with radius
     *
     * @param _radius sets the desired radius
     */
    public RadialGeometry(double _radius) {
        this(Color.BLACK, new Material(0,0,0), _radius);
    }

    /**
     * builds a copy of the resulting radial
     *
     * @param _radialGeometry radial to copy
     */
    public RadialGeometry(RadialGeometry _radialGeometry) {

        this._radius = _radialGeometry.getRadius();
        this.emission = _radialGeometry.getEmission();
        this.material = _radialGeometry.getMaterial();
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
