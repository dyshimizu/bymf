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

package br.com.igorbonadio.bymf;

import br.com.igorbonadio.bymf.files.BymfMP3File;
import br.com.igorbonadio.bymf.files.BymfMediaFile;
import br.com.igorbonadio.bymf.files.Finder;
import java.io.File;

public class main {

    public static void main(String[] args) {
        
        try {
            Finder f = new Finder("d:/testebymf/");
            File file;
            BymfMediaFile bmf;
            while(f.next()){
                file = f.getNextFile();
                bmf = new BymfMP3File(file);
                System.out.println(bmf.getArtist());
                System.out.println(bmf.getAlbum());
                System.out.println(bmf.getComment());
                System.out.println(bmf.getGenre());
                System.out.println(bmf.getTitle());
                System.out.println(bmf.getTotalTracks());
                System.out.println(bmf.getTrackNumber());
                System.out.println(bmf.getYear());
                System.out.println();
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }  
    }

}
