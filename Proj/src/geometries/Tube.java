package geometries;

import primitives.*;

import java.util.List;

/**
 * tube in a 3D Cartesian coordinate wth a radius and ray
 *
 * @author BS"D Matanya Goharian, Yaniv Moradov
 * <matanya.goharian@gmail.com > <MoradovYaniv.Ym@gmail.com>
 */
public class Tube extends RadialGeometry {

    public Ray _ray;

    /**
     * constructor
     *
     * @param emission
     * @param material
     * @param _radius
     * @param _ray
     */
    public Tube(Color emission, Material material, double _radius, Ray _ray) {
        super(emission, material, _radius);
        this._ray = _ray;
    }

    /**
     * constructor
     *
     * @param emission
     * @param _radius
     * @param _ray
     */
    public Tube(Color emission, double _radius, Ray _ray) {
        this(emission, new Material(0,0,0), _radius, _ray);
    }

    /**
     * builds tube with radius and ray
     *
     * @param _radius
     * @param _ray
     */
    public Tube(double _radius, Ray _ray) {
        this(Color.BLACK, new Material(0,0,0), _radius, _ray);
    }

    /**
     * copy constructor
     *
     * @param _radialGeometry to copy
     */
    public Tube(RadialGeometry _radialGeometry, Ray _ray) {
        super(_radialGeometry);
        this._ray = _ray;
    }


    /**
     * @param p Point across the plane
     * @return Normal vector perpendicular to the tube
     */
    @Override
    public Vector getNormal(Point3D p)
    {
        Vector v = p.subtract((_ray.getStart()));
        double t = _ray.getDirection().dotProduct(v);
        Point3D o = _ray.getStart().add(_ray.getDirection().scale(t));
        Vector n = (p.subtract(o)).normalize();
        return n;
    }
    /**
     * @return Ray of the tube
     */
    public Ray get_ray() {
        return _ray;
    }

    /**
     * @return A string representing the specified tube
     */
    @Override
    public String toString() {
        return "Tube{" + "_axisRay=" + _ray + '}';
    }

    @Override
    public List<GeoPoint> findIntersections(Ray ray) {
        return null;
    }
}
