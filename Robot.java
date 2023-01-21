Hi Raymond u handsome man

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.*;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;


public class Robot extends TimedRobot {

// DRIVETRAIN
  WPI_VictorSPX left = new WPI_VictorSPX(0);
  WPI_VictorSPX right = new WPI_VictorSPX(1);
  DifferentialDrive drive = new DifferentialDrive(left, right);
  Joystick joystick = new Joystick(0);


// PNEUMATICS
  Compressor compressor = new Compressor(0, PneumaticsModuleType.CTREPCM); 
  DoubleSolenoid solenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 6, 7);


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

  // 12% POWER
    if (joystick.getRawButton(7)) {
      left.set(0.12);
      right.set(0.12);
    } else if (joystick.getRawButton(8)) {
      left.set(-0.12);
      right.set(-0.12);
    }

  // SOLENOIDS
    if (joystick.getRawButton(3)) {
      solenoid.set(Value.kReverse);;
    } else if (joystick.getRawButton(4)) {
      solenoid.set(Value.kForward);
    }

  // COMPRESSOR ON/OFF
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
