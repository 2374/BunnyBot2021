package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import frc.robot.Constants;

// Bloons // Taloon // Balon // Telon // Taron // Nalon // Calon // Waton // Wa-POW! // big spinny
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;



public class DrivetrainSubsystem extends SubsystemBase{
    private WPI_TalonFX leftBackMotor;
    private WPI_TalonFX rightBackMotor;
    private WPI_TalonFX leftFrontMotor;
    private WPI_TalonFX rightFrontMotor;

    private SpeedControllerGroup left;
    private SpeedControllerGroup right;

    private DrivetrainSubsystem instance;
    public boolean brakeEnabled = false;
    private DifferentialDrive diffyDrive;

    public DrivetrainSubsystem() {
        leftBackMotor = new WPI_TalonFX(Constants.TALON_DRIVE_BACK_LEFT);
        rightBackMotor = new WPI_TalonFX(Constants.TALON_DRIVE_BACK_RIGHT);
        leftFrontMotor = new WPI_TalonFX(Constants.TALON_DRIVE_FRONT_LEFT);
        rightFrontMotor = new WPI_TalonFX(Constants.TALON_DRIVE_FRONT_RIGHT);

        leftBackMotor.setInverted(true);
        leftFrontMotor.setInverted(true);

        // left = new SpeedControllerGroup(new WPI_TalonFX(), new WPI_TalonFX());

        leftBackMotor.follow(leftFrontMotor); //.Sendable error
        rightBackMotor.follow(rightFrontMotor);

    
        left = new SpeedControllerGroup(leftFrontMotor, leftBackMotor);
        right = new SpeedControllerGroup(rightFrontMotor, rightBackMotor);

        diffyDrive = new DifferentialDrive(left, right);
        //diffyDrive = new DifferentialDrive(leftFrontMotor, rightFrontMotor);
    }

    public DrivetrainSubsystem getDriveTrainInstance() {
        if (instance == null) {
            System.out.println("Creating new Drive Train Subsystem");
            instance = new DrivetrainSubsystem();
        }

        return instance;
    }
    
    @Override
    public void periodic() {

    }

    public void drive(double left, double right){
        diffyDrive.tankDrive(left, right);
    }

    public void setToCoastMode() {
        leftFrontMotor.setNeutralMode(NeutralMode.Coast);
        rightFrontMotor.setNeutralMode(NeutralMode.Coast);
        leftBackMotor.setNeutralMode(NeutralMode.Coast);
        rightBackMotor.setNeutralMode(NeutralMode.Coast);
        brakeEnabled = false;
    }

    public void setToBrakeMode() {
        leftFrontMotor.setNeutralMode(NeutralMode.Brake);
        rightFrontMotor.setNeutralMode(NeutralMode.Brake);
        leftBackMotor.setNeutralMode(NeutralMode.Brake);
        rightBackMotor.setNeutralMode(NeutralMode.Brake);
        brakeEnabled = true;
    }

    public boolean isBrakeEnabled() { return brakeEnabled; }




}