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
public class AlbumTest extends TestCase {

    public AlbumTest(String name) {
        super(name); 
    }

    /**
     * Test of findByTitle method, of class Album.
     */
    @Test
    public void testFindByTitle() throws Exception {
        Database database = new Database("dbTest");
        database.connect();
        database.createTables();
        database.execute("INSERT INTO albums (title,total_tracks,released_at) VALUES ('Album1',11,2001)");
        database.execute("INSERT INTO albums (title,total_tracks,released_at) VALUES ('Album2',12,2002)");
        database.execute("INSERT INTO albums (title,total_tracks,released_at) VALUES ('Album3',13,2003)");
        
        Album album = new Album(database);
        album.findByTitle("Album1");
        
        boolean next = album.next();
        assertEquals(true, next);
        
        next = album.next();
        assertEquals(false, next);
        
        database.dropTables();
        database.disconnect();
    }

    /**
     * Test of findByReleasedAt method, of class Album.
     */
    @Test
    public void testFindByReleasedAt() throws Exception {
        Database database = new Database("dbTest");
        database.connect();
        database.createTables();
        database.execute("INSERT INTO albums (title,total_tracks,released_at) VALUES ('Album1',11,2001)");
        database.execute("INSERT INTO albums (title,total_tracks,released_at) VALUES ('Album2',12,2001)");
        database.execute("INSERT INTO albums (title,total_tracks,released_at) VALUES ('Album3',13,2001)");
        
        Album album = new Album(database);
        album.findByReleasedAt(2001);
        
        boolean next = album.next();
        assertEquals(true, next);
        
        next = album.next();
        assertEquals(true, next);
        
        next = album.next();
        assertEquals(true, next);
        
        next = album.next();
        assertEquals(false, next);
        
        database.dropTables();
        database.disconnect();
    }

    /**
     * Test of getId method, of class Album.
     */
    @Test
    public void testGetId() throws Exception {
        Database database = new Database("dbTest");
        database.connect();
        database.createTables();
        database.execute("INSERT INTO albums (title,total_tracks,released_at) VALUES ('Album1',11,2001)");
        database.execute("INSERT INTO albums (title,total_tracks,released_at) VALUES ('Album2',12,2002)");
        database.execute("INSERT INTO albums (title,total_tracks,released_at) VALUES ('Album3',13,2003)");
        
        Album album = new Album(database);
        album.find();
        
        boolean next = album.next();
        assertEquals(true, next);
        assertEquals(1,album.getId());
        
        next = album.next();
        assertEquals(true, next);
        assertEquals(2,album.getId());
        
        next = album.next();
        assertEquals(true, next);
        assertEquals(3,album.getId());
        
        next = album.next();
        assertEquals(false, next);
        
        database.dropTables();
        database.disconnect();
    }

    /**
     * Test of getTitle method, of class Album.
     */
    @Test
    public void testGetTitle() throws Exception {
        Database database = new Database("dbTest");
        database.connect();
        database.createTables();
        database.execute("INSERT INTO albums (title,total_tracks,released_at) VALUES ('Album1',11,2001)");
        database.execute("INSERT INTO albums (title,total_tracks,released_at) VALUES ('Album2',12,2002)");
        database.execute("INSERT INTO albums (title,total_tracks,released_at) VALUES ('Album3',13,2003)");
        
        Album album = new Album(database);
        album.find();
        
        boolean next = album.next();
        assertEquals(true, next);
        assertEquals("Album1",album.getTitle());
        
        next = album.next();
        assertEquals(true, next);
        assertEquals("Album2",album.getTitle());
        
        next = album.next();
        assertEquals(true, next);
        assertEquals("Album3",album.getTitle());
        
        next = album.next();
        assertEquals(false, next);
        
        database.dropTables();
        database.disconnect();
    }

    /**
     * Test of getTotalTracks method, of class Album.
     */
    @Test
    public void testGetTotalTracks() throws Exception {
        Database database = new Database("dbTest");
        database.connect();
        database.createTables();
        database.execute("INSERT INTO albums (title,total_tracks,released_at) VALUES ('Album1',11,2001)");
        database.execute("INSERT INTO albums (title,total_tracks,released_at) VALUES ('Album2',12,2002)");
        database.execute("INSERT INTO albums (title,total_tracks,released_at) VALUES ('Album3',13,2003)");
        
        Album album = new Album(database);
        album.find();
        
        boolean next = album.next();
        assertEquals(true, next);
        assertEquals(11,album.getTotalTracks());
        
        next = album.next();
        assertEquals(true, next);
        assertEquals(12,album.getTotalTracks());
        
        next = album.next();
        assertEquals(true, next);
        assertEquals(13,album.getTotalTracks());
        
        next = album.next();
        assertEquals(false, next);
        
        database.dropTables();
        database.disconnect();
    }

