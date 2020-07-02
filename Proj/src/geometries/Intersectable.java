package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.List;
import java.util.Objects;

/**
 * @author BS"D Matanya Goharian, Yaniv Moradov
 * <matanya.goharian@gmail.com > <MoradovYaniv.Ym@gmail.com>
 */
public interface Intersectable {
    /**
     * Find intersections points with the geometry shape
     *
     * @param ray Ray that coming out from the camera
     * @return intersections points with the geometry shape
     */
    List<GeoPoint> findIntersections(Ray ray);

    /**
     * Class geometric point
     */
    public static class GeoPoint {
        public Geometry geometry;

        /**
         * equals
         *
         * @param o
         * @return true if the geometries and the points are equal
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return Objects.equals(geometry, geoPoint.geometry) &&
                    Objects.equals(point, geoPoint.point);
        }


        /**
         * constructor
         *
         * @param geometry
         * @param point
         */
        public GeoPoint(Geometry geometry, Point3D point) {
            this.geometry = geometry;
            this.point = point;
        }

        public Point3D point;
    }
}
