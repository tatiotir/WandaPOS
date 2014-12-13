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
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author JG uniCenta
 */
public class SerializerReadBasic implements SerializerRead {
    
    private Datas[] m_classes;
    
    /** Creates a new instance of SerializerReadBasic
     * @param classes */
    public SerializerReadBasic(Datas[] classes) {
        m_classes = classes;
    }
    
    public SerializerReadBasic() {
        
    }
    
    public void addClasse(Datas dataClass) {
        ArrayList list = new ArrayList<>();
        if (m_classes.length != 0)
            list.addAll(Arrays.asList(m_classes));
        
        list.add(dataClass);
        m_classes = (Datas[]) list.toArray();
    }
    
    /**
     *
     * @param dr
     * @return
     * @throws BasicException
     */
    public Object readValues(DataRead dr) throws BasicException {
        
        Object[] m_values = new Object[m_classes.length];
        for (int i = 0; i < m_classes.length; i++) {
            m_values[i] = m_classes[i].getValue(dr, i + 1);
        }
        return m_values;
    }    
}
