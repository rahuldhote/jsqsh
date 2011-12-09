/*
 * Copyright (C) 2007 by Scott C. Gray
 * 
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, write to the Free Software Foundation, 675 Mass Ave,
 * Cambridge, MA 02139, USA.
 */
package org.sqsh;

/**
 * "Connections" are theoretically abstract things, and this is the base
 * representation of a connection.  For most of jsqsh, a connection is a 
 * {@link SQLConnection}, however it is possible that you could have
 * other types of "connections"--to wrap jsqsh around a scripting language
 * where the current batch is "evaled" in the language, then an
 * implementation of this object would contain a handle to the scripting
 * engine.
 */
public abstract class ConnectionContext {
    
    /**
     * Evaluates a batch of SQL (or something else) on the connection.
     * 
     * @param batch The batch of text to be evaluated.
     * @param session A handle to the executing session.
     * @param renderer How the results should be rendered (if applicable to
     *   this type of connection).
     */
    public abstract void eval (String batch, Session session, SQLRenderer renderer)
        throws Exception;
    
    
    /**
     * Returns whether or not the connection wants the terminator character
     * removed from the buffer before executing it.
     * 
     * @return true or false
     */
    public boolean isTerminatorRemoved(char terminator) {
        
        return true;
    }

    /**
     * Analyzes a block of text to see if it is terminated with the provided
     * terminator character (presumably the character is a semicolon most of
     * the time). 
     * 
     * @param batch The batch to analyze
     * @param terminator The terminator character
     * @return True if the batch is terminated.
     */
    public abstract boolean isTerminated(String batch, char terminator);
    
    /**
     * Every sub-class of ConnectionContext needs to override this
     * method to return a simple, single line string describing what the
     * session is connected to.
     */
    @Override
    public String toString() {
        
        return "IMPLEMENT ME";
    }
    
    /**
     * This method will be called when the connection is to be closed.
     * Implementations should override this method if necessary.
     */
    public void close() {
        
        /* EMPTY */
    }
}
