package imageTest;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ImageTest extends JFrame {
	static short[][] inImage = new short[120][30];
	static short[][] outImage = new short[120][30];

	Container pane;

	public static void main(String[] args) {
		FileInputStream fin;
		try {
			fin = new FileInputStream("C:\\sample.png");
			for (int i = 0; i < 120; i++) {
				for (int j = 0; j < 30; j++) {
					inImage[i][j] = (short) fin.read();
				}
			}
			fin.close();
		} catch (IOException e) {
		}

		new ImageTest();
	}

	ImageTest() {
		setTitle("사진 처리 프로그램 Ver 0.1");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pane = getContentPane();
		// 패널을 만들고 벽에 붙이자...
		MyPanel panel = new MyPanel();
		pane.add(panel, BorderLayout.CENTER);

		setSize(1024, 512);
		setVisible(true);
		// 키보드 이벤트 걸어 놓기.
		pane.addKeyListener(new MyKeyListener());
		pane.requestFocus();

	}

	class MyKeyListener extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			if (e.getKeyChar() == 'A') {
				// 여기서 영상을 밝게 해라...
				brightImage();
			}

		}

		public void brightImage() {
			int value;

			for (int i = 0; i < 120; i++) {
				for (int j = 0; j < 30; j++) {
					value = (short) ((int) inImage[i][j] + 100);
					if (value > 255)
						outImage[i][j] = 255;
					else
						outImage[i][j] = (short) value;
				}
			}
			Graphics g = pane.getGraphics();
			pane.paintAll(g);
		}

	}

	// 패널 --> inImage, outImage 사진을 화면에 출력하는 기능
	class MyPanel extends JPanel {
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			int R, G, B;
			for (int i = 0; i < 120; i++) {
				for (int j = 0; j < 30; j++) {
					R = G = B = (int) inImage[i][j];
					g.setColor(new Color(R, G, B));
					g.drawRect(j, i, 1, 1);

				}
			}
			for (int i = 0; i < 120; i++) {
				for (int j = 0; j < 30; j++) {
					R = G = B = (int) outImage[i][j];
					g.setColor(new Color(R, G, B));
					g.drawRect(j + 512, i, 1, 1);

				}
			}
		}
	}
}
