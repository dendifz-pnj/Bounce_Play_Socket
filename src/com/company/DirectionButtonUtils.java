package com.company;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

final class DirectionButtonUtils implements ActionListener {

   private Dashboard dashboard;

   DirectionButtonUtils(Dashboard dashboard) {
      super();
      this.dashboard = dashboard;
   }

   public final void actionPerformed(ActionEvent var1) {
      if (Dashboard.directionCounter(dashboard) > 3) {
         Dashboard.btn_start(dashboard);
      }

      String direction = "";
      switch(Dashboard.directionIndicator(dashboard)) {
      case 1:
         direction = "Direction: Diagonal";
         break;
      case 2:
         direction = "Direction: Horizontal";
         break;
      case 3:
         direction = "Direction: Vertical";
      }

      Dashboard.btn_direction(dashboard).setText(direction);
   }
}
