package com.hippo.merry.gallery;

import java.io.File;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import com.hippo.merry.R;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Gallery.LayoutParams;
import android.widget.TextView;
import android.widget.ViewSwitcher.ViewFactory;

public class GalleryActivity extends Activity implements
		OnItemSelectedListener, ViewFactory, OnItemClickListener {

	final String path = "mnt/sdcard/Pic";
	private String str[];
	private CoverFlow cf;
	private int index;

	// �Զ���ͼƬ����䷽ʽ
	public class ImageAdapter extends BaseAdapter {
		int mGalleryItemBackground;
		private Context mContext;

		private HashMap<String, SoftReference<Bitmap>> mImageCache;

		public Bitmap loadBitmapImage(String path) {

			if (mImageCache.containsKey(path)) {

				SoftReference<Bitmap> softReference = mImageCache.get(path);

				Bitmap bitmap = softReference.get();

				if (null != bitmap)

					return bitmap;

			}
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inSampleSize = 4;// ͼƬ��߶�Ϊԭ���Ķ���֮һ����ͼƬΪԭ�����ķ�֮һ

			Bitmap bitmap = BitmapFactory.decodeFile(path, options);

			mImageCache.put(path, new SoftReference<Bitmap>(bitmap));

			return bitmap;

		}

		public ImageAdapter(Context context) {
			mContext = context;
			TypedArray typedArray = obtainStyledAttributes(R.styleable.Gallery);
			mGalleryItemBackground = typedArray.getResourceId(
					R.styleable.Gallery_android_galleryItemBackground, 0);
			mImageCache = new HashMap<String, SoftReference<Bitmap>>();
		}

		public int getCount() {
			return str.length;
		}

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView imageView = new ImageView(mContext);
			Log.e("das", path + "/" + index + "/" + str[position]);
			Bitmap originalImage = loadBitmapImage(path + "/" + index + "/"
					+ str[position]);
			imageView.setImageBitmap(MyImgView
					.createReflectedImage(originalImage));

			imageView.setLayoutParams(new Gallery.LayoutParams(132, 99));
			return imageView;
		}
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// ѡ��Gallery��ĳ��ͼ��ʱ

	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
	}

	@Override
	// ImageSwitcher�����Ҫ�������������һ��View����һ��ΪImageView����
	// ����ʾͼ��
	public View makeView() {
		ImageView imageView = new ImageView(this);
		imageView.setBackgroundColor(0xFF000000);
		imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
		imageView.setLayoutParams(new ImageSwitcher.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		return imageView;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_gallery);
		index = getIntent().getIntExtra("index", 1);
		File file = new File(path + "/" + index);
		str = file.list();
		// final CoverFlow cf = new CoverFlow(this);
		cf = (CoverFlow) findViewById(R.id.gallery1);
		cf.init();
		cf.setAdapter(new ImageAdapter(this));
		ImageAdapter imageAdapter = new ImageAdapter(this);
		cf.setAdapter(imageAdapter);// �Զ���ͼƬ����䷽ʽ
		cf.setAnimationDuration(1500);
		cf.setOnItemClickListener(this);
		cf.setOnItemLongClickListener(lonClick);
		TextView t = (TextView) findViewById(R.id.gallery_text);
		if (index == 1) {
			t.setText("��������ж�������ÿ�����������ҵ�����");
		} else if (index == 2) {
			t.setText("����������ֵ��Ǻ����ȥ���е�����");
		} else if (index == 3) {
			t.setText("���������������Ǻ�����һ��ĵ��ε�");
		}
	}

	// ���ĳ��ʱ
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

	}

	// �����¼������������
	public OnItemLongClickListener lonClick = new OnItemLongClickListener() {

		@Override
		public boolean onItemLongClick(AdapterView<?> parent, View view,
				int position, long id) {

			return true;
		}
	};

}