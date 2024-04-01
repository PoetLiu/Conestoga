package com.peng.project2;

import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;

@RunWith(AndroidJUnit4.class)
public class StorageTest {

    @Test
    public void downloadTest() {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();

        StorageReference imgRef = storageRef.child(
                "images/custom_nike_air_force_1_low_by_you.jpeg");
        File localFile = null;
        try {
            localFile = File.createTempFile("images", "jpg");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        imgRef.getFile(localFile).addOnSuccessListener(taskSnapshot -> {
            // Local temp file has been created
            Log.i("Main", "Download img succeed, task:%s" + taskSnapshot.toString());
        }).addOnFailureListener(exception -> {
            // Handle any errors
            Log.e("Main", "Download img failed", exception);
        });
    }
}
