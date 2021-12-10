package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase {

    private TalonSRX intakeMotor;
    private TalonSRX outtakeMotor;
    
    private TalonSRX armMotor;
    private TalonSRX handMotor;

    private IntakeSubsystem instance;

    public IntakeSubsystem() {
        intakeMotor = new TalonSRX(Constants.INTAKE);
        outtakeMotor = new TalonSRX(Constants.OUTTAKE);
        // THESE NEED TO BE CONSTANT VARIABLES, NOT NUMBER THINGS (i think i fixed it)
        armMotor = new TalonSRX(Constants.INTAKE_ARM);
        handMotor = new TalonSRX(Constants.INTAKE_HAND);

        armMotor.setNeutralMode(NeutralMode.Brake);
        handMotor.setNeutralMode(NeutralMode.Brake);

    }
    
    public IntakeSubsystem getIntakeInstance() {
        if (instance == null) {
            System.out.println("Creating new Intake Subsystem");
            instance = new IntakeSubsystem();
        }

        return instance;
    }

    //spinTake = "spin intake"
    public void spinTake() {
        intakeMotor.set(TalonSRXControlMode.PercentOutput, 0.3);
    }

    //spoutTake = "spin outtake"
    public void spoutTake() {
        outtakeMotor.set(TalonSRXControlMode.PercentOutput, 0.3);
    }
    
    public void raiseArmMotor() {
        if (getArmTicks() < Constants.ARM_DESTINATION) {
            armMotor.set(TalonSRXControlMode.PercentOutput, 0.2);
        }
    }

    public void lowerArmMotor() {
        if (getArmTicks() > Constants.ARM_ZERO_LIMIT) {
            armMotor.set(TalonSRXControlMode.PercentOutput, -0.2);
        }
    }

    public void lowerHandMotor() {
        if (getHandTicks() > Constants.HAND_ZERO_LIMIT) {
            handMotor.set(TalonSRXControlMode.PercentOutput, -0.2);
        }
    } 

    public void raiseHandMotor() {
        if (getHandTicks() < Constants.HAND_DESTINATION) {
            handMotor.set(TalonSRXControlMode.PercentOutput, 0.2);
        }
    }

    public void brakeHand() {
        handMotor.setNeutralMode(NeutralMode.Brake);
    }

    public void brakeArm() {
        armMotor.setNeutralMode(NeutralMode.Brake);
    }
    @Override
    public void periodic() {

    }

    public double getArmTicks() {
        return armMotor.getSelectedSensorPosition();
    }

    public double getHandTicks() {
        return handMotor.getSelectedSensorPosition();
    }

}