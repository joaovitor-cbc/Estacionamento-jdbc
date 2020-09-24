/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author joao_vitor
 */
public class TableModelCarro extends AbstractTableModel {
    private List<Carro> dados = new ArrayList<>();
    private String[] colunas = {"Id", "Placa","Cor","Descrição"};
    
    public TableModelCarro(List<Carro> lista){
        this.dados = new ArrayList<Carro>(lista);
    }

    public TableModelCarro() {
    }

    @Override
    public String getColumnName(int coluna) {
        return colunas[coluna];
    }
    
    @Override
    public int getRowCount() {
        return dados.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch(coluna){
            case 0:
                return dados.get(linha).getId();
            case 1:
                return dados.get(linha).getPlaca();
            case 2:
                return dados.get(linha).getCor();
            case 3:
                return dados.get(linha).getDescricao();
        }
        return null;
    }

    @Override
    public void setValueAt(Object campo, int linha, int coluna) {
        switch(coluna){
            case 0:
                dados.get(linha).setId(Integer.parseInt((String) campo));
                break;
            case 1:
                dados.get(linha).setPlaca((String) campo);
                break;
            case 2:
                dados.get(linha).setCor((String) campo);
                break;
            case 3:
                dados.get(linha).setDescricao((String) campo);
                break;
        }
        this.fireTableRowsUpdated(linha, linha);
    }
    
    public void addRow(Carro carro){
        dados.add(carro);
        this.fireTableDataChanged();
    }
    
    public void removeRow(int linha){
        this.dados.remove(linha);
        this.fireTableRowsDeleted(linha, linha);
    }   
}
