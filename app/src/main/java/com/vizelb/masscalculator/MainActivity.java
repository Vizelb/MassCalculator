package com.vizelb.masscalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editTextAddMassFirst, editTextAddMassSecond, editTextAddRatioFirst,
            editTextAddRatioSecond, editTextAddTotalMass;
    Button buttonCalculate;
    TextView textViewOutputValue, textViewOutputFormula, textViewFormulaDigital;

    float value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextAddMassFirst = findViewById(R.id.edit_text_add_mass_first);
        editTextAddMassSecond = findViewById(R.id.edit_text_add_mass_second);
        editTextAddRatioFirst = findViewById(R.id.edit_text_add_ratio_first);
        editTextAddRatioSecond = findViewById(R.id.edit_text_add_ratio_second);
        editTextAddTotalMass = findViewById(R.id.edit_text_add_total_mass);

        buttonCalculate = findViewById(R.id.button_calculate);

        textViewOutputValue = findViewById(R.id.text_view_output_value);
        textViewOutputFormula = findViewById(R.id.text_view_output_formula);
        textViewFormulaDigital = findViewById(R.id.text_view_formula_digital);

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                float Mr1 = testingEmptyBoard(editTextAddMassFirst);
                float Mr2 = testingEmptyBoard(editTextAddMassSecond);
                float x = testingEmptyBoard(editTextAddRatioFirst);
                float y = testingEmptyBoard(editTextAddRatioSecond);
                float mass = testingEmptyBoard(editTextAddTotalMass);


                if (Mr1 != 0 && Mr2 != 0 && x != 0 && y != 0 && mass != 0) {

                    float firstStep = (float) ((Mr1 * x) + (Mr2 * y));
                    float secondStep = (float) (mass / firstStep);

                    float massX = (float) (secondStep * Mr1);
                    float massY = (float) (mass - massX);

                    String stringX = String.valueOf(massX);
                    String stringY = String.valueOf(massY);

                    String formulaDigital = (String.valueOf(Mr1) + " * " + String.valueOf(x) + "i + "
                            + String.valueOf(Mr2) + " * " + String.valueOf(y) + "j = " + String.valueOf(mass)) ;

                    textViewOutputValue.setText(stringX);
                    textViewOutputFormula.setText(stringY);
                    textViewFormulaDigital.setText(formulaDigital);
                } else {
                    textViewOutputFormula.setText("TRY");
                    textViewOutputValue.setText("AGAIN");
                }
                hideKeyBoard(buttonCalculate);

            }

        });
    }

    private void hideKeyBoard(Button button) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(button.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private float testingEmptyBoard(EditText editText) {
        if (!TextUtils.isEmpty(editText.getText().toString().trim())) {
            value = Float.parseFloat(editText.getText().toString());
        } else {
            value = 0;
        }
        return value;
    }


}