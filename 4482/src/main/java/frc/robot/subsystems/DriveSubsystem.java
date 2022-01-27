// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSubsystem extends SubsystemBase {
  private final CANSparkMax FRDrive = new CANSparkMax(4, MotorType.kBrushless);
  private final CANSparkMax FLDrive = new CANSparkMax(3, MotorType.kBrushless);
  private final CANSparkMax BRDrive = new CANSparkMax(2, MotorType.kBrushless);
  private final CANSparkMax BLDrive = new CANSparkMax(1, MotorType.kBrushless);

  private final MecanumDrive robotDrive = new MecanumDrive(FLDrive, BLDrive, FRDrive, BRDrive);

  private Joystick input = null;

  private int enableX;

  /** Creates a new DriveSubsystem. */
  public DriveSubsystem(Joystick input, int enableX) {
    this.input = input;
    this.enableX = enableX;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void drive(){
    robotDrive.driveCartesian(input.getX() * enableX, input.getY(), input.getZ() * -0.5);
  }
}
