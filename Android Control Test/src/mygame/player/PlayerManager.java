/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.player;

import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.bullet.BulletAppState;
import com.jme3.math.Vector3f;

/**
 *
 * @author Bawb
 */
public class PlayerManager extends AbstractAppState {
   
    private Player            player;
    private SimpleApplication app;
    
    public PlayerManager(SimpleApplication app) {
        this.app = app;
        createPlayer();
    }
    
    public void initPersonPlayer(BulletAppState physics) {
        app.getRootNode().attachChild(player);
        physics.getPhysicsSpace().add(player.getPhys());
        player.getPhys().setGravity(new Vector3f(0,-50,0));
    }       
    
    private void createPlayer() {
        player = new Player(app);
        app.getRootNode().attachChild(player);
    }
    
    public Player getPlayer() {
        return player;
    }
    
}
