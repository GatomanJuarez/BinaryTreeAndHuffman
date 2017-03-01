package Arboles;

import javax.swing.JOptionPane;

/**
 * @author By Gatoman
 */
public class Menu extends javax.swing.JFrame {

    public Menu() {
        initComponents();
    }

    class nodo {

        int dato;
        nodo der;
        nodo izq;
        //Clase de tipo nodo.

        nodo(int dat) {
            //Inicializa las variables agregando su valor predeterminado.
            this.dato = dat;
            this.der = null;
            this.izq = null;
        }
    }

    public class arbol {

        nodo raiz = null;

        //Para saber si tiene raiz.
        public boolean tieneraiz() {
            if (raiz == null) {
                return false;
            } else {
                return true;
            }
        }

        //Crea el arbol.
        public arbol alta(int dat) {
            //Crea un nuevo nodo, la raiz va a ser igual a este nodo.
            if (!tieneraiz()) {
                nodo nuevo = new nodo(dat);
                raiz = nuevo;
            } else {
                //Si no tiene raiz.
                boolean izq;
                nodo actual = raiz;
                //Crea los nodos.
                while (true) {
                    //Si el dato es menor lo manda a la izquierda.
                    if (actual.dato < dat) {
                        izq = false;
                    } else {
                        //Si es mayor lo manda a la derecha.
                        izq = true;
                    }
                    //Si tiene un valor a la izquierda.
                    if (!izq) {
                        //Lo manda a la derecha si esta vacio.
                        if (actual.der == null) {
                            //Crea un nuevo nodo.
                            nodo nuevo = new nodo(dat);
                            //Le da el valor a la derecha.
                            actual.der = nuevo;
                            break;
                        } else {
                            //Si derecha tiene algo, entonces recuperamos el valor.
                            actual = actual.der;
                        }
                        //Si izquierda tiene nada.
                    } else if (actual.izq == null) {
                        //Creamos un nuevo nodo.
                        nodo nuevo = new nodo(dat);
                        //Guardamos el valor a la izquierda.
                        actual.izq = nuevo;
                        break;
                    } else {
                        //Si la izquierda tiene algo, solo recuperra el valor.
                        actual = actual.izq;
                    }
                }
            }
            //Despues de todo regresamos los valores.
            return this;
        }

        //Clase para imprimir.
        public void imprimirpreorden() {
            //Le enviamos la raiz.
            txtArea2.setText("Impresion de PreOrden\n");
            ayudantePreorden(raiz);
        }

        public void ayudantePreorden(nodo dat) {
            //raiz, izquierda, derecha
            if (dat == null) {
                return;
            }
            //Imprime el valor.
            txtArea2.append("\t" + dat.dato + "\n");
            System.out.printf("%d ", dat.dato);
            ayudantePreorden(dat.izq);
            ayudantePreorden(dat.der);
        }

        public void impririnorden(nodo dat) {
            //Si el nodo tiene algo.
            if (dat != null) {
                //Recursividad para el valor izquierdo, lo recorre hasta el mas izquierdo.
                impririnorden(dat.izq);
                System.out.println(" " + dat.dato);
                //Recursividad para el valor derecho, lo recorre hasta el mas derecho.
                impririnorden(dat.der);
            }
        }

        ////////////////////////////////////////
        //Clase para imprimir.
        public void imprimirpost() {
            //Le enviamos la raiz.
            txtArea2.setText("Impresion de PosOrden\n");
            ayudantePost(raiz);
        }

        public void ayudantePost(nodo dat) {
            //izquierda, derecha, raiz
            if (dat == null) {
                return;
            }
            ayudantePost(dat.izq);
            ayudantePost(dat.der);
            System.out.printf("%d ", dat.dato);
            txtArea2.append("\t" + dat.dato + "\t\n");
        }

        public void impririnorden2(nodo dat) {
            if (dat != null) { //Hasta que se nos acaben los datos.
                impririnorden2(dat.izq);
                impririnorden2(dat.der);
                System.out.println(" " + dat.dato);//La raiz.
            }
        }

        ////////////////////////////////////////
        //Clase para imprimir.
        public void imprimirin() {
            //Le enviamos la raiz.
            txtArea2.setText("Impresion de InOrden\n");
            ayudanteIn(raiz);
        }

        public void ayudanteIn(nodo dat) {
            //izquierda, raiz, derecho
            if (dat == null) {
                return;
            }
            ayudanteIn(dat.izq);
            System.out.printf("%d ", dat.dato);
            ayudanteIn(dat.der);
            txtArea2.append("\t" + dat.dato + "\t\n");
        }

        public void impririnorden3(nodo dat) {
            if (dat != null) { //Hasta que se nos acaben los datos.
                impririnorden3(dat.izq);
                System.out.println(" " + dat.dato);//La raiz.
                impririnorden3(dat.der);
            }
        }
    }

    //Generamos un objeto de la clase arbol.
    arbol x = new arbol();

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtArea2 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtArea = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        txtnum = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton3.setText("PosOrden");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });

        jButton2.setText("PreOrden");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        jButton4.setText("InOrden");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });

        txtArea2.setColumns(20);
        txtArea2.setRows(5);
        jScrollPane2.setViewportView(txtArea2);

        jButton1.setText("Go");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        txtArea.setColumns(20);
        txtArea.setRows(5);
        jScrollPane1.setViewportView(txtArea);

        jLabel1.setText("Ingresa el maximo de numeros:");

        jButton5.setText("Salir.");
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtnum, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(51, 51, 51)))
                        .addGap(104, 104, 104)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton3)
                                .addGap(26, 26, 26)
                                .addComponent(jButton4)
                                .addGap(30, 30, 30))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addGap(26, 26, 26)
                                .addComponent(jButton5)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtnum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jButton5))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(jButton4))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        System.out.println("\nValores Capturados en PosOrden:");
        x.imprimirpost();
    }//GEN-LAST:event_jButton3MouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        System.out.println("Valores Capturados en PreOrden:");
        x.imprimirpreorden();
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        System.out.println("\nValores Capturados en InOrden:");
        x.imprimirin();
    }//GEN-LAST:event_jButton4MouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        int numeroMa = Integer.parseInt(txtnum.getText());
        txtArea.setText("Los numeros que ingresaste son:\n");
        int auxiliar = 0;
        for (byte o = 1; o <= numeroMa; o++) {
            auxiliar = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el dato: " + o));
            txtArea.append(auxiliar + "\n");
            x.alta(auxiliar);
        }
        txtArea2.setText("");
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseClicked
        System.exit(1);
    }//GEN-LAST:event_jButton5MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea txtArea;
    private javax.swing.JTextArea txtArea2;
    private javax.swing.JTextField txtnum;
    // End of variables declaration//GEN-END:variables
}
