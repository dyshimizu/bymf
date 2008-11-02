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
public class MusicTest extends TestCase {

    public MusicTest(String name) {
        super(name); 
    }

    /**
     * Test of findByTitle method, of class Music.
     */
    @Test
    public void testFindByTitle() throws Exception {
        Database database = new Database("dbTest");
        database.connect();
        database.createTables();
        database.execute("INSERT INTO albums (title,total_tracks,released_at) VALUES ('Album1',11,2001)");
        database.execute("INSERT INTO albums (title,total_tracks,released_at) VALUES ('Album2',12,2002)");
        database.execute("INSERT INTO albums (title,total_tracks,released_at) VALUES ('Album3',13,2003)");
        database.execute("INSERT INTO artists (name) VALUES ('Artista1')");
        database.execute("INSERT INTO artists (name) VALUES ('Artista2')");
        database.execute("INSERT INTO artists (name) VALUES ('Artista3')");
        database.execute("INSERT INTO genres (name) VALUES ('Genero1')");
        database.execute("INSERT INTO genres (name) VALUES ('Genero2')");
        database.execute("INSERT INTO genres (name) VALUES ('Genero3')");
        database.execute("INSERT INTO musics (title,track_number,comment,artist_id,album_id,genre_id) VALUES ('musica1',11,'oi1',1,1,1)");
        database.execute("INSERT INTO musics (title,track_number,comment,artist_id,album_id,genre_id) VALUES ('musica2',12,'oi2',2,2,2)");
        database.execute("INSERT INTO musics (title,track_number,comment,artist_id,album_id,genre_id) VALUES ('musica3',13,'oi3',3,3,3)");
        
        Music music = new Music(database);
        music.findByTitle("musica1");
        
        boolean next = music.next();
        assertEquals(true, next);
        
        next = music.next();
        assertEquals(false, next);
        
        database.dropTables();
        database.disconnect();
    }
    
    /**
     * Test of findByArtistId method, of class Music.
     */
    @Test
    public void testFindByArtistId() throws Exception {
        Database database = new Database("dbTest");
        database.connect();
        database.createTables();
        database.execute("INSERT INTO albums (title,total_tracks,released_at) VALUES ('Album1',11,2001)");
        database.execute("INSERT INTO albums (title,total_tracks,released_at) VALUES ('Album2',12,2002)");
        database.execute("INSERT INTO albums (title,total_tracks,released_at) VALUES ('Album3',13,2003)");
        database.execute("INSERT INTO artists (name) VALUES ('Artista1')");
        database.execute("INSERT INTO genres (name) VALUES ('Genero1')");
        database.execute("INSERT INTO genres (name) VALUES ('Genero2')");
        database.execute("INSERT INTO genres (name) VALUES ('Genero3')");
        database.execute("INSERT INTO musics (title,track_number,comment,artist_id,album_id,genre_id) VALUES ('musica1',11,'oi1',1,1,1)");
        database.execute("INSERT INTO musics (title,track_number,comment,artist_id,album_id,genre_id) VALUES ('musica2',12,'oi2',1,2,2)");
        database.execute("INSERT INTO musics (title,track_number,comment,artist_id,album_id,genre_id) VALUES ('musica3',13,'oi3',1,3,3)");
        
        Music music = new Music(database);
        music.findByArtistId(1);
        
        boolean next = music.next();
        assertEquals(true, next);
        
        next = music.next();
        assertEquals(true, next);
        
        next = music.next();
        assertEquals(true, next);
        
        next = music.next();
        assertEquals(false, next);
        
        database.dropTables();
        database.disconnect();
    }
    
    /**
     * Test of findByAlbumId method, of class Music.
     */
    @Test
    public void testFindByAlbumId() throws Exception {
        Database database = new Database("dbTest");
        database.connect();
        database.createTables();
        database.execute("INSERT INTO albums (title,total_tracks,released_at) VALUES ('Album1',11,2001)");
        database.execute("INSERT INTO albums (title,total_tracks,released_at) VALUES ('Album2',12,2002)");
        database.execute("INSERT INTO artists (name) VALUES ('Artista1')");
        database.execute("INSERT INTO artists (name) VALUES ('Artista2')");
        database.execute("INSERT INTO artists (name) VALUES ('Artista3')");
        database.execute("INSERT INTO genres (name) VALUES ('Genero1')");
        database.execute("INSERT INTO genres (name) VALUES ('Genero2')");
        database.execute("INSERT INTO genres (name) VALUES ('Genero3')");
        database.execute("INSERT INTO musics (title,track_number,comment,artist_id,album_id,genre_id) VALUES ('musica1',11,'oi1',1,1,1)");
        database.execute("INSERT INTO musics (title,track_number,comment,artist_id,album_id,genre_id) VALUES ('musica2',12,'oi2',1,1,2)");
        database.execute("INSERT INTO musics (title,track_number,comment,artist_id,album_id,genre_id) VALUES ('musica3',13,'oi3',1,2,3)");
        
        Music music = new Music(database);
        music.findByAlbumId(1);
        
        boolean next = music.next();
        assertEquals(true, next);
        
        next = music.next();
        assertEquals(true, next);
        
        next = music.next();
        assertEquals(false, next);
        
        database.dropTables();
        database.disconnect();
    }
    
