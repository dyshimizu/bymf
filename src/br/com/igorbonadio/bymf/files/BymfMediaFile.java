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

/**
 * Esta interface tem como objetivo definir métodos para todas as classes
 * de arquivos de midia que o Bymf! poderá usar.
 * 
 * @author Ígor Bonadio
 * @version %I%, %G%
 */
public interface BymfMediaFile {
    
    /**
     * Quando acessado, este método deve retornar o album
     * 
     * @return nome do album
     */
    public String getAlbum();
    
    /**
     * Quando acessado, este método deve retornar o artista
     * 
     * @return nome do artista
     */
    public String getArtist();
    
    /**
     * Quando acessado, este método deve retornar o comentário contido no
     * arquivo de mídia
     * 
     * @return comentário
     */
    public String getComment();
    
    /**
     * Quando acessado, este método deve retornar o estilo correspondente ao
     * arquivo de mídia
     * 
     * @return estilo
     */
    public String getGenre();
    
    /**
     * Quando acessado, este método deve rtornar o título
     * 
     * @return  título
     */
    public String getTitle();
    
    /**
     * Quando acessado, este método deve retornar o número total de faixas
     * do disco o qual esta faixa pertence
     * 
     * @return número total de faixas
     */    
    public int getTotalTracks();
    
    /**
     * Quando acessado, este método deve retornar o número da faixa
     * 
     * @return número da faixa
     */
    public int getTrackNumber();
    
    /**
     * Quando acessado, este método deve retornar o ano correspondete ao
     * arquivo de mídia
     * 
     * @return ano
     */
    public int getYear();
    
}
