package com.example.employeedetails.Fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import androidx.appcompat.app.AppCompatActivity;


import android.util.Base64;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.example.employeedetails.R;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class MyProfileChangePhoto extends DialogFragment implements View.OnClickListener{

    private View rootView;
    private CropImageView mCropImageView;
    private Uri mCropImageUri;
    private PopupWindow popUpWindow;
    private PopupWindow pw;
    private RelativeLayout idImagePicker;
    private LinearLayout idAddForm;
    private byte[] imageBytes;
    private String imgScreenshot = null;
//    private static String WEB_SERVICE_URL = Bitmap.Config.BASE_URL + Config.SUBMIT_PHOTO;
    private ProgressDialog progressDialog = null;
    private Button btnSave = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_my_profile_change_photo, container, false);

        idAddForm = (LinearLayout) rootView.findViewById(R.id.ltMainContainer);
        idImagePicker = (RelativeLayout) rootView.findViewById(R.id.idImagePicker);
        Button btnChangePhoto = (Button) rootView.findViewById(R.id.btnChangePhoto);
        btnSave = (Button) rootView.findViewById(R.id.btnSave);
        btnChangePhoto.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        return rootView;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    public void onClick(final View rootView) {
        popUpWindow = new PopupWindow(getActivity());
        try {
            switch (rootView.getId()) {
                case R.id.imgAttach:
                    showSelectedImage();
                    break;
                case R.id.btnChangePhoto:
                    startActivityForResult(getPickImageChooserIntent(), 200);
                    break;
                case R.id.btnSave:
//                    submitProfilePhoto(rootView);
                    break;
            }
        }
        catch (Exception ex){
            //todo
        }
    }

    @Override
    @SuppressLint("NewApi")
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == AppCompatActivity.RESULT_OK) {
            Uri imageUri = CropImage.getPickImageResultUri(getActivity(), data);
            // For API >= 23 we need to check specifically that we have permissions to read external storage,
            // but we don't know if we need to for the URI so the simplest is to try open the stream and see if we get error.
            boolean requirePermissions = false;
            if (CropImage.isReadExternalStoragePermissionsRequired(getActivity(), imageUri)) {
                // request permissions and handle the result in onRequestPermissionsResult()
                requirePermissions = true;
                mCropImageUri = imageUri;
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, CropImage.PICK_IMAGE_PERMISSIONS_REQUEST_CODE);
            }
            else {
                showCroppedImage(imageUri);
            }
        }
    }


    public void showCroppedImage(Uri imageUri){
        try {
            idAddForm.setVisibility(View.GONE);
            idImagePicker.setVisibility(View.VISIBLE);
            final ImageView imgSelected = (ImageView) rootView.findViewById(R.id.imgSelected);
            //Manage image Crop
            mCropImageView = (CropImageView) rootView.findViewById(R.id.imgCropImage);
            mCropImageView.setImageUriAsync(imageUri);
            ImageButton btnCancel = (ImageButton) rootView.findViewById(R.id.btnCancel);
            final ImageButton btnSelect = (ImageButton) rootView.findViewById(R.id.btnSelect);
            final ImageButton btnCrop = (ImageButton) rootView.findViewById(R.id.btnCrop);
            final ImageView imgAttach = (ImageView) rootView.findViewById(R.id.imgAttach);

            btnSelect.setVisibility(View.GONE);
            btnCrop.setVisibility(View.VISIBLE);
            imgSelected.setImageBitmap(null);
            imgSelected.setVisibility(View.GONE);
            mCropImageView.setVisibility(View.VISIBLE);

            btnSelect.setOnClickListener(new View.OnClickListener() {
                public void onClick(View popupView) {
                    idAddForm.setVisibility(View.VISIBLE);
                    btnSave.setVisibility(View.VISIBLE);
                    idImagePicker.setVisibility(View.GONE);
                }

            });
            btnCancel.setOnClickListener(new View.OnClickListener() {
                public void onClick(View popupView) {
                    imgScreenshot = null;
                    //imgSelected.setVisibility(View.GONE);
                    //imgAttach.setImageBitmap(null);
                    //imgAttach.setVisibility(View.GONE);
                    btnSave.setVisibility(View.GONE);
                    idAddForm.setVisibility(View.VISIBLE);
                    idImagePicker.setVisibility(View.GONE);
                }
            });
            btnCrop.setOnClickListener(new View.OnClickListener() {
                public void onClick(View popupView) {
                    Bitmap cropped = mCropImageView.getCroppedImage(250, 250);
                    if (cropped != null) {
                        //imgScreenshot = BitMapToString(cropped);
                        imageBytes = BitMapToByteArray(cropped);
                        imgSelected.setImageBitmap(cropped);
                        cropped = Bitmap.createScaledBitmap(cropped, 250, 250, true);
                        imgAttach.setImageBitmap(cropped);
                        imgAttach.setVisibility(View.VISIBLE);
                        cropped = null;
                        System.gc();
                        btnSelect.setVisibility(View.VISIBLE);
                        btnCrop.setVisibility(View.GONE);
                        imgSelected.setVisibility(View.VISIBLE);
                        mCropImageView.setVisibility(View.GONE);
                        rootView.invalidate();
                    }
                }
            });
        }
        catch (Exception ex){
            String s=null;
            //todo
        }
    }

    public byte[] BitMapToByteArray(Bitmap bitmap) {
        byte[] byteArray = null;
        try {
            System.gc();
            ByteArrayOutputStream imageBytes = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, imageBytes);
            byteArray = imageBytes.toByteArray();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return byteArray;
    }

    public String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100, baos);
        byte [] byteArray = baos.toByteArray();
        String imgBase64 = null;
        try{
            System.gc();
            if(bitmap.getHeight() <= 250 && bitmap.getWidth() <= 250) {
                imgBase64 = Base64.encodeToString(byteArray, Base64.DEFAULT);
            }
            else {
                bitmap = Bitmap.createScaledBitmap(bitmap, 250, 250, false);
                imgBase64 = Base64.encodeToString(byteArray, Base64.DEFAULT);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        catch(OutOfMemoryError e){
            //"Out of memory error catched"
            baos = new  ByteArrayOutputStream();
            bitmap = Bitmap.createScaledBitmap(bitmap, 250, 250, false);
            bitmap.compress(Bitmap.CompressFormat.JPEG,70, baos);
            byteArray = baos.toByteArray();
            imgBase64 = Base64.encodeToString(byteArray, Base64.DEFAULT);
        }
        System.gc();
        return imgBase64;
    }

    public void showSelectedImage(){
        try {
            idAddForm.setVisibility(View.GONE);
            idImagePicker.setVisibility(View.VISIBLE);
            final ImageView imgSelected = (ImageView) rootView.findViewById(R.id.imgSelected);
            ImageButton btnCancel = (ImageButton) rootView.findViewById(R.id.btnCancel);
            final ImageButton btnSelect = (ImageButton) rootView.findViewById(R.id.btnSelect);
            final ImageButton btnCrop = (ImageButton) rootView.findViewById(R.id.btnCrop);
            imgSelected.setImageBitmap(null);
            btnSelect.setVisibility(View.GONE);
            btnCrop.setVisibility(View.GONE);
            if (imageBytes != null) {
                Bitmap bmp = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                imgSelected.setImageBitmap(bmp);
                imgSelected.setVisibility(View.VISIBLE);
                bmp  = null;
            }
            btnCancel.setOnClickListener(new View.OnClickListener() {
                public void onClick(View popupView) {
                    idAddForm.setVisibility(View.VISIBLE);
                    idImagePicker.setVisibility(View.GONE);
                }
            });
        }
        catch (Exception ex){
            String s=null;
            //todo
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == CropImage.CAMERA_CAPTURE_PERMISSIONS_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                CropImage.startPickImageActivity(getActivity());
            }
        }
        if (requestCode == CropImage.PICK_IMAGE_PERMISSIONS_REQUEST_CODE) {
            if (mCropImageUri != null && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showCroppedImage(mCropImageUri);
            }
        }
    }


    // Create a chooser intent to select the source to get image from.
    public Intent getPickImageChooserIntent() {
        Intent chooserIntent = null;
        try {
            Uri outputFileUri = getCaptureImageOutputUri();

            List<Intent> allIntents = new ArrayList<>();
            PackageManager packageManager = getActivity().getPackageManager();

            // collect all camera intents
            Intent captureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);
            for (ResolveInfo res : listCam) {
                Intent intent = new Intent(captureIntent);
                intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
                intent.setPackage(res.activityInfo.packageName);
                if (outputFileUri != null) {
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
                }
                allIntents.add(intent);
            }

            // collect all gallery intents
            Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
            galleryIntent.setType("image/*");
            List<ResolveInfo> listGallery = packageManager.queryIntentActivities(galleryIntent, 0);
            for (ResolveInfo res : listGallery) {
                Intent intent = new Intent(galleryIntent);
                intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
                intent.setPackage(res.activityInfo.packageName);
                allIntents.add(intent);
            }

            // the main intent is the last in the list
            Intent mainIntent = allIntents.get(allIntents.size() - 1);
            for (Intent intent : allIntents) {
                if (intent.getComponent().getClassName().equals("com.android.documentsui.DocumentsActivity")) {
                    mainIntent = intent;
                    break;
                }
            }
            allIntents.remove(mainIntent);

            // Create a chooser from the main intent
            chooserIntent = Intent.createChooser(mainIntent, "Select Image");

            // Add all other intents
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, allIntents.toArray(new Parcelable[allIntents.size()]));
        }
        catch (Exception e) {
            // e.printStackTrace();
        }
        return chooserIntent;
    }

    //Get URI to image received from capture by camera.
    private Uri getCaptureImageOutputUri() {
        Uri outputFileUri = null;
        try {
            File getImage = getActivity().getExternalCacheDir();
            if (getImage != null) {
                outputFileUri = Uri.fromFile(new File(getImage.getPath(), "pickImageResult.jpeg"));
            }
        }
        catch (Exception e) {
            // e.printStackTrace();
        }
        return outputFileUri;
    }

    //Get the URI of the selected image from {@link #getPickImageChooserIntent()}
    public Uri getPickImageResultUri(Intent data) {
        boolean isCamera = true;
        try {
            if (data != null && data.getData() != null) {
                String action = data.getAction();
                isCamera = action != null && action.equals(MediaStore.ACTION_IMAGE_CAPTURE);
            }
        }
        catch (Exception e) {
            // e.printStackTrace();
        }
        return isCamera ? getCaptureImageOutputUri() : data.getData();
    }

    public boolean isUriRequiresPermissions(Uri uri) {
        try {
            ContentResolver resolver = getActivity().getContentResolver();
            InputStream stream = resolver.openInputStream(uri);
            stream.close();
            return false;
        }
        catch (FileNotFoundException e) {
//            if (e.getCause() instanceof ErrnoException) {
//                return true;
//            }
        }
        catch (Exception e) {
            //catch
        }
        return false;
    }

