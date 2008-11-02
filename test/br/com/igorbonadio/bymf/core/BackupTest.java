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

package br.com.igorbonadio.bymf.core;

import br.com.igorbonadio.bymf.db.Database;
import br.com.igorbonadio.bymf.db.models.Album;
import br.com.igorbonadio.bymf.db.models.Artist;
import br.com.igorbonadio.bymf.db.models.Genre;
import br.com.igorbonadio.bymf.db.models.Music;
import br.com.igorbonadio.bymf.files.Finder;
import br.com.igorbonadio.bymf.files.InvalidDirectoryException;
import java.sql.SQLException;
import junit.framework.TestCase;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ígor Bonadio
 * @version %I%, %G%
 */
public class BackupTest extends TestCase {

    public BackupTest(String name){
        super(name);
    }

    /**
     * Test of run method, of class Backup.
     */
    @Test
    public void testRun() throws InvalidDirectoryException, ClassNotFoundException, SQLException {
        Finder finder = new Finder("test_data/mp3/");
        
        Database database = new Database("dbTest");
        database.connect();
        database.createTables();
        
        Backup backup = new Backup(finder,database,"mp3");
        
        backup.run();
        
        Music music = new Music(database);
        music.findByTitle("aaaaa");
        boolean next = music.next();
        assertEquals(true, next);
        assertEquals("aaaaa",music.getTitle());
        assertEquals(6,music.getTrackNumber());
        Album album = new Album(database);
        album.find(music.getAlbumId());
        album.next();
        assertEquals("Album",album.getTitle());
        assertEquals(1999,album.getReleasedAt());
        assertEquals(10,album.getTotalTracks());
        Artist artist = new Artist(database);
        artist.find(music.getArtistId());
        artist.next();
        assertEquals("Artist",artist.getName());
        Genre genre = new Genre(database);
        genre.find(music.getGenreId());
        genre.next();
        assertEquals("(17)Rock",genre.getName());
        
        music = new Music(database);
        music.findByTitle("bbbbb");
        next = music.next();
        assertEquals(true, next);
        assertEquals("bbbbb",music.getTitle());
        assertEquals(7,music.getTrackNumber());
        album = new Album(database);
        album.find(music.getAlbumId());
        album.next();
        assertEquals("Album",album.getTitle());
        assertEquals(1999,album.getReleasedAt());
        assertEquals(10,album.getTotalTracks());
        artist = new Artist(database);
        artist.find(music.getArtistId());
        artist.next();
        assertEquals("Artist",artist.getName());
        genre = new Genre(database);
        genre.find(music.getGenreId());
        genre.next();
        assertEquals("(17)Rock",genre.getName());
        
        music = new Music(database);
        music.findByTitle("ccccc");
        next = music.next();
        assertEquals(true, next);
        assertEquals("ccccc",music.getTitle());
        assertEquals(6,music.getTrackNumber());
        album = new Album(database);
        album.find(music.getAlbumId());
        album.next();
        assertEquals("Album2",album.getTitle());
        assertEquals(1999,album.getReleasedAt());
        assertEquals(10,album.getTotalTracks());
        artist = new Artist(database);
        artist.find(music.getArtistId());
        artist.next();
        assertEquals("Artist2",artist.getName());
        genre = new Genre(database);
        genre.find(music.getGenreId());
        genre.next();
        assertEquals("(17)Rock",genre.getName());
        
        music = new Music(database);
        music.findByTitle("ddddd");
        next = music.next();
        assertEquals(true, next);
        assertEquals("ddddd",music.getTitle());
        assertEquals(7,music.getTrackNumber());
        album = new Album(database);
        album.find(music.getAlbumId());
        album.next();
        assertEquals("Album2",album.getTitle());
        assertEquals(1999,album.getReleasedAt());
        assertEquals(10,album.getTotalTracks());
        artist = new Artist(database);
        artist.find(music.getArtistId());
        artist.next();
        assertEquals("Artist2",artist.getName());
        genre = new Genre(database);
        genre.find(music.getGenreId());
        genre.next();
        assertEquals("(17)Rock",genre.getName());
        
        database.dropTables();
        database.disconnect();
    }

}