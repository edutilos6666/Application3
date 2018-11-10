package com.edutilos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class CalculatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        initComponents();
        registerEvents();
    }


    private EditText editDisplay;
    private Button
            btn0, btn1, btn2, btn3, btn4,
            btn5, btn6, btn7, btn8, btn9,
            btnAdd, btnSubtract, btnMultiply, btnDivide, btnModulo,
            btnSin, btnCos, btnTan, btnCot, btnEq, btnClear;

    private void initComponents() {
        editDisplay = findViewById(R.id.calc_edit_display);
        btn0 = findViewById(R.id.calc_btn_0);
        btn1 = findViewById(R.id.calc_btn_1);
        btn2 = findViewById(R.id.calc_btn_2);
        btn3 = findViewById(R.id.calc_btn_3);
        btn4 = findViewById(R.id.calc_btn_4);
        btn5 = findViewById(R.id.calc_btn_5);
        btn6 = findViewById(R.id.calc_btn_6);
        btn7 = findViewById(R.id.calc_btn_7);
        btn8 = findViewById(R.id.calc_btn_8);
        btn9 = findViewById(R.id.calc_btn_9);
        btnAdd = findViewById(R.id.calc_btn_add);
        btnSubtract = findViewById(R.id.calc_btn_subtract);
        btnMultiply = findViewById(R.id.calc_btn_multiply);
        btnDivide = findViewById(R.id.calc_btn_divide);
        btnModulo = findViewById(R.id.calc_btn_modulo);
        btnSin = findViewById(R.id.calc_btn_sin);
        btnCos = findViewById(R.id.calc_btn_cos);
        btnTan = findViewById(R.id.calc_btn_tan);
        btnCot = findViewById(R.id.calc_btn_cot);
        btnEq = findViewById(R.id.calc_btn_eq);
        btnClear = findViewById(R.id.calc_btn_clear);
    }

    private void registerEvents() {
        View.OnClickListener btnNumberClickListener = new BtnNumberClickListener();
        btn0.setOnClickListener(btnNumberClickListener);
        btn1.setOnClickListener(btnNumberClickListener);
        btn2.setOnClickListener(btnNumberClickListener);
        btn3.setOnClickListener(btnNumberClickListener);
        btn4.setOnClickListener(btnNumberClickListener);
        btn5.setOnClickListener(btnNumberClickListener);
        btn6.setOnClickListener(btnNumberClickListener);
        btn7.setOnClickListener(btnNumberClickListener);
        btn8.setOnClickListener(btnNumberClickListener);
        btn9.setOnClickListener(btnNumberClickListener);

        View.OnClickListener btnTrigonometryClickListener = new BtnTrigonometryClickListener();
        btnSin.setOnClickListener(btnTrigonometryClickListener);
        btnCos.setOnClickListener(btnTrigonometryClickListener);
        btnTan.setOnClickListener(btnTrigonometryClickListener);
        btnCot.setOnClickListener(btnTrigonometryClickListener);

        View.OnClickListener btnBinaryOperatorClickListener = new BtnBinaryOperatorClickListener();
        btnAdd.setOnClickListener(btnBinaryOperatorClickListener);
        btnSubtract.setOnClickListener(btnBinaryOperatorClickListener);
        btnMultiply.setOnClickListener(btnBinaryOperatorClickListener);
        btnDivide.setOnClickListener(btnBinaryOperatorClickListener);
        btnModulo.setOnClickListener(btnBinaryOperatorClickListener);

        btnEq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Expression expression = new ExpressionBuilder(editDisplay.getText().toString()).build();
                    double result = expression.evaluate();
                    editDisplay.setText(String.format("%s = %s",
                            editDisplay.getText(),
                            Double.toString(result)));
                } catch(Exception ex) {
                    editDisplay.setText(ex.getMessage());
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editDisplay.setText("");
            }
        });
    }


    private class BtnBinaryOperatorClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            editDisplay.setText(String.format("%s %s ",
                    editDisplay.getText(),
                    ((Button)v).getText()));
        }
    }

    private class BtnTrigonometryClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            editDisplay.setText(String.format("%s(%s) ",
                    ((Button)v).getText(),
                    editDisplay.getText()));
        }
    }

    private class BtnNumberClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            editDisplay.setText(String.format("%s%s", editDisplay.getText(), ((Button)v).getText()));
        }
    }
}
