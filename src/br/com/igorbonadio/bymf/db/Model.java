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

package br.com.igorbonadio.bymf.db;

import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Modelo responsável por buscar e salvar no banco de dados.
 * 
 * @author Igor
 */
public class Model {
    
    /**
     * Construtor.
     * 
     * @param database
     * @param tableName
     */
    public Model(Database database, String tableName){
        this.database = database;
        this.tableName = tableName;
        rs = null;
    }
    
    /**
     * Busca todos as ocorrências da tabela representada pelo modelo.
     * 
     * @return <code>ture</code> se houver uma ocorrência, e <code>false</code>
     *         caso contrário.
     * @throws java.sql.SQLException
     */
    public void find() throws SQLException{
        rs = database.executeQuery("SELECT * FROM "+tableName);
    }
    
    /**
     * Busca a ocorrência cujo id é passado por parâmetro.
     * 
     * @param id id da ocorrência a ser buscada
     * @return <code>ture</code> se houver uma ocorrência, e <code>false</code>
     *         caso contrário.
     * @throws java.sql.SQLException
     */
    public void find(int id) throws SQLException{
        rs = database.executeQuery("SELECT * FROM "+tableName+" where id = "+id);
    }
    
    /**
     * Busca as ocorrências cuja condição é passada por parâmentro.
     * 
     * @param condition condição de busca.
     * @return <code>ture</code> se houver uma ocorrência, e <code>false</code>
     *         caso contrário.
     * @throws java.sql.SQLException
     */
    public void find(String condition) throws SQLException{
        rs = database.executeQuery("SELECT * FROM "+tableName+" where "+condition);
    }
    
    /**
     * Atualiza os campos de um certo resgistro identificado por id
     * 
     * @param id identificação do registro a ser atualizado
     * @param values valores a serem atualizado separados por virgula.
     *               Exemplo: "campo1 = valor1, campo2 = valor2"
     * @throws java.sql.SQLException
     */
    public void update(int id, String values) throws SQLException{
        database.executeUpdate("UPDATE "+tableName+" SET "+values+" WHERE id = "+id);
    }
    
    /**
     * Salva novo registro no banco de dados
     * 
     * @param fields campos a serem armazenados. Exemplo: campo1, campo2, campo3
     * @param values valores correspondentes ao campos já listados. Exempo: valor1, valor2, valor3
     * @throws java.sql.SQLException
     */
    public void save(String fields, String values) throws SQLException{
        database.executeUpdate("INSERT INTO "+tableName+" ("+fields+") VALUES ("+values+")");
    }
    
    /**
     * Retorna um integer da ocorrência atual, cujo nome da coluna é passado 
     * por parâmetro.
     * 
     * @param name nome da coluna na tabela
     * @return
     * @throws java.sql.SQLException
     */
    public int getInt(String name) throws SQLException{
        return rs.getInt(name);
    }
    
    /**
     * Retorna uma string da ocorrência atual, cujo nome da coluna é passado
     * por parâmetro.
     * 
     * @param name nome da coluna na tabela
     * @return
     * @throws java.sql.SQLException
     */
    public String getString(String name) throws SQLException{
        return rs.getString(name);
    }
    
    /**
     * Retorna um clob da ocorrência atual, cujo nome da coluna é passado por
     * parâmento.
     * 
     * @param name nome da coluna na tabela
     * @return
     * @throws java.sql.SQLException
     */
    public Clob getClob(String name) throws SQLException{
        return rs.getClob(name);
    }
    
    /**
     * Muda o ponteiro de ocorrências para a próxima.
     * 
     * @return <code>true</code> se houver uma próxima ocorrência, e
     *         <code>false</code> se não houver.
     * @throws java.sql.SQLException
     */
    public boolean next() throws SQLException{
        return rs.next();
    }
    
    /**
     * Muda o ponteiro de ocorrências para a anterior.
     * 
     * @return <code>true</code> se houver uma ocorrência anterior, e
     *         <code>false</code> se não houver.
     * @throws java.sql.SQLException
     */
    public boolean previous() throws SQLException{
        return rs.previous();
    }
    
    /**
     * Cola o ponteiro de ocorrências na primeira.
     * 
     * @return <code>true</code> se houver uma, e
     *         <code>false</code> se não houver.
     * @throws java.sql.SQLException
     */
    public boolean first() throws SQLException{
        return rs.first();
    }
    
    /**
     * Cola o ponteiro de ocorrências na última.
     * 
     * @return <code>true</code> se houver uma, e
     *         <code>false</code> se não houver.
     * @throws java.sql.SQLException
     */
    public boolean last() throws SQLException{
        return rs.last();
    }
    
    private Database database;
    private ResultSet rs;
    private String tableName;

}
