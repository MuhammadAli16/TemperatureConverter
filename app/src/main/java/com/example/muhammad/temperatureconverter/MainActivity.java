package com.example.muhammad.temperatureconverter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    // Current selected value
    private String selectedEditTxt = "";
    // Value retrieved from sharedPref
    private String decimalPlaces = null;

    // Class that limits the values accpeted for each input(editText)
    public class InputFilterMinMax implements InputFilter {

        private double min, max;

        public InputFilterMinMax(double min, double max) {
            this.min = min;
            this.max = max;
        }

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            try {
                // Remove the string out of destination that is to be replaced
                String newVal = dest.toString().substring(0, dstart) + dest.toString().substring(dend, dest.toString().length());
                // Add the new string in
                newVal = newVal.substring(0, dstart) + source.toString() + newVal.substring(dstart, newVal.length());

                if (newVal.equals("-") && selectedEditTxt.equals("3")) {
                    Toast.makeText(MainActivity.this, "Absolute zero is = 0°Ra",
                            Toast.LENGTH_LONG).show();
                    return "";
                }
                if (newVal.equals("-") && selectedEditTxt.equals("5")) {
                    Toast.makeText(MainActivity.this, "Absolute zero is = 0K",
                            Toast.LENGTH_LONG).show();
                    return "";
                }
                if (newVal.equals("-"))
                    return null;


                double input = Double.parseDouble(newVal);
                if (isInRange(min, max, input))
                    return null;
                else if (selectedEditTxt.equals("1")) {
                    Toast.makeText(MainActivity.this, "Absolute zero is = -273.15°C",
                            Toast.LENGTH_LONG).show();
                } else if (selectedEditTxt.equals("2")) {
                    Toast.makeText(MainActivity.this, "Absolute zero is = -459.67°F",
                            Toast.LENGTH_LONG).show();
                } else if (selectedEditTxt.equals("3")) {
                    Toast.makeText(MainActivity.this, "Absolute zero is = 0Ra",
                            Toast.LENGTH_LONG).show();
                } else if (selectedEditTxt.equals("4")) {
                    Toast.makeText(MainActivity.this, "Absolute zero is = -218.52°Ré",
                            Toast.LENGTH_LONG).show();
                } else if (selectedEditTxt.equals("5")) {
                    Toast.makeText(MainActivity.this, "Absolute zero is = 0K",
                            Toast.LENGTH_LONG).show();
                }
            } catch (NumberFormatException nfe) {
            }
            return "";

        }

        private boolean isInRange(double a, double b, double c) {
            return b > a ? c >= a && c <= b : c >= b && c <= a;
        }
    }

    public void setColours(String x){
        int arr = Integer.parseInt(x) - 1;

        for (int z = 0; z < textViewArray.length; z++){

            if (arr == z){
                textViewArray[z].setTextColor(Color.parseColor(accentColour));
            } else {
                textViewArray[z].setTextColor(Color.BLACK);
            }

        }

    }

    //
    private TextView[] textViewArray = new TextView[4];
    private final String accentColour = "#A5D6A7";

    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        final NumberFormat nm = NumberFormat.getInstance();

        final EditText celsiusValue = (EditText) findViewById(R.id.celciusText);
        final EditText far = (EditText) findViewById(R.id.farText);
        final EditText rank = (EditText) findViewById(R.id.rankineText);
        final EditText rem = (EditText) findViewById(R.id.remText);
        final EditText kelvin = (EditText) findViewById(R.id.kelvinText);

//
        textViewArray = new TextView[] {
        (TextView) findViewById(R.id.celciusSymbol),
        (TextView) findViewById(R.id.farSymbol),
        (TextView) findViewById(R.id.rankineSymbol),
        (TextView) findViewById(R.id.remSymbol),
        (TextView) findViewById(R.id.kelvinSymbol)
        };
        //


//        final TextView celText = (TextView) findViewById(R.id.celciusSymbol);
//        final TextView farText = (TextView) findViewById(R.id.farSymbol);
//        final TextView rankineText = (TextView) findViewById(R.id.rankineSymbol);
//        final TextView remText = (TextView) findViewById(R.id.remSymbol);
//        final TextView kelvinText = (TextView) findViewById(R.id.kelvinSymbol);


        // cel.addTextChangedListener(this);

        celsiusValue.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View arg0, boolean hasFocus) {
                selectedEditTxt = "1";

                // Set Celcius
                celsiusValue.setFilters(new InputFilter[]{new InputFilterMinMax(-273.15, Double.MAX_VALUE)});
                //getWindow().setNavigationBarColor(Color.parseColor("#A5D6A7"));

                setColours(selectedEditTxt);
//                celValue.setTextColor(Color.parseColor(accentColour));
//
//                //reset others to black
//                farValue.setTextColor(Color.BLACK);
//                rankineValue.setTextColor(Color.BLACK);
//                remValue.setTextColor(Color.BLACK);
//                kelvinValue.setTextColor(Color.BLACK);
            }

        });

        far.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                selectedEditTxt = "2";
                setColours(selectedEditTxt);
                celsiusValue.setFilters(new InputFilter[]{new InputFilterMinMax(-459.67, Double.MAX_VALUE)});

                // Set fahrenheit
