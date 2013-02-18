package com.hippo.merry;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class LetterActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_letter);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		ListView listView = (ListView) findViewById(R.id.letter_text);
		List<String> l = new ArrayList<String>();
		l.add("�װ��Ĵ�������ţ�");
		l.add("       ����һ����ҹ��������ӣ�ÿ��Ҵ�ææ�����һ������ݣ���Ȼ��������Ȼ���źֲܴڡ�������ô˵����������ҵ��뷨��");
		l.add("       ���ǿ����������ˣ����������ǲ�ͣ�ĳ��ܣ�����ǰ���컹�ڳ��ܣ�����������Ȼ��һ���������������Һܶ���֣����������ġ�ǰ���������ڰ��ң���������˵�������������������㡢�չ���ɣ���");
		l.add("       ��������Ӧ�������ǹ��ĵ�������˽ڣ����Ǵ��ġ��Ҳ���һ���������ˣ�����һֱ������ô����һ����������������˽ڡ�������������һ������ӣ�����ʵ�ʵ�ã�����ѡ��û����ģ��һ�׬Ǯ���ỨǮ���ٺ١�");
		l.add("       ���滹�ڷ��̻���һ������һֱ���ҵ������ǰ��Ĵ���ҹ���Һ�����������̻����ҴӺ��汧ס�㣬˵�Ұ��㡣������Ҳ��˵...");
		l.add("       �Ұ��㣬�Ұ��㣬�Ұ��㣡����");
		l.add("       ��֪����鲻��һ�������£�������һ�����϶����ˣ������ϱ�������Ƿ��Ļ�����Ƿ�ҵģ���֮�Ⱳ������û�ܶ��ˡ��������ӦҲ�ô�Ӧ������ӦҲ�ô�Ӧ���ٺ٣�");
		l.add("       ����������ô��������Ҫ����һ��ѡ��......--->>>");

		MyListAdapter mlAdapter = new MyListAdapter(l, LetterActivity.this);
		listView.setAdapter(mlAdapter);
		AnimationSet set = new AnimationSet(true);
		Animation animation = new AlphaAnimation(0.0f, 1.0f);
		animation.setDuration(5000);
		set.addAnimation(animation);
		animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 1.0f,
				Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
				0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
		animation.setDuration(5000);
		set.addAnimation(animation);
		LayoutAnimationController controller = new LayoutAnimationController(
				set, 1.0f);
		listView.setLayoutAnimation(controller);
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			@SuppressLint("NewApi")
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				if (arg2 == 7) {
					Intent intent = new Intent(LetterActivity.this,
							SelectorActivity.class);
					LetterActivity.this.startActivity(intent);
					overridePendingTransition(R.anim.hyperspace_in,
							R.anim.hyperspace_out);
				}

			}
		});
	}

	public class MyListAdapter extends BaseAdapter {

		private List<String> list = null;
		private LayoutInflater inflater;

		public MyListAdapter(List<String> list, Context context) {
			this.list = list;
			this.inflater = LayoutInflater.from(context);

		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int arg0) {
			return list.get(arg0);
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = null;
			if (convertView == null) {
				view = inflater.inflate(R.layout.adapter_listview, null);
			} else {
				view = convertView;
			}

			TextView textView = (TextView) view.findViewById(R.id.list_text);

			if (list != null) {
				String infoVo = list.get(position);
				textView.setText(infoVo);
			}
			return view;
		}

	}

}
