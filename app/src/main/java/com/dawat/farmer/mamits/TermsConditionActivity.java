package com.dawat.farmer.mamits;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.dawat.farmer.mamits.databinding.ActivityTermsConditionBinding;

import se.warting.signatureview.utils.SignaturePadBindingAdapter;

public class TermsConditionActivity extends AppCompatActivity {

    private ActivityTermsConditionBinding binding;
    private SignaturePadBindingAdapter signaturePadBindingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityTermsConditionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnContinue.setOnClickListener(v -> {
            /*Bitmap bitmap = binding.signPad.getTransparentSignatureBitmap(true);
            Toast.makeText(this, "Saved", Toast.LENGTH_LONG).show();*/
            binding.signPad.clear();
            startActivity(new Intent(this, MainActivity.class));
            finishAffinity();
        });
    }
}