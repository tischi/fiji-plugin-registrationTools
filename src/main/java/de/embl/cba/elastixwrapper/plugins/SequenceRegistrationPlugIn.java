package de.embl.cba.elastixwrapper.plugins;

import ij.plugin.PlugIn;

import javax.swing.*;

public class SequenceRegistrationPlugIn implements PlugIn {

    @Override
    public void run(String s)
    {
        SequenceRegistrationGUI sequenceRegistrationGUI = new SequenceRegistrationGUI();

        SwingUtilities.invokeLater(new Runnable() {
            public void run()
            {
                sequenceRegistrationGUI.showDialog();
            }
        });

    }


}