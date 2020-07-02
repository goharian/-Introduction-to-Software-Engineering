package elements;

import primitives.Color;
/**
 *
 * @author BS"D Matanya Goharian, Yaniv Moradov
 * <matanya.goharian@gmail.com > <MoradovYaniv.Ym@gmail.com>
 */
public class AmbientLight extends Light{

    /**
     * constructor
     * @param _intensity
     * @param ka
     */
    public AmbientLight(Color _intensity, double ka) {
        super(_intensity.scale(ka));
    }


}
