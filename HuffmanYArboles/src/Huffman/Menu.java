package Huffman;

/**
 * @author By Gatoman
 */
public class Menu extends javax.swing.JFrame {

    public Menu() {
        initComponents();
    }

    public class Nodo {

        //Se crean variables nodo.
        Nodo Izq;
        Nodo Der;
        int Frec;
        char Letra;
        Nodo Sig;

        public Nodo(char l, int f) {
            //Se le da un valor a estas variables.
            Izq = null;
            Der = null;
            Sig = null;
            Frec = f;
            Letra = l;
        }
    }

    class AlgoritmoDeHuffman {

        //Se crea vector para calculo de frecuencias.
        String Codigos[] = new String[256];
        //Se crea variable de nodo.
        Nodo Primero;

        public AlgoritmoDeHuffman() {
            //Constructor de la variable Primero.
            Primero = null;
        }

        void Comprime(String Cadena) {
            //La cadena que resibimos la convertimos en un char array.
            char Letras[] = Cadena.toCharArray();
            //Creamos vector de frecuencias.
            int Frecuencia[] = new int[256];
            //Llamamos metodo para calcular las frecuencias.
            Frecuencia = GeneraFrecuenciaDeAparicion(Letras, Frecuencia);
            //Damos estructura al arbol.
            FormaNodos(Frecuencia, Letras);
            //Crea los nodos.
            Codifica(Primero, "");
            //Nos muestra el recorrido de las letras.
            MuestraCodigo(Codigos);
        }

        void Codifica(Nodo NodoActual, String Codigo) {
            //Recibe el nodo o la raiz.
            //En caso de que sea un espacio.
            if (NodoActual.Letra != '*') {
                Codigos[NodoActual.Letra] = Codigo;
            }
            //Si el nodo izquierdo tiene algo, le ponemos el codigo de recorrido, 
            //ademàs de asignarle el valor al nodo.
            if (NodoActual.Izq != null) {
                Codifica(NodoActual.Izq, Codigo += "0");
            }
            //Si el nodo derecho tiene algo, le ponemos el codigo de recorrido, 
            //ademàs de asignarle el valor al nodo.
            if (NodoActual.Der != null) {
                Codigo = Codigo.substring(0, Codigo.length() - 1);
                Codifica(NodoActual.Der, Codigo += "1");
            }
        }

        int[] GeneraFrecuenciaDeAparicion(char Letras[], int Frecuencia[]) {
            //Necesitamos el vector de las letras, y un vector que funcionara para las frecuencias.
            //Auxiliar.
            int Indice = 0;
            while (Indice < Letras.length) {
                //Guardamos la frecuencia de la letra o numero en un vector.
                Frecuencia[Letras[Indice]]++;
                //Aumentamos el indice para que siga recorriendo el vector.
                Indice++;
            }
            //Retornamos el vector con las frecuencias.
            return Frecuencia;
        }

        void FormaNodos(int Frecuencia[], char Letras[]) {
            //Recibimos el vector de frecuencia y de letras.
            int Indice = 0;
            //Recorre el ventor de las letras y/o numeros.
            while (Indice < Letras.length) {
                //Lamamos a nuestra clase para que se inserte un nuevo nodo.
                //El cual le pasamos como parametro la letra y/o numero y su frecuencia.
                InsertaNodo(Letras[Indice], Frecuencia[Letras[Indice]]);
                //Aumendamos el indice.
                Indice++;
            }
            //Lalmamos a la funcion FormaArbol esto para que haga la forma del arbol.
            FormaArbol();
        }

        void FormaArbol() {
            //Hacemos un objeto de tipo nodo.
            Nodo P = Primero;
            //Declaramos unas variables de tipo nodo.
            Nodo Sub1, Sub2;
            //Ciclo que crea los nodos con la frecuencia mas pequeña.
            //El ciclo se ejecuta hasta no encontrar mas nodos.
            //El .Sig es para que abarquemos todos los nodos.
            while (P.Sig != null) {
                //Guardamos el valor en las dos variables para los dos lados del arbol.
                Sub1 = ObtenMasChico();
                Sub2 = ObtenMasChico();
                //Despuès de esto enviamos los valores para que inserte el valor en los nodos.
                InsertaSubArbol(Sub1, Sub2);
                //Volvemos a guardar un valor a nuestro objeto.
                P = Primero;
            }
        }

