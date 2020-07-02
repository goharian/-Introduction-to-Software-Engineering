package geometries;


import primitives.Point3D;
import primitives.Ray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Geometries class represents a list of shapes of all kinds
 *
 * @author BS"D Matanya Goharian, Yaniv Moradov
 * <matanya.goharian@gmail.com > <MoradovYaniv.Ym@gmail.com>
 */
public class Geometries implements Intersectable {
    List<Intersectable> geometries;

    public Geometries() {
        this.geometries = new ArrayList();
        //Array list - Dynamically re-sizing
        //Constant-time positional access
    }

    /**
     * @param geometries geometries to add to list that's being created
     */
    public Geometries(Intersectable... geometries) {
        this.geometries = new ArrayList();
        this.geometries.addAll(Arrays.asList(geometries));
    }

    /**
     * @param geometries geometries to add to list
     */
    public void add(Intersectable... geometries) {
        this.geometries.addAll(Arrays.asList(geometries));
    }


    /**
     * @return list of interesections from all shapes in list
     */
    @Override
    public List<GeoPoint> findIntersections(Ray ray) {
        List<GeoPoint> intersections = new ArrayList();
        for (Intersectable g : geometries) {
            List<GeoPoint> result = g.findIntersections(ray);
            if (result != null)
                intersections.addAll(result);
        }
        if (intersections.isEmpty())
            return null;
        return intersections;
    }
}