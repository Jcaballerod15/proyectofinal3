package com.mordor.mordorLloguer.vistas;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import javax.swing.SpringLayout;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JProgressBar;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VistaCargarBD extends JInternalFrame {
	private JButton btnCancelar;
	private SwingWorker<?,?> task;
	private String texto;
	private JLabel lblTextodeseado;
	private JProgressBar progressBar;

	/**
	 * Create the frame.
	 */
	public VistaCargarBD(SwingWorker<?,?> task,String texto) {
		setFrameIcon(new ImageIcon(VistaCargarBD.class.getResource("/com/mordor/mordorLloguer/recursos/autorenew24dp.png")));
		setBounds(100, 100, 450, 300);
		this.task=task;
		this.texto=texto;
		JPanel panel = new JPanel();
		
		JPanel panel_1 = new JPanel();
		
		JPanel panel_3 = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 420, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 420, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(14))
		);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		lblTextodeseado = new JLabel("");
		lblTextodeseado.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblTextodeseado);
		
		lblTextodeseado.setText(texto);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				task.cancel(true);
				dispose();
			}
		});
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGap(166)
					.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(147, Short.MAX_VALUE))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addComponent(btnCancelar, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
		);
		panel_3.setLayout(gl_panel_3);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		progressBar = new JProgressBar();
		progressBar.setIndeterminate(true);
		panel_1.add(progressBar, BorderLayout.CENTER);
		getContentPane().setLayout(groupLayout);
		
		
		
	}
}
