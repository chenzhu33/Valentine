package com.hippo.merry;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.List;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GridViewAdapter extends BaseAdapter {

	private Context mContext;
	private List<String> mContent;

	public GridViewAdapter(Context context, List<String> content) {
		mContext = context;
		mContent = content;
	}

	@Override
	public int getCount() {
		return mContent.size();
	}

	@Override
	public Object getItem(int arg0) {
		return mContent.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		GridViewHolder gridholder;
		if (arg1 == null) {

			arg1 = View.inflate(mContext, R.layout.adapter_gridview, null);
			gridholder = new GridViewHolder();
			gridholder.t = (TextView) arg1.findViewById(R.id.grid_text);
			gridholder.i = (ImageView) arg1.findViewById(R.id.grid_image);
			arg1.setTag(gridholder);
		} else {
			gridholder = (GridViewHolder) arg1.getTag();
		}
		gridholder.t.setText(mContent.get(arg0));
		try {
			gridholder.i.setImageBitmap(BitmapFactory
					.decodeStream(new BufferedInputStream(mContext.getAssets().open(
							"icon"+arg0+".png"))));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return arg1;
	} 

	private class GridViewHolder {
		public TextView t;
		public ImageView i;
	}

}
