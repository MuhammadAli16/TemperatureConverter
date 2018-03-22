package com.example.muhammad.temperatureconverter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {

    private Temperature selectedEditTxt;
    private String decimalPlaces = null;

    private final String accentColour = "#A5D6A7";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final EditText celsiusValue = (EditText) findViewById(R.id.celciusText);
        final EditText far = (EditText) findViewById(R.id.farText);
        final EditText rank = (EditText) findViewById(R.id.rankineText);
        final EditText rem = (EditText) findViewById(R.id.remText);
        final EditText kelvin = (EditText) findViewById(R.id.kelvinText);

        celsiusValue.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View arg0, boolean hasFocus) {
                selectedEditTxt = Temperature.CELCIUS;
                celsiusValue.setFilters(new InputFilter[]{new InputFilterMinMax(-273.15, Double.MAX_VALUE, selectedEditTxt, MainActivity.this), new InputFilter.LengthFilter(10)});
            }

        });

        far.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                selectedEditTxt = Temperature.FAHRENHEIT;
                far.setFilters(new InputFilter[]{new InputFilterMinMax(-459.67, Double.MAX_VALUE, selectedEditTxt, MainActivity.this), new InputFilter.LengthFilter(10)});
            }
        });

        rank.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                selectedEditTxt = Temperature.RANKINE;
                rank.setFilters(new InputFilter[]{new InputFilterMinMax(0, Double.MAX_VALUE, selectedEditTxt, MainActivity.this), new InputFilter.LengthFilter(10)});
            }
        });
        rem.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                selectedEditTxt = Temperature.REAUMUR;
                rem.setFilters(new InputFilter[]{new InputFilterMinMax(-218.52, Double.MAX_VALUE, selectedEditTxt, MainActivity.this), new InputFilter.LengthFilter(10)});
            }
        });

        kelvin.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                selectedEditTxt = Temperature.KELVIN;
                kelvin.setFilters(new InputFilter[]{new InputFilterMinMax(0, Double.MAX_VALUE, selectedEditTxt, MainActivity.this), new InputFilter.LengthFilter(10)});
            }
        });



        celsiusValue.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                String tempValue = celsiusValue.getText().toString();
                if (!tempValue.equals("") && !tempValue.equals(".") && !tempValue.equals("-") && selectedEditTxt == Temperature.CELCIUS) {
                    double temp = Double.parseDouble(tempValue);
                    // fahrenheit
                    double outputFar = (temp * 1.8) + 32;
                    far.setText(String.valueOf(stripDecimal(outputFar)));
                    // rankine
                    double outputRank = temp * 1.8 + 32 + 459.67;
                    rank.setText(String.valueOf(stripDecimal(outputRank)));
                    // Réaumur
                    double outputRéaumur = temp * 0.8;
                    rem.setText(String.valueOf(stripDecimal(outputRéaumur)));
                    //kelvin
                    double outputKelvin = temp + 273.15;
                    kelvin.setText(String.valueOf(stripDecimal(outputKelvin)));
                } else if (selectedEditTxt == Temperature.CELCIUS) {
                    far.setText("");
                    rank.setText("");
                    rem.setText("");
                    kelvin.setText("");
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        far.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                String tempValue = far.getText().toString();

                if (!tempValue.equals("") && !tempValue.equals(".") && !tempValue.equals("-") && selectedEditTxt == Temperature.FAHRENHEIT) {

                    double temp = Double.parseDouble(tempValue);

                    //Celcius
                    double outputCelcius = (temp - 32) / 1.8;
                    celsiusValue.setText(String.valueOf((stripDecimal(outputCelcius))));
                    //Rankine
                    double outputRankine = (temp + 459.67);
                    rank.setText(String.valueOf(stripDecimal(outputRankine)));
                    //Rem
                    double outputRem = (temp - 32) / 2.25;
                    rem.setText(String.valueOf(stripDecimal(outputRem)));
                    //Kelvin
                    double outputKelvin = (temp + 459.67) / 1.8;
                    kelvin.setText(String.valueOf(stripDecimal(outputKelvin)));
                } else if (selectedEditTxt == Temperature.FAHRENHEIT) {
                    celsiusValue.setText("");
                    rank.setText("");
                    rem.setText("");
                    kelvin.setText("");
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        rank.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                String tempValue = rank.getText().toString();
                if (!tempValue.equals("") && !tempValue.equals(".") && !tempValue.equals("-") && selectedEditTxt == Temperature.RANKINE) {
                    double temp = Double.parseDouble(tempValue);
                    //Celcius
                    double outputCelcius = (temp - 32 - 459.67) / 1.8;
                    celsiusValue.setText(String.valueOf((stripDecimal(outputCelcius))));
                    //fahrenheit
                    double outputFahrenheit = (temp - 459.67);
                    far.setText(String.valueOf((stripDecimal(outputFahrenheit))));
                    //reamur
                    double outputReamur = (temp-491.67) * 4/9;
                    rem.setText(String.valueOf(stripDecimal(outputReamur)));
                    //kelvin
                    double outputKelvin = temp * (5d/9d);
                    System.out.println("HEYYYY " + outputKelvin + "  "+  temp*(5/9));
                    kelvin.setText(String.valueOf(stripDecimal(outputKelvin)));
                } else if (selectedEditTxt == Temperature.RANKINE) {
                    celsiusValue.setText("");
                    far.setText("");
                    rem.setText("");
                    kelvin.setText("");
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        rem.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                String tempValue = rem.getText().toString();
                if (!tempValue.equals("") && !tempValue.equals(".") && !tempValue.equals("-") && selectedEditTxt == Temperature.REAUMUR) {
                    double temp = Double.parseDouble(tempValue);
                    //Celcius
                    double outputCelcius = temp * 1.25d;
                    celsiusValue.setText(String.valueOf(stripDecimal(outputCelcius)));
                    //fahrenheit
                    double outputFahrenheit = temp * 2.25 + 32;
                    far.setText(String.valueOf(stripDecimal(outputFahrenheit)));
                    //rankine
                    double outputRankine = 0;
                    outputRankine = temp * 2.25 + 32 + 459.67;
                    rank.setText(String.valueOf(stripDecimal(outputRankine)));
                    //kelvin
                    double outputKelvin = temp * 5/4 + 273.15;
                    kelvin.setText(String.valueOf(stripDecimal(outputKelvin)));
                } else if (selectedEditTxt == Temperature.REAUMUR) {
                    celsiusValue.setText("");
                    far.setText("");
                    rank.setText("");
                    kelvin.setText("");
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        kelvin.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                String tempValue = kelvin.getText().toString();
                if (!tempValue.equals("") && !tempValue.equals(".") && !tempValue.equals("-") && selectedEditTxt == Temperature.KELVIN) {
                    double temp = Double.parseDouble(tempValue);
                    //Celcius
                    double outputCelcius = temp - 273.15;
                    celsiusValue.setText(String.valueOf(stripDecimal(outputCelcius)));
                    //fahrenheit
                    double outputFahrenheit = temp * 1.8 - 459.67;
                    far.setText(String.valueOf((stripDecimal(outputFahrenheit))));
                    //rankine
                    double outputRankine = temp * 1.8;
                    rank.setText(String.valueOf((stripDecimal(outputRankine))));
                    //rem
                    double outputRem = (temp - 273.15) * 0.8;
                    rem.setText(String.valueOf((stripDecimal(outputRem))));
                } else if (selectedEditTxt == Temperature.KELVIN) {
                    celsiusValue.setText("");
                    far.setText("");
                    rank.setText("");
                    rem.setText("");
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

    }

    // rounds numbers to x decimal places - depending on user request
    public Double stripDecimal(Double temp){

        Double strippedTemp;
        switch(decimalPlaces)
        {
            case "1":
                strippedTemp = (double)Math.round(temp * 10d) / 10d;
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
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

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


