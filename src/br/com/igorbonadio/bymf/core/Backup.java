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
import br.com.igorbonadio.bymf.files.BymfMP3File;
import br.com.igorbonadio.bymf.files.Finder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.blinkenlights.jid3.ID3Exception;

/**
 * Classe responsável por fazer o backup da mp3.
 * 
 * @author Ígor Bonadio
 * @version %I%, %G%
 */
public class Backup extends Thread {
    
    /**
     * Contrutor
     * 
     * @param finder
     * @param database
     * @param folder
     */
    public Backup(Finder finder, Database database, String folder){
        this.database = database;
        this.finder = finder;
        this.folder = folder;
        File dir = new File(folder);
        dir.mkdir();
    }

    @Override
    public void run(){
        File file;
        String fileName;
        BymfMP3File bymfMP3File;
        int albumId;
        int artistId;
        int genreId;
        int musicId;
        
        while(finder.next()){
            file = finder.getNextFile();
            fileName = file.getName();
            if(fileName.substring(fileName.length()-4).equals(".mp3")){
                try {
                    bymfMP3File = new BymfMP3File(file);
                    
                    albumId = insertAlbum(bymfMP3File);
                    artistId = insertArtist(bymfMP3File);
                    genreId = insertGenre(bymfMP3File);
                    musicId = insertMusic(bymfMP3File,albumId,artistId,genreId);
                    
                    copyFile(file,musicId);
                    
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Backup.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Backup.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(Backup.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ID3Exception ex) {
                    Logger.getLogger(Backup.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private void copyFile(File file, int musicId) throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream(file);
        FileOutputStream fos = new FileOutputStream(folder+"/MUSIC"+musicId);
        byte[] buf = new byte[1024];
        int len;
        while ((len = fis.read(buf)) > 0) {
            fos.write(buf, 0, len);
        }
        fis.close();
        fos.close();
    }
    
    private int insertAlbum(BymfMP3File bymfMP3File) throws SQLException {
        Album album = new Album(database);
        
        int id = findAlbum(bymfMP3File);
        
        if(id == -1){
            album = new Album(database);
            album.setTitle(bymfMP3File.getAlbum());
            album.setTotalTracks(bymfMP3File.getTotalTracks());
            album.setReleasedAt(bymfMP3File.getYear());
            album.save();
            
            id = findAlbum(bymfMP3File);
        }
        
        return id;
    }
    
    private int findAlbum(BymfMP3File bymfMP3File) throws SQLException{
        Album album = new Album(database);
        int id = -1;
        album.findByTitle(bymfMP3File.getAlbum());
        while(album.next()){
            if(album.getReleasedAt() == bymfMP3File.getYear() && album.getTotalTracks() == bymfMP3File.getTotalTracks()){
                id = album.getId();
                break;
            }
        }
        return id;
    }
    
    private int insertArtist(BymfMP3File bymfMP3File) throws SQLException {
        int id = findArtist(bymfMP3File);
        
        if(id == -1){
            Artist artist = new Artist(database);
            artist.setName(bymfMP3File.getArtist());
            artist.save();
            id = findArtist(bymfMP3File);
        }
        
        return id;
    }
    
    private int findArtist(BymfMP3File bymfMP3File) throws SQLException{
        Artist artist = new Artist(database);
        int id = -1;
        artist.findByName(bymfMP3File.getArtist());
        if(artist.next()){
            id = artist.getId();
        }
        return id;
    }
    
    private int insertGenre(BymfMP3File bymfMP3File) throws SQLException {
        int id = findGenre(bymfMP3File);
        
        if(id == -1){
            Genre genre = new Genre(database);
            genre.setName(bymfMP3File.getGenre());
            genre.save();
            id = findGenre(bymfMP3File);
        }
        
        return id;
    }
    
    private int findGenre(BymfMP3File bymfMP3File) throws SQLException{
        Genre genre = new Genre(database);
        int id = -1;
        genre.findByName(bymfMP3File.getGenre());
        if(genre.next()){
            id = genre.getId();
        }
        return id;
    }
    
    private int insertMusic(BymfMP3File bymfMP3File, int albumId, int artistId, int genreId) throws SQLException {
        Music music = new Music(database);
        
        music.setAlbumId(albumId);
        music.setArtistId(artistId);
        music.setGenreId(genreId);
        music.setTitle(bymfMP3File.getTitle());
        music.setTrackNumber(bymfMP3File.getTrackNumber());
        music.save();
        
        return findMusic(bymfMP3File, albumId, artistId, genreId);
        
    }
    
    private int findMusic(BymfMP3File bymfMP3File, int albumId, int artistId, int genreId) throws SQLException{
        Music music = new Music(database);
        int id = -1;
        music.findByTitle(bymfMP3File.getTitle());
        while(music.next()){
            if(music.getArtistId() == artistId && music.getAlbumId() == albumId && music.getGenreId() == genreId && music.getTrackNumber() == bymfMP3File.getTrackNumber()){
                id = music.getId();
                break;
            }
        }
        return id;
    }
    
    private Finder finder;
    private Database database;
    private String folder;

}
