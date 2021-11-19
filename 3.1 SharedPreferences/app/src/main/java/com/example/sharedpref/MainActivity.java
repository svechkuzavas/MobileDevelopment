package com.example.sharedpref;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private static final int CAMERA_REQUEST = 12;
    Button save, open, camera;
    EditText nameField;
    ImageView image;
    String currentPhotoPath=null;
    SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
    SharedPreferences reader = getPreferences(MODE_PRIVATE);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        save = (Button) findViewById(R.id.save);
        open = (Button) findViewById(R.id.open);
        camera = (Button) findViewById(R.id.camera);
        nameField = (EditText) findViewById(R.id.input);
        image = (ImageView) findViewById(R.id.image);

        View.OnClickListener click = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.save:
                        editor.putString("name",nameField.getText().toString());
                        editor.putString("photo", currentPhotoPath);
                        editor.commit();
                        break;
                    case R.id.open:
                        nameField.setText(reader.getString("name",""));
                        String imagePath = reader.getString("photo", "");
                        if (!imagePath.equals("")){
                            File file = new File(imagePath);
                            if (file.exists()){
                                image.setImageBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()));
                            }
                        }
                        break;
                    case R.id.camera:
                        dispatchTakePictureIntent();
                        break;
                }
            }
        };
        camera.setOnClickListener(click);
        save.setOnClickListener(click);
        open.setOnClickListener(click);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            File imgFile = new File(currentPhotoPath);
            if(imgFile.exists()){
                image.setImageBitmap(BitmapFactory.decodeFile(imgFile.getAbsolutePath()));
            }
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "PH_" + timeStamp + "_";

        String currentDocumentPath = getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath();
        File storageDir = new File(currentDocumentPath);
        File image = File.createTempFile(
                imageFileName,  /* filename */
                ".jpg",         /* extension */
                storageDir     /* path */
        );
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) { }
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.files",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, CAMERA_REQUEST);
            }
        }
    }
}