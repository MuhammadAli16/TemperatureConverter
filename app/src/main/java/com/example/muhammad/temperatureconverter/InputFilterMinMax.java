package com.example.muhammad.temperatureconverter;

import android.content.Context;
import android.text.InputFilter;
import android.text.Spanned;
import android.widget.Toast;

// Class that limits the values accpeted for each input(editText)
public class InputFilterMinMax implements InputFilter {

    private double min, max;
    private Temperature selectedEditTxt;
    private Context context;


    public InputFilterMinMax(double min, double max, Temperature selectedEditTxt, Context context) {
        this.min = min;
        this.max = max;
        this.selectedEditTxt = selectedEditTxt;
        this.context = context;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        try {
            // Remove the string out of destination that is to be replaced
            String newVal = dest.toString().substring(0, dstart) + dest.toString().substring(dend, dest.toString().length());
            // Add the new string in
            newVal = newVal.substring(0, dstart) + source.toString() + newVal.substring(dstart, newVal.length());

            if (newVal.equals("-") && selectedEditTxt == Temperature.RANKINE) {
                Toast.makeText(context, "Absolute zero is = 0°Ra",
                        Toast.LENGTH_LONG).show();
                return "";
            }
            if (newVal.equals("-") && selectedEditTxt == Temperature.KELVIN) {
                Toast.makeText(context, "Absolute zero is = 0K",
                        Toast.LENGTH_LONG).show();
                return "";
            }
            if (newVal.equals("-"))
                return null;


            double input = Double.parseDouble(newVal);
            if (isInRange(min, max, input))
                return null;
            else if (selectedEditTxt == Temperature.CELCIUS) {
                Toast.makeText(context, "Absolute zero is = -273.15°C",
                        Toast.LENGTH_LONG).show();
            } else if (selectedEditTxt == Temperature.FAHRENHEIT) {
                Toast.makeText(context, "Absolute zero is = -459.67°F",
                        Toast.LENGTH_LONG).show();
            } else if (selectedEditTxt == Temperature.RANKINE) {
                Toast.makeText(context, "Absolute zero is = 0Ra",
                        Toast.LENGTH_LONG).show();
            } else if (selectedEditTxt == Temperature.REAUMUR) {
                Toast.makeText(context, "Absolute zero is = -218.52°Ré",
                        Toast.LENGTH_LONG).show();
            } else if (selectedEditTxt == Temperature.RANKINE) {
                Toast.makeText(context, "Absolute zero is = 0K",
                        Toast.LENGTH_LONG).show();
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return "";

    }

    private boolean isInRange(double a, double b, double c) {
        return b > a ? c >= a && c <= b : c >= b && c <= a;
    }
}
