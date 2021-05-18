package com.company;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

final class AddButton implements ActionListener {

   private Dashboard a;

   AddButton(Dashboard var1) {
      super();
      this.a = var1;
   }

   public final void actionPerformed(ActionEvent var1) {
      this.a.addShape();
   }
}
