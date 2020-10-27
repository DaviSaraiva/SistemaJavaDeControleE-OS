package br.com.infox.telas;

import java.sql.*;
//importa o modulo de conexao
import br.com.infox.dal.ModuloConexao;
import java.awt.Color;
import javax.swing.JOptionPane;

public class TelaLogin extends javax.swing.JFrame {
//aq na class princiapal eu vou usar a variavel conexao

    // Concetion Para abrir uma conexão com um banco de dados, precisamos utilizar sempre um driver.invocamos o método estático getConnection com uma String que indica a qual banco desejamos nos conectar.
    //PreparedStaement-uma forma de você fazer uma inserção no banco mais segura, onde você prepara os parametros para serem inseridos.evitando assim ataques como o sql injection.   
    //ResultSet é uma interface utilizada pra guardar dados vindos de um banco de dados.
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    //exibe os resultados das instruçoes sql feitas no java 

    public void Logar() {
        // criar variavel que vai execultar o sql a pesquisa no banco o usuario e senha 
        String sql = "select * from tbusuarios where login=? and senha=? ";
        try {
            //as linhas abaixo prepararm a consulta ao banco em funçao do que foi digitado nas caixas de texto 
            // o ? é substituido pelo conteudo das variaveis 
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUsuario.getText());
            pst.setString(2, txtSenha.getText());
            //a linha abaixo executa  a query, no caso o select la em cima
            rs = pst.executeQuery();
            //se existir usuario e senha correspondente
            if (rs.next()) {
                //a linha abaixo obtem o conteudo do campo perfil da tabela tbusuarios no caso o num 6
                String perfil = rs.getString(6);
                // a estrutura a abaixo faz o tratamento do perfil
                    if (perfil.equals("admin")) {
                        TelaPrincipal principal = new TelaPrincipal();
                        principal.setVisible(true);
                        TelaPrincipal.MenRel.setEnabled(true);
                        TelaPrincipal.MenCadUsu.setEnabled(true);
                        TelaPrincipal.lblUsuario.setText(rs.getString(2));
                        //se for adm pintar de vermelho o nome, infeite so. 
                        TelaPrincipal.lblUsuario.setForeground(Color.red);
                        this.dispose();
                        //se tiver o perfil adm ele vai liberar algumas funçoes 
                    }
                    else {
                        //se nao tiver perfil adm, uma pena ne 
                        TelaPrincipal principal = new TelaPrincipal();
                        principal.setVisible(true);
                        this.dispose();
                        TelaPrincipal.lblUsuario.setText(rs.getString(2));
                    }
            } 
            else {
                JOptionPane.showMessageDialog(null, "usuario e/ou senha invalido(s)");
            }

        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "erro ao conectar");
        }

    }

    public TelaLogin() {
        initComponents();
        //aq eu vou chamar o metodo conector 
        conexao = ModuloConexao.conector();
        // se este conteudo for nulo, ouve um erro ele nao encontrou o banco de dados
        // se o conteudo  for uma string de conexao ele conseguil se conectar ao banco de dados.
        //resumindo esse pirnt ai é so pra testar 

        //serve de apoio ao status da conexao.
        // System.out.println(conexao);
        if (conexao != null) {
            lblstatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/database_connect.png")));

        } else {
            lblstatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/database_error.png")));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        btnLogin = new javax.swing.JButton();
        txtSenha = new javax.swing.JPasswordField();
        lblstatus = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("X System - Login");
        setResizable(false);

        jLabel1.setText("Usuário");

        jLabel2.setText("Senha");

        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        lblstatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/database_connect.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(66, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblstatus, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(272, 272, 272)
                        .addComponent(btnLogin))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(30, 30, 30)
                            .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(65, 65, 65))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblstatus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLogin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(93, 93, 93))
        );

        setSize(new java.awt.Dimension(516, 326));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        //chama o metodo logar
        Logar();


    }//GEN-LAST:event_btnLoginActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblstatus;
    private javax.swing.JPasswordField txtSenha;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
