package com.lassegottwald.contextframework;

import android.app.ListActivity;
import android.content.Context;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.solver.ArrayLinkedVariables;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.R.layout.simple_list_item_1;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LoggerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LoggerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoggerFragment extends Fragment implements LoggerListener.OnLogReceivedListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Context context;
    private LoggerListener ll;
    private ArrayList<HashMap<String, String>> logList;
    private OnFragmentInteractionListener mListener;
    private ListView logListView;
    private CustomArrayAdapter adapter;

    public LoggerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoggerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoggerFragment newInstance(String param1, String param2) {
        LoggerFragment fragment = new LoggerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        this.context = getActivity().getApplicationContext();
        this.ll = new LoggerListener();
        this.ll.setOnLogReceivedListener(this);
        this.logList = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> log = new HashMap<String, String>();
        log.put("source", "LoggerFragment");
        log.put("msg", "INITIALISED");
        this.logList.add(log);


        IntentFilter broadcastFilter = new IntentFilter();
        broadcastFilter.addAction("com.lassegottwald.contextframework.LOG_BROADCAST");
        this.context.registerReceiver(ll, broadcastFilter);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_logger, container, false);
        this.logListView = view.findViewById(R.id.fragment_logger_list);
        this.adapter = new CustomArrayAdapter(inflater, this.context, R.layout.log_list_item, this.logList);
        this.logListView.setAdapter(this.adapter);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }


    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onLogReceived(String source, String msg) {
        HashMap<String, String> log = new HashMap<String, String>();
        log.put("source", source);
        log.put("msg", msg);
        this.logList.add(log);
        Log.v("loggerFragment", "onLogReceived: " +msg);

        //this.logListView.smoothScrollToPosition(this.logListView.getAdapter().getCount()-1);

        logListView.post(new Runnable(){
            public void run() {
                logListView.setSelection(logListView.getCount() - 1);
            }});

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }



    private class CustomArrayAdapter extends ArrayAdapter{

        Context context;
        LayoutInflater inflater;
        int layoutResourceId;
        ArrayList<HashMap<String, String>> data = null;

        public CustomArrayAdapter(LayoutInflater inflater, Context context, int layoutResourceId, ArrayList<HashMap<String, String>> data) {
            super(context, layoutResourceId, data);
            this.layoutResourceId = layoutResourceId;
            this.context = context;
            this.inflater = inflater;
            this.data = data;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            ViewHolder holder = null;

            if(row == null)
            {

                row = this.inflater.inflate(layoutResourceId, parent, false);

                holder = new ViewHolder();
                holder.source = row.findViewById(R.id.log_list_item_class);
                holder.msg = row.findViewById(R.id.log_list_item_text);

                row.setTag(holder);
            }
            else
            {
                holder = (ViewHolder) row.getTag();
            }

            String msg = data.get(position).get("msg");
            String source = data.get(position).get("source");
            holder.msg.setText(msg);
            holder.source.setText(source);
            return row;
        }

        class ViewHolder
        {
            TextView source;
            TextView msg;
        }
    }

}
