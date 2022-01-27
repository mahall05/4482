// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class PneumaticSubsystem extends SubsystemBase {
  //private final Compressor c = new Compressor(20);

  private final Solenoid butterflyDrive = new Solenoid(4, null, 20);
  //private final Solenoid butterflyDrive = new Solenoid(4, 20);
  //private final Solenoid butterflyDrive = new Solenoid(4, 20);
  //private final Solenoid butterflyDrive = new Solenoid(4, 20);
  //private final Solenoid butterflyDrive = new Solenoid(4, 20);

  private boolean mecanumEnabled = false;

  private int enableX = 0;

  /** Creates a new PneumaticSubsystem. */
  public PneumaticSubsystem() {
    butterflyDrive.set(false);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void activateMecanum(){
    butterflyDrive.set(!butterflyDrive.get());
    mecanumEnabled = !mecanumEnabled;
    if(mecanumEnabled){
      enableX = 1;
    }
    else{
      enableX = 0;
    }
  }

  public void liftArms(){

  }

  public void extendArms(){
    
  }

  public int getEnableX(){
    return enableX;
  }
}
