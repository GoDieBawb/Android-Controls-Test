/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.controls;

import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.TextureKey;
import com.jme3.input.event.MouseButtonEvent;
import com.jme3.math.FastMath;
import com.jme3.math.Vector2f;
import com.jme3.texture.Texture;
import mygame.player.PlayerManager;
import mygame.util.ControlManager;
import mygame.util.InteractionManager;
import mygame.util.UtilityManager;
import tonegod.gui.controls.buttons.ButtonAdapter;
import tonegod.gui.controls.extras.android.Joystick;
import tonegod.gui.core.Screen;
import tonegod.gui.effects.Effect;

/**
 *
 * @author Bawb
 */
public class ControlGui {
    
    private ButtonAdapter   menuButton, slerpButton, chaseButton, topDownButton;
    private Screen          screen;
    private ControlManager  controlManager;
    private Joystick        stick;
    private AppStateManager stateManager;
    private UtilityManager  um;
    
    public ControlGui(AppStateManager stateManager, UtilityManager um) {
        screen            = um.getGuiManager().getScreen();
        controlManager    = um.getControlManager();
        this.stateManager = stateManager;
        this.um           = um;
        createMenuButton();
        createChaseButton();
        createSlerpButton();
        createTopDownButton();
        createJoystick();
    }
    
    private void createJoystick(){
        
        stick = new Joystick(screen, Vector2f.ZERO, (int)(screen.getWidth()/6)) {
            InteractionManager im = um.getInteractionManager();
            
            @Override
            public void show() {
                
                boolean isAndroid = "Dalvik".equals(System.getProperty("java.vm.name"));
                
                //if (!isAndroid)
                    //return;
                
                super.show();
                
            }
            
            @Override
            public void onUpdate(float tpf, float deltaX, float deltaY) {
            
                
                float dzVal = .3f; // Dead zone threshold
            
                if (deltaX < -dzVal) {
                    im.setLeft(true);
                    im.setRight(false);
                }
            
                else if (deltaX > dzVal) {
                    im.setRight(true);
                    im.setLeft(false);
                }
            
                else {
                    im.setRight(false);
                    im.setLeft(false);
                }
            
                if (deltaY < -dzVal) {
                    im.setDown(true);
                    im.setUp(false);
                }
            
                else if (deltaY > dzVal) {
                    im.setDown(false);
                    im.setUp(true);
                }
            
                else {
                    im.setUp(false);
                    im.setDown(false);
                }
            
                app.getStateManager().getState(PlayerManager.class).getPlayer().setSpeedMult((FastMath.abs(deltaY) + FastMath.abs(deltaX)));
            
                }
        
            };
        
        
            TextureKey key = new TextureKey("Textures/barrel.png", false);
            Texture tex = ((SimpleApplication)stateManager.getApplication()).getAssetManager().loadTexture(key);
            stick.setTextureAtlasImage(tex, "x=20|y=20|w=120|h=35");
            stick.getThumb().setTextureAtlasImage(tex, "x=20|y=20|w=120|h=35");
            screen.addElement(stick, true);
            stick.setPosition(screen.getWidth()/10 - stick.getWidth()/2, screen.getHeight() / 10f - stick.getHeight()/5);
            // Add some fancy effects
            Effect fxIn = new Effect(Effect.EffectType.FadeIn, Effect.EffectEvent.Show,.5f);
            stick.addEffect(fxIn);
            Effect fxOut = new Effect(Effect.EffectType.FadeOut, Effect.EffectEvent.Hide,.5f);
            stick.addEffect(fxOut);
            stick.show();
            
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
