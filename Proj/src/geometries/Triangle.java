package geometries;
import primitives.Point3D;

/**
 * Triangle in a three-dimensional Cartesian system
 * @author BS"D Matanya Goharian, Yaniv Moradov 
 * <matanya.goharian@gmail.com > <MoradovYaniv.Ym@gmail.com>
 */
public class Triangle extends Polygon
{
    /**
     * builds plane with 3D vertex 
     * @param vertex1 First vertex of the triangle
     * @param vertex2 Second vertex of the triangle
     * @param vertex3 Third vertex of the triangle
     */
    public Triangle(Point3D vertex1, Point3D vertex2, Point3D vertex3)
    {
        super(vertex1,vertex2,vertex3);
    }  
}
