package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.cscore.VideoSink;
import edu.wpi.first.cscore.VideoSource.ConnectionStrategy;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;

public class Robot extends TimedRobot {


  // DRIVETRAIN
  VictorSP left = new VictorSP(0);
  VictorSP right = new VictorSP(1);
  DifferentialDrive drive = new DifferentialDrive(left, right);
  Joystick joystick = new Joystick(0);

// CLAW
  private CANSparkMax claw;


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

    claw = new CANSparkMax(1, MotorType.kBrushless);
  }

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {

      drive.arcadeDrive(joystick.getY(), joystick.getZ());
    
   // drive.arcadeDrive(-joystick.getY(), joystick.getX());

   // drive.arcadeDrive(-joystick.getY(), -joystick.getZ());
    // CLAW V2
      if (joystick.getRawButton(1)) {
        claw.set(0.2);
      } else if (joystick.getRawButton(2)) {
        claw.set(-0.2); 
      } else {
        claw.set(0);
      }      
  

    // HORIZONTAL TRACK
      if (joystick.getRawButton(6)) {
        horizontal.set(0.5);
      } else if (joystick.getRawButton(5)) {
        horizontal.set(-0.5);
      } else {
        horizontal.set(0);
      }
  
    // VERTICAL TRACK
      if (joystick.getRawButton(4)) {
        vertical.set(0.5); 
      } else if (joystick.getRawButton(3)) {
        vertical.set(-0.5); 
      } else {
        vertical.set(0);
      }
  
    // 12% POWER
      if (joystick.getRawButton(7)) {
        left.set(0.12);
        right.set(0.12);
      } else if (joystick.getRawButton(8)) {
        left.set(-0.12);
        right.set(-0.12);
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

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}