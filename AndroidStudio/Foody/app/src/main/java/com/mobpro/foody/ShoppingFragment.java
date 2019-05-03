package com.mobpro.foody;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ShoppingFragment extends Fragment {

    String[] names = {"Butter", "Milch", "Apfel", "Glacee"};
    String[] amounts = {"200g", "1l", "10", "Alles"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shopping, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        ListView listView = (ListView) getView().findViewById(R.id.ListView);

        ShoppingFragment.CustomAdapter customerAdatpter = new ShoppingFragment.CustomAdapter();
        listView.setAdapter(customerAdatpter);

    }

    class CustomAdapter extends BaseAdapter {

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
            view = getLayoutInflater().inflate(R.layout.shopping_listlayout, null);

            TextView tV_name = (TextView) view.findViewById(R.id.tV_Name);
            TextView tV_description = (TextView) view.findViewById(R.id.tV_Amount);

            tV_name.setText(names[i]);
            tV_description.setText(amounts[i]);

            return view;
        }
    }
}
