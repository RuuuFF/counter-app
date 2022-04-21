package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class FirstFragment extends Fragment {
    TextView counterTextView;

    private void toastMessage(View view) {
        String toast_message = getString(R.string.toast_message);
        Toast myToast = Toast.makeText(getActivity(), toast_message, Toast.LENGTH_SHORT);
        myToast.show();
    }

    private void countMe(View view) {
        // Get the value of the text view
        String countString = counterTextView.getText().toString();
        // Convert value to a number and increment it
        int count = Integer.parseInt(countString);
        count++;
        // Display the new value in the text view.
        counterTextView.setText(String.valueOf(count));
    }

    private void navigate(View view) {
        // Capture the value in counter_textview
        int currentCount = Integer.parseInt(counterTextView.getText().toString());
        // Create a action with the type of the action
        FirstFragmentDirections.ActionFirstFragmentToSecondFragment action;
        // Store the action to navigate with 'currentCount' value
        action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(currentCount);
        // Find the nav controller and navigate with the action created
        NavHostFragment.findNavController(FirstFragment.this).navigate(action);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View firstFragmentLayout = inflater.inflate(R.layout.fragment_first, container, false);
        // Get the count text view
        counterTextView = firstFragmentLayout.findViewById(R.id.counter_textview);

        return firstFragmentLayout;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.toast_button).setOnClickListener(this::toastMessage);
        view.findViewById(R.id.counter_button).setOnClickListener(this::countMe);
        view.findViewById(R.id.random_button).setOnClickListener(this::navigate);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        counterTextView = null;
    }
}