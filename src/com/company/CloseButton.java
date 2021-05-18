package com.company;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

final class CloseButton implements ActionListener {
   CloseButton(Dashboard var1) {
   }

   public final void actionPerformed(ActionEvent var1) {
      System.exit(0);
   }
}
