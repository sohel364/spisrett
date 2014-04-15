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
         

         
        //Log.i("onCheckedChanged", "isChecked: "+parent.isChecked());
         
        // Change right check image on parent at runtime
//        if(parent.isChecked()==true){
//            rightcheck.setImageResource(context.
//                 getResources().getIdentifier(
//                      "com.androidexample.customexpandablelist:drawable/rightcheck",null,null));
//        }   
//        else{
//            rightcheck.setImageResource(context.
//                 getResources().getIdentifier(
//                      "com.androidexample.customexpandablelist:drawable/button_check",null,null));
//        }   
         
        // Get grouprow.xml file checkbox elements
//        CheckBox checkbox = (CheckBox) convertView.findViewById(R.id.checkbox);
//        checkbox.setChecked(parent.isChecked());
         
        // Set CheckUpdateListener for CheckBox (see below CheckUpdateListener class)
//        checkbox.setOnCheckedChangeListener(new CheckUpdateListener(parent));
         
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
        //Log.i("Childs", groupPosition+"=  getChild =="+childPosition);
        return parents.get(groupPosition).getChildren().get(childPosition);
    }

    //Call when child row clicked
    @Override
    public long getChildId(int groupPosition, int childPosition)
    {
        /****** When Child row clicked then this function call *******/
         
        //Log.i("Noise", "parent == "+groupPosition+"=  child : =="+childPosition);
        if( ChildClickStatus!=childPosition)
        {
           ChildClickStatus = childPosition;
            
//           Toast.makeText(context.getApplicationContext(), "Parent :"+groupPosition + " Child :"+childPosition , 
//                    Toast.LENGTH_LONG).show();
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
         
        if(groupPosition==2 && ParentClickStatus!=groupPosition){
             
            //Alert to user
            //Toast.makeText(context.getApplicationContext(), "Parent :"+groupPosition , 
                //    Toast.LENGTH_LONG).show();
        }
         
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
     
     
     
    /******************* Checkbox Checked Change Listener ********************/
     
//    private final class CheckUpdateListener implements OnCheckedChangeListener
//    {
//        private final Parent parent;
//         
//        private CheckUpdateListener(Parent parent)
//        {
//            this.parent = parent;
//        }
//        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
//        {
//            Log.i("onCheckedChanged", "isChecked: "+isChecked);
//            parent.setChecked(isChecked);
//             
//            ((MyExpandableListAdapter)getExpandableListAdapter()).notifyDataSetChanged();
//             
//            final Boolean checked = parent.isChecked();
//            Toast.makeText(getApplicationContext(), 
//                  "Parent : "+parent.getName() + " " + (checked ? STR_CHECKED : STR_UNCHECKED), 
//                       Toast.LENGTH_LONG).show();
//        }
//    }
    /***********************************************************************/
     
}