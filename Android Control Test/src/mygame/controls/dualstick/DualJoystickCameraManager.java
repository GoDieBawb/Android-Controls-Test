/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.controls.dualstick;

import com.jme3.app.SimpleApplication;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.util.TempVars;
import mygame.GameManager;
import mygame.player.Player;
import mygame.player.PlayerManager;
import mygame.util.UtilityManager;

/**
 *
 * @author Bawb
 */
public class DualJoystickCameraManager {
  
  private SimpleApplication app;
  private Player            player;
  public  Camera            cam;
  private Vector3f          panDir;
  private Vector3f          cameraSpot;
  private Vector3f          cameraLook;
  public  boolean           isPan;
  private boolean           isHunt;
  private boolean           panLeft = false, panRight = false;
  private Vector2f          touchSpot;
  
  public DualJoystickCameraManager(SimpleApplication app) {
       this.app   = app;
       player     = app.getStateManager().getState(PlayerManager.class).getPlayer();
       isHunt     = false;
       initCamera();
  }
  
  //Creates camera
  private void initCamera() {
      app.getFlyByCamera().setEnabled(false);
      app.getFlyByCamera().setDragToRotate(true);
      cam = this.app.getCamera();
      cameraLook = player.getModel().getWorldTranslation().add(0,1f,0);
  }
  
  public void chaseCamMove(float tpf) {
      
      float minDistance   = 3f;
      float heightOffset  = player.getLookHeight()+.5f;

      cameraLook = cameraLook.mult(.7f).add
                    (player.getModel().getWorldTranslation().add(0,heightOffset,0).mult(.3f));
      
      cameraSpot = player.getModel().getWorldTranslation()
                      .add(player.getPhys().getViewDirection()
                          .normalize()
                            .negate().mult(minDistance)).add(0,.5f,0);
      
      slerpLookAt(cameraLook, tpf);
 
      if (cam.getLocation().distance(player.getModel().getWorldTranslation()) > minDistance && !isPan) {
          
          panDir = cameraSpot.subtract(cam.getLocation()).mult(2);
          cam.setLocation(cam.getLocation().addLocal(panDir.mult(tpf)));
          
      }
      
      else if (cam.getLocation().distance(player.getModel().getWorldTranslation()) < minDistance && !isPan) {
          
          panDir = cameraSpot.subtract(cam.getLocation());
          cam.setLocation(cam.getLocation().addLocal(panDir.mult(tpf)));
          isPan = true;
          
      }
      
      else if (isPan) {
      
          cam.setLocation(cam.getLocation().addLocal(panDir.mult(tpf)));
          
          if (cam.getLocation().distance(cameraSpot) < .05f)
            isPan = false; 
          
          else if (cam.getLocation().distance(cameraSpot) > 2f)
            isPan = false;    
      
      }
    
  }
 
  private void slerpLookAt(Vector3f pos, float amount) {
      
      TempVars vars          = TempVars.get();
      Vector3f newDirection  = vars.vect1;
      Vector3f newUp         = vars.vect2;
      Vector3f newLeft       = vars.vect3;
      Quaternion airRotation = vars.quat1;

      newDirection.set(pos).subtractLocal(cam.getLocation()).normalizeLocal();

      newLeft.set(Vector3f.UNIT_Y).crossLocal(newDirection).normalizeLocal();
    
      if (newLeft.equals(Vector3f.ZERO)) {
        
          if (newDirection.x != 0) {
              newLeft.set(newDirection.y, -newDirection.x, 0f);
          } 
        
          else {
              newLeft.set(0f, newDirection.z, -newDirection.y);
          }
        
      }

       newUp.set(newDirection).crossLocal(newLeft).normalizeLocal();

       airRotation.fromAxes(newLeft, newUp, newDirection);
       airRotation.normalizeLocal();

       slerpTo(airRotation, amount*5);

       vars.release();

    }
  
    private void slerpTo(Quaternion quaternion, float amount) {
        Quaternion rotation = cam.getRotation();
        rotation.slerp(quaternion, amount);
        cam.setRotation(rotation);
    }
    
    private void rotateCam(float tpf) {
    
        UtilityManager um = app.getStateManager().getState(GameManager.class).getUtilityManager();
        panLeft           = false;
        panRight          = false;     
        
        if (um.getInteractionManager().getIsPressed("Left1"))
            panRight = true;
        
        else if (um.getInteractionManager().getIsPressed("Right1"))
            panLeft = true;
    
        if (panLeft) {
            Vector3f camDir = cam.getLeft().mult(6);
            cam.setLocation(cam.getLocation().add(camDir.mult(tpf)));
            player.getPhys().setViewDirection(cam.getDirection());
            cam.lookAt(cameraLook, new Vector3f(0,1,0));
        }
    
        else if (panRight) {
            Vector3f camDir = cam.getLeft().negate().mult(6);
            cam.setLocation(cam.getLocation().add(camDir.mult(tpf)));
            player.getPhys().setViewDirection(cam.getDirection());
            cam.lookAt(cameraLook, new Vector3f(0,1,0));
        }
    
    }
    
    public void setTouchSpot(Vector2f newVal) {
        touchSpot = newVal;
    }
    
    public void update(float tpf) {
        
        if(!isHunt){
            rotateCam(tpf);
            chaseCamMove(tpf);
        }
        
    }
    
}
