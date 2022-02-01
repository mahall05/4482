// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autos;

/** Add your docs here. */
public class AutoUtilities {
    public double inchesToTicks(double inches){
        double ticks;
        ticks = (1.0485*inches);
        return ticks;
    }

    public double getProportionalSpeed(double distance, double position){
        double proportionalSpeed = 1 - position / distance;
        if(proportionalSpeed < 0.1){
            return 0.1; // Don't want the robot to be going slower than this
        }
        else{
            return proportionalSpeed;
        }
    }
}
