// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autos;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LauncherSubsystem;
import frc.robot.subsystems.PneumaticSubsystem;

public class AdvancedAuto extends CommandBase {
  private DriveSubsystem ds;
  private PneumaticSubsystem ps;
  private LauncherSubsystem ls;
  private IntakeSubsystem is;

  private AHRS gyro;

  private AutoUtilities util = new AutoUtilities();

  private int step;
  private double tempTicks;
  private int gyroRange;
  private boolean finished;

  // {forwardSpeed, strafe, rotation, distance}
  public double[][] path = {
    {0.2, 0, 0, 10},
    {0.2, 0, 90, 0},
    {0.2, 0, 0, 10}
  };

  /** Creates a new AdvancedAuto. */
  public AdvancedAuto(DriveSubsystem ds, PneumaticSubsystem ps, LauncherSubsystem ls, IntakeSubsystem is, AHRS gyro) {
    this.ds = ds;
    this.ps = ps;
    this.ls = ls;
    this.is = is;
    this.gyro = gyro;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(ds, ps, ls, is);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    step = 0;
    tempTicks = ds.getPosition();
    finished = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(step < path.length){
      runStep(path[step]);
    }
    else{
      finished = true;
    }
  }

  // TODO ###################  TEST TO SEE WHAT GYRO AXIS NEEDS TO BE USED
  public void runStep(double[] values){
    if(values[3] == 0){     // If the robot isn't supposed to move forward/backward, then it is supposed to rotate
      if(gyro.getYaw() > values[2] + gyroRange || gyro.getYaw() < values[2] - gyroRange){
        double difference = values[2] - gyro.getYaw();
        double rotDirection = difference / Math.abs(difference); // This will return either 1 or -1 to determine which way the robot needs to turn
        ds.driveAuto(0, 0, values[0] * rotDirection);
      }
      else{
        tempTicks = ds.getPosition();
        step++;
      }
    }
    else{
      if(tempTicks - ds.getPosition() < util.inchesToTicks(values[3])){
        ds.driveAuto(0, values[0], 0);
      }
      else{
        tempTicks = ds.getPosition();
        step++;
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finished;
  }
}
