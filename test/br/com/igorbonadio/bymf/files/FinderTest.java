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

import junit.framework.TestCase;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Teste realizado utilizando JUnit 4.1
 * 
 * @author Ígor Bonadio
 */
public class FinderTest extends TestCase {

    private Finder finder;
    
    public FinderTest(String name) throws InvalidDirectoryException {
        super(name);
        finder = new Finder("libs/jid3_0.46/test_data/");
    }

    /**
     * Test of next method, of class Finder.
     */
    @Test
    public void testNext() {
        for(int i=1; i<10; i++){
            switch(i){
                case 1:     assertEquals(true,finder.next());
                            break;
                case 2:     assertEquals(true,finder.next());
                            break;
                case 3:     assertEquals(true,finder.next());
                            break;
                case 4:     assertEquals(true,finder.next());
                            break;
                default:    assertEquals(false,finder.next());
                            break;
            }
        }
    }

    /**
     * Test of getNextFile method, of class Finder.
     */
    @Test
    public void testGetNextFile() {
        int i = 0;
        while(finder.next()){
            i++;
            switch(i){
                case 1:     assertEquals("v2_3_0tags.mp3",finder.getNextFile().getName());
                            break;
                case 2:     assertEquals("v1_1tags.mp3",finder.getNextFile().getName());
                            break;
                case 3:     assertEquals("v1_0tags.mp3",finder.getNextFile().getName());
                            break;
                case 4:     assertEquals("notags.mp3",finder.getNextFile().getName());
                            break;
                default:    assertEquals("","erro");
                            break;
            }
        }
    }

}