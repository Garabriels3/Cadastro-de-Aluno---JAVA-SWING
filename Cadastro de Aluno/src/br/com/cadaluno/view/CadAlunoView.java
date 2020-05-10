package br.com.cadaluno.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import br.com.cadaluno.DI.ServiceLocator;
import br.com.cadaluno.controller.AlunoController;
import br.com.cadaluno.controller.BoletimController;
import br.com.cadaluno.controller.NotasFaltasController;
import br.com.cadaluno.model.AlunoModel;
import br.com.cadaluno.model.NotasFaltasModel;
import br.com.cadaluno.model.RemoteDataModel;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import java.awt.Cursor;
import javax.swing.JSeparator;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Method;

public class CadAlunoView extends JFrame {

	private JPanel contentPane;
	private JTextField txtRGM;
	private JTextField txtNome;
	private JTextField txtEmail;
	private JTextField txtEndereco;
	private JTextField txtMunicipio;
	private JTextField txtNotasRGM;
	private JTextField txtFaltas;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	AlunoController controller;
	NotasFaltasController controllerNotas;
	private JTable boletimTable;
	private JTextField txtBusca;
	ServiceLocator instance;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadAlunoView frame = new CadAlunoView();
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
	public CadAlunoView() throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 644, 396);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnAluno = new JMenu("Aluno");
		menuBar.add(mnAluno);
		
		JMenuItem mntmSalvar = new JMenuItem("Salvar");
		
		mnAluno.add(mntmSalvar);
		
		JMenuItem mntmAlterar = new JMenuItem("Alterar");
		
		mnAluno.add(mntmAlterar);
		
		JMenuItem mntmConsultar = new JMenuItem("Consultar");
		
		mnAluno.add(mntmConsultar);
		
		JMenuItem mntmExcluir = new JMenuItem("Excluir");
		
		mnAluno.add(mntmExcluir);
		
		JSeparator separator = new JSeparator();
		mnAluno.add(separator);
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnAluno.add(mntmSair);
		
		JMenu mnNotasEFaltas = new JMenu("Notas e Faltas");
		menuBar.add(mnNotasEFaltas);
		
		JMenuItem mntmSalvar_1 = new JMenuItem("Salvar");
		
		mnNotasEFaltas.add(mntmSalvar_1);
		
		JMenuItem mntmAlterar_1 = new JMenuItem("Alterar");
		
		mnNotasEFaltas.add(mntmAlterar_1);
		
		JMenuItem mntmConsultar_1 = new JMenuItem("Consultar");
		
		mnNotasEFaltas.add(mntmConsultar_1);
		
		JMenuItem mntmExcluir_1 = new JMenuItem("Excluir");
		
		mnNotasEFaltas.add(mntmExcluir_1);
		
		JMenu mnAjuda = new JMenu("Ajuda");
		menuBar.add(mnAjuda);
		
		JMenuItem mntmSobre = new JMenuItem("Sobre");
		mntmSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openURL("https://github.com/Garabriels3/Cadastro-de-Aluno---JAVA-SWING/tree/master");
			}
		});
		mnAjuda.add(mntmSobre);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 22, 595, 302);
		contentPane.add(tabbedPane);
		
		JPanel pnDadosPessoais = new JPanel();
		tabbedPane.addTab("Dados Pessoais", null, pnDadosPessoais, null);
		pnDadosPessoais.setLayout(null);
		
		JLabel lblRgm = new JLabel("RGM");
		lblRgm.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRgm.setBounds(10, 11, 56, 20);
		pnDadosPessoais.add(lblRgm);
		
		txtRGM = new JTextField();
		txtRGM.setBounds(59, 11, 165, 20);
		pnDadosPessoais.add(txtRGM);
		txtRGM.setColumns(10);
		
		JLabel lblDataDeNascimento = new JLabel("DATA DE NASCIMENTO");
		lblDataDeNascimento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDataDeNascimento.setBounds(10, 73, 163, 14);
		pnDadosPessoais.add(lblDataDeNascimento);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCpf.setBounds(328, 70, 56, 20);
		pnDadosPessoais.add(lblCpf);
		
		JFormattedTextField ftmDataNascimento = new JFormattedTextField(new MaskFormatter("##/##/####"));
		ftmDataNascimento.setBounds(169, 72, 135, 20);
		pnDadosPessoais.add(ftmDataNascimento);
		
		JFormattedTextField ftmCPF = new JFormattedTextField(new MaskFormatter("###.###.###.##"));
		ftmCPF.setBounds(373, 72, 205, 20);
		pnDadosPessoais.add(ftmCPF);
		
		JLabel lblNome = new JLabel("NOME");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNome.setBounds(248, 11, 56, 20);
		pnDadosPessoais.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setBounds(313, 13, 265, 20);
		pnDadosPessoais.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblEmail = new JLabel("EMAIL");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmail.setBounds(10, 109, 56, 20);
		pnDadosPessoais.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(74, 111, 504, 20);
		pnDadosPessoais.add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblEnd = new JLabel("END.");
		lblEnd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEnd.setBounds(10, 192, 56, 20);
		pnDadosPessoais.add(lblEnd);
		
		JLabel lblMunicipio = new JLabel("MUNICIPIO");
		lblMunicipio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMunicipio.setBounds(10, 243, 86, 20);
		pnDadosPessoais.add(lblMunicipio);
		
		txtEndereco = new JTextField();
		txtEndereco.setBounds(76, 194, 504, 20);
		pnDadosPessoais.add(txtEndereco);
		txtEndereco.setColumns(10);
		
		txtMunicipio = new JTextField();
		txtMunicipio.setBounds(106, 243, 142, 20);
		pnDadosPessoais.add(txtMunicipio);
		txtMunicipio.setColumns(10);
		
		JLabel lblUf = new JLabel("UF");
		lblUf.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUf.setBounds(258, 243, 30, 20);
		pnDadosPessoais.add(lblUf);
		
		JComboBox cbUF = new JComboBox();
		cbUF.setModel(new DefaultComboBoxModel(new String[] {"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"}));
		cbUF.setBounds(284, 244, 46, 22);
		pnDadosPessoais.add(cbUF);
		
		JLabel lblCelular = new JLabel("CELULAR");
		lblCelular.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCelular.setBounds(340, 243, 76, 20);
		pnDadosPessoais.add(lblCelular);
		
		JFormattedTextField ftmCelular = new JFormattedTextField(new MaskFormatter("(##)#####-####"));
		ftmCelular.setBounds(413, 245, 165, 20);
		pnDadosPessoais.add(ftmCelular);
		
		JLabel lblCep = new JLabel("CEP");
		lblCep.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCep.setBounds(10, 151, 56, 20);
		pnDadosPessoais.add(lblCep);
		
		JFormattedTextField ftmCep = new JFormattedTextField(new MaskFormatter("#####-###"));
		ftmCep.setBounds(76, 153, 228, 20);
		pnDadosPessoais.add(ftmCep);
		
		JButton btnConsultarCep = new JButton("Consultar CEP");
		
		btnConsultarCep.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnConsultarCep.setBounds(327, 142, 126, 41);
		pnDadosPessoais.add(btnConsultarCep);
		
		JPanel pnCursos = new JPanel();
		tabbedPane.addTab("Cursos", null, pnCursos, null);
		pnCursos.setLayout(null);
		
		JLabel lblCurso = new JLabel("CURSO");
		lblCurso.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCurso.setBounds(10, 30, 56, 20);
		pnCursos.add(lblCurso);
		
		JLabel lblCampos = new JLabel("CAMPUS");
		lblCampos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCampos.setBounds(10, 81, 73, 20);
		pnCursos.add(lblCampos);
		
		JLabel lblPeriodo = new JLabel("PERIODO");
		lblPeriodo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPeriodo.setBounds(10, 129, 73, 20);
		pnCursos.add(lblPeriodo);
		
		JComboBox cbCurso = new JComboBox();
		cbCurso.setModel(new DefaultComboBoxModel(new String[] {"SELECIONE SEU CURSO", "Analise e Desenvolvimento de Sistemas", "Ci\u00EAncias da Computa\u00E7\u00E3o", "Sistemas de Informa\u00E7\u00E3o"}));
		cbCurso.setBounds(136, 31, 349, 22);
		pnCursos.add(cbCurso);
		
		JComboBox cbCampus = new JComboBox();
		cbCampus.setModel(new DefaultComboBoxModel(new String[] {"SELECIONE SEU CAMPOS", "UNIDADE TATUAP\u00C9", "UNIDADE JOINVILLE", "UNIDADE BARRA FUNDA"}));
		cbCampus.setBounds(136, 82, 349, 22);
		pnCursos.add(cbCampus);
		
		JRadioButton rdMatutino = new JRadioButton("MATUTINO");
		buttonGroup.add(rdMatutino);
		rdMatutino.setBounds(138, 130, 101, 23);
		pnCursos.add(rdMatutino);
		
		JRadioButton rdVespertino = new JRadioButton("VESPERTINO");
		buttonGroup.add(rdVespertino);
		rdVespertino.setBounds(241, 130, 101, 23);
		pnCursos.add(rdVespertino);
		
		JRadioButton rdNoturno = new JRadioButton("NOTURNO");
		buttonGroup.add(rdNoturno);
		rdNoturno.setBounds(356, 130, 109, 23);
		pnCursos.add(rdNoturno);
		
		JButton btnConsultar = new JButton("CONSULTAR");
		
		btnConsultar.setBounds(123, 205, 114, 23);
		pnCursos.add(btnConsultar);
		
		JButton btnSalvar = new JButton("SALVAR");
		
		btnSalvar.setBounds(10, 205, 103, 23);
		pnCursos.add(btnSalvar);
		
		JButton btnAlterar = new JButton("ALTERAR");
		
		btnAlterar.setBounds(250, 205, 103, 23);
		pnCursos.add(btnAlterar);
		
		JButton btnExcluir = new JButton("EXCLUIR");
		
		btnExcluir.setBounds(363, 205, 89, 23);
		pnCursos.add(btnExcluir);
		
		JPanel pnNotas = new JPanel();
		tabbedPane.addTab("Notas e Faltas", null, pnNotas, null);
		pnNotas.setLayout(null);
		
		JLabel lblRgm_1 = new JLabel("RGM");
		lblRgm_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRgm_1.setBounds(10, 9, 56, 20);
		pnNotas.add(lblRgm_1);
		
		txtNotasRGM = new JTextField();
		txtNotasRGM.setBounds(60, 11, 143, 20);
		pnNotas.add(txtNotasRGM);
		txtNotasRGM.setColumns(10);
		
		JLabel lblCursoAluno = new JLabel("Curso");
		lblCursoAluno.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(105, 105, 105), null, new Color(160, 160, 160), new Color(160, 160, 160)));
		lblCursoAluno.setForeground(UIManager.getColor("Button.darkShadow"));
		lblCursoAluno.setBounds(10, 80, 570, 26);
		pnNotas.add(lblCursoAluno);
		
		JLabel lblNomeAluno = new JLabel("Nome Aluno");
		lblNomeAluno.setForeground(SystemColor.controlDkShadow);
		lblNomeAluno.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(105, 105, 105), null, new Color(160, 160, 160), new Color(160, 160, 160)));
		lblNomeAluno.setBounds(10, 40, 570, 26);
		pnNotas.add(lblNomeAluno);
		
		JLabel lblDisciplina = new JLabel("DISCIPLINA");
		lblDisciplina.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDisciplina.setBounds(10, 117, 95, 20);
		pnNotas.add(lblDisciplina);
		
		JComboBox cbDisciplina = new JComboBox();
		cbDisciplina.setModel(new DefaultComboBoxModel(new String[] {"DISCIPLINA"}));
		cbDisciplina.setBounds(102, 118, 478, 22);
		pnNotas.add(cbDisciplina);
		
		JLabel lblSemestre = new JLabel("SEMESTRE");
		lblSemestre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSemestre.setBounds(10, 164, 95, 20);
		pnNotas.add(lblSemestre);
		
		JComboBox cbSemestre = new JComboBox();
		cbSemestre.setModel(new DefaultComboBoxModel(new String[] {"SEMESTRE", "1\u00B0SEMESTRE", "2\u00B0SEMESTRE", "3\u00B0SEMESTRE", "4\u00B0SEMESTRE", "5\u00B0SEMESTRE", "6\u00B0SEMESTRE", "7\u00B0SEMESTRE", "8\u00B0SEMESTRE"}));
		cbSemestre.setBounds(91, 165, 100, 22);
		pnNotas.add(cbSemestre);
		
		JComboBox cbNotas = new JComboBox();
		cbNotas.setModel(new DefaultComboBoxModel(new String[] {"NOTA", "1.0", "2.0", "3.0", "4.0", "5.0", "6.0", "7.0", "8.0", "9.0", "10.0"}));
		cbNotas.setBounds(281, 165, 67, 22);
		pnNotas.add(cbNotas);
		
		JLabel lblNota = new JLabel("NOTA");
		lblNota.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNota.setBounds(228, 164, 49, 20);
		pnNotas.add(lblNota);
		
		JLabel lblFaltas = new JLabel("FALTAS");
		lblFaltas.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFaltas.setBounds(401, 164, 56, 20);
		pnNotas.add(lblFaltas);
		
		txtFaltas = new JTextField();
		txtFaltas.setBounds(467, 166, 86, 20);
		pnNotas.add(txtFaltas);
		txtFaltas.setColumns(10);
		
		JButton btnSalvarNotas = new JButton("Salvar");
		
		btnSalvarNotas.setBounds(29, 221, 126, 23);
		pnNotas.add(btnSalvarNotas);
		
		JButton btnConsultarNotas = new JButton("Consultar");
		
		btnConsultarNotas.setBounds(165, 221, 132, 23);
		pnNotas.add(btnConsultarNotas);
		
		JButton btnAlterarNotas = new JButton("Alterar");
		
		btnAlterarNotas.setBounds(307, 221, 120, 23);
		pnNotas.add(btnAlterarNotas);
		
		JButton btnExcluirNotas = new JButton("Excluir");
		
		btnExcluirNotas.setBounds(437, 221, 116, 23);
		pnNotas.add(btnExcluirNotas);
		
		JButton btnConsultarAluno = new JButton("Consultar Aluno");
		
		btnConsultarAluno.setBounds(213, 8, 171, 27);
		pnNotas.add(btnConsultarAluno);
		
		JPanel pnBoletim = new JPanel();
		tabbedPane.addTab("Boletim", null, pnBoletim, null);
		pnBoletim.setLayout(null);
		
		boletimTable = new JTable();
		
		boletimTable.setName("RGM");
		boletimTable.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		boletimTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		boletimTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"RGM", "NOME", "CURSO"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		boletimTable.getColumnModel().getColumn(0).setResizable(false);
		boletimTable.getColumnModel().getColumn(0).setPreferredWidth(100);
		boletimTable.getColumnModel().getColumn(0).setMaxWidth(10000);
		boletimTable.getColumnModel().getColumn(1).setResizable(false);
		boletimTable.getColumnModel().getColumn(1).setPreferredWidth(218);
		boletimTable.getColumnModel().getColumn(2).setResizable(false);
		boletimTable.getColumnModel().getColumn(2).setPreferredWidth(304);
		boletimTable.setBounds(0, 77, 590, 197);
		pnBoletim.add(boletimTable);
		
		JLabel lblRgm_2 = new JLabel("RGM");
		lblRgm_2.setBounds(25, 52, 46, 14);
		pnBoletim.add(lblRgm_2);
		
		JLabel lblNome_1 = new JLabel("NOME");
		lblNome_1.setBounds(178, 52, 46, 14);
		pnBoletim.add(lblNome_1);
		
		JLabel lblCurso_1 = new JLabel("CURSO");
		lblCurso_1.setBounds(420, 52, 46, 14);
		pnBoletim.add(lblCurso_1);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(143, 29, 1, 2);
		pnBoletim.add(separator_1);
		
		txtBusca = new JTextField();
		txtBusca.setBounds(103, 11, 207, 20);
		pnBoletim.add(txtBusca);
		txtBusca.setColumns(10);
		
		JLabel lblBuscarPorRgm = new JLabel("Buscar Por RGM");
		lblBuscarPorRgm.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblBuscarPorRgm.setBounds(0, 13, 97, 14);
		pnBoletim.add(lblBuscarPorRgm);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		
		btnPesquisar.setBounds(320, 10, 110, 23);
		pnBoletim.add(btnPesquisar);
		
		// MARK: METHODS ALUNO E CURSO
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				instance = ServiceLocator.getInstance();
				controller = (AlunoController) instance.uniqueInstance("AlunoController");
				String periodo;
				
				String RGM = txtRGM.getText();
				String nome = txtNome.getText();
				String dtNasc = ftmDataNascimento.getText();
				String CPF = ftmCPF.getText().replace(".", "").replace("-", "");
				String email = txtEmail.getText();
				String endereco = txtEndereco.getText();
				String municipio = txtMunicipio.getText();
				String uf = cbUF.getSelectedItem().toString();
				String celular = ftmCelular.getText();
				String curso = cbCurso.getSelectedItem().toString();
				String campus = cbCampus.getSelectedItem().toString();
				if(rdMatutino.isSelected()){
			         periodo = "Matutino";
				}
				else if(rdVespertino.isSelected()){
			         periodo = "Vespertino";
				}
				else {
					periodo = "Noturno";
				}
				
				try {
					controller.cadAlunoBD(RGM, nome, dtNasc, CPF, email, endereco, municipio, uf, celular, curso, campus, periodo);
					System.out.println("PASSOU DA VIEW");
					lerTabela();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			   String RGM = null;
			   RGM = txtRGM.getText();
			   System.out.println(RGM);
			   instance = ServiceLocator.getInstance();
			   controller = (AlunoController) instance.uniqueInstance("AlunoController");
				
				try {
					
					if(!RGM.isEmpty() && RGM.length() == 8) {
						AlunoModel aluno = controller.consultaDados(RGM);
					if(aluno != null){
						txtRGM.setText(aluno.getRGM());
						txtNome.setText(aluno.getNome());
						ftmDataNascimento.setText(aluno.getData_nascimento());
						ftmCPF.setText(aluno.getCPF());
						txtEmail.setText(aluno.getEmail());
						txtEndereco.setText(aluno.getEndereco());
						txtMunicipio.setText(aluno.getMunicipio());
						cbUF.setSelectedItem(aluno.getUf());
						ftmCelular.setText(aluno.getCelular());
						cbCurso.setSelectedItem(aluno.getCurso());
						cbCampus.setSelectedItem(aluno.getCampos());
						if(rdMatutino.isSelected()){
					        rdMatutino.setSelected(true);
						}
						else if(rdVespertino.isSelected()){
					         rdVespertino.setSelected(true);
						}
						else {
							rdNoturno.setSelected(true);
						}
					}else {
						JOptionPane.showMessageDialog(null, "RGM NÃO ENCONTRADO");
					}
					}} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String RGM = null;
				RGM = txtRGM.getText();
				instance = ServiceLocator.getInstance();
				controller = (AlunoController) instance.uniqueInstance("AlunoController");
				
				try {
					controller.deletarDados(RGM);
					lerTabela();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				instance = ServiceLocator.getInstance();
				controller = (AlunoController) instance.uniqueInstance("AlunoController");
				String periodo;
				
				String RGM = txtRGM.getText();
				String nome = txtNome.getText();
				String dtNasc = ftmDataNascimento.getText();
				String CPF = ftmCPF.getText();
				String email = txtEmail.getText();
				String endereco = txtEndereco.getText();
				String municipio = txtMunicipio.getText();
				String uf = cbUF.getSelectedItem().toString();
				String celular = ftmCelular.getText();
				String curso = cbCurso.getSelectedItem().toString();
				String campus = cbCampus.getSelectedItem().toString();
				if(rdMatutino.isSelected()){
			         periodo = "Matutino";
				}
				else if(rdVespertino.isSelected()){
			         periodo = "Vespertino";
				}
				else {
					periodo = "Noturno";
				}
				
				try {
					controller.alteraBD(RGM, nome, dtNasc, CPF, email, endereco, municipio, uf, celular, curso, campus, periodo);
					lerTabela();

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		// Metodo para setar informações vindas da API, nos campos de endereço da tela, através do CEP
		btnConsultarCep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RemoteDataModel data = null;
				instance = ServiceLocator.getInstance();
				controller = (AlunoController) instance.uniqueInstance("AlunoController");
				String cep = ftmCep.getText();
				data = controller.getData(cep);
				
				txtEndereco.setText(data.getLogradouro() + " - Bairro " + data.getBairro());
				txtMunicipio.setText(data.getLocalidade());
				cbUF.setSelectedItem(data.getUf());
			}
		});
		
		// MARK: METHODS NOTAS E FALTAS
		
		// Metodos para Salvar Notas E Faltas
		btnSalvarNotas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				instance = ServiceLocator.getInstance();
				controllerNotas = (NotasFaltasController) instance.uniqueInstance("NotasFaltasController");
				
				String RGM = txtNotasRGM.getText();
				String disciplina = cbDisciplina.getSelectedItem().toString();
				String semestre = cbSemestre.getSelectedItem().toString();
				String notas = cbNotas.getSelectedItem().toString();
				String faltas = txtFaltas.getText();
				
				try {
					controllerNotas.cadNotasBD(RGM, disciplina, semestre, notas, faltas);
					System.out.println("PASSOU DA VIEW");
					lerTabela();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		//Metodo para Consultar Notas e Faltas
		btnConsultarNotas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
				   String RGM = null;
				   RGM = txtNotasRGM.getText();
				   instance = ServiceLocator.getInstance();
				   controllerNotas = (NotasFaltasController) instance.uniqueInstance("NotasFaltasController");
					
				try {
						
					if(!RGM.isEmpty() && RGM.length() == 8) {
							NotasFaltasModel aluno = controllerNotas.consultaDados(RGM);
						if(aluno != null){
							lblNomeAluno.setText(aluno.getNome());
							lblCursoAluno.setText(aluno.getCurso());
							cbDisciplina.setSelectedItem(aluno.getDisciplina().isEmpty() ? "DISCIPLINA" : aluno.getDisciplina());
							cbSemestre.setSelectedItem(aluno.getSemestre().isEmpty() ? "SEMESTRE" : aluno.getSemestre());
							cbNotas.setSelectedItem(aluno.getNota().isEmpty() ? "NOTA" : aluno.getNota());
							txtFaltas.setText(aluno.getFaltas().isEmpty() ? "" : aluno.getFaltas());
						}else {
							JOptionPane.showMessageDialog(null, "RGM NÃO ENCONTRADO");
						}
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		
		// Metodo para Alterar Notas e Faltas
		btnAlterarNotas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String RGM = null;
				String disciplina = null;
				String semestre = null;
				String nota = null;
				String falta = null;
				instance = ServiceLocator.getInstance();
				controllerNotas = (NotasFaltasController) instance.uniqueInstance("NotasFaltasController");

				
				RGM = txtNotasRGM.getText();
				disciplina = cbDisciplina.getSelectedItem().toString();
				semestre = cbSemestre.getSelectedItem().toString();
				nota = cbNotas.getSelectedItem().toString();
				falta = txtFaltas.getText();
				
				try {
					controllerNotas.alteraBD(RGM, disciplina, semestre, nota, falta);
					lerTabela();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		// Metodo Excluir Notas e Faltas
		btnExcluirNotas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String RGM = null;
				RGM = txtNotasRGM.getText();
				instance = ServiceLocator.getInstance();
				controllerNotas = (NotasFaltasController) instance.uniqueInstance("NotasFaltasController");
				
				try {
					 controller.deletarDados(RGM);
						lerTabela();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		// Metodo para consultar dados da Tabela Aluno, e retorna-los, nas Labels
		btnConsultarAluno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AlunoModel aluno;
				String RGM = null;
				RGM = txtNotasRGM.getText();
				instance = ServiceLocator.getInstance();
				controller = (AlunoController) instance.uniqueInstance("AlunoController");
				
				try {
					aluno = controller.consultaDados(RGM);
					if(aluno != null) {
						lblNomeAluno.setText(aluno.getNome());
						lblCursoAluno.setText(aluno.getCurso());
						// IF's encadeados para checar o Curso do aluno vindo do BD, e setar a MODEL de Disciplinas do ComboBox, conforme o curso do aluno
						if(aluno.getCurso().contains("Analise e Desenvolvimento de Sistemas")) {
							cbDisciplina.setModel(new DefaultComboBoxModel(new String[] {"DISCIPLINA", "TECNICAS DE PROGRAMA\u00C7\u00C3O", "FUNDAMENTOS DE ESTRUTURAS DE DADOS", "APLICAÇÕES WEB", "LÓGICA PROGRAMAÇÃO", "MODELAGEM DE NEGOCIOS", "MODELAGEM DE DADOS", "ANALISE DE PROJETOS", "BANCO DE DADOS", "REDES", "ENGENHARIA DE SOFTWARE"}));
						}
							if(aluno.getCurso().contains("Ciências da Computação")) {
								cbDisciplina.setModel(new DefaultComboBoxModel(new String[] {"DISCIPLINA", "MATEMATICA", "FUNDAMENTOS DE ESTRUTURAS DE DADOS", "SISTEMAS OPERACIONAIS", "LÓGICA PROGRAMAÇÃO", "MICROPROCESSADORES", "MODELAGEM DE DADOS", "ANALISE DE PROJETOS", "BANCO DE DADOS", "CALCULO DIFERENCIAL INTEGRAL I", "FISICA PARA COMPUTAÇÃO", "ENGENHARIA DE SOFTWARE"}));
							}
								if(aluno.getCurso().contains("Sistemas de Informação")) {
									cbDisciplina.setModel(new DefaultComboBoxModel(new String[] {"DISCIPLINA", "PROJETISTA DE SISTEMAS DE INFORMAÇÃO", "ANALISTA DE SISTEMAS", "ADMINISTRADOR DE BANCO DE DADOS", "AUDITOR DE SISTEMAS DE INFORMAÇÃO", "ANALISTA DE TESTES", "COORDERNADOR DE QUALIDADE", "BANCO DE DADOS", "GERENTE DE SISTEMAS DE INFO", "ENGENHARIA DE SOFTWARE"}));
								}
					}
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		// MARK: BOTOES JBAR ALUNO
		mntmSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				instance = ServiceLocator.getInstance();
				controller = (AlunoController) instance.uniqueInstance("AlunoController");
				String periodo;
				
				String RGM = txtRGM.getText();
				String nome = txtNome.getText();
				String  dtNasc= ftmDataNascimento.getText();
				String CPF = ftmCPF.getText().replace(".", "").replace("-", "");
				String email = txtEmail.getText();
				String endereco = txtEndereco.getText();
				String municipio = txtMunicipio.getText();
				String uf = cbUF.getSelectedItem().toString();
				String celular = ftmCelular.getText();
				String curso = cbCurso.getSelectedItem().toString();
				String campus = cbCampus.getSelectedItem().toString();
				if(rdMatutino.isSelected()){
			         periodo = "Matutino";
				}
				else if(rdVespertino.isSelected()){
			         periodo = "Vespertino";
				}
				else {
					periodo = "Noturno";
				}
				
				try {
					controller.cadAlunoBD(RGM, nome, dtNasc, CPF, email, endereco, municipio, uf, celular, curso, campus, periodo);
					System.out.println("PASSOU DA VIEW");
					lerTabela();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		mntmAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					instance = ServiceLocator.getInstance();
					controller = (AlunoController) instance.uniqueInstance("AlunoController");
					String periodo;
					
					String RGM = txtRGM.getText();
					String nome = txtNome.getText();
					String dtNasc = ftmDataNascimento.getText();
					String CPF = ftmCPF.getText();
					String email = txtEmail.getText();
					String endereco = txtEndereco.getText();
					String municipio = txtMunicipio.getText();
					String uf = cbUF.getSelectedItem().toString();
					String celular = ftmCelular.getText();
					String curso = cbCurso.getSelectedItem().toString();
					String campus = cbCampus.getSelectedItem().toString();
					if(rdMatutino.isSelected()){
				         periodo = "Matutino";
					}
					else if(rdVespertino.isSelected()){
				         periodo = "Vespertino";
					}
					else {
						periodo = "Noturno";
					}
					
					try {
						controller.alteraBD(RGM, nome, dtNasc, CPF, email, endereco, municipio, uf, celular, curso, campus, periodo);
						lerTabela();

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		
		mntmConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String RGM = null;
				   RGM = txtRGM.getText();
				   System.out.println(RGM);
				   instance = ServiceLocator.getInstance();
				   controller = (AlunoController) instance.uniqueInstance("AlunoController");
					
					try {
						
						if(!RGM.isEmpty() && RGM.length() == 8) {
							AlunoModel aluno = controller.consultaDados(RGM);
						if(aluno != null){
							txtRGM.setText(aluno.getRGM());
							txtNome.setText(aluno.getNome());
							ftmDataNascimento.setText(aluno.getData_nascimento());
							ftmCPF.setText(aluno.getCPF());
							txtEmail.setText(aluno.getEmail());
							txtEndereco.setText(aluno.getEndereco());
							txtMunicipio.setText(aluno.getMunicipio());
							cbUF.setSelectedItem(aluno.getUf());
							ftmCelular.setText(aluno.getCelular());
							cbCurso.setSelectedItem(aluno.getCurso());
							cbCampus.setSelectedItem(aluno.getCampos());
							if(rdMatutino.isSelected()){
						        rdMatutino.setSelected(true);
							}
							else if(rdVespertino.isSelected()){
						         rdVespertino.setSelected(true);
							}
							else {
								rdNoturno.setSelected(true);
							}
						}else {
							JOptionPane.showMessageDialog(null, "RGM NÃO ENCONTRADO");
						}
						}} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		
		mntmExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String RGM = null;
				RGM = txtRGM.getText();

				instance = ServiceLocator.getInstance();
				controller = (AlunoController) instance.uniqueInstance("AlunoController");
				
				try {
					controller.deletarDados(RGM);
					lerTabela();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		// MARK: BOTOES JBAR NOTAS E FALTAS
		mntmSalvar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				instance = ServiceLocator.getInstance();
				controllerNotas = (NotasFaltasController) instance.uniqueInstance("NotasFaltasController");
				
				String RGM = txtNotasRGM.getText();
				String disciplina = cbDisciplina.getSelectedItem().toString();
				String semestre = cbSemestre.getSelectedItem().toString();
				String notas = cbNotas.getSelectedItem().toString();
				String faltas = txtFaltas.getText();
				
				try {
					controllerNotas.cadNotasBD(RGM, disciplina, semestre, notas, faltas);
					System.out.println("PASSOU DA VIEW");
					lerTabela();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		mntmAlterar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String RGM = null;
				String disciplina = null;
				String semestre = null;
				String nota = null;
				String falta = null;
				instance = ServiceLocator.getInstance();
				controllerNotas = (NotasFaltasController) instance.uniqueInstance("NotasFaltasController");

				
				RGM = txtNotasRGM.getText();
				disciplina = cbDisciplina.getSelectedItem().toString();
				semestre = cbSemestre.getSelectedItem().toString();
				nota = cbNotas.getSelectedItem().toString();
				falta = txtFaltas.getText();
				
				try {
					controllerNotas.alteraBD(RGM, disciplina, semestre, nota, falta);
					lerTabela();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		mntmConsultar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String RGM = null;
				   RGM = txtNotasRGM.getText();
				   instance = ServiceLocator.getInstance();
				   controllerNotas = (NotasFaltasController) instance.uniqueInstance("NotasFaltasController");
					
				try {
						
					if(!RGM.isEmpty() && RGM.length() == 8) {
							NotasFaltasModel aluno = controllerNotas.consultaDados(RGM);
						if(aluno != null){
							lblNomeAluno.setText(aluno.getNome());
							lblCursoAluno.setText(aluno.getCurso());
							cbDisciplina.setSelectedItem(aluno.getDisciplina().isEmpty() ? "DISCIPLINA" : aluno.getDisciplina());
							cbSemestre.setSelectedItem(aluno.getSemestre().isEmpty() ? "SEMESTRE" : aluno.getSemestre());
							cbNotas.setSelectedItem(aluno.getNota().isEmpty() ? "NOTA" : aluno.getNota());
							txtFaltas.setText(aluno.getFaltas().isEmpty() ? "" : aluno.getFaltas());
						}else {
							JOptionPane.showMessageDialog(null, "RGM NÃO ENCONTRADO");
						}
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		
		mntmExcluir_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String RGM = null;
				RGM = txtNotasRGM.getText();
				
				instance = ServiceLocator.getInstance();
				controllerNotas = (NotasFaltasController) instance.uniqueInstance("NotasFaltasController");
				
				try {
					controllerNotas.deletarDados(RGM);
						lerTabela();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		// MARK: METHODS BOLETIM
		
		// Pesquisa 1 aluno por RGM
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String RGM = null;
				RGM = txtBusca.getText();
				try {
					buscarAluno(RGM);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		boletimTable.addMouseListener(new MouseAdapter() {
			String RGM;
			String nome;
			String curso;
			String disciplina;
			String nota;
			String falta;

			@Override
			public void mouseClicked(MouseEvent e) {
				String alunoRGM = boletimTable.getValueAt(boletimTable.getSelectedRow(), 0).toString();
				instance = ServiceLocator.getInstance();
				BoletimController controller = (BoletimController) instance.uniqueInstance("BoletimController");
				List<NotasFaltasModel> alunoSelecionado;
				
				try {
					alunoSelecionado = controller.detalhesAlunoController(alunoRGM);
					
					if(alunoSelecionado != null) {
						for(NotasFaltasModel aluno: alunoSelecionado) {
							
								 this.RGM = aluno.getRGM();
								 this.nome = aluno.getNome();
								 this.curso = aluno.getCurso();
								 this.disciplina = aluno.getDisciplina();
								 this.nota = aluno.getNota();
								 this.falta = aluno.getFaltas();
						}
						
						DetalhesAluno detalheAlunoView = new DetalhesAluno();
						detalheAlunoView.recebeDados(alunoSelecionado, nome, curso);
						detalheAlunoView.setLocationRelativeTo(null);
						detalheAlunoView.setVisible(true);
						
					} else {
						System.out.println("Aluno sem notas lançadas ainda");
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		// METODO EXECUTADO QUANDO PROGRAMA É INICIALIZADO, PARA APRESENTAR DADOS NA TABELA
		lerTabela();
	}
	
	// MARK: METHODS DO BOLETIM, USADOS FORA DO CONSTRUTOR
	
	// Filtra JTable, buscando pelo RGM, e retorna na JTable
	public void buscarAluno(String RGM) throws Exception {
		DefaultTableModel modelo = (DefaultTableModel) boletimTable.getModel();
		instance = ServiceLocator.getInstance();
		BoletimController controller = (BoletimController) instance.uniqueInstance("BoletimController");
		modelo.setNumRows(0);
		
		for(AlunoModel aluno: controller.buscarAlunoController(RGM)) {
			
			modelo.addRow(new Object[] {
				aluno.getRGM(),
				aluno.getNome(),
				aluno.getCurso()
			});
		}
	}
	
	// Busca todos os alunos
	public void lerTabela() throws Exception {
		DefaultTableModel modelo = (DefaultTableModel) boletimTable.getModel();
		instance = ServiceLocator.getInstance();
		BoletimController controller = (BoletimController) instance.uniqueInstance("BoletimController");
		modelo.setNumRows(0);
		for(AlunoModel aluno: controller.listarAlunoController()) {
				modelo.addRow(new Object[] {
						aluno.getRGM(),
						aluno.getNome(),
						aluno.getCurso()
				});
		}
	}
	
	// Metodo para abrir através do botao "Sobre", uma pagina web(No caso, o GitHub, com o README com detalhes breves da APP)
	public static void openURL(String url) {
        String osName = System.getProperty("os.name");
        String browser = null;
        try {
            if (osName.startsWith("Mac OS")) {
            	
                Class fileMgr = Class.forName("com.apple.eio.FileManager");
                Method openURL = fileMgr.getDeclaredMethod("openURL", new Class[]{String.class});
                openURL.invoke(null, new Object[]{url});
                
            } else if (osName.startsWith("Windows")) {
            	
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
                
            } else { //assume Unix or Linux   
            	
                String[] browsers = {
                    "firefox", "opera", "konqueror", "epiphany", "mozilla", "netscape"};
                
                for (int count = 0; count < browsers.length && browser == null; count++) {
                    if (Runtime.getRuntime().exec(
                            new String[]{"which", browsers[count]}).waitFor() == 0) {
                        browser = browsers[count];
                    }
                }
                JOptionPane.showMessageDialog(null,browser);
                if (browser == null) {
                    JOptionPane.showMessageDialog(null,"Navegador não encontrado!");
                } else {
                    Runtime.getRuntime().exec(new String[]{browser,url});
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO" + ":\n" + e.getLocalizedMessage());
        }
	}
}
