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
 * Classe do modelo da tabela Musics
 *
 * @author Ígor Bonadio
 * @version %I%, %G%
 */
public class Music extends Model {
    
    /**
     * Construtor
     * 
     * @param database
     */
    public Music(Database database){
        super(database, "musics");
        this.database = database;
    }
    
    /**
     * Procura músicas por título
     * 
     * @param title título da música
     * @throws java.sql.SQLException
     */
    public void findByTitle(String title) throws SQLException{
        find("title = '"+ title+"'");
    }
    
    /**
     * Procura músicas por artista
     * 
     * @param artistId
     * @throws java.sql.SQLException
     */
    public void findByArtistId(int artistId) throws SQLException{
        find("artist_id = "+ artistId+"");
    }
    
    /**
     * Procura músicas por álbum
     * 
     * @param albumId id do album
     * @throws java.sql.SQLException
     */
    public void findByAlbumId(int albumId) throws SQLException{
        find("album_id = "+ albumId+"");
    }
    
    /**
     * Procura  músiicas por gênero
     * 
     * @param genreId id do genero
     * @throws java.sql.SQLException
     */
    public void findByGenreId(int genreId) throws SQLException{
        find("genre_id = "+ genreId+"");
    }
    
    /**
     * Retorna id da música corrente
     * 
     * @return id
     * @throws java.sql.SQLException
     */
    public int getId() throws SQLException{
        return getInt("id");
    }
    
    /**
     * Retorna título da música corrent
     * 
     * @return título
     */
    public String getTitle(){
        return title;
    }
    
    /**
     * Retorna o número da faixa da música corrente
     * 
     * @return número da faixa
     */
    public int getTrackNumber(){
        return track_number;
    }
    
    /**
     * Retorna o id do artista da música corrente
     * 
     * @return id do artista
     */
    public int getArtistId(){
        return artist_id;
    }
    
    /**
     * Retorna id do álbum da música corrente
     * 
     * @return id do álbum
     */
    public int getAlbumId(){
        return album_id;
    }
    
    /**
     * Retorna id do gênero da música corrente
     * 
     * @return id do gênero
     */
    public int getGenreId(){
        return genre_id;
    }
    
    /**
     * Atribui à música corrente, ou à uma nova música, um novo título
     * 
     * @param title título
     */
    public void setTitle(String title){
        this.title = title;
    }
    
    /**
     * Atribui à música corrente, ou à uma nova música, o número da faixa
     * 
     * @param track_number número da faixa
     */
    public void setTrackNumber(int track_number){
        this.track_number = track_number;
    }
    
    /**
     * Atribui à música corrente, ou à uma nova música, um id de artista
     * 
     * @param artist_id id do artista
     */
    public void setArtistId(int artist_id){
        this.artist_id = artist_id;
    }
    
    /**
     * Atribui à música corrente, ou à uma nova música, um id de álbum
     * 
     * @param album_id id do álbum
     */
    public void setAlbumId(int album_id){
        this.album_id = album_id;
    }
    
    /**
     * Atribui à música corrente, ou à uma nova música, um id de gênero
     * 
     * @param genre_id id do gênero
     */
    public void setGenreId(int genre_id){
        this.genre_id = genre_id;
    }
    
    /**
     * salva uma nova música
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
        if(track_number != -1){
            fields += "track_number,";
            values += track_number+",";
            ok = true;
        }
        if(artist_id != -1){
            fields += "artist_id,";
            values += artist_id+",";
            ok = true;
        }
        if(album_id != -1){
            fields += "album_id,";
            values += album_id+",";
            ok = true;
        }
        if(genre_id != -1){
            fields += "genre_id,";
            values += genre_id+",";
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
     * Faz update de uma música já existente e corrente
     * 
     * @throws java.sql.SQLException
     */
    public void update() throws SQLException{
        boolean ok = false;
        int id = getId();
        String values = "";
        if(title != null){
            values += "title='"+title+"',";
            ok = true;
        }
        if(track_number != -1 ){
            values += "track_number="+track_number+",";
            ok = true;
        }
        if(artist_id != -1){
            values += "artist_id="+artist_id+",";
            ok = true;
        }
        if(album_id != -1){
            values += "album_id="+album_id+",";
            ok = true;
        }
        if(genre_id != -1){
            values += "genre_id="+genre_id+",";
            ok = true;
        }
        if(ok){
            values = values.substring(0, values.length()-1);
            update(id, values);
        }else{
            throw new SQLException("the new music has no changes");
        }
    }
    
    /**
     * Aponta para a próxima Música
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
            track_number = getInt("track_number");
            artist_id = getInt("artist_id");
            album_id = getInt("album_id");
            genre_id = getInt("genre_id");
        }
        
        return n;
    }
    
    /**
     * Aponta para a Música anterior
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
            track_number = getInt("track_number");
            artist_id = getInt("artist_id");
            album_id = getInt("album_id");
            genre_id = getInt("genre_id");
        }
        
        return n;
    }
    
    private void discardChanges(){
        title = null;
        track_number = -1;
        artist_id = -1;
        album_id = -1;
        genre_id = -1;
    }

    private Database database;
    private String title = null;
    private int track_number = -1;
    private int artist_id = -1;
    private int album_id = -1;
    private int genre_id = -1;
}
