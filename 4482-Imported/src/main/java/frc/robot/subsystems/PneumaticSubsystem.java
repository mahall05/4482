// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PneumaticSubsystem extends SubsystemBase {
  private final Compressor c = new Compressor(20, PneumaticsModuleType.CTREPCM);

  private final Solenoid controlPanelSol = new Solenoid(20, PneumaticsModuleType.CTREPCM, 0);
  private final Solenoid sol4 = new Solenoid(20, PneumaticsModuleType.CTREPCM, 4);
  private final Solenoid butterflyDrive = new Solenoid(20, PneumaticsModuleType.CTREPCM, 5);
  private final Solenoid raiseClimb = new Solenoid(20, PneumaticsModuleType.CTREPCM, 6);
  private final Solenoid climb = new Solenoid(20, PneumaticsModuleType.CTREPCM, 7);

  private boolean mecanumEnabled = false;

  private int enableX = 0;

  /** Creates a new PneumaticSubsystem. */
  public PneumaticSubsystem() {
    //c.setClosedLoopControl(true);
    c.enableDigital();
    
    controlPanelSol.set(false);
    sol4.set(false);
    butterflyDrive.set(false);
    raiseClimb.set(false);
    climb.set(false);
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

  public void raiseClimb(){
    raiseClimb.set(!raiseClimb.get());
  }

  public void extendClimb(){
    climb.set(!climb.get());
  }

  public int getEnableX(){
    return enableX;
  }
}
