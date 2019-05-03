package com.mobpro.foody;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class HomeFragment extends Fragment {

    String[] names = {"Spaghetti", "Cookies", "Brownies", "Glacee"};
    String[] descriptions = {"Easy", "Medium", "Medium", "Easy"};
    public static final String EXTRA_MESSAGE = "com.mobpro.foody.MESSAGE";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        ListView listView = (ListView) getView().findViewById(R.id.ListView);

        CustomAdapter customerAdatpter = new CustomAdapter();
        listView.setAdapter(customerAdatpter);

        // Get the name of the clicked item and open Detail activity
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selected = ((TextView) view.findViewById(R.id.tV_Title)).getText().toString();
                Intent intent = new Intent(HomeFragment.this.getActivity(), Details.class);
                intent.putExtra(EXTRA_MESSAGE, selected);
                startActivity(intent);
            }
        });


    }

    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return names.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.customlayout, null);

            TextView tV_name = (TextView) view.findViewById(R.id.tV_Title);
            TextView tV_description = (TextView) view.findViewById(R.id.tV_Description);

            tV_name.setText(names[i]);
            tV_description.setText(descriptions[i]);

            return view;
        }
    }


}
