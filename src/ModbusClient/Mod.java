package ModbusClient;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.serotonin.modbus4j.ip.IpParameters;
import com.serotonin.modbus4j.msg.*;
import com.serotonin.modbus4j.msg.WriteRegistersRequest;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;


import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Properties;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.JProgressBar;
import javax.swing.JToolBar;
import javax.swing.JSplitPane;
import javax.swing.JDesktopPane;
import javax.swing.JTabbedPane;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextPane;
import javax.swing.JTextArea;

public class Mod extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JTextField IPField;
	private JTextField PortField;
	private JTextField SlaveField;
	private JTextField ReadAdressField;
	private JTextField LengthField;
	private JTextField AdressField;
	private JTextField DataField;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private ModbusMaster master;

	JCheckBox doubleCheckBox;
	JCheckBox reverseCheckBox;
	//JTextPane textPane ;
	
	private ConfigManager configManager;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new Mod();
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					Mod frame = new Mod();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	String arr[]= {"Coils","Input Registers","Holding Registers","Inputs"};
	String arr2[]= {"Even ","Odd","None"};
	String arr3[]= {"Coils","Input Registers","Holding Registers","Inputs"};
	
	JTextPane textPane ;

	/**
	 * Create the frame.
	 */
	public Mod() {
		//initialize();
		setTitle("Modbus");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 783, 619);
		configManager=new ConfigManager();

		//InitUI();
		
		//tabbedPane.setBounds(0, 10, 79, 36);
		//getContentPane().add(tabbedPane);
		//getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 629, 582);
		getContentPane().add(tabbedPane);
		
		/*JLabel label = new JLabel(" ");
		Image icon=new ImageIcon(Mod.class.getResource("/logo3.png")).getImage();
		label.setIcon(new ImageIcon( icon));
		label.setBounds(292, 195, 293, 317);
		panel.add(label);*/
		
		JPanel p = new JPanel();
		p.setForeground(new Color(128, 0, 0));
		tabbedPane.addTab("TCP", null, p, null);
		p.setLayout(null);
		
		JLabel IPLabel = new JLabel("IP");
		IPLabel.setForeground(new Color(0, 0, 128));
		IPLabel.setBounds(10, 10, 45, 13);
		p.add(IPLabel);
		
		IPField = new JTextField(configManager.getIp());
		IPField.setBounds(10, 33, 96, 19);
		p.add(IPField);
		IPField.setColumns(10);
		
		JLabel PortLabel = new JLabel("Port");
		PortLabel.setForeground(new Color(0, 0, 128));
		PortLabel.setBounds(148, 10, 45, 13);
		p.add(PortLabel);
		
		PortField = new JTextField(String.valueOf(configManager.getPort()));
		PortField.setBounds(140, 33, 96, 19);
		p.add(PortField);
		PortField.setColumns(10);
		
		JLabel SlaveLabel = new JLabel("Slave Id");
		SlaveLabel.setForeground(new Color(0, 0, 128));
		SlaveLabel.setBounds(278, 10, 75, 13);
		p.add(SlaveLabel);
		
		SlaveField = new JTextField(String.valueOf(configManager.getSlaveid()));
		SlaveField.setBounds(278, 33, 96, 19);
		p.add(SlaveField);
		SlaveField.setColumns(10);
		
		JLabel ReadAdressLabel = new JLabel("Read Adress");
		ReadAdressLabel.setForeground(new Color(0, 0, 128));
		ReadAdressLabel.setBounds(10, 62, 85, 13);
		p.add(ReadAdressLabel);
		
		ReadAdressField = new JTextField();
		ReadAdressField.setBounds(10, 85, 96, 19);
		p.add(ReadAdressField);
		ReadAdressField.setColumns(10);
		
		JLabel LengthLabel = new JLabel("Length");
		LengthLabel.setForeground(new Color(0, 0, 128));
		LengthLabel.setBounds(148, 62, 45, 13);
		p.add(LengthLabel);
		
		LengthField = new JTextField();
		LengthField.setBounds(140, 85, 96, 19);
		p.add(LengthField);
		LengthField.setColumns(10);
		
		JComboBox comboBox = new JComboBox(arr);
		comboBox.setBounds(282, 84, 145, 21);
		p.add(comboBox);
		comboBox.setSelectedItem("Holding Registers");
		
		JButton ReadButton = new JButton("Read TCP");
		ReadButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		ReadButton.setForeground(new Color(128, 0, 0));
		ReadButton.setBackground(new Color(192, 192, 192));
		ReadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				readModbus();
	
			}
		});
		ReadButton.setBounds(437, 84, 126, 21);
		p.add(ReadButton);
		
		JLabel AdressLabel = new JLabel("Adress");
		AdressLabel.setForeground(new Color(0, 0, 128));
		AdressLabel.setBounds(10, 128, 45, 13);
		p.add(AdressLabel);
		
		AdressField = new JTextField();
		AdressField.setBounds(10, 151, 96, 19);
		p.add(AdressField);
		AdressField.setColumns(10);
		
		JLabel DataLabel = new JLabel("Data");
		DataLabel.setForeground(new Color(0, 0, 128));
		DataLabel.setBounds(148, 128, 45, 13);
		p.add(DataLabel);
		
		DataField = new JTextField();
		DataField.setBounds(140, 151, 96, 19);
		p.add(DataField);
		DataField.setColumns(10);
		
		doubleCheckBox = new JCheckBox("Double");
		doubleCheckBox.setBounds(261, 150, 75, 21);
		p.add(doubleCheckBox);
		
			reverseCheckBox = new JCheckBox("Reverse");
			reverseCheckBox.setBounds(338, 150, 93, 21);
			p.add(reverseCheckBox);
			
			JButton WriteButton = new JButton("Write TCP");
			WriteButton.setFont(new Font("Tahoma", Font.BOLD, 12));
			WriteButton.setForeground(new Color(128, 0, 0));
			WriteButton.setBackground(new Color(192, 192, 192));
			WriteButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					writeModbus();
					
				}
			});
			
			WriteButton.setBounds(437, 150, 126, 21);
			p.add(WriteButton);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 195, 272, 350);
			p.add(scrollPane);
			
			 textPane = new JTextPane();
			 scrollPane.setViewportView(textPane);
			 
			 Image img=new ImageIcon(this.getClass().getResource("/indir.png")).getImage(); 
			 	JLabel label = new JLabel("");
			 	label.setIcon(new ImageIcon(img));
			 	label.setBounds(316, 204, 360, 318);
			 	p.add(label);
			 				 	

			 	JButton saveButton = new JButton("Save");
			 	saveButton.setBackground(new Color(192, 192, 192));
			 	saveButton.setForeground(new Color(128, 0, 0));
			 	saveButton.setFont(new Font("Verdana", Font.BOLD, 12));
			 	saveButton.setBounds(437, 32, 126, 21);
			 	p.add(saveButton);
			 	saveButton.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent e) {
				 String ip=IPField.getText();
				 int port;
				 int slaveid;
				 try {
					 
					 port=Integer.parseInt(PortField.getText());
					 slaveid=Integer.parseInt(SlaveField.getText());
				 }
				 catch(NumberFormatException ex) {
					 appendToTextPane("Success");
					 return;
				 }
				 configManager.saveConfig(ip, port, slaveid);
				 appendToTextPane("Kaydedildi");
				 
				 
			 	}
			 
			 	});	
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Serial", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_7 = new JLabel("Serial Port");
		lblNewLabel_7.setForeground(new Color(0, 0, 128));
		lblNewLabel_7.setBounds(10, 10, 68, 13);
		panel_1.add(lblNewLabel_7);
		
		textField_7 = new JTextField();
		textField_7.setBounds(10, 29, 96, 19);
		panel_1.add(textField_7);
		textField_7.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Speed");
		lblNewLabel_8.setForeground(new Color(0, 0, 128));
		lblNewLabel_8.setBounds(132, 10, 45, 13);
		panel_1.add(lblNewLabel_8);
		
		textField_8 = new JTextField();
		textField_8.setBounds(124, 29, 96, 19);
		panel_1.add(textField_8);
		textField_8.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Parity");
		lblNewLabel_9.setForeground(new Color(0, 0, 128));
		lblNewLabel_9.setBounds(249, 10, 45, 13);
		panel_1.add(lblNewLabel_9);
		
		JComboBox comboBox_1 = new JComboBox(arr2);
		comboBox_1.setBounds(242, 28, 68, 21);
		panel_1.add(comboBox_1);
		
		JLabel lblNewLabel_10 = new JLabel("Stop Bits");
		lblNewLabel_10.setForeground(new Color(0, 0, 128));
		lblNewLabel_10.setBounds(341, 10, 90, 13);
		panel_1.add(lblNewLabel_10);
		
		textField_9 = new JTextField();
		textField_9.setBounds(333, 29, 96, 19);
		panel_1.add(textField_9);
		textField_9.setColumns(10);
		
		JLabel lblNewLabel_11 = new JLabel("Stop Bits");
		lblNewLabel_11.setForeground(new Color(0, 0, 128));
		lblNewLabel_11.setBounds(465, 10, 121, 13);
		panel_1.add(lblNewLabel_11);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(465, 28, 96, 21);
		panel_1.add(comboBox_2);
		
		JLabel lblNewLabel_12 = new JLabel("Slave Id");
		lblNewLabel_12.setForeground(new Color(0, 0, 128));
		lblNewLabel_12.setBounds(10, 69, 96, 13);
		panel_1.add(lblNewLabel_12);
		
		textField_10 = new JTextField();
		textField_10.setBounds(10, 94, 96, 19);
		panel_1.add(textField_10);
		textField_10.setColumns(10);
		
		JComboBox comboBox_3 = new JComboBox(arr3);
		comboBox_3.setBounds(132, 93, 106, 21);
		panel_1.add(comboBox_3);
		
		JLabel lblNewLabel_13 = new JLabel("Read Adress");
		lblNewLabel_13.setForeground(new Color(0, 0, 128));
		lblNewLabel_13.setBounds(231, 69, 96, 13);
		panel_1.add(lblNewLabel_13);
		
		textField_11 = new JTextField();
		textField_11.setBounds(249, 92, 75, 19);
		panel_1.add(textField_11);
		textField_11.setColumns(10);
		
		JLabel lblNewLabel_14 = new JLabel("Length");
		lblNewLabel_14.setForeground(new Color(0, 0, 128));
		lblNewLabel_14.setBounds(341, 69, 45, 13);
		panel_1.add(lblNewLabel_14);
		
		textField_12 = new JTextField();
		textField_12.setBounds(341, 94, 57, 19);
		panel_1.add(textField_12);
		textField_12.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("Read RTU");
		btnNewButton_2.setBackground(new Color(192, 192, 192));
		btnNewButton_2.setForeground(new Color(128, 0, 0));
		btnNewButton_2.setBounds(408, 93, 102, 21);
		panel_1.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Read ASCII");
		btnNewButton_3.setBackground(new Color(192, 192, 192));
		btnNewButton_3.setForeground(new Color(128, 0, 0));
		btnNewButton_3.setBounds(520, 93, 104, 21);
		panel_1.add(btnNewButton_3);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 150, 311, 395);
		panel_1.add(scrollPane_1);
		
		JTextPane textPane_1 = new JTextPane();
		scrollPane_1.setViewportView(textPane_1);
		
		JLabel lblNewLabel_15 = new JLabel("Slave Id");
		lblNewLabel_15.setForeground(new Color(0, 0, 128));
		lblNewLabel_15.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_15.setBounds(333, 151, 57, 30);
		panel_1.add(lblNewLabel_15);
		
		textField_13 = new JTextField();
		textField_13.setBounds(333, 191, 57, 19);
		panel_1.add(textField_13);
		textField_13.setColumns(10);
		
		textField_14 = new JTextField();
		textField_14.setBounds(400, 191, 51, 19);
		panel_1.add(textField_14);
		textField_14.setColumns(10);
		
		JButton btnNewButton_4 = new JButton("RTU Tara");
		btnNewButton_4.setBackground(new Color(192, 192, 192));
		btnNewButton_4.setForeground(new Color(128, 0, 0));
		btnNewButton_4.setBounds(465, 190, 121, 21);
		panel_1.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("ASCII Tara");
		btnNewButton_5.setBackground(new Color(192, 192, 192));
		btnNewButton_5.setForeground(new Color(128, 0, 0));
		btnNewButton_5.setBounds(465, 221, 121, 21);
		panel_1.add(btnNewButton_5);
	   // Image img=new ImageIcon(this.getClass().getResource("/mitsu.png")).getImage();
		
	}
	
	private void readModbus() {
		String ip=IPField.getText();
		int port=Integer.parseInt(PortField.getText());
		int slaveid=Integer.parseInt(SlaveField.getText());
		int readadress=Integer.parseInt(ReadAdressField.getText());
		int length=Integer.parseInt(LengthField.getText());
		
		IpParameters params=new IpParameters();
		params.setHost(ip);
		params.setPort(port);
		
		ModbusFactory modbusFactory=new ModbusFactory();
		ModbusMaster master = modbusFactory.createTcpMaster(params,true);
		
	
		try {
			try {
				master.init();
			} catch (ModbusInitException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			ReadHoldingRegistersRequest readRequest=new ReadHoldingRegistersRequest(slaveid,readadress,length); /* !!*/
			ReadHoldingRegistersResponse readResponse= (ReadHoldingRegistersResponse)master.send(readRequest);
			
			if(readResponse.isException()) {
				appendToTextPane("Read Exception:"+readResponse.getExceptionMessage());
			}
			else {
				
				StringBuilder result=new StringBuilder();
				short[] data=readResponse.getShortData();
				if(reverseCheckBox.isSelected()) {
					data=reverseData(data);	
					appendToTextPane("Reverse Read Success");
			}
				for(short datum: data) {
					result.append(datum).append(" ");
				}
				
				
				
				appendToTextPane("Read Success:"+result.toString());
			}
			
		}catch(ModbusTransportException e) {
			appendToTextPane("Modbus Error"+ e.getMessage());
		}finally {
			master.destroy();
			
		}		
	}
		private void writeModbus() {	
		String ip=IPField.getText();
		int port=Integer.parseInt(PortField.getText());
		int slaveid=Integer.parseInt(SlaveField.getText());
		int data=Integer.parseInt(DataField.getText());
		int adress=Integer.parseInt(AdressField.getText());
		IpParameters params=new IpParameters();
		params.setHost(ip);
		params.setPort(port);
		ModbusFactory modbusFactory=new ModbusFactory();
	    master = modbusFactory.createTcpMaster(params,true);
		
		try {
			try {
				master.init();
			} catch (ModbusInitException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			 
				if(doubleCheckBox.isSelected()) {
					data*=2;
					appendToTextPane("Double Data:"+ data);
					WriteRegistersRequest writeRequest=new WriteRegistersRequest(slaveid,adress,new short[] {(short) data});
					master.send(writeRequest);
					appendToTextPane("Succes Write  " +"Adress :"+adress+" "+ "Data:"+data);
		
				}
				else {
					WriteRegistersRequest writeRequest=new WriteRegistersRequest(slaveid,adress,new short[] {(short) data});
					master.send(writeRequest);
					appendToTextPane("Succes Write  " +"Adress :"+adress+" "+ "Data:"+data);
					
				}
		}

			
		catch(ModbusTransportException e) {
			appendToTextPane("Modbus Error"+e.getMessage());
			
		}finally {
			master.destroy();

		}
		
	}
	private void appendToTextPane(String message) {
	
		textPane.setText(textPane.getText()+message+"\n");
    }
	

	private short[] reverseData(short[] data) {
		short[] reversedData=new short[data.length];
		for(int i=0;i<data.length;i++) {
			reversedData[i]=data[data.length-1-i];
		}
		return reversedData;
	 }
	
  }
