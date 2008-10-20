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

import java.sql.ResultSet;
import junit.framework.TestCase;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Igor
 */
public class DatabaseTest extends TestCase {

    private Database database;
    
    public DatabaseTest(String name) {
        super(name);
        database = new Database("dbTest");
    }

    /**
     * Test of connect and disconnect method, of class Database.
     */
    @Test
    public void testConnectDisconnect() throws Exception {
        database.connect();
        database.disconnect();
    }

    /**
     * Test of createTables and dropTables method, of class Database.
     */
    @Test
    public void testCreateDropTables() throws Exception {
        database.connect();
        
        database.createTables();
        database.dropTables();
        
        database.disconnect();
    }

    /**
     * Test of execute method, of class Database.
     */
    @Test
    public void testExecute() throws Exception {
        database.connect();
        database.createTables();
        
        database.execute("INSERT INTO artists (name) VALUES ('Artista teste 1')");
        database.execute("INSERT INTO artists (name) VALUES ('Artista teste 2')");
        database.execute("INSERT INTO artists (name) VALUES ('Artista teste 3')");
        
        database.dropTables();
        database.disconnect();
    }

    /**
     * Test of executeQuery method, of class Database.
     */
    @Test
    public void testExecuteQuery() throws Exception {
        database.connect();
        database.createTables();
        database.execute("INSERT INTO artists (name) VALUES ('Artista teste 1')");
        database.execute("INSERT INTO artists (name) VALUES ('Artista teste 2')");
        database.execute("INSERT INTO artists (name) VALUES ('Artista teste 3')");
        
        ResultSet rs = database.executeQuery("SELECT * FROM artists");
        
        rs.next();
        assertEquals("Artista teste 1",rs.getString("name"));
        
        rs.next();
        assertEquals("Artista teste 2",rs.getString("name"));
        
        rs.next();
        assertEquals("Artista teste 3",rs.getString("name"));
        
        database.dropTables();
        database.disconnect();
    }

    /**
     * Test of executeUpdate method, of class Database.
     */
    @Test
    public void testExecuteUpdate() throws Exception {
        database.connect();
        database.createTables();
        database.execute("INSERT INTO artists (name) VALUES ('Artista teste 1')");
        database.execute("INSERT INTO artists (name) VALUES ('Artista teste 2')");
        database.execute("INSERT INTO artists (name) VALUES ('Artista teste 3')");
        
        database.executeUpdate("UPDATE artists SET name='Artista teste 4' WHERE name='Artista teste 1'");
        database.executeUpdate("UPDATE artists SET name='Artista teste 5' WHERE name='Artista teste 2'");
        database.executeUpdate("UPDATE artists SET name='Artista teste 6' WHERE name='Artista teste 3'");
        
        database.dropTables();
        database.disconnect();
    }

}