        /**
     * Test of findByGenreId method, of class Music.
     */
    @Test
    public void testFindByGenreId() throws Exception {
        Database database = new Database("dbTest");
        database.connect();
        database.createTables();
        database.execute("INSERT INTO albums (title,total_tracks,released_at) VALUES ('Album1',11,2001)");
        database.execute("INSERT INTO albums (title,total_tracks,released_at) VALUES ('Album2',12,2002)");
        database.execute("INSERT INTO artists (name) VALUES ('Artista1')");
        database.execute("INSERT INTO artists (name) VALUES ('Artista2')");
        database.execute("INSERT INTO artists (name) VALUES ('Artista3')");
        database.execute("INSERT INTO genres (name) VALUES ('Genero1')");
        database.execute("INSERT INTO genres (name) VALUES ('Genero2')");
        database.execute("INSERT INTO musics (title,track_number,comment,artist_id,album_id,genre_id) VALUES ('musica1',11,'oi1',1,1,1)");
        database.execute("INSERT INTO musics (title,track_number,comment,artist_id,album_id,genre_id) VALUES ('musica2',12,'oi2',1,1,2)");
        database.execute("INSERT INTO musics (title,track_number,comment,artist_id,album_id,genre_id) VALUES ('musica3',13,'oi3',1,2,1)");
        
        Music music = new Music(database);
        music.findByGenreId(1);
        
        boolean next = music.next();
        assertEquals(true, next);
        
        next = music.next();
        assertEquals(true, next);
        
        next = music.next();
        assertEquals(false, next);
        
        database.dropTables();
        database.disconnect();
    }

    /**
     * Test of getId method, of class Music.
     */
    @Test
    public void testGetId() throws Exception {
        Database database = new Database("dbTest");
        database.connect();
        database.createTables();
        database.execute("INSERT INTO albums (title,total_tracks,released_at) VALUES ('Album1',11,2001)");
        database.execute("INSERT INTO albums (title,total_tracks,released_at) VALUES ('Album2',12,2002)");
        database.execute("INSERT INTO albums (title,total_tracks,released_at) VALUES ('Album3',13,2003)");
        database.execute("INSERT INTO artists (name) VALUES ('Artista1')");
        database.execute("INSERT INTO artists (name) VALUES ('Artista2')");
        database.execute("INSERT INTO artists (name) VALUES ('Artista3')");
        database.execute("INSERT INTO genres (name) VALUES ('Genero1')");
        database.execute("INSERT INTO genres (name) VALUES ('Genero2')");
        database.execute("INSERT INTO genres (name) VALUES ('Genero3')");
        database.execute("INSERT INTO musics (title,track_number,comment,artist_id,album_id,genre_id) VALUES ('musica1',11,'oi1',1,1,1)");
        database.execute("INSERT INTO musics (title,track_number,comment,artist_id,album_id,genre_id) VALUES ('musica2',12,'oi2',2,2,2)");
        database.execute("INSERT INTO musics (title,track_number,comment,artist_id,album_id,genre_id) VALUES ('musica3',13,'oi3',3,3,3)");
        
        Music music = new Music(database);
        music.find();
        
        boolean next = music.next();
        assertEquals(true, next);
        assertEquals(1, music.getId());
        
        next = music.next();
        assertEquals(true, next);
        assertEquals(2, music.getId());
        
        next = music.next();
        assertEquals(true, next);
        assertEquals(3, music.getId());
        
        database.dropTables();
        database.disconnect();
    }

