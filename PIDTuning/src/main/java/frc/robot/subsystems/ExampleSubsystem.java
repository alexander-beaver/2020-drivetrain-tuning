/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ExampleSubsystem extends SubsystemBase {

  public TalonFX leftMaster;
  public TalonFX leftFollower;
  public TalonFX rightMaster;
  public TalonFX rightFollower;

  /**
   * Puts default values to SmartDashboard
   */
  public void putToSmartDashboard() {
    SmartDashboard.putBoolean("Flush", false);
    SmartDashboard.putNumber("kP", 0);
    SmartDashboard.putNumber("kI", 0);
    SmartDashboard.putNumber("kD", 0);
    SmartDashboard.putNumber("kF", 0);
    SmartDashboard.putNumber("Setpoint", 0);
  }

  /**
   * Creates a new ExampleSubsystem.
   */
  public ExampleSubsystem() {
    leftMaster = new TalonFX(0);
    leftFollower = new TalonFX(1);
    rightMaster = new TalonFX(15);
    rightFollower = new TalonFX(14);

    if (SmartDashboard.getBoolean("published", false) == false) {
      putToSmartDashboard();
    }
    flush();

  }

  /**
   * Flushes the latest SmartDashboard values to the motors
   */
  public void flush() {
    TalonFX[] motors = { leftMaster, leftFollower, rightMaster, rightFollower };
    for (TalonFX motor : motors) {
      motor.config_kP(0, SmartDashboard.getNumber("kP", 0));
      motor.config_kI(0, SmartDashboard.getNumber("kI", 0));
      motor.config_kD(0, SmartDashboard.getNumber("kD", 0));
      motor.config_kF(0, SmartDashboard.getNumber("kF", 0));
      motor.set(ControlMode.Velocity, SmartDashboard.getNumber("Setpoint", 0));

    }
    SmartDashboard.putBoolean("Flush", false);
  }

  /**
   * Puts the feedback values to Smart Dashboard
   */
  public void putMotorValuesToSmartDashboard() {
    TalonFX[] motors = { leftMaster, leftFollower, rightMaster, rightFollower };
    for (TalonFX motor : motors) {
      String baseName = "Motor " + motor.getDeviceID() + " ";
      SmartDashboard.putNumber(baseName + "target", motor.getClosedLoopTarget());
      SmartDashboard.putNumber(baseName + "velocity", motor.getSelectedSensorVelocity());

    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if (SmartDashboard.getBoolean("Flush", false)) {
      flush();
    }
  }
}
