package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.drivers.LazyTalonFX;
import frc.lib.drivers.TalonFXFactory;
import frc.robot.Constants;

public class TurretSubsystem extends SubsystemBase {
    private static TurretSubsystem instance = null;

    public static TurretSubsystem getInstance() {
        if (instance == null) {
            instance = new TurretSubsystem();
        }
        return instance;
    }

    private final LazyTalonFX mTurretMotor;  
    private double setpoint;
    // private double targetAngle;
    
    private TurretSubsystem() {
        mTurretMotor = TalonFXFactory.createDefaultFalcon("Turret Motor", 45);
        configureMotor(mTurretMotor, true);
        mTurretMotor.setSelectedSensorPosition(0);
    }   

    private void configureMotor(LazyTalonFX talon, boolean b){
        talon.setInverted(b);
        talon.configVoltageCompSaturation(12.0, Constants.kTimeOutMs);
        talon.enableVoltageCompensation(true);
        talon.setNeutralMode(NeutralMode.Coast);

        talon.config_kF(0, 0.0, Constants.kTimeOutMs);
        talon.config_kP(0, 0.25, Constants.kTimeOutMs);
        talon.config_kI(0, 0, Constants.kTimeOutMs);
        talon.config_kD(0, 0.08, Constants.kTimeOutMs);
    }

    double velocity;

    public void setTurretVelocity(double velocity) {
        this.velocity = velocity;
        mTurretMotor.set(ControlMode.Velocity, velocity);
    }

    public void increaseTurretVelocity(double velocity) {
        this.velocity += velocity;
        mTurretMotor.set(ControlMode.Velocity, this.velocity);
    }

    public void decreaseTurretVelocity(double velocity) {
        this.velocity -= velocity;
        mTurretMotor.set(ControlMode.Velocity, this.velocity);
    }

    public void setPosition(double pos) {
        mTurretMotor.configMotionAcceleration(10000); // accel limit for motion profile, test value
        mTurretMotor.configMotionCruiseVelocity(10000); // velo limit for motion profile, test value
        mTurretMotor.set(ControlMode.MotionMagic, pos);
        setpoint = pos;
    }
    
    public double getSetpoint() {
        return setpoint;
    }
    
    public double getPosition() {
        return mTurretMotor.getSelectedSensorPosition();
    }
    
    public void resetPosition() {
        mTurretMotor.setSelectedSensorPosition(0);
    }

    public void increasePosition(double pos) {
        setPosition(pos + setpoint);
    }

    public void decreasePosition(double pos) {
        setPosition(setpoint - pos);
    }

    // public void setTargetAngle(double angle) {
    //     targetAngle = angle;
    // }

    // public double getTargetAngle() {
    //     return targetAngle;
    // }

    // // counter clockwise +90
    // // clockwise -270
    // public double getAngle() {
    //     double angle = 0;
    //     if (Math.copySign(1, setpoint) < 0) {
    //         // depends on bounds for falcon
	// 	} else if (Math.copySign(1, setpoint) > 0) {
    //         // depends on bounds for falcon
	// 	}
    //     return angle;
    // }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("turret setpoint", getSetpoint());
        SmartDashboard.putNumber("turret position", getPosition());
        SmartDashboard.putNumber("turret velocity", velocity);
        SmartDashboard.putNumber("sensor turret velocity", mTurretMotor.getSelectedSensorVelocity());
        // SmartDashboard.putNumber("turret angle", getAngle());
        // SmartDashboard.putNumber("turret target angle", getTargetAngle());
    }

}
