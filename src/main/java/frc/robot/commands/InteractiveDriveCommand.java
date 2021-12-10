package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

public class InteractiveDriveCommand extends CommandBase {
    private DrivetrainSubsystem m_drivetrain;
    private IntakeSubsystem m_intake;
    private XboxController xbox = new XboxController(Constants.XBOX_CONTROLLER_PORT);
    

    public InteractiveDriveCommand(DrivetrainSubsystem drivetrain, IntakeSubsystem intake) {
        m_drivetrain = drivetrain;
        m_intake = intake;
        
        addRequirements(drivetrain);
        addRequirements(intake);
    }

    @Override
    public void execute() {
        double leftAxis = xbox.getRawAxis(Constants.XBOX_CONTROLLER_LEFT_AXIS_Y); // are these correct for the remote?
        double rightAxis = xbox.getRawAxis(Constants.XBOX_CONTROLLER_RIGHT_AXIS_Y); // it could be whay the axis are messed up.
        if (xbox.getBumperPressed(Hand.kLeft)) {
            m_intake.lowerHandMotor();
        
        } else if (xbox.getBumperPressed(Hand.kRight)) {
            m_intake.raiseHandMotor();
        } else {
            m_intake.brakeHand();
        }

        double leftPower = xbox.getTriggerAxis(Hand.kLeft);
        double rightPower = xbox.getTriggerAxis(Hand.kRight);

        if (leftPower > 0.1) {
            m_intake.lowerArmMotor();
        } else if (rightPower > 0.1) {
            m_intake.raiseArmMotor();
        } else {
            m_intake.brakeArm();
        }

        if (xbox.getAButton()) {
            m_intake.spinTake();
        } else if (xbox.getBButton()) {
            m_intake.spoutTake();
        }

    
        m_drivetrain.drive(leftAxis,rightAxis);

        
    
    }
        
    }

