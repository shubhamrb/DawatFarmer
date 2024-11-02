package com.dawat.farmer.mamits.ui.farmerProfile.farmDetails;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static com.dawat.farmer.mamits.utils.AppConstant.PREF_KEY_ACCESS_TOKEN;
import static com.dawat.farmer.mamits.utils.AppConstant.PREF_NAME;
import static com.dawat.farmer.mamits.utils.AppConstant.SHARED_PREF_NAME;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dawat.farmer.mamits.R;
import com.dawat.farmer.mamits.adapter.IrrigationSourcesAdapter;
import com.dawat.farmer.mamits.databinding.FragmentFieldDetailsBinding;
import com.dawat.farmer.mamits.model.AttachmentModel;
import com.dawat.farmer.mamits.model.CommentsModel;
import com.dawat.farmer.mamits.model.IrrigationSourcesModel;
import com.dawat.farmer.mamits.model.LandDetailsModel;
import com.dawat.farmer.mamits.remote.ApiHelper;
import com.dawat.farmer.mamits.ui.messages.PlayerActivity;
import com.dawat.farmer.mamits.utils.AppConstant;
import com.dawat.farmer.mamits.utils.CustomLinearLayoutManager;
import com.dawat.farmer.mamits.utils.ResponseListener;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FieldDetailsFragment extends Fragment implements OnMapReadyCallback {

    private FragmentFieldDetailsBinding binding;
    private SharedPreferences sharedPreferences;
    private String strToken = "", user_name = "";
    private String farm_id, field_id, farm_code, field_code;
    private LandDetailsModel landDetailsModel;
    private IrrigationSourcesAdapter irrigationSourcesAdapter;
    private GoogleMap googleMap;
    List<LatLng> pontos = new ArrayList<>();
    private Polyline polyline;
    private ActivityResultLauncher<String[]> requestPermissionLauncher;
    private CommentsModel commentsModel;
    private List<AttachmentModel> imageArray;

    public FieldDetailsFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFieldDetailsBinding.inflate(inflater, container, false);
        sharedPreferences = getContext().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        strToken = sharedPreferences.getString(PREF_KEY_ACCESS_TOKEN, "");
        user_name = sharedPreferences.getString(PREF_NAME, "");
        View root = binding.getRoot();
        binding.txtSubHeading.setText(user_name + " फार्म विवरण");
        Bundle bundle = getArguments();
        imageArray = new ArrayList<>();
        if (bundle != null) {
            farm_id = bundle.getString("farm_id");
            field_id = bundle.getString("field_id");
            farm_code = bundle.getString("farm_code");
            field_code = bundle.getString("field_code");
            binding.txtFarmCodeHeading.setText("Farm Code " + farm_code);
            binding.txtFarmName.setText("Field " + field_code);
        }
        requestPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), result -> {
            boolean allPermissionsGranted = true;
            for (Boolean granted : result.values()) {
                if (!granted) {
                    allPermissionsGranted = false;
                    break;
                }
            }
            if (allPermissionsGranted) {
                showRoutes();
            } else {
                Toast.makeText(getContext(), "Permissions required", Toast.LENGTH_SHORT).show();
            }
        });


        requestLocationPermission();

        binding.mapView.onCreate(savedInstanceState);
        binding.mapView.getMapAsync(this);
        clickListeners();
        getFarmLandList();
        return root;
    }

    private void clickListeners() {
        binding.rlImg1.setOnClickListener(v -> {
            try {
                startActivity(new Intent(getContext(), PlayerActivity.class)
                        .putExtra("file_type", landDetailsModel.getComments().getAttachment().get(0).getFile_type())
                        .putExtra("filepath", landDetailsModel.getComments().getAttachment().get(0).getAttachment()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        binding.rlImg2.setOnClickListener(v -> {
            try {
                startActivity(new Intent(getContext(), PlayerActivity.class)
                        .putExtra("file_type", landDetailsModel.getComments().getAttachment().get(1).getFile_type())
                        .putExtra("filepath", landDetailsModel.getComments().getAttachment().get(1).getAttachment()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        binding.rlImg3.setOnClickListener(v -> {
            try {
                startActivity(new Intent(getContext(), PlayerActivity.class)
                        .putExtra("file_type", landDetailsModel.getComments().getAttachment().get(2).getFile_type())
                        .putExtra("filepath", landDetailsModel.getComments().getAttachment().get(2).getAttachment()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        binding.rlImg4.setOnClickListener(v -> {
            try {
                startActivity(new Intent(getContext(), PlayerActivity.class)
                        .putExtra("file_type", landDetailsModel.getComments().getAttachment().get(3).getFile_type())
                        .putExtra("filepath", landDetailsModel.getComments().getAttachment().get(3).getAttachment()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void getFarmLandList() {
        try {
            new ApiHelper().getFarmLandList(strToken, farm_id, field_id, new ResponseListener() {
                @Override
                public void onSuccess(JsonObject jsonObject) {
                    Log.e(AppConstant.LOG_KEY_RESPONSE, jsonObject.toString());
                    Type land = new TypeToken<List<LandDetailsModel>>() {
                    }.getType();

                    List<LandDetailsModel> landList = new Gson().fromJson(jsonObject.get("data").getAsJsonArray().toString(), land);
                    if (landList.size() != 0) {
                        landDetailsModel = landList.get(0);
                        setData();
                    }

                }

                @Override
                public void onFailed(Throwable throwable) {
                    Log.e(AppConstant.LOG_KEY_ERROR, throwable.getMessage());
                    Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception e) {
            Log.e(AppConstant.LOG_KEY_ERROR, e.getMessage());
        }
    }

    private void setData() {
        if (landDetailsModel != null) {
            binding.txtFarmCode.setText(landDetailsModel.getFarm_name());
            binding.txtFieldCode.setText(landDetailsModel.getFarm_field());
            binding.txtState.setText(landDetailsModel.getState());
            binding.txtDistrict.setText(landDetailsModel.getDistrict());
            binding.txtBlock.setText(landDetailsModel.getBlock());
            binding.txtVillage.setText(landDetailsModel.getVillage());
            binding.txtLandOwned.setText(landDetailsModel.getLand_area_owned());
            binding.txtLandLease.setText(landDetailsModel.getLand_area_onlease());
            binding.txtTotalLandArea.setText(landDetailsModel.getLand_area());
            binding.txtLandUnderProject.setText(landDetailsModel.getLand_area_underproject());
            binding.txtLlCost.setText(landDetailsModel.getLl_cost());

            commentsModel = landDetailsModel.getComments();
            showAttachments();

            if (hasLocationPermission()) {
                showRoutes();
            } else {
                requestLocationPermission();
            }
        }
    }

    private void showAttachments() {
        if (commentsModel != null) {
            if (commentsModel.getComment().size() != 0) {
                binding.txtComment.setText(commentsModel.getComment().get(0).getComment());
            }
            if (commentsModel.getAttachment() != null) {
                imageArray = commentsModel.getAttachment();
            }
        }
        binding.txtFile1.setImageResource(R.drawable.image_24);
        binding.txtFile2.setImageResource(R.drawable.image_24);
        binding.txtFile3.setImageResource(R.drawable.image_24);
        binding.txtFile4.setImageResource(R.drawable.image_24);
        binding.rlImg1.setVisibility(View.GONE);
        binding.rlImg2.setVisibility(View.GONE);
        binding.rlImg3.setVisibility(View.GONE);
        binding.rlImg4.setVisibility(View.GONE);

        for (int i = 0; i < imageArray.size(); i++) {
            if (i == 0) {
                if (imageArray.get(i).getFile_type().equalsIgnoreCase("png")
                        || imageArray.get(i).getFile_type().equalsIgnoreCase("jpg")
                        || imageArray.get(i).getFile_type().equalsIgnoreCase("jpeg")) {
                    Glide.with(getContext()).load(imageArray.get(i).getAttachment()).into(binding.txtFile1);
                } else {
                    binding.txtFile1.setImageResource(R.drawable.video_24);
                }
                binding.rlImg1.setVisibility(View.VISIBLE);
            } else if (i == 1) {
                if (imageArray.get(i).getFile_type().equalsIgnoreCase("png")
                        || imageArray.get(i).getFile_type().equalsIgnoreCase("jpg")
                        || imageArray.get(i).getFile_type().equalsIgnoreCase("jpeg")) {
                    Glide.with(getContext()).load(imageArray.get(i).getAttachment()).into(binding.txtFile2);
                } else {
                    binding.txtFile2.setImageResource(R.drawable.video_24);
                }
                binding.rlImg2.setVisibility(View.VISIBLE);
            } else if (i == 2) {
                if (imageArray.get(i).getFile_type().equalsIgnoreCase("png")
                        || imageArray.get(i).getFile_type().equalsIgnoreCase("jpg")
                        || imageArray.get(i).getFile_type().equalsIgnoreCase("jpeg")) {
                    Glide.with(getContext()).load(imageArray.get(i).getAttachment()).into(binding.txtFile3);
                } else {
                    binding.txtFile3.setImageResource(R.drawable.video_24);
                }
                binding.rlImg3.setVisibility(View.VISIBLE);
            } else if (i == 3) {
                if (imageArray.get(i).getFile_type().equalsIgnoreCase("png")
                        || imageArray.get(i).getFile_type().equalsIgnoreCase("jpg")
                        || imageArray.get(i).getFile_type().equalsIgnoreCase("jpeg")) {
                    Glide.with(getContext()).load(imageArray.get(i).getAttachment()).into(binding.txtFile4);
                } else {
                    binding.txtFile4.setImageResource(R.drawable.video_24);
                }
                binding.rlImg4.setVisibility(View.VISIBLE);
            }
        }
    }

    private void showRoutes() {
        if (landDetailsModel != null && landDetailsModel.getGeo_fencing_data().size() > 1) {
            pontos = new ArrayList<>();
            pontos.addAll(landDetailsModel.getGeo_fencing_data());
            drawMap();
        }
    }

    private void drawMap() {
        if (pontos.size() < 2) {
            return; // Ensure you have at least two points to draw the polyline
        }
        LatLngBounds.Builder builder = new LatLngBounds.Builder();

        for (int i = 0; i < pontos.size() - 1; i++) {
            LatLng src = pontos.get(i);
            builder.include(src);
            LatLng dest = pontos.get(i + 1);
            try {
                polyline = googleMap.addPolyline(new PolylineOptions().add(new LatLng(src.latitude, src.longitude), new LatLng(dest.latitude, dest.longitude)).width(20).color(Color.GREEN).geodesic(true));
            } catch (NullPointerException e) {
                Log.e("Error", "NullPointerException onPostExecute: " + e);
            } catch (Exception e2) {
                Log.e("Error", "Exception onPostExecute: " + e2);
            }
        }

        try {
            LatLngBounds bounds = builder.build();
            CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, 100);
            googleMap.moveCamera(cu);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        this.googleMap = googleMap;
        this.googleMap.getUiSettings().setZoomGesturesEnabled(false);
        this.googleMap.getUiSettings().setScrollGesturesEnabled(false);
    }

    private boolean hasLocationPermission() {
        return ActivityCompat.checkSelfPermission(getContext(),
                ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(),
                ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestLocationPermission() {
        String[] PERMISSIONS_TO_REQUEST = {
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
                // Add other permissions you need here
        };
        requestPermissionLauncher.launch(PERMISSIONS_TO_REQUEST);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (binding != null) {
            binding.mapView.onResume();
        }
    }

    @Override
    public void onPause() {
        if (binding != null) {
            binding.mapView.onPause();
        }
        super.onPause();
    }

    @Override
    public void onDestroy() {
        if (binding != null) {
            binding.mapView.onDestroy();
        }
        binding = null;
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        if (binding != null) {
            binding.mapView.onLowMemory();
        }
        super.onLowMemory();
    }
}