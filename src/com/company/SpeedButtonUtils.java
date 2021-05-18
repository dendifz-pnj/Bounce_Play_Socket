package com.company;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

final class SpeedButtonUtils implements ActionListener {

   private Dashboard dashboard;

   SpeedButtonUtils(Dashboard dashboard) {
      super();
      this.dashboard = dashboard;
   }

   public final void actionPerformed(ActionEvent var1) {
      if (Dashboard.speedCounter(dashboard) > 3) {
         Dashboard.btn_stop(dashboard);
      }

      String speed = "";
      switch(Dashboard.speedIndicator(dashboard)) {
      case 1:
         speed = "Speed: Fast";
         break;
      case 2:
         speed = "Speed: Medium";
         break;
      case 3:
         speed = "Speed: Low";
      }

      Dashboard.btn_speed(dashboard).setText(speed);
   }
}
