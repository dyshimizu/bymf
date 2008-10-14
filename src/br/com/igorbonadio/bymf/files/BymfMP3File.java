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

package br.com.igorbonadio.bymf.files;

import java.io.File;
import org.blinkenlights.jid3.ID3Exception;
import org.blinkenlights.jid3.MP3File;
import org.blinkenlights.jid3.io.FileSource;
import org.blinkenlights.jid3.v2.ID3V2Tag;

/**
 * Classe responsável pela manipulação de arquivos MP3
 * 
 * @author Ígor Bonadio
 * @version %I%, %G%
 */
public class BymfMP3File extends MP3File implements BymfMediaFile {
    
    /**
     * 
     * @param file
     * @throws org.blinkenlights.jid3.ID3Exception
     */
    public BymfMP3File(File file) throws ID3Exception{
        super(file);
        
        init();
    }
    
    /**
     * 
     * @param file
     * @throws org.blinkenlights.jid3.ID3Exception
     */
    public BymfMP3File(FileSource file) throws ID3Exception{
        super(file);
        init();
    }

    /**
     * Retorna o album da MP3
     * 
     * @return nome do album da MP3
     */
    public String getAlbum(){
        return tag.getAlbum();
    }
    
    /**
     * Retorna o artista da MP3
     * 
     * @return nome do artista da MP3
     */
    public String getArtist() {
        return tag.getArtist();
    }

    /**
     * Retorna o comentário da MP3
     * 
     * @return comentário da MP3
     */
    public String getComment() {
        return tag.getComment();
    }

    /**
     * Retorna o estilo da MP3
     * 
     * @return estilo da MP3
     */
    public String getGenre() {
        return tag.getGenre();
    }
    
    /**
     * Retorna o títluo da MP3
     * 
     * @return título da MP3
     */
    public String getTitle() {
        return tag.getTitle();
    }

    /**
     * Retorna o numero total de faixas do disco o qual a MP3 pertence
     * 
     * @return numero total de fixas do disco o qual a MP3 pertence
     */
    public int getTotalTracks() {
        try {
            return tag.getTotalTracks();
        } catch (ID3Exception ex) {
            return -1;
        }
    }

    /**
     * Retorna o número da MP3 no disco
     * 
     * @return número da MP3
     */
    public int getTrackNumber() {
        try {
            return tag.getTrackNumber();
        } catch (ID3Exception ex) {
            return -1;
        }
    }

    /**
     * Retorna o ano da MP3
     * 
     * @return ano da MP3
     */
    public int getYear() {
        try {
            return tag.getYear();
        } catch (ID3Exception ex) {
            return -1;
        }
    }
    
    /**
     * Inicialização da classe. Este método é clamado pelos construtores da
     * classe
     * 
     * @throws org.blinkenlights.jid3.ID3Exception
     */
    private void init() throws ID3Exception{
        tag = getID3V2Tag();
    }
    
    private ID3V2Tag tag;

}
