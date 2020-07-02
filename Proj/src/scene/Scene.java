package scene;

import elements.*;
import geometries.Geometries;
import geometries.Intersectable;
import primitives.Color;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * class representing a scene with all of its details
 *
 * @author BS"D Matanya Goharian, Yaniv Moradov
 * <matanya.goharian@gmail.com > <MoradovYaniv.Ym@gmail.com>
 */
public class Scene {
    String _name;
    Color _background;          //background color
    AmbientLight _ambientLight; //scene lighting
    Geometries _geometries;     //list of 3d geometries
    Camera _camera;
    double _distance;           //distance of camera from viewplane
    List<LightSource> _lights;

    /**
     * @param _name scene name
     *              also create empty list of geometries
     */
    public Scene(String _name) {
        this._name = _name;
        _geometries = new Geometries();
        _lights = new LinkedList<LightSource>();
    }

    /**
     * getter
     *
     * @return _lights list
     */
    public List<LightSource> get_lights() {
        return _lights;
    }

    /**
     * @return scene name
     */
    public String getName() {
        return _name;
    }

    /**
     * @return scene background color
     */
    public Color getBackground() {
        return new Color(_background);
    }

    /**
     * @return scene's ambient lighting
     */
    public AmbientLight getAmbientLight() {
        return _ambientLight;
    }

    /**
     * @return list of scene geometries
     */
    public Geometries getGeometries() {
        return new Geometries(_geometries);
    }

    /**
     * @return scene camera
     */
    public Camera getCamera() {
        return _camera;
    }

    /**
     * @return viewplane distance from camera
     */
    public double getDistance() {
        return _distance;
    }

    /**
     * @param _background color to set background with
     */
    public void setBackground(Color _background) {
        this._background = _background;
    }

    /**
     * @param _ambientLight lighting that the scene has
     */
    public void setAmbientLight(AmbientLight _ambientLight) {
        this._ambientLight = _ambientLight;
    }

    /**
     * @param _camera new scene's camera
     */
    public void setCamera(Camera _camera) {
        this._camera = _camera;
    }

    /**
     * @param _distance distance to set to
     * @throws IllegalArgumentException for negative number
     */
    public void set_distance(double _distance) {
        if (_distance < 0)
            throw new IllegalArgumentException("distance must be positive");
        this._distance = _distance;
    }

    /**
     * add the given geometries to the list
     *
     * @param geometries geometries to add to list in scene
     */
    public void addGeometries(Intersectable... geometries) {
        _geometries.add(geometries);
    }

    /**
     * add the given lights to the list
     *
     * @param lights lights to add to list in scene
     */
    public void addLights(LightSource... lights) {
        _lights.addAll(Arrays.asList(lights));
    }

}
