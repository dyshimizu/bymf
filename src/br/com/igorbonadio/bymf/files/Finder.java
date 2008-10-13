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
import java.util.Stack;

/**
 * Finder é responsável por listar todos os arquívos (não diretórios)
 * recursivamente a partir de um dado diretório inicial.
 * 
 * @author  Ígor Bonadio
 * @version %I%, %G%
 */
public class Finder {
    
    /**
     * Construtor da classe que já define o diretório inicial
     * 
     * @param pathDir   Caminho do diretório inicial
     * @throws br.com.igorbonadio.bymf.files.InvalidDirectoryException
     */
    public Finder(String pathDir) throws InvalidDirectoryException{
        file = new File(pathDir);
        if(!file.isDirectory()){
            throw new InvalidDirectoryException("invalid directory!");
        }
        
        stack = new Stack();
        stack.push(file);
        
        File files[] = file.listFiles();
        int i;
        for(i=0; i<files.length; i++){
            stack.push(files[i]);
        }
        
        nextFile = file;
    }
    
    /**
     * Retorna true se existir um próximo arquivo, movendo o ponteiro para o
     * mesmo, e false se não houver mais arquivos no caminho passado.
     * 
     * @return  <code>true</code> se existir um próximo arquivo e 
     *          <code>false</code> se não existir mais arquivos no dado caminho
     * @see     Finder#getNextFile() 
     */
    public boolean next(){
        File f;
        
        f = stack.pop();
        
        while(f.isDirectory() && f != file){
            File files[] = f.listFiles();
            
            int i;
            for(i=0; i<files.length; i++){
                stack.push(files[i]);
            }
            f = stack.pop();
        }
        
        nextFile = f;
        
        if(f.isDirectory()){
            return false;
        }else{
            return true;
        }
    }
    
    /**
     * 
     * @return arquivo cujo o ponteiro aponta atualmente.
     * @see Finder#next() 
     */
    public File getNextFile(){
        return nextFile;
    }
    
    private File file;
    private File nextFile;
    private Stack<File> stack;
}