    /**
     * Test of getTitle method, of class Music.
     */
    @Test
    public void testGetTitle() throws Exception {
        Database database = new Database("dbTest");
        database.connect();
        database.createTables();
        database.execute("INSERT INTO albums (title,total_tracks,released_at) VALUES ('Album1',11,2001)");
        database.execute("INSERT INTO albums (title,total_tracks,released_at) VALUES ('Album2',12,2002)");
        database.execute("INSERT INTO albums (title,total_tracks,released_at) VALUES ('Album3',13,2003)");
        database.execute("INSERT INTO artists (name) VALUES ('Artista1')");
        database.execute("INSERT INTO artists (name) VALUES ('Artista2')");
        database.execute("INSERT INTO artists (name) VALUES ('Artista3')");
        database.execute("INSERT INTO genres (name) VALUES ('Genero1')");
        database.execute("INSERT INTO genres (name) VALUES ('Genero2')");
        database.execute("INSERT INTO genres (name) VALUES ('Genero3')");
        database.execute("INSERT INTO musics (title,track_number,comment,artist_id,album_id,genre_id) VALUES ('musica1',11,'oi1',1,1,1)");
        database.execute("INSERT INTO musics (title,track_number,comment,artist_id,album_id,genre_id) VALUES ('musica2',12,'oi2',2,2,2)");
        database.execute("INSERT INTO musics (title,track_number,comment,artist_id,album_id,genre_id) VALUES ('musica3',13,'oi3',3,3,3)");
        
        Music music = new Music(database);
        music.find();
        
         boolean next = music.next();
        assertEquals(true, next);
        assertEquals("musica1", music.getTitle());
        
        next = music.next();
        assertEquals(true, next);
        assertEquals("musica2", music.getTitle());
        
        next = music.next();
        assertEquals(true, next);
        assertEquals("musica3", music.getTitle());
        
        database.dropTables();
        database.disconnect();
    }

    /**
     * Test of getTrackNumber method, of class Music.
     */
    @Test
    public void testGetTrackNumber() throws Exception {
        Database database = new Database("dbTest");
        database.connect();
        database.createTables();
        database.execute("INSERT INTO albums (title,total_tracks,released_at) VALUES ('Album1',11,2001)");
        database.execute("INSERT INTO albums (title,total_tracks,released_at) VALUES ('Album2',12,2002)");
        database.execute("INSERT INTO albums (title,total_tracks,released_at) VALUES ('Album3',13,2003)");
        database.execute("INSERT INTO artists (name) VALUES ('Artista1')");
        database.execute("INSERT INTO artists (name) VALUES ('Artista2')");
        database.execute("INSERT INTO artists (name) VALUES ('Artista3')");
        database.execute("INSERT INTO genres (name) VALUES ('Genero1')");
        database.execute("INSERT INTO genres (name) VALUES ('Genero2')");
        database.execute("INSERT INTO genres (name) VALUES ('Genero3')");
        database.execute("INSERT INTO musics (title,track_number,comment,artist_id,album_id,genre_id) VALUES ('musica1',11,'oi1',1,1,1)");
        database.execute("INSERT INTO musics (title,track_number,comment,artist_id,album_id,genre_id) VALUES ('musica2',12,'oi2',2,2,2)");
        database.execute("INSERT INTO musics (title,track_number,comment,artist_id,album_id,genre_id) VALUES ('musica3',13,'oi3',3,3,3)");
        
        Music music = new Music(database);
        music.find();
        
         boolean next = music.next();
        assertEquals(true, next);
        assertEquals(11, music.getTrackNumber());
        
        next = music.next();
        assertEquals(true, next);
        assertEquals(12, music.getTrackNumber());
        
        next = music.next();
        assertEquals(true, next);
        assertEquals(13, music.getTrackNumber());
        
        database.dropTables();
        database.disconnect();
    }

    /**
     * Test of getArtistId method, of class Music.
     */
    @Test
    public void testGetArtistId() throws Exception {
        Database database = new Database("dbTest");
        database.connect();
        database.createTables();
        database.execute("INSERT INTO albums (title,total_tracks,released_at) VALUES ('Album1',11,2001)");
        database.execute("INSERT INTO albums (title,total_tracks,released_at) VALUES ('Album2',12,2002)");
        database.execute("INSERT INTO albums (title,total_tracks,released_at) VALUES ('Album3',13,2003)");
        database.execute("INSERT INTO artists (name) VALUES ('Artista1')");
        database.execute("INSERT INTO artists (name) VALUES ('Artista2')");
        database.execute("INSERT INTO artists (name) VALUES ('Artista3')");
        database.execute("INSERT INTO genres (name) VALUES ('Genero1')");
        database.execute("INSERT INTO genres (name) VALUES ('Genero2')");
        database.execute("INSERT INTO genres (name) VALUES ('Genero3')");
        database.execute("INSERT INTO musics (title,track_number,comment,artist_id,album_id,genre_id) VALUES ('musica1',11,'oi1',1,1,1)");
        database.execute("INSERT INTO musics (title,track_number,comment,artist_id,album_id,genre_id) VALUES ('musica2',12,'oi2',2,2,2)");
        database.execute("INSERT INTO musics (title,track_number,comment,artist_id,album_id,genre_id) VALUES ('musica3',13,'oi3',3,3,3)");
        
        Music music = new Music(database);
        music.find();
        
         boolean next = music.next();
        assertEquals(true, next);
        assertEquals(1, music.getArtistId());
        
        next = music.next();
        assertEquals(true, next);
        assertEquals(2, music.getArtistId());
        
        next = music.next();
        assertEquals(true, next);
        assertEquals(3, music.getArtistId());
        
        database.dropTables();
        database.disconnect();
    }
 
