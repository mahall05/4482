// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LauncherSubsystem extends SubsystemBase {
  private final CANSparkMax FLauncher = new CANSparkMax(7, MotorType.kBrushless);
  private final CANSparkMax BLauncher = new CANSparkMax(8, MotorType.kBrushless);

  /** Creates a new LauncherSubsystem. */
  public LauncherSubsystem() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void launch(){
    FLauncher.set(1);
    BLauncher.set(1);
  }

  public void stop(){
    FLauncher.set(0);
    BLauncher.set(0);
  }
}
