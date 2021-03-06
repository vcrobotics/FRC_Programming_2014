/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;

/**
 *
 * @author VCS Robotics
 */
public class DriveTrain {
  //private int victor1, victor2, victor3, victor4;
  private Victor leftMotor1; 
  private Victor leftMotor2;
  private Victor rightMotor1;
  private Victor rightMotor2;
  
  private Encoder leftEncoder = new  Encoder(8, 7);
  private Encoder rightEncoder = new  Encoder(3, 4);
  
  public DriveTrain(int victor1, int victor2, int victor3, int victor4){
      leftMotor1 = new Victor(victor3);
      leftMotor2 = new Victor(victor4);
      rightMotor1 = new Victor(victor1);
      rightMotor2 = new Victor(victor2);
  }
  
  
  public int getLeftEncoder(){
      return leftEncoder.getRaw();
  }
  
  public int getRightEncoder(){
      return rightEncoder.getRaw();
  }
  
  public void driveLeft(double power){
        if(power <-1 ){
            power = -1;
        } else if(power > 1){
            power = 1;
        }
        if (power < 0)
            power = -power*power;
        else
            power = power*power;
         leftMotor1.set(power);
         leftMotor2.set(power);
         //rightMotor1.set(power);
     
  }
  
  public void driveRight(double power){
        if(power < -1 ) {
            power = -1;
        } else if(power > 1){
            power = 1;
        }
        if (power < 0)
            power = -power*power;
        else
            power = power*power;
          rightMotor1.set(power);
          rightMotor2.set(power); 
  }
  
  public double limit(double val) {
      return val > 1.0 ? 1.0 : (val < -1.0) ? -1.0 : val;
  }
  
  public void arcadeDrive(double rotateValue, double moveValue, boolean squaredInputs) {
        double leftMotorSpeed;
        double rightMotorSpeed;
        
        moveValue = limit(moveValue);
        rotateValue = limit(rotateValue);
        
        if (squaredInputs) {
            if (moveValue >= 0.0) {
                moveValue = (moveValue * moveValue);
            } else {
                moveValue = -(moveValue * moveValue);
            }
            if (rotateValue >= 0.0) {
                rotateValue = (rotateValue * rotateValue);
            } else {
                rotateValue = -(rotateValue * rotateValue);
            }
        }
        
        if (moveValue > 0.0) {
            if (rotateValue > 0.0) {
                leftMotorSpeed = moveValue - rotateValue;
                rightMotorSpeed = Math.max(moveValue, rotateValue);
            } else {
                leftMotorSpeed = Math.max(moveValue, -rotateValue);
                rightMotorSpeed = moveValue + rotateValue;
            }
        } else {
            if (rotateValue > 0.0) {
                leftMotorSpeed = -Math.max(-moveValue, rotateValue);
                rightMotorSpeed = moveValue + rotateValue;
            } else {
                leftMotorSpeed = moveValue - rotateValue;
                rightMotorSpeed = -Math.max(-moveValue, -rotateValue);
            }
        }
        driveLeft(leftMotorSpeed);
        driveRight(rightMotorSpeed);
    }
}

