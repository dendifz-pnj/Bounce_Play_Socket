package com.company;

import java.awt.Component;

public final class SpeedUtils implements Runnable {
   private ShapeUtils shapeUtils;
   private Component component;
   private int speed = 5;

   public SpeedUtils(ShapeUtils shapeUtils, Component component) {
      this.shapeUtils = shapeUtils;
      this.component = component;
   }

   public final void run() {
      try {
         for(; !Thread.interrupted(); Thread.sleep((long)speed)) {
            if (((ShapeComponent)component).getStartIndicator()) {
               shapeUtils.move(component.getBounds());
               component.repaint();
            }

            switch(shapeUtils.speed_indicator()) {
            case 1:
               speed = 5;
               break;
            case 2:
               speed = 15;
               break;
            case 3:
               speed = 30;
            }
         }

      } catch (InterruptedException var1) {
      }
   }
}
