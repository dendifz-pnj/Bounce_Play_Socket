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
	private static final long serialVersionUID = 2L;
	private List<ShapeUtils> arrayBall = new ArrayList<ShapeUtils>();
	private List<Thread> arrayThread = new ArrayList<Thread>();
	private boolean start_indicator = false;

	//Add Object Shape To List
	public final void ListShape(ShapeUtils var1) {
		this.arrayBall.add(var1);
	}

	//Add Object Thread To List
	public final void ListThread(Thread var1) {
		this.arrayThread.add(var1);
		var1.start();
	}

	//Remove Object
	public final void removeShape() {
		if (!this.arrayBall.isEmpty()) {
			this.arrayBall.remove(0);
		}

		if (!this.arrayThread.isEmpty()) {
			((Thread) this.arrayThread.get(0)).interrupt();
			this.arrayThread.remove(0);
		}

	}

	//Start indicator
	public final void start() {
		this.start_indicator = true;
	}

	//Stop indicator
	public final void stop() {
		this.start_indicator = false;
	}

	//Getting start indicator
	public final boolean getStartIndicator() {
		return this.start_indicator;
	}

	//Set Grapich For Object
	public final void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D graphics2D = (Graphics2D) graphics;
		Iterator<ShapeUtils> iterator = this.arrayBall.iterator();

		while (iterator.hasNext()) {
			ShapeUtils shapeUtils = (ShapeUtils) iterator.next();
			graphics2D.setColor(shapeUtils.b());
			if (shapeUtils.getTipeShape() == 1) {
				graphics2D.fill((Ellipse2D) shapeUtils.sizeshape());
			} else {
				graphics2D.fill((Rectangle2D) shapeUtils.sizeshape());
			}
		}

	}

	//Set Limit Size Dimension
	public final Dimension getPreferredSize() {
		return new Dimension(650, 450);
	}
}
