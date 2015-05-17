package com.example.ruchi.azurecontact;

import com.example.ruchi.azurecontact.Contact.*;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceTable;
import com.microsoft.windowsazure.mobileservices.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.TableOperationCallback;
import com.microsoft.windowsazure.mobileservices.TableQueryCallback;

import java.net.MalformedURLException;
import java.util.List;


public class AzureContactActivity extends ActionBarActivity {

    private MobileServiceClient mobileClient;
    private MobileServiceTable mobileContactTable;
    private EditText newContactName;
    private EditText newContactEmail;
    private EditText newContactPhone;
    private Button addButton;
    private Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_azure_contact);

        try {
            // Create the Mobile Service Client instance, using the provided
            // Mobile Service URL and key
            mobileClient = new MobileServiceClient(
                    "https://mydroid.azure-mobile.net/",
                    "YDDmYNZbMOUmbTdPafgPrVDgdTgdPa36",
                    this);

        } catch (MalformedURLException e) {
            createAndShowDialog(new Exception("There was an error creating the Mobile Service. Verify the URL"), "Error");
        }

        // TODO: Comment this section out if you do not want authentication
		/*
                mobileClient.login(MobileServiceAuthenticationProvider.MicrosoftAccount,
		        new UserAuthenticationCallback() {

		            @Override
		            public void onCompleted(MobileServiceUser user,
		                    Exception exception, ServiceFilterResponse response) {
		                if (exception == null) {
		                    createContactTable();
		                } else {
		                    createAndShowDialog("You must login.", "Error");
		                    return;
		                }
		            }
		        });
		 */

        // TODO: Uncomment this section if you do not want authentication
        createContactTable();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_azure_contact, menu);
        return true;
    }

    public void createContactTable() {
        mobileContactTable = mobileClient.getTable(Contact.class);
        newContactName = (EditText) findViewById(R.id.p_name);
        newContactEmail = (EditText) findViewById(R.id.p_email);
        newContactPhone= (EditText) findViewById(R.id.p_contact);
        addButton = (Button) findViewById(R.id.btn_add);
        searchButton = (Button) findViewById(R.id.btn_search);
        addButton.setOnClickListener( new Button.OnClickListener() {
            public void onClick(View v) {
                addContact(v);
            }
        });

        searchButton.setOnClickListener( new Button.OnClickListener() {
            public void onClick(View v) {
              // searchContact(v);
            }
        });
    }

    public void addContact(View view) {

        // Create a new contact
        Contact contact = new Contact();

        contact.setName(newContactName.getText().toString());
        contact.setEmail(newContactEmail.getText().toString());
        contact.setPhone(newContactPhone.getText().toString());

        // Insert the new contact
        mobileContactTable.insert(contact, new TableOperationCallback() {

            @Override
            public void onCompleted(Object entity, Exception exception, ServiceFilterResponse response) {
                if (exception != null) {
                    createAndShowDialog(exception, "Error");
                }
                else{
                    Toast.makeText(getApplicationContext(), "Data added successfully!!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

  /* private void searchContact(View view) {
        String name = newContactName.getText().toString();
        // Search for the contact based on the name field
        mobileContactTable.where().field("name").eq(name).execute(new TableQueryCallback() {
            public void onCompleted(List result, int count, Exception exception, ServiceFilterResponse response) {
                if (exception == null) if (!result.isEmpty()) {
                    newContactName.setText(result.get(0).getName());
                    newContactEmail.setText(result.get(0).getEmail());
                    newContactPhone.setText(resu);
                }
                else {
                    createAndShowDialog(exception, "Error");

                }
            }
        });
    }*/

    private void createAndShowDialog(Exception exception, String title) {
        createAndShowDialog(exception.toString(), title);
    }

    private void createAndShowDialog(String message, String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(message);
        builder.setTitle(title);
        builder.create().show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
