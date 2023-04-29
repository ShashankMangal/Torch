package com.sharkBytesLab.torch;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity{

    private ImageButton torch, more;
    private boolean state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        torch = findViewById(R.id.torchButton);
        more = findViewById(R.id.more);

        Dexter.withContext(this).withPermission(Manifest.permission.CAMERA).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {

                runFlashLight();

            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                Toast.makeText(MainActivity.this, "Camera Permission is Required.", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {

                permissionToken.continuePermissionRequest();

            }
        }).check();


        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MenuActivity.class));

            }
        });


    }

    private void runFlashLight()
    {

        torch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!state)
                {
                    CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

                    try
                        {

                            String cameraId = cameraManager.getCameraIdList()[0];
                            cameraManager.setTorchMode(cameraId, true);
                            state = true;
                            torch.setImageResource(R.drawable.torch_on);

                        }catch(Exception e)
                        {

                        }
                    }
                else
                {
                    CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

                    try
                    {

                        String cameraId = cameraManager.getCameraIdList()[0];
                        cameraManager.setTorchMode(cameraId, false);
                        state = false;
                        torch.setImageResource(R.drawable.torch_off);

                    }catch(Exception e)
                    {

                    }


                }



                }


        });

    }

}