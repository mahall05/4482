// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
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

  private final RelativeEncoder FREncoder = FRDrive.getEncoder();
  private final RelativeEncoder FLEncoder = FLDrive.getEncoder();
  private final RelativeEncoder BREncoder = BRDrive.getEncoder();
  private final RelativeEncoder BLEncoder = BLDrive.getEncoder();

  private final MecanumDrive robotDrive = new MecanumDrive(FLDrive, BLDrive, FRDrive, BRDrive);

  private Joystick input;
  private PneumaticSubsystem ps;
  private AHRS gyro;

  /** Creates a new DriveSubsystem. */
  public DriveSubsystem(Joystick input, PneumaticSubsystem ps, AHRS gyro) {
    this.input = input;
    this.ps = ps;
    this.gyro = gyro;

    FRDrive.setIdleMode(IdleMode.kBrake);
    FLDrive.setIdleMode(IdleMode.kBrake);
    BRDrive.setIdleMode(IdleMode.kBrake);
    BLDrive.setIdleMode(IdleMode.kBrake);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("FR Encoder Pos", FREncoder.getPosition());
    SmartDashboard.putNumber("FL Encoder Pos", FLEncoder.getPosition());
    SmartDashboard.putNumber("BR Encoder Pos", BREncoder.getPosition());
    SmartDashboard.putNumber("BL Encoder Pos", BLEncoder.getPosition());

    SmartDashboard.putNumber("Gyro Pitch", gyro.getPitch());
    SmartDashboard.putNumber("Gyro Yaw", gyro.getYaw());
    SmartDashboard.putNumber("Gyro Roll", gyro.getRoll());
    SmartDashboard.putNumber("Gyro Angle", gyro.getAngle());
  }

  public void drive(){
    robotDrive.driveCartesian(input.getX() * ps.getEnableX(), input.getY(), input.getZ() * -0.5);
  }

  public void driveAuto(double speed, double strafe, double rotation){
    robotDrive.driveCartesian(strafe, -1 * speed, rotation);
  }

  public double getPosition(){
    return FREncoder.getPosition();
  }

  public void resetEncoders(){
    FREncoder.setPosition(0);
    FLEncoder.setPosition(0);
    BREncoder.setPosition(0);
    BLEncoder.setPosition(0);
  }
}
