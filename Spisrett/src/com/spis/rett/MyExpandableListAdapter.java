package com.spis.rett;

import java.util.ArrayList;

import com.spis.rett.model.search.Child;
import com.spis.rett.model.search.Parent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

public class MyExpandableListAdapter extends BaseExpandableListAdapter
{
     

    private LayoutInflater inflater;
    ArrayList<Parent> parents;
    Context context;
    private int ChildClickStatus=-1;
    private int ParentClickStatus=-1;
    public MyExpandableListAdapter(Context context ,ArrayList<Parent> parent)
    {
        inflater =( (Activity)context).getLayoutInflater();
        this.context=context;
        this.parents=parent;
    }
      
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, 
            View convertView, ViewGroup parentView)
    {
        
		final Parent parent = parents.get(groupPosition);
         
        convertView = inflater.inflate(R.layout.grouprow, parentView, false); 
         
        ((TextView) convertView.findViewById(R.id.text_category)).setText(parent.getText1());
        ImageView image=(ImageView)convertView.findViewById(R.id.image);
         

         

         
        return convertView;
    }

     
    // This Function used to inflate child rows view
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, 
            View convertView, ViewGroup parentView)
    {
        final Parent parent = parents.get(groupPosition);
        final Child child = parent.getChildren().get(childPosition);
         
        convertView = inflater.inflate(R.layout.childrow, parentView, false);
         
        ((TextView) convertView.findViewById(R.id.textProductTitle)).setText(child.getText1());
        ImageView image=(ImageView)convertView.findViewById(R.id.image);
        int id = context.getResources().getIdentifier(child.getImageName(), "drawable", context.getPackageName());
            image.setImageResource(id);

            convertView.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Log.i("xZing","clicked");
					Intent intent=new Intent(context, ProductDetailActivity.class);
					intent.putExtra("PRODUCT_CODE", child.getProductCode());
					context.startActivity(intent);
				}
			});
         
        return convertView;
    }

     
    @Override
    public Object getChild(int groupPosition, int childPosition)
    {
        return parents.get(groupPosition).getChildren().get(childPosition);
    }

    //Call when child row clicked
    @Override
    public long getChildId(int groupPosition, int childPosition)
    {
        /****** When Child row clicked then this function call *******/
         
        if( ChildClickStatus!=childPosition)
        {
           ChildClickStatus = childPosition;
            
        }  
         
        return childPosition;
    }

    @Override
    public int getChildrenCount(int groupPosition)
    {
        int size=0;
        if(parents.get(groupPosition).getChildren()!=null)
            size = parents.get(groupPosition).getChildren().size();
        return size;
    }
  
     
    @Override
    public Object getGroup(int groupPosition)
    {
        Log.i("Parent", groupPosition+"=  getGroup ");
         
        return parents.get(groupPosition);
    }

    @Override
    public int getGroupCount()
    {
        return parents.size();
    }

    //Call when parent row clicked
    @Override
    public long getGroupId(int groupPosition)
    {
        Log.i("Parent", groupPosition+"=  getGroupId "+ParentClickStatus);
         

         
        ParentClickStatus=groupPosition;
        if(ParentClickStatus==0)
            ParentClickStatus=-1;
         
        return groupPosition;
    }

    @Override
    public void notifyDataSetChanged()
    {
        // Refresh List rows
        super.notifyDataSetChanged();
    }

    @Override
    public boolean isEmpty()
    {
        return ((parents == null) || parents.isEmpty());
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition)
    {
        return true;
    }

    @Override
    public boolean hasStableIds()
    {
        return true;
    }

    @Override
    public boolean areAllItemsEnabled()
    {
        return true;
    }
     
}