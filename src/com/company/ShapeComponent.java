package com.company;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JPanel;

public final class ShapeComponent extends JPanel {
   private List arrayBall = new ArrayList();
   private List arrayThread = new ArrayList();
   private boolean start_indicator = false;

   public final void ListShape(ShapeUtils var1) {
      this.arrayBall.add(var1);
   }

   public final void ListThread(Thread var1) {
      this.arrayThread.add(var1);
      var1.start();
   }

   public final void hapus() {
      if (!this.arrayBall.isEmpty()) {
         this.arrayBall.remove(0);
      }

      if (!this.arrayThread.isEmpty()) {
         ((Thread)this.arrayThread.get(0)).interrupt();
         this.arrayThread.remove(0);
      }

   }

   public final void start() {
      this.start_indicator = true;
   }

   public final void stop() {
      this.start_indicator = false;
   }

   public final boolean getStartIndicator() {
      return this.start_indicator;
   }

   public final void paintComponent(Graphics graphics) {
      super.paintComponent(graphics);
      Graphics2D graphics2D = (Graphics2D)graphics;
      Iterator iterator = this.arrayBall.iterator();

      while(iterator.hasNext()) {
         ShapeUtils shapeUtils = (ShapeUtils)iterator.next();
         graphics2D.setColor(shapeUtils.b());
         if (shapeUtils.getTipeShape() == 1) {
            graphics2D.fill((Ellipse2D)shapeUtils.sizeshape());
         } else {
            graphics2D.fill((Rectangle2D)shapeUtils.sizeshape());
         }
      }

   }

   public final Dimension getPreferredSize() {
      return new Dimension(650, 450);
   }
}
