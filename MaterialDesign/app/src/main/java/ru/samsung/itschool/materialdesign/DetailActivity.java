package ru.samsung.itschool.materialdesign;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by d.yacenko on 03.04.19.
 * based on materilas from The Android Open Source Project
 */

public class DetailActivity extends AppCompatActivity {
    public static final String POSITION = "position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Установка Collapsing Toolbar на экран
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        int postion = getIntent().getIntExtra(POSITION, 0);
        Resources resources = getResources();
        String[] projects = resources.getStringArray(R.array.projects);
        collapsingToolbar.setTitle(projects[postion % projects.length]);

        String[] projectDetails = resources.getStringArray(R.array.project_details);
        TextView projectDetail = (TextView) findViewById(R.id.project_detail);
        projectDetail.setText(projectDetails[postion % projectDetails.length]);

        String[] projectLocations = resources.getStringArray(R.array.project_locations);
        TextView projectLocation =  (TextView) findViewById(R.id.project_location);
        projectLocation.setText(projectLocations[postion % projectLocations.length]);

        TypedArray projectPictures = resources.obtainTypedArray(R.array.projects_picture);
        ImageView projectPicutre = (ImageView) findViewById(R.id.image);
        projectPicutre.setImageDrawable(projectPictures.getDrawable(postion % projectPictures.length()));

        projectPictures.recycle();
    }
}
