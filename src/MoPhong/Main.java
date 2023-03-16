/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MoPhong;
import static java.awt.AWTEventMulticaster.add;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.List;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Thread.sleep;
import static java.lang.module.ModuleDescriptor.read;
import static java.lang.module.ModuleDescriptor.read;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.NumberFormatter;
/**
 *
 * @author HP
 */
public class Main extends javax.swing.JFrame {
	private boolean isIncrease = true;
	public int num;
	private JLabel[] lbArrays;
	private int[] array;
	private static Main frame;
	private Thread[] threads = new Thread[1000000];
	private int curT = -1;
	private int time = 200;
        private int step = 0;	
        private int[] lbI = new int[50];
        private int[] lbJ = new int[50];
        private int[] oriLocat = new int[15];
        private int[] lbL = new int[50];
        private int[] lbR = new int[50];
        File F ;
        private JLabel lbPoint1 = new JLabel();
        private JLabel lbPoint2 = new JLabel();
        private JLabel lbPointM = new JLabel();
        private Color processingColor = new Color(255, 153, 153);
        private Color selectedGreen = new Color(153, 255, 153);
        private Color selectedYellow = new Color(255, 255, 153);
        public java.awt.event.ActionEvent evt;
        private int dem=0, e = 0;
       
       
    /**
     * Creates new form Main
     */
    public Main() {        
        initComponents();
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);  
        setTitle("Mô phỏng thuật toán");

