/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.util;

import com.jme3.app.state.AppStateManager;
import mygame.controls.ControlGui;
import mygame.controls.InteractionControl;
import mygame.controls.chase.ChaseControl;
import mygame.controls.dualstick.DualJoystickControl;
import mygame.controls.slerp.SlerpControl;
import mygame.controls.topdown.TopDownControl;

/**
 *
 * @author Bawb
 */
public class ControlManager {
    
    private SlerpControl        slerpControl;
    private ChaseControl        chaseControl;
    private TopDownControl      topDownControl;
    private InteractionControl  currentControl;   
    private DualJoystickControl dualControl;
    private ControlGui          controlGui;
    
    public ControlManager(AppStateManager stateManager, UtilityManager um) {
        createSlerpControl(stateManager);
        createChaseControl(stateManager);
        createTopDownControl(stateManager);
        createDualControl(stateManager);
        currentControl = topDownControl;
    }
    
    public void initControlGui(AppStateManager stateManager, UtilityManager um) {
        controlGui = new ControlGui(stateManager, um);
    }
    
    public ControlGui getControlGui() {
        return controlGui;
    }
    
    private void createDualControl(AppStateManager stateManager) {
        dualControl = new DualJoystickControl(stateManager);
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
    
    public ChaseControl getChaseControl() {
        return chaseControl;
    }
    
    public SlerpControl getSlerpControl() {
        return slerpControl;
    }
    
    public TopDownControl getTopDownControl() {
        return topDownControl;
    }
    
    public DualJoystickControl getDualControl() {
        return dualControl;
    }
    
    public void setCurrentControl(InteractionControl control) {
        
        if(currentControl instanceof ChaseControl)
            ((ChaseControl)currentControl).setEnabled(false);
        
        currentControl = control;
        
        if(currentControl instanceof ChaseControl)
            ((ChaseControl)currentControl).setEnabled(true);
        
    }
    
    public void update(float tpf) {
        currentControl.update(tpf);
    }
    
}