//                farText.setTextColor(Color.parseColor(accentColour));
//
//                // reset others
//                celText.setTextColor(Color.BLACK);
//                // celsiusValue.setFilters(new InputFilter[]{});
//                rankineText.setTextColor(Color.BLACK);
//                remText.setTextColor(Color.BLACK);
//                kelvinText.setTextColor(Color.BLACK);
            }
        });

        rank.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                selectedEditTxt = "3";
                // Set rankine
                rank.setFilters(new InputFilter[]{new InputFilterMinMax(0, Double.MAX_VALUE), new InputFilter.LengthFilter(10)});
                setColours(selectedEditTxt);
//                rankineText.setTextColor(Color.parseColor(accentColour));
//
//                // reset others
//                celText.setTextColor(Color.BLACK);
//                farText.setTextColor(Color.BLACK);
//                remText.setTextColor(Color.BLACK);
//                kelvinText.setTextColor(Color.BLACK);
            }
        });
        rem.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                selectedEditTxt = "4";
                // Set rem
                rem.setFilters(new InputFilter[]{new InputFilterMinMax(-218.52, Double.MAX_VALUE), new InputFilter.LengthFilter(10)});
                setColours(selectedEditTxt);
//                remText.setTextColor(Color.parseColor(accentColour));
//
//                // reset others
//                celText.setTextColor(Color.BLACK);
//                farText.setTextColor(Color.BLACK);
//                rankineText.setTextColor(Color.BLACK);
//                kelvinText.setTextColor(Color.BLACK);
            }
        });

        kelvin.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                selectedEditTxt = "5";
                // Set rem
                kelvin.setFilters(new InputFilter[]{new InputFilterMinMax(0, Double.MAX_VALUE), new InputFilter.LengthFilter(10)});
                setColours(selectedEditTxt);
