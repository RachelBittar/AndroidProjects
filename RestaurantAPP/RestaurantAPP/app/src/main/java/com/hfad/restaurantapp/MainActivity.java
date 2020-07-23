package com.hfad.restaurantapp;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hfad.restaurantapp.database.AppDatabase;
import com.hfad.restaurantapp.database.CreditCardEntry;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {


  //

    private static final String LOG_TAG = AppDatabase.class.getSimpleName();

    private static final String DISCOVER = "Discover";
    private static final String VISA = "Visa";
    private static final String MASTERCARD = "MasterCard";
    private static final String AMERICAN = "American Express";

    String flag = null;
    String error=null;

    private AppDatabase myDB;
    List<CreditCardEntry> myccdata = new ArrayList<CreditCardEntry>();

    TextView tvFromDate;
    ImageView visaImage;
    ImageView mastercardImage;
    ImageView discoverImage;
    ImageView americanImage;
    StringBuilder ccnumberbuilder;
    DateFormat dateFormatter = new SimpleDateFormat("MM/YYYY");


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB = AppDatabase.getInstance(getApplicationContext());

        tvFromDate = (TextView) findViewById(R.id.tvFromDate);
        visaImage = (ImageView) findViewById(R.id.visa);
        visaImage.setVisibility(View.INVISIBLE);

        mastercardImage = (ImageView) findViewById(R.id.mastercard);
        mastercardImage.setVisibility(View.INVISIBLE);

        discoverImage = (ImageView) findViewById(R.id.discover);
        discoverImage.setVisibility(View.INVISIBLE);

        americanImage = (ImageView) findViewById(R.id.americaexpress);
        americanImage.setVisibility(View.INVISIBLE);


    }

    public void handleOnDateSet(int year, int month, int day) {
        Date date = new GregorianCalendar(year, month, 0).getTime();
        String formatedDate = dateFormatter.format(date);
        tvFromDate.setText(formatedDate);

    }

    private boolean parseCreditCardNumber() {

        boolean result = false;
        EditText ccnumber = (EditText) findViewById(R.id.cc_number);


        ccnumberbuilder = new StringBuilder(ccnumber.getText().toString());
        StringBuilder aux_ccnumber = ccnumberbuilder;


        int size = ccnumberbuilder.length();
        Log.d(LOG_TAG, "Length " + String.valueOf(ccnumberbuilder.length()));


        if (size > 14 && size < 29) {

            char[] check = new char[1];
            check[0] = aux_ccnumber.charAt(0);
            int head = Integer.parseInt(String.valueOf(check[0]));

            switch (head) {
                case 3:
                   if(AmericanExpress(aux_ccnumber)) result = true; //3
                    break;

                case 5:
                    if(MasterCard(aux_ccnumber)) return true;
                    break;

                case 6:
                    if (Discover(aux_ccnumber)) result = true;
                    break;
                case 4:
                   if(Visa(aux_ccnumber)) result = true; //4
                    break;

            }
        }

       if(size == 0)
       {
           Toast.makeText(this.getBaseContext(), "Fields are missing", Toast.LENGTH_SHORT).show();

       }

        return result;

    }

    private boolean Discover(StringBuilder aux_ccnumber) {

        boolean result = false;

        if (aux_ccnumber.length() == 16) {

            char[] check = new char[7];
            check[0] = aux_ccnumber.charAt(0);
            check[1] = aux_ccnumber.charAt(1);
            check[2] = aux_ccnumber.charAt(2);
            check[3] = aux_ccnumber.charAt(3);
            check[4] = aux_ccnumber.charAt(4);
            check[5] = aux_ccnumber.charAt(5);
            check[6] = aux_ccnumber.charAt(6);

            StringBuilder stail = new StringBuilder();
            stail.append(check[1]).append(check[2]).append(check[3]).append(check[4]).append(check[5]).append(check[6]);

            int range = Integer.valueOf("1100");
            int range2 = Integer.valueOf("1109");
            int range3 = Integer.valueOf("1120");
            int range4 = Integer.valueOf("1149");
            int range5 = Integer.valueOf("1177");
            int range6 = Integer.valueOf("1179");


            if (check[0] == '6') {
                if (check[1] == '0') {
                    stail.deleteCharAt(5);
                  //  Log.d("Discover", stail.toString());
                    int ntail_d = Integer.parseInt(stail.toString());

                    if (ntail_d >= range && ntail_d <= range2) {
                        flag = DISCOVER;
                        result = true;
                    }

                    if (ntail_d >= range3 && ntail_d <= range4) {
                        flag = DISCOVER;
                        result = true;
                    }

                    if (ntail_d >= range5 && ntail_d <= range6) {
                        flag = DISCOVER;
                        result = true;
                    }
                }


                if (check[1] == '4') {
                    stail.deleteCharAt(5);
                  //  Log.d("Discover", stail.toString());
                    int ntail_d = Integer.parseInt(stail.toString());

                    if (ntail_d >= range && ntail_d <= range2) {
                        flag = DISCOVER;
                        result = true;
                    }
                }
            }

        }

        return result;
    }

    private boolean Visa(StringBuilder aux_ccnumber) {

        boolean result =false;
        if (aux_ccnumber.length() < 20) {
            flag = VISA;
            result = true;
        }

        return result;
    }

    private boolean MasterCard(StringBuilder aux_ccnumber) {

        //-------------MASTERCARD-----------------------

        boolean result = false;
        if (aux_ccnumber.length() == 16) {
            char[] check = new char[7];

            check[0] = aux_ccnumber.charAt(0);
            check[1] = aux_ccnumber.charAt(1);
            check[2] = aux_ccnumber.charAt(2);
            check[3] = aux_ccnumber.charAt(3);
            check[4] = aux_ccnumber.charAt(4);
            check[5] = aux_ccnumber.charAt(5);
            check[6] = aux_ccnumber.charAt(6);


            StringBuilder stail = new StringBuilder();
            stail.append(check[1]).append(check[2]).append(check[3]).append(check[4]).append(check[5]);

            String tail = stail.toString();
            int ntail = Integer.parseInt(tail);

            check[0] = aux_ccnumber.charAt(0);

            if (check[0] == '5') {
                if (ntail >= 10000 && ntail <= 59999) {
                    flag = MASTERCARD;
                    result = true;
                }
            }
            if (check[0] == '2') {
                if (ntail >= 22100 && ntail <= 72099) {
                    flag = MASTERCARD;
                    result = true;
                }
            }

        }

        return result;

    }

    private boolean AmericanExpress(StringBuilder aux_ccnumber) {

        boolean result = false;
        if (aux_ccnumber.length() == 15) {

            char[] check = new char[2];

            check[0] = aux_ccnumber.charAt(0);
            check[1] = aux_ccnumber.charAt(1);

            if (check[0] == '3') {
                if (check[1] == '4' || check[1] == '7') {
                    flag = AMERICAN;
                    result = true;

                }
            }
        }

        return false;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();
        if (itemThatWasClickedId == R.id.action_search) {
            Context context = MainActivity.this;

            String textToShow = "Add clicked";
            Toast.makeText(context, textToShow, Toast.LENGTH_SHORT).show();

            SaveDate();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void loadImage(){

        if (flag.equals(VISA)) {
            visaImage.setVisibility(View.VISIBLE);
        }

        if (flag.equals(AMERICAN)) {
            americanImage.setVisibility(View.VISIBLE);
        }

        if (flag.equals(VISA)) {
            visaImage.setVisibility(View.VISIBLE);
        }

        if (flag.equals(DISCOVER)) {
            discoverImage.setVisibility(View.VISIBLE);
        }

    }

    public CreditCard popCrediCardData(){

        String firstname=null;
        String lasttname=null;
        String cvv=null;
        CreditCard cc=null;

        EditText efirstname = (EditText) findViewById(R.id.cc_name);
            if(!efirstname.getText().toString().isEmpty()){
                firstname = efirstname.getText().toString();
            }
            else{
                error = "First Name can not be empty";
            }


        EditText elastname = (EditText) findViewById(R.id.cc_lastname);
        if(!elastname.getText().toString().isEmpty()){
            lasttname = efirstname.getText().toString();
        }
        else{
            error = "Last Name can not be empty";
        }


        EditText ecvv = (EditText) findViewById(R.id.cc_cvv);
        if(!ecvv.getText().toString().isEmpty()){
            cvv = elastname.getText().toString();
        }
        else{
            error = "CVV can not be empty";
        }


        if(error!=null){
            Toast.makeText(this.getBaseContext(),error,
                    Toast.LENGTH_SHORT).show();
        }
        else {
            cc = new CreditCard(firstname, lasttname, cvv, ccnumberbuilder.toString(), tvFromDate.getText().toString(), flag);
            return cc;
        }

        return cc;
    }




    public void getExpDate(View view) {
        TextView et1 = (TextView) view;
        showDatePickerDialog(et1.getText().toString().trim());
    }

    private void showDatePickerDialog(String dateText) {

        Date date = null;
        try {
            if (dateText.equals(""))
                date = new Date();
            else
                date = dateFormatter.parse(dateText);
        } catch (Exception exp) {
            date = new Date();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH); // calendar month 0-11
        int day = calendar.get(Calendar.DATE);

        DatePickerDialog datePicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                handleOnDateSet(year, month, day);
            }
        }, year, month, day);
        datePicker.setTitle("Any day");
        datePicker.setButton(DatePickerDialog.BUTTON_POSITIVE, "Ok", datePicker);
        datePicker.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Cancel button clicked
            }
        });
        datePicker.show();
    }


    public void AddCCNumber(View view) {

         SaveDate();

    }

    void SaveDate(){

        CreditCard cc;
        if(parseCreditCardNumber()) {
            loadImage();
            cc = popCrediCardData();


            if (cc != null) {

                Toast.makeText(this.getBaseContext(), "Credit Card has been added",
                        Toast.LENGTH_SHORT).show();

                final CreditCardEntry ccTask = new CreditCardEntry(cc.getFirstName(), cc.getLastName(),
                        cc.getCvv(), cc.getNum_card(), cc.getExp_date(), cc.getFlag());

                myccdata.add(ccTask);

                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        myDB.taskDao().insertTask(ccTask);
                        finish();
                    }
                });

                Intent addTaskIntent = new Intent(this, CreditCardTasks.class);
                startActivity(addTaskIntent);

            }

        }



    }

}




