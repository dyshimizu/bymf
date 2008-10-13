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

import br.com.igorbonadio.bymf.files.Finder;
import br.com.igorbonadio.bymf.files.InvalidDirectoryException;

public class main {

    public static void main(String[] args) {
        
        try {
            Finder f = new Finder("d:/mp3/");
            
            int i=0;
            while(f.next()){
                System.out.println(f.getNextFile().getName());
                i++;
            }
            System.out.println(i);
            
        } catch (InvalidDirectoryException ex) {
            ex.printStackTrace();
        }  
    }

}