        void InsertaSubArbol(Nodo Sub1, Nodo Sub2) {
            //Creamos un nuevo nodo, el cual tiene como datos los nodos con sus frecuencias.
            Nodo Nuevo = new Nodo('*', Sub1.Frec + Sub2.Frec);
            //Creamos objetos de cada lado.
            Nuevo.Izq = Sub1;
            Nuevo.Der = Sub2;
            //Esto para que continue con todos los nodos.
            Nuevo.Sig = Primero;
            //El valor de nodo sera el siguiente o el actual.
            Primero = Nuevo;
        }

        Nodo ObtenMasChico() {
            //Creamos otro objeto. Recrodemos que tiene un valor null.
            Nodo P = Primero;
            //Creamos un objeto que almacena el nodo anterior.
            Nodo Pequeño = Primero;
            //Ciclo hasta que ya no se tengan nodos.
            while (P != null) {
                //Si el nodo actual es mayor al pequeño en frecuencias.
                if (Pequeño.Frec > P.Frec) {
                    //Entonces el pequeño sera un nuevo nodo.
                    Pequeño = P;
                }
                //Si no lo es continua con los nodos.
                P = P.Sig;
            }
            //Borramos el nodo pequeño para que continue.
            BorraNodo(Pequeño);
            return Pequeño;
        }

        void BorraNodo(Nodo Pequeño) {
            //Declaramos nuestras variables para los nodos.
            Nodo Ant, Act;
            //Al principio el nodo es el actual y el anterior.
            Ant = Act = Primero;
            //Ciclo para acesar a los nodos.
            while (Act != Pequeño) {
                Ant = Act;
                Act = Act.Sig;
            }
            //Si los nodos cohiciden asignamos el primer nodo al siguiente.
            if (Pequeño == Primero) {
                Primero = Primero.Sig;
                //Si el nodo siguiente actual esta vacio.
            } else if (Act.Sig == null) {
                //El nodo anterior estara vacio.
                Ant.Sig = null;
            } else {
                //Si no se cumple el anterior sera el actual.
                Ant.Sig = Act.Sig;
            }
            //El nodo pequeño estara vacio.
            Pequeño.Sig = null;
        }

        void InsertaNodo(char Letra, int Frecuencia) {
            //Creamos un nuevo nodo que obtiene como argumentos el vector con
            //los numeros y/o letras y sus frecuencias.
            Nodo Nuevo = new Nodo(Letra, Frecuencia);
            //Si exite
            if (!Existe(Letra)) {
                //Si el nodo esta vacio, le asignara el siguiente valor del vector.
                if (Primero == null) {
                    Primero = Nuevo;
                } else {
                    //Continua con los demas nodos.
                    Nuevo.Sig = Primero;
                    Primero = Nuevo;
                }
            }
        }

        boolean Existe(char Letra) {
            //Clase para asignar los valores a los nodos.
            //Creamos un objeto.
            Nodo P = Primero;
            //Hasta que no encuentre mas valores.
            while (P != null) {
                //Si encontramos la letr.
                if (P.Letra == Letra) {
                    return true;
                }
                //Continuamos con la siguiente.
                P = P.Sig;
            }
            return false;
        }

        void MuestraCodigo(String Codigos[]) {
            //Limpia la area de texto.
            txtArea.setText("");
            //Coloca un titulo.
            txtArea.append("La codificación de las letra es:\n");
            //Ciclo para recorrer el vector y que imprima el recorrido de cada letra.
            for (int Indice = 0; Indice < Codigos.length; Indice++) {
                //Para que no respete los nodos vacios.
                if (Codigos[Indice] != null) {
                    txtArea.append("Código [" + (char) Indice + "] = " + Codigos[Indice] + "\n");
                }
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        txtfras = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtArea = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Codificar -.-!");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jLabel2.setText("By Gatoman.");

        txtArea.setColumns(20);
        txtArea.setRows(5);
        jScrollPane1.setViewportView(txtArea);

        jLabel1.setText("Ingresa lo que quieres codificar:");

        jButton2.setText("Salir.");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(txtfras, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                    .addGap(62, 62, 62)
                    .addComponent(jLabel2))
                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                    .addGap(18, 18, 18)
                    .addComponent(jLabel1)
                    .addGap(47, 47, 47)
                    .addComponent(jButton2)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtfras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        AlgoritmoDeHuffman Algoritmo = new AlgoritmoDeHuffman();
        String frase = txtfras.getText();
        Algoritmo.Comprime(frase);
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        System.exit(1);
    }//GEN-LAST:event_jButton2MouseClicked

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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtArea;
    private javax.swing.JTextField txtfras;
    // End of variables declaration//GEN-END:variables
}
