/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sfp.rubrica.application;

import com.sfp.core.domain.Rubrica;
import java.util.List;

/**
 *
 * @author manoe
 */
public class ControladorRubrica {
    private CatalogoRubrica catalogo = new CatalogoRubrica();

    public void cadastrarRubrica(Rubrica r)  
    { 
        catalogo.cadastrarRubrica(r); 
    }
    
    public void editarRubrica(Rubrica r)  
    { 
        catalogo.editarRubrica(r); 
    }
    
    public void excluirRubrica(int codigo)   
    { 
        catalogo.excluirRubrica(codigo); 
    }
    
    public List<Rubrica> listarTodasRubricas()
    { 
        return catalogo.listarTodasRubricas(); 
    }
    
    public Rubrica buscarRubricaCod(int codigo) 
    { 
        return catalogo.buscarRubricaCod(codigo); 
    }

    public boolean podeExcluir(int codigo) 
    {
        return codigo > 5; //001-005 não podem ser excluídas
    }
    
    public void desativarRubrica(int codigo) 
    {
        catalogo.desativarRubrica(codigo);
    }
    public boolean podeEditar(int codigo) 
    {
        return codigo > 5; //001-005 são somente leitura
    }    
}
