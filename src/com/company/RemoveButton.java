package com.company;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

final class RemoveButton implements ActionListener {

   private Dashboard dashboard;

   RemoveButton(Dashboard db) {
      super();
      this.dashboard = db;
   }

   public final void actionPerformed(ActionEvent actionEvent) {
      this.dashboard.hapusShape();
   }
}
