package com.mobpro.foody;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class HomeFragment extends Fragment {

    List<String> names = new ArrayList<String>();
    List<String> descriptions = new ArrayList<String>();

    private static final String TAG = "MainActivity";
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    public static final String EXTRA_MESSAGE = "com.mobpro.foody.MESSAGE";
    String description = " ";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FirebaseApp.initializeApp(getContext());
        return inflater.inflate(R.layout.fragment_home, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        addElements();
    }

    public void addElements(){
        db.collection("Recipes").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot document : task.getResult()){
                                names.add(document.getId());
                                //descriptions.add("Easy");
                            }
                        }else
                        {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                        addToListView();
                    }
                });
    }

    void addToListView() {
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
            return names.size();
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
            view = getLayoutInflater().inflate(R.layout.home_listlayout, null);

            TextView tV_name = (TextView) view.findViewById(R.id.tV_Title);
            TextView tV_description = (TextView) view.findViewById(R.id.tV_Description);

            tV_name.setText(names.get(i));
            //tV_description.setText(descriptions.get(i));
            notifyDataSetChanged();

            return view;
        }
    }


}
