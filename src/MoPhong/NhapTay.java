/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MoPhong;

import static com.sun.java.accessibility.util.AWTEventMonitor.addActionListener;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author HP
 */
public class NhapTay extends javax.swing.JFrame {
private static final long serialVersionUID = 1L;
	private JSpinner[] txtArrays;
	private JLabel[] lbArrays;
	private int num ;
	private int[] arrays;
        

    /**
     * Creates new form NhapTay
     */
     public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
		public void run() {
                    setLockAndFeel();
                    try {
                        NhapTay frame = new NhapTay();
                        frame.setVisible(true);
			} catch (Exception e) {
                            e.printStackTrace();
                            }
			}
		});
          
	}
    public NhapTay() {
        initComponents();	   
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
          setTitle("Nhập dữ liệu tay");
    }
    
    
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
     
	public void deleteArrays() {
		for (int i = 0; i < num; i++) {
			lbArrays[i].setVisible(false);
			txtArrays[i].setVisible(false);
			contentPane.remove(lbArrays[i]);
			contentPane.remove(txtArrays[i]);
		}
	}
	
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
   // @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        try {
            contentPane =(javax.swing.JPanel)java.beans.Beans.instantiate(getClass().getClassLoader(), "MoPhong.NhapTay_contentPane");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        btnOK = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        spNum = new javax.swing.JSpinner();
        TaoMang = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnOK.setText("Xác nhận");
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKActionPerformed(evt);
            }
        });

        jLabel1.setText("Nhập số phần tử của mảng ( từ 2 đến 15 )");

        spNum.setModel(new javax.swing.SpinnerNumberModel(0, 0, 15, 1));

        TaoMang.setText("Tạo mảng");
        TaoMang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TaoMangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout contentPaneLayout = new javax.swing.GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPaneLayout.createSequentialGroup()
                .addGroup(contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnOK, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(contentPaneLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(spNum, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(38, 38, 38)
                .addComponent(TaoMang, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(spNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TaoMang))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 230, Short.MAX_VALUE)
                .addComponent(btnOK)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contentPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contentPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TaoMangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TaoMangActionPerformed
        deleteArrays();
        num = (Integer)spNum.getValue();//
        arrays = new int[num];
        lbArrays = new JLabel[num];
        txtArrays = new JSpinner[num];
        arrays = new int[num];

        for (int i = 0; i < num; i++) {
            // Vị trí phần tử mảng
            lbArrays[i] = new JLabel("A[" + i + "]:");
            SpinnerModel smValue = new SpinnerNumberModel(0, 0, 100, 1);
            
            txtArrays[i] = new JSpinner(smValue);
            JFormattedTextField txt = ((JSpinner.NumberEditor) txtArrays[i].getEditor()).getTextField();
            ((NumberFormatter) txt.getFormatter()).setAllowsInvalid(false);
            contentPane.add(lbArrays[i]);
            contentPane.add(txtArrays[i]);
            lbArrays[i].setSize(40,30);
            
            //canh chỉnh giao diện nếu đủ  dòng sẽ tạo cột kế tiếp
            if (i == 0 || i == 5 || i == 10)
                lbArrays[i].setLocation(150 * (i + 1)/5  , 40);
            else
                lbArrays[i].setLocation(lbArrays[i-1].getX(), lbArrays[i-1].getY() + 40);
                txtArrays[i].setSize(50,30);
                txtArrays[i].setLocation(lbArrays[i].getX() + 40, lbArrays[i].getY());
        }
        contentPane.setVisible(true);
        contentPane.validate();
        contentPane.repaint();

    }//GEN-LAST:event_TaoMangActionPerformed

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        //Thêm phần tử vào mảng
       for (int i = 0; i < num; i++) {
			arrays[i] = (int) txtArrays[i].getValue();
			System.out.println(arrays[i]);	
		}
		Frame[] listFrames = Frame.getFrames();
		((Main) listFrames[0]).setArray(arrays);
		
        // Xuất thông báo đã tạo dữ liệu
		if (num != 0) {
			JOptionPane.showMessageDialog(this, "Đã tạo dữ liệu mảng thành công!\nChuẩn bị thoát!");
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}
                else {
			JOptionPane.showMessageDialog(this, "Chưa tạo được dữ liệu mảng!\nChuẩn bị thoát!");
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}
		
    }//GEN-LAST:event_btnOKActionPerformed
          
    /**
     * @param args the command line arguments
     */
   
    public int getNum() {
	return num;
	}
	
    public int[] getArrays() {
	return arrays;
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton TaoMang;
    private javax.swing.JButton btnOK;
    private javax.swing.JPanel contentPane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSpinner spNum;
    // End of variables declaration//GEN-END:variables
}

   
