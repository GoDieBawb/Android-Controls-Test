/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.controls;

import com.jme3.app.state.AppStateManager;
import com.jme3.input.event.MouseButtonEvent;
import com.jme3.math.Vector2f;
import mygame.GameManager;
import tonegod.gui.controls.buttons.ButtonAdapter;
import tonegod.gui.core.Screen;

/**
 *
 * @author Bawb
 */
public class ControlGui {
    
    private ButtonAdapter   menuButton, slerpButton, chaseButton, topDownButton;
    private Screen          screen;
    private AppStateManager stateManager;
    
    public ControlGui(AppStateManager stateManager) {
        this.stateManager = stateManager;
        screen            = stateManager.getState(GameManager.class).getUtilityManager().getGuiManager().getScreen();
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
            }
            
        };
        
        screen.addElement(menuButton);
        menuButton.setDimensions(screen.getWidth()/10, screen.getHeight()/10);
        menuButton.setPosition(screen.getWidth()/2,screen.getHeight()/2);
        
    }
    
    private void createTopDownButton() {
        
        topDownButton = new ButtonAdapter(screen, "TopDown Button", new Vector2f(12,12)) {
        
            @Override
            public void onButtonMouseLeftUp(MouseButtonEvent evt, boolean toggled) {
                super.onButtonMouseLeftUp(evt, toggled);
            }
            
        };
        
        topDownButton.setDimensions(screen.getWidth()/10, screen.getHeight()/10);
        
    }
    
    private void createSlerpButton() {
        
        slerpButton = new ButtonAdapter(screen, "Slerp Button", new Vector2f(12,12)) {
        
            @Override
            public void onButtonMouseLeftUp(MouseButtonEvent evt, boolean toggled) {
                super.onButtonMouseLeftUp(evt, toggled);
            }
            
        };
        
        slerpButton.setDimensions(screen.getWidth()/10, screen.getHeight()/10);
        
    }
    
    private void createChaseButton() {
        
        chaseButton = new ButtonAdapter(screen, "Chase Button", new Vector2f(12,12)) {
        
            @Override
            public void onButtonMouseLeftUp(MouseButtonEvent evt, boolean toggled) {
                super.onButtonMouseLeftUp(evt, toggled);
            }
            
        };
        
        chaseButton.setDimensions(screen.getWidth()/10, screen.getHeight()/10);
        
    }
    
}
