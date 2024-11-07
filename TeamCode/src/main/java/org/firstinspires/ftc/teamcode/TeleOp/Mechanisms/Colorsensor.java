package org.firstinspires.ftc.teamcode.TeleOp.Mechanisms;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.SwitchableLight;

public class Colorsensor {
    NormalizedColorSensor colorSensor;
    NormalizedRGBA color;
    float gain = (float) 2.5;
    public void init(HardwareMap hm) {
        colorSensor = hm.get(NormalizedColorSensor.class, "sensor_color");
        colorSensor.setGain(gain);
        if (colorSensor instanceof SwitchableLight) {
            ((SwitchableLight) colorSensor).enableLight(true);
        }

    }
    public void Loop(Gamepad gp1, Gamepad gp2){
        color = colorSensor.getNormalizedColors();
    }
    public float[] getColor() {
        return new float[] {color.red,color.green,color.blue};
    }
    public boolean sensorIsRed() {
        return color.red>100&&color.green<50&&color.blue<50;
    }
}
