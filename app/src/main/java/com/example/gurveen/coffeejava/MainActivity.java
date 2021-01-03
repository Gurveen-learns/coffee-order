package com.example.gurveen.coffeejava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the  button is clicked.
     */
    public int quantity = 0;

    public void increment(View view) {
        displayQuantity(quantity + 1);
        quantity++;
    }

    public void decrement(View view) {
        if (quantity > 0) {
            displayQuantity(quantity - 1);
            quantity--;
        } else {
            Toast.makeText(this, "You cannot order Coffee", Toast.LENGTH_SHORT);
        }
    }
    /**
    * Creates appropriate message
    * and send Intent to email app
    */

    public void submitOrder(View view) {
        String myMessage = "Thanks, " + nameGiver() + "\nYour order is $";
        float price = calculatePrice(quantity);
        String addMessage = "";
        TextView messageTextView = findViewById(R.id.message_view);
        CheckBox wcCheckView = findViewById(R.id.wc_checkbox);
        CheckBox chocCheckView = findViewById(R.id.choc_checkbox);

        if (wcCheckView.isChecked()) {
            price += (quantity * 0.5);
            addMessage += "\nToppings:" + wcCheckView.getText();
        }
        if (chocCheckView.isChecked()) {
            price += quantity;
            addMessage += "\nToppings:" + chocCheckView.getText();
        }

        myMessage += price + addMessage;


        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Order for "+nameGiver());
        intent.putExtra(Intent.EXTRA_TEXT,myMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);

        }
    }

    /**
     * this method is called when name field is clicked
     */
    public void displayName(View view) {
        nameGiver();
    }
    public String nameGiver() {
        EditText editText = findViewById(R.id.name_field);
        return editText.getText().toString();
    }
     /**calculates price of one cup*/

    private int calculatePrice(int i) {
        return i * 5;
    }





    /**
     * This method displays the given quantity and price value on the screen.
     */


    public void displayQuantity(int i) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + i);
    }


}
