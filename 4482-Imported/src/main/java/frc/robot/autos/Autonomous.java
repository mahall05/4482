// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autos;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class Autonomous extends CommandBase {
  private DriveSubsystem driveSubsystem;
  private AHRS gyro;

  /** Creates a new Autonomous. */
  public Autonomous(DriveSubsystem driveSubsystem, AHRS gyro) {
    this.driveSubsystem = driveSubsystem;
    this.gyro = gyro;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    driveSubsystem.driveAuto(0, 0.2, 0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(driveSubsystem.getPosition() < 10){
      return false;
    }
    else{
      return true;
    }
  }
}
