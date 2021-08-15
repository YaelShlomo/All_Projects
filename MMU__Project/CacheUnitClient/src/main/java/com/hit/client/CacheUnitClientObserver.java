package com.hit.client;

import java.beans.PropertyChangeListener;

import com.hit.view.CacheUnitView;

public class CacheUnitClientObserver implements PropertyChangeListener
{
    private CacheUnitClient cUClient;
    private CacheUnitView updateUI;


    public CacheUnitClientObserver() {
        cUClient = new CacheUnitClient();
    }
    @Override
    public void propertyChange(java.beans.PropertyChangeEvent evt)
    {
        String res = null;
        updateUI = (CacheUnitView) evt.getSource();
        try
        {
            res = cUClient.send(evt.getNewValue().toString());
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        updateUI.updateUIData(res);

    }
}
