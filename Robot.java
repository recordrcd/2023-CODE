// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import javax.xml.namespace.QName;

import com.ctre.phoenix.motorcontrol.can.*;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

//front motors
  WPI_VictorSPX left = new WPI_VictorSPX(0);
  WPI_VictorSPX right = new WPI_VictorSPX(1);


  DifferentialDrive drive = new DifferentialDrive(left, right);

  Joystick joystick = new Joystick(0);

//Controller 


//pneumatics
  Compressor compressor = new Compressor(0, PneumaticsModuleType.CTREPCM); 
  DoubleSolenoid solenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 6, 7);


  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    left.setInverted(true);
  }

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {

    compressor.disable(); 

  }

  @Override
  public void teleopPeriodic() {

    drive.tankDrive(joystick.getY(), joystick.getZ());


    if (joystick.getRawButton(3)) {
      solenoid.set(Value.kReverse);;
    } else if (joystick.getRawButton(4)) {
      solenoid.set(Value.kForward);
    }

    if (joystick.getRawButton(5)) {
      compressor.enableDigital();
    } else if (joystick.getRawButton(6)) {
      compressor.disable();
    }

  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}
}
