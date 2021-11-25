package com.example.employeedetails.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
//
//import androidx.activity.result.ActivityResult;
//import androidx.activity.result.ActivityResultCallback;
//import androidx.activity.result.ActivityResultLauncher;
//import androidx.activity.result.contract.ActivityResultContracts;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
//import android.support.v7.app.AppCompatActivity;
import com.example.employeedetails.AppSession;
import com.example.employeedetails.R;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.IOException;

public class ChangePhotoActivity extends AppCompatActivity {
    AppSession session;
    TextView b;
    TextView b2;
    ImageView viewImage;
    Uri values;

    ActivityResultLauncher<Intent> someActivityResultLauncher;
    ActivityResultLauncher<Intent> gettingOtherContent;
    ActivityResultLauncher<String> gettingthecontent;
    Bitmap value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_photo);
        b=findViewById(R.id.btnSelectPhoto);
        b2=findViewById(R.id.Savebtn);
        viewImage=findViewById(R.id.shapeableImageView);

        session=new AppSession(getApplicationContext());
        viewImage.setImageBitmap(session.getProfileBitmap());
//       ActivityResultContract val= new ActivityResultContract<ActivityResult, Uri>(){
//
//
//           @NonNull
//           @Override
//           public Intent createIntent(@NonNull Context context, ActivityResult input) {
//                   return CropImage.activity().setAspectRatio(16,9).getIntent(getApplicationContext());
//               }
//
//
//               @NonNull
//           @Override
//           public Uri parseResult(
//                   int resultCode, @Nullable Intent intent) {
//               return CropImage.getActivityResult(intent).getUri();
//           }
//        };
        someActivityResultLauncher= registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode()==RESULT_OK&& result.getData()!=null){
                   Bundle bundle=result.getData().getExtras();
                  value=(Bitmap) bundle.get("data");
                   viewImage.setImageBitmap(value);

                   b2.setVisibility(View.VISIBLE);
                }
            }
        });
        gettingthecontent=registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                try {
                  value = MediaStore.Images.Media.getBitmap(getContentResolver(), result);
                    values=result;
                    b2.setVisibility(View.VISIBLE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                viewImage.setImageURI(result);
            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                session.setImageBitmap(value);
                finish();
            }
        });
            }

    private void selectImage() {
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Photo!");

        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo"))
                {
                    Intent intt=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if(intt.resolveActivity(getPackageManager())!=null){
                        someActivityResultLauncher.launch(intt);
                    }
//                    Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//                    File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");
//                    Uri photoURI = FileProvider.getUriForFile(getApplicationContext(), getApplicationContext().getPackageName() + ".provider",f);
//                    intent.putExtra(MediaStore.EXTRA_OUTPUT,photoURI);
////                    Uri.fromFile(f)
//                    intent.putExtra("item",1);
//                    startActivityForResult(intent,1);
                }
                else if (options[item].equals("Choose from Gallery"))
                {
//                    Intent intent = new  Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                    startActivityForResult(intent,2);
//                    intent.putExtra("item",2);
//                    startActivityForResult(intent,2);                 ine
                    gettingthecontent.launch("image/*");
//                    Intent i=getPickImageChooserIntent();
//                    i.putExtra("values",200);
//                    gettingOtherContent.launch(i);
//                    someActivityResultLauncher.launch(intent);
                }
                else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
//
//    public void showSelectedImage(){
//        try {
//            idAddForm.setVisibility(View.GONE);
//            idImagePicker.setVisibility(View.VISIBLE);
//            final ImageView imgSelected = (ImageView)findViewById(R.id.imgSelected);
//            ImageButton btnCancel = (ImageButton)findViewById(R.id.btnCancel);
//            final ImageButton btnSelect = (ImageButton)findViewById(R.id.btnSelect);
//            final ImageButton btnCrop = (ImageButton) findViewById(R.id.btnCrop);
//            imgSelected.setImageBitmap(null);
//            btnSelect.setVisibility(View.GONE);
//            btnCrop.setVisibility(View.GONE);
//            if (imageBytes != null) {
//                Bitmap bmp = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
//                imgSelected.setImageBitmap(bmp);
//                imgSelected.setVisibility(View.VISIBLE);
//                value=bmp;
//                bmp  = null;
//            }
//            btnCancel.setOnClickListener(new View.OnClickListener() {
//                public void onClick(View popupView) {
//                    idAddForm.setVisibility(View.VISIBLE);
//                    idImagePicker.setVisibility(View.GONE);
//                }
//            });
//        }
//        catch (Exception ex){
//            String s=null;
//            //todo
//        }
//    }
//    public Intent getPickImageChooserIntent() {
//        Intent chooserIntent = null;
//        try {
//            Uri outputFileUri = getCaptureImageOutputUri();
//
//            List<Intent> allIntents = new ArrayList<>();
//            PackageManager packageManager = getPackageManager();
//
//            // collect all camera intents
//            Intent captureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//            List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);
//            for (ResolveInfo res : listCam) {
//                Intent intent = new Intent(captureIntent);
//                intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
//                intent.setPackage(res.activityInfo.packageName);
//                if (outputFileUri != null) {
//                    intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
//                }
//                allIntents.add(intent);
//            }
//
//            // collect all gallery intents
//            Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
//            galleryIntent.setType("image/*");
//            List<ResolveInfo> listGallery = packageManager.queryIntentActivities(galleryIntent, 0);
//            for (ResolveInfo res : listGallery) {
//                Intent intent = new Intent(galleryIntent);
//                intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
//                intent.setPackage(res.activityInfo.packageName);
//                allIntents.add(intent);
//            }
//
//            // the main intent is the last in the list
//            Intent mainIntent = allIntents.get(allIntents.size() - 1);
//            for (Intent intent : allIntents) {
//                if (intent.getComponent().getClassName().equals("com.android.documentsui.DocumentsActivity")) {
//                    mainIntent = intent;
//                    break;
//                }
//            }
//            allIntents.remove(mainIntent);
//
//            // Create a chooser from the main intent
//            chooserIntent = Intent.createChooser(mainIntent, "Select Image");
//
//            // Add all other intents
//            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, allIntents.toArray(new Parcelable[allIntents.size()]));
//        }
//        catch (Exception e) {
//            // e.printStackTrace();
//        }
//        return chooserIntent;
//    }
//    //Get URI to image received from capture by camera.
//    private Uri getCaptureImageOutputUri() {
//        Uri outputFileUri = null;
//        try {
//            File getImage = getExternalCacheDir();
//            if (getImage != null) {
//                outputFileUri = Uri.fromFile(new File(getImage.getPath(), "pickImageResult.jpeg"));
//            }
//        }
//        catch (Exception e) {
//            // e.printStackTrace();
//        }
//        return outputFileUri;
//    }
//    public void showCroppedImage(Uri imageUri){
//        try {
//            idAddForm.setVisibility(View.GONE);
//            idImagePicker.setVisibility(View.VISIBLE);
//            final ImageView imgSelected = (ImageView)findViewById(R.id.imgSelected);
//            //Manage image Crop
//            mCropImageView = (CropImageView) findViewById(R.id.imgCropImage);
//            mCropImageView.setImageUriAsync(imageUri);
//            ImageButton btnCancel = (ImageButton)findViewById(R.id.btnCancel);
//            final ImageButton btnSelect = (ImageButton)findViewById(R.id.btnSelect);
//            final ImageButton btnCrop = (ImageButton)findViewById(R.id.btnCrop);
//            final ImageView imgAttach = (ImageView)findViewById(R.id.imgAttach);
//
//            btnSelect.setVisibility(View.GONE);
//            btnCrop.setVisibility(View.VISIBLE);
//            imgSelected.setImageBitmap(null);
//            imgSelected.setVisibility(View.GONE);
//            mCropImageView.setVisibility(View.VISIBLE);
//
//            btnSelect.setOnClickListener(new View.OnClickListener() {
//                public void onClick(View popupView) {
//                    idAddForm.setVisibility(View.VISIBLE);
//                    btnSave.setVisibility(View.VISIBLE);
//                    idImagePicker.setVisibility(View.GONE);
//                }
//
//            });
//            btnCancel.setOnClickListener(new View.OnClickListener() {
//                public void onClick(View popupView) {
//                    imgScreenshot = null;
//                    //imgSelected.setVisibility(View.GONE);
//                    //imgAttach.setImageBitmap(null);
//                    //imgAttach.setVisibility(View.GONE);
//                    btnSave.setVisibility(View.GONE);
//                    idAddForm.setVisibility(View.VISIBLE);
//                    idImagePicker.setVisibility(View.GONE);
//                }
//            });
//            btnCrop.setOnClickListener(new View.OnClickListener() {
//                public void onClick(View popupView) {
//                    Bitmap cropped = mCropImageView.getCroppedImage(250, 250);
//                    if (cropped != null) {
//                        //imgScreenshot = BitMapToString(cropped);
//                        imageBytes = BitMapToByteArray(cropped);
//                        imgSelected.setImageBitmap(cropped);
//                        cropped = Bitmap.createScaledBitmap(cropped, 250, 250, true);
//                        imgAttach.setImageBitmap(cropped);
//                        imgAttach.setVisibility(View.VISIBLE);
//                        cropped = null;
//                        System.gc();
//                        btnSelect.setVisibility(View.VISIBLE);
//                        btnCrop.setVisibility(View.GONE);
//                        imgSelected.setVisibility(View.VISIBLE);
//                        mCropImageView.setVisibility(View.GONE);
////                      invalidate();
//                    }
//                }
//            });
//        }
//        catch (Exception ex){
//            String s=null;
//            //todo
//        }
//    }
//    public byte[] BitMapToByteArray(Bitmap bitmap) {
//        byte[] byteArray = null;
//        try {
//            System.gc();
//            ByteArrayOutputStream imageBytes = new ByteArrayOutputStream();
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, imageBytes);
//            byteArray = imageBytes.toByteArray();
//        }
//        catch(Exception e){
//            e.printStackTrace();
//        }
//        return byteArray;
//    }
}
//
//   java.lang.SecurityException: Permission Denial: starting Intent { act=android.media.action.IMAGE_CAPTURE cmp=com.android.camera2/com.android.camera.CaptureActivity } from ProcessRecord{512189 1728:com.example.employeedetails/u0a64} (pid=1728, uid=10064) with revoked permission android.permission.CAMERA