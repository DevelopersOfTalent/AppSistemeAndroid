package com.example.arranque1.appsisteme;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.arranque1.appsisteme.bbdd.DaoContacts;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class AddActivity extends AppCompatActivity {
    EditText etPhone,etName;
    ImageView image;
    int ACTIVITY_GET_IMAGE = 1;
    File imageFile;
    Bitmap selectedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        etName = (EditText)findViewById(R.id.etName);
        etPhone = (EditText)findViewById(R.id.etPhone);
        image = (ImageView) findViewById(R.id.add_contactImage);

        image.setOnClickListener(new View.OnClickListener() {
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
                startActivityForResult(Intent.createChooser(intent, "Selecciona el origen"), ACTIVITY_GET_IMAGE);
            }
        });
    }

    public void save (View v){
        String name = etName.getText().toString();
        String phone = etPhone.getText().toString();

        imageFile = saveImageFile(selectedImage, name);
        String imageSource = imageFile.getPath();

        DaoContacts daoContacts = new DaoContacts(this);
        Contact contact = new Contact(null,name,phone,imageSource);
        daoContacts.addContact(contact);
        startActivity(new Intent(this, ListContactActivity.class));
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try{
            if(requestCode == ACTIVITY_GET_IMAGE && resultCode == RESULT_OK){
                Bundle extras = data.getExtras();
                selectedImage = extras.getParcelable("data");
                image.setImageBitmap(selectedImage);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public File saveImageFile(Bitmap bitmap, String title){
        File savedImage = new File(Environment.getExternalStorageDirectory().getPath(), title+".png");
        try {
            OutputStream outStream = new FileOutputStream(savedImage);
            bitmap.compress(Bitmap.CompressFormat.PNG,0,outStream);
            outStream.flush();
            outStream.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return savedImage;
    }
    /*public String getImageSrcString(Uri uri){
        String[] projection = { MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int index = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(index);
    }*/

    public void list(View v){
        startActivity(new Intent(AddActivity.this, ListContactActivity.class));
        finish();
    }

}
