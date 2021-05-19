package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JFrame;

final class Dashboard extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;
	private ShapeComponent shapeComponent = new ShapeComponent();
	private int tipe_shape = 1;
	private int size = 1;
	private int direction = 1;
	private int speed = 1;

	//Construct for jframe
	public Dashboard() {
		this.add(this.shapeComponent, "Center");
		this.setSize(700, 500);
	}

	//Function adding ball into jframe
	public void addShape() {
		ShapeUtils shapeUtils = new ShapeUtils(tipe_shape, size, direction, speed);
		shapeComponent.ListShape(shapeUtils);
		SpeedUtils speedUtils = new SpeedUtils(shapeUtils, this.shapeComponent);
		Thread thread = new Thread(speedUtils);
		shapeComponent.ListThread(thread);
		shapeComponent.repaint();
	}

	//Remove object from shape jframe
	public final void removeShape() {
		this.shapeComponent.removeShape();
		this.shapeComponent.repaint();
	}

	//Running ball
	public final void start() {
		this.shapeComponent.start();
	}

	//Stopping Ball
	public final void stop() {
		this.shapeComponent.stop();
	}

	//Function set speed object
	void shapeSpeed(String line) {
		if (line.trim().matches(".*fast")) {
			this.speed = 1;
		} else if (line.trim().matches(".*half")) {
			this.speed = 2;
		} else if (line.trim().matches(".*slow")) {
			this.speed = 3;
		} else {
			System.out.println("Wrong Command : add [square|circle] [small|medium|large] [diagonal|horizontal|vertical] [fast|half|slow]");
		}
	}

	//Function set directional object
	void shapeDirectional(String line) {
		if (line.trim().matches(".*diagonal .*")) {
			this.direction = 1;
		} else if (line.trim().matches(".*horizontal .*")) {
			this.direction = 2;
		} else if (line.trim().matches(".*vertical .*")) {
			this.direction = 3;
		} else {
			System.out.println("Wrong Command : add [square|circle] [small|medium|large] [diagonal|horizontal|vertical] [fast|half|slow]");
		}

		shapeSpeed(line);
	}

	//Function set size object
	void shapeSize(String line) {
		if (line.trim().matches(".*small .* .*")) {
			this.size = 1;
		} else if (line.trim().matches(".*medium .* .*")) {
			this.size = 2;
		} else if (line.trim().matches(".*large .* .*")) {
			this.size = 3;
		} else {
			System.out.println("Wrong Command : add [square|circle] [small|medium|large] [diagonal|horizontal|vertical] [fast|half|slow]");
		}

		shapeDirectional(line);
	}

	//Function set object type
	void shapeType(String line) {
		if (line.trim().matches(".*square .* .* .*")) {
			this.tipe_shape = 2;
		} else if (line.trim().matches(".*circle .* .* .*")) {
			this.tipe_shape = 1;
		} else {
			System.out.println("Wrong Command : add [square|circle] [small|medium|large] [diagonal|horizontal|vertical] [fast|half|slow]");
		}

		shapeSize(line);
	}

	//Running Socket
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

				out.println("====================================================");
				out.println("	Hello! Welcome To Bounce Play");
				out.println("====================================================");
				out.println("Command : start, stop, add, remove, exit");
				out.println("Insert --help for detail of command's \n");

				out.println("Insert Command");

				//Echo client input
				boolean done = false;
				while (!done && ins.hasNextLine()) {
					String line = ins.nextLine();

					out.println("\nRunning " + ": " + line);
					if (line.trim().equals("exit")) {
						done = true;
					} else if (line.trim().matches("add")) {
						out.println("Wrong Command : add [square|circle] [small|medium|large] [diagonal|horizontal|vertical] [fast|half|slow]");
					} else if (line.trim().matches("add.* .* .* .*")) {
						shapeType(line);
						addShape();
					} else if (line.trim().equals("start")) {
						start();
					} else if (line.trim().equals("stop")) {
						stop();
					} else if (line.trim().equals("remove")) {
						removeShape();
					} else if (line.trim().equals("--help")) {
						out.println("====================================================");
						out.println("	    	Command List");
						out.println("====================================================");
						out.println("1. Add		: Add with option [square|circle] [small|medium|large] [diagonal|horizontal|vertical] [fast|half|slow]");
						out.println("2. Remove	: Remove previous object");
						out.println("3. Start	: Run Ball");
						out.println("4. Stop		: Pause Ball");
						out.println("5. Exit		: Exit Program");
						out.println("====================================================");
					}

					out.println("\nInsert Command");
				}
			} finally {
				System.exit(1);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Socket incoming;
}
