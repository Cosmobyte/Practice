package com.example.android.xe
        ;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (ListView) findViewById(R.id.listview);
        list.setAdapter(new cosminAdapter(this));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        inflater.inflate(R.menu.notifications,menu);
        return super.onCreateOptionsMenu(menu);
    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item)
//    {
//    int id = item.getItemId();
//        if(id == R.id.notifications)
//        {
//
//            }
//        }
//        return super.onOptionsItemSelected(item);
//    }
@Override
public boolean onOptionsItemSelected(MenuItem item)
{
    int id = item.getItemId();
    switch (id){
        case R.id.notifications:
            Uri webpage = Uri.parse("http://www.android.com");
            Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
           if (webIntent.resolveActivity(getPackageManager()) != null) {
               startActivity(webIntent);
           }
            return true;
        case R.id.action_settings:
            return true;
        default:
            return super.onOptionsItemSelected(item);
    }
}


    class SingleRow
    {
        String title;
        String description;
        int image;
        SingleRow(String title,String description,int image)
        {
            this.title = title;
            this.description = description;
            this.image = image;
        }
    }

    class cosminAdapter extends BaseAdapter
    {
        Context context;
        ArrayList<SingleRow> list;
        cosminAdapter(Context c)
        {
            context =c;
            list =new ArrayList<SingleRow>();
            Resources res = c.getResources();
           String[] title = res.getStringArray(R.array.titles);
            String[] description = res.getStringArray(R.array.descriptions);
            int[] images ={R.drawable.meme1,R.drawable.meme2,R.drawable.meme3,R.drawable.meme4,R.drawable.meme5,R.drawable.meme6,R.drawable.meme7,R.drawable.meme8};
            for(int i=0;i<8;i++)
            {
                list.add(new SingleRow(title[i],description[i],images[i]));
            }
        }
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.single_row,parent,false);
            TextView title = (TextView) view.findViewById(R.id.textView);
            TextView description = (TextView) view.findViewById(R.id.textView2);
            ImageView image = (ImageView) view.findViewById(R.id.imageView);
            SingleRow temp = list.get(position);

            title.setText(temp.title);
            description.setText(temp.description);
            image.setImageResource(temp.image);
            return view;
        }
    }
}