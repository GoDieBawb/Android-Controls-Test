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
import tonegod.gui.core.Screen;
import tonegod.gui.effects.Effect;

/**
 *
 * @author Bawb
 */
public class ControlGui {
    
    private ButtonAdapter   menuButton, slerpButton, chaseButton, topDownButton, dualButton;
    private Screen          screen;
    private ControlManager  controlManager;
    private MyJoystick      leftStick;
    private MyJoystick      rightStick;
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
        createLeftStick();
        createRightStick();
        createDualButton();
    }
    
    private void createLeftStick(){
        
        leftStick = new MyJoystick(screen, Vector2f.ZERO, (int)(screen.getWidth()/6)) {
            
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
        leftStick.setTextureAtlasImage(tex, "x=20|y=20|w=120|h=35");
        leftStick.getThumb().setTextureAtlasImage(tex, "x=20|y=20|w=120|h=35");
        screen.addElement(leftStick, true);
        leftStick.setPosition(screen.getWidth()/10 - leftStick.getWidth()/2, screen.getHeight() / 10f - leftStick.getHeight()/5);
        // Add some fancy effects
        Effect fxIn = new Effect(Effect.EffectType.FadeIn, Effect.EffectEvent.Show,.5f);
        leftStick.addEffect(fxIn);
        Effect fxOut = new Effect(Effect.EffectType.FadeOut, Effect.EffectEvent.Hide,.5f);
        leftStick.addEffect(fxOut);
        leftStick.show();
            
    }
    
    private void createRightStick(){
        
        rightStick = new MyJoystick(screen, Vector2f.ZERO, (int)(screen.getWidth()/6)) {
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
                    im.setLeft1(true);
                    im.setRight1(false);
                }
            
                else if (deltaX > dzVal) {
                    im.setRight1(true);
                    im.setLeft1(false);
                }
            
                else {
                    im.setRight1(false);
                    im.setLeft1(false);
                }
            
                if (deltaY < -dzVal) {
                    im.setDown1(true);
                    im.setUp1(false);
                }
            
                else if (deltaY > dzVal) {
                    im.setDown1(false);
                    im.setUp1(true);
                }
            
                else {
                    im.setUp1(false);
                    im.setDown1(false);
                }
            
            }
        
        };
        
        
        TextureKey key = new TextureKey("Textures/barrel.png", false);
        Texture tex = ((SimpleApplication)stateManager.getApplication()).getAssetManager().loadTexture(key);
        rightStick.setTextureAtlasImage(tex, "x=20|y=20|w=120|h=35");
        rightStick.getThumb().setTextureAtlasImage(tex, "x=20|y=20|w=120|h=35");
        screen.addElement(rightStick, true);
        rightStick.setPosition(screen.getWidth()*.9f - rightStick.getWidth()/2, screen.getHeight() / 10f - rightStick.getHeight()/5);
        // Add some fancy effects
        Effect fxIn = new Effect(Effect.EffectType.FadeIn, Effect.EffectEvent.Show,.5f);
        rightStick.addEffect(fxIn);
        Effect fxOut = new Effect(Effect.EffectType.FadeOut, Effect.EffectEvent.Hide,.5f);
        rightStick.addEffect(fxOut);
        rightStick.hide();
            
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
                    dualButton.show();
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
        dualButton.hide();
    }
    
    private void createTopDownButton() {
        
        topDownButton = new ButtonAdapter(screen, "TopDown Button", new Vector2f(12,12)) {
        
            @Override
            public void onButtonMouseLeftUp(MouseButtonEvent evt, boolean toggled) {
                super.onButtonMouseLeftUp(evt, toggled);
                controlManager.setCurrentControl(controlManager.getTopDownControl());
                rightStick.hide();
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
                rightStick.hide();
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
                rightStick.hide();
                exitMenu();
            }
            
        };
        
        chaseButton.setDimensions(screen.getWidth()/10, screen.getHeight()/10);
        chaseButton.setPosition(screen.getWidth()/2 - chaseButton.getWidth()/2, screen.getHeight()/2 - chaseButton.getHeight()/2 + chaseButton.getHeight()*2);
        chaseButton.setText("Chase");
        screen.addElement(chaseButton);
        chaseButton.hide();
        
    }
    
    private void createDualButton() {
        
        dualButton = new ButtonAdapter(screen, "Dual Button", new Vector2f(12,12)) {
        
            @Override
            public void onButtonMouseLeftUp(MouseButtonEvent evt, boolean toggled) {
                super.onButtonMouseLeftUp(evt, toggled);
                controlManager.setCurrentControl(controlManager.getDualControl());
                rightStick.show();
                exitMenu();
            }
            
        };
        
        dualButton.setDimensions(screen.getWidth()/10, screen.getHeight()/10);
        dualButton.setPosition(screen.getWidth()/2 - dualButton.getWidth()/2 - dualButton.getWidth()*2, screen.getHeight()/2 - dualButton.getHeight()/2 - dualButton.getHeight()*2);
        dualButton.setText("Dual Stick");
        screen.addElement(dualButton);
        dualButton.hide();
        
    }
    
}
