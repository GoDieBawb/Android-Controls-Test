/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.player;

import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.app.SimpleApplication;
import com.jme3.bullet.control.BetterCharacterControl;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

/**
 *
 * @author Bawb
 */
public class Player extends Node {
    
    private SimpleApplication      app;
    private BetterCharacterControl phys;
    private Node                   model;
    private AnimControl            animControl;
    private AnimChannel            animChannel;
    private float                  speedMult;
    private float                  lookHeight;
    
    public Player(SimpleApplication app) {
        this.app = app;
        createModel();
        createPhys();
        speedMult = 1;
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
        animControl = findAnimControl(model);
        animChannel = animControl.createChannel();
        animChannel.setAnim("FkIdle");
    }
    
    public Node getModel() {
        return model;
    }
    
    public void run() {
        
        if(!animChannel.getAnimationName().equals("FkWalk")) {
            animChannel.setAnim("FkWalk");
        }
        
    }
    
    public void idle() {
        
        if(!animChannel.getAnimationName().equals("FkIdle")) {
            animChannel.setAnim("FkIdle");
        }
        
    }
    
    public AnimControl findAnimControl(final Spatial parent){
        
        AnimControl animControl = parent.getControl(AnimControl.class);
        if (animControl != null) {
            return animControl;
        }
        
        if (parent instanceof Node) {
            
            for (final Spatial s : ((Node) parent).getChildren()) {
                
                final AnimControl animControl2 = findAnimControl(s);
                
                if (animControl2 != null) {
                    return animControl2;
                }
                
            }
            
        }
        
        return null;
    }    
    
    public float getSpeedMult() {
        return speedMult;
    }
    
    public void setSpeedMult(float newVal) {
        speedMult = newVal;
    }
    
    public float getLookHeight() {
        return lookHeight;
    }
    
    public void setLookHeight(float newVal) {
        lookHeight = newVal;
    }
    
}