        pnArray.setBorder(new TitledBorder(null, "Dữ liệu", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        pnAlgorithm.setBorder(new TitledBorder(null, "Lựa chọn thuật toán", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        pnControl.setBorder(new TitledBorder(null, "Điều khiển", TitledBorder.LEADING, TitledBorder.TOP, null, null));        
       
        pnCreateArray.setBorder(new TitledBorder(null, "Khởi tạo mảng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        pnSetValueArray.setBorder(new TitledBorder(null, "Tạo dữ liệu mảng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        pnImitiate.setBorder(new TitledBorder(null, "Khung chạy mô phỏng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        
                lbPoint1.setSize(50, 20);// size ibPoint1
                lbPoint1.setOpaque(true);
                lbPoint1.setLocation(50, 50);
                lbPoint1.setFont(new Font("Helvetica", Font.BOLD, 17));// Định dạng kiểu phông chữ
               
                lbPoint1.setHorizontalAlignment(SwingConstants.CENTER);
                lbPoint1.setVerticalAlignment(SwingConstants.CENTER);
                pnImitiate.add(lbPoint1);
                pnImitiate.add(lbPoint2);
                
                lbPoint2.setSize(50, 20); // size  lbPoint2
                lbPoint2.setOpaque(true);
                lbPoint2.setLocation(50, 50);
                lbPoint2.setFont(new Font("Helvetica", Font.BOLD, 17));// Định dạng kiểu phông chữ     
                
                lbPoint2.setHorizontalAlignment(SwingConstants.CENTER);
                lbPoint2.setVerticalAlignment(SwingConstants.CENTER);                
                pnImitiate.add(lbPointM);
                
                lbPointM.setSize(50, 20); // size lbPointM
                lbPointM.setOpaque(true);
                lbPointM.setLocation(50, 50);
                lbPointM.setFont(new Font("Helvetica", Font.BOLD, 17));// Định dạng kiểu phông chữ
               
                lbPointM.setHorizontalAlignment(SwingConstants.CENTER);
                lbPointM.setVerticalAlignment(SwingConstants.CENTER);
                setState(0);
    }
    
    /*
    Chỉnh giao diện 
    */
    public static void setLockAndFeel() {
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
                            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
            }
	}
    
   // Hiệu ứng cho các nút chức năng
    public void setState(int state) {
		switch (state) {
		case 0: //first state, haven't created arrays.
			btnCreateArray.setEnabled(true);
			btnDeleteArray.setEnabled(false);
			
			btnRandom.setEnabled(false);
			btnByHand.setEnabled(true);
			btnOpenFile.setEnabled(true);
			btnReadFile.setEnabled(true);
                        btnXuatFile.setEnabled(false);
						
			rdSelectionSort.setEnabled(true);
			rdBubbleSort.setEnabled(true);
			rdInsertionSort.setEnabled(true);			
			rdInterchangeSort.setEnabled(true);
			rdQuickSort.setEnabled(true);
						
			rdIncrease.setEnabled(true);
			rdDecrease.setEnabled(true);
			
			btnSort.setEnabled(false);
                        reset.setEnabled(false);
			break;
			
		case 1: //created arrays, be waiting to set value arrays.
			btnDeleteArray.setEnabled(true);
			
			btnRandom.setEnabled(true);
			btnByHand.setEnabled(true);
			btnOpenFile.setEnabled(true);
			btnReadFile.setEnabled(true);
			
			rdSelectionSort.setEnabled(true);
			rdBubbleSort.setEnabled(true);
			rdInsertionSort.setEnabled(true);			
			rdInterchangeSort.setEnabled(true);
			rdQuickSort.setEnabled(true);
			
			
			rdIncrease.setEnabled(true);
			rdDecrease.setEnabled(true);	
			break;
			
		case 2: //be set values, ready to sort
			btnDeleteArray.setEnabled(true);

			btnRandom.setEnabled(true);
                        btnXuatFile.setEnabled(true);
			
			rdSelectionSort.setEnabled(true);
			rdBubbleSort.setEnabled(true);
			rdInsertionSort.setEnabled(true);			
			rdInterchangeSort.setEnabled(true);
			rdQuickSort.setEnabled(true);
			
			
			rdIncrease.setEnabled(true);
			rdDecrease.setEnabled(true);
			
			btnSort.setEnabled(true);
                        reset.setEnabled(true);
			break;
			
		case 3: //sorting
			btnCreateArray.setEnabled(true);
			btnDeleteArray.setEnabled(true);
			
			
			btnRandom.setEnabled(false);
			btnByHand.setEnabled(false);
			btnOpenFile.setEnabled(false);
			btnReadFile.setEnabled(false);
                        btnXuatFile.setEnabled(false);
			
			rdIncrease.setEnabled(false);
			rdDecrease.setEnabled(false);			
			
			rdSelectionSort.setEnabled(false);
			rdBubbleSort.setEnabled(false);
			rdInsertionSort.setEnabled(false);			
			rdInterchangeSort.setEnabled(false);
			rdQuickSort.setEnabled(false);
			
			
			btnSort.setEnabled(false);	
                        reset.setEnabled(true);
			break;
			
		case 4: //sort done
			btnCreateArray.setEnabled(true);
			btnDeleteArray.setEnabled(true);
					
			btnRandom.setEnabled(true);
			btnByHand.setEnabled(true);
			btnOpenFile.setEnabled(true);
			btnReadFile.setEnabled(true);	
                        btnXuatFile.setEnabled(true);
			
			rdSelectionSort.setEnabled(true);
			rdBubbleSort.setEnabled(true);
			rdInsertionSort.setEnabled(true);		
			rdInterchangeSort.setEnabled(true);
			rdQuickSort.setEnabled(true);
						
			rdIncrease.setEnabled(true);
			rdDecrease.setEnabled(true);
			
			btnSort.setEnabled(true);
                        reset.setEnabled(true);
			break;
			default:
				btnCreateArray.setEnabled(true);
				btnDeleteArray.setEnabled(false);
				

				btnRandom.setEnabled(false);
				btnByHand.setEnabled(true);
				btnOpenFile.setEnabled(true);
				btnReadFile.setEnabled(true);
				
				
				rdSelectionSort.setEnabled(true);
				rdBubbleSort.setEnabled(true);
				rdInsertionSort.setEnabled(true);				
				rdInterchangeSort.setEnabled(true);
				rdQuickSort.setEnabled(true);
				
				
				rdIncrease.setEnabled(true);
				rdDecrease.setEnabled(true);
				
				btnSort.setEnabled(false);
                                reset.setEnabled(true);
				}  
	}
    
    /*
    
    Hoán đổi vị trí trên khung chạy mô phỏng 
    
                                            */
    
public int Swap(JLabel lb1, JLabel lb2) {                
		int x1 = lb1.getX();
		int x2 = lb2.getX();
		curT ++;		
		int cur = curT;
		threads[cur] = new Thread(new Runnable() {
		    @Override
		    public void run() {
		    	try {
		    		if (cur != 0) {
			    		threads[cur-1].join();
			    	}
		    		// Tô màu phần tử hoán vị
		    		lb1.setBackground(processingColor);
		    		lb2.setBackground(processingColor);
                                
			        while (lb1.getY() > 100) {
			        	lb1.setLocation(lb1.getX(), lb1.getY() - 10);
			        	lb2.setLocation(lb2.getX(), lb2.getY() + 10);
                                        lbPointM.setLocation(x2, lbPointM.getY() + 10);
			        	Thread.sleep(time);
			        }
			        while (lb1.getX() < x2) {
			        	lb1.setLocation(lb1.getX() + 10, lb1.getY());
			        	lb2.setLocation(lb2.getX() - 10, lb2.getY());
                                        lbPointM.setLocation(lb2.getX(), 250);
			        	Thread.sleep(time);
			        }
			        while (lb1.getY() < 140) {
			        	lb1.setLocation(lb1.getX(), lb1.getY() + 10);
			        	lb2.setLocation(lb2.getX(), lb2.getY() - 10);
                                        lbPointM.setLocation(x1, lbPointM.getY() - 10);
			        	Thread.sleep(time);
			        }
			        String txtLb1 = lb1.getText();
			        lb1.setText(lb2.getText());
			        lb2.setText(txtLb1);
			        lb1.setLocation(x1, 150);
		        	lb2.setLocation(x2, 150);
		        	lb1.setBackground(SystemColor.inactiveCaption);
		        	lb2.setBackground(SystemColor.inactiveCaption);
		    	} catch (Exception e) {
		    	}
		    }
		});                
		threads[cur].start();
                dem++;
                return dem;
	}

// Chỉ vị trí phần tử của mảng 
public void setlbPoint(JLabel lbPoint, int i, String s) {
            curT ++;
            System.out.println(curT);
            int cur = curT;
            threads[cur] = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        if (cur != 0)
                            threads[cur - 1].join();
                        if (i == -1) {
                            lbPoint.setText("");
                            return;
                        }
                        
                        if (s.charAt(0) == 'm') {
                            lbPoint.setLocation(lbArrays[i].getX(), 200);
                            lbPoint.setText(s);
                        } else if (s.charAt(0) == 'k' || s.charAt(0) == 'p') {
                            lbPoint.setLocation(oriLocat[i], 200);
                            lbPoint.setText(s + i);
                        }
                        // vị trí phần tử i,j
                        else {
                            lbPoint.setLocation(lbArrays[i].getX(), 275);
                            lbPoint.setText(s + i);
                        }
                    } catch (Exception e){}
                }
            });
            threads[cur].start();
        }
/*
    *Thuật toán Selection Sort
                            */

public void SelectionSort() {
                dem=0;
		if (isIncrease) {			
			int min,i,j; 
			for (i = 0; i < num - 1; i++) {				
                                setlbPoint(lbPoint1, i, "i = ");
				min = i;
                                setlbPoint(lbPointM, i, "min");
				
				for(j = i + 1; j < num; j++) {					
                                        setlbPoint(lbPoint2, j, "j = ");
                                        
					if (array[j] < array[min]) {						
						min = j;
                                                setlbPoint(lbPointM, j, "min");
					}
				}				
				if (min > i) {
					int temp = array[min];		
					array[min] = array[i];			
					array[i] = temp;					
					Swap(lbArrays[i], lbArrays[min]);                                                                             
				}                            
			}
		} else {			
			int min,i,j; 
			for (i = 0; i < num - 1; i++) {				
                                setlbPoint(lbPoint1, i, "i = ");
				min = i;
                                setlbPoint(lbPointM, i, "max");
				
				for(j = i + 1; j < num; j++) {					
                                        setlbPoint(lbPoint2, j, "j = ");
					if (array[j] > array[min]) {					
						min = j;
                                                setlbPoint(lbPointM, j, "max");
					}
				}				
				if (min > i) {
					int temp = array[min];		
					array[min] = array[i];			
					array[i] = temp;					
					Swap(lbArrays[i], lbArrays[min]);                                       
				}
			}
		}
	}

/*
    *Thuật toán Bubble Sort
                            */

    public void BubbleSort(){
                dem=0;
		if (isIncrease) {			
			int i, j;
			for (i = 0; i< num; i++) {				
                                setlbPoint(lbPoint1, i, "i = ");
				for (j = num - 1; j > i; j--) {
					setlbPoint(lbPoint2, j, "j = ");
                                        
					if(array[j]< array[j-1]) {
						int temp = array[j];
						array[j] = array[j - 1];
						array[j - 1] = temp;						
						Swap(lbArrays[j - 1], lbArrays[j]);
					}
				}
			}
			
		} else {			
			int i, j;
			for (i = 0; i< num; i++) {				
                                setlbPoint(lbPoint1, i, "i = ");
                                
				for (j = num - 1; j > i; j--) {				
                                        setlbPoint(lbPoint2, j, "j = ");
                                        
					if(array[j] > array[j-1]) {
						int temp = array[j];
						array[j] = array[j - 1];
						array[j - 1] = temp;						
						Swap(lbArrays[j - 1], lbArrays[j]);
					}
				}
			}			
		}
    }
    
    /*
	 * Interchange Sort
	 */
	public void InterchangeSort() {
            dem=0;
		if (isIncrease) {			
			int i, j;
			for (i = 0 ; i < num ; i++) {			
                                setlbPoint(lbPoint1, i, "i = ");
				for (j = i + 1; j < num ; j++) {
                                    setlbPoint(lbPoint2, j, "j = ");			
					if(array[j] < array[i]) {
						int temp = array[i];
						array[i] = array[j];
						array[j] = temp;			
						Swap(lbArrays[i], lbArrays[j]);
					}
				}
			}
			
		} else {			
			int i, j;
			for (i = 0 ; i < num ; i++) {				
                                setlbPoint(lbPoint1, i, "i = ");
				for (j = i + 1; j < num ; j++) {
                                    setlbPoint(lbPoint2, j, "j = ");					
					if(array[j] > array[i]) {
						int temp = array[i];
						array[i] = array[j];
						array[j] = temp;					
						Swap(lbArrays[i], lbArrays[j]);
					}
				}
			}			
		}
	}

    
    /*
    *Thuật toán Insertion Sort
                            */
    
    public void InsertionSort(){
        dem=0;
    	if (isIncrease) {
	        int pos, i, j;                
    		int x;	      
	        for (i = 1; i < num; i++) {  
                    pos=i;                    
                    setlbPoint(lbPoint1, i, "i= ");
                    j=i;                
	            
	             while(( j > 0) && ( array[j] < array[j-1] )) {	            	
                        setlbPoint(lbPoint2, j-1, "j= ");                  
                            int temp = array[j];
                            array[j]= array[j - 1];
                            array[j - 1] = temp;
                            setlbPoint(lbPointM, pos, "pos ");
                            Swap(lbArrays[j-1], lbArrays[j]);
                            j--;      
                    }
	        }
	      
    	}
    	else {
    		int pos, i, j;                
    		int x;	      
	        for (i = 1; i < num; i++) {  
                    pos=i;                    
                    setlbPoint(lbPoint1, i, "i= ");
                    j=i;                
	            
	            while(( j > 0) && ( array[j] > array[j-1] )) {	            	
                        setlbPoint(lbPoint2, j-1, "j= ");                  
                            int temp = array[j];
                            array[j]= array[j - 1];
                            array[j - 1] = temp;
                            setlbPoint(lbPointM, pos, "pos ");
                            Swap(lbArrays[j-1], lbArrays[j]);
                            j--;      
                    }
	        }	        
    	}
    }
    
/*
    Thuật toán QuickSort
    */
 
    public void QuickSort() {
        dem=0; // so lan hoan vi
        QuickSortAl(0, num - 1);
        QuickSortAnimation();
        step = 0;
    }
    
    public void Coloring(JLabel lb1, Color c) {
        curT ++;
        System.out.println(curT);
        int cur = curT;
        threads[cur] = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (cur != 0)
                        threads[cur - 1].join();
                    lb1.setBackground(c);
                    Thread.sleep(time);
                } catch (Exception e) {}
            }
        });
        threads[cur].start();
    }
    
    public void Coloring(int left, int right, Color c) {
        curT ++;
        System.out.println(curT);
        int cur = curT;
        threads[cur] = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (cur != 0)
                        threads[cur - 1].join();
                    int i = left;
                    while (i <= right) {
                        if (i != (left + right) / 2)
                            lbArrays[i].setBackground(c);
                        i ++;
                    }
                    Thread.sleep(time);
                } catch (Exception e) {}
            }
        });
        threads[cur].start();
    }

    public void QuickSortAnimation() {
        int s, i, j;
        for (s = 0; s < step; s ++) {
            i = lbI[s];
            j = lbJ[s];
            setlbPoint(lbPoint1, i, "i = ");
            setlbPoint(lbPoint2, j, "j = ");
         
            if (i != j) {
                Coloring(lbArrays[(lbL[s] + lbR[s]) / 2], selectedGreen);
                Coloring(lbL[s], lbR[s], selectedYellow);
                Swap(lbArrays[i], lbArrays[j]);     // dem hoan vi
            }
            if (lbL[s + 1] + lbR[s + 1] != lbL[s] + lbR[s]) {
                Coloring(lbArrays[(lbL[s] + lbR[s]) / 2], SystemColor.inactiveCaption);
                Coloring(lbL[s], lbR[s], SystemColor.inactiveCaption);
            }
        }
    }
    
    public void QuickSortAl(int left, int right) {
        if (isIncrease) {
	    	int i, j, x;
	        x = array[(left + right) / 2];
	        i = left; j = right;
	        while (i < j) {
	            while (array[i] < x) {
                        i ++;
                    }
	            while (array[j] > x) {
                        j --;
                    }
	            if (i <= j) {
                        if (array[i] != array[j]) {
	                    int temp = array[i];
	                    array[i] = array[j];
	                    array[j] = temp;
	                    if (i != j) {
                                lbL[step] = left; lbR[step] = right;
	                        lbI[step] = i; lbJ[step] = j;
	                        step ++;
	                    }
                        }
	                i ++; j --;
	            }
	        }
                if (i < right)
	            QuickSortAl(i, right);
	        if (left < j)
	            QuickSortAl(left, j);
	        
        }
        else {
        	int i, j, x;
	        x = array[(left + right) / 2];
	        i = left; j = right;
	        while (i < j) {
	            while (array[i] > x) {
                        i ++;
                    }
	            while (array[j] < x) {
                        j --;
                    }
	            if (i <= j) {
                        if (array[i] != array[j]) {
	                    int temp = array[i];
	                    array[i] = array[j];
	                    array[j] = temp;
	                    if (i != j) {
                                lbL[step] = left; lbR[step] = right;
	                        lbI[step] = i; lbJ[step] = j;
	                        step ++;
	                    }
                        }
	                i ++; 
                        j --;
	            }
	        }
	        if (left < j)
	            QuickSortAl(left, j);
	        if (i < right)
	            QuickSortAl(i, right);
        }
    }
      
   
    // </editor-fold>
    
       /*
     * Hàm chờ đợi để dừng thuật toán
     */
    public void waitEnd() {
    	curT++;
		
		int cur = curT;
		threads[cur] = new Thread(new Runnable() {
		    @Override
		    public void run() {
		    	try {
		    		if (cur != 0) {
			    		threads[cur-1].join();
			    	}
		    		setState(4);
		    		for (int i = 0; i < num; i++) {
		    			lbArrays[i].setForeground(Color.darkGray);
		    		}
                                
                                lbPoint1.setText("");
                                lbPoint2.setText("");
                                lbPointM.setText("");
                                
                                //  Hiện thông báo khi sắp xếp xong		    		
                                    ThongBao form = new ThongBao();
                                    form.setVisible(true);
                   
		    	} catch (Exception e) {
		    		
		    	}
		    }
		});
		threads[cur].start();                
    }
    
    /*
    Lấy dữ liệu nhập tay    
                            */
    public void setArray(int[] array) {
                btnDeleteArray(evt);
                spNum.setValue(array.length);
		num = (Integer)spNum.getValue();
		
		lbArrays = new JLabel[num];
		this.array = array;
		
		for (int i = 0; i < num; i++) {
			
			//create label, set text "0"
			lbArrays[i] = new JLabel(String.valueOf(array[i]));
			pnImitiate.add(lbArrays[i]);
			
			//set size label
			lbArrays[i].setSize(50,50);
			lbArrays[i].setOpaque(true);
			lbArrays[i].setForeground(Color.BLUE);
			
			//set location label
			if (i == 0)
				lbArrays[i].setLocation(((int) ((16 - num) * 0.5) * 70) + 100, 150);
			else
				lbArrays[i].setLocation(lbArrays[i-1].getX() + 70, 150);
			
			//set fonts
			lbArrays[i].setFont(new Font("Tahoma", Font.PLAIN, 30));
			
			//set background color
			lbArrays[i].setBackground(SystemColor.inactiveCaption);
			
			//set text alignment center
			lbArrays[i].setHorizontalAlignment(SwingConstants.CENTER); 
			lbArrays[i].setVerticalAlignment(SwingConstants.CENTER);
		}
                pnImitiate.add(lbPoint1);
                pnImitiate.add(lbPoint2);
                pnImitiate.add(lbPointM);
                
		pnImitiate.setVisible(true);
		pnImitiate.validate();
		pnImitiate.repaint();
		setState(2);
    }
    
    /*
    Kiểm tra mảng đã sắp xếp chưa     
                                */
    
    public boolean isSorted() {
    	if (isIncrease) {
    		for (int i = 0; i < array.length - 1; i++)
    			if (array[i] > array[i+1])
    				return false;
    	}	
    	else {
    		for (int i = 0; i < array.length - 1; i++)
    			if (array[i] < array[i+1])
    				return false;
    	}
    	return true;
    }
     
     
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        contentPane = new javax.swing.JPanel();
        pnAlgorithm = new javax.swing.JPanel();
        rdInsertionSort = new javax.swing.JRadioButton();
        rdSelectionSort = new javax.swing.JRadioButton();
        rdBubbleSort = new javax.swing.JRadioButton();
        rdInterchangeSort = new javax.swing.JRadioButton();
        rdQuickSort = new javax.swing.JRadioButton();
        pnControl = new javax.swing.JPanel();
        rdIncrease = new javax.swing.JRadioButton();
        rdDecrease = new javax.swing.JRadioButton();
        btnSort = new javax.swing.JButton();
        reset = new javax.swing.JButton();
        pnImitiate = new javax.swing.JPanel();
        lbHoanVi = new javax.swing.JLabel();
        lbHoanVi1 = new javax.swing.JLabel();
        pnArray = new javax.swing.JPanel();
        pnCreateArray = new javax.swing.JPanel();
        lbNum = new javax.swing.JLabel();
        spNum = new javax.swing.JSpinner();
        btnCreateArray = new javax.swing.JButton();
        btnDeleteArray = new javax.swing.JButton();
        pnSetValueArray = new javax.swing.JPanel();
        btnRandom = new javax.swing.JButton();
        btnByHand = new javax.swing.JButton();
        btnOpenFile = new javax.swing.JButton();
        btnReadFile = new javax.swing.JButton();
        btnXuatFile = new javax.swing.JButton();
        lbTutorial = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        contentPane.setBackground(new java.awt.Color(51, 255, 204));
        contentPane.setToolTipText("");

        buttonGroup1.add(rdInsertionSort);
        rdInsertionSort.setText("Insertion Sort");

        buttonGroup1.add(rdSelectionSort);
        rdSelectionSort.setText("Selection Sort");

        buttonGroup1.add(rdBubbleSort);
        rdBubbleSort.setText("Bubble Sort");

        buttonGroup1.add(rdInterchangeSort);
        rdInterchangeSort.setText("InterchangeSort");

        buttonGroup1.add(rdQuickSort);
        rdQuickSort.setSelected(true);
        rdQuickSort.setText("Quick Sort");

        javax.swing.GroupLayout pnAlgorithmLayout = new javax.swing.GroupLayout(pnAlgorithm);
        pnAlgorithm.setLayout(pnAlgorithmLayout);
        pnAlgorithmLayout.setHorizontalGroup(
            pnAlgorithmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnAlgorithmLayout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(pnAlgorithmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdInsertionSort, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdSelectionSort, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdQuickSort, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdBubbleSort, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdInterchangeSort, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        pnAlgorithmLayout.setVerticalGroup(
            pnAlgorithmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnAlgorithmLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(rdInsertionSort)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdSelectionSort)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rdBubbleSort)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdInterchangeSort)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdQuickSort)
                .addContainerGap(64, Short.MAX_VALUE))
        );

        buttonGroup2.add(rdIncrease);
        rdIncrease.setSelected(true);
        rdIncrease.setText("Sắp xếp tăng dần");
        rdIncrease.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdIncreaseActionPerformed(evt);
            }
        });

        buttonGroup2.add(rdDecrease);
        rdDecrease.setText("Sắp xếp giảm dần");
        rdDecrease.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdDecreaseActionPerformed(evt);
            }
        });

        btnSort.setText("Sắp Xếp");
        btnSort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSort(evt);
            }
        });

        reset.setText("Reset");
        reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReset(evt);
            }
        });

        javax.swing.GroupLayout pnControlLayout = new javax.swing.GroupLayout(pnControl);
        pnControl.setLayout(pnControlLayout);
        pnControlLayout.setHorizontalGroup(
            pnControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnControlLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdIncrease)
                    .addComponent(rdDecrease)
                    .addGroup(pnControlLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(pnControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(reset, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSort, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        pnControlLayout.setVerticalGroup(
            pnControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnControlLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(rdIncrease)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rdDecrease)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSort, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(reset, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        lbHoanVi.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbHoanVi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbHoanVi.setText("Thuật toán 1");
        lbHoanVi.setPreferredSize(new java.awt.Dimension(123, 22));

        lbHoanVi1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbHoanVi1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbHoanVi1.setText("Thuật toán 2");

        javax.swing.GroupLayout pnImitiateLayout = new javax.swing.GroupLayout(pnImitiate);
        pnImitiate.setLayout(pnImitiateLayout);
        pnImitiateLayout.setHorizontalGroup(
            pnImitiateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnImitiateLayout.createSequentialGroup()
                .addGap(288, 288, 288)
                .addComponent(lbHoanVi, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84)
                .addComponent(lbHoanVi1, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnImitiateLayout.setVerticalGroup(
            pnImitiateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnImitiateLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(pnImitiateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbHoanVi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbHoanVi1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(261, Short.MAX_VALUE))
        );

        lbNum.setText("Số phần tử");

        spNum.setModel(new javax.swing.SpinnerNumberModel(2, 0, 15, 1));

        btnCreateArray.setText("Tạo mảng");
        btnCreateArray.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateArray(evt);
            }
        });

        btnDeleteArray.setText("Xóa Mảng");
        btnDeleteArray.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteArray(evt);
            }
        });

        javax.swing.GroupLayout pnCreateArrayLayout = new javax.swing.GroupLayout(pnCreateArray);
        pnCreateArray.setLayout(pnCreateArrayLayout);
        pnCreateArrayLayout.setHorizontalGroup(
            pnCreateArrayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnCreateArrayLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lbNum, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(spNum, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(pnCreateArrayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCreateArray, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDeleteArray, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43))
        );
        pnCreateArrayLayout.setVerticalGroup(
            pnCreateArrayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnCreateArrayLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnCreateArrayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreateArray)
                    .addComponent(spNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNum))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDeleteArray)
                .addContainerGap())
        );

        btnRandom.setText("Ngẫu Nhiên");
        btnRandom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRandom(evt);
            }
        });

        btnByHand.setText("Nhập Tay");
        btnByHand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnByHand(evt);
            }
        });

        btnOpenFile.setText("Mở FIle");
        btnOpenFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenFile(evt);
            }
        });

        btnReadFile.setText("Đọc File");
        btnReadFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReadFile(evt);
            }
        });

        btnXuatFile.setText("Lưu mảng");
        btnXuatFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatFile(evt);
            }
        });

        javax.swing.GroupLayout pnSetValueArrayLayout = new javax.swing.GroupLayout(pnSetValueArray);
        pnSetValueArray.setLayout(pnSetValueArrayLayout);
        pnSetValueArrayLayout.setHorizontalGroup(
            pnSetValueArrayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnSetValueArrayLayout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addGroup(pnSetValueArrayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnOpenFile, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                    .addComponent(btnXuatFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addGroup(pnSetValueArrayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnByHand, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReadFile, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnSetValueArrayLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRandom)
                .addGap(44, 44, 44))
        );
        pnSetValueArrayLayout.setVerticalGroup(
            pnSetValueArrayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnSetValueArrayLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRandom)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnSetValueArrayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnByHand)
                    .addComponent(btnXuatFile))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnSetValueArrayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReadFile)
                    .addComponent(btnOpenFile))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout pnArrayLayout = new javax.swing.GroupLayout(pnArray);
        pnArray.setLayout(pnArrayLayout);
        pnArrayLayout.setHorizontalGroup(
            pnArrayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnArrayLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnArrayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnCreateArray, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnSetValueArray, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        pnArrayLayout.setVerticalGroup(
            pnArrayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnArrayLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnCreateArray, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnSetValueArray, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(87, Short.MAX_VALUE))
        );

        lbTutorial.setBackground(new java.awt.Color(255, 204, 204));
        lbTutorial.setText("Hướng Dẫn");
        lbTutorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lbTutorial(evt);
            }
        });

        javax.swing.GroupLayout contentPaneLayout = new javax.swing.GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPaneLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnAlgorithm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(pnArray, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addGroup(contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnControl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(contentPaneLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(lbTutorial, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(221, 221, 221))
            .addGroup(contentPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnImitiate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnImitiate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnArray, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(contentPaneLayout.createSequentialGroup()
                        .addGroup(contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnAlgorithm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnControl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(lbTutorial)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(contentPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(contentPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCreateArray(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateArray

//delete previous arrays and set number elements of array              
                btnDeleteArray(evt);
		num = (Integer)spNum.getValue();		
		lbArrays = new JLabel[num];
		array = new int[num];		
		for (int i = 0; i < num; i++) {			
			//create label, set text "0"
			lbArrays[i] = new JLabel("0");
			array[i] = 0;
			pnImitiate.add(lbArrays[i]);
			lbArrays[i].setText(String.valueOf(array[i]));			
			//set size label
			lbArrays[i].setSize(50,50);
			lbArrays[i].setOpaque(true);
			lbArrays[i].setForeground(Color.blue);			
			//set location label
			if (i == 0)
				lbArrays[i].setLocation(((int) ((16 - num) * 0.5) * 70) + 100, 150);
			else
				lbArrays[i].setLocation(lbArrays[i-1].getX() + 70, 150);			
			//set fonts
			lbArrays[i].setFont(new Font("Tahoma", Font.PLAIN, 30));			
			//set background color
			lbArrays[i].setBackground(SystemColor.inactiveCaption);
			//set text alignment center
			lbArrays[i].setHorizontalAlignment(SwingConstants.CENTER); 
			lbArrays[i].setVerticalAlignment(SwingConstants.CENTER);
		}
                
                pnImitiate.add(lbPoint1);
                pnImitiate.add(lbPoint2);
                pnImitiate.add(lbPointM);
                
		pnImitiate.setVisible(true);
		pnImitiate.validate();
		pnImitiate.repaint();
		setState(1);
    }//GEN-LAST:event_btnCreateArray

    private void btnDeleteArray(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteArray
        for (int i = 0; i < num; i++) {
		lbArrays[i].setVisible(false);
		pnImitiate.remove(lbArrays[i]);
	}
                
        lbPoint1.setText("");
        lbPoint2.setText("");
        lbPointM.setText("");
        pnImitiate.remove(lbPoint1);
        pnImitiate.remove(lbPoint2);
        pnImitiate.remove(lbPointM);
		
	for (int i = 0; i < curT; i++) {
		try {
                    threads[i].interrupt();
		} 
		catch (Exception e) {
				
		}
	}
	curT = -1;		
	pnImitiate.revalidate();
	pnImitiate.repaint();
		
       setState(0);
    }//GEN-LAST:event_btnDeleteArray

 
    private void btnByHand(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnByHand
              NhapTay NhapTay = new NhapTay();
                    NhapTay.setVisible(true);
                    setState(3);

    }//GEN-LAST:event_btnByHand

    private void btnRandom(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRandom
           
        Random rand = new Random();
		for (int i = 0; i < num; i++) {
			int ranNum = rand.nextInt(100) ;
			lbArrays[i].setText(String.valueOf(ranNum));
			lbArrays[i].setForeground(Color.BLUE);
			array[i] = ranNum;
		}
		pnImitiate.setVisible(true);
		pnImitiate.validate();
		pnImitiate.repaint();
		setState(2);
    }//GEN-LAST:event_btnRandom
     
    private void btnOpenFile(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenFile

        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter txtFilter = new FileNameExtensionFilter("txt", "txt");
        fileChooser.setFileFilter(txtFilter);
        fileChooser.setMultiSelectionEnabled(false);
        
 // Chọn file      
        int x = fileChooser.showDialog(this, "OK");
        if(x == JFileChooser.APPROVE_OPTION){
            F = fileChooser.getSelectedFile();
                     
/*
    * Sửa nội dung file
                */
//    try {        
//        Desktop desktop = null;
//        if (Desktop.isDesktopSupported()) {
//       	desktop = Desktop.getDesktop();
//	}
//        desktop.open(F);
//    } catch (IOException ex) {
//        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//    }
        }
		
    }//GEN-LAST:event_btnOpenFile

    private void btnReadFile(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReadFile
        btnDeleteArray(evt);
        try {
                    Scanner in = new Scanner(F);
                    num = Integer.parseInt(in.nextLine());
                    array = new int[num];
                    int pos = 0;
                    while (in.hasNextLine()) {
                    	array[pos] = Integer.parseInt(in.nextLine());
                    	pos++;
                    }
                    in.close();
                    lbArrays = new JLabel[num];
				
                    for (int i = 0; i < num; i++) {
                    //create label, set text "0"
					
                        lbArrays[i] = new JLabel(String.valueOf(array[i]));
                        pnImitiate.add(lbArrays[i]);
						
                    //set size label
                        lbArrays[i].setSize(50,50);
                        lbArrays[i].setOpaque(true);
                        lbArrays[i].setForeground(Color.BLUE);
						
                    //set location label
                      if (i == 0)
				lbArrays[i].setLocation(((int) ((16 - num) * 0.5) * 70) + 100, 150);
			else
				lbArrays[i].setLocation(lbArrays[i-1].getX() + 70, 150);
						
                    //set fonts
                            lbArrays[i].setFont(new Font("Tahoma", Font.PLAIN, 30));
						
                    //set background color
                            lbArrays[i].setBackground(SystemColor.inactiveCaption);
						
//                    //set text alignment center
                        	lbArrays[i].setHorizontalAlignment(SwingConstants.CENTER); 
				lbArrays[i].setVerticalAlignment(SwingConstants.CENTER);
			}
                        pnImitiate.add(lbPoint1);
                        pnImitiate.add(lbPoint2);
                        pnImitiate.add(lbPointM);
                    
			pnImitiate.setVisible(true);
			pnImitiate.validate();
			pnImitiate.repaint();
			
			} catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                        	e.printStackTrace();
			}
        setState(2);
    }//GEN-LAST:event_btnReadFile

    private void btnSort(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSort
       
        if (!isSorted()) {
            setState(3);
            for (int i = 0; i < num; i++) 
		lbArrays[i].setForeground(Color.BLUE);
				
		if (rdSelectionSort.isSelected()){
			SelectionSort();
                        if(e%2==0){
                lbHoanVi.setText(String.valueOf("Số lần hoán vị SeclectionSort: "+ dem));
                lbHoanVi.setSize(50,50);
                lbHoanVi.setOpaque(true);
                lbHoanVi.setForeground(Color.blue);
                lbHoanVi.setFont(new Font("Tahoma", Font.PLAIN, 18));
            }
            else{
                lbHoanVi1.setText(String.valueOf("Số lần hoán vị SelectionSort: "+ dem));
                lbHoanVi1.setSize(50,50);
                lbHoanVi1.setOpaque(true);
                lbHoanVi1.setForeground(Color.blue);
                lbHoanVi1.setFont(new Font("Tahoma", Font.PLAIN, 18));
            }
                }     
		if (rdBubbleSort.isSelected()){
			BubbleSort();
                        if(e%2==0){
                            lbHoanVi.setText(String.valueOf("Số lần hoán vị BubbleSort: "+ dem));
                            lbHoanVi.setSize(50,50);
                            lbHoanVi.setOpaque(true);
                            lbHoanVi.setForeground(Color.blue);
                            lbHoanVi.setFont(new Font("Tahoma", Font.PLAIN, 18));
                        }
                        else{
                            lbHoanVi1.setText(String.valueOf("Số lần hoán vịBubbleSort: "+ dem));
                            lbHoanVi1.setSize(50,50);
                            lbHoanVi1.setOpaque(true);
                            lbHoanVi1.setForeground(Color.blue);
                            lbHoanVi1.setFont(new Font("Tahoma", Font.PLAIN, 18));
                        }
                }
		if (rdInsertionSort.isSelected()){
                        InsertionSort();
                        if(e%2==0){
                            lbHoanVi.setText(String.valueOf("Số lần hoán vị IntertionSort: "+ dem));
                            lbHoanVi.setSize(50,50);
                            lbHoanVi.setOpaque(true);
                            lbHoanVi.setForeground(Color.blue);
                            lbHoanVi.setFont(new Font("Tahoma", Font.PLAIN, 18));
                        }
                        else{
                            lbHoanVi1.setText(String.valueOf("Số lần hoán vị IntertionSort: "+ dem));
                            lbHoanVi1.setSize(50,50);
                            lbHoanVi1.setOpaque(true);
                            lbHoanVi1.setForeground(Color.blue);
                            lbHoanVi1.setFont(new Font("Tahoma", Font.PLAIN, 18));
                        }
                }
		if (rdInterchangeSort.isSelected()) {
			InterchangeSort();      
                        if(e%2==0){
                            lbHoanVi.setText(String.valueOf("Số lần hoán vị InterchangeSort: "+ dem));
                            lbHoanVi.setSize(50,50);
                            lbHoanVi.setOpaque(true);
                            lbHoanVi.setForeground(Color.blue);
                            lbHoanVi.setFont(new Font("Tahoma", Font.PLAIN, 18));
                        }
                        else{
                            lbHoanVi1.setText(String.valueOf("Số lần hoán vị InterchangeSort: "+ dem));
                            lbHoanVi1.setSize(50,50);
                            lbHoanVi1.setOpaque(true);
                            lbHoanVi1.setForeground(Color.blue);
                            lbHoanVi1.setFont(new Font("Tahoma", Font.PLAIN, 18));
                        }
		}				
                if (rdQuickSort.isSelected()){
			QuickSort();
                        if(e%2==0){
                            lbHoanVi.setText(String.valueOf("Số lần hoán vị QuickSort: "+ dem));
                            lbHoanVi.setSize(50,50);
                            lbHoanVi.setOpaque(true);
                            lbHoanVi.setForeground(Color.blue);
                            lbHoanVi.setFont(new Font("Tahoma", Font.PLAIN, 18));
                        }
                        else{
                            lbHoanVi1.setText(String.valueOf("Số lần hoán vị QuickSort: "+ dem));
                            lbHoanVi1.setSize(50,50);
                            lbHoanVi1.setOpaque(true);
                            lbHoanVi1.setForeground(Color.blue);
                            lbHoanVi1.setFont(new Font("Tahoma", Font.PLAIN, 18));
                        }
                }
		
          
                waitEnd();                    	     
                e++;
        }
                                
    }//GEN-LAST:event_btnSort

 
    private void rdIncreaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdIncreaseActionPerformed
 	
        isIncrease = true;
    }//GEN-LAST:event_rdIncreaseActionPerformed

    private void rdDecreaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdDecreaseActionPerformed
     
        isIncrease = false;
    }//GEN-LAST:event_rdDecreaseActionPerformed

    private void btnXuatFile(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatFile
        String tenFile = JOptionPane.showInputDialog(this, "Nhập tên file", "Xuất file", JOptionPane.DEFAULT_OPTION);
        File file = new File(tenFile);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        pw.println(num);
        for(int i=0; i< num; i++){     
          pw.println(lbArrays[i].getText());    
    }      
        pw.close();
        JOptionPane.showConfirmDialog(this, "Lưu file hành công", "Thành công", JOptionPane.CLOSED_OPTION);

    }//GEN-LAST:event_btnXuatFile

    private void btnReset(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReset
        e = 0;       
        btnDeleteArray(evt);
        lbHoanVi.setText("Thuật toán 1" );
        lbHoanVi.setForeground(Color.BLACK);
        lbHoanVi1.setText("Thuật toán 2" );
        lbHoanVi1.setForeground(Color.BLACK);
        
    }//GEN-LAST:event_btnReset

    private void lbTutorial(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lbTutorial
        HuongDan hd = new HuongDan();
        hd.setVisible(true);
        hd.setLocationRelativeTo(null);
          
    }//GEN-LAST:event_lbTutorial

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
       EventQueue.invokeLater(new Runnable() {
			public void run() {
				setLockAndFeel();
				try {
					frame = new Main();
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH); //set JFrame full screen
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnByHand;
    private javax.swing.JButton btnCreateArray;
    private javax.swing.JButton btnDeleteArray;
    private javax.swing.JButton btnOpenFile;
    private javax.swing.JButton btnRandom;
    private javax.swing.JButton btnReadFile;
    private javax.swing.JButton btnSort;
    private javax.swing.JButton btnXuatFile;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JPanel contentPane;
    private javax.swing.JLabel lbHoanVi;
    private javax.swing.JLabel lbHoanVi1;
    private javax.swing.JLabel lbNum;
    private javax.swing.JButton lbTutorial;
    private javax.swing.JPanel pnAlgorithm;
    private javax.swing.JPanel pnArray;
    private javax.swing.JPanel pnControl;
    private javax.swing.JPanel pnCreateArray;
    private javax.swing.JPanel pnImitiate;
    private javax.swing.JPanel pnSetValueArray;
    private javax.swing.JRadioButton rdBubbleSort;
    private javax.swing.JRadioButton rdDecrease;
    private javax.swing.JRadioButton rdIncrease;
    private javax.swing.JRadioButton rdInsertionSort;
    private javax.swing.JRadioButton rdInterchangeSort;
    private javax.swing.JRadioButton rdQuickSort;
    private javax.swing.JRadioButton rdSelectionSort;
    private javax.swing.JButton reset;
    private javax.swing.JSpinner spNum;
    // End of variables declaration//GEN-END:variables
}
