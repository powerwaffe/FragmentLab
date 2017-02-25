package edu.dtcc.sean.fragmentlab;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by Sean on 2/25/2017.
 */

public class InputFragment extends Fragment
{
    private static EditText etShippingInput;

    // Interface
    InputFragmentListener activityCommander;

    // Variables
    static final double BASE = 3.00;
    static final double ADDED = .50;
    static final int BASE_WEIGHT = 16;
    static final double EXTRA_OUNCES = 4.0;

    // Data members
    private int mWeight;
    private double mBaseCost;
    private double mAddedCost;
    private double mTotalCost;

    public interface InputFragmentListener
    {
        public void createCosts (double weight);
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        try
        {
            activityCommander = (InputFragmentListener) context;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(context.toString());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.input_fragment, container, false);

        etShippingInput = (EditText) view.findViewById(R.id.etShippingInput);

        return view;
    }

    // Constructor
    public InputFragment()
    {
        mWeight = 0;
        mAddedCost = 0.0;
        mBaseCost = BASE;
        mTotalCost = 0.0;
    }

    public void setWeight (int weight)
    {
        mWeight = weight;
        computeCosts();
    }

    private void computeCosts()
    {
        mAddedCost = 0.0;
        mBaseCost = BASE;

        if (mWeight <= 0)
            mBaseCost = 0.0;
        else if (mWeight > BASE_WEIGHT)
            mAddedCost = Math.ceil((double)(mWeight - BASE_WEIGHT) / EXTRA_OUNCES) * ADDED;

        mTotalCost = mBaseCost + mAddedCost;
    }

    public double getBaseCost()
    {
        return mBaseCost;
    }

    public double getAddedCost()
    {
        return mAddedCost;
    }

    public double getTotalCost()
    {
        return mTotalCost;
    }
}