    /**
     * Test of getAlbumId method, of class Music.
     */
    @Test
    public void testGetAlbumId() throws Exception {
        Database database = new Database("dbTest");
        database.connect();
        database.createTables();
        database.execute("INSERT INTO albums (title,total_tracks,released_at) VALUES ('Album1',11,2001)");
        database.execute("INSERT INTO albums (title,total_tracks,released_at) VALUES ('Album2',12,2002)");
        database.execute("INSERT INTO albums (title,total_tracks,released_at) VALUES ('Album3',13,2003)");
        database.execute("INSERT INTO artists (name) VALUES ('Artista1')");
        database.execute("INSERT INTO artists (name) VALUES ('Artista2')");
        database.execute("INSERT INTO artists (name) VALUES ('Artista3')");
        database.execute("INSERT INTO genres (name) VALUES ('Genero1')");
        database.execute("INSERT INTO genres (name) VALUES ('Genero2')");
        database.execute("INSERT INTO genres (name) VALUES ('Genero3')");
        database.execute("INSERT INTO musics (title,track_number,comment,artist_id,album_id,genre_id) VALUES ('musica1',11,'oi1',1,1,1)");
        database.execute("INSERT INTO musics (title,track_number,comment,artist_id,album_id,genre_id) VALUES ('musica2',12,'oi2',2,2,2)");
        database.execute("INSERT INTO musics (title,track_number,comment,artist_id,album_id,genre_id) VALUES ('musica3',13,'oi3',3,3,3)");
        
        Music music = new Music(database);
        music.find();
        
         boolean next = music.next();
        assertEquals(true, next);
        assertEquals(1, music.getAlbumId());
        
        next = music.next();
        assertEquals(true, next);
        assertEquals(2, music.getAlbumId());
        
        next = music.next();
        assertEquals(true, next);
        assertEquals(3, music.getAlbumId());
        
        database.dropTables();
        database.disconnect();
    }

    /**
     * Test of getGenreId method, of class Music.
     */
    @Test
    public void testGetGenreId() throws Exception {
        Database database = new Database("dbTest");
        database.connect();
        database.createTables();
        database.execute("INSERT INTO albums (title,total_tracks,released_at) VALUES ('Album1',11,2001)");
        database.execute("INSERT INTO albums (title,total_tracks,released_at) VALUES ('Album2',12,2002)");
        database.execute("INSERT INTO albums (title,total_tracks,released_at) VALUES ('Album3',13,2003)");
        database.execute("INSERT INTO artists (name) VALUES ('Artista1')");
        database.execute("INSERT INTO artists (name) VALUES ('Artista2')");
        database.execute("INSERT INTO artists (name) VALUES ('Artista3')");
        database.execute("INSERT INTO genres (name) VALUES ('Genero1')");
        database.execute("INSERT INTO genres (name) VALUES ('Genero2')");
        database.execute("INSERT INTO genres (name) VALUES ('Genero3')");
        database.execute("INSERT INTO musics (title,track_number,comment,artist_id,album_id,genre_id) VALUES ('musica1',11,'oi1',1,1,1)");
        database.execute("INSERT INTO musics (title,track_number,comment,artist_id,album_id,genre_id) VALUES ('musica2',12,'oi2',2,2,2)");
        database.execute("INSERT INTO musics (title,track_number,comment,artist_id,album_id,genre_id) VALUES ('musica3',13,'oi3',3,3,3)");
        
        Music music = new Music(database);
        music.find();
        
         boolean next = music.next();
        assertEquals(true, next);
        assertEquals(1, music.getGenreId());
        
        next = music.next();
        assertEquals(true, next);
        assertEquals(2, music.getGenreId());
        
        next = music.next();
        assertEquals(true, next);
        assertEquals(3, music.getGenreId());
        
        database.dropTables();
        database.disconnect();
    }

