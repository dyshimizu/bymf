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

import junit.framework.TestCase;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Igor
 */
public class ModelTest extends TestCase {
    
    public ModelTest(String name) {
        super(name);
    }

    /**
     * Test of find method, of class Model.
     */
    @Test
    public void testFind_0args() throws Exception {
        Database database = new Database("dbTest");
        database.connect();
        database.createTables();
        
        Model model = new Model(database, "artists");
        model.find();
        boolean next = model.next();
        assertEquals(false,next);
        
        database.execute("INSERT INTO artists (name) VALUES ('Artista teste 1')");
        database.execute("INSERT INTO artists (name) VALUES ('Artista teste 2')");
        database.execute("INSERT INTO artists (name) VALUES ('Artista teste 3')");
        
        model.find();
        
        next = model.next();
        assertEquals(true,next);
        assertEquals("Artista teste 1", model.getString("name"));
        
        next = model.next();
        assertEquals(true,next);
        assertEquals("Artista teste 2", model.getString("name"));
        
        next = model.next();
        assertEquals(true,next);
        assertEquals("Artista teste 3", model.getString("name"));
        
        next = model.next();
        assertEquals(false,next);
        
        database.dropTables();
        database.disconnect();
    }

    /**
     * Test of find method, of class Model.
     */
    @Test
    public void testFind_int() throws Exception {
        Database database = new Database("dbTest");
        database.connect();
        database.createTables();
        
        Model model = new Model(database, "artists");
        model.find(1);
        boolean next = model.next();
        assertEquals(false,next);
        
        database.execute("INSERT INTO artists (name) VALUES ('Artista teste 1')");
        database.execute("INSERT INTO artists (name) VALUES ('Artista teste 2')");
        database.execute("INSERT INTO artists (name) VALUES ('Artista teste 3')");
        
        model.find(1);
        
        next = model.next();
        assertEquals(true,next);
        assertEquals("Artista teste 1", model.getString("name"));
        
        next = model.next();
        assertEquals(false,next);
        
        database.dropTables();
        database.disconnect();
    }

    /**
     * Test of find method, of class Model.
     */
    @Test
    public void testFind_String() throws Exception {
        Database database = new Database("dbTest");
        database.connect();
        database.createTables();
        
        Model model = new Model(database, "artists");
        model.find("name = 'Artista teste 1'");
        boolean next = model.next();
        assertEquals(false,next);
        
        database.execute("INSERT INTO artists (name) VALUES ('Artista teste 1')");
        database.execute("INSERT INTO artists (name) VALUES ('Artista teste 2')");
        database.execute("INSERT INTO artists (name) VALUES ('Artista teste 3')");
        
        model.find("name = 'Artista teste 1'");
        
        next = model.next();
        assertEquals(true,next);
        assertEquals("Artista teste 1", model.getString("name"));
        
        next = model.next();
        assertEquals(false,next);
        
        database.dropTables();
        database.disconnect();
    }

    /**
     * Test of update method, of class Model.
     */
    @Test
    public void testUpdate() throws Exception {
        Database database = new Database("dbTest");
        database.connect();
        database.createTables();
        
        Model model = new Model(database, "artists");
        model.find(1);
        boolean next = model.next();
        assertEquals(false,next);
        
        database.execute("INSERT INTO artists (name) VALUES ('Artista teste 1')");
        database.execute("INSERT INTO artists (name) VALUES ('Artista teste 2')");
        database.execute("INSERT INTO artists (name) VALUES ('Artista teste 3')");
        
        model.update(1, "name = 'Artista teste 4'");
        
        model.find(1);
        
        next = model.next();
        assertEquals(true,next);
        assertEquals("Artista teste 4", model.getString("name"));
        
        next = model.next();
        assertEquals(false,next);
        
        database.dropTables();
        database.disconnect();
    }

    /**
     * Test of save method, of class Model.
     */
    @Test
    public void testSave() throws Exception {
        Database database = new Database("dbTest");
        database.connect();
        database.createTables();
        
        Model model = new Model(database, "artists");
        model.find();
        boolean next = model.next();
        assertEquals(false,next);
        
        model.save("name", "'Artista teste 1'");
        model.save("name", "'Artista teste 2'");
        model.save("name", "'Artista teste 3'");
        
        model.find();
        
        next = model.next();
        assertEquals(true,next);
        assertEquals("Artista teste 1", model.getString("name"));
        
        next = model.next();
        assertEquals(true,next);
        assertEquals("Artista teste 2", model.getString("name"));
        
        next = model.next();
        assertEquals(true,next);
        assertEquals("Artista teste 3", model.getString("name"));
        
        next = model.next();
        assertEquals(false,next);
        
        database.dropTables();
        database.disconnect();
    }

}