#pragma config(Motor,  port2,           rightMotor,    tmotorNormal, openLoop, reversed)
#pragma config(Motor,  port3,           leftMotor,     tmotorNormal, openLoop)
#pragma config(Motor,  port6,           armMotor,      tmotorNormal, openLoop)
#pragma config(Motor,  port7,           clawMotor,      tmotorNormal, openLoop)

task main()
{
  while(1 == 1)
  {
    //Driving Motor Control
    motor[leftMotor] = vexRT[Ch3] / 2;
    motor[rightMotor] = vexRT[Ch2] / 2;

    //Arm Control
    if(vexRT[Btn6U] == 1)
    {
      motor[armMotor] = 50;
    }
    else if(vexRT[Btn6D] == 1)
    {
      motor[armMotor] = -50;
    }
    else
    {
      motor[armMotor] = 0;
    }

    //Claw Control
    if(vexRT[Btn5U] == 1)
    {
      motor[clawMotor] = 40;
    }
    else if(vexRT[Btn5D] == 1)
    {
      motor[clawMotor] = -40;
    }
    else
    {
      motor[clawMotor] = 0;
    }
  }
}

