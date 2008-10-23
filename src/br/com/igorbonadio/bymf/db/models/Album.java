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
 *
 * @author Ígor Bonadio
 * @version %I%, %G%
 */
public class Album extends Model {
    
    /**
     * Construtor
     * @param database
     */
    public Album(Database database){
        super(database, "albums");
    }
    
    /**
     * Procura albums por títlulo.
     * 
     * @param title título do album
     * @throws java.sql.SQLException
     */
    public void findByTitle(String title) throws SQLException{
        find("title = '"+ title+"'");
    }
    
    /**
     * Procura albums por data de lançamento
     * 
     * @param year ano de lançamento
     * @throws java.sql.SQLException
     */
    public void findByReleasedAt(int year) throws SQLException{
        find("released_at = "+year);
    }
    
    /**
     * Retorna id do album corrente
     * 
     * @return id
     * @throws java.sql.SQLException
     */
    public int getId() throws SQLException{
        return getInt("id");
    }
    
    /**
     * Retorna título do album corrente
     * 
     * @return título
     * @throws java.sql.SQLException
     */
    public String getTitle() throws SQLException{
        return title;
    }
    
    /**
     * Retorna número total de faixas do album corrent
     * 
     * @return número total de faixas
     * @throws java.sql.SQLException
     */
    public int getTotalTracks() throws SQLException{
        return totalTracks;
    }
    
    /**
     * Retorna data de lançamento do album corrente
     * 
     * @return ano de lançamento
     * @throws java.sql.SQLException
     */
    public int getReleasedAt() throws SQLException{
        return releasedAt;
    }
    
    /**
     * Atritui ao album corrente, ou a um novo album, um novo título
     * 
     * @param title titulo
     */
    public void setTitle(String title){
        this.title = title;
    }
    
    /**
     * Atritui ao album corrente, ou a um novo album, um novo número total de
     * faixas
     * 
     * @param totalTracks numero total de faixas
     */
    public void setTotalTracks(int totalTracks){
        this.totalTracks = totalTracks;
    }
    
    /**
     * Atritui ao album corrente, ou a um novo album, uma nova data de 
     * lançamento
     * 
     * @param releasedAt ano de lançamento
     */
    public void setReleasedAt(int releasedAt){
        this.releasedAt = releasedAt;
    }
    
    /**
     * salva um novo disco
     * 
     * @throws java.sql.SQLException
     */
    public void save() throws SQLException{
        boolean ok = false;
        String fields = "";
        String values = "";
        if(title != null){
            fields += "title,";
            values += "'"+title+"',";
            ok = true;
        }
        if(totalTracks != -1){
            fields += "total_tracks,";
            values += totalTracks+",";
            ok = true;
        }
        if(releasedAt != -1){
            fields += "released_at,";
            values += releasedAt+",";
            ok = true;
        }
        if(ok){
            fields = fields.substring(0, fields.length()-1);
            values = values.substring(0, values.length()-1);
            save(fields, values);
        }else{
            throw new SQLException("the new album is empty");
        }
    }
    
    /**
     * Faz update de um disco já existe e corrente.
     * 
     * @throws java.sql.SQLException
     */
    public void update() throws SQLException{
        int id = getId();
        boolean ok = false;
        String values = "";
        if(title != null){
            values += "title='"+title+"',";
            ok = true;
        }
        if(totalTracks != -1){
            values += "total_tracks="+totalTracks+",";
            ok = true;
        }
        if(releasedAt != -1){
            values += "released_at="+releasedAt+",";
            ok = true;
        }
        if(ok){
            values = values.substring(0, values.length()-1);
            update(id, values);
        }else{
            throw new SQLException("the new album has no changes");
        }
    }
    
    /**
     * Aponta para o próximo Album
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
            title = getString("title");
            totalTracks = getInt("total_tracks");
            releasedAt = getInt("released_at");
        }
        
        return n;
    }
    
    /**
     * Aponta para o Album anterior
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
            title = getString("title");
            totalTracks = getInt("total_tracks");
            releasedAt = getInt("released_at");
        }
        
        return n;
    }
    
    private void discardChanges(){
        title = null;
        totalTracks = -1;
        releasedAt = -1;
    }
    
    private String title = null;
    private int totalTracks = -1;
    private int releasedAt = -1;
    
}
