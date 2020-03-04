/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ExampleSubsystem extends SubsystemBase {

  public WPI_TalonSRX pivot;
  /**
   * Creates a new ExampleSubsystem.
   */
  public ExampleSubsystem() {
    pivot = new WPI_TalonSRX(10);
    pivot.setInverted(InvertType.InvertMotorOutput);
    pivot.setSensorPhase(true);
    pivot.configAllowableClosedloopError(0, 2, 10);
    pivot.configSelectedFeedbackSensor(FeedbackDevice.Analog);
    initialWriteToSmartDashboard();
  }

  public void initialWriteToSmartDashboard(){
    SmartDashboard.putNumber("kP", 0);
    SmartDashboard.putNumber("kI", 0);
    SmartDashboard.putNumber("kD", 0);
    SmartDashboard.putNumber("kF", 0);
    SmartDashboard.putNumber("Setpoint", 100);
    SmartDashboard.putBoolean("Flush", false);

    pivot.config_kP(0, SmartDashboard.getNumber("kP", 0));
    pivot.config_kI(0, SmartDashboard.getNumber("kI", 0));
    pivot.config_kD(0, SmartDashboard.getNumber("kD", 0));
    pivot.config_kF(0, SmartDashboard.getNumber("kF", 0));
    
    }

  public void updateCurrentValueToSD(){
    SmartDashboard.putNumber("Motor Setpoint", pivot.getClosedLoopTarget());
    SmartDashboard.putNumber("Current Enc Pos", pivot.getSelectedSensorPosition());

  }

  public void updateValsFromSD(){
    pivot.config_kP(0, SmartDashboard.getNumber("kP", 0));
    pivot.config_kI(0, SmartDashboard.getNumber("kI", 0));
    pivot.config_kD(0, SmartDashboard.getNumber("kD", 0));
    pivot.config_kF(0, SmartDashboard.getNumber("kF", 0));
    pivot.set(ControlMode.Position, SmartDashboard.getNumber("Setpoint", 100)+9);




    SmartDashboard.putBoolean("Flush", false);

  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if(SmartDashboard.getBoolean("Flush", false)){
      updateValsFromSD();
    }
    updateCurrentValueToSD();
  }
}
