/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.util;

import com.jme3.app.state.AppStateManager;
import mygame.controls.ControlGui;
import mygame.controls.InteractionControl;
import mygame.controls.chase.ChaseControl;
import mygame.controls.slerp.SlerpControl;
import mygame.controls.topdown.TopDownControl;

/**
 *
 * @author Bawb
 */
public class ControlManager {
    
    private SlerpControl       slerpControl;
    private ChaseControl       chaseControl;
    private TopDownControl     topDownControl;
    private InteractionControl currentControl;   
    private ControlGui         controlGui;
    
    public ControlManager(AppStateManager stateManager) {
        createSlerpControl(stateManager);
        createChaseControl(stateManager);
        createTopDownControl(stateManager);
        createControlGui(stateManager);
        currentControl = topDownControl;
    }
    
    private void createControlGui(AppStateManager stateManager) {
        controlGui = new ControlGui(stateManager);
    }
    
    private void createTopDownControl(AppStateManager stateManager) {
        topDownControl = new TopDownControl(stateManager);
    }
    
    private void createSlerpControl(AppStateManager stateManager) {
        slerpControl = new SlerpControl(stateManager);
    }
    
    private void createChaseControl(AppStateManager stateManager) {
        chaseControl = new ChaseControl(stateManager);
        chaseControl.setEnabled(false);
    }
    
    public void setCurrentControl(InteractionControl control) {
        currentControl = control;
    }
    
    public void update(float tpf) {
        currentControl.update(tpf);
    }
    
}
