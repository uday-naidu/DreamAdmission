package com.dreamadmission;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;

public class ELVAdapter extends BaseExpandableListAdapter {
	

	private Context context;
	private ArrayList<Groupe> groupes;
	private LayoutInflater inflater;
	private String college_name,phone;
	private int i=0;
	
	public ELVAdapter(Context context, ArrayList<Groupe> groupes) {
		this.context = context;
		this.groupes = groupes;
		inflater = LayoutInflater.from(context);
	}
	
	@Override
	public boolean areAllItemsEnabled() {
		return true;
	}
	
	public Object getChild(int gPosition, int cPosition) {
		return groupes.get(gPosition).getObjets().get(cPosition);
	}

	public long getChildId(int gPosition, int cPosition) {
		return cPosition;
	}

	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
		final Objet objet = (Objet) getChild(groupPosition, childPosition);
		
		ChildViewHolder childViewHolder;
		
        if (convertView == null) {
        	childViewHolder = new ChildViewHolder();
        	
            convertView = inflater.inflate(R.layout.group_child, null);
            
            childViewHolder.textViewChild = (TextView) convertView.findViewById(R.id.TVChild);
            childViewHolder.buttonChild = (Button) convertView.findViewById(R.id.BTChild);
            childViewHolder.buttoncall =(Button ) convertView.findViewById(R.id.buttoncall);
            convertView.setTag(childViewHolder);
        } else {
        	childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        
        childViewHolder.textViewChild.setText(objet.getNom());
        //if(i%2==0)
        //{
        	childViewHolder.buttonChild.setText("map");
        	childViewHolder.buttoncall.setText("call");
        //}
        //Toast.makeText(context,""+i,Toast.LENGTH_SHORT).show();
        //i++;}//objet.getNom());
        /*else
        {childViewHolder.buttonChild.setText("call");
        Toast.makeText(context,""+i,Toast.LENGTH_SHORT).show();
        i++;}*/
        final int a=groupPosition;
        //final int b=childPosition;
        
        childViewHolder.buttonChild.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				//Toast.makeText(context, "Groupe : " + objet.getGroupe().getNom() + " - Bouton : " + objet.getNom(), Toast.LENGTH_SHORT).show();
				//Toast.makeText(context, ""+a+" "+b,Toast.LENGTH_SHORT).show();
				//if(b==0)
				//{
					Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:" +TestexpandableActivity.latitude[a] +"," +TestexpandableActivity.longitude[a] +"?q=" +TestexpandableActivity.latitude[a] +","+TestexpandableActivity.longitude[a]+"(" +TestexpandableActivity.colleges[a]+")")); 
					context.startActivity(intent);	
				//}
				//else
				//{
					
			//	}	
			}
		});
        childViewHolder.buttoncall.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+TestexpandableActivity.phoneno[a]));
				context.startActivity(intent);		
				
				
			}
			
        });
        
        return convertView;
	}

	public int getChildrenCount(int gPosition) {
		return groupes.get(gPosition).getObjets().size();
	}

	public Object getGroup(int gPosition) {
		return groupes.get(gPosition);
	}

	public int getGroupCount() {
		return groupes.size();
	}

	public long getGroupId(int gPosition) {
		return gPosition;
	}

	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		GroupViewHolder gholder;
		
		Groupe group = (Groupe) getGroup(groupPosition);
		
        if (convertView == null) {
        	gholder = new GroupViewHolder();
        	
        	convertView = inflater.inflate(R.layout.group_row, null);
        	
        	gholder.textViewGroup = (TextView) convertView.findViewById(R.id.TVGroup);
        	
        	convertView.setTag(gholder);
        } else {
        	gholder = (GroupViewHolder) convertView.getTag();
        }
        
        gholder.textViewGroup.setText(group.getNom());
        
        return convertView;
	}

	public boolean hasStableIds() {
		return true;
	}

	public boolean isChildSelectable(int arg0, int arg1) {
		return true;
	}
	
	class GroupViewHolder {
		public TextView textViewGroup;
	}
	
	class ChildViewHolder {
		public TextView textViewChild;
		public Button buttonChild;
		public Button buttoncall; 
	}

}
