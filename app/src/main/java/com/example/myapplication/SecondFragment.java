package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication.databinding.FragmentSecondBinding;

import java.util.Random;

public class SecondFragment extends Fragment {
    private FragmentSecondBinding binding;

    private void navigate(View view) {
        NavController thisFragment = NavHostFragment.findNavController(SecondFragment.this);
        thisFragment.navigate(R.id.action_SecondFragment_to_FirstFragment);
    }

    private void updateRandomNumber(View view, int randomNumber) {
        TextView randomView = view.getRootView().findViewById(R.id.random_textview);
        randomView.setText(String.valueOf(randomNumber));
    }

    private void updateRandomHeader(View view, int count) {
        // Update the string (in %d) with 'count' number and store it in 'countText'
        String countText = getString(R.string.random_header_text, count);
        // Get the textview with id 'random_header' and store it in 'headerView'
        TextView headerView = view.getRootView().findViewById(R.id.random_header);
        // Update the 'headerView' with the value in 'countText'
        headerView.setText(countText);
    }

    private int getMyArgument() {
        // getMyArg is the arg created in nav_graph (myArg)
        // Method 'getMyArg' name changes if 'myArg' (in nav_graph) receive another name
        return SecondFragmentArgs.fromBundle(getArguments()).getMyArg();
    }

    private int getRandomNumber(int count) {
        Random random = new java.util.Random();
        return (count > 0) ? random.nextInt(count + 1) : 0;
    }

    private void updateRandomValues(View view) {
        // Receive arguments submitted by FirstFragment and store in 'count'
        int count = getMyArgument();
        // Update the string random_header_text with 'count' value
        updateRandomHeader(view, count);
        // Get a random number and update the random_textview with it
        int random = getRandomNumber(count);
        updateRandomNumber(view, random);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonSecond.setOnClickListener(this::navigate);
        updateRandomValues(view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}