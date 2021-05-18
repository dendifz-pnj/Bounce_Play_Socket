package com.company;

import java.awt.BorderLayout;
import java.io.IOException;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


final class Dashboard extends JFrame implements Runnable{
	
	   
   private ShapeComponent shapeComponent = new ShapeComponent();
   private JButton btn_shape;
   private JButton btn_size;
   private JButton btn_directional;
   private JButton btn_speed;
   private int tipe_shape = 1;
   private int size = 1;
   private int direction = 1;
   private int speed = 1;

   public Dashboard() {
      this.add(this.shapeComponent, "Center");
      JPanel jp1 = new JPanel();
      JPanel jp2 = new JPanel();
      JPanel jp3;
      (jp3 = new JPanel(new BorderLayout())).add(jp2, "Center");
      jp3.add(jp1, "South");;
      this.add(jp3, "South");
      this.setSize(700, 500);
      
      
   }
   

   public void addShape() {
      ShapeUtils shapeUtils = new ShapeUtils(tipe_shape, size, direction, speed);
      shapeComponent.ListShape(shapeUtils);
      SpeedUtils speedUtils = new SpeedUtils(shapeUtils, this.shapeComponent);
      Thread thread = new Thread(speedUtils);
      shapeComponent.ListThread(thread);
      shapeComponent.repaint();
   }

   public final void hapusShape() {
      this.shapeComponent.hapus();
      this.shapeComponent.repaint();
   }
   

   public final void btn_start() {
      this.shapeComponent.start();
   }

   public final void btn_stop() {
      this.shapeComponent.stop();
   }
   
   static JButton btn_shape(Dashboard dashboard) {
	      return dashboard.btn_shape;
	   }
   
   // sebagai incerement counter untuk tipe shape
   public static int shapeCounter(Dashboard dashboard) {
      return ++dashboard.tipe_shape;
   }

   // untuk membalikan counter ke nilai 1
   static int shapeButtonDefault(Dashboard dashboard) {
      return dashboard.tipe_shape;
   }

   // untuk membalikan variabel tipe_shape yang sudah diubah
   static int shapeIndicator(Dashboard dashboard) {
      return dashboard.tipe_shape;
   }

   // sebagai increment counter untuk size shape
   static int sizeCounter(Dashboard dashboard) {
      return ++dashboard.size;
   }

   // untuk membalikan variabel size ke nilai default yaitu 1
   static int sizeIndicatorDefault(Dashboard dashboard) {
      return dashboard.size;
   }

   // untuk membalikan variabel size yang sudah diubah
   static int sizeIndicator(Dashboard dashboard) {
      return dashboard.size;
   }

   // untuk merubah text button, yang dipanggil pada class SizeUtils
   static JButton btn_size(Dashboard dashboard) {
      return dashboard.btn_size;
   }

   // sebagai increment counter untuk direction
   static int directionCounter(Dashboard dashboard) {
      return ++dashboard.direction;
   }

   // untuk membalikan variabel direction ke nilai default yaitu 1
   static int btn_start(Dashboard dashboard) {
      return dashboard.direction;
   }

   // untuk membalikan variabel direction yang sudah diubah
   static int directionIndicator(Dashboard dashboard) {
      return dashboard.direction;
   }

   // untuk merubah text button, yang dipanggil pada class DirectionButtonUtils
   static JButton btn_direction(Dashboard dashboard) {
      return dashboard.btn_directional;
   }

   // sebagai increment counter untuk speed variabel
   static int speedCounter(Dashboard dashboard) {
      return ++dashboard.speed;
   }

   // untuk membalikan variabel speed ke nilai default yaitu 1
   static int btn_stop(Dashboard dashboard) {
      return dashboard.speed;
   }

   // untuk membalikan variabel speed yang sudah diubah
   static int speedIndicator(Dashboard dashboard) {
      return dashboard.speed;
   }

   // untuk merubah text button, yang dipanggil pada class SpeedButtonUtils
   static JButton btn_speed(Dashboard dashboard) {
      return dashboard.btn_speed;
   }
   
   void shapeSpeed(String line) {
	   if (line.trim().matches(".*fast"))  {
			this.speed = 1;
		} else if (line.trim().matches(".*half"))  {
			this.speed = 2;
		} else if (line.trim().matches(".*slow"))  {
			this.speed = 3;
		} else {
			System.out.println("Wrong Command : add [square|circle] [small|medium|large] [diagonal|horizontal|vertical] [fast|half|slow]");
		}
   }
   
   void shapeDirectional(String line) {
	   if (line.trim().matches(".*diagonal .*"))  {
			this.direction = 1;
		} else if (line.trim().matches(".*horizontal .*"))  {
			this.direction = 2;
		} else if (line.trim().matches(".*vertical .*"))  {
			this.direction = 3;
		} else {
			System.out.println("Wrong Command : add [square|circle] [small|medium|large] [diagonal|horizontal|vertical] [fast|half|slow]");
		}
	   
	   shapeSpeed(line);
   }
   
   void shapeSize(String line) {
	   if (line.trim().matches(".*small .* .*"))  {
			this.size = 1;
		} else if (line.trim().matches(".*medium .* .*"))  {
			this.size = 2;
		} else if (line.trim().matches(".*large .* .*"))  {
			this.size = 3;
		} else {
			System.out.println("Wrong Command : add [square|circle] [small|medium|large] [diagonal|horizontal|vertical] [fast|half|slow]");
		}

		shapeDirectional(line);
   }
   
   void shapeType (String line) {
	   if (line.trim().matches(".*square .* .* .*") ) {
			this.tipe_shape = 2;
		} else if (line.trim().matches(".*circle .* .* .*"))  {
			this.tipe_shape = 1;
		} else {
			System.out.println("Wrong Command : add [square|circle] [small|medium|large] [diagonal|horizontal|vertical] [fast|half|slow]");
		}

		shapeSize(line);
   }


	public void run() {
		try {
			try {
				Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"telnet localhost 8190\"");
				ServerSocket s = new ServerSocket(8190);
				Socket in = s.accept();
				InputStream inStream = in.getInputStream();
				OutputStream outStream = in.getOutputStream();
				
				Scanner ins = new Scanner(inStream);
				PrintWriter out = new PrintWriter(outStream, true);
				
				out.println("Hello! Enter BYE to exit.");
				out.println("Command : start, stop, add, BYE");
				// echo client input
				boolean done = false;
				while (!done && ins.hasNextLine()) {
					String line = ins.nextLine();
					
					out.println("Command " + ": " + line);
					if (line.trim().equals("BYE")) {
						done = true;
					} else if (line.trim().matches("add") || line.trim().matches("add.* .*") || line.trim().matches("add.* .* .*"))   {
						out.println("Wrong Command : add [square|circle] [small|medium|large] [diagonal|horizontal|vertical] [fast|half|slow]");
					} else if (line.trim().matches("add.* .* .* .*"))  {
						shapeType(line);
						addShape();
					} else if (line.trim().equals("start") ) {
						btn_start();
					}  else if (line.trim().equals("stop") ) {
						btn_stop();
					}
				}
			} finally {
				this.incoming.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private Socket incoming;
}
