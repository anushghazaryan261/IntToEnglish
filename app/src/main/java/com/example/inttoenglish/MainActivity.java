package com.example.inttoenglish;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.security.InvalidParameterException;

public class MainActivity extends AppCompatActivity {

    private EditText number;
    private Button show;
    private TextView answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        number = findViewById(R.id.inputNumber);
        show = findViewById(R.id.show);
        answer = findViewById(R.id.answer);


        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = Integer.parseInt(number.getText().toString());
                String result = intToEnglish(num);
                answer.setText("Result is: " + result);
            }
        });


    }

    public static String intToEnglish(int num){
        if (num == 0)
            return "zero";
        if (num < 0 || num > 999999999)
            throw new InvalidParameterException("Number should be in range of 0 to 999,999,999");
        if (num >= 1000000)
            if(num % 1000000 != 0){
                return intToEnglish(num / 1000000) + " million " + intToEnglish(num % 1000000);
            } else {
                return intToEnglish(num / 1000000) + " million";
            }
        if (num >= 1000)
            if(num % 1000 != 0){
                return intToEnglish(num / 1000) + " thousand " + intToEnglish(num % 1000);
            }else {
                return intToEnglish(num / 1000) + " thousand";
            }
        if(num >= 100)
            if(num%100!=0) {
                return intToEnglish(num / 100) + " hundred " + intToEnglish(num % 100);
            }else {
                return intToEnglish(num / 100) + " hundred";
            }
        String[] digits = new String[]{"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        String[] tens = new String[]{"", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
        String[] teens = new String[]{"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};

        String result;

        if (num < 10)
            result = digits[num];
        else if (num < 20)
            result = teens[num - 10];
        else {
            result = tens[num / 10];
            if (num % 10 != 0)
                result += "-" + digits[num % 10];
        }

        return result;
    }
}