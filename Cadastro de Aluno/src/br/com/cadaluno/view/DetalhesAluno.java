package br.com.cadaluno.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

import br.com.cadaluno.controller.BoletimController;
import br.com.cadaluno.model.AlunoModel;
import br.com.cadaluno.model.NotasFaltasModel;

import java.awt.Dialog.ModalExclusionType;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import java.awt.Color;

public class DetalhesAluno extends JFrame {

	private JPanel contentPane;
	private JTable tbBoletim;
	JLabel lblNome;
	JLabel lblCurso;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DetalhesAluno frame = new DetalhesAluno();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public DetalhesAluno() throws Exception {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 445);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tbBoletim = new JTable();
		tbBoletim.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(128, 0, 0), new Color(220, 20, 60), null, null));
		tbBoletim.setColumnSelectionAllowed(true);
		tbBoletim.setRowSelectionAllowed(false);
		tbBoletim.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"DISCIPLINAS", "NOTAS", "FALTAS"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tbBoletim.getColumnModel().getColumn(0).setResizable(false);
		tbBoletim.getColumnModel().getColumn(0).setPreferredWidth(308);
		tbBoletim.getColumnModel().getColumn(1).setPreferredWidth(151);
		tbBoletim.getColumnModel().getColumn(2).setPreferredWidth(163);
		tbBoletim.setBounds(10, 127, 700, 307);
		contentPane.add(tbBoletim);
		
		JLabel lblDisciplinas = new JLabel("DISCIPLINAS");
		lblDisciplinas.setBounds(86, 102, 79, 14);
		contentPane.add(lblDisciplinas);
		
		JLabel lblNotas = new JLabel("NOTAS");
		lblNotas.setBounds(384, 102, 79, 14);
		contentPane.add(lblNotas);
		
		JLabel lblFaltas = new JLabel("FALTAS");
		lblFaltas.setBounds(554, 102, 79, 14);
		contentPane.add(lblFaltas);
		
		lblNome = new JLabel("NOME");
		lblNome.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(192, 192, 192), Color.LIGHT_GRAY, null, new Color(64, 64, 64)));
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNome.setBounds(62, 43, 482, 20);
		contentPane.add(lblNome);
		
		lblCurso = new JLabel("CURSO");
		lblCurso.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(192, 192, 192), new Color(128, 128, 128), null, new Color(128, 128, 128)));
		lblCurso.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCurso.setBounds(62, 74, 482, 20);
		contentPane.add(lblCurso);
		
		JLabel lblBoletimDoAluno = new JLabel("BOLETIM DO ALUNO");
		lblBoletimDoAluno.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBoletimDoAluno.setBounds(260, 11, 179, 14);
		contentPane.add(lblBoletimDoAluno);
		
		JButton btnFechar = new JButton("FECHAR");
		btnFechar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		btnFechar.setBounds(583, 52, 89, 39);
		contentPane.add(btnFechar);
		
		JLabel lblNome_1 = new JLabel("NOME:");
		lblNome_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNome_1.setBounds(10, 46, 51, 14);
		contentPane.add(lblNome_1);
		
		JLabel lblCurso_1 = new JLabel("CURSO:");
		lblCurso_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCurso_1.setBounds(10, 77, 51, 14);
		contentPane.add(lblCurso_1);
		
		// MARK : METODOS
		
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DetalhesAluno detalheAlunoView;
				try {
					detalheAlunoView = new DetalhesAluno();
					detalheAlunoView.setVisible(false);
					dispose();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
	}
	
	//MARK : METODOS FORA DO CONSTRUTOR
	
	public void recebeDados(
	List<NotasFaltasModel> lista, String nome, String curso) {
		DefaultTableModel modelo = (DefaultTableModel) tbBoletim.getModel();
		lblNome.setText(nome);
		lblCurso.setText(curso);
		
	for(NotasFaltasModel boletimAluno : lista) {
			modelo.addRow(new Object[] {
				boletimAluno.getDisciplina(),
				boletimAluno.getNota(),
				boletimAluno.getFaltas()
			});
		}
	}
}
