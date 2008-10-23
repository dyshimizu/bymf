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
 * @author Ígor Bonadio
 * @version %I%, %G%
 */
public class ArtistTest extends TestCase {

    public ArtistTest(String name) {
        super(name); 
    }

    /**
     * Test of findByName method, of class Artist.
     */
    @Test
    public void testFindByName() throws Exception {
        Database database = new Database("dbTest");
        database.connect();
        database.createTables();
        database.execute("INSERT INTO artists (name) VALUES ('Artista1')");
        database.execute("INSERT INTO artists (name) VALUES ('Artista2')");
        database.execute("INSERT INTO artists (name) VALUES ('Artista3')");
        
        Artist artist = new Artist(database);
        artist.findByName("Artista1");
        
        boolean next = artist.next();
        assertEquals(true, next);
        
        next = artist.next();
        assertEquals(false, next);
        
        database.dropTables();
        database.disconnect();
    }

    /**
     * Test of getId method, of class Artist.
     */
    @Test
    public void testGetId() throws Exception {
        Database database = new Database("dbTest");
        database.connect();
        database.createTables();
        database.execute("INSERT INTO artists (name) VALUES ('Artista1')");
        database.execute("INSERT INTO artists (name) VALUES ('Artista2')");
        database.execute("INSERT INTO artists (name) VALUES ('Artista3')");
        
        Artist artist = new Artist(database);
        artist.find();
        
        boolean next = artist.next();
        assertEquals(true, next);
        assertEquals(1, artist.getId());
        
        next = artist.next();
        assertEquals(true, next);
        assertEquals(2, artist.getId());
        
        next = artist.next();
        assertEquals(true, next);
        assertEquals(3, artist.getId());
        
        next = artist.next();
        assertEquals(false, next);
        
        database.dropTables();
        database.disconnect();
    }

    /**
     * Test of getName method, of class Artist.
     */
    @Test
    public void testGetName() throws Exception {
        Database database = new Database("dbTest");
        database.connect();
        database.createTables();
        database.execute("INSERT INTO artists (name) VALUES ('Artista1')");
        database.execute("INSERT INTO artists (name) VALUES ('Artista2')");
        database.execute("INSERT INTO artists (name) VALUES ('Artista3')");
        
        Artist artist = new Artist(database);
        artist.find();
        
        boolean next = artist.next();
        assertEquals(true, next);
        assertEquals("Artista1", artist.getName());
        
        next = artist.next();
        assertEquals(true, next);
        assertEquals("Artista2", artist.getName());
        
        next = artist.next();
        assertEquals(true, next);
        assertEquals("Artista3", artist.getName());
        
        next = artist.next();
        assertEquals(false, next);
        
        database.dropTables();
        database.disconnect();
    }

    /**
     * Test of setName method, of class Artist.
     */
    @Test
    public void testSetName() throws Exception {
        Database database = new Database("dbTest");
        database.connect();
        database.createTables();
        
        Artist artist = new Artist(database);
        String name = artist.getName();
        assertEquals(null, name);
        
        artist.setName("Artista");
        name = artist.getName();
        assertEquals("Artista", name);
        
        database.dropTables();
        database.disconnect();
    }

    /**
     * Test of save method, of class Artist.
     */
    @Test
    public void testSave() throws Exception {
        Database database = new Database("dbTest");
        database.connect();
        database.createTables();
        
        Artist artist = new Artist(database);
        artist.setName("Artista");
        artist.save();
        
        artist = new Artist(database);
        artist.find();
        
        boolean next = artist.next();
        assertEquals(true, next);
        assertEquals("Artista", artist.getName());
        
        next = artist.next();
        assertEquals(false, next);
        
        database.dropTables();
        database.disconnect();
    }

    /**
     * Test of update method, of class Artist.
     */
    @Test
    public void testUpdate() throws Exception {
        Database database = new Database("dbTest");
        database.connect();
        database.createTables();
        
        Artist artist = new Artist(database);
        artist.setName("Artista");
        artist.save();
        
        artist = new Artist(database);
        artist.find();
        
        boolean next = artist.next();
        artist.setName("Artista Modificado");
        artist.update();
        
        artist = new Artist(database);
        artist.find();
        
        next = artist.next();
        assertEquals(true, next);
        assertEquals("Artista Modificado", artist.getName());
        
        next = artist.next();
        assertEquals(false, next);
        
        database.dropTables();
        database.disconnect();
    }

}