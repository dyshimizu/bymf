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
import junit.framework.TestCase;
import org.blinkenlights.jid3.ID3Exception;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Teste realizado utilizando JUnit 4.1
 * 
 * @author Ígor Bonadio
 */
public class BymfMP3FileTest extends TestCase {

    private BymfMediaFile bmf;
    
    public BymfMP3FileTest(String name) throws ID3Exception {
        super(name);
        bmf = new BymfMP3File(new File("libs/jid3_0.46/test_data/v2_3_0tags.mp3"));
    }

    /**
     * Test of getAlbum method, of class BymfMP3File.
     */
    @Test
    public void testGetAlbum() {
        String resp = bmf.getAlbum();
        assertEquals("Album", resp);
    }

    /**
     * Test of getArtist method, of class BymfMP3File.
     */
    @Test
    public void testGetArtist() {
        String resp = bmf.getArtist();
        assertEquals("Artist", resp);
    }

    /**
     * Test of getComment method, of class BymfMP3File.
     */
    @Test
    public void testGetComment() {
        String resp = bmf.getComment();
        assertEquals("Comment.", resp);
    }

    /**
     * Test of getGenre method, of class BymfMP3File.
     */
    @Test
    public void testGetGenre() {
        String resp = bmf.getGenre();
        assertEquals("Rock", resp);
    }

    /**
     * Test of getTitle method, of class BymfMP3File.
     */
    @Test
    public void testGetTitle() {
        String resp = bmf.getTitle();
        assertEquals("Title", resp);
    }

    /**
     * Test of getTotalTracks method, of class BymfMP3File.
     */
    @Test
    public void testGetTotalTracks() {
        int resp = bmf.getTotalTracks();
        assertEquals(10, resp);
    }

    /**
     * Test of getTrackNumber method, of class BymfMP3File.
     */
    @Test
    public void testGetTrackNumber() {
        int resp = bmf.getTrackNumber();
        assertEquals(7, resp);
    }

    /**
     * Test of getYear method, of class BymfMP3File.
     */
    @Test
    public void testGetYear() {
        int resp = bmf.getYear();
        assertEquals(1999, resp);
    }

}