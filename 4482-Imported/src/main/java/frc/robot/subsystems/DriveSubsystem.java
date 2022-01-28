// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSubsystem extends SubsystemBase {
  private final CANSparkMax FRDrive = new CANSparkMax(4, MotorType.kBrushless);
  private final CANSparkMax FLDrive = new CANSparkMax(3, MotorType.kBrushless);
  private final CANSparkMax BRDrive = new CANSparkMax(2, MotorType.kBrushless);
  private final CANSparkMax BLDrive = new CANSparkMax(1, MotorType.kBrushless);

  private final MecanumDrive robotDrive = new MecanumDrive(FLDrive, BLDrive, FRDrive, BRDrive);

  private Joystick input = null;
  PneumaticSubsystem ps;

  private int enableX = 0;

  /** Creates a new DriveSubsystem. */
  public DriveSubsystem(Joystick input, PneumaticSubsystem ps) {
    this.input = input;
    this.ps = ps;

    FRDrive.setIdleMode(IdleMode.kBrake);
    FLDrive.setIdleMode(IdleMode.kBrake);
    BRDrive.setIdleMode(IdleMode.kBrake);
    BLDrive.setIdleMode(IdleMode.kBrake);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("FR Encoder", readEncoder());
  }

  public void drive(){
    robotDrive.driveCartesian(input.getX() * ps.getEnableX(), input.getY(), input.getZ() * -0.5);
  }

  public void driveAuto(double xSpeed, double ySpeed, double zSpeed){
    robotDrive.driveCartesian(xSpeed, -1 * ySpeed, zSpeed);
  }

  public double readEncoder(){
    return FRDrive.getEncoder().getPosition();
  }

  public void resetEncoder(){
    FRDrive.getEncoder().setPosition(0);
  }
}
