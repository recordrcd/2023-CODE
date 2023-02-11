package frc.robot;

import com.ctre.phoenix.motorcontrol.can.*;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.cscore.VideoSink;
import edu.wpi.first.cscore.VideoSource.ConnectionStrategy;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;


public class Robot extends TimedRobot {


// DRIVETRAIN
  VictorSP left = new VictorSP(0);
  VictorSP right = new VictorSP(1);
  DifferentialDrive drive = new DifferentialDrive(left, right);
  Joystick joystick = new Joystick(0);

// PNEUMATICS
  Compressor compressor = new Compressor(0, PneumaticsModuleType.CTREPCM); 
  DoubleSolenoid solenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 6, 7);


//ELEVATOR
 Spark horizontal = new Spark(2);
 Spark vertical = new Spark(3);

//cameras
  UsbCamera camera1;
  UsbCamera camera2;
  VideoSink server;


  @Override
  public void robotInit() {
    left.setInverted(true);

    camera1 = CameraServer.startAutomaticCapture(0);
    camera2 = CameraServer.startAutomaticCapture(1);
    server = CameraServer.getServer();

    camera1.setConnectionStrategy(ConnectionStrategy.kKeepOpen);
    camera2.setConnectionStrategy(ConnectionStrategy.kKeepOpen);
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

    drive.arcadeDrive(-joystick.getY(), -joystick.getZ());

  // HORIZONTAL TRACK
    if (joystick.getRawButton(6)) {
      horizontal.set(0.5);
    } else if (joystick.getRawButton(4)) {
      horizontal.set(-0.5);
    } else {
      horizontal.set(0);
    }

  // VERTICAL TRACK
    if (joystick.getRawButton(5)) {
      vertical.set(0.5); 
    } else if (joystick.getRawButton(3)) {
      vertical.set(-0.5); 
    } else {
      vertical.set(0);
    }



  // 12% POWER
    if (joystick.getRawButton(1)) {
      left.set(0.12);
      right.set(0.12);
    } else if (joystick.getRawButton(2)) {
      left.set(-0.12);
      right.set(-0.12);
    }

  // SOLENOIDS
    if (joystick.getRawButton(7)) {
      solenoid.set(DoubleSolenoid.Value.kReverse);
    } else if (joystick.getRawButton(8)) {
      solenoid.set(DoubleSolenoid.Value.kForward);
    } else if (joystick.getRawButton(9)) {
      solenoid.set(DoubleSolenoid.Value.kOff);
    }

  // COMPRESSOR ON/OFF
    if (joystick.getRawButton(10)) {
      compressor.enableDigital();
    } else if (joystick.getRawButton(11)) {
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
