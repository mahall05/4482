// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {
  private final CANSparkMax Intake = new CANSparkMax(5, MotorType.kBrushless);
  private final CANSparkMax Index = new CANSparkMax(6, MotorType.kBrushless);

  /** Creates a new IntakeSubsystem. */
  public IntakeSubsystem() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void intake(){
    Intake.set(0.5);
    Index.set(0.3);
  }

  public void eject(){
    Intake.set(-0.3);
    Index.set(-0.3);
  }

  public void stop(){
    Intake.set(0);
    Index.set(0);
  }
}
