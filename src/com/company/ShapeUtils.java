package com.company;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D.Double;

public final class ShapeUtils {
	private int lebar = 15;
	private int tinggi = 15;
	private double posX = 0.0D;
	private double posY = 0.0D;
	private double speedX = 1.0D;
	private double speedY = 1.0D;
	private Color color;
	private int tipeShape = 1;
	private int size_selector = 1;
	private int diagonal = 1;
	private int speed_selector = 1;

	//Moving Object
	public final void move(Rectangle2D var1) {
		if (this.diagonal == 1 || this.diagonal == 2) {
			this.posX += this.speedX;
			if (this.posX < var1.getMinX()) {
				this.posX = var1.getMinX();
				this.speedX = -this.speedX;
			}

			if (this.posX + (double) this.lebar >= var1.getMaxX()) {
				this.posX = var1.getMaxX() - (double) this.lebar;
				this.speedX = -this.speedX;
			}
		}

		if (this.diagonal == 1 || this.diagonal == 3) {
			this.posY += this.speedY;
			if (this.posY < var1.getMinY()) {
				this.posY = var1.getMinY();
				this.speedY = -this.speedY;
			}

			if (this.posY + (double) this.tinggi >= var1.getMaxY()) {
				this.posY = var1.getMaxY() - (double) this.tinggi;
				this.speedY = -this.speedY;
			}
		}

	}

	//Set size shape
	public final Object sizeshape() {
		switch (this.size_selector) {
		case 1:
			this.lebar = this.tinggi = 15;
			break;
		case 2:
			this.lebar = this.tinggi = 30;
			break;
		case 3:
			this.lebar = this.tinggi = 45;
		}

		return this.tipeShape == 1 ? new Double(this.posX, this.posY, (double) this.lebar, (double) this.tinggi)
				: new java.awt.geom.Rectangle2D.Double(this.posX, this.posY, (double) this.lebar, (double) this.tinggi);
	}

	//Random colors and position
	public ShapeUtils() {
		this.posX = (double) ((int) (Math.random() * 500.0D));
		this.posY = (double) ((int) (Math.random() * 300.0D));
		this.color = new Color((int) (Math.random() * 255.0D), (int) (Math.random() * 255.0D),
				(int) (Math.random() * 255.0D));
	}

	//Getting and save option from object
	public ShapeUtils(int tipeshape_selector, int size_selector, int diagonal_selector, int speed_selector) {
		this.posX = (double) ((int) (Math.random() * 500.0D));
		this.posY = (double) ((int) (Math.random() * 300.0D));
		this.color = new Color((int) (Math.random() * 255.0D), (int) (Math.random() * 255.0D),
				(int) (Math.random() * 255.0D));
		this.tipeShape = tipeshape_selector;
		this.size_selector = size_selector;
		this.diagonal = diagonal_selector;
		this.speed_selector = speed_selector;
	}

	//Set color
	public final Color b() {
		return this.color;
	}

	//Get Type Shape
	public final int getTipeShape() {
		return this.tipeShape;
	}

	//Get Speed Indicator
	public final int speed_indicator() {
		return this.speed_selector;
	}
}
