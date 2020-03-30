#pragma config(Motor,  port7,           pulleyMotor,      tmotorNormal, openLoop)
#pragma config(Motor,  port6,           rightMotor,    		tmotorNormal, openLoop, reversed)
#pragma config(Motor,  port3,           leftMotor,     		tmotorNormal, openLoop)
#pragma config(Motor,  port5,           spinnyMotor,      tmotorNormal, openLoop)
#pragma config(Motor,  port4,           clawMotor,      	tmotorNormal, openLoop)
#pragma config(Motor,  port2,           rampMotor,      	tmotorNormal, openLoop)


task main()
{
  while(1 == 1)
  {
    //Driving Motor Control
    motor[leftMotor] = vexRT[Ch3] / 2;
    motor[rightMotor] = vexRT[Ch2] / 2;

    //Ramp Control
    if(vexRT[Btn6U] == 1)
    {
      motor[rampMotor] = 50;
    }
    else if(vexRT[Btn6D] == 1)
    {
    	motor[pulleyMotor] = 40;

    }
    else
    {
      motor[rampMotor] = 0;
      motor[pulleyMotor] = 0;
    }

    //Claw Control
    if(vexRT[Btn5U] == 1)
    {
    	motor[rampMotor] = -50;

    }
    else if(vexRT[Btn5D] == 1)
    {
    	motor[pulleyMotor] = -40;

    }
    else
    {
      motor[rampMotor] = 0;
      motor[pulleyMotor] = 0;
    }

    //Spinny Motor
    if(vexRT[Btn7L] == 1)
    {
      motor[spinnyMotor] = 40;
    }
    else if(vexRT[Btn7R] == 1)
    {
      motor[spinnyMotor] = -40;
    }
    else
    {
      motor[spinnyMotor] = 0;
    }

    //Pulley Motor
    if(vexRT[Btn8L] == 1)
    {
      motor[clawMotor] = 40;
    }
    else if(vexRT[Btn8R] == 1)
    {
      motor[clawMotor] = -40;
    }
    else
    {
      motor[clawMotor] = 0;
    }
  }
}

