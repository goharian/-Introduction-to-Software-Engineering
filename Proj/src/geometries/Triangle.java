package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point3D;

/**
 * Triangle in a three-dimensional Cartesian system
 *
 * @author BS"D Matanya Goharian, Yaniv Moradov
 * <matanya.goharian@gmail.com > <MoradovYaniv.Ym@gmail.com>
 */
public class Triangle extends Polygon {
    /**
     * constructor
     *
     * @param emission
     * @param material
     * @param _point1
     * @param _point2
     * @param _point3
     */
    public Triangle(Color emission, Material material, Point3D _point1, Point3D _point2, Point3D _point3) {
        super(emission, material, new Point3D[]{_point1, _point2, _point3});
    }

    /**
     * constructor
     *
     * @param emission
     * @param _point1
     * @param _point2
     * @param _point3
     */
    public Triangle(Color emission, Point3D _point1, Point3D _point2, Point3D _point3) {
        super(emission, new Point3D[]{_point1, _point2, _point3});
    }


    /**
     * builds plane with 3D vertex
     *
     * @param vertex1 First vertex of the triangle
     * @param vertex2 Second vertex of the triangle
     * @param vertex3 Third vertex of the triangle
     */
    public Triangle(Point3D vertex1, Point3D vertex2, Point3D vertex3) {
        super(vertex1, vertex2, vertex3);
    }
}
