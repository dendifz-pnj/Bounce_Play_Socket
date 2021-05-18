package com.company;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

final class StartButton implements ActionListener {

   private Dashboard a;

   StartButton(Dashboard var1) {
      super();
      this.a = var1;
   }

   public final void actionPerformed(ActionEvent var1) {
      this.a.btn_start();
   }
}
