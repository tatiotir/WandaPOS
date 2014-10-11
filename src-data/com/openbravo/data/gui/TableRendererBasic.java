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

package com.openbravo.data.gui;

import com.openbravo.format.Formats;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author JG uniCenta
 */
public class TableRendererBasic extends DefaultTableCellRenderer {
    
    private Formats[] m_aFormats;
    
    /** Creates a new instance of TableRendererBasic
     * @param aFormats */
    public TableRendererBasic(Formats[] aFormats) {
        m_aFormats = aFormats;
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){

        JLabel aux = (JLabel) super.getTableCellRendererComponent(table, null, isSelected, hasFocus, row, column);
        
        aux.setText(m_aFormats[column].formatValue(value));
        aux.setHorizontalAlignment(m_aFormats[column].getAlignment());

        return aux;
    }    
}
