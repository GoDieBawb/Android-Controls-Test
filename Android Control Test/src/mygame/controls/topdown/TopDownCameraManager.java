/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.controls.topdown;

import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppStateManager;
import com.jme3.math.Vector3f;
import mygame.player.Player;
import mygame.player.PlayerManager;

/**
 *
 * @author Bawb
 */
public class TopDownCameraManager {
    
    private SimpleApplication app;
    private Player            player;
    
    public TopDownCameraManager(AppStateManager stateManager) {
        app     = (SimpleApplication) stateManager.getApplication();
        player  = app.getStateManager().getState(PlayerManager.class).getPlayer();
    }
    
    private void topDownCamMove(){
        app.getCamera().setLocation(player.getWorldTranslation().addLocal(0,10,0));
        app.getCamera().lookAt(player.getWorldTranslation().multLocal(1,0,1), new Vector3f(0,1,0));
    }      
    
    public void update(float tpf) {
        topDownCamMove();
    }
    
}
