package com.company;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

final class ShapeButtonUtils implements ActionListener {

   private Dashboard dashboard;

   ShapeButtonUtils(Dashboard dashboard) {
      super();
      this.dashboard = dashboard;
   }

   public final void actionPerformed(ActionEvent var1) {
      if (Dashboard.shapeCounter(dashboard) > 2) {
         Dashboard.shapeButtonDefault(dashboard);
      }

      String shapes = "";
      switch(Dashboard.shapeIndicator(dashboard)) {
      case 1:
         shapes = "Shape: Circle";
         break;
      case 2:
         shapes = "Shape: Square";
      }

      Dashboard.btn_shape(this.dashboard).setText(shapes);
   }
}
