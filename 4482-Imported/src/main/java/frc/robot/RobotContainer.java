// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.autos.AdvancedAuto;
import frc.robot.autos.Autonomous;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.LauncherCommand;
import frc.robot.commands.PneumaticCommand;
import frc.robot.commands.TeleopDrive;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LauncherSubsystem;
import frc.robot.subsystems.PneumaticSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.SPI;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  private final Joystick input = new Joystick(0);
  private final AHRS gyro = new AHRS(SPI.Port.kMXP);
  
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  //  PNEUMATICS
  private final PneumaticSubsystem pneumaticSubsystem = new PneumaticSubsystem();
  private final PneumaticCommand pneumaticCommand = new PneumaticCommand(input, pneumaticSubsystem);

  //  DRIVE
  private final DriveSubsystem driveSubsystem = new DriveSubsystem(input, pneumaticSubsystem, gyro);
  private final TeleopDrive teleopDrive = new TeleopDrive(input, driveSubsystem);

  //  INTAKE
  private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
  private final IntakeCommand intakeCommand = new IntakeCommand(input, intakeSubsystem);

  // LAUNCHER
  private final LauncherSubsystem launcherSubsystem = new LauncherSubsystem();
  private final LauncherCommand launcherCommand = new LauncherCommand(input, launcherSubsystem);

  // AUTONOMOUS PROGRAMS
  private final Autonomous simpleForward = new Autonomous(driveSubsystem, gyro);
  private final AdvancedAuto advancedAuto = new AdvancedAuto(driveSubsystem, pneumaticSubsystem, launcherSubsystem, intakeSubsystem, gyro);

  private final SendableChooser<Command> chooser = new SendableChooser<>();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    driveSubsystem.setDefaultCommand(teleopDrive);
    intakeSubsystem.setDefaultCommand(intakeCommand);
    launcherSubsystem.setDefaultCommand(launcherCommand);
    pneumaticSubsystem.setDefaultCommand(pneumaticCommand);

    chooser.addOption("Simple Autonomous", simpleForward);
    chooser.addOption("Advanced Autonomous", advancedAuto);
    SmartDashboard.putData("Autonomous Choices", chooser);

    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {}

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return chooser.getSelected();
  }
}
