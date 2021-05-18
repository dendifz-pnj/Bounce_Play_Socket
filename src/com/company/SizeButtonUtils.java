package com.company;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

final class SizeButtonUtils implements ActionListener {

   private Dashboard a;

   SizeButtonUtils(Dashboard var1) {
      super();
      a = var1;
   }

   public final void actionPerformed(ActionEvent var1) {
      if (Dashboard.sizeCounter(a) > 3) {
         Dashboard.sizeIndicatorDefault(a);
      }

      String size = "";
      switch(Dashboard.sizeIndicator(this.a)) {
      case 1:
         size = "Size: Small";
         break;
      case 2:
         size = "Size: Medium";
         break;
      case 3:
         size = "Size: Large";
      }

      Dashboard.btn_size(this.a).setText(size);
   }
}
