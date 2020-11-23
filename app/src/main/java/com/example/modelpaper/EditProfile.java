package com.example.modelpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.List;

public class EditProfile extends AppCompatActivity {

    EditText etUserName, etPassword, etDOB;
    RadioButton rbMale, rbFemale;
    Button btnEdit, btnSearch, btnDelete;
    String gender;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        etUserName = findViewById(R.id.eTUserName);
        etPassword = findViewById(R.id.eTPassword);
        etDOB = findViewById(R.id.eTDateOfBirth);
        rbMale = findViewById(R.id.rBMale);
        rbFemale = findViewById(R.id.rBFemale);
        btnEdit = findViewById(R.id.btnLogin);
        btnSearch = findViewById(R.id.btnSearch);
        btnDelete = findViewById(R.id.btnCreateAccount);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHandler dbHandler = new DBHandler(EditProfile.this);
                List SearchList = dbHandler.readAllInfo(etUserName.getText().toString());
                id = Integer.parseInt(SearchList.get(0).toString());
                etDOB.setText(SearchList.get(2).toString());
                etPassword.setText(SearchList.get(3).toString());
                gender = SearchList.get(4).toString();
                if (gender.equals("Male")){
                    rbMale.setChecked(true);
                }else{
                    rbFemale.setChecked(true);
                }
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHandler dbHandler = new DBHandler(EditProfile.this);
                Boolean status = dbHandler.updateInfor(id,etUserName.getText().toString(),etDOB.getText().toString(),etPassword.getText().toString(),gender);
                if(status){
                    Toast.makeText(EditProfile.this,"updated",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(EditProfile.this,"Not Updated",Toast.LENGTH_LONG).show();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHandler dbHandler = new DBHandler(EditProfile.this);
                boolean status = dbHandler.deleteInfo(etUserName.getText().toString());
                if(status){
                    etUserName.setText(null);
                    etPassword.setText(null);
                    etDOB.setText(null);
                    rbFemale.setChecked(false);
                    rbMale.setChecked(false);
                    Toast.makeText(EditProfile.this,"deleted",Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(EditProfile.this,"Enter correct user name",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}