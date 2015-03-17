/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.controls;

import com.jme3.app.state.AppStateManager;
import com.jme3.input.event.MouseButtonEvent;
import com.jme3.math.Vector2f;
import mygame.util.ControlManager;
import mygame.util.UtilityManager;
import tonegod.gui.controls.buttons.ButtonAdapter;
import tonegod.gui.core.Screen;

/**
 *
 * @author Bawb
 */
public class ControlGui {
    
    private ButtonAdapter   menuButton, slerpButton, chaseButton, topDownButton;
    private Screen          screen;
    private ControlManager  controlManager;
    
    public ControlGui(AppStateManager stateManager, UtilityManager um) {
        screen            = um.getGuiManager().getScreen();
        controlManager    = um.getControlManager();
        createMenuButton();
        createChaseButton();
        createSlerpButton();
        createTopDownButton();
    }
    
    private void createMenuButton() {
        
        menuButton = new ButtonAdapter(screen, "Menu Button", new Vector2f(12,12)) {
        
            @Override
            public void onButtonMouseLeftUp(MouseButtonEvent evt, boolean toggled) {
                super.onButtonMouseLeftUp(evt, toggled);
                
                if(getText().equals("Menu")) {
                    setText("Exit");
                    slerpButton.show();
                    chaseButton.show();
                    topDownButton.show();
                }
                
                else {
                    exitMenu();
                }
                
            }
            
        };
        
        screen.addElement(menuButton);
        menuButton.setDimensions(screen.getWidth()/10, screen.getHeight()/10);
        menuButton.setPosition(0, screen.getHeight()-menuButton.getHeight());
        menuButton.setText("Menu");
        
    }
    
    private void exitMenu() {
        menuButton.setText("Menu");
        slerpButton.hide();
        chaseButton.hide();
        topDownButton.hide();
    }
    
    private void createTopDownButton() {
        
        topDownButton = new ButtonAdapter(screen, "TopDown Button", new Vector2f(12,12)) {
        
            @Override
            public void onButtonMouseLeftUp(MouseButtonEvent evt, boolean toggled) {
                super.onButtonMouseLeftUp(evt, toggled);
                controlManager.setCurrentControl(controlManager.getTopDownControl());
                exitMenu();
            }
            
        };
        
        topDownButton.setDimensions(screen.getWidth()/10, screen.getHeight()/10);
        topDownButton.setPosition(screen.getWidth()/2 - topDownButton.getWidth()/2, screen.getHeight()/2 - topDownButton.getHeight()/2);
        topDownButton.setText("Top Down");
        screen.addElement(topDownButton);
        topDownButton.hide();
        
    }
    
    private void createSlerpButton() {
        
        slerpButton = new ButtonAdapter(screen, "Slerp Button", new Vector2f(12,12)) {
        
            @Override
            public void onButtonMouseLeftUp(MouseButtonEvent evt, boolean toggled) {
                super.onButtonMouseLeftUp(evt, toggled);
                controlManager.setCurrentControl(controlManager.getSlerpControl());
                exitMenu();
            }
            
        };
        
        slerpButton.setDimensions(screen.getWidth()/10, screen.getHeight()/10);
        slerpButton.setPosition(screen.getWidth()/2 - slerpButton.getWidth()/2, screen.getHeight()/2 - slerpButton.getHeight()/2 - slerpButton.getHeight()*2);
        slerpButton.setText("Slerp");
        screen.addElement(slerpButton);
        slerpButton.hide();
        
    }
    
    private void createChaseButton() {
        
        chaseButton = new ButtonAdapter(screen, "Chase Button", new Vector2f(12,12)) {
        
            @Override
            public void onButtonMouseLeftUp(MouseButtonEvent evt, boolean toggled) {
                super.onButtonMouseLeftUp(evt, toggled);
                controlManager.setCurrentControl(controlManager.getChaseControl());
                exitMenu();
            }
            
        };
        
        chaseButton.setDimensions(screen.getWidth()/10, screen.getHeight()/10);
        chaseButton.setPosition(screen.getWidth()/2 - chaseButton.getWidth()/2, screen.getHeight()/2 - chaseButton.getHeight()/2 + chaseButton.getHeight()*2);
        chaseButton.setText("Chase");
        screen.addElement(chaseButton);
        chaseButton.hide();
        
    }
    
}
