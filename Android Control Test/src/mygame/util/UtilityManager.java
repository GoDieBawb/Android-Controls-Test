/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.util;

import mygame.controls.slerp.SlerpCameraManager;
import com.jme3.app.SimpleApplication;


/**
 *
 * @author Bawb
 */
public class UtilityManager {
    
    private InteractionManager interactionManager;
    private GuiManager         guiManager;
    private PhysicsManager     physicsManager;
    private MaterialManager    materialManager;
    private ControlManager     controlManager;
    private SimpleApplication  app;
    
    public UtilityManager(SimpleApplication app) {
        this.app = app;
        createInteractionManager();
        createGuiManager();
        createPhysicsManager();
        createMaterialManager();
        createControlManager();
    }
    
    private void createControlManager() {
        controlManager = new ControlManager(app.getStateManager(), this);
        controlManager.initControlGui(app.getStateManager(), this);
    }
    
    public ControlManager getControlManager() {
        return controlManager;
    }
    
    private void createInteractionManager() {
        interactionManager = new InteractionManager(app);
    }
    
    public InteractionManager getInteractionManager() {
        return interactionManager;
    }
    
    private void createGuiManager() {
        guiManager = new GuiManager(app);
    }
    
    public GuiManager getGuiManager() {
        return guiManager;
    }
    
    private void createPhysicsManager() {
        physicsManager = new PhysicsManager(app.getStateManager());
    }
    
    public PhysicsManager getPhysicsManager() {
        return physicsManager;
    }
    
    private void createMaterialManager() {
        materialManager = new MaterialManager(app.getAssetManager());
    }
    
    public MaterialManager getMaterialManager() {
        return materialManager;
    }
    
}