//    private void submitProfilePhoto(final View rootView){
//        final FragmentManager manager = getActivity().getSupportFragmentManager();
//        final FragmentTransaction transaction = manager.beginTransaction();
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            progressDialog = new ProgressDialog(getActivity(),R.style.MyDialogTheme);
//        } else {
//            progressDialog = new ProgressDialog(getActivity());
//        }
//        try {
//            final View rootView1 = getView();
//            // Get Volley
//            RequestQueue mQueue = Volley.newRequestQueue(getActivity());
//            VolleyMultipartRequest stringRequest = new  VolleyMultipartRequest(Request.Method.POST, WEB_SERVICE_URL,
//                    new Response.Listener<NetworkResponse>() {
//                        @Override
//                        public void onResponse(NetworkResponse response) {
//                            String resultResponse = new String(response.data);
//                            try {
//                                String result = GeneralFunctions.isAuthenticated(resultResponse);
//                                JSONObject jsonObject = null;
//                                final Bundle bundles = new Bundle();
//                                switch (result.toLowerCase()) {
//                                    case "success":
//                                        GeneralFunctions.hideLoader(progressDialog);
//                                        jsonObject = new JSONObject(resultResponse);
//                                        String resultString = (String) jsonObject.get("message");
//                                        if (resultString.equalsIgnoreCase("success")) {
//                                            GeneralFunctions.showSnackbar("Record saved successfully.", getView());
//                                            String imgPhoto = Base64.encodeToString(imageBytes, Base64.DEFAULT);
//                                            final MySession mySession = new MySession(getActivity().getApplicationContext());
//                                            mySession.setProfileImage(GeneralFunctions.aes_encrypt(imgPhoto));
//
//                                            manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
//                                            Fragment fragment = new MyProfileFragment();
//                                            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
//                                            transaction.replace(R.id.container, fragment);
//                                            transaction.addToBackStack("myprofile");
//                                            transaction.commit();
//
//                                        }
//                                        else {
//                                            GeneralFunctions.showSnackbar("Problem while saving the record. Try again", getView());
//                                        }
//                                        break;
//                                    case "failed":
//                                        break;
//                                    case "unauthorized":
//                                        GeneralFunctions.hideLoader(progressDialog);
//                                        String mresult = GeneralFunctions.isAuthenticated(resultResponse);
//                                        String strStatus = GeneralFunctions.getResponseStatus(resultResponse);
//                                        if(Config.EXCEPTTIONS.contains(strStatus)){
//                                            GeneralFunctions.showException(rootView1, strStatus.toUpperCase(), getActivity());
//                                        }
//                                        else {
//                                            GeneralFunctions.showSnackbar(strStatus, getView());
//                                            //GeneralFunctions.showException(rootView1, strStatus.toUpperCase(), getActivity());
//                                        }
//                                        break;
//                                }
//                            }
//                            catch (Exception ex) {
//                                String s="";
//                                // Catch exception here
//                            }
//                        }
//                    },
//                    new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError volleyError) {
//                            try {
//                                String responseBody = new String(volleyError.networkResponse.data, "utf-8");
//                                GeneralFunctions.hideLoader(progressDialog);
//                                String result = GeneralFunctions.isAuthenticated(responseBody);
//                                if (result.equalsIgnoreCase("failed")) {
//                                    GeneralFunctions.showException(rootView1, "INVALID_USER", getActivity());
//                                }
//                                String strStatus = GeneralFunctions.getResponseStatus(responseBody);
//                                if(Config.EXCEPTTIONS.contains(strStatus)){
//                                    GeneralFunctions.showException(rootView1, strStatus.toUpperCase(), getActivity());
//                                }
//                            }
//                            catch (Exception e) {
//                                //todo
//                            }
//                        }
//                    }) {
//                @Override
//                protected Map<String, String> getParams() {
//                    Map<String, String> params = new HashMap<String, String>();
//                    MySession mySession = new MySession(getActivity().getApplicationContext());
//                    params.put("userid", mySession.getUniqueId());
//                    params.put("username", mySession.getUserName());
//                    params.put("passwd", mySession.getPassword());
//                    params.put("expirydate", GeneralFunctions.aes_decrypt(mySession.getPasswordExpiryDate()));
//                    final String user = GeneralFunctions.aes_decrypt(mySession.getUniqueId());
//                    final String paramN = GeneralFunctions.getSha2Hash(user);
//                    params.put("paramN", paramN);
//                    params.put("version", Config.MVERSION);
//                    return params;
//                }
//
//                @Override
//                protected Map<String, DataPart> getByteData() {
//                    Map<String, DataPart> params = new HashMap<>();
//                    if (imageBytes != null) {
//                        params.put("profileimage", new VolleyMultipartRequest.DataPart("file_new.jpg", imageBytes, "image/jpg"));
//                    }
//                    return params;
//                }
//            };
//            stringRequest.setRetryPolicy(new DefaultRetryPolicy(30 * 1000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//            VolleySingleton.getInstance(getActivity().getBaseContext()).addToRequestQueue(stringRequest);
//            GeneralFunctions.showLoader(rootView, progressDialog, getActivity(),"Saving, please wait..");
//        }
//        catch (Exception ex){
//            String g="";
//        }
//    }
}