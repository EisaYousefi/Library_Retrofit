package com.broa.broasjapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SendMailActivity extends AppCompatActivity {
    Button sendmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_mail);
        sendmail=findViewById(R.id.sendMail);
        sendmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email();
            }
        });
    }

    private void email() {
        String [] to={"eisa.yousefi.2910@gmail.com"};
        String [] cc={"www.eisa.ir"};
        Intent mail=new Intent(Intent.ACTION_SEND);
        mail.setData(Uri.parse("mail to : "));
        mail.setType("text/plain");
        mail.putExtra(Intent.EXTRA_EMAIL,to);
        mail.putExtra(Intent.EXTRA_CC,cc);
        mail.putExtra(Intent.EXTRA_TEXT,"salam azizam");
        mail.putExtra(Intent.EXTRA_SUBJECT,"E-sdsfsdf");
        startActivity(Intent.createChooser(mail,"Mail to..."));

    }
}