    /**
     * Test of setTile method, of class Music.
     */
    @Test
    public void testSetTitle() throws Exception {
        Database database = new Database("dbTest");
        database.connect();
        database.createTables();
        
        Music music = new Music(database);
        String title = music.getTitle();
        assertEquals(null, title);
        
        music.setTitle("Titulo");
        title = music.getTitle();
        assertEquals("Titulo", title);
        
        database.dropTables();
        database.disconnect();
    }

    /**
     * Test of setTrackNumber method, of class Music.
     */
    @Test
    public void testSetTrackNumber() throws Exception {
        Database database = new Database("dbTest");
        database.connect();
        database.createTables();
        
        Music music = new Music(database);
        int track_number = music.getTrackNumber();
        assertEquals(-1, track_number);
        
        music.setTrackNumber(11);
        track_number = music.getTrackNumber();
        assertEquals(11, track_number);
        
        database.dropTables();
        database.disconnect();
    }

    /**
     * Test of setArtistId method, of class Music.
     */
    @Test
    public void testSetArtistId() throws Exception {
        Database database = new Database("dbTest");
        database.connect();
        database.createTables();
        
        Music music = new Music(database);
        int artist_id = music.getArtistId();
        assertEquals(-1, artist_id);
        
        music.setArtistId(11);
        artist_id = music.getArtistId();
        assertEquals(11, artist_id);
        
        database.dropTables();
        database.disconnect();
    }

    /**
     * Test of setAlbumId method, of class Music.
     */
    @Test
    public void testSetAlbumId() throws Exception {
        Database database = new Database("dbTest");
        database.connect();
        database.createTables();
        
        Music music = new Music(database);
        int album_id = music.getAlbumId();
        assertEquals(-1, album_id);
        
        music.setAlbumId(11);
        album_id = music.getAlbumId();
        assertEquals(11, album_id);
        
        database.dropTables();
        database.disconnect();
    }

    /**
     * Test of setGenreId method, of class Music.
     */
    @Test
    public void testSetGenreId() throws Exception {
        Database database = new Database("dbTest");
        database.connect();
        database.createTables();
        
        Music music = new Music(database);
        int genre_id = music.getGenreId();
        assertEquals(-1, genre_id);
        
        music.setGenreId(11);
        genre_id = music.getGenreId();
        assertEquals(11, genre_id);
        
        database.dropTables();
        database.disconnect();
    }

    /**
     * Test of save method, of class Music.
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
        
        Artist artist = new Artist(database);
        artist.setName("Artista");
        artist.save();
        
        Genre genre = new Genre(database);
        genre.setName("Genero");
        genre.save();
        
        Music music = new Music(database);
        music.setTitle("Musica");
        music.setTrackNumber(11);
        music.setArtistId(1);
        music.setAlbumId(1);
        music.setGenreId(1);
        music.save();
        
        music = new Music(database);
        music.find();
        
        boolean next = music.next();
        assertEquals(true, next);
        assertEquals("Musica",music.getTitle());
        assertEquals(11,music.getTrackNumber());
        assertEquals(1,music.getArtistId());
        assertEquals(1,music.getAlbumId());
        assertEquals(1,music.getGenreId());
        
        next = music.next();
        assertEquals(false, next);
        
        database.dropTables();
        database.disconnect();
    }

    /**
     * Test of update method, of class Music.
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
        
        Artist artist = new Artist(database);
        artist.setName("Artista");
        artist.save();
        
        Genre genre = new Genre(database);
        genre.setName("Genero");
        genre.save();
        
        Music music = new Music(database);
        music.setTitle("Musica1");
        music.setTrackNumber(10);
        music.setArtistId(1);
        music.setAlbumId(1);
        music.setGenreId(1);
        music.save();
        
        music = new Music(database);
        music.find();
        
        boolean next = music.next();
        assertEquals(true, next);
        music.setTitle("Musica");
        music.setTrackNumber(11);
        music.update();
        
        music = new Music(database);
        music.find();
        
        next = music.next();
        assertEquals(true, next);
        assertEquals("Musica",music.getTitle());
        assertEquals(11,music.getTrackNumber());
        assertEquals(1,music.getArtistId());
        assertEquals(1,music.getAlbumId());
        assertEquals(1,music.getGenreId());
        
        next = music.next();
        assertEquals(false, next);
        
        database.dropTables();
        database.disconnect();
    }

}