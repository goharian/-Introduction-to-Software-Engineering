package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Geometric plane in 3D cartesian coordinates
 *
 * @author BS"D Matanya Goharian, Yaniv Moradov
 * <matanya.goharian@gmail.com > <MoradovYaniv.Ym@gmail.com>
 */
public class Plane implements Geometry
{

    private Point3D _point;
    private Vector _normal;

    /**
     * builds plane with 3D point and normal vector
     *
     * @param point point on the plane
     * @param normal Normal vector perpendicular to the plane
     */
    public Plane(Point3D point, Vector normal)
    {
        this._normal = new Vector(normal.normalize());
        this._point = new Point3D(point);
    }

    /**
     * builds plane with 3 point
     *
     * @param vertice1 first point on the plane
     * @param vertice2 second point on the plane
     * @param vertice3 third point on the plane
     */
    Plane(Point3D vertice1, Point3D vertice2, Point3D vertice3)
    {
        _point = new Point3D(vertice1);
        Vector v1 = vertice2.subtract(vertice1);
        Vector v2 = vertice3.subtract(vertice1);
        
        _normal = v1.crossProduct(v2).normalize();
    }

    /**
     * @param point Point across the plane
     * @return Normal vector perpendicular to the plane
     */
    @Override
    public Vector getNormal(Point3D point)
    {
        return getNormal();
    }

    /**
     * @return Normal vector perpendicular to the plane
     */
    public Vector getNormal()
    {
        return new Vector(_normal);
    }

    /**
     * @return The point on the plane as a new point
     */
    public Point3D getPoint()
    {
        return _point;
    }


    /**
     *
     * @param ray ray to check intersection with
     * @return returns intersection point.
     * if ray doesn't intersect or ray's head is on the plane returns null
     */
    @Override
    public List<Point3D> findIntersections(Ray ray) {
        try {
            if (this._point.equals(ray.getStart())) //p0 = q0
                return null;

            double t, nv = _normal.dotProduct(ray.getDirection()),
                    nqp = _normal.dotProduct(this._point.subtract(ray.getStart()));
            //t's denominator and numerator calculation

            if (!isZero(nv)) //n and v aren't orthogonal
            {
                if (!isZero(nqp)) //the ray's head isn't on the plane. otherwise returns null
                {
                    t = alignZero(nqp / nv);//calculates t
                    if (t > 0) //positive and not zero
                    {
                        LinkedList<Point3D> linkL = new LinkedList<Point3D>(); //creates list
                        linkL.add(ray.getPoint(t)); //adds single point to list
                        return linkL; //returns it
                    }

                }

            }
            return null;
            //any other case ray doesn't cut plane
        }
        catch (IllegalArgumentException e)//thrown if q0 and p0 are the same point
        {
            return null;
        }

    }

    /**
     * @return A string representing the specified plane
     */
    @Override
    public String toString()
    {
        return "point=" + _point.toString() + ", normal=" + _normal.toString();
    }
}
