package com.example.modelpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ProfileManagment extends AppCompatActivity {

    EditText eTName, eTDBirth, eTPassword;
    RadioGroup radioGroup;
    RadioButton rBFemale, rBMale;
    Button btnRegister, btnUpdateProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_managment);

        eTName = findViewById(R.id.eTUserName);
        eTDBirth = findViewById(R.id.eTDateOfBirth);
        eTPassword = findViewById(R.id.eTPassword);
        radioGroup = findViewById(R.id.radioGroupGender);
        rBFemale = findViewById(R.id.rBFemale);
        btnRegister = findViewById(R.id.btnCreateAccount);
        btnUpdateProfile = findViewById(R.id.btnUpdate);
        rBMale = findViewById(R.id.rBMale);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = eTName.getText().toString();
                String BirthDay = eTDBirth.getText().toString();
                String password = eTPassword.getText().toString();
                String Gender;
                int radioID = radioGroup.getCheckedRadioButtonId();
                if (radioID == rBFemale.getId()){
                    Gender = "Female";
                }else{
                    Gender = "Male";
                }

                DBHandler dbHandler = new  DBHandler(ProfileManagment.this);
                if(name.equals("")){
                    Toast.makeText(ProfileManagment.this,"empty",Toast.LENGTH_LONG).show();
                }else {
                    long newID = dbHandler.addInfo(name,BirthDay,password,Gender);
                    Toast.makeText(ProfileManagment.this,"inserted: "+newID,Toast.LENGTH_LONG).show();
                    eTName.setText(null);
                    eTDBirth.setText(null);
                    eTPassword.setText(null);
                    rBFemale.setChecked(false);
                    rBMale.setChecked(false);

                }


                eTName.setText("");
                eTDBirth.setText("");
                eTPassword.setText("");
            }
        });
        btnUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileManagment.this,EditProfile.class);
                startActivity(intent);
            }
        });

    }


}