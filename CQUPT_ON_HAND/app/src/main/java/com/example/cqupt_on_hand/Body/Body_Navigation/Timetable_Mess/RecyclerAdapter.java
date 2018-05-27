package com.example.cqupt_on_hand.Body.Body_Navigation.Timetable_Mess;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.cqupt_on_hand.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by 郝书逸 on 2018/5/22.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

    //添加数据，如每个item中显示的图片文字或其url
    ArrayList<Class_detail> class_details;
    Context context;
    public RecyclerAdapter(ArrayList<Class_detail> class_details, Context context){
        this.class_details = class_details;
        this.context=context;
    }
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView classroom;
        CardView cardView;
        public ViewHolder(View view) {
            super(view);
            cardView=(CardView)view.findViewById(R.id.card);
            name=(TextView)view.findViewById(R.id.class_name);
            classroom=(TextView)view.findViewById(R.id.class_room);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.classitem,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }
    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(final RecyclerAdapter.ViewHolder holder, int position) {
        if (class_details.get(position).getName()==null){
            holder.cardView.setAlpha(00);
        }else {
            switch (class_details.get(position).getNumber()){
                case 0:
                case 1:
                    holder.cardView.setCardBackgroundColor(ContextCompat.getColor(context,R.color.redclass));
                    break;
                case 2:
                case 3:
                    holder.cardView.setCardBackgroundColor(ContextCompat.getColor(context,R.color.yellowclass));
                    break;
                default:
                    holder.cardView.setCardBackgroundColor(ContextCompat.getColor(context,R.color.blueclass));
            }
            holder.name.setText(class_details.get(position).getName());
            holder.classroom.setText(class_details.get(position).getClassroom()+"");
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, "onClick: 111111111111111111111111");
                    holder.getAdapterPosition();
                    View popupView = LayoutInflater.from(context).inflate(R.layout.popupwindow, null);
                    PopupWindow popupWindow = new PopupWindow( ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    popupWindow.setContentView(popupView);
                    popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
                    popupWindow.setOutsideTouchable(true);
                    popupWindow.setClippingEnabled(true);
                    popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                        @Override public void onDismiss() {
                        } });

                }
            });
        }
    }


    @Override
    public int getItemCount() {
        //获取item的数量（可以从url数量获得）
        return 42;
    }

}
