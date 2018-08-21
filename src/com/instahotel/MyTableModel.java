package com.instahotel;

import java.awt.EventQueue;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

public class MyTableModel extends DefaultTableModel {

    public MyTableModel(String THead[]) {
      super(THead, 0);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
      Class clazz = String.class;
      switch (columnIndex) {
        case 1:
          clazz = Integer.class;
          break;
        case 0:
          clazz = Boolean.class;
          break;
      }
      return clazz;
    }
  }