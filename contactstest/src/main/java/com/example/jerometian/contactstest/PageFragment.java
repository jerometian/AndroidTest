package com.example.jerometian.contactstest;


import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.app.Fragment;
import android.provider.ContactsContract;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PageFragment extends android.support.v4.app.Fragment {

    private static String ARG_PAGE = "ARG_PAGE";
    private int mPage;

    private String[] myDataset = new String[]{"Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
            "Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale",
            "Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale",
            "Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale",
            "Aisy Cendre", "Allgauer Emmentaler", "Alverca", "Ambert", "American Cheese",
            "Ami du Chambertin", "Anejo Enchilado", "Anneau du Vic-Bilh", "Anthoriro", "Appenzell",
            "Aragon", "Ardi Gasna", "Ardrahan", "Armenian String", "Aromes au Gene de Marc",
            "Asadero", "Asiago", "Aubisque Pyrenees", "Autun", "Avaxtskyr", "Baby Swiss"


    };

    public static PageFragment newInstance(int page) {
        PageFragment fragment = new PageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        fragment.setArguments(args);
        return fragment;
    }

    public PageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPage = getArguments().getInt(ARG_PAGE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_page, container, false);
      /*  TextView textView = (TextView)view.findViewById(R.id.textViewinFrame);
        textView.setText("Fragment test #" + mPage);*/

        if (mPage == 1 || mPage == 2) {
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_1);
            recyclerView.setHasFixedSize(true);
            recyclerView.setNestedScrollingEnabled(false);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            RecyclerView.LayoutManager mLayoutManager = linearLayoutManager;

            recyclerView.setLayoutManager(mLayoutManager);
            // specify an adapter (see also next example)
            RecyclerView.Adapter mAdapter = new MyAdapter(myDataset);
            recyclerView.setAdapter(mAdapter);

        } else {
            String[] data = readContacts();
            if ( data != null)
            {
                RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_1);
                recyclerView.setHasFixedSize(true);
                recyclerView.setNestedScrollingEnabled(false);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                RecyclerView.LayoutManager mLayoutManager = linearLayoutManager;

                recyclerView.setLayoutManager(mLayoutManager);
                // specify an adapter (see also next example)
                RecyclerView.Adapter mAdapter = new MyAdapter(data);
                recyclerView.setAdapter(mAdapter);
            }

        }

        return view;
    }

    private String[] readContacts() {
        Cursor cursor = null;
        try {
            String[] myDataset1 = new String[10];
            cursor = getContext().getContentResolver().query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null, null, null, null);
            int i = 0;
            while (cursor.moveToNext()) {
                if ( i>9)
                    break;
                String displayName = cursor.getString(cursor.getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

                String number = cursor.getString(cursor.getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.NUMBER));
                myDataset1[i] = displayName + "\n" + number;
                i++;
            }

            return myDataset1;
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("readContacts:", e.getMessage().toString());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return null;
    }



}