    /**
     * Test of getReleasedAt method, of class Album.
     */
    @Test
    public void testGetReleasedAt() throws Exception {
        Database database = new Database("dbTest");
        database.connect();
        database.createTables();
        database.execute("INSERT INTO albums (title,total_tracks,released_at) VALUES ('Album1',11,2001)");
        database.execute("INSERT INTO albums (title,total_tracks,released_at) VALUES ('Album2',12,2002)");
        database.execute("INSERT INTO albums (title,total_tracks,released_at) VALUES ('Album3',13,2003)");
        
        Album album = new Album(database);
        album.find();
        
        boolean next = album.next();
        assertEquals(true, next);
        assertEquals(2001,album.getReleasedAt());
        
        next = album.next();
        assertEquals(true, next);
        assertEquals(2002,album.getReleasedAt());
        
        next = album.next();
        assertEquals(true, next);
        assertEquals(2003,album.getReleasedAt());
        
        next = album.next();
        assertEquals(false, next);
        
        database.dropTables();
        database.disconnect();
    }

    /**
     * Test of setTitle method, of class Album.
     */
    @Test
    public void testSetTitle() throws Exception {
        Database database = new Database("dbTest");
        database.connect();
        database.createTables();
        
        Album album = new Album(database);
        String title = album.getTitle();
        assertEquals(null, title);
        
        album.setTitle("Titulo");
        title = album.getTitle();
        assertEquals("Titulo", title);
        
        database.dropTables();
        database.disconnect();
    }

    /**
     * Test of setTotalTracks method, of class Album.
     */
    @Test
    public void testSetTotalTracks() throws Exception {
        Database database = new Database("dbTest");
        database.connect();
        database.createTables();
        
        Album album = new Album(database);
        int tt = album.getTotalTracks();
        assertEquals(-1, tt);
        
        album.setTotalTracks(12);
        tt = album.getTotalTracks();
        assertEquals(12, tt);
        
        database.dropTables();
        database.disconnect();
    }

    /**
     * Test of setReleasedAt method, of class Album.
     */
    @Test
    public void testSetReleasedAt() throws Exception {
        Database database = new Database("dbTest");
        database.connect();
        database.createTables();
        
        Album album = new Album(database);
        int ra = album.getReleasedAt();
        assertEquals(-1, ra);
        
        album.setReleasedAt(2001);
        ra = album.getReleasedAt();
        assertEquals(2001, ra);
        
        database.dropTables();
        database.disconnect();
    }

    /**
     * Test of save method, of class Album.
     */
    @Test
    public void testSave() throws Exception {
        Database database = new Database("dbTest");
        database.connect();
        database.createTables();
        
        Album album = new Album(database);
        album.setTitle("Album1");
        album.setTotalTracks(11);
        album.setReleasedAt(2001);
        album.save();
        
        album = new Album(database);
        album.find();
        
        boolean next = album.next();
        assertEquals(true, next);
        assertEquals("Album1",album.getTitle());
        assertEquals(11,album.getTotalTracks());
        assertEquals(2001,album.getReleasedAt());
        
        next = album.next();
        assertEquals(false, next);
        
        database.dropTables();
        database.disconnect();
    }

    /**
     * Test of update method, of class Album.
     */
    @Test
    public void testUpdate() throws Exception {
        Database database = new Database("dbTest");
        database.connect();
        database.createTables();
        
        Album album = new Album(database);
        album.setTitle("Album1");
        album.setTotalTracks(11);
        album.setReleasedAt(2001);
        album.save();
        
        album = new Album(database);
        album.find();
        
        boolean next = album.next();
        album.setTitle("Album1 Modificado");
        album.setReleasedAt(2002);
        album.setTotalTracks(12);
        album.update();
        
        album = new Album(database);
        album.find();
        next = album.next();
        assertEquals(true, next);
        assertEquals("Album1 Modificado",album.getTitle());
        assertEquals(12,album.getTotalTracks());
        assertEquals(2002,album.getReleasedAt());
        
        database.dropTables();
        database.disconnect();
    }

}