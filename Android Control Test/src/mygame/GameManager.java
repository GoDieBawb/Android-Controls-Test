/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import mygame.player.PlayerManager;
import mygame.util.UtilityManager;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.scene.Node;

/**
 *
 * @author Bawb
 */
public class GameManager extends AbstractAppState {
    
    private UtilityManager    utilityManager;
    private SimpleApplication app;
    private PlayerManager     playerManager;
    
    public GameManager(SimpleApplication app) {
        this.app = app;
        createPlayerManager();
        createUtilityManager();
        initTestScene();
    }

    private void initTestScene() {
        Node scene = (Node) app.getAssetManager().loadModel("Scenes/TestScene.j3o");
        playerManager.initPersonPlayer(utilityManager.getPhysicsManager().getPhysics());
        app.getRootNode().attachChild(scene);
        utilityManager.getPhysicsManager().addToPhysics(scene);
        utilityManager.getMaterialManager().makeUnshaded(app.getRootNode());
    }
    
    private void createPlayerManager() {
        playerManager = new PlayerManager(app);
        app.getStateManager().attach(playerManager);
    }
    
    private void createUtilityManager() {
         utilityManager = new UtilityManager(app);
    }
    
    public UtilityManager getUtilityManager() {
        return utilityManager;
    }
    
    @Override
    public void update(float tpf) {
        utilityManager.getControlManager().update(tpf);
    }
    
}
