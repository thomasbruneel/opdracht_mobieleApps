package com.example.thomas.slidingnavigationmenu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.thomas.slidingnavigationmenu.Room.ZoekertjeDB;

public class Email extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        Intent i = getIntent();
        String sub = i.getStringExtra("subject");

        Button buttonSend = (Button) findViewById(R.id.sendButton);
        final EditText receiver=(EditText) findViewById(R.id.editText1);
        final EditText subject=(EditText) findViewById(R.id.editText2);
        final EditText message=(EditText) findViewById(R.id.editText3);

        subject.setText("Second Chance: "+sub);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL,new String[]{receiver.getText().toString()});
                i.putExtra(Intent.EXTRA_SUBJECT,subject.getText().toString());
                i.putExtra(Intent.EXTRA_TEXT,message.getText().toString());
                try{
                    startActivity(Intent.createChooser(i,"Send mail..."));
                }catch(android.content.ActivityNotFoundException ex){
                    Log.i("email client","er is geen email client");
                }

            }
        });


    }
}
