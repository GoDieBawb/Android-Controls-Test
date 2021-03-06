/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.controls.slerp;

import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppStateManager;
import com.jme3.math.Vector3f;
import mygame.GameManager;
import mygame.controls.InteractionControl;
import mygame.player.Player;
import mygame.player.PlayerManager;
import mygame.util.InteractionManager;

/**
 *
 * @author Bawb
 */
public class SlerpControl extends InteractionControl {
    
    private boolean            left, right, up, down;
    private AppStateManager    stateManager;
    private Player             player;
    private SimpleApplication  app;
    private Vector3f           camDir = new Vector3f(), camLeft = new Vector3f(), walkDirection = new Vector3f();
    private SlerpCameraManager cameraManager;
    
    public SlerpControl(AppStateManager stateManager) {
        this.stateManager = stateManager;
        player            = stateManager.getState(PlayerManager.class).getPlayer();
        app               = (SimpleApplication) stateManager.getApplication();
        createCameraManager();
    } 
    
    private void createCameraManager() {
        cameraManager = new SlerpCameraManager(app);
    }
    
    private void updateKeys() {
        InteractionManager im = stateManager.getState(GameManager.class).getUtilityManager().getInteractionManager();
        up    = im.getIsPressed("Up");
        down  = im.getIsPressed("Down");
        left  = im.getIsPressed("Left");
        right = im.getIsPressed("Right");
    }
    
    private void chaseMove(float tpf){
        camDir.set(app.getCamera().getDirection()).multLocal(10.0f, 0.0f, 10.0f);
        camLeft.set(app.getCamera().getLeft()).multLocal(10.0f);
        walkDirection.set(0, 0, 0);
        
        if (up) {
            walkDirection.addLocal(camDir);
            player.run();
        }
        else if (down) {
            walkDirection.addLocal(camDir.negate());
            player.run();
        }
        if (left) {
            walkDirection.addLocal(camLeft);
            player.run();
        }
        else if (right) {
            walkDirection.addLocal(camLeft.negate());
            player.run();
        }
        else if (!up && !down) {
            player.idle();
        }
        
        float speedMult;
        speedMult = player.getSpeedMult()/2f;
        
        player.getPhys().setWalkDirection(walkDirection.mult(speedMult));
        if (!up && !down && !left && !right)
        player.getPhys().setViewDirection(camDir);
        else
        player.getPhys().setViewDirection(walkDirection);
        
    }
    
    @Override
    public void update(float tpf) {
        chaseMove(tpf);
        updateKeys();
        cameraManager.update(tpf);
    }
    
}
