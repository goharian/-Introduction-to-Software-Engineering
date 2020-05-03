package geometries;


import primitives.Point3D;
import primitives.Ray;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Geometries class represents a list of shapes of all kinds
 * @author BS"D Matanya Goharian, Yaniv Moradov
 * <matanya.goharian@gmail.com > <MoradovYaniv.Ym@gmail.com>
 */
public class Geometries implements Intersectable {

    LinkedList<Intersectable> _shapes;

    /**
     * @param geometries geometries to add to list that's being created
     */
    public Geometries(Intersectable... geometries) {
        _shapes = new LinkedList<Intersectable>();
        //linkedList chosen because the adding time is O(1) and we only add items to this list, and do less to change them
        if (geometries.length > 0)//if there are items to add
            add(geometries); //call add function with geometries to add to list
    }

    /**
     * @param geometries geometries to add to list
     */
    public void add(Intersectable... geometries) {
        if (geometries.length > 0) {
            _shapes.addAll(Arrays.asList(geometries));//adds items in geometries to shapes
        }
    }

    /**
     * @return list of interesections from all shapes in list
     */
    @Override
    public List<Point3D> findIntsersections(Ray ray) {
        LinkedList<Point3D> intersections = new LinkedList<Point3D>(); //New list to store points in
        for (Intersectable shape : _shapes)//iterates over list
        {
            List<Point3D> thisIntersections = shape.findIntsersections(ray);//finds intersections
            if (thisIntersections != null)//interesections returned
                intersections.addAll(thisIntersections);//adds all points to end of intersections list
        }
        if (intersections.size() == 0)//no elements
            return null;
        return intersections;
    }

    /**
     * @return copy of list of shapes
     */
    public LinkedList<Intersectable> get_shapes() {
        return new LinkedList<Intersectable>(_shapes); //creates new list and returns
    }
}