//                kelvinText.setTextColor(Color.parseColor(accentColour));
//
//                // reset others
//                celText.setTextColor(Color.BLACK);
//                farText.setTextColor(Color.BLACK);
//                rankineText.setTextColor(Color.BLACK);
//                remText.setTextColor(Color.BLACK);
            }
        });



        celsiusValue.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                String tempValue = celsiusValue.getText().toString();
                if (!tempValue.equals("") && !tempValue.equals(".") && !tempValue.equals("-") && selectedEditTxt.equals("1")) {
                    double temp = Double.parseDouble(tempValue);
                    // fahrenheit
                    double outputFar = (temp * 1.8) + 32;
                    far.setText(stripDecimal(outputFar) + "");
                    // rankine
                    double outputRank = temp * 1.8 + 32 + 459.67;
                    rank.setText(stripDecimal(outputRank) + "");
                    // Réaumur
                    double outputRéaumur = temp * 0.8;
                    rem.setText(stripDecimal(outputRéaumur) + "");
                    //kelvin
                    double outputKelvin = temp + + 273.15;
                    kelvin.setText(stripDecimal(outputKelvin) + "");
                } else if (selectedEditTxt.equals("1")) {
                    far.setText("");
                    rank.setText("");
                    rem.setText("");
                    kelvin.setText("");
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });

        far.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                String tempValue = far.getText().toString();

                if (!tempValue.equals("") && !tempValue.equals(".") && !tempValue.equals("-") && selectedEditTxt.equals("2")) {

                    double temp = Double.parseDouble(tempValue);

                    //Celcius
                    double outputCelcius = (temp - 32) / 1.8;
                    celsiusValue.setText(nm.format(outputCelcius).replaceAll(",", "") + "");
                    //Rankine
                    double outputRankine = (temp + 459.67);
                    rank.setText(stripDecimal(outputRankine) + "");
                    //Rem
                    double outputRem = (temp - 32) / 2.25;
                    rem.setText(stripDecimal(outputRem) + "");
                    //Kelvin
                    double outputKelvin = (temp + 459.67) / 1.8;
                    kelvin.setText(stripDecimal(outputKelvin) + "");
                } else if (selectedEditTxt.equals("2")) {
                    celsiusValue.setText("");
                    rank.setText("");
                    rem.setText("");
                    kelvin.setText("");
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });

        rank.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                String tempValue = rank.getText().toString();
                if (!tempValue.equals("") && !tempValue.equals(".") && !tempValue.equals("-") && selectedEditTxt.equals("3")) {
                    double temp = Double.parseDouble(tempValue);
                    //Celcius
                    double outputCelcius = (temp - 32 - 459.67) / 1.8;
                    celsiusValue.setText(stripDecimal(outputCelcius) + "");
                    //fahrenheit
                    double outputFahrenheit = (temp - 459.67);
                    far.setText(stripDecimal(outputFahrenheit) + "");
                    //reamur
                    double outputReamur = (temp-491.67) * 4/9;
                    rem.setText(stripDecimal(outputReamur) + "");
                    //kelvin
                    double outputKelvin = temp * (5/9);
                    kelvin.setText(stripDecimal(outputKelvin) + "");
                } else if (selectedEditTxt.equals("3")) {
                    celsiusValue.setText("");
                    far.setText("");
                    rem.setText("");
                    kelvin.setText("");
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });

        rem.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                String tempValue = rem.getText().toString();
                if (!tempValue.equals("") && !tempValue.equals(".") && !tempValue.equals("-") && selectedEditTxt.equals("4")) {
                    double temp = Double.parseDouble(tempValue);
                    //Celcius
                    //double outputCelcius = 0;
                    BigDecimal bd1 = new BigDecimal("1.25");
                    BigDecimal outputCelcius = bd1.multiply(BigDecimal.valueOf(temp));
                    //outputCelcius = temp * new BigDecimal("1.25");
                    celsiusValue.setText(stripDecimal(outputCelcius.doubleValue()) + "");
                    System.out.println(stripDecimal(outputCelcius.doubleValue()));
                    System.out.println((outputCelcius));
                    //fahrenheit
                    double outputFahrenheit = 0;
                    outputFahrenheit = temp * 2.25 + 32;
                    far.setText(stripDecimal(outputFahrenheit) + "");
                    //rankine
                    double outputRankine = 0;
                    outputRankine = temp * 2.25 + 32 + 459.67;
                    rank.setText(stripDecimal(outputRankine) + "");
                    //kelvin
                    double outputKelvin = temp * 5/4 + 273.15;
                    kelvin.setText(stripDecimal(outputKelvin) + "");
                } else if (selectedEditTxt.equals("4")) {
                    celsiusValue.setText("");
                    far.setText("");
                    rank.setText("");
                    kelvin.setText("");
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });

        kelvin.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                String tempValue = kelvin.getText().toString();
                if (!tempValue.equals("") && !tempValue.equals(".") && !tempValue.equals("-") && selectedEditTxt.equals("5")) {
                    double temp = Double.parseDouble(tempValue);
                    //Celcius
                    double outputCelcius = temp - 273.15;
                    celsiusValue.setText(stripDecimal(outputCelcius) + "");
                    //fahrenheit
                    double outputFahrenheit = temp * 1.8 - 459.67;
                    far.setText(stripDecimal(outputFahrenheit) + "");
                    //rankine
                    double outputRankine = temp * 1.8;
                    rank.setText(stripDecimal(outputRankine) + "");
                    //rem
                    double outputRem = (temp - 273.15) * 0.8;
                    rem.setText(stripDecimal(outputRem) + "");
                } else if (selectedEditTxt.equals("5")) {
                    celsiusValue.setText("");
                    far.setText("");
                    rank.setText("");
                    rem.setText("");
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });

    }

    // rounds numbers to x decimal places - depending on user request
    public Double stripDecimal(Double temp){

        Double strippedTemp;
        switch(decimalPlaces)
        {
            case "1":
                strippedTemp = Math.round(temp * 10d) / 10d;
                break;
            case "2":
                strippedTemp = (double)Math.round(temp * 100d) / 100d;
                break;
            case "3":
                strippedTemp = (double)Math.round(temp * 1000d) / 1000d;
                break;
            case "4":
                strippedTemp = (double)Math.round(temp * 10000d) / 10000d;
                break;
            default:
                strippedTemp = temp;
                break;


        }

        return strippedTemp;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume(){
    super.onResume();
    // Get value or user selected decimal places
    final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
    decimalPlaces = prefs.getString("decimals", "");
    }
}
