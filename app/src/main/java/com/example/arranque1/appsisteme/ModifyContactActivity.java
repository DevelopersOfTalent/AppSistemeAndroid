package com.example.arranque1.appsisteme;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.arranque1.appsisteme.bbdd.DaoContacts;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ModifyContactActivity extends AppCompatActivity {
    EditText EditName, EditPhone;
    ImageView contactImage;
    Bitmap contactPhoto;
    DaoContacts daocontact;
    String path;

    int contactID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_contact);
        EditName=(EditText)findViewById(R.id.EditName);
        EditPhone=(EditText)findViewById(R.id.EditPhone);
        contactImage=(ImageView)findViewById(R.id.edit_contactPhoto);
        Intent i = getIntent();
        daocontact = new DaoContacts(this);
        contactID = i.getExtras().getInt("id");
        String contactName = i.getExtras().getString("name");
        String contactPhone = i.getExtras().getString("phone");
        path = i.getExtras().getString("imageSource");
        EditName.setText(contactName);
        EditPhone.setText(contactPhone);

        contactPhoto = BitmapFactory.decodeFile(path);
        contactImage.setImageBitmap(contactPhoto);
        contactImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                intent.putExtra("crop", "true");
                intent.putExtra("aspectX", 1);
                intent.putExtra("aspectY", 1);
                intent.putExtra("outputX", 300);
                intent.putExtra("outputY", 300);
                intent.putExtra("return-data", true);
                startActivityForResult(Intent.createChooser(intent, "Selecciona el origen"), 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try{
            if(requestCode == 1 && resultCode == RESULT_OK){
                Bundle extras = data.getExtras();
                contactPhoto = extras.getParcelable("data");
                contactImage.setImageBitmap(contactPhoto);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void edit (View v){
        String updatedContactName = EditName.getText().toString();
        String updatedContactPhone = EditPhone.getText().toString();

        File contactImageFile = new File(path);
        OutputStream outStream = null;
        try {
            outStream = new FileOutputStream(contactImageFile);
            contactPhoto.compress(Bitmap.CompressFormat.PNG,0,outStream);
            outStream.flush();
            outStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        path = contactImageFile.getAbsolutePath();
        Contact editedContact = new Contact(contactID,updatedContactName,updatedContactPhone,path);
        daocontact.editContact(editedContact);
        startActivity(new Intent(ModifyContactActivity.this, ListContactActivity.class));
        finish();
    }

    public void delete (View v){
        Intent i = getIntent();
        String selectedContactName = i.getExtras().getString("name");
        String selectedContactPhone = i.getExtras().getString("phone");
        Contact selectedContact = new Contact(contactID,selectedContactName,selectedContactPhone);
        daocontact.deleteContact(selectedContact);
        startActivity(new Intent(ModifyContactActivity.this, ListContactActivity.class));
        finish();
    }

    public void deleteAlert(final View v){
        final AlertDialog.Builder deleteDialog = new AlertDialog.Builder(this);
        deleteDialog.setTitle("¿Seguro que deseas borrar?");
        deleteDialog.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                delete(v);
            }
        });
        deleteDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(ModifyContactActivity.this, ListContactActivity.class));
                finish();
            }
        });
        deleteDialog.show();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ModifyContactActivity.this,ListContactActivity.class));
    }
}
