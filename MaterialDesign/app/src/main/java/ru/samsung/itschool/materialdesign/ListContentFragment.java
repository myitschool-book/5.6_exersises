package ru.samsung.itschool.materialdesign;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by d.yacenko on 03.04.19.
 * based on materilas from The Android Open Source Project
 */

public class ListContentFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);
        MyContentAdapter adapter = new MyContentAdapter(recyclerView.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return recyclerView;
    }
public static class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView teacher;
        public TextView name;
        public TextView description;
        public MyViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.fragment_list, parent, false));
            teacher = (ImageView) itemView.findViewById(R.id.list_avatar);
            name = (TextView) itemView.findViewById(R.id.list_title);
            description = (TextView) itemView.findViewById(R.id.list_desc);
            itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, DetailActivity.class);
                context.startActivity(intent);
            }
        });
        }
    }
    /**
     * Adapter для отображения recycler view.
     */
    public static class MyContentAdapter extends RecyclerView.Adapter<MyViewHolder> {
        // кол-во в RecyclerView.
        private static final int LENGTH = 18;
        private final String[] mProjects;
        private final String[] mProjectDesc;
        private final Drawable[] mProjectTeachers;
        public MyContentAdapter(Context context) {
            Resources resources = context.getResources();
            mProjects = resources.getStringArray(R.array.projects);
            mProjectDesc = resources.getStringArray(R.array.project_desc);
            TypedArray a = resources.obtainTypedArray(R.array.project_teacher);
            mProjectTeachers = new Drawable[a.length()];
            for (int i = 0; i < mProjectTeachers.length; i++) {
                mProjectTeachers[i] = a.getDrawable(i);
            }
            a.recycle();
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.teacher.setImageDrawable(mProjectTeachers[position % mProjectTeachers.length]);
            holder.name.setText(mProjects[position % mProjects.length]);
            holder.description.setText(mProjectDesc[position % mProjectDesc.length]);
        }

        @Override
        public int getItemCount() {
            return LENGTH;
        }
    }
}
