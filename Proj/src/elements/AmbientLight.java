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
     * @param _ia
     * @param ka
     */
    public AmbientLight(Color _ia, double ka) {
        super(_ia.scale(ka));
    }
}

