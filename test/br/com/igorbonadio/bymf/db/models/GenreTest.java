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
import junit.framework.TestCase;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Igor
 */
public class GenreTest extends TestCase {

    public GenreTest(String name) {
        super(name);
    }

    /**
     * Test of findByName method, of class Genre.
     */
    @Test
    public void testFindByName() throws Exception {
        Database database = new Database("dbTest");
        database.connect();
        database.createTables();
        database.execute("INSERT INTO genres (name) VALUES ('Genero1')");
        database.execute("INSERT INTO genres (name) VALUES ('Genero2')");
        database.execute("INSERT INTO genres (name) VALUES ('Genero3')");
        
        Genre genre = new Genre(database);
        genre.findByName("Genero1");
        
        boolean next = genre.next();
        assertEquals(true, next);
        
        next = genre.next();
        assertEquals(false, next);
        
        database.dropTables();
        database.disconnect();
    }

    /**
     * Test of getId method, of class Genre.
     */
    @Test
    public void testGetId() throws Exception {
        Database database = new Database("dbTest");
        database.connect();
        database.createTables();
        database.execute("INSERT INTO genres (name) VALUES ('Genero1')");
        database.execute("INSERT INTO genres (name) VALUES ('Genero2')");
        database.execute("INSERT INTO genres (name) VALUES ('Genero3')");
        
        Genre genre = new Genre(database);
        genre.find();
        
        boolean next = genre.next();
        assertEquals(true, next);
        assertEquals(1, genre.getId());
        
        next = genre.next();
        assertEquals(true, next);
        assertEquals(2, genre.getId());
        
        next = genre.next();
        assertEquals(true, next);
        assertEquals(3, genre.getId());
        
        next = genre.next();
        assertEquals(false, next);
        
        database.dropTables();
        database.disconnect();
    }

    /**
     * Test of getName method, of class Genre.
     */
    @Test
    public void testGetName() throws Exception {
        Database database = new Database("dbTest");
        database.connect();
        database.createTables();
        database.execute("INSERT INTO genres (name) VALUES ('Genero1')");
        database.execute("INSERT INTO genres (name) VALUES ('Genero2')");
        database.execute("INSERT INTO genres (name) VALUES ('Genero3')");
        
        Genre genre = new Genre(database);
        genre.find();
        
        boolean next = genre.next();
        assertEquals(true, next);
        assertEquals("Genero1", genre.getName());
        
        next = genre.next();
        assertEquals(true, next);
        assertEquals("Genero2", genre.getName());
        
        next = genre.next();
        assertEquals(true, next);
        assertEquals("Genero3", genre.getName());
        
        next = genre.next();
        assertEquals(false, next);
        
        database.dropTables();
        database.disconnect();
    }

    /**
     * Test of setName method, of class Genre.
     */
    @Test
    public void testSetName() throws Exception {
        Database database = new Database("dbTest");
        database.connect();
        database.createTables();
        
        Genre genre = new Genre(database);
        String name = genre.getName();
        assertEquals(null, name);
        
        genre.setName("Genero");
        name = genre.getName();
        assertEquals("Genero", name);
        
        database.dropTables();
        database.disconnect();
    }

    /**
     * Test of save method, of class Genre.
     */
    @Test
    public void testSave() throws Exception {
        Database database = new Database("dbTest");
        database.connect();
        database.createTables();
        
        Genre genre = new Genre(database);
        genre.setName("Genero");
        genre.save();
        
        genre = new Genre(database);
        genre.find();
        
        boolean next = genre.next();
        assertEquals(true, next);
        assertEquals("Genero", genre.getName());
        
        next = genre.next();
        assertEquals(false, next);
        
        database.dropTables();
        database.disconnect();
    }

    /**
     * Test of update method, of class Genre.
     */
    @Test
    public void testUpdate() throws Exception {
        Database database = new Database("dbTest");
        database.connect();
        database.createTables();
        
        Genre genre = new Genre(database);
        genre.setName("Genero");
        genre.save();
        
        genre = new Genre(database);
        genre.find();
        
        boolean next = genre.next();
        genre.setName("Genero Modificado");
        genre.update();
        
        genre = new Genre(database);
        genre.find();
        
        next = genre.next();
        assertEquals(true, next);
        assertEquals("Genero Modificado", genre.getName());
        
        next = genre.next();
        assertEquals(false, next);
        
        database.dropTables();
        database.disconnect();
    }

}