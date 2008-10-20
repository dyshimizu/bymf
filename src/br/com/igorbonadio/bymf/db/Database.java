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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Database é responsável por manipular o banco de dados de músicas.
 *
 * @author Ígor Bonadio
 * @version %I%, %G%
 */
public class Database {
    
    /**
     * Construtor
     * 
     * @param   db    path do banco de dados a ser criado
     */
    public Database(String db){
        this.db = db;
    }
    
    /**
     * Conectar o banco de dados. Se o banco não existir, este será criado.
     * 
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public void connect() throws ClassNotFoundException, SQLException{
        Class.forName(driver); 
        conn = DriverManager.getConnection("jdbc:derby:"+db+";create=true");
        statement = conn.createStatement();
    }
    
    /**
     * Disconecta o banco de dados.
     * 
     * @throws java.sql.SQLException
     */
    public void disconnect() throws SQLException{
        conn.close();
    }
    
    /**
     * Cria todas as tabelas necessárias.
     * 
     * @throws java.sql.SQLException
     */
    public void createTables() throws SQLException{
        createTableAlbums();
        createTableArtists();
        createTableGenres();
        createTableMusics();
    }
    
    /**
     * Destrói todas as tabelas.
     * 
     * @throws java.sql.SQLException
     */
    public void dropTables() throws SQLException{
        dropTableAlbums();
        dropTableArtists();
        dropTableGenres();
        dropTableMusics();
    }
    
    private void createTableMusics() throws SQLException{
        String sql =    "CREATE TABLE musics ("+
                            "id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"+
                            "title VARCHAR(255) NOT NULL,"+
                            "track_number INTEGER,"+
                            "comment CLOB,"+
                            "artist_id INTEGER NOT NULL,"+
                            "album_id INTEGER NOT NULL,"+
                            "genre_id INTEGER NOT NULL,"+
                            "PRIMARY KEY (id),"+
                            "FOREIGN KEY (artist_id) REFERENCES artists(id),"+
                            "FOREIGN KEY (album_id) REFERENCES albums(id),"+
                            "FOREIGN KEY (genre_id) REFERENCES genres(id)"+
                        ")";
        execute(sql);
    }
    
    private void createTableAlbums() throws SQLException{
        String sql =    "CREATE TABLE albums ("+
                            "id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"+
                            "title VARCHAR(255) NOT NULL,"+
                            "total_tracks INTEGER,"+
                            "released_at INTEGER ,"+
                            "PRIMARY KEY (id)"+
                        ")";
        execute(sql);
    }
    
    private void createTableArtists() throws SQLException{
        String sql =    "CREATE TABLE artists ("+
                            "id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"+
                            "name VARCHAR(255) NOT NULL,"+
                            "PRIMARY KEY (id)"+
                        ")";
        execute(sql);
    }
    
    private void createTableGenres() throws SQLException{
        String sql =    "CREATE TABLE genres ("+
                            "id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"+
                            "name VARCHAR(255) NOT NULL,"+
                            "PRIMARY KEY (id)"+
                        ")";
        execute(sql);
    }
    
    private void dropTableMusics() throws SQLException{
        String sql = "DROP TABLE musics";
        execute(sql);
    }
    
    private void dropTableAlbums() throws SQLException{
        String sql = "DROP TABLE albums";
        execute(sql);
    }
    
    private void dropTableArtists() throws SQLException{
        String sql = "CREATE TABLE artists";
        execute(sql);
    }
    
    private void dropTableGenres() throws SQLException{
        String sql = "CREATE TABLE genres";
        execute(sql);
    }
    
    public void execute(String sql) throws SQLException{
        statement.execute(sql);
    }
    
    public ResultSet executeQuery(String sql) throws SQLException{
        return statement.executeQuery(sql);
    }
    
    public void executeUpdate(String sql) throws SQLException{
        statement.executeUpdate(sql);
    }
    
    private String driver = "org.apache.derby.jdbc.EmbeddedDriver"; 
    private String db;
    private Connection conn;
    private Statement statement;
    
}
