/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.player;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.control.BetterCharacterControl;
import com.jme3.scene.Node;

/**
 *
 * @author Bawb
 */
public class Player extends Node {
    
    private SimpleApplication      app;
    private BetterCharacterControl phys;
    private Node                   model;
    
    public Player(SimpleApplication app) {
        this.app = app;
        createModel();
        createPhys();
    }
    
    private void createPhys() {
        phys = new BetterCharacterControl(.3f, 1.1f, 100);
        addControl(phys);
    }
    
    public BetterCharacterControl getPhys() {
        return phys;
    }
    
    private void createModel() {
        model = (Node) app.getAssetManager().loadModel("Models/Warrior/Warrior.j3o");
        model.scale(.075f);
        model.setLocalTranslation(0,1,0);
        attachChild(model);
    }
    
    public Node getModel() {
        return model;
    }
    
    public void run() {
    
    }
    
    public void idle() {
    
    }
    
    public int getSpeedMult() {
        return 1;
    }
    
}
