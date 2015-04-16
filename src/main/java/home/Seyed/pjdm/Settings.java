package home.Seyed.pjdm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.*;

/*
 *This is Settings class for Download Limitation and Determine Save place... 
 * @author SMA74/9031806
 */
public class Settings extends JFrame {
	private JPanel panel, panel2, panel3;
	private JButton enter, browse;
	private BorderLayout borderLayout;
	private FlowLayout flowLayout;
	private JSpinner js;
	private JTextField directory;
	private JCheckBox unlimited;
	private JLabel label, label2;

	public Settings(){
		super("تنظیمات");
		js = new JSpinner() {
			@Override
			public void setValue(Object value){
				if (value instanceof Integer && (Integer) value >= 0)
					getModel().setValue(value);
				else
					JOptionPane.showMessageDialog(this, "لطفا عدد صحیح وارد کنید", "خطا", JOptionPane.ERROR_MESSAGE);
			}
		};
		js.setPreferredSize(new Dimension(80, 30));
		if (NewURL.downloadLimit == -1)
			js.setEnabled(false);
		else {
			js.setEnabled(true);
			js.setValue(NewURL.downloadLimit);
		}
		label = new JLabel("تعداد دانلودهای همزمان");
		unlimited = new JCheckBox("نامحدود");
		if (NewURL.downloadLimit == -1)
			unlimited.setSelected(true);
		else
			unlimited.setSelected(false);
		label2 = new JLabel("محل ذخیره دانلودها");
		directory = new JTextField(Downloading.dir);
		directory.setEditable(false);
		directory.setPreferredSize(new Dimension(350, 30));
		browse = new JButton("پیمایش");
		enter = new JButton("تایید");
		panel = new JPanel();
		panel.add(label, flowLayout);
		panel.add(js, flowLayout);
		panel.add(unlimited, flowLayout);
		enter.addActionListener(new EnterHandler());
		unlimited.addActionListener(new CheckboxHandler());
		browse.addActionListener(new ButtonHandler());
		panel2 = new JPanel();
		panel3 = new JPanel();
		panel3.add(enter);
		panel2.add(label2);
		panel2.add(directory);
		panel2.add(browse);
		add(panel, borderLayout.CENTER);
		add(panel2, borderLayout.NORTH);
		add(panel3, borderLayout.SOUTH);
		pack();
		setLocation(300, 150);
		setResizable(false);
	}

	private class CheckboxHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e){
			js.setEnabled(!js.isEnabled());
		}
	}

	private class EnterHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event){
			Downloading.dir = directory.getText();
			if (unlimited.isSelected() == true) {
				NewURL.downloadLimit = -1;
			} else {
				NewURL.downloadLimit = (Integer) js.getValue();
			}
			WindowEvent wev = new WindowEvent(Settings.this, WindowEvent.WINDOW_CLOSING);
			Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
		}
	}

	private class ButtonHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e){
			JFileChooser fileChooser = new JFileChooser("C:/Users/SMA74/Desktop");
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			if (fileChooser.showDialog(JDM.frame, "Save") == JFileChooser.APPROVE_OPTION)
				directory.setText(fileChooser.getSelectedFile().getAbsolutePath());
		}
	}
}