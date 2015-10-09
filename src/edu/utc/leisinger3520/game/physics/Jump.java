package edu.utc.leisinger3520.game.physics;

/**
 * Created by Ethan Leisinger on 9/16/2015.
 */
public class Jump extends Force {
    public Jump(float power) {
        force.setY(-power);
        immediate = true;
    }


}
