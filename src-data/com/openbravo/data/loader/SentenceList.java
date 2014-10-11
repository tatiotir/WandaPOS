//    Wanda POS - Africa's Gift to the World
//    Copyright (c) 2014-2015 IT-Kamer & previous Unicenta POS and Openbravo POS works
//    www.erp-university-africa.com
//
//    This file is part of Wanda POS
//
//    Wanda POS is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//   Wanda POS is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with Wanda POS.  If not, see <http://www.gnu.org/licenses/>.

package com.openbravo.data.loader;

import com.openbravo.basic.BasicException;
import java.util.List;

/**
 *
 * @author JG uniCenta
 */
public interface SentenceList {
    
    /**
     *
     * @return
     * @throws BasicException
     */
    public List list() throws BasicException;

    /**
     *
     * @param params
     * @return
     * @throws BasicException
     */
    public List list(Object... params) throws BasicException;

    /**
     *
     * @param params
     * @return
     * @throws BasicException
     */
    public List list(Object params) throws BasicException;
    
    /**
     *
     * @param offset
     * @param length
     * @return
     * @throws BasicException
     */
    public List listPage(int offset, int length) throws BasicException;

    /**
     *
     * @param params
     * @param offset
     * @param length
     * @return
     * @throws BasicException
     */
    public List listPage(Object params, int offset, int length) throws BasicException;    
}
