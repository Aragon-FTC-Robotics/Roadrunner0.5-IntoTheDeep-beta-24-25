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
    public float[] redHigher = {255,50,50}; //CLOSER TO WHITE
    public float[] redLower = {100,0,0}; //CLOSER TO BLACK
    public float[] yellowHigher = {255,255,50}; //etc
    public float[] yellowLower = {100,100,0};
    public float[] blueHigher = {50,50,255};
    public float[] blueLower = {0,0,100};
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
        return colorInRange(new float[] {color.red,color.green,color.blue},redLower,redHigher);
    }
    public boolean sensorIsYellow() {
        return colorInRange(new float[] {color.red,color.green,color.blue},yellowLower,yellowHigher);
    }
    public boolean sensorIsBlue() {
        return colorInRange(new float[] {color.red,color.green,color.blue},blueLower,blueHigher);
    }
    public boolean colorInRange(float[] color, float[] min, float[] max) {
        return
                min[0] < color[0] && color[0] < max[0] && //Red is within min and max range
                min[1] < color[1] && color[1] < max[1] && //Green is within min and max range
                min[2] < color[2] && color[2] < max[2];   //brue is sithin the range,
    }
}
