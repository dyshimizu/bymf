/*
 * This file is part of Bymf!
 *  
 * Copyright (c) 2008, Ígor Bonadio
 * All rights reserved.
 * 
 * Bymf! is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * Bymf! is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package br.com.igorbonadio.bymf.db.models;

import br.com.igorbonadio.bymf.db.Database;
import br.com.igorbonadio.bymf.db.Model;
import java.sql.SQLException;

/**
 * Classe do modelo da tabela Artists
 *
 * @author Ígor Bonadio
 * @version %I%, %G%
 */
public class Artist extends Model {
    
    /**
     * Construtor
     * 
     * @param database
     */
    public Artist(Database database){
        super(database, "artists");
    }
    
    /**
     * Procura artistas por nome
     * 
     * @param name
     * @throws java.sql.SQLException
     */
    public void findByName(String name) throws SQLException{
        find("name = '"+ name+"'");
    }
    
    /**
     * Retorna id do artista corrente
     * 
     * @return id
     * @throws java.sql.SQLException
     */
    public int getId() throws SQLException{
        return getInt("id");
    }
    
    /**
     * Retorna nome do artista corrente
     * 
     * @return nome
     * @throws java.sql.SQLException
     */
    public String getName(){
        return name;
    }
    
    /**
     * Atritui ao artista corrente, ou a um novo artista, um novo nome
     * 
     * @param name
     */
    public void setName(String name){
        this.name = name;
    }
    
    /**
     * Salva um novo artista
     * 
     * @throws java.sql.SQLException
     */
    public void save() throws SQLException{
        String fields = "";
        String values = "";
        if(name != null){
            fields += "name";
            values += "'"+name+"'";
            save(fields, values);
        }else{
            throw new SQLException("the new artist is empty");
        }
    }
    
    /**
     * Faz update de um artista já existe e corrente.
     * 
     * @throws java.sql.SQLException
     */
    public void update() throws SQLException{
        int id = getId();
        String values = "";
        if(name != null){
            values += "name='"+name+"'";
            update(id, values);
        }else{
            throw new SQLException("the new album has no changes");
        }
    }
    
    /**
     * Aponta para o próximo artista
     * 
     * @return <code>true</code> se houver uma próxima ocorrência, e
     *         <code>false</code> se não houver.
     * @throws java.sql.SQLException
     */
    @Override
    public boolean next() throws SQLException{
        discardChanges();
        boolean n = super.next();
        if(n){
            name = getString("name");
        }
        
        return n;
    }
    
    /**
     * Aponta para o artista anterior
     * 
     * @return <code>true</code> se houver uma próxima ocorrência, e
     *         <code>false</code> se não houver.
     * @throws java.sql.SQLException
     */
    @Override
    public boolean previous() throws SQLException{
        discardChanges();
        boolean n = super.previous();
        if(n){
            name = getString("name");
        }
        
        return n;
    }
    
    private void discardChanges(){
        name = null;
    }
    
    private String name = null;

}
