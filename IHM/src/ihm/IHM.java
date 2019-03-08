/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.Dimension;
import java.awt.Graphics;

/**
 *
 * @author lucas
 */
public class IHM {


static JPanel panel;
static JFrame frame;
public static void main(String[] args) throws InterruptedException{
    int [][] array = new int [28][28];
    
    array[19][15]=1;
    array[19][16]=1;
    array[19][19]=1;
    array[19][20]=1;
    array[19][21]=1;
    array[18][18]=1;
    array[17][16]=1;

            panel = new JPanel();
    Dimension dim = new Dimension(400, 400);
    panel.setPreferredSize(dim);
    frame = new JFrame();
    frame.setSize(500, 500);
    Container contentPane = frame.getContentPane();
    contentPane.add(panel);
    frame.setVisible(true);
    
    
    int[][] end = new int[array.length][array[0].length];
    int a = 0;
    while (true) {
        for (int i = 1; i <= array.length - 2; i++) {
            for (int j = 1; j <= array[0].length - 2; j++) {
                int counter = surround(array, i, j);
                if (array[i][j] == 1 && counter <= 2) {
                    end[i][j] = 0;
                }
                if (array[i][j] == 1 && counter == 3) {
                    end[i][j] = 1;
                }
                if (array[i][j] == 1 && counter > 4) {
                    end[i][j] = 0;
                }
                if (array[i][j] == 0 && counter == 3) {
                    end[i][j] = 1;
                }
                if (array[i][j] == 1 && counter == 4) {
                    end[i][j] = 1;
                }
            }
        }
        Graphics g = panel.getGraphics();
        Graphics(array, g);
        a++;
        
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                array[i][j] = end[i][j];
                end[i][j] = 0;
            }
        }
        Thread.sleep(125);
        g.dispose();
    }
}

public static int surround(int[][] initial, int i, int j) {
    int[][] surrounding = {
            { initial[i - 1][j - 1], initial[i - 1][j],
                    initial[i - 1][j + 1] },
            { initial[i][j - 1], initial[i][j], initial[i][j + 1] },
            { initial[i + 1][j - 1], initial[i + 1][j],
                    initial[i + 1][j + 1] } };
    int counter = 0;
    for (int a = 0; a <= 2; a++) {
        for (int b = 0; b <= 2; b++) {
            if (surrounding[a][b] == 1) {
                counter++;
            }
        }
    }
    return counter;
}

public static void printArray(int[][] input) {
    for (int x = 0; x < input.length; x++) {
        for (int y = 0; y < input[0].length; y++) {
            System.out.print(input[x][y]);
        }
        System.out.println("");
    }
}


public static void Graphics(int[][] array, Graphics g) {
    int BOX_DIM = 15;
    for (int i = 2; i < array.length; i++) {
        for (int j = 5; j < array[0].length; j++) {
            
            g.setColor(array[i][j] == 0 ? Color.WHITE : Color.BLACK);
            g.fillRect(i * BOX_DIM, j * BOX_DIM, BOX_DIM, BOX_DIM);
        }
    }
}

}


